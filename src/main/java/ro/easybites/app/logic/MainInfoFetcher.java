package ro.easybites.app.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.bson.types.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.easybites.app.model.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainInfoFetcher {

    private final MongoTemplate template;
    private final String EMAIL_FROM = "support@easybites.ro";
    private final String PREFIX = "http://easybitez.ro";
    private final String START = "recuperare-parola?";
    private final String ACCTIVATE = "activate-account?token=";

    public MainInfoFetcher(MongoTemplate template) {
        this.template = template;
    }

    public SimpleUser login(String email) {
        List<SimpleUser> result = template.find(new Query().addCriteria(Criteria.where("email").is(email)), SimpleUser.class);
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    public List<Reteta> getAllRecipes() {
        return template.findAll(Reteta.class);
    }

    public List<Order> getAllOrders(String startDateStr, String endDateStr) {

        Date startDate = new Date();
        Date endDate = new Date();

        try {
            startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateStr);
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        Query q = new Query();
        q.addCriteria(Criteria.where("date").gt(startDate).lt(endDate));
        return template.find(q, Order.class);
    }

    public List<Reteta> fetchReteteAfterType(String type) {
        if (type.isEmpty())
            return null;
        return template.find(new Query().addCriteria(Criteria.where("categorie").is(type)), Reteta.class);
    }

    public Reteta fetchReteteAfterID(String ID) {
        List<Reteta> l = template.find(new Query().addCriteria(Criteria.where("ID").is(ID)), Reteta.class);
        return l.get(0);
    }

    public List<Box> getAllBoxes() {
        return template.findAll(Box.class);
    }

    public void contactStaff(ContactRequest c) {
        //TODO: send email to staff
        PersonsContacted p = new PersonsContacted(c.getMessage(), c.getSubiect(), c.getName(), c.getEmail());
        template.save(p);
    }

    public String[] getDeliveryDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        if (day >= Calendar.MONDAY && day <= Calendar.THURSDAY) {
            LocalDateTime date = LocalDateTime.now().plusDays(Calendar.SATURDAY - day);
            return new String[]{"Sâmbătă", dtf.format(date), "Marți", dtf.format(date.plusDays(3))};
        } else {
            LocalDateTime date = LocalDateTime.now().plusDays(Calendar.SATURDAY - day + 3);
            return new String[]{"Marți", dtf.format(date), "Sâmbătă", dtf.format(date.plusDays(4))};
        }
    }

    public Box getBoxById(String ID) {
        return template.find(new Query().addCriteria(Criteria.where("ID").is(ID)), Box.class).get(0);
    }

    public List<String> getReteteID() {
        return template.findAll(Reteta.class).stream().map(Reteta::getID).collect(Collectors.toList());
    }

    public Integer getCodeReduction(String code) {
        try {
            return template.find(new Query().addCriteria(Criteria.where("codeID").is(code)), Codes.class).get(0).getReducePercentage();
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public ResponseEntity<String> registerUser(RegisterUserRequest r) {
        try {
            template.find(new Query(new Criteria().orOperator(Criteria.where("email").is(r.getEmail()), Criteria.where("telefon").is(r.getTel()))), SimpleUser.class).get(0);
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        } catch (IndexOutOfBoundsException e) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            UUID uuid = UUID.randomUUID();
            SimpleUser u = new SimpleUser(r.getUsername(), r.getEmail(), encoder.encode(r.getPass()), r.getTel(), "", "", "", "", "", 0, "ROLE_USER", false, uuid.toString());
            MailSender m = new MailSender(
                    r.getEmail(),
                    "Aceasta este linkul de activare a contului",
                    "Pentru a-ti activa contul, acceseaza " + PREFIX + "/" + ACCTIVATE + uuid
            );
            if(m.sendEmail()) {
                template.save(u);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public void placeOrder(HttpSession session) throws ParseException {
        String id = "-1";

        if (SecurityContextHolder.getContext().getAuthentication() != null)
            id = SecurityContextHolder.getContext().getAuthentication().getName();
        String deliveryDate = ((String) session.getAttribute("date")).replace("-", "").trim();

        List<String> foodTitle = new ArrayList<>();
        for(String foodID : (List<String>) session.getAttribute("mancare")) {
            if(!foodID.equals("-1"))
                foodTitle.add(
                        template.find(new Query().addCriteria(Criteria.where("ID").is(foodID)), Reteta.class).get(0).getName()
                );
        }

        Order o = new Order(
                new Date(),
                new SimpleDateFormat("dd/MM/yyyy").parse(deliveryDate),
                (String) session.getAttribute("boxID"),
                (List<String>) session.getAttribute("mancare"),
                foodTitle,
                (HashMap<String, String>) session.getAttribute("serializedParams"),
                null,
                id,
                (String) session.getAttribute("codeUsed"),
                String.valueOf(session.getAttribute("pret"))
        );
        template.save(o);

        Dump dump = new Dump(o);
        template.save(dump);
    }

    public Codes getCode(String code) {
        List<Codes> c = template.find(new Query().addCriteria(Criteria.where("codeID").is(code)), Codes.class);
        try {
            return c.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean findNewPassRequest(String email, String acctoken) {
        System.out.println(email + " " + acctoken + " <- infetcher");
        return template.find(
                new Query().addCriteria(
                        new Criteria().andOperator(
                                Criteria.where("email").is(email),
                                Criteria.where("uuid").is(acctoken)
                        )
                ),
                PassChangeRequest.class
        ).size() == 1;
    }

    public ResponseEntity<String> generateResetPass(String email) {
        UUID uuid = UUID.randomUUID();
        if (template.find(new Query().addCriteria(Criteria.where("email").is(email)), SimpleUser.class).isEmpty())
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        template.findAndRemove(new Query().addCriteria(Criteria.where("email").is(email)), PassChangeRequest.class);

        MailSender m = new MailSender(
                email,
                "Acesta este linkul de acces pentru resetarea parolei",
                "Buna, acceseaza acest link si schimba parola. Link: " + PREFIX + "/" + START + "email=" + email + "&acctoken=" + uuid.toString()
        );
        if(m.sendEmail()) {
            template.save(new PassChangeRequest(email, uuid.toString()));
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    public ResponseEntity<String> resetPass(String email, String pass) {
        template.findAndRemove(new Query().addCriteria(Criteria.where("email").is(email)), PassChangeRequest.class);
        List<SimpleUser> k = template.find(new Query().addCriteria(Criteria.where("email").is(email)), SimpleUser.class);
        if (k.isEmpty())
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            k.get(0).setPassword(encoder.encode(pass));
            template.remove(new Query().addCriteria(Criteria.where("email").is(email)), SimpleUser.class);
            template.save(k.get(0));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean activateAccountRequest(String token){
        List<SimpleUser> userNotActivated = template.find(new Query().addCriteria(Criteria.where("uuid").is(token)), SimpleUser.class);
        if(userNotActivated.size() == 1) {
            userNotActivated.get(0).setUuid("");
            userNotActivated.get(0).setActivated(true);
            template.remove(new Query().addCriteria(Criteria.where("uuid").is(token)), SimpleUser.class);
            template.save(userNotActivated.get(0));
            return true;
        } else return false;
    }

    public ResponseEntity<String> updateUser(UpdateAdressRequest request) {
        List<SimpleUser> k = template.find(new Query().addCriteria(Criteria.where("ID").is(request.getID())), SimpleUser.class);
        try {
            SimpleUser user = k.get(0);
            user.setAdresa(request.getAdresa());
            user.setNr(request.getNr());
            user.setStrada(request.getStrada());
            user.setSector(request.getJudet());

            template.remove(new Query().addCriteria(Criteria.where("ID").is(request.getID())), SimpleUser.class);
            template.save(user);

            return new ResponseEntity<>("Date schimbate cu success", HttpStatus.OK);
        } catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new ResponseEntity<>("A aparut o eroare", HttpStatus.OK);
        }
    }

    public List<Order> getUserOrders(String ID){
        return template.find(new Query().addCriteria(Criteria.where("userID").is(ID)), Order.class);
    }

    public void addTracker(String remoteAddr, String access) {
        template.save(new Tracker(remoteAddr, access));
    }
}

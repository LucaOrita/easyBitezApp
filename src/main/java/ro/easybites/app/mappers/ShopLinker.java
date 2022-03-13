package ro.easybites.app.mappers;

import org.bson.types.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.*;
import ro.easybites.app.logic.MainInfoFetcher;
import ro.easybites.app.model.*;
import ro.easybites.app.user_files.EasyUserDetails;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopLinker {

    private final MainInfoFetcher fetcher;
    private final List<String> ids;

    @Autowired
    public ShopLinker(MongoTemplate template) {
        fetcher = new MainInfoFetcher(template);
        ids = fetcher.getReteteID();
    }

    @GetMapping("/shop")
    public String startShop(Model model, Authentication auth, HttpServletRequest request) {
        if(auth != null && auth.isAuthenticated())
            return "redirect:/shop/1";
        model.addAttribute("step", 1);
        buildStep1(model);
        return "shop/shop_login_point";
    }


    //-------- CONSTRUCTING THE SHOP ------------//

    @GetMapping("/shop/{step}")
    public String contructShop(HttpServletRequest req, @PathVariable Integer step, Model model, HttpSession session, Authentication auth) throws IOException {
        if (step == 1 || step == 2 || step == 3 || step == 4 || step == 5) {
            session.setAttribute("reduction", false);
            model.addAttribute("step", step);
            switch (step) {
                case 1: {
                    fetcher.addTracker(req.getRemoteAddr(), "step1");
                    buildStep1(model);
                    return "shop/shop_pagina_1";
                }
                case 2:
                    return buildStep2(model, session);
                case 3: {
                    if (session.getAttribute("boxID") == null)
                        return "shop/start";
                    Box selectedbox = fetcher.getBoxById((String) session.getAttribute("boxID"));
                    if (selectedbox.getDays() == 7) {
                        return buildStep3(model, session, selectedbox);
                    } else return makeCheckout(model, session, selectedbox, auth);
                }
                case 4: {
                    if (session.getAttribute("boxID") == null)
                        return "shop/start";
                    Box selectedbox = fetcher.getBoxById((String) session.getAttribute("boxID"));

                    return makeCheckout(model, session, selectedbox, auth);
                }
            }
        }
        return "shop/start";
    }

    @GetMapping("/na/shop/{step}")
    public String contructNaShop(@PathVariable Integer step, Model model, HttpServletRequest req) throws IOException {
        if (step == 1 || step == 2 || step == 3 || step == 4 || step == 5) {
            HttpSession session = req.getSession(true);
            session.setAttribute("reduction", false);
            model.addAttribute("step", step);
            switch (step) {
                case 1: {
                    fetcher.addTracker(req.getRemoteAddr(), "step1");
                    buildStep1(model);
                    return "shop/shop_pagina_1";
                }
                case 2:
                    return buildStep2(model, session);
                case 3: {
                    if (session.getAttribute("boxID") == null)
                        return "shop/start";
                    Box selectedbox = fetcher.getBoxById((String) session.getAttribute("boxID"));
                    if (selectedbox.getDays() == 7) {
                        return buildStep3(model, session, selectedbox);
                    } else return makeCheckout(model, session, selectedbox, null);
                }
                case 4: {
                    if (session.getAttribute("boxID") == null)
                        return "shop/start";
                    Box selectedbox = fetcher.getBoxById((String) session.getAttribute("boxID"));

                    return makeCheckout(model, session, selectedbox, null);
                }
            }
        }
        return "shop/start";
    }

    private void buildStep1(Model model) {
        String[] getDates = fetcher.getDeliveryDate();
        model.addAttribute("boxes", fetcher.getAllBoxes());
        model.addAttribute("livrare1d", getDates[0]);
        model.addAttribute("livrare1date", "- " + getDates[1] + " -");
        model.addAttribute("livrare2d", getDates[2]);
        model.addAttribute("livrare2date", " -" + getDates[3] + " -");
    }

    private String buildStep2(Model model, HttpSession session) {
        if (session.getAttribute("boxID") == null || session.getAttribute("day") == null || session.getAttribute("date") == null)
            return "shop/start";
        model.addAttribute("recipes", fetcher.getAllRecipes());
        Box selectedbox = fetcher.getBoxById((String) session.getAttribute("boxID"));

        if (selectedbox.getDays() == 7) {
            model.addAttribute("days", 2);
            model.addAttribute("titleOfSlide", "PRIMA LIVRARE");
            model.addAttribute("cday", 1);
            if (session.getAttribute("day").equals("Sâmbătă")) {
                session.setAttribute("dayToCheck", 3);
                model.addAttribute("dates_1", "(Duminică - Marți)");
                model.addAttribute("dates_2", "(Miercuri - Sâmbătă)");
                session.setAttribute("dates_1", model.getAttribute("dates_1"));
                session.setAttribute("dates_2", model.getAttribute("dates_2"));
                buildPortii(model, selectedbox.getNrPers());
                return "shop/shop_casute_3";
            } else {
                session.setAttribute("dayToCheck", 4);
                model.addAttribute("dates_1", "(Miercuri - Sâmbătă)");
                model.addAttribute("dates_2", "(Duminică - Marți)");
                session.setAttribute("dates_1", model.getAttribute("dates_1"));
                session.setAttribute("dates_2", model.getAttribute("dates_2"));
                buildPortii(model, selectedbox.getNrPers());
                return "shop/shop_casute_4";
            }
        } else {
            model.addAttribute("days", 1);
            model.addAttribute("cday", 1);
            if (selectedbox.getDays() == 4) {
                model.addAttribute("titleOfSlide", "CELE 4 ZILE");
                session.setAttribute("dayToCheck", 4);
                if (session.getAttribute("day").equals("Sâmbătă")) {
                    model.addAttribute("dates_1", "(Sâmbătă - Miercuri)");
                    session.setAttribute("dates_1", model.getAttribute("dates_1"));
                } else {
                    model.addAttribute("dates_1", "(Marți - Vineri)");
                    session.setAttribute("dates_1", model.getAttribute("dates_1"));
                }
                buildPortii(model, selectedbox.getNrPers());
                return "shop/shop_casute_4";
            } else {
                model.addAttribute("titleOfSlide", "CELE 3 ZILE");
                session.setAttribute("dayToCheck", 3);
                if (session.getAttribute("day").equals("Sâmbătă")) {
                    model.addAttribute("dates_1", "(Duminică - Marți)");
                    session.setAttribute("dates_1", model.getAttribute("dates_1"));
                } else {
                    model.addAttribute("dates_1", "(Miercuri - Sâmbătă)");
                    session.setAttribute("dates_1", model.getAttribute("dates_1"));
                }
                session.setAttribute("dates_1", model.getAttribute("dates_1"));
                buildPortii(model, selectedbox.getNrPers());
                return "shop/shop_casute_3";
            }
        }
    }

    private String buildStep3(Model model, HttpSession session, Box selectedbox) {
        if (session.getAttribute("mancare") == null)
            return "shop/start";

        List<String> links = new ArrayList<>();
        model.addAttribute("recipes", fetcher.getAllRecipes());

        List<String> l = (List<String>) session.getAttribute("mancare");
        for (int i = 0; i < (Integer) session.getAttribute("dayToCheck"); i++) {
            String pic = fetcher.fetchReteteAfterID(l.get(i)).getPics().get(0);
            links.add(pic);
        }

        model.addAttribute("recipesLinks", links);

        if ((int) session.getAttribute("dayToCheck") == 3) {
            model.addAttribute("recipesAllowed", 4);
        } else {
            model.addAttribute("recipesAllowed", 3);
        }

        buildPortii(model, selectedbox.getNrPers());
        return "shop/shop_pagina_3";
    }

    //-------- END CONSTRUCTING THE SHOP ----------//

    private String makeCheckout(Model model, HttpSession session, Box selectedbox, Authentication auth) {
        if(auth != null) {
            EasyUserDetails e = ((EasyUserDetails) auth.getPrincipal());
            model.addAttribute("ud_username", e.getSimpleUser().getUsername());
            model.addAttribute("ud_email", e.getSimpleUser().getEmail());
            model.addAttribute("ud_telefon", e.getSimpleUser().getTelefon());
            model.addAttribute("ud_sector", e.getSimpleUser().getSector());
            model.addAttribute("ud_strada", e.getSimpleUser().getStrada());
            model.addAttribute("ud_nr", e.getSimpleUser().getNr());
            model.addAttribute("ud_adresa", e.getSimpleUser().getAdresa());
        }

        model.addAttribute("days", selectedbox.getDays());
        if(!((boolean) session.getAttribute("codeApplied"))) {
            model.addAttribute("pret", selectedbox.getPret());
            session.setAttribute("pret", Double.valueOf(selectedbox.getPret()));
        } else model.addAttribute("pret", session.getAttribute("pret"));
        model.addAttribute("dates_1", session.getAttribute("dates_1"));
        model.addAttribute("dates_2", session.getAttribute("dates_2"));
        int dayToCheck = (int) session.getAttribute("dayToCheck");


        List<String> ids = (List<String>) session.getAttribute("mancare");
        List<String> links = new ArrayList<>();
        for (int k = 0; k < dayToCheck; k++) {
            links.add(fetcher.fetchReteteAfterID(ids.get(k)).getPics().get(0));
        }
        model.addAttribute("pics_1", links);

        if (selectedbox.getDays() == 7) {
            links = new ArrayList<>();
            for (int k = dayToCheck; k < 7; k++) {
                links.add(fetcher.fetchReteteAfterID(ids.get(k)).getPics().get(0));
            }
            model.addAttribute("pics_2", links);
        }
        buildPortii(model, selectedbox.getNrPers());
        return "shop/checkout";
    }

    // -----------------BOX SELECTION--------------- //

    @PostMapping("/shop/select/box")
    public ResponseEntity<String> selectBox(@RequestBody RequestBoxID box, HttpSession session) {
        session.setAttribute("boxID", box.getID());
        session.setAttribute("codeApplied", false);
        session.setAttribute("mancare", new ArrayList<>(Arrays.asList("-1", "-1", "-1", "-1", "-1", "-1", "-1")));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/na/shop/select/box")
    public ResponseEntity<String> selectBoxNa(@RequestBody RequestBoxID box, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.setAttribute("boxID", box.getID());
        session.setAttribute("codeApplied", false);
        session.setAttribute("mancare", new ArrayList<>(Arrays.asList("-1", "-1", "-1", "-1", "-1", "-1", "-1")));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // ---------------END BOX SELECTION--------------- //


    // -----------------SETTING DATE--------------- //

    @PostMapping("/shop/select/date")
    public ResponseEntity<String> selectDate(@RequestBody RequestDayAndDate dd, HttpSession session) {
        session.setAttribute("date", dd.getDate());
        session.setAttribute("day", dd.getDay());
        session.setAttribute("mancare", new ArrayList<>(Arrays.asList("-1", "-1", "-1", "-1", "-1", "-1", "-1")));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/na/shop/select/date")
    public ResponseEntity<String> selectDateNa(@RequestBody RequestDayAndDate dd, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.setAttribute("date", dd.getDate());
        session.setAttribute("day", dd.getDay());
        session.setAttribute("mancare", new ArrayList<>(Arrays.asList("-1", "-1", "-1", "-1", "-1", "-1", "-1")));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // ---------------END SETTING DATE--------------- //


    //------------------- ADD SHOP ------------------//

    @PostMapping("shop/add/{food}/{page}/{id}")
    public ResponseEntity<String> addFood(@PathVariable String food, @PathVariable String id, @PathVariable Integer page, HttpSession session) {
        switch (food) {
            case "mancare":
                List<String> fids = (List<String>) session.getAttribute("mancare");
                int position = fids.indexOf("-1");

                if (page == 1)
                    if (position < (Integer) session.getAttribute("dayToCheck")) {
                        fids.set(position, id);
                        session.setAttribute("mancare", fids);
                    } else return new ResponseEntity<>(HttpStatus.CONFLICT);
                else if (page == 2) {
                    if (position >= (Integer) session.getAttribute("dayToCheck")) {
                        fids.set(position, id);
                        session.setAttribute("mancare", fids);
                    } else return new ResponseEntity<>(HttpStatus.CONFLICT);
                } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

                return new ResponseEntity<>(String.valueOf(fids.size()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/na/shop/add/{food}/{page}/{id}")
    public ResponseEntity<String> addFoodNa(@PathVariable String food, @PathVariable String id, @PathVariable Integer page, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        switch (food) {
            case "mancare":
                List<String> fids = (List<String>) session.getAttribute("mancare");
                int position = fids.indexOf("-1");

                if (page == 1)
                    if (position < (Integer) session.getAttribute("dayToCheck")) {
                        fids.set(position, id);
                        session.setAttribute("mancare", fids);
                    } else return new ResponseEntity<>(HttpStatus.CONFLICT);
                else if (page == 2) {
                    if (position >= (Integer) session.getAttribute("dayToCheck")) {
                        fids.set(position, id);
                        session.setAttribute("mancare", fids);
                    } else return new ResponseEntity<>(HttpStatus.CONFLICT);
                } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

                return new ResponseEntity<>(String.valueOf(fids.size()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //--------------- END ADD SHOP ------------------//


    //------------------ REMOVE SHOP ------------------//

    @PostMapping("shop/remove/{food}/{id}/{place}")
    public ResponseEntity<String> deleteFood(@PathVariable String food, @PathVariable String id, @PathVariable Integer place, HttpSession session) {
        switch (food) {
            case "mancare":
                List<String> fids = (List<String>) session.getAttribute("mancare");

                if (fids.contains(id)) {
                    fids.set(place, "-1");

                    return new ResponseEntity<>(HttpStatus.OK);
                } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/na/shop/remove/{food}/{id}/{place}")
    public ResponseEntity<String> deleteFoodNa(@PathVariable String food, @PathVariable String id, @PathVariable Integer place, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        switch (food) {
            case "mancare":
                List<String> fids = (List<String>) session.getAttribute("mancare");
                if (fids.contains(id)) {
                    fids.set(place, "-1");

                    return new ResponseEntity<>(HttpStatus.OK);
                } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //--------------- END REMOVE SHOP ------------------//


    //------------------CHECKING STATS-----------------//

    @GetMapping("/shop/check/{page}")
    public Object check(@PathVariable Integer page, HttpSession session) {
        switch (page) {
            case 1: {
                List<String> l = new ArrayList<>();
                l.add((String) session.getAttribute("date"));
                l.add((String) session.getAttribute("day"));
                l.add((String) session.getAttribute("boxID"));
                return new ResponseEntity<>(l, HttpStatus.OK);
            }
            case 2: {
                List<String> l = (List<String>) session.getAttribute("mancare");
                if (l.equals(new ArrayList<>(Arrays.asList("-1", "-1", "-1", "-1", "-1", "-1", "-1"))))
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                for (int i = 0; i < (Integer) session.getAttribute("dayToCheck"); i++)
                    if (l.get(i).equals("-1"))
                        return new ResponseEntity<>(HttpStatus.CONFLICT);

//                session.setAttribute("codeApplied", false);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            case 3: {
                List<String> l = (List<String>) session.getAttribute("mancare");
                if (l.equals(new ArrayList<>(Arrays.asList("-1", "-1", "-1", "-1", "-1", "-1", "-1"))))
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                for (int i = (Integer) session.getAttribute("dayToCheck"); i < 7; i++)
                    if (l.get(i).equals("-1"))
                        return new ResponseEntity<>(HttpStatus.CONFLICT);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/na/shop/check/{page}")
    public Object checkNa(@PathVariable Integer page, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        switch (page) {
            case 1: {
                List<String> l = new ArrayList<>();
                l.add((String) session.getAttribute("date"));
                l.add((String) session.getAttribute("day"));
                l.add((String) session.getAttribute("boxID"));
                return new ResponseEntity<>(l, HttpStatus.OK);
            }
            case 2: {
                List<String> l = (List<String>) session.getAttribute("mancare");
                if (l.equals(new ArrayList<>(Arrays.asList("-1", "-1", "-1", "-1", "-1", "-1", "-1"))))
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                for (int i = 0; i < (Integer) session.getAttribute("dayToCheck"); i++)
                    if (l.get(i).equals("-1"))
                        return new ResponseEntity<>(HttpStatus.CONFLICT);

//                session.setAttribute("codeApplied", false);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            case 3: {
                List<String> l = (List<String>) session.getAttribute("mancare");
                if (l.equals(new ArrayList<>(Arrays.asList("-1", "-1", "-1", "-1", "-1", "-1", "-1"))))
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                for (int i = (Integer) session.getAttribute("dayToCheck"); i < 7; i++)
                    if (l.get(i).equals("-1"))
                        return new ResponseEntity<>(HttpStatus.CONFLICT);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //----------------END CHECKING STATS-----------------//


    @PostMapping("/na/shop/delete/code")
    public ResponseEntity<String> deleteNaCode(HttpServletRequest req){
        HttpSession session = req.getSession(true);
        return deleteCodeGenerated(session);
    }

    private ResponseEntity<String> deleteCodeGenerated(HttpSession session) {
        String code = (String) session.getAttribute("codeUsed");
        Codes c = fetcher.getCode(code);
        if(c != null && c.getMenu_type().equals("percentage"))
            session.setAttribute("pret", session.getAttribute("oldPret"));
        session.setAttribute("codeApplied", false);
        session.setAttribute("addOn", "");
        session.setAttribute("codeUsed", "");
        return new ResponseEntity<>(String.valueOf(session.getAttribute("pret")), HttpStatus.OK);
    }

    @PostMapping("/shop/delete/code")
    public ResponseEntity<String> deleteCode(String n, HttpSession session){
        return deleteCodeGenerated(session);
    }

    @PostMapping("/na/shop/code")
    public ResponseEntity<String> applyCode(String code, HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        if (!((boolean) session.getAttribute("codeApplied"))) {
            Codes c = fetcher.getCode(code);
            if (c != null) {
                switch (c.getMenu_type()) {
                    case "percentage":
                        if (code.equals("easy10") || code.equals("gusturoi15") || code.equals("easy30") || code.equals("emelicious15") || code.equals("alinaedu15") || code.equals("sophie15") || code.equals("jurnaldulce15") || code.equals("rvegetariene15") || code.equals("oracudora15") || code.equals("oanastoica15") || code.equals("bebemananca15") || code.equals("BEAUTYBAROMETER15") || code.equals("ANDREEACRISTINAM15") || code.equals("ZBURATOAREARO15") || code.equals("TRENDS15") || code.equals("DANIELANICULI15")) {
                            Integer percentage = c.getReducePercentage();
                            double newPrice = ((Double) session.getAttribute("pret")) * (100 - percentage) / 100.0;
                            session.setAttribute("codeApplied", true);
                            session.setAttribute("oldPret", session.getAttribute("pret"));
                            session.setAttribute("pret", newPrice);
                            session.setAttribute("addOn", "reduction applied");
                            session.setAttribute("codeUsed", code);
                            return new ResponseEntity<>(String.valueOf(newPrice), HttpStatus.OK);
                        } else
                        return new ResponseEntity<>("-3.0", HttpStatus.OK);

                    case "offerFoodForPack":
                        return buildOfferPackCode(code, session, c);

                    default:
                        return new ResponseEntity<>("-3.0", HttpStatus.OK);
                }
            } else return new ResponseEntity<>("-1.0", HttpStatus.OK);
        } else return new ResponseEntity<>("-2.0", HttpStatus.OK);
    }

    @PostMapping("/shop/code")
    public ResponseEntity<String> applyCode(String code, HttpSession session) {
        if (!((boolean) session.getAttribute("codeApplied"))) {
            Codes c = fetcher.getCode(code);
            if (c != null) {
                switch (c.getMenu_type()) {
                    case "percentage":
                        {
                            Integer percentage = c.getReducePercentage();
                            double newPrice = ((Double) session.getAttribute("pret")) * (100 - percentage) / 100.0;
                            session.setAttribute("codeApplied", true);
                            session.setAttribute("oldPret", session.getAttribute("pret"));
                            session.setAttribute("pret", newPrice);
                            session.setAttribute("addOn", "reduction applied");
                            session.setAttribute("codeUsed", code);
                            return new ResponseEntity<>(String.valueOf(newPrice), HttpStatus.OK);
                        }
                    case "offerFoodForPack":
                        return buildOfferPackCode(code, session, c);

                    default:
                        return new ResponseEntity<>("-3.0", HttpStatus.OK);
                }
            } else return new ResponseEntity<>("-1.0", HttpStatus.OK);
        } else return new ResponseEntity<>("-2.0", HttpStatus.OK);
    }

    private ResponseEntity<String> buildOfferPackCode(String code, HttpSession session, Codes c) {
        if (code.equals("ong30")) {
            double percentagePrice = ((Double) session.getAttribute("pret")) * (30) / 100.0;
            session.setAttribute("codeApplied", true);
            session.setAttribute("addOn", "reduction applied");
            session.setAttribute("codeUsed", code);
            return new ResponseEntity<>(" " + String.valueOf(percentagePrice) + " lei vor fi donati catre " + "<a href='https://www.instagram.com/easybitez_ro/'>ONG</a>", HttpStatus.OK);
        } else
        if (code.equals("vday") &&
                fetcher.getBoxById((String) session.getAttribute("boxID")).getNrPers() == 1) {

            session.setAttribute("codeApplied", true);
            session.setAttribute("addOn", "vin proseco");
            session.setAttribute("codeUsed", code);
            return new ResponseEntity<>("Vei primi la livrare o noua sticla de vin ", HttpStatus.OK);

        } else
        if (code.equals("vday") &&
                fetcher.getBoxById((String) session.getAttribute("boxID")).getNrPers() == 2) {

            session.setAttribute("codeApplied", true);
            session.setAttribute("addOn", "vin proseco");
            session.setAttribute("codeUsed", code);
            return new ResponseEntity<>("Vei primi la livrare doua sticle de vin ", HttpStatus.OK);

        } else
        if (c.getScope().equals("single") &&
                fetcher.getBoxById((String) session.getAttribute("boxID")).getNrPers() == 1) {

            session.setAttribute("codeApplied", true);
            session.setAttribute("addOn", "desert");
            session.setAttribute("codeUsed", code);
            return new ResponseEntity<>("Vei primi un desert pentru fiecare zi din livrare!", HttpStatus.OK);

        } else if (c.getScope().equals("family") &&
                fetcher.getBoxById((String) session.getAttribute("boxID")).getNrPers() == 2) {

            session.setAttribute("codeApplied", true);
            session.setAttribute("addOn", "vin " + code.replace("easy", ""));
            session.setAttribute("codeUsed", code);
            return new ResponseEntity<>("Vei primi la livrare o noua sticlă de vin " + code.replace("easy", ""), HttpStatus.OK);

        } else return new ResponseEntity<>("-3.0", HttpStatus.OK);
    }

    @GetMapping("/shop/pay")
    public Object buy(HttpSession session, Model model, HttpServletRequest req) throws ParseException {

        if (session.getAttribute("boxID") == null)
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        if (session.getAttribute("mancare") == null || ((ArrayList<String>) session.getAttribute("mancare")).isEmpty())
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        if (session.getAttribute("serializedParams") == null)
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);

        HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("serializedParams");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            model.addAttribute(key, value);
        }

        fetcher.placeOrder(session);
        fetcher.addTracker(req.getRemoteAddr(), "pay");

        //plata online

            return "shop/redirect_payment";

//            return "shop/ramburs.html";
    }

    @GetMapping("/shop/payramburs")
    public Object buyramburs(HttpSession session, Model model, HttpServletRequest req) throws ParseException {

        if (session.getAttribute("boxID") == null)
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        if (session.getAttribute("mancare") == null || ((ArrayList<String>) session.getAttribute("mancare")).isEmpty())
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        if (session.getAttribute("serializedParams") == null)
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);

        HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("serializedParams");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            model.addAttribute(key, value);
        }

        fetcher.placeOrder(session);
        fetcher.addTracker(req.getRemoteAddr(), "pay");


        return "shop/ramburs.html";
    }

    @GetMapping("/na/shop/pay")
    public Object buy(HttpServletRequest req, Model model) throws ParseException {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("boxID") == null)
            return new ResponseEntity<>("box", HttpStatus.PRECONDITION_FAILED);
        if (session.getAttribute("mancare") == null || ((ArrayList<String>) session.getAttribute("mancare")).isEmpty())
            return new ResponseEntity<>("mancare", HttpStatus.PRECONDITION_FAILED);
        if (session.getAttribute("serializedParams") == null)
            return new ResponseEntity<>("params", HttpStatus.PRECONDITION_FAILED);

        HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("serializedParams");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            model.addAttribute(key, value);
        }

        fetcher.placeOrder(session);
        fetcher.addTracker(req.getRemoteAddr(), "pay");


            return "shop/redirect_payment";

    }

    @GetMapping("/na/shop/payramburs")
    public Object buyramburs(HttpServletRequest req, Model model) throws ParseException {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("boxID") == null)
            return new ResponseEntity<>("box", HttpStatus.PRECONDITION_FAILED);
        if (session.getAttribute("mancare") == null || ((ArrayList<String>) session.getAttribute("mancare")).isEmpty())
            return new ResponseEntity<>("mancare", HttpStatus.PRECONDITION_FAILED);
        if (session.getAttribute("serializedParams") == null)
            return new ResponseEntity<>("params", HttpStatus.PRECONDITION_FAILED);

        HashMap<String, String> params = (HashMap<String, String>) session.getAttribute("serializedParams");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            model.addAttribute(key, value);
        }

        fetcher.placeOrder(session);
        fetcher.addTracker(req.getRemoteAddr(), "pay");



            model.addAttribute("data_livrare", session.getAttribute("date"));
            return "shop/ramburs.html";

    }

    @PostMapping("/shop/getReady")
    public ResponseEntity<String> prepare(@RequestBody LivrareFacturareRequest body, HttpSession session) {

        if (!((ArrayList<String>) session.getAttribute("mancare")).isEmpty()) {

            String mid = "44840996900";
            String key = "0ba388d7b5f12509aef35d3cd5f570faf971a8df";

            Date date = new Date();
            String dateString = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(date);

            HashMap<String, String> params = new HashMap<>();

            //SET LIVRARE DETAILS
            params.put("fname", body.getNume());
            params.put("lname", body.getNume2());
            params.put("country", "Romania");
            params.put("company", "");
            params.put("city", body.getTown());
            params.put("add", body.makeAdress());
            params.put("email", body.getNume());
            params.put("phone", body.getNrTel());
            params.put("fax", "");

            //SET FACTURARE
            params.put("sfname", body.getNume());
            params.put("slname", body.getNume2());
            params.put("scountry", "Romania");
            params.put("scompany", "");
            params.put("scity", body.getTown2());
            params.put("sadd", body.makeAdress2());
            params.put("semail", body.getNume2());
            params.put("sphone", body.getNrTel2());
            params.put("sfax", "");

            //SET DETALII
            params.put("amount", String.valueOf(session.getAttribute("pret")));
            params.put("curr", "RON");
            params.put("invoice_id", Long.toString(date.getTime()));
            params.put("order_desc", Long.toString(date.getTime()));
            params.put("merch_id", mid);
            params.put("timestamp", dateString);
            params.put("nonce", nonceGen(32));

            params.put("fp_hash", fp_hash(serializeParams(params), key));

            session.setAttribute("serializedParams", params);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/na/shop/getReady")
    public ResponseEntity<String> prepare(@RequestBody LivrareFacturareRequest body, HttpServletRequest req) {
        HttpSession session = req.getSession(true);

        if (!((ArrayList<String>) session.getAttribute("mancare")).isEmpty()) {

            String mid = "44840996900";
            String key = "0ba388d7b5f12509aef35d3cd5f570faf971a8df";

            Date date = new Date();
            String dateString = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(date);

            HashMap<String, String> params = new HashMap<>();

            //SET LIVRARE DETAILS
            params.put("fname", body.getNume());
            params.put("lname", body.getNume2());
            params.put("country", "Romania");
            params.put("company", "");
            params.put("city", body.getTown());
            params.put("add", body.makeAdress());
            params.put("email", body.getNume());
            params.put("phone", body.getNrTel());
            params.put("fax", "");

            //SET FACTURARE
            params.put("sfname", body.getNume());
            params.put("slname", body.getNume2());
            params.put("scountry", "Romania");
            params.put("scompany", "");
            params.put("scity", body.getTown2());
            params.put("sadd", body.makeAdress2());
            params.put("semail", body.getNume2());
            params.put("sphone", body.getNrTel2());
            params.put("sfax", "");

            //SET DETALII
            params.put("amount", String.valueOf(session.getAttribute("pret")));
            params.put("curr", "RON");
            params.put("invoice_id", Long.toString(date.getTime()));
            params.put("order_desc", Long.toString(date.getTime()));
            params.put("merch_id", mid);
            params.put("timestamp", dateString);
            params.put("nonce", nonceGen(32));

            params.put("fp_hash", fp_hash(serializeParams(params), key));

            session.setAttribute("serializedParams", params);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ArrayList<String> serializeParams(HashMap<String, String> params) {
        ArrayList<String> s = new ArrayList<>();
        s.add(params.get("amount"));
        s.add(params.get("curr"));
        s.add(params.get("invoice_id"));
        s.add(params.get("order_desc"));
        s.add(params.get("merch_id"));
        s.add(params.get("timestamp"));
        s.add(params.get("nonce"));
        return s;
    }

    private String nonceGen(int len) {
        String AlphaNumericString = "ABCDEF0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    private byte[] hex2byte(String key) {
        int len = key.length();
        byte[] bkey = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bkey[i / 2] = (byte) ((Character.digit(key.charAt(i), 16) << 4) + Character.digit(key.charAt(i + 1), 16));
        }
        return bkey;
    }

    //RFC2104HMAC
    private String fp_hash(ArrayList<String> s, String key) {
        StringBuffer ret = new StringBuffer();
        Formatter formatter = new Formatter();
        String t;
        Integer l;
        for (String o : s) {
            t = o.trim();
            if (t.length() == 0)
                ret.append("-");
            else {
                l = t.length();
                ret.append(l.toString());
                ret.append(t.toString());
            }
        }
        String data = ret.toString();
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(hex2byte(key), "HmacMD5");
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKeySpec);
            for (byte b : mac.doFinal(data.getBytes())) {
                formatter.format("%02x", b);
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException ignored) {
        }

        return formatter.toString().toUpperCase();
    }

    private void buildPortii(Model model, Integer nrPers) {
        if (nrPers == 2)
            model.addAttribute("nrPers", 4);
        else model.addAttribute("nrPers", 2);
    }
}

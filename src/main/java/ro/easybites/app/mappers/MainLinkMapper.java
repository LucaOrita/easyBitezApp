package ro.easybites.app.mappers;

import com.sun.mail.iap.Response;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.easybites.app.logic.MailSender;
import ro.easybites.app.logic.MainInfoFetcher;
import ro.easybites.app.model.ContactRequest;
import ro.easybites.app.model.RegisterUserRequest;
import ro.easybites.app.model.SimpleUser;
import ro.easybites.app.model.UpdateAdressRequest;
import ro.easybites.app.user_files.EasyUserDetails;
import ro.easybites.app.user_files.EasyUserService;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


@Controller
public class MainLinkMapper {

    private final MainInfoFetcher fetcher;

    public MainLinkMapper(MongoTemplate template) {
        fetcher = new MainInfoFetcher(template);
    }

    @GetMapping("/")
    public String index(HttpServletRequest req) {
        fetcher.addTracker(req.getRemoteAddr(), "enter");
        return "mainSite/index";
    }

    @GetMapping("/preturi")
    public String preturi(Model model) {
        model.addAttribute("boxes", fetcher.getAllBoxes());
        return "mainSite/preturi";
    }

    @GetMapping("/g9nnrkiayjrxwr56qdj8oysxdkve3i.html")
    public String g9nnrkiayjrxwr56qdj8oysxdkve3i() {
        return "mainSite/g9nnrkiayjrxwr56qdj8oysxdkve3i";
    }

    @GetMapping("/multumim.html")
    public String multumim() {
        return "mainSite/multumim";
    }


    //------------RETETE START---------------//

    @GetMapping("/retete")
    public String retete(Model model) {
        model.addAttribute("recipes", fetcher.getAllRecipes());
        model.addAttribute("current", "");
        return "mainSite/retete";
    }

    @GetMapping("/retete/{type}")
    public String reteteFiltered(@PathVariable String type, Model model) {
        model.addAttribute("recipes", fetcher.fetchReteteAfterType(type));
        model.addAttribute("current", type + "/");
        return "mainSite/retete";
    }

    @GetMapping("/retete/r/{id}")
    public String reteteFilteredAndOpened(@PathVariable String id, Model model) {
        model.addAttribute("recipeDetails", fetcher.fetchReteteAfterID(id));
        return "mainSite/UI/product_details";
    }

    //------------RETETE END---------------//

    @GetMapping("/dev/buy")
    public String buy(Model model) {
        model.addAttribute("recipes", fetcher.getAllRecipes());
        return "dev/mini-shop";
    }

    @PostMapping("/dev/buy/{ID}")
    public String buyID(@PathVariable String ID, HttpSession session) {
        ArrayList<String> ids = new ArrayList<>();
        if (session.getAttribute("ids") != null)
            ids = (ArrayList<String>) session.getAttribute("ids");

        System.out.println(ID);
        ids.add(ID);
        session.setAttribute("ids", ids);
        return "dev/null";
    }

    @GetMapping("/admin")
    public String constructAdmin(Model model) {
        model.addAttribute("retete", fetcher.getAllRecipes());
        model.addAttribute("orders", fetcher.getAllOrders());
        return "admin/admin";
    }



    @GetMapping("/contact")
    public String contact(Model model) {
        return "mainSite/contact";
    }

    @PostMapping("/contacted")
    public String sendContact(@ModelAttribute ContactRequest request, Model model) {
        model.addAttribute("contact", request);
        model.addAttribute("isContacted", true);
        fetcher.contactStaff(request);
        return "mainSite/contact";
    }

    @GetMapping("/about-us")
    public String aboutus() {
        return "mainSite/aboutus";
    }

    @GetMapping("/register")
    public String register() {
        return "mainSite/account/register";
    }

    @GetMapping("/login")
    public String login() {
        return "mainSite/account/login";
    }

    @GetMapping("/success_login_handler")
    public String successLoginHandler() {
        return "redirect:userpage";
    }

    @GetMapping("/error_login_handler")
    public String errorLoginHandler() {
        return "redirect:login?error";
    }

//    @PostMapping("/registeruser")
    @RequestMapping(value = "/registeruser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> registerUser(RegisterUserRequest registerRequest) {
        return fetcher.registerUser(registerRequest);
    }

    @GetMapping("/userpage")
    public String userpage(Authentication auth, Model model) {
        EasyUserDetails e = ((EasyUserDetails) auth.getPrincipal());
        model.addAttribute("user", e.getSimpleUser());
        model.addAttribute("orders", fetcher.getUserOrders(e.getSimpleUser().getEmail()));
        return "mainSite/account/userpage";
    }

    @GetMapping("/parola-pierduta")
    public String lostPassword() {
        return "mainSite/account/lost_pass_start";
    }

    @GetMapping("/recuperare-parola")
    public String saveNewPass(@RequestParam(value = "email", required = true) String email, @RequestParam(value = "acctoken", required = true) String acctoken, Model model) {
        System.out.println("data -> " + email + " " + acctoken);
        if (fetcher.findNewPassRequest(email, acctoken)) {
            model.addAttribute("email", email);
            return "mainSite/account/reset_pass_interface";
        } else return "mainSite/account/no_pass_good";
    }

    @RequestMapping(value = "/generate_reset_pass", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> registerPass(String email) {
        return fetcher.generateResetPass(email);
    }

    @RequestMapping(value = "/reset_pass", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> resetPass(String email, String password_new) {
        return fetcher.resetPass(email, password_new);
    }

    @GetMapping("/activate-account")
    public String acAccount(@RequestParam(value = "token", required = true) String accToken, Model model) {
        System.out.println("hey -> " + accToken);
        if (fetcher.activateAccountRequest(accToken)) {
            return "mainSite/account/activated";
        } else return "mainSite/account/no_pass_good";
    }

    @RequestMapping(value = "/userpage/update_details", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> updateUserDetails(UpdateAdressRequest request) {
        return fetcher.updateUser(request);
    }

}

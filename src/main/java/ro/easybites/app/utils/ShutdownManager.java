package ro.easybites.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
@Controller
class ShutdownManager {

    @Autowired
    private ApplicationContext appContext;

    public void initiateShutdown(int returnCode){
        SpringApplication.exit(appContext, () -> returnCode);
    }

    @GetMapping("/deactivate-site/{id}")
    public ResponseEntity<String> deactivateWebsite(@PathVariable String id){
        if(id.equals(new SaltGenerator().getSalt())) {
            initiateShutdown(0);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } return new ResponseEntity<>("No pass match", HttpStatus.BAD_REQUEST);
    }
}

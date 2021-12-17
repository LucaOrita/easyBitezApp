package ro.easybites.app.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SaltGenerator {

    private final String salt;

    public SaltGenerator() {
        DateFormat df = new SimpleDateFormat("ddyyyy");
        Date today = Calendar.getInstance().getTime();
        salt = df.format(today);
    }

    public String getSalt() {
        return salt;
    }
}

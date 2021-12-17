package ro.easybites.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("trackerIP")
public class Tracker {

    private String IP;
    private String access;

    public Tracker(String IP, String access) {
        this.IP = IP;
        this.access = access;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}

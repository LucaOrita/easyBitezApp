package ro.easybites.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBoxID {

    @JsonProperty("boxID")
    private String boxID;

    public RequestBoxID(String boxID) {
        this.boxID = boxID;
    }

    public RequestBoxID(){}

    public String getID() {
        return boxID;
    }

    public void setID(String boxID) {
        this.boxID = boxID;
    }
}

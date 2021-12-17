package ro.easybites.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDayAndDate {

    @JsonProperty("day")
    private String day;
    @JsonProperty("date")
    private String date;

    public RequestDayAndDate(String day, String date) {
        this.day = day;
        this.date = date;
    }

    public RequestDayAndDate(){

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

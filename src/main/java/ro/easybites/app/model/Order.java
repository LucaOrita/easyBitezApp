package ro.easybites.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Document("orders")
public class Order {

    @Id
    private String id;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date date;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date datePlaced;
    private String boxID;
    private List<String> food;
    private List<String> foodTitle;
    private HashMap<String, String> livrare;
    private HashMap<String, String> facturare;
    private String userID;
    private String codeUsed;
    private String price;

    public Order(Date datePlaced, Date date, String boxID, List<String> food, List<String> foodTitle, HashMap<String, String> livrare, HashMap<String, String> facturare, String userID, String codeUsed, String price) {
        this.datePlaced = datePlaced;
        this.date = date;
        this.boxID = boxID;
        this.food = food;
        this.foodTitle = foodTitle;
        this.livrare = livrare;
        this.facturare = facturare;
        this.userID = userID;
        this.codeUsed = codeUsed;
        this.price = price;
    }

    public List<String> getFoodTitle() {
        return foodTitle;
    }

    public Date getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Date date) {
        this.datePlaced = date;
    }

    public String getDatePlacedString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.datePlaced);
    }

    public void setFoodTitle(List<String> foodTitle) {
        this.foodTitle = foodTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBoxID() {
        return boxID;
    }

    public void setBoxID(String boxID) {
        this.boxID = boxID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.date);
    }

    public List<String> getFood() {
        return food;
    }

    public void setFood(ArrayList<String> food) {
        this.food = food;
    }

    public HashMap<String, String> getLivrare() {
        return livrare;
    }

    public void setLivrare(HashMap<String, String> livrare) {
        this.livrare = livrare;
    }

    public HashMap<String, String> getFacturare() {
        return facturare;
    }

    public String getLivrareFormated(){
        return livrare.toString();
    }

    public void setFacturare(HashMap<String, String> facturare) {
        this.facturare = facturare;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCodeUsed() {
        return codeUsed;
    }

    public void setCodeUsed(String codeUsed) {
        this.codeUsed = codeUsed;
    }
}

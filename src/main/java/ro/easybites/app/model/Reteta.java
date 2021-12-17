package ro.easybites.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("recipes")
public class Reteta {

    @Id
    String ID;

    String name;
    String innerName;
    String option;
    ArrayList<String> pics;
    String durata;
    String type;
    String categorie;
    String alergii;
    Integer premium;
    Double inPlus;
    String referinta;
    String descriere;
    Double reviewScore;
    ArrayList<Ingredient> i;

    public Reteta(String name, String innerName, String option, ArrayList<String> pics, String durata, String type, String categorie, String alergii, Integer premium, Double inPlus, String referinta, String descriere, Double reviewScore, ArrayList<Ingredient> i) {
        this.name = name;
        this.innerName = innerName;
        this.option = option;
        this.pics = pics;
        this.durata = durata;
        this.type = type;
        this.categorie = categorie;
        this.alergii = alergii;
        this.premium = premium;
        this.inPlus = inPlus;
        this.referinta = referinta;
        this.descriere = descriere;
        this.reviewScore = reviewScore;
        this.i = i;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public ArrayList<String> getPics() {
        return pics;
    }

    public void setPics(ArrayList<String> pics) {
        this.pics = pics;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getAlergii() {
        return alergii;
    }

    public void setAlergii(String alergii) {
        this.alergii = alergii;
    }

    public Integer getPremium() {
        return premium;
    }

    public void setPremium(Integer premium) {
        this.premium = premium;
    }

    public Double getInPlus() {
        return inPlus;
    }

    public void setInPlus(Double inPlus) {
        this.inPlus = inPlus;
    }

    public String getReferinta() {
        return referinta;
    }

    public void setReferinta(String referinta) {
        this.referinta = referinta;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Double getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Double reviewScore) {
        this.reviewScore = reviewScore;
    }

    public ArrayList<Ingredient> getI() {
        return i;
    }

    public void setI(ArrayList<Ingredient> i) {
        this.i = i;
    }
}

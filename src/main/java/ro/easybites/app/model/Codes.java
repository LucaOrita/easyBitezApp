package ro.easybites.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Codes")
public class Codes {

    @Id
    public String ID;

    public Integer reducePercentage;
    public String codeID;
    public String type;
    public String menu_type;
    public String offerOfBox;
    public String scope;

    public Codes(Integer reducePercentage, String codeID, String type, String menu_type, String offerOfBox, String scope) {
        this.reducePercentage = reducePercentage;
        this.codeID = codeID;
        this.type = type;
        this.menu_type = menu_type;
        this.offerOfBox = offerOfBox;
        this.scope = scope;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getReducePercentage() {
        return reducePercentage;
    }

    public void setReducePercentage(Integer reducePercentage) {
        this.reducePercentage = reducePercentage;
    }

    public String getCodeID() {
        return codeID;
    }

    public void setCodeID(String codeID) {
        this.codeID = codeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMenu_type() {
        return menu_type;
    }

    public void setMenu_type(String menu_type) {
        this.menu_type = menu_type;
    }

    public String getOfferOfBox() {
        return offerOfBox;
    }

    public void setOfferOfBox(String offerOfBox) {
        this.offerOfBox = offerOfBox;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}

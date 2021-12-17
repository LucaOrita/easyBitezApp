package ro.easybites.app.model;

public class Ingredient {

    public String name;
    public String internalName;
    public String img;
    public String masurare;
    public Integer cant;
    public double v;

    public Ingredient(String name, String internalName, String img, int cant, String masurare, double v) {
        this.name = name;
        this.internalName = internalName;
        this.cant = cant;
        this.img = img;
        this.masurare = masurare;
        this.v = v;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMasurare() {
        return masurare;
    }

    public void setMasurare(String masurare) {
        this.masurare = masurare;
    }

    public Integer getCant() {
        return cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }
}

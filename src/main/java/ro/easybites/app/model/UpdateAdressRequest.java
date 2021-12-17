package ro.easybites.app.model;

public class UpdateAdressRequest {

    private String ID;
    private String judet;
    private String strada;
    private String nr;
    private String adresa;

    public UpdateAdressRequest(String ID, String judet, String strada, String nr, String adresa) {
        this.ID = ID;
        this.judet = judet;
        this.strada = strada;
        this.nr = nr;
        this.adresa = adresa;
    }

    public UpdateAdressRequest() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}

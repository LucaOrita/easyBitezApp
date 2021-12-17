package ro.easybites.app.model;

public class LivrareFacturareRequest {

    public String detalii;
    public String detalii2;
    public String judet;
    public String judet2;
    public String nrStrada;
    public String nrStrada2;
    public String nrTel;
    public String nrTel2;
    public String nume;
    public String nume2;
    public String strada;
    public String strada2;
    public String town;
    public String town2;

    public LivrareFacturareRequest(String detalii, String detalii2, String judet, String judet2, String nrStrada, String nrStrada2, String nrTel, String nrTel2, String nume, String nume2, String strada, String strada2, String town, String town2) {
        this.detalii = detalii;
        this.detalii2 = detalii2;
        this.judet = judet;
        this.judet2 = judet2;
        this.nrStrada = nrStrada;
        this.nrStrada2 = nrStrada2;
        this.nrTel = nrTel;
        this.nrTel2 = nrTel2;
        this.nume = nume;
        this.nume2 = nume2;
        this.strada = strada;
        this.strada2 = strada2;
        this.town = town;
        this.town2 = town2;
    }

    public LivrareFacturareRequest() {
    }

    public String getDetalii() {
        return detalii;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    public String getDetalii2() {
        return detalii2;
    }

    public void setDetalii2(String detalii2) {
        this.detalii2 = detalii2;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getJudet2() {
        return judet2;
    }

    public void setJudet2(String judet2) {
        this.judet2 = judet2;
    }

    public String getNrStrada() {
        return nrStrada;
    }

    public void setNrStrada(String nrStrada) {
        this.nrStrada = nrStrada;
    }

    public String getNrStrada2() {
        return nrStrada2;
    }

    public void setNrStrada2(String nrStrada2) {
        this.nrStrada2 = nrStrada2;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public String getNrTel2() {
        return nrTel2;
    }

    public void setNrTel2(String nrTel2) {
        this.nrTel2 = nrTel2;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume2() {
        return nume2;
    }

    public void setNume2(String nume2) {
        this.nume2 = nume2;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getStrada2() {
        return strada2;
    }

    public void setStrada2(String strada2) {
        this.strada2 = strada2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTown2() {
        return town2;
    }

    public void setTown2(String town2) {
        this.town2 = town2;
    }

    public String makeAdress() {
        return "Sector: " + getJudet() + "; Strada: " + getStrada() + ", nr. " + getNrStrada() + "; Detalii : " + getDetalii();
    }

    public String makeAdress2() {
        return "Sector: " + getJudet2() + "; Strada: " + getStrada2() + ", nr. " + getNrStrada2() + "; Detalii : " + getDetalii2();
    }
}

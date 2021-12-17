package ro.easybites.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class SimpleUser {

    @Id
    private String ID;

    private String username;

    @Indexed(unique = true)
    private String email;
    private String password;
    private String telefon;
    private String adresa;
    private String oras;
    private String sector;
    private String strada;
    private String nr;
    private Integer tutorial;
    private String role;
    private Boolean isActivated;
    private String uuid;

    public SimpleUser() {}

    public SimpleUser(
            String username, String email, String password,
            String telefon, String adresa, String oras, String sector, String strada, String nr,
            Integer tutorial, String role, Boolean isActivated, String uuid) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.telefon = telefon;
        this.adresa = adresa;
        this.oras = oras;
        this.sector = sector;
        this.strada = strada;
        this.nr = nr;
        this.tutorial = tutorial;
        this.role = role;
        this.isActivated = isActivated;
        this.uuid = uuid;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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

    public Integer getTutorial() {
        return tutorial;
    }

    public void setTutorial(Integer tutorial) {
        this.tutorial = tutorial;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    @Override
    public String toString() {
        return "SimpleUser{" +
                "ID='" + ID + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefon='" + telefon + '\'' +
                ", adresa='" + adresa + '\'' +
                ", oras='" + oras + '\'' +
                ", sector='" + sector + '\'' +
                ", strada='" + strada + '\'' +
                ", nr='" + nr + '\'' +
                ", tutorial=" + tutorial +
                ", role='" + role + '\'' +
                ", isActivated=" + isActivated +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

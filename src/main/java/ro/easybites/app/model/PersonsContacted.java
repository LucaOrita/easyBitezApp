package ro.easybites.app.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document("contact_list")
public class PersonsContacted {

    private String messaje;
    private String subject;
    private String nume;
    private String email;

    public PersonsContacted(String messaje, String subject, String nume, String email) {
        this.messaje = messaje;
        this.subject = subject;
        this.nume = nume;
        this.email = email;
    }

    public String getMessaje() {
        return messaje;
    }

    public void setMessaje(String messaje) {
        this.messaje = messaje;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

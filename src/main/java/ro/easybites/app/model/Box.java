package ro.easybites.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("boxes")
public class Box {

    @Id
    private String ID;

    private String pic;
    private String numeCutie;
    private String descriere;
    private Integer pret;
    private Integer portii;
    private Integer micdejun;
    private Integer gustari;
    private String superClass;
    private Integer isLive;
    private Integer oldPret;
    private Integer nrPers;
    private Integer days;

    public Box(String pic, String numeCutie, String descriere, Integer pret, Integer portii, Integer micdejun, Integer gustari, String superClass, Integer isLive, Integer oldPret, Integer nrPers, Integer days) {
        this.pic = pic;
        this.numeCutie = numeCutie;
        this.descriere = descriere;
        this.pret = pret;
        this.portii = portii;
        this.micdejun = micdejun;
        this.gustari = gustari;
        this.superClass = superClass;
        this.isLive = isLive;
        this.oldPret = oldPret;
        this.nrPers = nrPers;
        this.days = days;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getNrPers() {
        return nrPers;
    }

    public void setNrPers(Integer nrPers) {
        this.nrPers = nrPers;
    }

    public Integer getOldPret() {
        return oldPret;
    }

    public void setOldPret(Integer oldPret) {
        this.oldPret = oldPret;
    }

    public String getID() {
        return ID;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNumeCutie() {
        return numeCutie;
    }

    public void setNumeCutie(String numeCutie) {
        this.numeCutie = numeCutie;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Integer getPret() {
        return pret;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    public Integer getPortii() {
        return portii;
    }

    public void setPortii(Integer portii) {
        this.portii = portii;
    }

    public Integer getMicdejun() {
        return micdejun;
    }

    public void setMicdejun(Integer micdejun) {
        this.micdejun = micdejun;
    }

    public Integer getGustari() {
        return gustari;
    }

    public void setGustari(Integer gustari) {
        this.gustari = gustari;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public Integer getIsLive() {
        return isLive;
    }

    public void setIsLive(Integer isLive) {
        this.isLive = isLive;
    }
}

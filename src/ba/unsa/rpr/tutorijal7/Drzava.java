package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;

public class Drzava implements Serializable {
    String naziv;
    int broj_stanovnika;
    double povrsina;
    String jedinica_za_povrsinu;
    Grad glavni_grad;

    public Drzava(){
        glavni_grad = new Grad();
    }

    public String getNaziv(){
        return naziv;
    }
    public void setNaziv(String naziv){
        this.naziv = naziv;
    }
    public int getBroj_stanovnika(){
        return broj_stanovnika;
    }
    public void setBroj_stanovnika(int br){
        broj_stanovnika = br;
    }
    public double getPovrsina(){
        return povrsina;
    }
    public void setPovrsina(double povrsina){
        this.povrsina = povrsina;
    }
    public String getJedinica_za_povrsinu(){
        return jedinica_za_povrsinu;
    }
    public void setJedinica_za_povrsinu(String jedinica){
        jedinica_za_povrsinu = jedinica;
    }
    public Grad getGlavni_grad(){
        return glavni_grad;
    }
    public void setGlavni_grad(Grad g){
        glavni_grad = g;
    }
}

package ba.unsa.rpr.tutorijal7;

import java.io.Serializable;

public class Grad implements Serializable {
    String naziv;
    int broj_stanovnika;
    double[] temperature = new double[1000];

    public Grad(){}

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
    public double[] getTemperature(){
        return temperature;
    }
    public void setTemperature(double[] temperature){
        this.temperature = temperature;
    }
}

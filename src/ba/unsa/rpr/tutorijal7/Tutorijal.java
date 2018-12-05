package ba.unsa.rpr.tutorijal7;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {
    public static ArrayList<Grad> ucitajGradove(){
        ArrayList<Grad> gradovi = new ArrayList<>();
        Scanner ulaz;
        try{
            ulaz = new Scanner(new FileReader("mjerenja.txt"));
            ulaz.useDelimiter("\n");
        }
        catch (FileNotFoundException e){
            System.out.println("Datoteka ne postoji, greska: " + e);
            return null;
        }
        try{
            //Sarajevo,5.4,15.8,2.3,0.44,-1.1
            //Banja Luka,8.7,20.1,5.6,4.3,2.2,1.1,-2.2,-8.777
            while(ulaz.hasNext()){
                Grad novi_grad = new Grad();
                double[] nove_temperature = new double[1000];
                String[] nova_linija = ulaz.nextLine().split(",");
                novi_grad.setNaziv(nova_linija[0]);
                for(int i=1; i < nova_linija.length; i++) {
                    if(i == 1000) break;
                    nove_temperature[i-1] = Double.parseDouble(nova_linija[i]);
                }
                novi_grad.setTemperature(nove_temperature);
                gradovi.add(novi_grad);
            }
        }
        catch (Exception e){
            System.out.println("Greska pri citanju podataka, " + e);
        }
        return null;
    }
    public static UN ucitajXML(ArrayList<Grad> gradovi){
        UN f = null;
        try {
            XMLDecoder ulaz = new XMLDecoder(new FileInputStream("drzave.xml"));
            f = (UN) ulaz.readObject();
            ulaz.close();
        } catch(Exception e) {
            System.out.println("Greška: "+e);
        }
        for(Drzava d: f.getDrzave()){
            for(int i=0; i<gradovi.size(); i++){
                if(gradovi.get(i).getNaziv().equals(d.getGlavni_grad())){
                    d.glavni_grad.setTemperature(gradovi.get(i).temperature);
                }
            }
        }
        return f;
    }
    public static void main(String[] args) {

        ucitajGradove();
    }
}

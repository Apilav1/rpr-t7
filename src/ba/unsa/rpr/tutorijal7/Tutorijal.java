package ba.unsa.rpr.tutorijal7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.io.*;
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
        UN un = new UN();
        Document xmldoc = null;
        try {
            /*DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmldoc = docReader.parse(new File("drzave.xml"));
            Element element = xmldoc.getDocumentElement();
            int brAtributa = element.getAttributes().getLength();
            NodeList djeca = element.getChildNodes();
            System.out.print(djeca.getLength());*/
            File fXmlFile = new File("drzave.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            ArrayList<Drzava> drzave2 = new ArrayList<>();
            NodeList nList = doc.getElementsByTagName("drzava");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Drzava d2 = new Drzava();
                        Element eElement = (Element) nNode;
                        System.out.println(eElement.getAttribute("brojStanovnika"));
                        d2.setBrojStanovnika(Integer.parseInt(eElement.getAttribute("brojStanovnika")));
                        System.out.println(eElement.getElementsByTagName("naziv").item(0).getTextContent());
                        d2.setNaziv(eElement.getElementsByTagName("naziv").item(0).getTextContent());
                        Grad gg = new Grad();
                        NodeList nlist2 = doc.getElementsByTagName("glavnigrad");
                        Node nNode2 = nlist2.item(temp);
                        Element e2 = (Element) nNode2;
                         System.out.println(e2.getAttribute("stanovnika"));
                         gg.setBrojStanovnika(Integer.parseInt(e2.getAttribute("stanovnika")));
                         System.out.println(e2.getElementsByTagName("nazivGlavnogGrada").item(0).getTextContent());
                         gg.setNaziv(e2.getElementsByTagName("nazivGlavnogGrada").item(0).getTextContent());
                        d2.setGlavniGrad(gg);

                       System.out.println(eElement.getElementsByTagName("povrsina").item(0).getTextContent());
                       d2.setPovrsina(Integer.parseInt(eElement.getElementsByTagName("povrsina").item(0).getTextContent()));
                       drzave2.add(d2);
                }
            }
            un.setDrzave(drzave2);
        }
        catch(Exception e) {
            System.out.println("Greška: "+e);
        }
        return un;
    }
    public static void main(String[] args) {


        //ucitajXML(ucitajGradove());
        ucitajXML(new ArrayList<Grad>());
    }
}

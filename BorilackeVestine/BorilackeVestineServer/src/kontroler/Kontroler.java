/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Kurs;
import domen.OpstiDomenskiObjekat;
import domen.StavkaZakazivanjaTreninga;
import domen.Trener;
import domen.ZakazivanjeTreninga;
import forme.ServerskeForme;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import so.OpstaSistemskaOperacija;
import so.OpstaSistemskaOperacija2;
import so.kurs.SOAzurirajKurs;
import so.kurs.SOKreirajKurs;
import so.kurs.SOObrisiKurs;
import so.kurs.SOVratiIzabraniKurs;
import so.kurs.SOVratiKurseve;
import so.kurs.SOVratiKursevePolaznika;
import so.polaznik.SOAzurirajPolaznika;
import so.polaznik.SOKreirajPolaznika;
import so.polaznik.SOVratiListuPolaznika;
import so.polaznik.SOVratiPolaznikID;
import so.stavke.SOIzbrisiStavku;
import so.stavke.SOSacuvajStavku;
import so.stavke.SOVratiListuStavki;
import so.trener.SOIzmeniTrenera;
import so.trener.SOPronadjiTrenera;
import so.trener.SOVratiImeTrenera;
import so.trener.SOVratiPrezimeTrenera;
import so.trener.SOVratiTrenerID;
import so.trening.SOVratiTreninge;
import so.zakazivanjeTreninga.SOOtkazivanje;
import so.zakazivanjeTreninga.SOPronadjiZakazaneTreninge;
import so.zakazivanjeTreninga.SOVratiZakazivanje;
import so.zakazivanjeTreninga.SOVratiZakazivanjeTreningaID;
import so.zakazivanjeTreninga.SOZakazivanjeTreninga;

/**
 *
 * @author User
 */
public class Kontroler {
    
    private static Kontroler instance;
    ServerskeForme sf;
    ArrayList<Socket> klijenti = new ArrayList<>();

    public Kontroler() {
    }

    public static Kontroler getInstance() {
        if(instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ServerskeForme getSf() {
        return sf;
    }

    public void setSf(ServerskeForme sf) {
        this.sf = sf;
    }

    public ArrayList<Socket> getKlijenti() {
        return klijenti;
    }
    
    public void dodajKlijenta(Socket socket) {
        klijenti.add(socket);
    }

     public Object pronadjiTrenera(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOPronadjiTrenera();
        oso.izvrsi(odo);
        return ((SOPronadjiTrenera) oso).getLista();
    }
    
    public int vratiTrenerID(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiTrenerID();
        oso.izvrsi(odo);
        return ((SOVratiTrenerID) oso).getId();

    }

    public String vratiImeTrenera(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiImeTrenera();
        oso.izvrsi(odo);
        return ((SOVratiImeTrenera) oso).getIme();
    }
    
    public String vratiPrezimeTrenera(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiPrezimeTrenera();
        oso.izvrsi(odo);
        return ((SOVratiPrezimeTrenera) oso).getPrezime();
    }
    
    public void izmeniTrener(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOIzmeniTrenera();
        oso.izvrsi(odo);
    }

    public void kreirajPolaznika(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOKreirajPolaznika();
        oso.izvrsi(odo);
    }

    public void kreirajKurs(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOKreirajKurs();
        oso.izvrsi(odo);
    }

    public List<OpstiDomenskiObjekat> vratiListuKurseva(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiKurseve();
        oso.izvrsi(odo);
        return ((SOVratiKurseve) oso).getLista();
    }

    public int vratiIzabranikurs(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiIzabraniKurs();
        oso.izvrsi(odo);
        return ((SOVratiIzabraniKurs) oso).getId();
    }

    public void azurirajKurs(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOAzurirajKurs();
        oso.izvrsi(odo);
    }

    public void obrisiKurs(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOObrisiKurs();
        oso.izvrsi(odo);
    }

    public ArrayList<OpstiDomenskiObjekat> vratiListuPolaznika(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiListuPolaznika();
        oso.izvrsi(odo);
        return ((SOVratiListuPolaznika) oso).getLista();
    }

    public int vratiPolaznika(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiPolaznikID();
        oso.izvrsi(odo);
        return ((SOVratiPolaznikID) oso).getId();
    }

    public void azurirajPolaznika(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOAzurirajPolaznika();
        oso.izvrsi(odo);
    }

    public ArrayList<OpstiDomenskiObjekat> vratiListuTreninga(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiTreninge();
        oso.izvrsi(odo);
        return ((SOVratiTreninge) oso).getLista();
    }

    public void zakazivanjeTreninga(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOZakazivanjeTreninga();
        oso.izvrsi(odo);
    }

    public boolean postojiZakazivanje(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiZakazivanje();
        oso.izvrsi(odo);
        ArrayList<OpstiDomenskiObjekat> lista = ((SOVratiZakazivanje) oso).getLista();
        if(lista.isEmpty()) {
            return false;
        }else {
            return true;
        }
        
    }

    public ArrayList<OpstiDomenskiObjekat> vratiZakazivanja(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOPronadjiZakazaneTreninge();
        oso.izvrsi(odo);
        return ((SOPronadjiZakazaneTreninge) oso).getLista();
    }

    public int pronadjiZakazivanje(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiZakazivanjeTreningaID();
        oso.izvrsi(odo);
        return ((SOVratiZakazivanjeTreningaID) oso).getId();
    }

    public ArrayList<OpstiDomenskiObjekat> vratiListuStavki(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOVratiListuStavki();
        oso.izvrsi(odo);
        return ((SOVratiListuStavki) oso).getLista();
    }
    
    public void sacuvajStavku(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOSacuvajStavku();
        oso.izvrsi(odo);
    }
    
    public void izbrisiStavku(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOIzbrisiStavku();
        oso.izvrsi(odo);
    }
    

    public void promeniPodatkeZakazivanje(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<StavkaZakazivanjaTreninga> novaLista = ((ZakazivanjeTreninga) odo).getStavke();
        
        StavkaZakazivanjaTreninga stavka = new StavkaZakazivanjaTreninga();
        stavka.setZakazivanjeTreninga((ZakazivanjeTreninga) odo);
        ArrayList<OpstiDomenskiObjekat> staraLista = vratiListuStavki(stavka);
        
        for(OpstiDomenskiObjekat staro: staraLista) {
            OpstaSistemskaOperacija oso = new SOIzbrisiStavku();
            oso.izvrsi(staro);
        }
        
        for(OpstiDomenskiObjekat novo: novaLista) {
            OpstaSistemskaOperacija oso = new SOSacuvajStavku();
            oso.izvrsi(novo);
        }

    }

    public void otkaizivanje(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSistemskaOperacija oso = new SOOtkazivanje();
        oso.izvrsi(odo);
    }

    //public List<OpstiDomenskiObjekat> vratiKursevePolaznika(OpstiDomenskiObjekat odo) throws Exception {
        //OpstaSistemskaOperacija oso = new SOVratiKursevePolaznika();
        //oso.izvrsi(odo);
        //return ((SOVratiKursevePolaznika) odo).getLista();
    //}

    public List<OpstiDomenskiObjekat> vratiKursevePolaznika(OpstiDomenskiObjekat odo, OpstiDomenskiObjekat odo1) throws Exception {
         
        OpstaSistemskaOperacija2 oso = new SOVratiKursevePolaznika();
        oso.izvrsi(odo, odo1);
        System.out.println(((SOVratiKursevePolaznika) oso).getLista());
        return ((SOVratiKursevePolaznika) oso).getLista();
    }

    

    


    
    
    
    
}

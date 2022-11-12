/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kurs;

import baza.DBBroker;
import domen.Kurs;
import domen.OpstiDomenskiObjekat;
import domen.Polaznik;
import java.util.List;
import so.OpstaSistemskaOperacija;
import so.OpstaSistemskaOperacija2;
import so.polaznik.SOVratiPolaznikID;

/**
 *
 * @author User
 */
public class SOVratiKursevePolaznika extends OpstaSistemskaOperacija2{
    
    List<OpstiDomenskiObjekat> lista;
    private int id;

    public List<OpstiDomenskiObjekat> getLista() {
        return lista;
    }

    public int getId() {
        return id;
    }


    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo, OpstiDomenskiObjekat odo1) throws Exception {
        if(odo == null || !(odo instanceof Kurs)) {
            throw new Exception("Greska");
        }

        
        if(odo1 == null || !(odo1 instanceof Polaznik)) {
            throw new Exception("Greska");
        }
        
        Polaznik polaznik = (Polaznik) odo1;
        if (polaznik.getIme().isEmpty())  throw new Exception("Polaznik nema uneto ime!");
        if (polaznik.getPrezime().isEmpty())  throw new Exception("Polaznik nema uneto prezime!");
        if (polaznik.getTelefon().isEmpty())  throw new Exception("Polaznik nema unet broj telefona!");
        if (polaznik.getGodina()== 0) throw new Exception("Polaznik nema unetu godinu rodjenja");
        if (polaznik.getEmail().isEmpty())  throw new Exception("Polaznik nema unet email!");
        if (polaznik.getDatumUpisa() == null)  throw new Exception("Polaznik nema unetdatum upisa!");
        
    }

    @Override
    protected void izvrsiOperaciju(OpstiDomenskiObjekat odo, OpstiDomenskiObjekat odo1) throws Exception {
        id = DBBroker.getInstance().vratiID(odo1);
        lista = DBBroker.getInstance().vratiKomplikovaniJoin(odo, id);
        System.out.println(lista);
    }


    
    
}

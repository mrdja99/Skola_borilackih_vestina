/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.polaznik;

import baza.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Polaznik;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author User
 */
public class SOAzurirajPolaznika extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
        if(odo == null || !(odo instanceof Polaznik)) {
            throw new Exception("Greska");
        }
        
        Polaznik polaznik = (Polaznik) odo;
        if (polaznik.getIme().isEmpty())  throw new Exception("Polaznik nema uneto ime!");
        if (polaznik.getPrezime().isEmpty())  throw new Exception("Polaznik nema uneto prezime!");
        if (polaznik.getTelefon().isEmpty())  throw new Exception("Polaznik nema unet broj telefona!");
        if (polaznik.getGodina()== 0) throw new Exception("Polaznik nema unetu godinu rodjenja");
        if (polaznik.getEmail().isEmpty())  throw new Exception("Polaznik nema unet email!");
        if (polaznik.getDatumUpisa() == null)  throw new Exception("Polaznik nema unetdatum upisa!");
    }

    @Override
    protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().izmeni(odo);
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.trener;

import baza.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Trener;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author User
 */
public class SOIzmeniTrenera extends OpstaSistemskaOperacija{
    

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null || !(odo instanceof Trener)){
            throw new Exception("Greska...");
        }
        
        Trener trener = (Trener) odo;
        if (trener.getIme().isEmpty())  throw new Exception("Trener nema uneto ime!");
        if (trener.getPrezime().isEmpty())  throw new Exception("Trener nema uneto prezime!");
        if (trener.getKorisnickoIme().isEmpty())  throw new Exception("Trener nema uneto korisnicko ime!");
        if (trener.getLozinka().isEmpty())  throw new Exception("Trener nema unetu lozinku!");
    }

    @Override
    protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().izmeni(odo);
    }
    
    
    
}

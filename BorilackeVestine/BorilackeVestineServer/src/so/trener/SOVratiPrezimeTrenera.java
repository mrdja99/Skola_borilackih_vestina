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
public class SOVratiPrezimeTrenera extends OpstaSistemskaOperacija{
    
    private String prezime;

    public String getPrezime() {
        return prezime;
    }

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null || !(odo instanceof Trener)){
            throw new Exception("Greska!");
        }
        
        Trener trener = (Trener) odo;
        if(trener.getKorisnickoIme().isEmpty() || trener.getLozinka().isEmpty()) throw new Exception("Trener nije uneo korisnicko ime ili lozinku!");
    }

    @Override
    protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        prezime = DBBroker.getInstance().vratiPrezime(odo);
    }
    
    
    
}

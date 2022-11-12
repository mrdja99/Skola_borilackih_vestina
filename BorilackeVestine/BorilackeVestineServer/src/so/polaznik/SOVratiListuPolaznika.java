/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.polaznik;

import baza.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Polaznik;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author User
 */
public class SOVratiListuPolaznika extends OpstaSistemskaOperacija{
    
    private ArrayList<OpstiDomenskiObjekat> lista;

    public ArrayList<OpstiDomenskiObjekat> getLista() {
        return lista;
    }


    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
        if(odo == null || !(odo instanceof Polaznik)) {
            throw new Exception("Greska");
        }
    }

    @Override
    protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        lista = DBBroker.getInstance().vratiSaUslovom(odo);
    }
    
    
    
}

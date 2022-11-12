/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import baza.DBBroker;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author User
 */
public abstract class OpstaSistemskaOperacija2{
     public void izvrsi(OpstiDomenskiObjekat odo, OpstiDomenskiObjekat odo1) throws Exception {
        try {
            proveriPreduslov(odo, odo1);
            izvrsiOperaciju(odo, odo1);
            potvrdi();
        } catch (Exception ex) {
            ponisti();
            throw new Exception("GRESKA: so -> " + ex.getMessage());
        }
    }

    protected abstract void proveriPreduslov(OpstiDomenskiObjekat odo, OpstiDomenskiObjekat odo1) throws Exception;

    protected abstract void izvrsiOperaciju(OpstiDomenskiObjekat odo, OpstiDomenskiObjekat odo1)throws Exception;

    private void potvrdi() throws Exception {
        DBBroker.getInstance().potvrdi();
    }

    private void ponisti() throws Exception {
        DBBroker.getInstance().ponisti();
    }
}

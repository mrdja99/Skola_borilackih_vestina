/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zakazivanjeTreninga;

import baza.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.ZakazivanjeTreninga;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author User
 */
public class SOVratiZakazivanjeTreningaID extends OpstaSistemskaOperacija{
    
    private int id;

    public int getId() {
        return id;
    }
    
    

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
         if (odo == null || !(odo instanceof ZakazivanjeTreninga)){
            throw new Exception("Greska...");
        }
         
        ZakazivanjeTreninga zt = (ZakazivanjeTreninga) odo;
        if (zt.getPolaznik()== null)  throw new Exception("Zakazivanje nema unetog polaznika!");
        if (zt.getTrener()== null)  throw new Exception("Zakazivanje nema unetog trenera!");
    }

    @Override
    protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        id = DBBroker.getInstance().vratiID(odo);
    }
    
    
    
}

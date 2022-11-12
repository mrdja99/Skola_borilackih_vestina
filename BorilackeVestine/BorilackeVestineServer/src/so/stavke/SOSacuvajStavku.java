/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavke;

import baza.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.StavkaZakazivanjaTreninga;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author User
 */
public class SOSacuvajStavku extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null || !(odo instanceof StavkaZakazivanjaTreninga)){
            throw new Exception("Greska");
        }
       
       StavkaZakazivanjaTreninga szt = (StavkaZakazivanjaTreninga) odo;
       if(szt.getKurs() == null) throw new Exception("Nije unet kurs");
       if(szt.getTrening() == null) throw new Exception("Nije unet trening");
       if(szt.getZakazivanjeTreninga() == null) throw new Exception("Nije uneto zakazivanje");
    }

    @Override
    protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().kreiraj2(odo);
    }
    
    
    
}

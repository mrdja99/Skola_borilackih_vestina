/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kurs;

import baza.DBBroker;
import domen.Kurs;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author User
 */
public class SOObrisiKurs extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
        if(odo == null || !(odo instanceof Kurs)) {
            throw new Exception("Greska");
        }
        
        Kurs kurs = (Kurs) odo;
        if(kurs.getNazivKursa().isEmpty()) throw new Exception("Nije unet naziv kursa");
        if(kurs.getMesto().isEmpty()) throw new Exception("Nije uneto mesto kursa");
        if(kurs.getOpis().isEmpty()) throw new Exception("Nije unet opis kursa");
        if(kurs.getVelicinaGrupe() == 0) throw new Exception("Nije uneta velicina grupe kursa");
    }

    @Override
    protected void izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().obrisi(odo);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author User
 */
public class StavkaZakazivanjaTreninga implements OpstiDomenskiObjekat{
    
    private ZakazivanjeTreninga zakazivanjeTreninga;
    private int rb;
    private Kurs kurs;
    private Trening trening;

    public StavkaZakazivanjaTreninga() {
    }

    public StavkaZakazivanjaTreninga(ZakazivanjeTreninga zakazivanjeTreninga, int rb, Kurs kurs, Trening trening) {
        this.zakazivanjeTreninga = zakazivanjeTreninga;
        this.rb = rb;
        this.kurs = kurs;
        this.trening = trening;
    }

    public ZakazivanjeTreninga getZakazivanjeTreninga() {
        return zakazivanjeTreninga;
    }

    public void setZakazivanjeTreninga(ZakazivanjeTreninga zakazivanjeTreninga) {
        this.zakazivanjeTreninga = zakazivanjeTreninga;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }
    
    @Override
    public String vratiNazivTabele() {
        return "stavkazakazivanjatreninga";
    }
    
     @Override
    public String vratiKolone() {
        return "ZakazivanjeTreningaID, RB, KursID, TreningID";
    }

    @Override
    public String vratiVrednostizaUbacivanje() {
        return rb + ", " + kurs.getKursId() + ", " + trening.getTreningId();
    }
    
    @Override
    public String vratiVrednostiZaubacivanje2() {
        return zakazivanjeTreninga.getZakazivanjeTreningaId() + ", " + rb + ", " + kurs.getKursId() + ", " + trening.getTreningId();
    }
    
    @Override
    public String vratiJoinTabelu() {
        return "kurs";
    }

    @Override
    public String vratiUslovZaJoin() {
        return "stavkazakazivanjatreninga.KursID = kurs.KursID";
    }

    @Override
    public String vratiJoinTabelu2() {
        return "trening";
    }

    @Override
    public String vratiUslovZaJoin2() {
        return "stavkazakazivanjatreninga.TreningID = trening.TreningID";
    }
    
    @Override
    public String vratiUslov() {
         return "stavkazakazivanjatreninga.ZakazivanjeTreningaID = " + zakazivanjeTreninga.getZakazivanjeTreningaId()+ " ORDER BY stavkazakazivanjatreninga.RB";
    }
    
    @Override
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()) {
            int rb = rs.getInt("stavkazakazivanjatreninga.RB");
            
            int zakazivanjeID = rs.getInt("stavkazakazivanjatreninga.ZakazivanjeTreningaID");
            ZakazivanjeTreninga zakazivanje = new ZakazivanjeTreninga();
            zakazivanje.setZakazivanjeTreningaId(zakazivanjeID);
            
            int kursID = rs.getInt("kurs.KursID");
            String naziv = rs.getString("kurs.NazivKursa");
            String mesto = rs.getString("kurs.Mesto");
            String opis = rs.getString("kurs.Opis");
            int velicinaGrupe = rs.getInt("kurs.VelicinaGrupe");
            Kurs k = new Kurs(kursID, naziv, mesto, opis, velicinaGrupe);
            
            int treningid = rs.getInt("trening.TreningID");
            Timestamp timestamp = rs.getTimestamp("trening.DatumVremeTreninga");
            Date datumVremeTreninga = new Date(timestamp.getTime());
            Trening t = new Trening(treningid, datumVremeTreninga);
            
            StavkaZakazivanjaTreninga szt = new StavkaZakazivanjaTreninga(zakazivanje, rb, k, t);
            lista.add(szt);

        }
        
        return lista;
        
    }
    
    @Override
    public String vratiVrednostiZaAzuriranje() {
        return "RB = " + rb;
    }
    
    @Override
    public String vratiUslovZaAzuriranje() {
        return " ZakazivanjeTreningaID = " + zakazivanjeTreninga.getZakazivanjeTreningaId() + " AND KursID = " + kurs.getKursId() + " AND TreningID = " + trening.getTreningId();
    }
    
    @Override
    public String vratiUslovZaBrisanje() {
        return " ZakazivanjeTreningaID = " + zakazivanjeTreninga.getZakazivanjeTreningaId();
    }
    
    @Override
    public String vratiUslovZaBrisanje2() {
        return " ZakazivanjeTreningaID = " + zakazivanjeTreninga.getZakazivanjeTreningaId() + " AND RB = " + rb;
    }
    
    @Override
    public String vratiIme() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiPrezime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiIme(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiPrezime(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String uzmiID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int vratiID(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaPretragu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaPretragu2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiJoinTabelu3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaJoin3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslov2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}

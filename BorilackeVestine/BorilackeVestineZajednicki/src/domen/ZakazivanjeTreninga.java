/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ZakazivanjeTreninga implements OpstiDomenskiObjekat{
    
    private int zakazivanjeTreningaId;
    private Polaznik polaznik;
    private Trener trener;
    private ArrayList<StavkaZakazivanjaTreninga> stavke;
    private String filter;

    public ZakazivanjeTreninga() {
    }

    public ZakazivanjeTreninga(int zakazivanjeTreningaId, Polaznik polaznik, Trener trener,ArrayList<StavkaZakazivanjaTreninga> stavke) {
        this.zakazivanjeTreningaId = zakazivanjeTreningaId;
        this.polaznik = polaznik;
        this.trener = trener;
        this.stavke = stavke;
    }

    public int getZakazivanjeTreningaId() {
        return zakazivanjeTreningaId;
    }

    public void setZakazivanjeTreningaId(int zakazivanjeTreningaId) {
        this.zakazivanjeTreningaId = zakazivanjeTreningaId;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public ArrayList<StavkaZakazivanjaTreninga> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<StavkaZakazivanjaTreninga> stavke) {
        this.stavke = stavke;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
    
    @Override
    public String vratiNazivTabele() {
        return "zakazivanjetreninga";
    }
    
    @Override
    public String vratiKolone() {
        return "PolaznikID,TrenerID";
    }
    
    @Override
    public String vratiVrednostizaUbacivanje() {
        return polaznik.getPolaznikId() + ", " + trener.getTrenerId();
    }
    
    @Override
    public String vratiJoinTabelu() {
        return "polaznik";
    }

    @Override
    public String vratiUslovZaJoin() {
        return "zakazivanjetreninga.PolaznikID = polaznik.PolaznikID";
    }

    @Override
    public String vratiJoinTabelu2() {
        return "trener";
    }
    
    @Override
    public String vratiUslovZaJoin2() {
        return "zakazivanjetreninga.TrenerID = trener.TrenerID";
    }
    
    @Override
    public String vratiUslovZaPretragu() {
        if(filter.equals("")){
           return "zakazivanjetreninga.TrenerID = " + trener.getTrenerId() + " AND polaznik.Prezime LIKE '%'"; 
        }
        return "zakazivanjetreninga.TrenerID = " + trener.getTrenerId() + " AND polaznik.Prezime LIKE " + "'" + filter + "%'";
    }
    
    @Override
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        
        while (rs.next()) {            
            int id = rs.getInt("zakazivanjetreninga.ZakazivanjeTreningaID");
            
            int polaznikID = rs.getInt("polaznik.PolaznikID");
            String ime = rs.getString("polaznik.Ime");
            String prezime = rs.getString("polaznik.Prezime");
            String telefon = rs.getString("polaznik.Telefon");
            int godina = rs.getInt("polaznik.Godina");
            String email = rs.getString("polaznik.Email");
            Date datumUpisa = rs.getDate("polaznik.DatumUpisa");
            Polaznik p = new Polaznik(polaznikID, ime, prezime, telefon, godina, email, datumUpisa);
            
            int TrenerID = rs.getInt("trener.TrenerID");
            String imeTrener = rs.getString("trener.Ime");
            String prezimeTrener = rs.getString("trener.Prezime");
            String korisnickoIme = rs.getString("trener.KorisnickoIme");
            String lozinka = rs.getString("trener.Lozinka");
            boolean ulogovan = rs.getBoolean("trener.Ulogovan");
            Trener t = new Trener(TrenerID, imeTrener, prezimeTrener, korisnickoIme, lozinka, ulogovan);
            
            ZakazivanjeTreninga zt = new ZakazivanjeTreninga(id, p, t, null);
            lista.add(zt);
            
        }
        
        return lista;
        
    }
    
    @Override
    public String uzmiID() {
        return "ZakazivanjeTreningaID";
    }

    @Override
    public String vratiUslovZaID() {
        return "PolaznikID = " + polaznik.getPolaznikId() + " AND TrenerID = " + trener.getTrenerId();
    }

    @Override
    public int vratiID(ResultSet rs) {
        int id = 0;
        try {
            while(rs.next()) {
                id = rs.getInt("ZakazivanjeTreningaID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZakazivanjeTreninga.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    @Override
    public String vratiUslovZaPretragu2() {
        return "zakazivanjetreninga.TrenerID = " + trener.getTrenerId();
    }
    
    @Override
    public String vratiUslov() {
        return "zakazivanjetreninga.PolaznikID = " + polaznik.getPolaznikId() + " AND zakazivanjetreninga.TrenerID = " + trener.getTrenerId();
    }
    
    @Override
    public String vratiUslovZaBrisanje() {
        return "ZakazivanjeTreningaID = " + zakazivanjeTreningaId;
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
    public String vratiVrednostiZaAzuriranje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaAzuriranje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String vratiVrednostiZaubacivanje2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String vratiUslovZaBrisanje2() {
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

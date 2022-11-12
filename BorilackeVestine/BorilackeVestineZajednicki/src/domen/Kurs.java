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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Kurs implements OpstiDomenskiObjekat{
    
    private int kursId;
    private String nazivKursa;
    private String mesto;
    private String opis;
    private int velicinaGrupe;
    private String filter;

    public Kurs() {
    }

    public Kurs(int kursId, String nazivKursa, String mesto, String opis, int velicinaGrupe) {
        this.kursId = kursId;
        this.nazivKursa = nazivKursa;
        this.mesto = mesto;
        this.opis = opis;
        this.velicinaGrupe = velicinaGrupe;
    }

    public int getKursId() {
        return kursId;
    }

    public void setKursId(int kursId) {
        this.kursId = kursId;
    }

    public String getNazivKursa() {
        return nazivKursa;
    }

    public void setNazivKursa(String nazivKursa) {
        this.nazivKursa = nazivKursa;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getVelicinaGrupe() {
        return velicinaGrupe;
    }

    public void setVelicinaGrupe(int velicinaGrupe) {
        this.velicinaGrupe = velicinaGrupe;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return nazivKursa;
    }
    
    @Override
    public String vratiNazivTabele() {
        return "kurs";
    }
    
    @Override
    public String vratiKolone() {
        return "NazivKursa,Mesto,Opis,VelicinaGrupe";
    }

    @Override
    public String vratiVrednostizaUbacivanje() {
        return "'" + nazivKursa + "', '" + mesto + "' ,'" + opis + "', " + velicinaGrupe;
    }
    
    @Override
    public String uzmiID() {
        return "KursID";
    }
    
    @Override
    public String vratiUslovZaID() {
        return " NazivKursa = '" + nazivKursa + "' AND Mesto = '" + mesto + "' AND Opis = '" + opis + "' AND VelicinaGrupe = " + velicinaGrupe;
    }
    
    @Override
    public int vratiID(ResultSet rs) {
        int id = 0; 
        try {
            while (rs.next()) {
                id = rs.getInt("KursID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kurs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }

    @Override
    public String vratiVrednostiZaAzuriranje() {
        return " NazivKursa = '" + nazivKursa + "', Mesto = '" + mesto + "', Opis = '" + opis + "' , VelicinaGrupe = " + velicinaGrupe;
    }
    
    @Override
    public String vratiUslovZaAzuriranje() {
        return " KursID = " + kursId;
    }
    
    @Override
    public String vratiUslovZaBrisanje() {
        return " KursID = " + kursId;
    }
    
    @Override
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> listaKurseva =  new ArrayList<>();
        
        while (rs.next()) {            
            int id = rs.getInt("KursID");
            String naziv = rs.getString("NazivKursa");
            String mesto = rs.getString("Mesto");
            String opis = rs.getString("Opis");
            int velicinaGrupe = rs.getInt("VelicinaGrupe");
            
            Kurs k = new Kurs(id, naziv, mesto, opis, velicinaGrupe);
            listaKurseva.add(k);
        }
        return listaKurseva;
    }
    
    
     @Override
    public String vratiJoinTabelu() {
        return "stavkazakazivanjatreninga";
    }

    @Override
    public String vratiUslovZaJoin() {
        return "kurs.KursID = stavkazakazivanjatreninga.KursID";
    }

    @Override
    public String vratiJoinTabelu2() {
        return "zakazivanjetreninga";
    }

    @Override
    public String vratiUslovZaJoin2() {
        return "stavkazakazivanjatreninga.ZakazivanjeTreningaID = zakazivanjetreninga.ZakazivanjeTreningaID";
    }
    
    @Override
    public String vratiJoinTabelu3() {
        return "polaznik";
    }
    
     @Override
    public String vratiUslovZaJoin3() {
        return "zakazivanjetreninga.PolaznikID = polaznik.PolaznikID";
    }

    @Override
    public String vratiUslov2() {
        return "polaznik.PolaznikID = ";
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
    public String vratiUslovZaPretragu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostiZaubacivanje2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaPretragu2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaBrisanje2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}

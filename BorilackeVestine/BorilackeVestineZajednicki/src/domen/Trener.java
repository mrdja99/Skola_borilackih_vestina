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
public class Trener implements OpstiDomenskiObjekat{
    
    private int trenerId;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private boolean ulogovan;

    public Trener() {
    }

    public Trener(int trenerId, String ime, String prezime, String korisnickoIme, String lozinka, boolean ulogovan) {
        this.trenerId = trenerId;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ulogovan = ulogovan;
    }

    public int getTrenerId() {
        return trenerId;
    }

    public void setTrenerId(int trenerId) {
        this.trenerId = trenerId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public boolean isUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(boolean ulogovan) {
        this.ulogovan = ulogovan;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    @Override
    public String vratiNazivTabele() {
        return "trener";
    }
    
    @Override
    public String vratiUslovZaPretragu() {
        return "trener.korisnickoIme = '" + korisnickoIme + "' AND trener.lozinka = '" + lozinka + "' ";
    }
    
    @Override
    public String uzmiID() {
        return "trenerID";
    }

    @Override
    public String vratiIme() {
        return "ime";
    }

    @Override
    public String vratiPrezime() {
        return "prezime";
    }
    
    @Override
    public int vratiID(ResultSet rs) {
        int id = 0;
        try {
            while(rs.next()){
                id = rs.getInt("TrenerID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public String vratiIme(ResultSet rs) {
        String ime = "";
        try {
            while(rs.next()){
                ime = rs.getString("Ime");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ime;
    }

    @Override
    public String vratiPrezime(ResultSet rs) {
        String prezime = "";
        try {
            while(rs.next()){
                prezime = rs.getString("Prezime");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prezime;
    }

    
    @Override
    public String vratiVrednostiZaAzuriranje() {
        int login = 1;
        if(ulogovan){
            login = 0;
        }
        
        return "trener.ulogovan = " + login;
    }
    
    
    @Override
    public String vratiUslovZaAzuriranje() {
        return "trener.korisnickoIme = '" + korisnickoIme + "' AND trener.lozinka = '" + lozinka + "' ";
    }
    
    @Override
    public String vratiUslovZaID() {
        return "trener.korisnickoIme = '" + korisnickoIme + "' AND trener.lozinka = '" + lozinka + "' ";
    }
    
    @Override
    public String vratiUslovZaBrisanje() {
        return "trener.trenerID = " + trenerId;
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> listaTrenera = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("TrenerID");
            String ime = rs.getString("Ime");
            String prezime = rs.getString("Prezime");
            String korisnickoIme = rs.getString("KorisnickoIme");
            String lozinka = rs.getString("Lozinka");
            boolean ulogovan = rs.getBoolean("ulogovan");

            Trener t = new Trener(id, ime, prezime, korisnickoIme, lozinka, ulogovan);
            listaTrenera.add(t);
        }
        return listaTrenera;
    }
    
    
    
    @Override
    public String vratiKolone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostizaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    


    @Override
    public String vratiJoinTabelu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaJoin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiJoinTabelu2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovZaJoin2() {
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

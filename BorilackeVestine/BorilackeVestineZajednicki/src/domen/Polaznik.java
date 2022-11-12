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
public class Polaznik implements OpstiDomenskiObjekat{
    
    private int polaznikId;
    private String ime;
    private String prezime;
    private String telefon;
    private int godina;
    private String email;
    private Date datumUpisa;
    private String filter;

    public Polaznik() {
    }

    public Polaznik(int polaznikId, String ime, String prezime, String telefon, int godina, String email, Date datumUpisa) {
        this.polaznikId = polaznikId;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.godina = godina;
        this.email = email;
        this.datumUpisa = datumUpisa;
    }

    public int getPolaznikId() {
        return polaznikId;
    }

    public void setPolaznikId(int polaznikId) {
        this.polaznikId = polaznikId;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(Date datumUpisa) {
        this.datumUpisa = datumUpisa;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "polaznik";
    }
    
    @Override
    public String vratiKolone() {
        return "Ime, Prezime, Telefon, Godina, Email, DatumUpisa";
    }
    
    @Override
    public String vratiVrednostizaUbacivanje() {
        return "'" + ime + "' ,'" + prezime + "' ,'" + telefon + "', " + godina + ", '" + email + "' , '" + new java.sql.Date(datumUpisa.getTime()) + "'";
    }
    
    @Override
    public String vratiUslovZaPretragu() {
        if(filter.equals("")){
            return "Prezime LIKE '%'"; 
        }
        return "Prezime LIKE " + "'" + filter + "%'";
    }
    
    @Override
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> listaPolaznika = new ArrayList<>();
        
        while (rs.next()) {            
            int id = rs.getInt("PolaznikID");
            String ime = rs.getString("Ime");
            String prezime = rs.getString("Prezime");
            String telefon = rs.getString("Telefon");
            int godina = rs.getInt("Godina");
            String email = rs.getString("Email");
            Date datumUpisa = rs.getDate("DatumUpisa");
            
            Polaznik p = new Polaznik(polaznikId, ime, prezime, telefon, godina, email, datumUpisa);
            listaPolaznika.add(p);
        }
        return listaPolaznika;
        
    }
    
    @Override
    public String uzmiID() {
        return "PolaznikID";
    }
    
    @Override
    public String vratiUslovZaID() {
        return "Ime = '" + ime + "' AND Prezime = '" + prezime + "' AND Telefon = '" + telefon + "' AND Godina= " + godina + " AND Email ='" + email + "' AND DatumUpisa = '" + new java.sql.Date(datumUpisa.getTime()) + "'";
    }
    
    @Override
    public int vratiID(ResultSet rs) {
        int id = 0;
        try {
            while(rs.next()) {
                id = rs.getInt("PolaznikID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Polaznik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    @Override
    public String vratiVrednostiZaAzuriranje() {
        return "Ime = '" + ime + "', Prezime = '" + prezime + "' , Telefon = '" + telefon + "' , Godina = " + godina + " , Email ='" + email + "' , DatumUpisa = '" + new java.sql.Date(datumUpisa.getTime()) + "'";
    }
    
    @Override
    public String vratiUslovZaAzuriranje() {
        return "PolaznikID = " + polaznikId;
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
    public String vratiUslovZaBrisanje() {
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

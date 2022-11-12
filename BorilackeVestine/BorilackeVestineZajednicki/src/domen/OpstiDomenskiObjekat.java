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

/**
 *
 * @author User
 */
public interface OpstiDomenskiObjekat extends Serializable{
    
    public String vratiIme();
    public String vratiPrezime();
    public String vratiIme(ResultSet rs);
    public String vratiPrezime(ResultSet rs);
    
    public String vratiNazivTabele();
    public String vratiKolone();
    public String vratiVrednostizaUbacivanje();
    
    public String uzmiID();
    public String vratiUslovZaID();
    public int vratiID(ResultSet rs);
    
    public String vratiVrednostiZaAzuriranje();
    public String vratiUslovZaAzuriranje();
    public String vratiUslovZaBrisanje();
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException;
    
    public String vratiUslovZaPretragu();
    

    public String vratiJoinTabelu();
    public String vratiUslovZaJoin();
    public String vratiJoinTabelu2();
    public String vratiUslovZaJoin2();
    public String vratiJoinTabelu3();
    public String vratiUslovZaJoin3();

    public String vratiUslov();
    public String vratiUslov2();

    public String vratiVrednostiZaubacivanje2();

    public String vratiUslovZaPretragu2();

    public String vratiUslovZaBrisanje2();
    
}

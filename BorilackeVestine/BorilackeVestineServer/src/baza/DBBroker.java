/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.OpstiDomenskiObjekat;
import domen.StavkaZakazivanjaTreninga;
import domen.ZakazivanjeTreninga;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DBBroker {
    
    private Connection connection;
    private static DBBroker instance;

    public DBBroker(){
        try {
            DBProperties dbp = new DBProperties();
            String url = dbp.vratiDbUrl();
            String username = dbp.vratiDbUsername();
            String password = dbp.vratiDbPassword();
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (IOException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DBBroker getInstance() {
        if(instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }
    
    public void zatvori() throws SQLException {
        connection.close();
    }
    
    public void potvrdi() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ponisti(){
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<OpstiDomenskiObjekat> vratiSaUslovom(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiUslovZaPretragu();
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.ucitajListu(rs);
    }

    public int vratiID(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT " + odo.uzmiID() + " FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiUslovZaID();
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.vratiID(rs);
    }

    public String vratiIme(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT " + odo.vratiIme() + " FROM " + odo.vratiNazivTabele() + " WHERE " +odo.vratiUslovZaID();
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.vratiIme(rs);
    }

    public String vratiPrezime(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT " + odo.vratiPrezime() + " FROM " + odo.vratiNazivTabele() + " WHERE " +odo.vratiUslovZaID();
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.vratiPrezime(rs);
    }

    public void izmeni(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiVrednostiZaAzuriranje()+ " WHERE " + odo.vratiUslovZaAzuriranje();
        System.out.println(upit);
        Statement st = connection.createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    public void kreiraj(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.vratiNazivTabele() + "(" + odo.vratiKolone() + ")" + " VALUES (" + odo.vratiVrednostizaUbacivanje()+ ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.executeUpdate(upit);
        
    }

    public List<OpstiDomenskiObjekat> vratiBezUslova(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM " + odo.vratiNazivTabele();
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.ucitajListu(rs);
    }

    public void obrisi(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiUslovZaBrisanje();
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.executeUpdate(upit);
    }

    public void sacuvaj(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.vratiNazivTabele() + "(" + odo.vratiKolone() + ")" + " VALUES (" + odo.vratiVrednostizaUbacivanje()+ ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        int zakazivanjeTreningaID = (int) tableKeys.getLong(1);
        System.out.println(zakazivanjeTreningaID);
        
        ArrayList<StavkaZakazivanjaTreninga> stavke = ((ZakazivanjeTreninga) odo).getStavke();
        
        for (OpstiDomenskiObjekat s : stavke) {
            upit = "INSERT INTO " + s.vratiNazivTabele() + "(" + s.vratiKolone() + ")" + " VALUES (" + zakazivanjeTreningaID + ", " + s.vratiVrednostizaUbacivanje()+ ")";
            System.out.println(upit);
            ps = connection.prepareStatement(upit);
            ps.executeUpdate();
        }

    }

    public ArrayList<OpstiDomenskiObjekat> vratiJoinTriTabeleSaUslovom(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " JOIN "
                + odo.vratiJoinTabelu() + " ON "+ odo.vratiUslovZaJoin() + " JOIN "
                + odo.vratiJoinTabelu2() + " ON "+ odo.vratiUslovZaJoin2() + " WHERE " + odo.vratiUslov();
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.ucitajListu(rs);
    }

    public ArrayList<OpstiDomenskiObjekat> vratiJoinFilter(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM "+ odo.vratiNazivTabele() + " JOIN "
                +odo.vratiJoinTabelu()+ " ON " + odo.vratiUslovZaJoin()+" JOIN "
                +odo.vratiJoinTabelu2()+ " ON " + odo.vratiUslovZaJoin2() + " WHERE " + odo.vratiUslovZaPretragu();
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.ucitajListu(rs);
    }

    public void obrisi2(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiUslovZaBrisanje2();
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.executeUpdate(upit);
    }

    public void kreiraj2(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.vratiNazivTabele() + "(" + odo.vratiKolone() + ")" + " VALUES (" + odo.vratiVrednostiZaubacivanje2()+ ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.executeUpdate(upit);
    }

    public void ukloni(OpstiDomenskiObjekat odo) throws SQLException {
        OpstiDomenskiObjekat o = new StavkaZakazivanjaTreninga();
        ((StavkaZakazivanjaTreninga) o).setZakazivanjeTreninga((ZakazivanjeTreninga) odo);
        String upit = "DELETE FROM " + o.vratiNazivTabele() + " WHERE " + o.vratiUslovZaBrisanje();
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.executeUpdate();
        
        upit = "DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiUslovZaBrisanje();
        System.out.println(upit);
        PreparedStatement ps2 = connection.prepareStatement(upit);
        ps2.executeUpdate();
    }

    public List<OpstiDomenskiObjekat> vratiKomplikovaniJoin(OpstiDomenskiObjekat odo, int id) throws SQLException {
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " JOIN "
                + odo.vratiJoinTabelu() + " ON ("+ odo.vratiUslovZaJoin() + ") JOIN "
                + odo.vratiJoinTabelu2() + " ON ("+ odo.vratiUslovZaJoin2() + ") JOIN "
                + odo.vratiJoinTabelu3() + " ON (" + odo.vratiUslovZaJoin3() +
                ") WHERE " + odo.vratiUslov2() + id;
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.ucitajListu(rs);
    }
    
    
    
    
}

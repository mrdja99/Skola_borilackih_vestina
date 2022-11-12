/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class KomunikacijaSaServerom {
    
    private static KomunikacijaSaServerom instance;
    private Socket s;
    private boolean zauzeto = false;

    public KomunikacijaSaServerom() {
        try {
            s = new Socket("localhost", 9000);
        } catch (IOException ex) {
            Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static KomunikacijaSaServerom getInstance() {
        if(instance == null) {
            instance = new KomunikacijaSaServerom();
        }
        return instance;
    }
    
    public ServerskiOdgovor primiOdgovor() {
        
        zauzeto = true;
        ServerskiOdgovor so = new ServerskiOdgovor();
        
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            so = (ServerskiOdgovor) ois.readObject();
            zauzeto = false;
        } catch (IOException ex) {
            Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so;
    }

    public void posaljiZahtev(KlijentskiZahtev kz) throws InterruptedException{
        try {
            if(zauzeto) {
                sleep(1000);
            }
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);
        } catch (IOException ex) {
            Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }


    public boolean isZauzeto() {
        return zauzeto;
    }

    public void setZauzeto(boolean zauzeto) {
        this.zauzeto = zauzeto;
    }
    
    
    
    
    
    
}

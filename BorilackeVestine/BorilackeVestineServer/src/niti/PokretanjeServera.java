/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PokretanjeServera extends Thread{
    
    private ServerSocket ss;
    private static List<KlijentskaNit> klijenti = new ArrayList<>();
    private static boolean working = false;



    public PokretanjeServera() {
        try {
            ss = new ServerSocket(9000);
        } catch (IOException ex) {
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            Socket s;
            System.out.println("Server je pokrenut!");

            while (!isInterrupted()) {
                s = ss.accept();
                kontroler.Kontroler.getInstance().dodajKlijenta(s);
                KlijentskaNit nitKlijenta = new KlijentskaNit(s, klijenti);
                nitKlijenta.start();
                klijenti.add(nitKlijenta);
                System.out.println("Klijent se povezao!");

            }
        } catch (SocketException ex) {
            System.out.println("Prekinula se konekcija sa serverom!");
        } catch (IOException ex) {
            System.out.println("Greska prilikom povezivanja klijenta!");
        }
        
    }
    
    
    public void zaustavi() {
    
        try {
            ss.close();
            for (KlijentskaNit klijentskaNit : klijenti) {
                klijentskaNit.getS().close();
            }
        } catch (IOException ex) {
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean isWorking() {
        return working;
    }

    public static void setWorking(boolean working) {
        PokretanjeServera.working = working;
    }
    
    
    
    
}

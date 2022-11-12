/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Trener;

/**
 *
 * @author User
 */
public class Kontroler {
    
    private static Kontroler instance;
    private Trener trener;


    public static Kontroler getInstance() {
        if(instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }
    
    
    
    
    
}

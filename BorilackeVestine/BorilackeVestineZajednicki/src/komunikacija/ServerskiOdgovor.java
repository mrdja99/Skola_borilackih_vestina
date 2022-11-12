/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class ServerskiOdgovor implements Serializable{
    
    private Object odgovor;
    private String poruka;
    private boolean uspesno;
    private String operacija;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, String poruka, boolean uspesno) {
        this.odgovor = odgovor;
        this.poruka = poruka;
        this.uspesno = uspesno;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public boolean isUspesno() {
        return uspesno;
    }

    public void setUspesno(boolean uspesno) {
        this.uspesno = uspesno;
    }

    public String getOperacija() {
        return operacija;
    }

    public void setOperacija(String operacija) {
        this.operacija = operacija;
    }
    
    
    
    
}

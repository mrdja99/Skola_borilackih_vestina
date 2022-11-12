/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Kurs;
import domen.StavkaZakazivanjaTreninga;
import domen.Trening;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabelaStavke extends AbstractTableModel{
    
    ArrayList<StavkaZakazivanjaTreninga> lista;
    String[] kolone = {"RB","Kurs","Termin treninga"};
    int rb = 0;

    public ModelTabelaStavke() {
        lista = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaZakazivanjaTreninga szt = lista.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return szt.getRb();
            case 1: return szt.getKurs();
            case 2: return szt.getTrening();
            
            default: return "";
        
        }
        
    }

    public ArrayList<StavkaZakazivanjaTreninga> getLista() {
        return lista;
    }

    public boolean vecPostoji(Kurs kurs, Trening trening) {
        for (StavkaZakazivanjaTreninga stavkaZakazivanjaTreninga : lista) {
            if(stavkaZakazivanjaTreninga.getKurs().equals(kurs) && stavkaZakazivanjaTreninga.getTrening().equals(trening)) {
                return true;
            }
        }
        return false;
    }

    public void dodajStavku(StavkaZakazivanjaTreninga szt) {
        rb = lista.size();
        szt.setRb(++rb);
        lista.add(szt);
        fireTableDataChanged();
    }

    public void obrisiRed(int selectedRow) {
        lista.remove(selectedRow);
        rb = 0;
        
        for (StavkaZakazivanjaTreninga stavkaZakazivanjaTreninga : lista) {
            stavkaZakazivanjaTreninga.setRb(++rb);
        }
        
        fireTableDataChanged();
    }

    public boolean vecZauzet(Trening trening) {
        for (StavkaZakazivanjaTreninga stavkaZakazivanjaTreninga : lista) {
            if(stavkaZakazivanjaTreninga.getTrening().getTreningId() == trening.getTreningId()) {
                return true;
            }
        }
        return false;
    }

    public void popuniTabelu(ArrayList<StavkaZakazivanjaTreninga> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public int vratiRb() {
        int rb = 0;
        for (StavkaZakazivanjaTreninga stavka : lista) {
            rb++;
        }
        return rb + 1;
    }
   
    
    
    
    
}

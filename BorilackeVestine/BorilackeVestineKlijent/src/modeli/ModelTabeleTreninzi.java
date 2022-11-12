/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.ZakazivanjeTreninga;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabeleTreninzi extends AbstractTableModel{
    
    ArrayList<ZakazivanjeTreninga> lista;
    String[] kolone = {"Polaznik", "Trener"};

    public ModelTabeleTreninzi() {
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
        ZakazivanjeTreninga zt = lista.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return zt.getPolaznik();
            case 1: return zt.getTrener();
            
            default: return "";
        }
    }

    public ArrayList<ZakazivanjeTreninga> getLista() {
        return lista;
    }

    public void popuniTabelu(ArrayList<ZakazivanjeTreninga> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public ZakazivanjeTreninga vratiZakazaniTrening(int selectedRow) {
        return lista.get(selectedRow);
    }

    public void obrisiRed(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
    }
    
    
    
    
    
}

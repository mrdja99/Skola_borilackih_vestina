/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Kurs;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabelaKurs extends AbstractTableModel{
    
    ArrayList<Kurs> lista;
    String[] kolone = {"Naziv kursa", "Mesto odrzavanja", "Velicina grupe"};

    public ModelTabelaKurs() {
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
        Kurs k = lista.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return k.getNazivKursa();
            case 1: return k.getMesto();
            case 2: return k.getVelicinaGrupe();
            
            default: return "";
        
        }
        
    }

    public ArrayList<Kurs> getLista() {
        return lista;
    }

    public void popuniTabeluKurseva(ArrayList<Kurs> kurseviBaza) {
        lista = kurseviBaza;
        fireTableDataChanged();
    }

    public Kurs vratiKurs(int selectedRow) {
        return lista.get(selectedRow);
    }
    
    
    
}

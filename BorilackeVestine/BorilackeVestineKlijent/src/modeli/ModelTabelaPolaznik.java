/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Polaznik;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelTabelaPolaznik extends AbstractTableModel{
    
    ArrayList<Polaznik> lista;
    String[] kolone = {"Ime","Prezime","Email","Broj telefona", "Starost"};

    public ModelTabelaPolaznik() {
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
        Polaznik p = lista.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return p.getIme();
            case 1: return p.getPrezime();
            case 2: return p.getEmail();
            case 3: return p.getTelefon();
            case 4: return p.getGodina();
            
            default: return "";
        
        }
    }

    public ArrayList<Polaznik> getLista() {
        return lista;
    }

    public void popuniTabelu(ArrayList<Polaznik> polaznici) {
        lista = polaznici;
        fireTableDataChanged();
    }

    public Polaznik vratiPolaznika(int selectedRow) {
        return lista.get(selectedRow);
    }

    
    
    
    
    
    
}

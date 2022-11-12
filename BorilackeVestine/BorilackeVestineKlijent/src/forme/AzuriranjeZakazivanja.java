/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Kurs;
import domen.StavkaZakazivanjaTreninga;
import domen.Trening;
import domen.ZakazivanjeTreninga;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.KlijentskiZahtev;
import komunikacija.KomunikacijaSaServerom;
import komunikacija.ServerskiOdgovor;
import modeli.ModelTabelaStavke;
import operacije.Operacije;

/**
 *
 * @author User
 */
public class AzuriranjeZakazivanja extends javax.swing.JFrame {
    
    private ZakazivanjeTreninga zakazivanjeTreninga;

    /**
     * Creates new form AzuriranjeZakazivanja
     */
    public AzuriranjeZakazivanja() {
        initComponents();
        setTitle("Azuriranje zakazivanja");
        setLocationRelativeTo(null);
        txtPolaznik.setEditable(false);
        txtTrener.setEditable(false);
        tblStavke.setModel(new ModelTabelaStavke());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtTrener = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPolaznik = new javax.swing.JTextField();
        btnDodaj = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStavke = new javax.swing.JTable();
        btnOtkazi = new javax.swing.JButton();
        btnSacuvaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Trener");

        jLabel2.setText("Polaznik");

        btnDodaj.setText("Dodaj stavku");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obrisi stavku");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        tblStavke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblStavke);

        btnOtkazi.setText("Otkazi");
        btnOtkazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOtkaziActionPerformed(evt);
            }
        });

        btnSacuvaj.setText("Sacuvaj");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTrener)
                            .addComponent(txtPolaznik, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnOtkazi, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTrener, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPolaznik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDodaj)
                    .addComponent(btnObrisi))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOtkazi)
                    .addComponent(btnSacuvaj))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOtkaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOtkaziActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnOtkaziActionPerformed

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        DodajStavku ds = new DodajStavku(this, true);
        ds.setVisible(true);
    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        int selectedRow = tblStavke.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Molimo Vas izaberite stavku");
            return;
        }
        
        ModelTabelaStavke mts = (ModelTabelaStavke) tblStavke.getModel();
        mts.obrisiRed(selectedRow);
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
        ModelTabelaStavke mts = (ModelTabelaStavke) tblStavke.getModel();
        ArrayList<StavkaZakazivanjaTreninga> stavke = mts.getLista();
        zakazivanjeTreninga.setStavke(stavke);
        
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.SACUVAJ_IZMENE_ZAKAZIVANJA);
        kz.setParametar(zakazivanjeTreninga);
        
        try {
            KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        } catch (InterruptedException ex) {
            Logger.getLogger(AzuriranjeZakazivanja.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();
        
        if(so.isUspesno()){
            JOptionPane.showMessageDialog(this, so.getPoruka());
        }else{
            JOptionPane.showMessageDialog(this, so.getPoruka(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
        
        this.dispose();
        
    }//GEN-LAST:event_btnSacuvajActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AzuriranjeZakazivanja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AzuriranjeZakazivanja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AzuriranjeZakazivanja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AzuriranjeZakazivanja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AzuriranjeZakazivanja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnOtkazi;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStavke;
    private javax.swing.JTextField txtPolaznik;
    private javax.swing.JTextField txtTrener;
    // End of variables declaration//GEN-END:variables

    void postavivrednosti(ZakazivanjeTreninga zt) {
        this.zakazivanjeTreninga = zt;
        txtTrener.setText(zakazivanjeTreninga.getTrener() + "");
        txtPolaznik.setText(zakazivanjeTreninga.getPolaznik() + "");
        popuniTabeluStavki(zakazivanjeTreninga);
    }

    private void popuniTabeluStavki(ZakazivanjeTreninga zt) {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_LISTU_STAVKI);
        kz.setParametar(zt);
        
        
        try {
            KomunikacijaSaServerom.getInstance().posaljiZahtev(kz);
        } catch (InterruptedException ex) {
            Logger.getLogger(AzuriranjeZakazivanja.class.getName()).log(Level.SEVERE, null, ex);
        }

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstance().primiOdgovor();

        if (so.isUspesno()) {
            ArrayList<StavkaZakazivanjaTreninga> lista = (ArrayList<StavkaZakazivanjaTreninga>) so.getOdgovor();
            ModelTabelaStavke mts = (ModelTabelaStavke) tblStavke.getModel();
            mts.popuniTabelu(lista);
        } else {
            JOptionPane.showMessageDialog(this, so.getPoruka());
            return;
        }
        
    }

    void dodajStavku(Kurs kurs, Trening trening) {
        ModelTabelaStavke mts = (ModelTabelaStavke) tblStavke.getModel();
        
        if(mts.vecPostoji(kurs,trening)) {
            JOptionPane.showMessageDialog(this, "Ova stavka vec postoji u tabeli");
            return;
        }
        
        if(mts.vecZauzet(trening)) {
            JOptionPane.showMessageDialog(this, "Ovaj termin je vec zauzet");
            return;
        }

        //int rb = mts.vratiRb();
        
        StavkaZakazivanjaTreninga szt = new StavkaZakazivanjaTreninga(zakazivanjeTreninga, -1, kurs, trening);
        
        mts.dodajStavku(szt);
    }
}
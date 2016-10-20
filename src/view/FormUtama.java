/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;

/**
 *
 * @author Irawan
 */
public class FormUtama extends javax.swing.JFrame {

    public static FormDataBarang formDataBarang = new FormDataBarang();
    //public static FormLihatBarang formLihatDataBarang;
    public static FormDataCustomer formDataCustomer = new FormDataCustomer();
    //public static FormLihatCustomer formLihatDataCustomer;
    public static FormDataSuplier formDataSuplier = new FormDataSuplier();
    //public static FormLihatSuplier formLihatDataSuplier;
    public static FormPenjualan formPenjualan = new FormPenjualan();
    public static FormDataUser formDataUser = new FormDataUser();
    public final FormDeskripsi formDeskripsi = new FormDeskripsi(this, true);
    public final FormLogin formLogin = new FormLogin(this, true);
    public static FormLaporanPenjualan formLaporanPenjualan=new FormLaporanPenjualan();
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    /**
     * Creates new form FormUtama
     */
    public FormUtama() {
        initComponents();
        setUkuranLokasiFrame(0.8, true);
        setMnemoniccc();
        setEnableMenu(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mdiDesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        aplikasiMenu = new javax.swing.JMenu();
        deskripsiMenuItem = new javax.swing.JMenuItem();
        loginMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        keluarMenuItem = new javax.swing.JMenuItem();
        masterDataMenu = new javax.swing.JMenu();
        dataBarangMenuItem = new javax.swing.JMenuItem();
        dataCustomerMenuItem = new javax.swing.JMenuItem();
        dataSuplierMenuItem = new javax.swing.JMenuItem();
        dataUserMenuItem = new javax.swing.JMenuItem();
        transaksiMenu = new javax.swing.JMenu();
        transaksiPenjualanMenuItem = new javax.swing.JMenuItem();
        laporanMenu = new javax.swing.JMenu();
        laporanPenjualanMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout mdiDesktopPaneLayout = new javax.swing.GroupLayout(mdiDesktopPane);
        mdiDesktopPane.setLayout(mdiDesktopPaneLayout);
        mdiDesktopPaneLayout.setHorizontalGroup(
            mdiDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mdiDesktopPaneLayout.setVerticalGroup(
            mdiDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        aplikasiMenu.setText("Aplikasi");

        deskripsiMenuItem.setText("Deskripsi");
        deskripsiMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deskripsiMenuItemActionPerformed(evt);
            }
        });
        aplikasiMenu.add(deskripsiMenuItem);

        loginMenuItem.setText("Login");
        loginMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginMenuItemActionPerformed(evt);
            }
        });
        aplikasiMenu.add(loginMenuItem);
        aplikasiMenu.add(jSeparator1);

        keluarMenuItem.setText("Keluar");
        keluarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarMenuItemActionPerformed(evt);
            }
        });
        aplikasiMenu.add(keluarMenuItem);

        jMenuBar1.add(aplikasiMenu);

        masterDataMenu.setText("Master Data");

        dataBarangMenuItem.setText("Data Barang");
        dataBarangMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataBarangMenuItemActionPerformed(evt);
            }
        });
        masterDataMenu.add(dataBarangMenuItem);

        dataCustomerMenuItem.setText("Data Customer");
        dataCustomerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataCustomerMenuItemActionPerformed(evt);
            }
        });
        masterDataMenu.add(dataCustomerMenuItem);

        dataSuplierMenuItem.setText("Data Suplier");
        dataSuplierMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataSuplierMenuItemActionPerformed(evt);
            }
        });
        masterDataMenu.add(dataSuplierMenuItem);

        dataUserMenuItem.setText("Data User");
        dataUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataUserMenuItemActionPerformed(evt);
            }
        });
        masterDataMenu.add(dataUserMenuItem);

        jMenuBar1.add(masterDataMenu);

        transaksiMenu.setText("Transaksi");

        transaksiPenjualanMenuItem.setText("Penjualan");
        transaksiPenjualanMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksiPenjualanMenuItemActionPerformed(evt);
            }
        });
        transaksiMenu.add(transaksiPenjualanMenuItem);

        jMenuBar1.add(transaksiMenu);

        laporanMenu.setText("Laporan");

        laporanPenjualanMenuItem.setText("Penjualan");
        laporanPenjualanMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanPenjualanMenuItemActionPerformed(evt);
            }
        });
        laporanMenu.add(laporanPenjualanMenuItem);

        jMenuBar1.add(laporanMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mdiDesktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mdiDesktopPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setMnemoniccc() {
        aplikasiMenu.setMnemonic('A');
        masterDataMenu.setMnemonic('M');
        transaksiMenu.setMnemonic('T');
        laporanMenu.setMnemonic('L');

        loginMenuItem.setMnemonic('L');
        keluarMenuItem.setMnemonic('K');
        dataBarangMenuItem.setMnemonic('B');
        dataCustomerMenuItem.setMnemonic('C');
        dataSuplierMenuItem.setMnemonic('S');
        transaksiPenjualanMenuItem.setMnemonic('P');
        laporanPenjualanMenuItem.setMnemonic('P');
        deskripsiMenuItem.setMnemonic('D');
        dataUserMenuItem.setMnemonic('U');

    }
    private void setEnableMenu(boolean enable){
        masterDataMenu.setEnabled(enable);
        transaksiMenu.setEnabled(enable);
        laporanMenu.setEnabled(enable);
    }

    private void setUkuranLokasiFrame(double skala, boolean tengah) {
        Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (skala * dimensi.getWidth()), (int) (skala * dimensi.getHeight()));

        if (tengah) {
            setLocation((int) ((dimensi.getWidth() - getWidth()) / 2), (int) ((dimensi.getHeight() - getHeight()) / 2));

        }
    }

    private void keluarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarMenuItemActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_keluarMenuItemActionPerformed

    private void dataBarangMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataBarangMenuItemActionPerformed
        // TODO add your handling code here:
        if ((formDataBarang != null) && (formDataBarang.isVisible())) {
            try {
                formDataBarang.setSelected(true);
            } catch (PropertyVetoException e) {
            }
        } else {
            formDataBarang = new FormDataBarang();
            mdiDesktopPane.add(formDataBarang);
            formDataBarang.setVisible(true);
        }

    }//GEN-LAST:event_dataBarangMenuItemActionPerformed

    private void dataCustomerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataCustomerMenuItemActionPerformed
        // TODO add your handling code here:
        if ((formDataCustomer != null) && (formDataCustomer.isVisible())) {
            try {
                formDataCustomer.setSelected(true);
            } catch (PropertyVetoException e) {
            }
        } else {
            formDataCustomer = new FormDataCustomer();
            mdiDesktopPane.add(formDataCustomer);
            formDataCustomer.setVisible(true);
        }

    }//GEN-LAST:event_dataCustomerMenuItemActionPerformed

    private void dataSuplierMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataSuplierMenuItemActionPerformed
        // TODO add your handling code here:
        if ((formDataSuplier != null) && (formDataSuplier.isVisible())) {
            try {
                formDataSuplier.setSelected(true);
            } catch (PropertyVetoException e) {
            }
        } else {
            formDataSuplier = new FormDataSuplier();
            mdiDesktopPane.add(formDataSuplier);
            formDataSuplier.setVisible(true);
        }

    }//GEN-LAST:event_dataSuplierMenuItemActionPerformed

    private void transaksiPenjualanMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksiPenjualanMenuItemActionPerformed
        // TODO add your handling code here:
        formPenjualan.setUsername(username);
        if ((formPenjualan != null) && (formPenjualan.isVisible())) {
            try {
                formPenjualan.setSelected(true);
            } catch (PropertyVetoException e) {
            }
        } else {
            formPenjualan = new FormPenjualan();
            mdiDesktopPane.add(formPenjualan);
            formPenjualan.setVisible(true);
        }
    }//GEN-LAST:event_transaksiPenjualanMenuItemActionPerformed

    private void deskripsiMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deskripsiMenuItemActionPerformed
        // TODO add your handling code here:
        formDeskripsi.setVisible(true);
    }//GEN-LAST:event_deskripsiMenuItemActionPerformed

    private void loginMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginMenuItemActionPerformed
        // TODO add your handling code here:
        if (loginMenuItem.getText().equals("Login")) {
            formLogin.setVisible(true);
            if (!formLogin.getUserLogin().equals("")) {
                setEnableMenu(true);
                loginMenuItem.setText("Logout");
            } else {
                setEnableMenu(false);
            }
        } else {
            loginMenuItem.setText("Login");
            setEnableMenu(false);
        }
    }//GEN-LAST:event_loginMenuItemActionPerformed

    private void dataUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataUserMenuItemActionPerformed
        // TODO add your handling code here:
        if ((formDataUser != null) && (formDataUser.isVisible())) {
            try {
                formDataUser.setSelected(true);
            } catch (PropertyVetoException e) {
            }
        } else {
            formDataUser = new FormDataUser();
            mdiDesktopPane.add(formDataUser);
            formDataUser.setVisible(true);
        }

    }//GEN-LAST:event_dataUserMenuItemActionPerformed

    private void laporanPenjualanMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanPenjualanMenuItemActionPerformed
        // TODO add your handling code here:
       if ((formLaporanPenjualan != null) && (formLaporanPenjualan.isVisible())) {
            try {
                formLaporanPenjualan.setSelected(true);
            } catch (PropertyVetoException e) {
            }
        } else {
            formLaporanPenjualan = new FormLaporanPenjualan();
            mdiDesktopPane.add(formLaporanPenjualan);
            formLaporanPenjualan.setVisible(true);
        }
        
    }//GEN-LAST:event_laporanPenjualanMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu aplikasiMenu;
    private javax.swing.JMenuItem dataBarangMenuItem;
    private javax.swing.JMenuItem dataCustomerMenuItem;
    private javax.swing.JMenuItem dataSuplierMenuItem;
    private javax.swing.JMenuItem dataUserMenuItem;
    private javax.swing.JMenuItem deskripsiMenuItem;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem keluarMenuItem;
    private javax.swing.JMenu laporanMenu;
    private javax.swing.JMenuItem laporanPenjualanMenuItem;
    private javax.swing.JMenuItem loginMenuItem;
    private javax.swing.JMenu masterDataMenu;
    private javax.swing.JDesktopPane mdiDesktopPane;
    private javax.swing.JMenu transaksiMenu;
    private javax.swing.JMenuItem transaksiPenjualanMenuItem;
    // End of variables declaration//GEN-END:variables
}

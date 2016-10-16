/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.swing.JOptionPane;
import model.Enkripsi;
import model.DataUser;

/**
 *
 * @author Irawan
 */
public class LoginController {
    private final DataUser dataUser = new DataUser();
    private final Enkripsi enkripsi = new Enkripsi();
    
    public boolean validasi(javax.swing.JTextField userNameTextField, javax.swing.JPasswordField passwordField){
        boolean valid = false;
        String hashedInputPassword = "";
        
        if (!userNameTextField.getText().equals("")){
            if (dataUser.baca(userNameTextField.getText())){
                try {
                    hashedInputPassword = enkripsi.hashMD5(new String(passwordField.getPassword()));
                } catch (Exception ex){}
                
                if (dataUser.getPassword().equalsIgnoreCase(hashedInputPassword)){
                    valid = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau password salah", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (dataUser.getPesan().substring(0, 3).equalsIgnoreCase("userId")){
                    JOptionPane.showMessageDialog(null, "Username atau password salah", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, dataUser.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        
        return valid;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.DataUser;
import model.Enkripsi;
//import view.FormLihatUser;
import view.FormUtama;

/**
 *
 * @author Irawan
 */
public class DataUserController {

    private final DataUser dataUser = new DataUser();
    private final Enkripsi enkripsi = new Enkripsi();
    private boolean hashed = false;

    /* private FormLihatCustomer formLihatCustomer;

    public void tampilkanFormLihatCustomer() {
        formLihatCustomer = new FormLihatCustomer(null, true);
        formLihatCustomer.setVisible(true);
    }

    public void tampilkanDaftar() {
        formLihatCustomer = new FormLihatCustomer(null, true);
        if (dataCustomer.bacaData()) {
            formLihatCustomer.tampilkanData(dataCustomer.getList());

            formLihatCustomer.setVisible(true);
            if (!formLihatCustomer.getNamaCustomerDipilih().equals("")) {
                FormUtama.formDataUser.setUsername(formLihatCustomer.getNamaCustomerDipilih());
                if (dataCustomer.baca(formLihatCustomer.getNamaCustomerDipilih())) {
                    FormUtama.formDataUser.setAlamat(dataCustomer.getAlamatCustomer());
                    FormUtama.formDataUser.setTelp(dataCustomer.getTelpCustomer());
                } else {
                    FormUtama.formDataUser.setUsername("");
                    FormUtama.formDataUser.setAlamat("");
                    FormUtama.formDataUser.setTelp("");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, dataCustomer.getPesan());
        }
    }
     */
    public void setHashed(boolean hashed) {
        this.hashed = hashed;
    }

    public void simpan(javax.swing.JTextField usernameTextField, javax.swing.JPasswordField passwordField) {
        if (!usernameTextField.getText().equals("")) {
            dataUser.setUsername(usernameTextField.getText());
            //dataUser.setPassword(alamatCustomerTextArea.getText());
            //dataUser.setTelpCustomer(telpCustomerTextField.getText());
            if (hashed) {
                dataUser.setPassword(new String(passwordField.getPassword()));
            } else {
                try {
                    dataUser.setPassword(enkripsi.hashMD5(new String(passwordField.getPassword())));
                } catch (Exception ex) {
                    dataUser.setPassword("");
                }
            }

            if (dataUser.simpan()) {
                FormUtama.formDataUser.setPassword("");
                FormUtama.formDataUser.setUsername("");
                //FormUtama.formDataUser.setTelp("");
            } else if (dataUser.getPesan().length() > 0) {
                JOptionPane.showMessageDialog(null, dataUser.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hapus(javax.swing.JTextField namaCustomerTextField) {
        if (!namaCustomerTextField.getText().equals("")) {
            if (dataUser.hapus(namaCustomerTextField.getText())) {
                FormUtama.formDataUser.setPassword("");
                //FormUtama.formDataUser.setUsername("");
                //FormUtama.formDataUser.setTelp("");
            } else {
                JOptionPane.showMessageDialog(null, dataUser.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nama Customer tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

}

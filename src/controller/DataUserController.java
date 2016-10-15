/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.DataUser;
//import view.FormLihatUser;
import view.FormUtama;

/**
 *
 * @author Irawan
 */
public class DataUserController {

    private final DataUser dataUser = new DataUser();
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
                FormUtama.formDataUser.setNama(formLihatCustomer.getNamaCustomerDipilih());
                if (dataCustomer.baca(formLihatCustomer.getNamaCustomerDipilih())) {
                    FormUtama.formDataUser.setAlamat(dataCustomer.getAlamatCustomer());
                    FormUtama.formDataUser.setTelp(dataCustomer.getTelpCustomer());
                } else {
                    FormUtama.formDataUser.setNama("");
                    FormUtama.formDataUser.setAlamat("");
                    FormUtama.formDataUser.setTelp("");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, dataCustomer.getPesan());
        }
    }
*/
    public void simpan(javax.swing.JTextField namaCustomerTextField, javax.swing.JTextArea alamatCustomerTextArea,
             javax.swing.JTextField telpCustomerTextField) {
        if (!namaCustomerTextField.getText().equals("")) {
            dataUser.setUserId(namaCustomerTextField.getText());
            dataUser.setPassword(alamatCustomerTextArea.getText());
            dataUser.setTelpCustomer(telpCustomerTextField.getText());

            if (dataUser.simpan()) {
                FormUtama.formDataUser.setAlamat("");
                FormUtama.formDataUser.setNama("");
                FormUtama.formDataUser.setTelp("");
            } else if (dataUser.getPesan().length() > 0) {
                JOptionPane.showMessageDialog(null, dataUser.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nama Customer tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void hapus(javax.swing.JTextField namaCustomerTextField){
        if (!namaCustomerTextField.getText().equals("")){
            if (dataUser.hapus(namaCustomerTextField.getText())){
                FormUtama.formDataUser.setAlamat("");
                //FormUtama.formDataUser.setNama("");
                FormUtama.formDataUser.setTelp("");
            }else {
                JOptionPane.showMessageDialog(null, dataUser.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nama Customer tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

}

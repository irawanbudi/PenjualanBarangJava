/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.DataCustomer;
import view.FormLihatCustomer;
import view.FormUtama;

/**
 *
 * @author Irawan
 */
public class DataCustomerController {

    private final DataCustomer dataCustomer = new DataCustomer();
    private FormLihatCustomer formLihatCustomer;

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
                FormUtama.formDataCustomer.setNama(formLihatCustomer.getNamaCustomerDipilih());
                if (dataCustomer.baca(formLihatCustomer.getNamaCustomerDipilih())) {
                    FormUtama.formDataCustomer.setAlamat(dataCustomer.getAlamatCustomer());
                    FormUtama.formDataCustomer.setTelp(dataCustomer.getTelpCustomer());
                } else {
                    FormUtama.formDataCustomer.setNama("");
                    FormUtama.formDataCustomer.setAlamat("");
                    FormUtama.formDataCustomer.setTelp("");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, dataCustomer.getPesan());
        }
    }

    public void simpan(javax.swing.JTextField namaCustomerTextField, javax.swing.JTextArea alamatCustomerTextArea,
             javax.swing.JTextField telpCustomerTextField) {
        if (!namaCustomerTextField.getText().equals("")) {
            dataCustomer.setNamaCustomer(namaCustomerTextField.getText());
            dataCustomer.setAlamatCustomer(alamatCustomerTextArea.getText());
            dataCustomer.setTelpCustomer(telpCustomerTextField.getText());

            if (dataCustomer.simpan()) {
                FormUtama.formDataCustomer.setAlamat("");
                FormUtama.formDataCustomer.setNama("");
                FormUtama.formDataCustomer.setTelp("");
            } else if (dataCustomer.getPesan().length() > 0) {
                JOptionPane.showMessageDialog(null, dataCustomer.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nama Customer tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void hapus(javax.swing.JTextField namaCustomerTextField){
        if (!namaCustomerTextField.getText().equals("")){
            if (dataCustomer.hapus(namaCustomerTextField.getText())){
                FormUtama.formDataCustomer.setAlamat("");
                //FormUtama.formDataCustomer.setNama("");
                FormUtama.formDataCustomer.setTelp("");
            }else {
                JOptionPane.showMessageDialog(null, dataCustomer.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nama Customer tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

}

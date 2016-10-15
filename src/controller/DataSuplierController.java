/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.DataSuplier;
import view.FormLihatSuplier;
import view.FormUtama;

/**
 *
 * @author Irawan
 */
public class DataSuplierController {

    private final DataSuplier dataSuplier = new DataSuplier();
    private FormLihatSuplier formLihatSuplier;

    public void tampilkanFormLihatSuplier() {
        formLihatSuplier = new FormLihatSuplier(null, true);
        formLihatSuplier.setVisible(true);
    }

    public void tampilkanDaftar() {
        formLihatSuplier = new FormLihatSuplier(null, true);
        if (dataSuplier.bacaData()) {
            formLihatSuplier.tampilkanData(dataSuplier.getList());

            formLihatSuplier.setVisible(true);
            if (!formLihatSuplier.getNamaSuplierDipilih().equals("")) {
                FormUtama.formDataSuplier.setNama(formLihatSuplier.getNamaSuplierDipilih());
                if (dataSuplier.baca(formLihatSuplier.getNamaSuplierDipilih())) {
                    FormUtama.formDataSuplier.setAlamat(dataSuplier.getAlamatSuplier());
                    FormUtama.formDataSuplier.setTelp(dataSuplier.getTelpSuplier());
                } else {
                    FormUtama.formDataSuplier.setNama("");
                    FormUtama.formDataSuplier.setAlamat("");
                    FormUtama.formDataSuplier.setTelp("");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, dataSuplier.getPesan());
        }
    }

    public void simpan(javax.swing.JTextField namaSuplierTextField, javax.swing.JTextArea alamatSuplierTextArea,
             javax.swing.JTextField telpSuplierTextField) {
        if (!namaSuplierTextField.getText().equals("")) {
            dataSuplier.setNamaSuplier(namaSuplierTextField.getText());
            dataSuplier.setAlamatSuplier(alamatSuplierTextArea.getText());
            dataSuplier.setTelpSuplier(telpSuplierTextField.getText());

            if (dataSuplier.simpan()) {
                FormUtama.formDataSuplier.setAlamat("");
                FormUtama.formDataSuplier.setNama("");
                FormUtama.formDataSuplier.setTelp("");
            } else if (dataSuplier.getPesan().length() > 0) {
                JOptionPane.showMessageDialog(null, dataSuplier.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nama Suplier tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void hapus(javax.swing.JTextField namaSuplierTextField){
        if (!namaSuplierTextField.getText().equals("")){
            if (dataSuplier.hapus(namaSuplierTextField.getText())){
                FormUtama.formDataSuplier.setAlamat("");
                //FormUtama.formDataSuplier.setNama("");
                FormUtama.formDataSuplier.setTelp("");
            }else {
                JOptionPane.showMessageDialog(null, dataSuplier.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nama Suplier tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

}

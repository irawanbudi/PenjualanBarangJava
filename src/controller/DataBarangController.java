/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.DataBarang;
import view.FormLihatBarang;
import view.FormStokBarang;
import view.FormUtama;
//tes

/**
 *
 * @author Irawan
 */
public class DataBarangController {

    private final DataBarang dataBarang = new DataBarang();
    private FormLihatBarang formLihatBarang;
   // private FormStokBarang formStokBarang;

    public void tampilkanFormLihatBarang() {
        formLihatBarang = new FormLihatBarang(null, true);
        formLihatBarang.setVisible(true);
    }

    public void tampilkanDaftar() {
        formLihatBarang = new FormLihatBarang(null, true);
        if (dataBarang.bacaData()) {
            formLihatBarang.tampilkanData(dataBarang.getList());

            formLihatBarang.setVisible(true);
            if (!formLihatBarang.getKodeBarangDipilih().equals("")) {
                FormUtama.formDataBarang.setKode(formLihatBarang.getKodeBarangDipilih());
                if (dataBarang.baca(formLihatBarang.getKodeBarangDipilih())) {
                    FormUtama.formDataBarang.setNama(dataBarang.getNamaBarang());
                    FormUtama.formDataBarang.setHarga(dataBarang.getHargaBarang());
                    FormUtama.formDataBarang.setStok(dataBarang.getStokBarang());
                    FormUtama.formDataBarang.setSatuan(dataBarang.getSatuanBarang());
                } else {
                    FormUtama.formDataBarang.setNama("");
                    FormUtama.formDataBarang.setSatuan("pcs");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, dataBarang.getPesan());
        }
    }

    public void simpan(javax.swing.JTextField kodeBarangTextField, javax.swing.JTextField namaBarangTextField,
            javax.swing.JSpinner hargaBarangSpinner, javax.swing.JSpinner stokSpinner, javax.swing.JComboBox satuanComboBox) {
        if (!kodeBarangTextField.getText().equals("")) {
            dataBarang.setKodeBarang(kodeBarangTextField.getText());
            dataBarang.setNamaBarang(namaBarangTextField.getText());
            dataBarang.setHargaBarang((long) hargaBarangSpinner.getValue());
            dataBarang.setStokBarang((int) stokSpinner.getValue());
            dataBarang.setSatuanBarang(satuanComboBox.getSelectedItem().toString());

            if (dataBarang.simpan()) {
                FormUtama.formDataBarang.setKode("");
                FormUtama.formDataBarang.setNama("");
                FormUtama.formDataBarang.setHarga(1000);
                FormUtama.formDataBarang.setStok(1);
                FormUtama.formDataBarang.setSatuan("pcs");
            } else if (dataBarang.getPesan().length() > 0) {
                JOptionPane.showMessageDialog(null, dataBarang.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Kode barang tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hapus(javax.swing.JTextField kodeBarangTextField) {
        if (!kodeBarangTextField.getText().equals("")) {
            if (dataBarang.hapus(kodeBarangTextField.getText())) {
                FormUtama.formDataBarang.setKode("");
                FormUtama.formDataBarang.setNama("");
                FormUtama.formDataBarang.setHarga(1000);
                FormUtama.formDataBarang.setStok(1);
                FormUtama.formDataBarang.setSatuan("pcs");
            } else {
                JOptionPane.showMessageDialog(null, dataBarang.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Kode barang tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Object[][] tampilkanStokBarang() {
            
        if (dataBarang.bacaData()) {
            return dataBarang.getList();

                      
        } else {
            JOptionPane.showMessageDialog(null, dataBarang.getPesan());
        }
        return dataBarang.getList();
    }

    public void cetakStokBarang() {
        dataBarang.cetakLaporanStokBarang();
    }

}

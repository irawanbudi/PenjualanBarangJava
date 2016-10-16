/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.DataBarang;
import model.DataCustomer;
import model.Penjualan;
import view.FormLihatBarang;
import view.FormLihatCustomer;
import view.FormUtama;

/**
 *
 * @author Irawan
 */
public class PenjualanController {

    private final DataBarang dataBarang = new DataBarang();
    private final DataCustomer dataCustomer = new DataCustomer();
    private final Penjualan penjualan = new Penjualan();
    private FormLihatBarang formLihatBarang;
    private FormLihatCustomer formLihatCustomer;
    private java.util.Date tanggal = new Date();
    private java.sql.Date tanggalSQL = new java.sql.Date(tanggal.getTime());
    private java.text.SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    private long jumlah=0;

    public void cariKodePenjualan(javax.swing.JTextField kodePenjualanTextField) {
        if (!kodePenjualanTextField.getText().equals("")) {
            if (penjualan.baca(kodePenjualanTextField.getText())) {
                //FormUtama.formPenjualan.setNamaCustomer(Integer.toString(penjualan.getIdCustomer()));
                FormUtama.formPenjualan.setTanggal(sdf.format(tanggal.getTime()));
                //FormUtama.formPenjualan.setKodePenjualan(dataBarang.get());
                FormUtama.formPenjualan.clearBanyaknyaTable();

                int jumlahNilai = 0;
                if (penjualan.baca(kodePenjualanTextField.getText())) {
                    Object[][] listPenjualan = penjualan.getListPenjualan();
                    FormUtama.formPenjualan.clearBanyaknyaTable();

                    if (listPenjualan.length > 0) {
                        for (int i = 0; i < listPenjualan.length; i++) {
                            if (!((String) listPenjualan[i][0]).equals("")) {
                                String namaCustomer="";
                                if(dataCustomer.bacaId((int) listPenjualan[i][2])){
                                    namaCustomer=dataCustomer.getNamaCustomer();
                                }
                                String namaBarang = "", satuanBarang = "";
                                long hargaBarang = 0;
                                if (dataBarang.baca((String) listPenjualan[i][4])) {
                                    namaBarang = dataBarang.getNamaBarang();
                                    hargaBarang = dataBarang.getHargaBarang();
                                    satuanBarang = dataBarang.getSatuanBarang();

                                }
                                FormUtama.formPenjualan.setNamaCustomer(namaCustomer);
                                FormUtama.formPenjualan.setTambahBarang(new Object[]{listPenjualan[i][4],
                                    namaBarang, hargaBarang, listPenjualan[i][5], satuanBarang, Long.parseLong(listPenjualan[i][5].toString()) * hargaBarang});
                                jumlahNilai++;
                            }
                        }
                    }
                }

                if (jumlahNilai == 0) {
                    FormUtama.formPenjualan.setTambahBarang(new Object[]{});
                }
            } else {
                //FormUtama.formPenjualan.setKodePenjualan("");
                FormUtama.formPenjualan.setTanggal(sdf.format(tanggal.getTime()));
                //FormUtama.formPenjualan.setNamaCustomer("");
                FormUtama.formPenjualan.clearBanyaknyaTable();
                FormUtama.formPenjualan.setTambahBarang(new Object[]{});

                //JOptionPane.showMessageDialog(null, penjualan.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "kode penjualan tidak boleh kosong\n", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void tampilkanFormLihatCustomer() {
        formLihatCustomer = new FormLihatCustomer(null, true);
        if (dataCustomer.bacaData()) {
            formLihatCustomer.tampilkanData(dataCustomer.getList());
            formLihatCustomer.setVisible(true);

            if (!formLihatCustomer.getNamaCustomerDipilih().equals("")) {
                if (dataCustomer.baca(formLihatCustomer.getNamaCustomerDipilih())) {
                    FormUtama.formPenjualan.setNamaCustomer(dataCustomer.getNamaCustomer());
                    FormUtama.formPenjualan.setIdCustomer(dataCustomer.getIdCustomer());
                    //FormUtama.formPenjualan.setSemester(Integer.toString(dataBarang.getSemester()));
                    //FormUtama.formNilai.setKelas(dataBarang.getKelas());
                    //FormUtama.formNilai.clearNilaiTable();

                    /*int jumlahNilai = 0;
                    if (penjualan.baca(formLihatCustomer.getNamaCustomerDipilih())) {
                        Object[][] listPenjualan = penjualan.getListPenjualan();
                        FormUtama.formPenjualan.clearBanyaknyaTable();

                        if (listPenjualan.length > 0) {
                            for (int i = 0; i < listPenjualan.length; i++) {
                                if (!((String) listPenjualan[i][0]).equals("")) {
                                    String namaBarang = "", satuanBarang = "";
                                    long hargaBarang = 0;
                                    if (dataBarang.baca((String) listPenjualan[i][4])) {
                                        namaBarang = dataBarang.getNamaBarang();
                                        hargaBarang = dataBarang.getHargaBarang();
                                        satuanBarang = dataBarang.getSatuanBarang();
                                    }
                                    FormUtama.formPenjualan.setTambahBarang(new Object[]{listPenjualan[i][4],
                                        namaBarang, hargaBarang, listPenjualan[i][5], satuanBarang,
                                        hargaBarang * ((long) listPenjualan[i][5])});
                                    jumlahNilai++;
                                }
                            }
                        }
                    }

                    if (jumlahNilai == 0) {
                        FormUtama.formPenjualan.setTambahBarang(new Object[]{});
                    }
*/
                    } else {
                    JOptionPane.showMessageDialog(null, dataCustomer.getPesan());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, dataCustomer.getPesan());
        }
    }

    public void tampilkanFormLihatBarang() {
        formLihatBarang = new FormLihatBarang(null, true);
        if (dataBarang.bacaData()) {
            formLihatBarang.tampilkanData(dataBarang.getList());

            formLihatBarang.setVisible(true);
            if (!formLihatBarang.getKodeBarangDipilih().equals("")) {
                FormUtama.formDataBarang.setKode(formLihatBarang.getKodeBarangDipilih());
                if (dataCustomer.baca(formLihatBarang.getKodeBarangDipilih())) {
                    FormUtama.formPenjualan.setTambahBarang(new Object[]{dataBarang.getKodeBarang(),
                        dataBarang.getNamaBarang(), dataBarang.getHargaBarang(), "", dataBarang.getSatuanBarang()});
                } else {
                    FormUtama.formPenjualan.setTambahBarang(new Object[]{formLihatBarang.getKodeBarangDipilih(),
                        "", "", "", "", ""});
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, dataBarang.getPesan());
        }
    }

    public void cariKodeBarang(String kodeBarang) {
        if (dataBarang.baca(kodeBarang)) {
            FormUtama.formPenjualan.setNamaBarangTabel(dataBarang.getNamaBarang());
            FormUtama.formPenjualan.setHargaBarangTabel(dataBarang.getHargaBarang());
            FormUtama.formPenjualan.setSatuanBarangTabel(dataBarang.getSatuanBarang());
            FormUtama.formPenjualan.setQty(1);
        } else {
            FormUtama.formPenjualan.setNamaBarangTabel("");

            JOptionPane.showMessageDialog(null, dataBarang.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cariNamaCustomer(String namaCustomer) {
        if (dataCustomer.baca(namaCustomer)) {
            FormUtama.formPenjualan.setNamaCustomer(dataCustomer.getNamaCustomer());
            FormUtama.formPenjualan.setIdCustomer(dataCustomer.getIdCustomer());
            
        } else {
            FormUtama.formPenjualan.setNamaCustomer("");

            JOptionPane.showMessageDialog(null, dataCustomer.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void simpan(javax.swing.JTextField kodePenjualan, javax.swing.JTextField namaCustomer, javax.swing.JTable penjualanTable) {
        if (!kodePenjualan.getText().equals("")) {
            penjualan.setKodePenjualan(kodePenjualan.getText());
            if (!namaCustomer.getText().equals("")) {
                dataCustomer.baca(namaCustomer.getText());
                penjualan.setIdCustomer(dataCustomer.getIdCustomer());
            }
            penjualan.setTanggal(tanggalSQL);

            Object[][] listPenjualan = new Object[penjualanTable.getRowCount()][6];

            for (int i = 0; i < penjualanTable.getRowCount(); i++) {
                listPenjualan[i][0] = penjualan.getKodePenjualan();
                listPenjualan[i][0] = tanggalSQL;
                listPenjualan[i][2] = penjualan.getIdCustomer();
                listPenjualan[i][3] = penjualan.getUsername();
                listPenjualan[i][4] = penjualanTable.getValueAt(i, 0);
                listPenjualan[i][5] = penjualanTable.getValueAt(i, 3);
            }

            penjualan.setListPenjualan(listPenjualan);

            if (penjualan.simpan()) {
                FormUtama.formPenjualan.setKodePenjualan("");
                FormUtama.formPenjualan.setNamaCustomer("");
                FormUtama.formPenjualan.setTanggal(sdf.format(tanggal));
                //FormUtama.formNilai.setKelas("");
                FormUtama.formPenjualan.clearBanyaknyaTable();
                FormUtama.formPenjualan.setTambahBarang(new Object[]{});
            } else {
                JOptionPane.showMessageDialog(null, penjualan.getPesan() + "Kode blm ada");
            }
        } else {
            JOptionPane.showMessageDialog(null, "kode penjualan tidak boleh kosong\n", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    public long getJumlah(){
        return FormUtama.formPenjualan.getHarga()*FormUtama.formPenjualan.getQty();
         
        
    }
    /*
    public void cetakLaporan(javax.swing.JComboBox semesterComboBox,
            javax.swing.JComboBox kelasComboBox) {
        int semester = 0;
        String kelas = "";

        if (semesterComboBox.getSelectedIndex() > 0) {
            semester = Integer.parseInt(semesterComboBox.getSelectedItem().toString());
        }

        if (kelasComboBox.getSelectedIndex() > 0) {
            kelas = kelasComboBox.getSelectedItem().toString();
        }

        penjualan.cetakLaporan(semester, kelas);
    }
     */
}

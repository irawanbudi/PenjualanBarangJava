/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    
    
    public void cariBarang(javax.swing.JTextField nama){
        if (!nama.getText().equals("")){
            if (dataBarang.baca(nama.getText())){
                FormUtama.formPenjualan.setKode(dataBarang.getKodeBarang());
                FormUtama.formPenjualan.setTanggal("");
                //FormUtama.formPenjualan.setNomor(dataBarang.get());
                FormUtama.formPenjualan.clearBanyaknyaTable();
                
                int jumlahNilai=0;
                if (penjualan.baca(nama.getText())){
       //             Object[][] listNilai = penjualan.getListNilai();
                    FormUtama.formPenjualan.clearBanyaknyaTable();
                    
/*                    if (listNilai.length>0){
                        for (int i=0; i<listNilai.length;i++){
                            if (!((String)listNilai[i][0]).equals("")){
                                String namaMataKuliah="";
                                if (dataCustomer.baca((String) listNilai[i][0])){
                                    namaMataKuliah = dataCustomer.getNamaMataKuliah();                      
                                }
                                FormUtama.formNilai.setTambahNilai(new Object[]{listNilai[i][0],namaMataKuliah,listNilai[i][1],listNilai[i][2],listNilai[i][3]});
                                jumlahNilai++;
                            }
                        }
                    }
  */              } 
                
                if (jumlahNilai==0) {
//                    FormUtama.formNilai.setTambahNilai(new Object[]{});
                }
            } else {
/*                FormUtama.formNilai.setNama("");
                FormUtama.formNilai.setSemester("");
                FormUtama.formNilai.setKelas("");
                FormUtama.formNilai.clearNilaiTable();
                FormUtama.formNilai.setTambahNilai(new Object[]{});
  */              
                JOptionPane.showMessageDialog(null, dataBarang.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,"nim tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void tampilkanFormLihatMahasiswa(){
        formLihatBarang = new FormLihatBarang(null, true);
        if (dataBarang.bacaData()){
            formLihatBarang.tampilkanData(dataBarang.getList());
            formLihatBarang.setVisible(true);
            
            if (!formLihatBarang.getKodeBarangDipilih().equals("")) {
                if (dataBarang.baca(formLihatBarang.getKodeBarangDipilih())){
                    FormUtama.formPenjualan.setKode(dataBarang.getKodeBarang());
                    FormUtama.formPenjualan.setNamaBarang(dataBarang.getNamaBarang());
/*                    FormUtama.formPenjualan.setSemester( Integer.toString( dataBarang.getSemester()));
                    FormUtama.formNilai.setKelas(dataBarang.getKelas());
                    FormUtama.formNilai.clearNilaiTable();
  */                  
                    int jumlahNilai=0;
                    if (penjualan.baca(formLihatBarang.getKodeBarangDipilih())){
//                        Object[][] listNilai = penjualan.getListNilai();
//                        FormUtama.formPenjualan.clearP();
                        
/*                        if (listNilai.length>0){
                            for (int i=0; i<listNilai.length;i++){
                                if (!((String)listNilai[i][0]).equals("")){
                                    String namaMataKuliah="";
                                    if (dataCustomer.baca((String)listNilai[i][0])){
                                        namaMataKuliah = dataCustomer.getNamaMataKuliah();
                                    }
                                    FormUtama.formNilai.setTambahNilai(new Object[]{listNilai[i][0],namaMataKuliah,listNilai[i][1],listNilai[i][2],listNilai[i][3]});
                                    jumlahNilai++;
                                }
                            }
                        }
                    }
                    
                    if (jumlahNilai==0) {
                        FormUtama.formNilai.setTambahNilai(new Object[]{});
                    }
                }  else {
                    JOptionPane.showMessageDialog(null, dataBarang.getPesan());
                }
  */          }
        } else {
            JOptionPane.showMessageDialog(null, dataBarang.getPesan());
        }
    
/*    
    public void tampilkanFormLihatMataKuliah(){
        formLihatCustomer = new FormLihatMataKuliah(null,true);
        if (dataCustomer.bacaData()){
            formLihatCustomer.tampilkanData(dataCustomer.getList());
            
            formLihatCustomer.setVisible(true);
            if (!formLihatCustomer.getKodeMataKuliahDipilih().equals("")){
                FormUtama.formMataKuliah.setKodeMataKuliah(formLihatCustomer.getKodeMataKuliahDipilih());
                if (dataCustomer.baca(formLihatCustomer.getKodeMataKuliahDipilih())){
                    FormUtama.formNilai.setTambahNilai(new Object[]{dataCustomer.getKodeMataKuliah(),dataCustomer.getNamaMataKuliah(),"","",""});
                } else {
                    FormUtama.formNilai.setTambahNilai(new Object[]{formLihatCustomer.getKodeMataKuliahDipilih(),"","","",""});
                }
            }
        }  else {
            JOptionPane.showMessageDialog(null, dataCustomer.getPesan());
        }
    }
    
    public void cariMataKuliah(String kodeMataKuliah){
        if (dataCustomer.baca(kodeMataKuliah)){
            FormUtama.formNilai.setNamaMataKuliah( dataCustomer.getNamaMataKuliah());                      
        } else {
            FormUtama.formNilai.setNamaMataKuliah("");        
            
            JOptionPane.showMessageDialog(null, dataCustomer.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    public void simpan(javax.swing.JTextField nim, javax.swing.JTable nilaiTable){
	if (!nim.getText().equals("")){
            penjualan.setKode(nim.getText());
            Object[][] listNilai = new Object[nilaiTable.getRowCount()][4];
            
            for (int i=0; i <nilaiTable.getRowCount();i++){
                listNilai[i][0] = nilaiTable.getValueAt(i, 0);
                listNilai[i][1] = nilaiTable.getValueAt(i, 2);
                listNilai[i][2] = nilaiTable.getValueAt(i, 3);
                listNilai[i][3] = nilaiTable.getValueAt(i, 4);
            }
            
            penjualan.setListNilai(listNilai);            
            
            if (penjualan.simpan()){
                FormUtama.formNilai.setNim("");
                FormUtama.formNilai.setNama("");
                FormUtama.formNilai.setSemester("");
                FormUtama.formNilai.setKelas("");
                FormUtama.formNilai.clearNilaiTable();
                FormUtama.formNilai.setTambahNilai(new Object[]{});
            }  else {
                JOptionPane.showMessageDialog(null, penjualan.getPesan());
            }
        } else {
            JOptionPane.showMessageDialog(null,"nim tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    public void cetakLaporan(javax.swing.JComboBox semesterComboBox,
            javax.swing.JComboBox kelasComboBox){
        int semester=0;
        String kelas="";
        
        if (semesterComboBox.getSelectedIndex()>0){
            semester = Integer.parseInt(semesterComboBox.getSelectedItem().toString());
        }
        
        if (kelasComboBox.getSelectedIndex()>0){
            kelas = kelasComboBox.getSelectedItem().toString();
        }
        
        penjualan.cetakLaporan(semester, kelas);
    }
*/
}
        }}}
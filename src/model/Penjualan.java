/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
/**
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
/**
 *
 * @author Irawan
 */
public class Penjualan {
    private String kode;
    private String pesan;
    //private Object[][] listNilai;
    private final koneksi conn = new koneksi();

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
/*
    public Object[][] getListNilai() {
        return listNilai;
    }

    public void setListNilai(Object[][] listNilai) {
        this.listNilai = listNilai;
    }
*/    
    public boolean simpan(){
        boolean adaKesalahan = false;	
	Connection connection; 
	
	if ((connection = conn.getConnection()) != null){
            int jumlahSimpan = 0;
            String SQLStatemen;
            Statement sta;
            
            try{
                SQLStatemen = "delete from penjualan where kode='"+kode+"'"; 
                sta = connection.createStatement();
                sta.executeUpdate(SQLStatemen);
            } catch (SQLException ex){}
  /*          
            for (int i=0; i < listNilai.length; i++){
                try {
                    SQLStatemen = "insert into penjualan values ('"+ kode +"','"+ 
                            listNilai[i][0] + "','" + listNilai[i][1] + "','" +
                            listNilai[i][2] + "','" + listNilai[i][3] +"')";
                    sta = connection.createStatement();
                    jumlahSimpan += sta.executeUpdate(SQLStatemen);
                } catch (SQLException ex){}

            }
*/            
            if (jumlahSimpan>0) {
                adaKesalahan = false;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
    public boolean baca(String kodeBarang){
        boolean adaKesalahan = false;	
	Connection connection; 
        
	this.kode = kodeBarang;
	//listNilai = null;
	
	if ((connection = conn.getConnection()) != null){
            String SQLStatemen;
            Statement sta;
            ResultSet rset;
            
            try {
                SQLStatemen = "select * from barang where kode='"+kodeBarang+"'"; 
                sta = connection.createStatement();
                rset = sta.executeQuery(SQLStatemen);
                
                rset.next();
                rset.last();
                //listNilai = new Object[rset.getRow()][4];
                
                rset.first();
                int i=0;
                do {
                    if (!rset.getString("kodematakuliah").equals("")){
                  //      listNilai[i] = new Object[]{ rset.getString("kodematakuliah"), rset.getInt("tugas"), rset.getInt("uts"), rset.getInt("uas")}; 		    
                    }
                    i++;
                } while (rset.next());
                
/*                if (listNilai.length > 0) {
                    adaKesalahan = false;
                }
  */              
                sta.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membaca data nilai siswa\n"+ex.getMessage();
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
/*    public boolean cetakLaporan(int semester, String kelas){
        boolean adaKesalahan = false;
        Connection connection;
        
        if ((connection = conn.getConnection()) != null){
            String SQLStatement;
            ResultSet resultSet = null;
                    
            try {
                Statement statement = connection.createStatement();
                
                SQLStatement = " SELECT tbmahasiswa.`nim` AS tbmahasiswa_nim, "
                        + " tbmahasiswa.`nama` AS tbmahasiswa_nama, "
                        + " tbmahasiswa.`semester` AS tbmahasiswa_semester, "
                        + " tbmahasiswa.`kelas` AS tbmahasiswa_kelas, "
                        + " tbmatakuliah.`kodematakuliah` AS tbmatakuliah_kodematakuliah, "
                        + " tbmatakuliah.`namamatakuliah` AS tbmatakuliah_namamatakuliah, "
                        + " tbmatakuliah.`jumlahsks` AS tbmatakuliah_jumlahsks, "
                        + " tbnilai.`nim` AS tbnilai_nim, "
                        + " tbnilai.`kodematakuliah` AS tbnilai_kodematakuliah, "
                        + " tbnilai.`tugas` AS tbnilai_tugas, "
                        + " tbnilai.`uts` AS tbnilai_uts, "
                        + " tbnilai.`uas` AS tbnilai_uas, "
                        + " round((tbnilai.`tugas`+tbnilai.`uts`+tbnilai.`uas`)/3, 2) AS tbnilai_nilaiakhir, "
                        + " (if((tbnilai.`tugas`+tbnilai.`uts`+tbnilai.`uas`)/3>=85,'A', "
                        + " if((tbnilai.`tugas`+tbnilai.`uts`+tbnilai.`uas`)/3>=70,'B', "
                        + " if((tbnilai.`tugas`+tbnilai.`uts`+tbnilai.`uas`)/3>=55,'C', "
                        + " if((tbnilai.`tugas`+tbnilai.`uts`+tbnilai.`uas`)/3>=40,'D','E'))))) AS tbnilai_nilaihuruf, "
                        + " (if((tbnilai.`tugas`+tbnilai.`uts`+tbnilai.`uas`)/3>=55,'Lulus','Tidak Lulus')) AS tbnilai_status "
                        + " FROM "
                        + " `tbmahasiswa` tbmahasiswa INNER JOIN `tbnilai` tbnilai ON tbmahasiswa.`nim` = tbnilai.`nim` "
                        + " INNER JOIN `tbmatakuliah` tbmatakuliah ON tbnilai.`kodematakuliah` = tbmatakuliah.`kodematakuliah` ";
                
                if (semester!=0){
                    SQLStatement = SQLStatement + " where tbmahasiswa.`semester`="+semester;
                    
                    if (!kelas.equals("")){
                        SQLStatement = SQLStatement + " and tbmahasiswa.`kelas`='"+kelas+"' ";
                    }
                } else {
                    if (!kelas.equals("")){
                        SQLStatement = SQLStatement + " where tbmahasiswa.`kelas`='"+kelas+"' ";
                    }
                }
                
                SQLStatement = SQLStatement +" ORDER BY "
                        + " tbmahasiswa.`semester` ASC, "
                        + " tbmahasiswa.`kelas` ASC, "
                        + " tbmahasiswa.`nama` ASC, "
                        + " tbmahasiswa.`nim` ASC";
                
                resultSet = statement.executeQuery(SQLStatement);
            } catch (SQLException ex) {
                pesan = "Tidak dapat membaca data\n"+ex;
            }
            
            if (resultSet != null){
                try {
                    JasperDesign disain = JRXmlLoader.load("src/reports/NilaiReport.jrxml");
                    JasperReport nilaiLaporan = JasperCompileManager.compileReport(disain);
                    JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
                    JasperPrint cetak = JasperFillManager.fillReport(nilaiLaporan,new HashMap(),resultSetDataSource);
                    JasperViewer.viewReport(cetak,false);
                } catch (JRException ex) {
                    pesan = "Tidak dapat mencetak laporan\n"+ex;
                }
            }
        }  else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
*/
}

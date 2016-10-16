/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import view.FormLogin;

/**
 * import net.sf.jasperreports.engine.JRException; import
 * net.sf.jasperreports.engine.JRResultSetDataSource; import
 * net.sf.jasperreports.engine.JasperCompileManager; import
 * net.sf.jasperreports.engine.JasperFillManager; import
 * net.sf.jasperreports.engine.JasperPrint; import
 * net.sf.jasperreports.engine.JasperReport; import
 * net.sf.jasperreports.engine.design.JasperDesign; import
 * net.sf.jasperreports.engine.xml.JRXmlLoader; import
 * net.sf.jasperreports.view.JasperViewer; /** /**
 *
 * @author Irawan
 */
public class Penjualan {

    private String kodePenjualan;
    private int idCustomer;
    
    private Date tanggal = new Date();
    private SimpleDateFormat bentuk = new SimpleDateFormat("hh:mm:ss");
    private String pesan;
    private Object[][] listPenjualan;
    private final koneksi conn = new koneksi();
    private view.FormUtama formUtama;
    private String username;
private long total;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodePenjualan() {
        return kodePenjualan;
    }

    public void setKodePenjualan(String kodePenjualan) {
        this.kodePenjualan = kodePenjualan;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public Object[][] getListPenjualan() {
        return listPenjualan;
    }

    public void setListPenjualan(Object[][] listPenjualan) {
        this.listPenjualan = listPenjualan;
    }

    public boolean simpan() {
        boolean adaKesalahan = false;
        Connection connection;

        if ((connection = conn.getConnection()) != null) {
            int jumlahSimpan = 0;
            String SQLStatemen;
            Statement sta;
            ResultSet rsa;
            try {
                SQLStatemen = "delete from penjualan where kodepenjualan='" + kodePenjualan + "'";
                sta = connection.createStatement();
                sta.executeUpdate(SQLStatemen);
            } catch (SQLException ex) {
            }

            for (int i = 0; i < listPenjualan.length; i++) {
                
                try {
                    SQLStatemen = "insert into penjualan (`kodepenjualan`, "
                            + "`tanggal`, `idcustomer`, `kasir`, `kodebarang`,"
                            + " `qty`) values ('" +kodePenjualan + "','"
                            +tanggal +  "','" + idCustomer + "','" + "kasir" + "','"
                            + listPenjualan[i][4] + "','" + listPenjualan[i][5] + "')";
                    sta = connection.createStatement();
                    System.out.println("Query= "+SQLStatemen);
                    jumlahSimpan += sta.executeUpdate(SQLStatemen);
                } catch (SQLException ex) {
                }

            }

            if (jumlahSimpan > 0) {
                adaKesalahan = false;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n" + conn.getPesanKesalahan();
        }

        return !adaKesalahan;
    }

    public boolean baca(String kodePenjualan) {
        boolean adaKesalahan = false;
        Connection connection;

        this.kodePenjualan = kodePenjualan;
        listPenjualan = null;

        if ((connection = conn.getConnection()) != null) {
            String SQLStatemen;
            Statement sta;
            ResultSet rset;

            try {
                SQLStatemen = "select * from penjualan where kodepenjualan='" + kodePenjualan + "'";
                sta = connection.createStatement();
                rset = sta.executeQuery(SQLStatemen);

                rset.next();
                rset.last();
                listPenjualan = new Object[rset.getRow()][6];

                rset.first();
                int i = 0;
                do {
                    if (!rset.getString("kodepenjualan").equals("")) {
                        listPenjualan[i] = new Object[]{rset.getString("kodepenjualan"), 
                            rset.getDate("tanggal"), rset.getInt("idcustomer"),
                            rset.getString("kasir"),rset.getString("kodebarang"), 
                            rset.getInt("qty")};
                    }
                    i++;
                } while (rset.next());

                if (listPenjualan.length > 0) {
                    adaKesalahan = false;
                }

                sta.close();
                rset.close();
                connection.close();
            } catch (SQLException ex) {
                adaKesalahan = true;
                pesan = "Tidak dapat membaca data penjualan\n" + ex.getMessage();
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n" + conn.getPesanKesalahan();
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

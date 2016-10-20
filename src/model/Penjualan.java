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
 * /**
 *
 * @author Irawan
 */
public class Penjualan {

    private String kodePenjualan;
    private int idCustomer;

    private Date tanggal = new Date();
    private SimpleDateFormat bentuk = new SimpleDateFormat("yyyy-MM-dd");
    private String pesan;
    private Object[][] listPenjualan;
    private final koneksi conn = new koneksi();
    private view.FormUtama formUtama;
    private String username;
    private long total;
    private view.FormLaporanPenjualan formLaporanPenjualan;

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

    public boolean bacaData() {
        boolean adaKesalahan = false;
        Connection connection;
        listPenjualan = new Object[0][0];

        if ((connection = conn.getConnection()) != null) {
            String SQLStatemen;
            Statement sta;
            ResultSet rset;

            try {
                SQLStatemen = "select kodepenjualan,kasir from penjualan";
                sta = connection.createStatement();
                rset = sta.executeQuery(SQLStatemen);

                rset.next();
                rset.last();
                listPenjualan = new Object[rset.getRow()][2];
                if (rset.getRow() > 0) {
                    rset.first();
                    int i = 0;
                    do {
                        listPenjualan[i] = new Object[]{rset.getString("kodepenjualan"), rset.getString("kasir")};
                        i++;
                    } while (rset.next());
                }

                sta.close();
                rset.close();
                connection.close();
            } catch (SQLException ex) {
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel penjualan" + ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n" + conn.getPesanKesalahan();
        }

        return !adaKesalahan;
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
                            + " `qty`) values ('" + kodePenjualan + "','"
                            + tanggal + "','" + idCustomer + "','" + "kasir" + "','"
                            + listPenjualan[i][4] + "','" + listPenjualan[i][5] + "')";
                    sta = connection.createStatement();
                    System.out.println("Query= " + SQLStatemen);
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
                            rset.getString("kasir"), rset.getString("kodebarang"),
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

    public boolean cetakLaporan(String tipe, String kodePenjualan,
            java.util.Date awal, java.util.Date akhir) {
        boolean adaKesalahan = false;
        
        Connection connection;

        if ((connection = conn.getConnection()) != null) {
            String SQLStatement;
            ResultSet resultSet = null;

            try {
                Statement statement = connection.createStatement();

                SQLStatement = "SELECT"
                        + "     penjualan.`kodepenjualan` AS penjualan_kodepenjualan,"
                        + "     penjualan.`tanggal` AS penjualan_tanggal,"
                        + "     penjualan.`idcustomer` AS penjualan_idcustomer,"
                        + "     penjualan.`kasir` AS penjualan_kasir,"
                        + "     penjualan.`kodebarang` AS penjualan_kodebarang,"
                        + "     penjualan.`qty` AS penjualan_qty,"
                        + "     customer.`nama` AS customer_nama,"
                        + "     barang.`nama` AS barang_nama,"
                        + "     barang.`harga` AS barang_harga,"
                        + "     barang.`stok` AS barang_stok,"
                        + "     barang.`satuan` AS barang_satuan,"
                        + "     customer.`alamat` AS customer_alamat,"
                        + "     customer.`telp` AS customer_telp,"
                        + "round(barang.`harga` * penjualan.`qty`, 2) AS penjualan_jumlah "
                        + "FROM"
                        + "     `penjualan` penjualan INNER JOIN `customer` customer ON penjualan.`idcustomer` = customer.`id`"
                        + "     INNER JOIN `barang` barang ON penjualan.`kodebarang` = barang.`kode`";
                System.out.println("tipe: " + tipe + "\t Kode: " + kodePenjualan);
                if (!tipe.equalsIgnoreCase("Tanggal")) {
                    if (!kodePenjualan.equalsIgnoreCase("SEMUA")) {
                        SQLStatement = SQLStatement + " where penjualan.`kodepenjualan`='" + kodePenjualan + "' ";
                    }
                } else {
                    SQLStatement=SQLStatement+" where DATE_FORMAT(penjualan.`tanggal`,'%Y-%m-%d')>='"+bentuk.format(awal)+"' AND "
                            +"DATE_FORMAT(penjualan.`tanggal`,'%Y-%m-%d')<='"+bentuk.format(akhir)+"'";
                }
                                 System.out.println("Query: "+SQLStatement);
                resultSet = statement.executeQuery(SQLStatement);
//                System.out.println("resultset: "+resultSet);
            } catch (SQLException ex) {
                pesan = "Tidak dapat membaca data\n" + ex;
            }

            if (resultSet != null) {
                try {
                    JasperDesign disain = JRXmlLoader.load("src\\report\\penjualan.jrxml");
                    JasperReport penjualanLaporan = JasperCompileManager.compileReport(disain);
                    JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
                    System.out.println("Cetak Laporan");
                    JasperPrint cetak = JasperFillManager.fillReport(penjualanLaporan, new HashMap(), resultSetDataSource);
                    JasperViewer.viewReport(cetak, false);
                } catch (JRException ex) {
                    pesan = "Tidak dapat mencetak laporan\n" + ex;
                }
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n" + conn.getPesanKesalahan();
        }

        return !adaKesalahan;
    }

}

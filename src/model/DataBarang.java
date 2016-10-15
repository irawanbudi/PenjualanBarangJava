/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import view.PesanDialog;

/**
 *
 * @author Irawan
 */

public class DataBarang {
private String kodeBarang,namaBarang,satuanBarang;
private int stokBarang;
private long hargaBarang;
private String pesan;
private Object [][] list;
private final koneksi conn=new koneksi();
private final PesanDialog pesanDialog=new PesanDialog();

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getSatuanBarang() {
        return satuanBarang;
    }

    public void setSatuanBarang(String satuanBarang) {
        this.satuanBarang = satuanBarang;
    }

    public int getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(int stokBarang) {
        this.stokBarang = stokBarang;
    }

    public long getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(long hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public String getPesan() {
        return pesan;
    }

    public Object[][] getList() {
        return list;
    }

    public void setList(Object[][] list) {
        this.list = list;
    }


    public boolean baca(String kodeBarang){
        boolean adaKesalahan = false;
        Connection connection;
        
        if ((connection = conn.getConnection()) != null){
            Statement sta;
            ResultSet rset;
            
            try {
                String SQLStatemen = "select * from barang where kode='"+kodeBarang+"'";
                sta = connection.createStatement();
                rset = sta.executeQuery(SQLStatemen);
                
                rset.next();
                if (rset.getRow()>0){
                    this.kodeBarang = rset.getString("kode");
                    this.namaBarang = rset.getString("nama");
                    this.hargaBarang = rset.getLong("harga");                    
                    this.stokBarang=rset.getInt("stok");
                    this.satuanBarang=rset.getString("satuan");
                } else {
                    adaKesalahan = true;
                    pesan = "Kode barang \""+kodeBarang+"\" tidak ditemukan";
                }
                
                sta.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true; 
                pesan = "Tidak dapat membuka tabel barang\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }

    public boolean bacaData(){
        boolean adaKesalahan = false;
        Connection connection;
        list = new Object[0][0] ;
        
        if ((connection = conn.getConnection()) != null){
            String SQLStatemen;
            Statement sta;
            ResultSet rset;
            
            try { 
                SQLStatemen = "select kode,nama from barang"; 
                sta = connection.createStatement(); 
                rset = sta.executeQuery(SQLStatemen);
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][2];
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do { 
                        list[i] = new Object[]{rset.getString("kode"), rset.getString("nama")};
                        i++;
                    } while (rset.next());
                }
                
                sta.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel barang"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
  public boolean simpan(){
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = conn.getConnection()) != null){
            int jumlahSimpan=0;
            boolean simpan = false; 
            
            try {
                String SQLStatemen = "select * from barang where kode='"+kodeBarang+"'";
                Statement sta = connection.createStatement();
                ResultSet rset = sta.executeQuery(SQLStatemen);
                
                rset.next();
                if (rset.getRow()>0){
                    if (pesanDialog.tampilkanPilihan("Kode barang sudah ada\nApakah data diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update barang set nama='" + namaBarang + "', harga='" + hargaBarang +
                                "', stok='" + stokBarang + "', satuan='" + satuanBarang +"' where kode='" + kodeBarang + "'";
                        System.out.println(SQLStatemen);
                        sta = connection.createStatement();
                        jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into barang(kode, nama, harga, stok, satuan) values ('" +
                            kodeBarang + "','" + namaBarang + "','" + hargaBarang + "','" + stokBarang + "','" + satuanBarang + "')"; 
                    sta = connection.createStatement();
                    jumlahSimpan = sta.executeUpdate(SQLStatemen);
                }
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data barang";
                    }
                }
                
                sta.close();
                rset.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel barang\n"+ex;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    public boolean hapus(String kodeBarang){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = conn.getConnection()) != null){
            int jumlahHapus;
            Statement sta;
            
            try {
                String SQLStatemen = "delete from barang where kode='"+kodeBarang+"'";
                sta = connection.createStatement();
                jumlahHapus = sta.executeUpdate(SQLStatemen);
                
                if (jumlahHapus < 1){
                    pesan = "Data barang dengan kode "+kodeBarang+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                sta.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel barang\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }

}

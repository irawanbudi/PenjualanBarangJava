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

public class DataSuplier {
private String namaSuplier,alamatSuplier,telpSuplier;
private String pesan;
private Object [][] list;
private final koneksi conn=new koneksi();
private final PesanDialog pesanDialog=new PesanDialog();

    public String getNamaSuplier() {
        return namaSuplier;
    }

    public void setNamaSuplier(String namaSuplier) {
        this.namaSuplier = namaSuplier;
    }

    public String getAlamatSuplier() {
        return alamatSuplier;
    }

    public void setAlamatSuplier(String alamatSuplier) {
        this.alamatSuplier = alamatSuplier;
    }

    public String getTelpSuplier() {
        return telpSuplier;
    }

    public void setTelpSuplier(String telpSuplier) {
        this.telpSuplier = telpSuplier;
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


    public boolean baca(String namaSuplier){
        boolean adaKesalahan = false;
        Connection connection;
        
        if ((connection = conn.getConnection()) != null){
            Statement sta;
            ResultSet rset;
            
            try {
                String SQLStatemen = "select * from suplier where nama='"+namaSuplier+"'";
                sta = connection.createStatement();
                rset = sta.executeQuery(SQLStatemen);
                
                rset.next();
                if (rset.getRow()>0){
                    this.namaSuplier = rset.getString("nama");
                    this.alamatSuplier = rset.getString("alamat");
                    this.telpSuplier=rset.getString("telp");
                } else {
                    adaKesalahan = true;
                    pesan = "Nama suplier \""+namaSuplier+"\" tidak ditemukan";
                }
                
                sta.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true; 
                pesan = "Tidak dapat membuka tabel suplier\n"+ex;
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
                SQLStatemen = "select nama,alamat from suplier"; 
                sta = connection.createStatement(); 
                rset = sta.executeQuery(SQLStatemen);
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][2];
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do { 
                        list[i] = new Object[]{rset.getString("nama"), rset.getString("alamat")};
                        i++;
                    } while (rset.next());
                }
                
                sta.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel suplier"+ex;
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
                String SQLStatemen = "select * from suplier where nama='"+namaSuplier+"'";
                Statement sta = connection.createStatement();
                ResultSet rset = sta.executeQuery(SQLStatemen);
                
                rset.next();
                if (rset.getRow()>0){
                    if (pesanDialog.tampilkanPilihan("Nama suplier sudah ada\nApakah data diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update suplier set alamat='" + alamatSuplier + 
                                "', telp='" + telpSuplier +"' where nama='" + namaSuplier + "'";
                        System.out.println(SQLStatemen);
                        sta = connection.createStatement();
                        jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into suplier(nama, alamat, telp) values ('" +
                            namaSuplier + "','" + alamatSuplier + "','" + telpSuplier + "')"; 
                    sta = connection.createStatement();
                    jumlahSimpan = sta.executeUpdate(SQLStatemen);
                }
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data suplier";
                    }
                }
                
                sta.close();
                rset.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel suplier\n"+ex;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    public boolean hapus(String namaSuplier){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = conn.getConnection()) != null){
            int jumlahHapus;
            Statement sta;
            
            try {
                String SQLStatemen = "delete from suplier where nama='"+namaSuplier+"'";
                sta = connection.createStatement();
                jumlahHapus = sta.executeUpdate(SQLStatemen);
                
                if (jumlahHapus < 1){
                    pesan = "Data suplier dengan nama "+namaSuplier+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                sta.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel suplier\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }

}

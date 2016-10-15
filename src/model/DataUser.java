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

public class DataUser {
private String userId,password;
private String pesan;
private Object [][] list;
private final koneksi conn=new koneksi();
private final PesanDialog pesanDialog=new PesanDialog();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    public boolean baca(String namaCustomer){
        boolean adaKesalahan = false;
        Connection connection;
        
        if ((connection = conn.getConnection()) != null){
            Statement sta;
            ResultSet rset;
            
            try {
                String SQLStatemen = "select * from user where userid='"+userId+"'";
                sta = connection.createStatement();
                rset = sta.executeQuery(SQLStatemen);
                
                rset.next();
                if (rset.getRow()>0){
                    this.userId = rset.getString("userId");
                    this.password = rset.getString("password");
                   // this.telpCustomer=rset.getString("telp");
                } else {
                    adaKesalahan = true;
                    pesan = "User ID \""+userId+"\" tidak ditemukan";
                }
                
                sta.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true; 
                pesan = "Tidak dapat membuka tabel user\n"+ex;
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
                SQLStatemen = "select userid,password from user"; 
                sta = connection.createStatement(); 
                rset = sta.executeQuery(SQLStatemen);
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][2];
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do { 
                        list[i] = new Object[]{rset.getString("userid"), rset.getString("password")};
                        i++;
                    } while (rset.next());
                }
                
                sta.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel user"+ex;
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
                String SQLStatemen = "select * from user where userid='"+userId+"'";
                Statement sta = connection.createStatement();
                ResultSet rset = sta.executeQuery(SQLStatemen);
                
                rset.next();
                if (rset.getRow()>0){
                    if (pesanDialog.tampilkanPilihan("User ID sudah ada\nApakah data diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update user set password='" + password + 
                                "' where userid='" + userId + "'";
                        System.out.println(SQLStatemen);
                        sta = connection.createStatement();
                        jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into user(userid, password) values ('" +
                            userId + "','" + password + "')"; 
                    sta = connection.createStatement();
                    jumlahSimpan = sta.executeUpdate(SQLStatemen);
                }
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data user";
                    }
                }
                
                sta.close();
                rset.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel user\n"+ex;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    public boolean hapus(String userId){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = conn.getConnection()) != null){
            int jumlahHapus;
            Statement sta;
            
            try {
                String SQLStatemen = "delete from user where userid='"+userId+"'";
                sta = connection.createStatement();
                jumlahHapus = sta.executeUpdate(SQLStatemen);
                
                if (jumlahHapus < 1){
                    pesan = "Data user dengan userid "+userId+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                sta.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel user\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+conn.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author WXO
 */
public class loginModel {
    Connector connector = new Connector();
    
    Connection koneksi = connector.koneksi;
    Statement statement = connector.statement;
    
    public int daftarAkun(String nama, String alamat, String telp, String username, String password){
        int jmlData=0;
        try {
           statement = koneksi.createStatement();
           String query = "Select * from account WHERE Username='" + username + "' OR Nama='" + nama + "'"; 
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()){ 
                jmlData++;
            }
            
            if (jmlData==0) {
                
                query = "INSERT INTO account VALUES('"+username+"', '"+password+"', '"+nama+"', '"+alamat+"', '"+telp+"')";
           
                statement = (Statement) koneksi.createStatement();
                statement.executeUpdate(query); //execute querynya
                JOptionPane.showMessageDialog(null, "Data Berhasil didaftarkan");
            }
            else {
                JOptionPane.showMessageDialog(null, "Account sudah ada");
            }
            
            return jmlData;
        } catch (HeadlessException | SQLException sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, sql.getMessage());
            return 0;
        }
    }
    
    public int loginAkun(String username, String password){
        int jmlData=0;
        try {
           String nama="";
           statement = koneksi.createStatement();
           String query = "Select * from account WHERE Username='" + username + "' AND Password='" + password + "'"; 
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()){ 
               nama = resultSet.getString("Nama"); 
                jmlData++;
            }
            
            if (jmlData>0) {
                JOptionPane.showMessageDialog(null, "Hello,  " + nama);
            }
            else {
                JOptionPane.showMessageDialog(null, "Username atau password tidak sesuai");
            }
            
            return jmlData;
        } catch (HeadlessException | SQLException sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, sql.getMessage());
            return 0;
        }
    }
    
}

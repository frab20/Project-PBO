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
public class userModel {
    
    Connector connector = new Connector();
    
    Connection koneksi = connector.koneksi;
    Statement statement = connector.statement;
    
    public void insertData(String username, float berat, String jenis, int deliver){
        int jmlData=0;
        try {
           String query = "Select * from pemesanan WHERE Username='" + username + "'"; 
           System.out.println(username + berat + " " + jenis);
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()){ 
                jmlData++;
            }
            
            if (jmlData<=5) {
                float harga,biaya;
                
                switch (jenis) {
                   case "Jeans":
                       harga = 4000;
                       break;
                   case "Selimut":
                       harga = 7000;
                       break;
                   case "Extraordinary":
                       harga = 10000;
                       break;
                   default:
                       harga = 3000;
                       break;
               }
                
                biaya = harga * berat; 
                
                query = "INSERT INTO pemesanan (Username, Berat, Jenis, Progress, Biaya) VALUES('"+username+"', '"+berat+"', '"+jenis+"', '"+deliver+"', '"+biaya+"')";
           
                statement = (Statement) koneksi.createStatement();
                statement.executeUpdate(query); //execute querynya
                System.out.println("Berhasil ditambahkan");
                JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
            }
            else {
                JOptionPane.showMessageDialog(null, "Maksimal 5 orderan");
            }
        } catch (HeadlessException | SQLException sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
    public int getBanyakData(String username){
        int jmlData = 0;
        try{
            statement = koneksi.createStatement();
            String query = "Select * from pemesanan WHERE Username='" + username + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){ 
                jmlData++;
            }
            return jmlData;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }
    
    public String[][] readData(String username){
        try{
            int jmlData = 0;
            
            String data[][] = new String[getBanyakData(username)][5]; //baris, kolom nya ada 5
            
            String query = "Select * from pemesanan WHERE Username='" + username + "'"; 
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[jmlData][0] = resultSet.getString("ID"); //harus sesuai nama kolom di mysql
                data[jmlData][1] = resultSet.getString("Berat");                
                data[jmlData][2] = resultSet.getString("Jenis");
                data[jmlData][3] = resultSet.getString("Biaya");
                data[jmlData][4] = Progress(resultSet.getString("Progress"));
                jmlData++;
            }
            return data;
               
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }
    
    private String Progress(String number){
        
        switch (number) {
            case "0":
                return "Pick up";
            case "1":
                return "Delivery";
            default:
                return "Selesai"; 
        }
        
    }
    
    public void Delete (String ID) {
        try{
            String query = "DELETE FROM pemesanan WHERE ID = "+ID+"";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus");
            
        }catch(SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
    
    public void updateProfil(String nama, String alamat, String telp, String username, String password){
        int jmlData=0;
         try {
           String query = "Select * from account WHERE Username='" + username + "' AND Password='" + password + "'";  
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()){ 
                jmlData++;
            }
           
             if (jmlData==1) {
                query = "UPDATE account SET Nama=" + nama + ", Alamat=" + alamat + ", Telp=" + telp + " WHERE Username='" + username + "' AND Password='" + password + "'";
                statement = (Statement) koneksi.createStatement();
                statement.executeUpdate(query); //execute querynya
                System.out.println("Berhasil diupdate");
                JOptionPane.showMessageDialog(null, "Data Berhasil diupdate");
             }
             else {
                 JOptionPane.showMessageDialog(null, "Data Tidak Ada");
             }
           
        } catch (Exception sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
}

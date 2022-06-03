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
import javax.swing.JOptionPane;

/**
 *
 * @author WXO
 */
public class adminModel {
    
    Connector connector = new Connector();
    
    Connection koneksi = connector.koneksi;
    Statement statement = connector.statement;
    
    
    public int getBanyakData(){
        int jmlData = 0;
        try{
            statement = koneksi.createStatement();
            String query = "Select * from pemesanan";
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
    
    public String[][] readData(){
        try{
            int jmlData = 0;
            
            String data[][] = new String[getBanyakData()][7]; //baris, kolom nya ada 4
            
            String query = "Select account.Nama as nama, account.Alamat as alamat, account.Telp as telp, pemesanan.Berat as berat, pemesanan.Progress as progress, pemesanan.Biaya as biaya, pemesanan.Jenis as jenis   from pemesanan INNER JOIN account ON pemesanan.Username = account.Username"; 
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[jmlData][0] = resultSet.getString("nama"); //harus sesuai nama kolom di mysql
                data[jmlData][1] = resultSet.getString("alamat");                
                data[jmlData][2] = resultSet.getString("telp");
                data[jmlData][3] = resultSet.getString("berat");
                data[jmlData][4] = resultSet.getString("jenis");
                data[jmlData][5] = resultSet.getString("biaya");
                data[jmlData][6] = Progress(resultSet.getString("Progress"));
                jmlData++;
            }
            return data;
               
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }
    
    public String[][] cariData(String nama){
        try{
            int jmlData = 0;
            
            String data[][] = new String[getBanyakData()][7]; //baris, kolom nya ada 4
            
            String query = "Select account.Nama as nama, account.Alamat as alamat, account.Telp as telp, pemesanan.Berat as berat, pemesanan.Progress as progress, pemesanan.Biaya as biaya, pemesanan.Jenis as jenis   from pemesanan INNER JOIN account ON pemesanan.Username = account.Username WHERE account.Nama='" +nama+ "'"; 
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[jmlData][0] = resultSet.getString("nama"); //harus sesuai nama kolom di mysql
                data[jmlData][1] = resultSet.getString("alamat");                
                data[jmlData][2] = resultSet.getString("telp");
                data[jmlData][3] = resultSet.getString("berat");
                data[jmlData][4] = resultSet.getString("jenis");
                data[jmlData][5] = resultSet.getString("biaya");
                data[jmlData][6] = Progress(resultSet.getString("Progress"));
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
    
    public  void updateProgress(String nama){
        int jmlData=0;
        String temp="";
        try {
           String query = "Select Username from account WHERE Nama='" + nama + "'";  
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()){ 
                temp = resultSet.getString("Username");  
                jmlData++;
            }
           
             if (jmlData==1) {
                query = "UPDATE pemesanan SET Progress=2 WHERE Username='" + temp + "'";
                statement = (Statement) koneksi.createStatement();
                statement.executeUpdate(query); //execute querynya
                JOptionPane.showMessageDialog(null, "Progress Berhasil diupdate");
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

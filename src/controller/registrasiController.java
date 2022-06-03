/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.loginModel;
import model.userModel;
import view.regisView;
import view.userView;

/**
 *
 * @author WXO
 */
public class registrasiController {
    loginModel loginM;
    regisView regisV;
    String nama, alamat, telp, username, password, re_pass;
    
    public registrasiController(loginModel loginM, regisView regisV) {
        
        this.loginM = loginM;
        this.regisV = regisV;
        
        regisV.setVisible(true);
        regisV.setLocationRelativeTo(null);
        regisV.setResizable(false);
        
        //remove panel
        regisV.downPanel.removeAll();
        regisV.downPanel.repaint();
        regisV.downPanel.revalidate();

        //add panel
        regisV.downPanel.add(regisV.daftar1);
        regisV.downPanel.repaint();
        regisV.downPanel.revalidate();
        
        regisV.btnDaftar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nama = regisV.getNama();
                alamat = regisV.getAlamat();
                telp = regisV.getTelp();
            
                if(nama.equals(""))  //Jika Nilai Yang diambil pada TxtNama Kosong ("")
                {   
                    JOptionPane.showMessageDialog(null,"Nama Tidak Boleh Kosong"); //Tampilkan Nilai Pada Comand Dialog
                }else if(telp.equals(""))  //Jika Nilai Yang diambil pada TxtNama Kosong ("")
                {
                    JOptionPane.showMessageDialog(null,"Nomor Telphone Tidak Boleh Kosong"); //Tampilkan Nilai Pada Comand Dialog
                }else if(alamat.equals(""))  //Jika Nilai Yang diambil pada TxtNama Kosong ("")
                {
                    JOptionPane.showMessageDialog(null,"Alamat Tidak Boleh Kosong"); //Tampilkan Nilai Pada Comand Dialog
                }else {

                    //remove panel
                    regisV.downPanel.removeAll();
                    regisV.downPanel.repaint();
                    regisV.downPanel.revalidate();

                    //add panel
                    regisV.downPanel.add(regisV.daftar2);
                    regisV.downPanel.repaint();
                    regisV.downPanel.revalidate();
                }    
                
            }
        });
    
        regisV.btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                username = regisV.getUsername();
                password = regisV.getPass();
                re_pass = regisV.getRe_pass();
                
                if(username.equals(""))  //Jika Nilai Yang diambil pada TxtNama Kosong ("")
                {   
                    JOptionPane.showMessageDialog(null,"Username Tidak Boleh Kosong"); //Tampilkan Nilai Pada Comand Dialog
                }else if(password.equals(""))  //Jika Nilai Yang diambil pada TxtNama Kosong ("")
                {
                    JOptionPane.showMessageDialog(null,"Password Telphone Tidak Boleh Kosong"); //Tampilkan Nilai Pada Comand Dialog
                }else if(re_pass.equals(""))  //Jika Nilai Yang diambil pada TxtNama Kosong ("")
                {
                    JOptionPane.showMessageDialog(null,"Re-Password Tidak Boleh Kosong"); //Tampilkan Nilai Pada Comand Dialog
                }else if(!re_pass.equalsIgnoreCase(password))  //Jika Nilai Yang diambil pada TxtNama Kosong ("")
                {
                    JOptionPane.showMessageDialog(null,"Re-Password Tidak Sama dengan Password"); //Tampilkan Nilai Pada Comand Dialog
                }else {

                    int banyakData = loginM.daftarAkun(nama, alamat, telp, username, password);
                    
                    if(banyakData==0){
                        regisV.setVisible(false);
                        userView userV =new userView();
                        userModel userM = new userModel();
                        userController uc = new userController(userM, userV, username);
                    }
                }
                
            }
        });    
    }
}

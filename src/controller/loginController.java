/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import model.adminModel;
import model.loginModel;
import model.userModel;
import view.adminView;
import view.loginView;
import view.regisView;
import view.userView;

/**
 *
 * @author WXO
 */
public class loginController {
    loginModel loginM;
    loginView loginV;
    
    private String username;

    public loginController(loginModel loginM, loginView loginV) {
        this.loginM = loginM;
        this.loginV = loginV;
        
        loginV.setVisible(true);
        loginV.setLocationRelativeTo(null);
        loginV.setResizable(false);
        
        loginV.btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                username = loginV.getUser();
                String pass = loginV.getPass();
            
            if(username.equals(""))  //Jika Nilai Yang diambil pada TxtNama Kosong ("")
            {   
                JOptionPane.showMessageDialog(null,"Username Tidak Boleh Kosong"); //Tampilkan Nilai Pada Comand Dialog
            }else if(pass.equals(""))  //Jika Nilai Yang diambil pada TxtNama Kosong ("")
            {
                JOptionPane.showMessageDialog(null,"Password Tidak Boleh Kosong"); //Tampilkan Nilai Pada Comand Dialog
            }else if(username.equals("admin") && pass.equals("admin123"))  //Jika Nilai Yang diambil pada TxtNama Kosong ("")
            {
                JOptionPane.showMessageDialog(null,"Hello, admin"); //Tampilkan Nilai Pada Comand Dialog
                
                loginV.setVisible(false);
                adminModel adminM = new adminModel();
                adminView adminV =new adminView();
                adminController rc = new adminController(adminM, adminV);
                
            }else {
                int banyakData = loginM.loginAkun(username, pass);
                
                if(banyakData>0){
                    loginV.setVisible(false);
                    userView userV =new userView();
                    userModel userM = new userModel();
                    userController uc = new userController(userM, userV, username);
                     
                }
            }    
                
            }
        });
        
        loginV.btnRegis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                loginV.setVisible(false);
                regisView regisV =new regisView();
                registrasiController rc = new registrasiController(loginM, regisV);
                
            }
        });
        
    } 

    public String getUsername() {
        return username;
    }
}

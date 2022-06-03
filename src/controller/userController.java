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
import javax.swing.JTable;
import model.loginModel;
import model.userModel;
import view.regisView;
import view.userView;

/**
 *
 * @author WXO
 */
public class userController {
    userModel userM;
    userView userV;
    String username;

    public userController(userModel userM, userView userV, String username) {
        this.userM = userM;
        this.userV = userV;
        this.username = username;
        
        userV.setVisible(true);
        userV.setLocationRelativeTo(null);
        userV.setResizable(false);
        
        if (userM.getBanyakData(username) !=0) {
            updateData();
            String dataLaundry[][] = userM.readData(username);
            userV.table.setModel((new JTable(dataLaundry, userV.namaKolom)).getModel());
        }
        else {
            updateData();
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
        
        userV.submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                float berat = Float.parseFloat(userV.getBerat());
                String jenis = userV.getJenis();
                int delivery = userV.getDelivery();
                
                userM.insertData(username, berat, jenis, delivery);
                String dataLaundry[][] = userM.readData(username);
                userV.table.setModel((new JTable(dataLaundry, userV.namaKolom)).getModel());
            }
        });    
        
        userV.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int baris = userV.table.getSelectedRow();

                String dataterpilih = userV.table.getValueAt(baris, 0).toString();

                int input = JOptionPane.showConfirmDialog(null,
                        "Apa order laundry ingin dihapus " + dataterpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    userM.Delete(dataterpilih);
                    String dataLaundry[][] = userM.readData(username);
                    userV.table.setModel((new JTable(dataLaundry, userV.namaKolom)).getModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak order Jadi dihapus");
                }
            }
        });
        
        userV.btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                userV.reset();
            }
        });
        
    
    }    
    
    private void updateData(){
        userV.lNama.setText(username);
        userV.lCount.setText(""+ userM.getBanyakData(username) +"");
    }
}

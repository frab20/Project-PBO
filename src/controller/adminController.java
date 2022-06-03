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
import model.adminModel;
import view.adminView;

/**
 *
 * @author WXO
 */
public class adminController {
    adminModel adminM;
    adminView adminV;

    public adminController(adminModel adminM, adminView adminV) {
        this.adminM = adminM;
        this.adminV = adminV;
        
        adminV.setVisible(true);
        adminV.setLocationRelativeTo(null);
        adminV.setResizable(false);
        
        if (adminM.getBanyakData() !=0) {
            String dataLaundry[][] = adminM.readData();
            adminV.table.setModel((new JTable(dataLaundry, adminV.namaKolom)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
        
        adminV.btnCari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String nama = adminV.getCari();
                
                String dataLaundry[][] = adminM.cariData(nama);
                adminV.table.setModel((new JTable(dataLaundry, adminV.namaKolom)).getModel());
            }
        }); 
        
        adminV.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int baris = adminV.table.getSelectedRow();

                String dataterpilih = adminV.table.getValueAt(baris, 0).toString();

                int input = JOptionPane.showConfirmDialog(null,
                        "Apa laundry sudah selesai " + dataterpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    adminM.updateProgress(dataterpilih);
                    String dataLaundry[][] = adminM.readData();
                    adminV.table.setModel((new JTable(dataLaundry, adminV.namaKolom)).getModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak Jadi diselesaikan");
                }
            }
        });
    }    
}

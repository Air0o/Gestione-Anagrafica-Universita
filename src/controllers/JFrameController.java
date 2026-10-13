/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Universita;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import views.JFrameView;

/**
 *
 * @author GI.AIROLDI
 */
public class JFrameController {
   
    public JFrameController(Universita uni){
        JFrameView view = new JFrameView();
        
        JTextArea label = view.getTextArea();
        
        label.setText(uni.getReport());
        
        view.setVisible(true);
    }
}

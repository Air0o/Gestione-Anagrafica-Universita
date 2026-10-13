/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestioneuniversita;

import domain.Universita;
import persistence.XMLParser;

/**
 *
 * @author GI.AIROLDI
 */
public class GestioneUniversita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        XMLParser parser = new XMLParser();
        Universita uni = parser.readFile();
        
        uni.stampaReport();
        
       parser.writeFile(uni, "provaScrittura.xml");
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.ArrayList;
import java.util.List;

public class Studente {
    private String nome;
    private String cognome;
    private int eta;
    private String corsoDiLaurea;
    private List<Esame> esami;
    
    public Studente(String nome, String cognome, int eta, String corsoDiLaurea) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.corsoDiLaurea = corsoDiLaurea;
        this.esami = new ArrayList<>();
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    
    public Integer getEta() { return eta; }
    public void setEta(int eta) { this.eta = eta; }
    
    public String getCorsoDiLaurea() { return corsoDiLaurea; }
    public void setCorsoDiLaurea(String corsoDiLaurea) { 
        this.corsoDiLaurea = corsoDiLaurea; 
    }
    
    public List<Esame> getEsami() { return esami; }
    
    // Metodi per gestire gli esami
    public void aggiungiEsame(Esame esame) {
        esami.add(esame);
    }
    
    public String getNomeCompleto() {
        return nome + " " + cognome;
    }
    
    public int getNumeroEsami() {
        return esami.size();
    }
    
    // Calcola la media dei voti (DA IMPLEMENTARE dagli studenti)
    public Double calcolaMedia() {
        double tot = 0;
        for(Esame esame : esami){
            tot += esame.getVoto();
        }
        
        return tot/(double)esami.size();
    }
    
    // Conta quanti esami hanno voto >= 28
    public int contaEsamiEccellenti() {
        int count = 0;
        for(Esame esame : esami){
            if(esame.getVoto() >= 28)
                count++;
        }
        return count;
    }
    
    @Override
    public String toString() {
        return getNomeCompleto() + " (" + eta + " anni) - " + 
               corsoDiLaurea + " - Esami sostenuti: " + esami.size() + 
                " - Media: " + calcolaMedia();
    }
    
    public void stampaDettagli() {
        System.out.println("\n=== " + getNomeCompleto() + " ===");
        System.out.println("Età: " + eta);
        System.out.println("Corso di Laurea: " + corsoDiLaurea);
        System.out.println("Numero esami: " + getNumeroEsami());
        System.out.printf("Media voti: %.2f\n", calcolaMedia());
        System.out.println("\nEsami sostenuti:");
        for (Esame esame : esami) {
            System.out.println("  - " + esame);
        }
    }
}
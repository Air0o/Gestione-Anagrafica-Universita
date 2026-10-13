/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;

public class Esame {

    private String nomeEsame;
    private int voto;
    private LocalDate dataEsame;

    public Esame(String nomeEsame, int voto, LocalDate dataEsame) {
        this.nomeEsame = nomeEsame;
        this.dataEsame = dataEsame;

        setVoto(voto);
    }

    // Getters e Setters
    public String getNomeEsame() {
        return nomeEsame;
    }

    public void setNomeEsame(String nomeEsame) {
        this.nomeEsame = nomeEsame;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        if(voto < 18 || voto > 30){
            //??? la traccia non dice cosa fare :)
        }
        this.voto = voto;
    }

    public LocalDate getDataEsame() {
        return dataEsame;
    }

    public void setDataEsame(LocalDate dataEsame) {
        this.dataEsame = dataEsame;
    }

    public boolean isLode() {
        return voto == 30; // Potrebbe essere esteso per 30 e lode
    }    
    
    @Override
    public String toString() {
        return nomeEsame + ": " + voto + "/30 (Data: " + dataEsame + ")";
    }
}

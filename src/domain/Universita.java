/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Universita {

    private String nome;
    private List<Studente> studenti;

    public Universita(String nome) {
        this.nome = nome;
        this.studenti = new ArrayList<>();
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Studente> getStudenti() {
        return studenti;
    }

    // Metodo per aggiungere studenti
    public void aggiungiStudente(Studente studente) {
        studenti.add(studente);
    }

    // Trova studenti per corso di laurea (DA IMPLEMENTARE dagli studenti)
    public List<Studente> trovaStudentiPerCorso(String corsoDiLaurea) {
        List<Studente> studentiCorso = new ArrayList<>();
        for (Studente s : studenti) {
            if (s.getCorsoDiLaurea().equals(corsoDiLaurea)) {
                studentiCorso.add(s);
            }
        }
        return studentiCorso;
    }

    // Trova lo studente con la media più alta (DA IMPLEMENTARE dagli studenti)
    public Studente trovaStudenteMigliore() {
        Studente best = studenti.getFirst();
        for (int i = 1; i < studenti.size(); i++) {
            if (studenti.get(i).calcolaMedia() > best.calcolaMedia()) {
                best = studenti.get(i);
            }
        }
        return best;
    }

    // Calcola la media generale di tutti gli studenti (DA IMPLEMENTARE dagli studenti)
    public double calcolaMediaGenerale() {
        double tot = 0;
        for (Studente s : studenti) {
            tot += s.calcolaMedia();
        }
        return tot / (double) studenti.size();
    }

    // Conta il numero totale di esami sostenuti
    public int contaTotaleEsami() {
        int totale = 0;
        for (Studente studente : studenti) {
            totale += studente.getNumeroEsami();
        }
        return totale;
    }

    // Trova tutti i corsi di laurea unici
    public List<String> getCorsiDiLaurea() {
        return studenti.stream()
                .map(Studente::getCorsoDiLaurea)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, Double> getMapNomeMediaEsami() {
        List<Esame> esami = new ArrayList<>();

        for (Studente s : studenti) {
            esami.addAll(s.getEsami());
        }

        //nome, count, tot
        Map<String, double[]> map = new HashMap();

        for (Esame e : esami) {
            String nomeEsame = e.getNomeEsame();
            if (!map.keySet().contains(nomeEsame)) {
                map.put(nomeEsame, new double[]{1, e.getVoto()});
            } else {
                map.put(nomeEsame, new double[]{map.get(nomeEsame)[0] + 1, map.get(nomeEsame)[0] + e.getVoto()});
            }
        }

        Map<String, Double> mapNomeMedia = new HashMap();

        for (String key : map.keySet()) {
            mapNomeMedia.put(key, map.get(key)[1] / map.get(key)[0]);
        }

        return mapNomeMedia;
    }

    public void ordinaStudentiPerMediaDecrescente() {
        studenti.sort((o1, o2) -> o2.calcolaMedia().compareTo(o1.calcolaMedia()));
    }

    public Double getPercentVotiMaggioriDi28() {
        Double countGreater = 0D;
        Double countTotal = 0D;

        for (Studente studente : studenti) {
            for (Esame esame : studente.getEsami()) {
                countTotal++;
                if (esame.getVoto() >= 28) {
                    countGreater++;
                }
            }
        }

        return 100 * countGreater / countTotal;
    }

    public List<Studente> getStudentePerEsame(String nomeEsame) {
        List<Studente> studentiEsame = new ArrayList<>();

        for (Studente studente : studenti) {
            for (Esame esame : studente.getEsami()) {
                if (esame.getNomeEsame().equals(nomeEsame)) {
                    studentiEsame.add(studente);
                    break;
                }
            }
        }

        return studentiEsame;
    }

    public Map<Studente, DistribuzioneVoti> getDistribuzioneVotiCorso(String corso) {
        //Boh
        return null;
    }

    // Stampa report completo (DA IMPLEMENTARE dagli studenti)
    public void stampaReport() {
        System.out.println("\tREPORT ANAGRAFICA UNIVERSITA'");
        System.out.println("Universita': " + nome);

        System.out.println();
        System.out.println("--- STATISTICHE GENERALI ---");
        System.out.println("Numero studenti iscritti: " + studenti.size());
        System.out.println("Totale esami sostenuti: " + contaTotaleEsami());
        System.out.println("Media generale universita': " + calcolaMediaGenerale() + "/30");

        System.out.println();
        System.out.println("--- CORSI DI LAUREA ---");
        for (String corso : getCorsiDiLaurea()) {
            System.out.println("- " + corso + ": " + trovaStudentiPerCorso(corso).size() + " studenti");
        }

        System.out.println();
        System.out.println("--- STUDENTE CON MEDIA PIU' ALTA ---");
        Studente best = trovaStudenteMigliore();
        System.out.println(best.getNome() + " " + best.getCognome() + " - Media: " + best.calcolaMedia() + "/30");

        System.out.println();
        System.out.println("--- ELENCO STUDENTI ---");
        for (Studente s : studenti) {
            System.out.println(s);
        }

        System.out.println();
        System.out.println("--- ELENCO STUDENTI PER MEDIA DECRESCENTE ---");
        ordinaStudentiPerMediaDecrescente();
        for (Studente s : studenti) {
            System.out.println(s);
        }

        System.out.println();
        System.out.println("--- MEDIE ESAMI ---");
        Map<String, Double> mapEsamiMedie = getMapNomeMediaEsami();
        for (String nome : mapEsamiMedie.keySet()) {
            System.out.println("- " + nome + ": " + mapEsamiMedie.get(nome) + "/30");
        }

        System.out.println();
        System.out.println("--- ESAME PIU' DIFFICILE ---");

        //Lo avrei fatto in un metodo ma devo creare una classe apposta per ritornare nome e media insieme
        String nomeEsameDifficile = null;
        Double mediaEsameDifficile = null;
        for (String nome : mapEsamiMedie.keySet()) {
            if (nomeEsameDifficile == null) {
                nomeEsameDifficile = nome;
                mediaEsameDifficile = mapEsamiMedie.get(nome);
            } else {
                Double newMedia = mapEsamiMedie.get(nome);
                if (newMedia < mediaEsameDifficile) {
                    nomeEsameDifficile = nome;
                    mediaEsameDifficile = newMedia;
                }
            }
        }
        System.out.println("- " + nomeEsameDifficile + ": " + mediaEsameDifficile + "/30");

        System.out.println();
        System.out.println("--- ESAMI CON VOTO >= 28 ---");
        System.out.println(getPercentVotiMaggioriDi28() + "%");
    }

    public String getReport() {
        StringBuilder sb = new StringBuilder();
        String nl = System.lineSeparator();

        appendLine(sb, "\tREPORT ANAGRAFICA UNIVERSITA'");
        appendLine(sb, "Universita': " + nome);

        appendLine(sb, "");
        appendLine(sb, "--- STATISTICHE GENERALI ---");
        appendLine(sb, "Numero studenti iscritti: " + studenti.size());
        appendLine(sb, "Totale esami sostenuti: " + contaTotaleEsami());
        appendLine(sb, "Media generale universita': " + calcolaMediaGenerale() + "/30");

        appendLine(sb, "");
        appendLine(sb, "--- CORSI DI LAUREA ---");
        for (String corso : getCorsiDiLaurea()) {
            appendLine(sb, "- " + corso + ": " + trovaStudentiPerCorso(corso).size() + " studenti");
        }

        appendLine(sb, "");
        appendLine(sb, "--- STUDENTE CON MEDIA PIU' ALTA ---");
        Studente best = trovaStudenteMigliore();
        appendLine(sb, best.getNome() + " " + best.getCognome() + " - Media: " + best.calcolaMedia() + "/30");

        appendLine(sb, "");
        appendLine(sb, "--- ELENCO STUDENTI ---");
        for (Studente s : studenti) {
            appendLine(sb, s.toString());
        }

        appendLine(sb, "");
        appendLine(sb, "--- ELENCO STUDENTI PER MEDIA DECRESCENTE ---");
        ordinaStudentiPerMediaDecrescente();
        for (Studente s : studenti) {
            appendLine(sb, s.toString());
        }

        appendLine(sb, "");
        appendLine(sb, "--- MEDIE ESAMI ---");
        Map<String, Double> mapEsamiMedie = getMapNomeMediaEsami();
        for (String nomeEsame : mapEsamiMedie.keySet()) {
            appendLine(sb, "- " + nomeEsame + ": " + mapEsamiMedie.get(nomeEsame) + "/30");
        }

        appendLine(sb, "");
        appendLine(sb, "--- ESAME PIU' DIFFICILE ---");

        String nomeEsameDifficile = null;
        Double mediaEsameDifficile = null;

        for (Map.Entry<String, Double> entry : mapEsamiMedie.entrySet()) {
            String nomeEsame = entry.getKey();
            Double mediaEsame = entry.getValue();

            if (nomeEsameDifficile == null || mediaEsame < mediaEsameDifficile) {
                nomeEsameDifficile = nomeEsame;
                mediaEsameDifficile = mediaEsame;
            }
        }

        appendLine(sb, "- " + nomeEsameDifficile + ": " + mediaEsameDifficile + "/30");

        appendLine(sb, "");
        appendLine(sb, "--- ESAMI CON VOTO >= 28 ---");
        appendLine(sb, getPercentVotiMaggioriDi28() + "%");

        return sb.toString();
    }

    private void appendLine(StringBuilder sb, String line) {
        sb.append(line).append(System.lineSeparator());
    }
}

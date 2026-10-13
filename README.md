# Gestione Anagrafica Università

Applicazione Java per la gestione dei dati anagrafici di un'università e degli esami sostenuti dagli studenti.

## Descrizione

Il progetto consiste nella realizzazione di un'applicazione Java in grado di leggere un file XML contenente le informazioni relative a un'università, agli studenti iscritti e agli esami sostenuti.

I dati vengono elaborati utilizzando **JAXP DOM** e organizzati all'interno di una struttura a oggetti appropriata.

## Obiettivi

L'applicazione dovrà:

- leggere e analizzare un file XML;
- utilizzare l'API **JAXP DOM** per navigare il documento;
- trasformare i dati XML in oggetti Java;
- gestire le informazioni relative a studenti, corsi di laurea ed esami;
- fornire funzionalità di analisi e ricerca sui dati acquisiti.

## Funzionalità bonus

### 1. Validazione dei dati

Verificare che i voti degli esami siano validi, ovvero compresi tra **18 e 30**.

### 2. Statistiche avanzate

Implementare funzionalità per:

- identificare l'esame più difficile, calcolato in base alla media dei voti più bassa;
- calcolare la percentuale di esami con voto maggiore o uguale a **28**;
- generare la distribuzione dei voti per corso di laurea.

### 3. Ordinamento degli studenti

Ordinare gli studenti in base alla loro media, in ordine decrescente.

### 4. Esportazione dei dati

Creare un metodo per esportare nuovamente i dati elaborati in formato XML utilizzando **DOM**.

### 5. Ricerca avanzata

Permettere di trovare tutti gli studenti che hanno sostenuto un determinato esame.

### 6. Interfaccia grafica

Realizzare una semplice interfaccia grafica per visualizzare e consultare i dati dell'università.

## Tecnologie utilizzate

- **Java**
- **JAXP DOM**
- **XML**
- **Programmazione orientata agli oggetti**

## Struttura prevista dei dati

Il file XML dovrà contenere almeno le informazioni relative a:

- studenti;
- nome e identificativo dello studente;
- corso di laurea;
- esami sostenuti;
- nome dell'esame;
- voto ottenuto.

## Scopo didattico

Il progetto ha lo scopo di mettere in pratica la lettura e la manipolazione di documenti XML in Java, l'utilizzo del pattern DOM e la progettazione di una struttura a oggetti per la gestione di dati complessi.

# Progetto Lotto - Studenti in Cattedra

In questo progetto, abbiamo sviluppato un software il quale obiettivo e`
di confutare i diversi misconcetti che spesso possono influire in
maniera pesante sui giocatori d'azzardo. L'applicativo e` sviluppato per i
dispositivi Android e, mediante una quantita` molto elevata di esperimenti,
e` in grado di generare un grafico in grado di sottolineare la minima
differenza nei guadagni di cinque giocatori, ognuno dei quali gioca con
una tattica diversa.

## Development

Per lo sviluppo abbiamo utilizzato Android Studio, unito ad un esteso
utilizzo di GitHub per una massima organizzazione del lavoro in
collaborazione. Le varie funzionalita` sono state testate singolarmente
su branch temporanei, poi eliminati, in modo da prevenire eventuali
effetti collaterali e malfunzionamenti.
Tutti i componenti hanno fornito eventuale supporto nello sviluppo di
funzionalita` nel quale ce ne sia stato il bisogno.
Il lavoro e` stato diviso nel seguente modo tra i componenti del team:

### Artur Smirnov - Lead Developer

Viste le precedenti esperienze in progetti di estese dimensioni, gli
viene dato il compito di supervisionare tutti i campi dello sviluppo del
software, dall'interfaccia grafica alle parti piu` profonde del codice,
e della principale progettazione del programma, definendo in linea
teorica la sua organizzazione. Contribuisce anche con l'implementazione
di un innovativo algoritmo di randomizzazione, riadattando un codice
direttamente dal Politecnico di Milano.
Si rende inoltre disponibile per un'aiuto nell'utilizzo di GitHub.

### Joel Bosio - Database Developer

Responsabile della stesura di buona parte del codice della classe di
Database, il cui compito e` quello di fornire i dati necessari alle
varie funzionalita` del programma nelle diverse Activity di cui esso
e` composto. Inoltre, si occupa dell'analisi dei dati nelle varie
partite, determinando quindi le vittorie di ciascun giocatore.
Il Database va a memorizzare svariate informazioni, solitamente in
forma di ArrayList, che vengono fornite tramite appositi Getter e 
Setter. Alcuni esempi sono la lista delle ultime estrazioni o delle
giocate dei vari Player. Analogamente, vengono salvate le varie
informazioni di stato dei vari giocatori, mediante la classe Profile.

### Enrico Visentin - Chart Developer

Si e` occupato dell'implementazione dei grafici, che hanno lo scopo di
rappresentare i dati dell'esperimento aleatorio in questione. Questi
sono realizzati tramite la libreria MP Chart. Il primo grafico mostra
il numero di vittorie per ciascun giocatore (asse Y), in rapporto al
numero di partite effettuate (asse X). Il secondo grafico, invece,
mostra il guadagno netto (o perdita) di ciascun giocatore (asse Y) in
rapporto al numero di partite effettuate.

### Andres Cocever - Player/Game Developer

Ha principalmente sviluppato gli schemi di comportamento dei vari
giocatori, definendo gli algoritmi per la creazione delle loro
scommesse ed interfacciandole con il Database. Definisce inoltre buona
parte della classe Game, la quale si occupa di eseguire le estrazioni 
preliminari alla simulazione (in modo da predisporre dei dati di
partenza) e poi di gestire le varie partite che vanno a comporre
l'esperimento, chiamando i vari giocatori ed inviando i dati al
Database.

### Alessandro Cecchini - Outside Cooperator

## Risultati dell'esperimento: analisi dei grafici

---
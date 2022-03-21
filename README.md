# Progetto Lotto - Studenti in Cattedra

In questo progetto, abbiamo sviluppato un software il quale obiettivo e\`
di confutare i diversi misconcetti che spesso possono influire in
maniera pesante sui giocatori d'azzardo. L'applicativo e\` sviluppato per i
dispositivi Android e, mediante una quantita\` molto elevata di esperimenti,
e\` in grado di generare un grafico in grado di sottolineare la minima
differenza nei guadagni di cinque giocatori, ognuno dei quali gioca con
una tattica diversa.

## Development

Per lo sviluppo abbiamo utilizzato Android Studio, unito ad un esteso
utilizzo di GitHub per una massima organizzazione del lavoro in
collaborazione. 
__Minimum SDK: 24 \(Android 7.0 Nougat\)__

__Android Studio Version: 2021.1 \(Bumblebee\)__

Il diagramma teorico proposto inizialmente era il seguente:
![alt text](https://github.com/VoltDevelopers/Simulation-lotto/blob/TestVersion-lotto/proj.svg?raw=true)
Segue il piano di lavoro inizialmente previsto:
![alt text](https://github.com/VoltDevelopers/Simulation-lotto/blob/TestVersion-lotto/templine.svg?raw=true)

Le varie funzionalita\` sono state testate singolarmente
su branch temporanei, poi eliminati, in modo da prevenire eventuali
effetti collaterali e malfunzionamenti.
Tutti i componenti hanno fornito eventuale supporto nello sviluppo di
funzionalita\` nel quale ce ne sia stato il bisogno.

Il lavoro e\` stato diviso nel seguente modo tra i componenti del team:

### Artur Smirnov - Lead Developer

Viste le precedenti esperienze in progetti di estese dimensioni, gli
viene dato il compito di supervisionare tutti i campi dello sviluppo del
software, dall'interfaccia grafica alle parti piu\` profonde del codice,
e della principale progettazione del programma, definendo in linea
teorica la sua organizzazione. Contribuisce anche con l'implementazione
di un innovativo algoritmo di randomizzazione, utilizzando un codice
direttamente dal Politecnico di Milano.
Si rende inoltre disponibile per un'aiuto nell'utilizzo di GitHub.

### Joel Bosio - Database Developer

Responsabile della stesura di buona parte del codice della classe di
Database, il cui compito e\` quello di fornire i dati necessari alle
varie funzionalita\` del programma nelle diverse Activity di cui esso
e\` composto. Inoltre, si occupa dell'analisi dei dati nelle varie
partite, determinando quindi le vittorie di ciascun giocatore.
Il Database va a memorizzare svariate informazioni, solitamente in
forma di ArrayList, che vengono fornite tramite appositi Getter e 
Setter. Alcuni esempi sono la lista delle ultime estrazioni o delle
giocate dei vari Player. Analogamente, vengono salvate le varie
informazioni di stato dei vari giocatori, mediante la classe Profile.

### Enrico Visentin - Chart Developer

Si e\` occupato dell'implementazione dei grafici, che hanno lo scopo di
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

### Esperimento Realistico

Avviando la simulazione in modalita\` realistica, si evince una tendenza
piuttosto simile tra le partite vinte dai diversi giocatori. Tuttavia,
essendo il gioco del lotto per definizione **non equo**, tutti i player
vanno inevitabilmente a perdere del denaro. Si fa notare che l'eventuale
guadagno maggiore di qualche giocatore rispetto agli altri e\` puramente
casuale, e cio\` e\` facilmente dimostrabile ripetendo la simulazione
altre volte, osservando che il giocatore che ha subito meno perdite
sara\`, con buona probabilita\`, diverso dal precedente.

### Esperimento Equo

In maniera del tutto analoga al caso precedente, la simulazione del gioco
equo sottolinea una percentuale di vincita che spazia in media tra il
4.5% e il 6.5% tra i vari giocatori, salvo casi eccezionali. Il grafico
dei guadagni, invece, mostra dei guadagni mediamente piu\` tendenti allo
zero, con alcuni casi eccezionali. Come nel caso dell'esperimento
realistico, alcuni giocatori potrebbero eccezionalmente subire spiccati
guadagni o perdite rispetto al resto, ma questo evento e\` da considerare
come puramente imprevedibile e non condizionato dai metodi utilizzati dai
vari player.

### Modalita\` Ambo

Questa particolare modalita\` fa giocare ai singoli giocatori una coppia
di numeri. Essi vinceranno una somma pari a 250$ nel caso **entrambi** i
numeri giocati venissero estratti. In questo caso si ha una decrescita
costante nel denaro di ciascun giocatore, con alcune rare crescite
improvvise delle loro entrate. Ancora di piu\` rispetto alle altre
simulazioni, la quantita\` estremamente ridotta di vincite rimane 
costante tra i vari giocatori.

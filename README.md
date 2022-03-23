# Progetto Lotto - Studenti in Cattedra V1.2

In questo progetto, abbiamo sviluppato un software il quale obiettivo è
di confutare i diversi misconcetti che spesso possono influire in
maniera pesante sui giocatori d'azzardo. L'applicativo è sviluppato per i
dispositivi Android e, mediante una quantità molto elevata di esperimenti,
è in grado di generare un grafico in grado di sottolineare la minima
differenza nei guadagni di cinque giocatori, ognuno dei quali gioca con
una tattica diversa.

## Development

Per lo sviluppo abbiamo utilizzato Android Studio, unito ad un esteso
utilizzo di GitHub per una massima organizzazione del lavoro in
collaborazione. 

__Minimum SDK: 24 \(Android 7.0 Nougat\)__

__Android Studio Version: 2021.1 \(Bumblebee\)__

### Esperimento Realistico

Avviando la simulazione in modalità realistica, si evince una tendenza
piuttosto simile tra le partite vinte dai diversi giocatori. Tuttavia,
essendo il gioco del lotto per definizione **non equo**, tutti i player
vanno inevitabilmente a perdere del denaro. Si fa notare che l'eventuale
guadagno maggiore di qualche giocatore rispetto agli altri è puramente
casuale, e ciò è facilmente dimostrabile ripetendo la simulazione
altre volte, osservando che il giocatore che ha subito meno perdite
sarà, con buona probabilità, diverso dal precedente.

### Esperimento Equo

In maniera del tutto analoga al caso precedente, la simulazione del gioco
equo sottolinea una percentuale di vincita che spazia in media tra il
4.5% e il 6.5% tra i vari giocatori, salvo casi eccezionali. Il grafico
dei guadagni, invece, mostra dei guadagni mediamente più tendenti allo
zero, con alcuni casi eccezionali. Come nel caso dell'esperimento
realistico, alcuni giocatori potrebbero eccezionalmente subire spiccati
guadagni o perdite rispetto al resto, ma questo evento è da considerare
come puramente imprevedibile e non condizionato dai metodi utilizzati dai
vari player.

### Modalità Ambo

Questa particolare modalità fa giocare ai singoli giocatori una coppia
di numeri. Essi vinceranno una somma pari a 250$ nel caso **entrambi** i
numeri giocati venissero estratti, oppure 5,62$ nel caso in cui solo uno
dei due numeri giocati dovesse risultare vincente. In questo caso si ha
una decrescita costante nel denaro di ciascun giocatore, con alcune rare
crescite improvvise delle loro entrate. Ancora di più rispetto alle altre
simulazioni, la quantità estremamente ridotta di vincite rimane 
costante tra i vari giocatori.

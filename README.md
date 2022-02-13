# Simulation-lotto
Implementare l'applicazione per Android, simulatore di lotto.
### Regole
 - Il branch *main* è una versione stabile del progetto, tutte le modifiche vengono prima apportate al branch di test, dopo convalidate e aggiunte alla versione stabile.
 - L'invio di un commit da parte dello sviluppatore deve contenere una parte di codice completo.
 - Commit deve contenere un messaggio del seguente tipo: *-m "Cosa è stato fatto / v.Versione / @Nick"*
### Come lavorare con il repository
- Scarica il repository (*git clone 'https / ssh-link'*)
- Vai al branch **TestVersion-lotto** (*git checkout TestVersion-lotto*)
- Se parte del codice su cui hai lavorato è funzionante e completa, invia commit
- Senior o Leader esaminerà il codice, apporterà le modifiche necessarie e lo invierà a **main**
### Tecnico
 - Ambiente di sviluppo: AndroidStudio (Bumblebee | 2021.1.1)
 - Versione Java: 17
## How to use git (cmd/terminal)
0) **git clone 'https/ssh-link'** --> creare una copia locale del repository remoto.
1) **git pull** --> aggiorna le modifiche dal repository remoto al repository locale.
2) **git checkout TestVersion-lotto** --> spostarsi nel test branch.
3) **git status** --> mostra le modifiche del repository locale rispetto al repository remoto.
4) **git add .** --> aggiunge tutte le modifiche al contenitore di invio al repository remoto.
4.1) **git restore .** --> cancella tutte le modifiche al contenitore di invio al repository remoto.
5) **git commit -m "msg"** --> crea il commit.
6) **git push** --> invia le modifiche al repository remoto.
## All git commands: http://guides.beanstalkapp.com/version-control/common-git-commands.html
# Simulation-lotto
Implementare l'applicazione per Android, simulatore di lotto. L'applicazione dove funzionare in due modalita: matematica e normale.

Matematica - viene eseguito il compito principale del progetto.
Noramle - il solito gioco del lotto.
### Regole
 - Il branch *main* è una versione stabile del progetto, tutte le modifiche vengono prima apportate al branch di test, dopo convalidate e aggiunte alla versione stabile.
 - L'invio di un commit da parte dello sviluppatore deve contenere una parte di codice completo.
 - Commit deve contenere un messaggio del seguente tipo: *-m "Cosa è stato fatto / v.Versione / @Nick"*
  - Ogni **Martedì** e **Giovedi** alle **17:00** si fa una riunione su Discord che discute il lavoro svolto, problemi e nuovi obiettivi.
### Come lavorare con il repository
- Scarica il repository (*git clone 'https / ssh-link'*)
- Vai al branch **TestVersion-lotto** (*git checkout TestVersion-lotto*)
- Se parte del codice su cui hai lavorato è funzionante e completa, invia commit
- Senior o Leader esaminerà il codice, apporterà le modifiche necessarie e lo invierà a **main**
### Tecnico
 - Ambiente di sviluppo: AndroidStudio (Bumblebee | 2021.1.1)
 - Versione Java: 1.8
 - Android API 25: 7.1.1 (Nougat) 
 - SDK: 32 (API 32)
## How to use git (cmd/terminal)
0) **git clone 'https/ssh-link'** --> creare una copia locale del repository remoto.
1) **git pull** --> aggiorna le modifiche dal repository remoto al repository locale.
2) **git checkout TestVersion-lotto** --> spostarsi nel test branch.
3) **git status** --> mostra le modifiche del repository locale rispetto al repository remoto.
4) **git add .** --> aggiunge tutte le modifiche al contenitore di invio al repository remoto.
5) **git restore .** --> cancella tutte le modifiche al contenitore di invio al repository remoto.
6) **git commit -m "msg"** --> crea il commit.
7) **git push** --> invia le modifiche al repository remoto.
### All git commands: http://guides.beanstalkapp.com/version-control/common-git-commands.html
### UML classi (backend documentation)  https://app.creately.com/diagram/gVNZn6yRGQz/edit
### Frontend docs https://miro.com/welcomeonboard/SUFGTmpvZ1FZMzBMNlIxU25uclliakdTT0pJeVphelE1cmt6RUR3cHhkUWdGSVFsZGNMRVZXeU91UXdTQXE3aHwzMDc0NDU3MzU5MTU3NTAyNDYz?invite_link_id=957489500654
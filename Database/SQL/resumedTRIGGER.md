Lista Trigger e Funzioni PL/pgSQL – UninaFoodLab

=============================================



\## CHEF / PARTECIPANTE



\* \[✓] Username univoco tra Chef e Partecipante

&nbsp; • Funzione: fun\_username\_unique

&nbsp; • Trigger: trg\_unico\_username\_partecipante, trg\_unico\_username\_chef



\* \[✓] Normalizzazione dei dati utente (username, codice fiscale, nome, cognome, email)

&nbsp; • Funzione: fun\_normalizza\_utente

&nbsp; • Trigger: trg\_normalizza\_chef, trg\_normalizza\_partecipante



\* \[✓] Blocco aggiornamento del Codice Fiscale

&nbsp; • Funzione: fun\_blocca\_aggiorna\_utente

&nbsp; • Trigger: trg\_blocca\_aggiorna\_\_chef, trg\_blocca\_aggiorna\_partecipante



\## RICETTA



\* \[✓] Normalizzazione nome ricetta (iniziale maiuscola)

&nbsp; • Funzione: fun\_normalizza\_ingr\_chef

&nbsp; • Trigger: trg\_normalizza\_ricetta



\* \[✓] Blocco aggiornamento IdRicetta e IdChef

&nbsp; • Funzione: fun\_blocca\_aggiorna\_ricetta

&nbsp; • Trigger: trg\_blocca\_aggiorna\_ricetta



\## INGREDIENTE



\* \[✓] Normalizzazione nome ingrediente

&nbsp; • Funzione: fun\_normalizza\_ingr\_chef

&nbsp; • Trigger: trg\_normalizza\_ingrediente



\* \[✓] Blocco su UPDATE/DELETE di IdIngrediente, Nome, Origine

&nbsp; • Funzione: fun\_blocca\_aggiorna\_ingrediente

&nbsp; • Trigger: trg\_blocca\_aggiorna\_ingrediente



\## CORSO



\* \[✓] Blocco aggiornamento IdCorso, Nome, NumeroSessioni, FrequenzaSessioni, Costo, IdChef

&nbsp; • Funzione: fun\_blocca\_aggiorna\_corso

&nbsp; • Trigger: trg\_blocca\_aggiorna\_corso



\* \[✓] Incremento/Decremento numero corsi

&nbsp; • Funzioni: fun\_incrementa\_num\_corsi, fun\_decrementa\_num\_corsi

&nbsp; • Trigger: trg\_incrementa\_num\_corsi, trg\_decrementa\_num\_corsi



\## ARGOMENTO



\* \[✓] Blocco modifica o eliminazione argomento

&nbsp; • Funzione: fun\_blocca\_aggiorna\_argomento

&nbsp; • Trigger: trg\_blocca\_aggiorna\_argomento



\## SESSIONI (Online/Pratiche)



\* \[✓] Blocco aggiornamento IdSessione e IdCorso

&nbsp; • Funzione: fun\_blocca\_aggiorna\_sessioni

&nbsp; • Trigger: trg\_blocca\_aggiorna\_sessioni\_online, trg\_blocca\_aggiorna\_sessioni\_pratiche



\* \[✓] Unicità sessioni per corso nello stesso giorno

&nbsp; • Funzione: fun\_unicita\_sessione\_giorno

&nbsp; • Trigger: trg\_unicita\_sessione\_online\_giorno, trg\_unicita\_sessione\_pratica\_giorno



\* \[✓] Data sessione dopo inizio corso (o uguale alla prima)

&nbsp; • Funzione: fun\_sessione\_dopo\_inizio\_corso

&nbsp; • Trigger: trg\_sessione\_online\_dopo\_inizio\_corso, trg\_sessione\_pratica\_dopo\_inizio\_corso



\* \[✓] Numero massimo di sessioni rispettato

&nbsp; • Funzione: fun\_max\_sessioni\_per\_corso

&nbsp; • Trigger: trg\_max\_sessioni\_online, trg\_max\_sessioni\_pratica



\* \[✓] Frequenza sessioni rispettata (giorni tra sessioni)

&nbsp; • Funzione: fun\_verifica\_frequenza\_sessioni

&nbsp; • Trigger: trg\_verifica\_frequenza\_sessioni\_online, trg\_verifica\_frequenza\_sessioni\_pratiche



\* \[✓] Blocco inserimento sessione pratica se corso non pratico

&nbsp; • Funzione: fun\_ispratico\_insert

&nbsp; • Trigger: trg\_ispratico\_insert



\## ISCRIZIONI



\* \[✓] Blocco aggiornamento Iscrizioni

&nbsp; • Funzione: fun\_blocca\_aggiorna\_iscrizioni

&nbsp; • Trigger: trg\_blocca\_aggiorna\_iscrizioni



\* \[✓] Vincolo limite iscrizioni corso

&nbsp; • Funzione: fun\_limite\_iscrizioni

&nbsp; • Trigger: trg\_limite\_iscrizioni



\## ADESIONI



\* \[✓] Incremento/Decremento numero partecipanti

&nbsp; • Funzioni: fun\_incrementa\_num\_utenti, fun\_decrementa\_num\_utenti

&nbsp; • Trigger: trg\_incremento\_numutenti, trg\_decrementa\_num\_utenti



\* \[✓] Data adesione antecedente alla sessione pratica

&nbsp; • Funzione: fun\_data\_adesione

&nbsp; • Trigger: trg\_data\_adesione



\* \[✓] Partecipante deve essere iscritto al corso

&nbsp; • Funzione: fun\_iscrizione\_before\_adesione

&nbsp; • Trigger: trg\_iscrizione\_before\_adesione



\* \[✓] Blocco aggiornamento Adesioni

&nbsp; • Funzione: fun\_blocca\_aggiorna\_adesioni

&nbsp; • Trigger: trg\_blocca\_aggiorna\_adesioni



\* \[✓] Blocco cancellazione adesione se meno di 3 giorni dalla sessione

&nbsp; • Funzione: fun\_blocca\_cancella\_adesioni

&nbsp; • Trigger: trg\_blocca\_cancella\_adesioni



\## ARGOMENTI\_CORSO



\* \[✓] Max 5 argomenti per corso

&nbsp; • Funzione: fun\_limit\_argomenti

&nbsp; • Trigger: trg\_limit\_argomenti



\* \[✓] Blocco aggiornamento Argomenti\_Corso

&nbsp; • Funzione: fun\_blocca\_aggiorna\_argomenticorso

&nbsp; • Trigger: trg\_blocca\_aggiorna\_argomenticorso



\## PREPARAZIONI



\* \[✓] Lo chef può usare solo ricette sue

&nbsp; • Funzione: fun\_ricette\_chef\_sessione

&nbsp; • Trigger: trg\_ricette\_chef\_sessione



\* \[✓] Blocco aggiornamento Preparazioni

&nbsp; • Funzione: fun\_blocca\_aggiorna\_preparazioni

&nbsp; • Trigger: trg\_blocca\_aggiorna\_preparazioni



\## UTILIZZI



\* \[✓] Blocco aggiornamento Utilizzi

&nbsp; • Funzione: fun\_blocca\_aggiorna\_utilizzi

&nbsp; • Trigger: trg\_blocca\_aggiorna\_utilizzi


### ***TRIGGER:***





**CHEF:**



**PARTECIPANTE:**



**RICETTA:**

* Della ricetta non è possibile aggiornare IdRicetta e IdChef



**INGREDIENTE:**

* Non si può modificare o eliminare un ingrediente
* Degli Ingredienti non deve essere possibile aggiornare IdIngrediente, Nome, Origine



**CORSO:**

* Del corso non  è possibile aggiornare IdCorso, Nome, FrequenzaSessioni, Costo, IdChef



**ARGOMENTO:**

* Non si può modificare o eliminare un argomento



**SESSIONE ONLINE:**



**SESSIONE PRATICA:**

* Non posso inserire una sessione pratica se ispratico = false



**ISCRIZIONI:**



* Trigger aggiornamento numero corsi(increment)
* Trigger aggiornamento numero corsi(decrement)
* Interrelazionale: Se viene inserita una partecipazione ma il limite di iscrizioni è già raggiunto, essa non viene inserita
* Non deve essere possibile aggiornare le  iscrizioni



**ARGOMENTI\_CORSO:**

* BUSINESS IntraRelazionale: Gli argomenti del corso non possono essere più di 5
* Non deve essere possibile aggiornare gli argomenti\_corso



**ADESIONI:**

* Trigger aggiornamento numero utenti (increment)
* Trigger aggiornamento numero utenti (decrement)
* Interrelazionale: La data dell'adesione alla sessione pratica deve essere antecedente alla data della sessione pratica/ Se la sessione pratica è già avvenuta, l'utente non puo' più aderire
* Interrelazionale: Un utente non puo' partecipare a una sessione pratica se non iscritto al corso che la organizza
* Non deve essere possibile aggiornare le adesioni



**PREPARAZIONI:**

* Interrelazionale: Lo chef non può usare ricette nelle sessioni pratiche che non sono sue
* Non deve essere possibile aggiornare le preparazioni



**UTILIZZI:**

* Non deve essere possibile aggiornare gli utilizzi



**CHEF/PARTECIPANTE:**

* Interrelazionale: Impedire che due utenti – uno Chef e uno Partecipante – abbiano lo stesso Username, anche se sono in due tabelle diverse.
* Il Codice Fiscale non è modificabile una volta creato l'utente



**SESSIONE ONLINE/SESSIONE PRATICA:**

* Interrelazionale: Non ci possono essere più sessioni per lo stesso corso nello stesso giorno
* Aggiornamento numero sessioni (increment)
* Aggiornamento numero sessioni (decrement)
* L'inserimento di una sessione pratica o online (se la data è maggiore della data odierna) aggiorna la data di inizio del corso (se è minore della data del corso oppure non c'è ancora una data di inizio), l'aggiornamento di sessioni già avvenute non deve essere possibile
* Delle sessioni non è possibile aggiornare IdSessione(pratica,online) e IdCorso

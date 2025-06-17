package UninaFoodLab.DTO;

import java.util.ArrayList;
import java.util.Date;

// visualizzareport
public class Chef extends Utente
{
    private String curriculum;
    private ArrayList<Ricetta> ricette = new ArrayList<Ricetta>();
    private ArrayList<Corso> corsi = new ArrayList<Corso>();

    public Chef(String username, String nome, String cognome, String codiceFiscale, Date dataDiNascita, String luogoDiNascita, String email, String password, String curriculum)
    {
        super(username, nome, cognome, codiceFiscale, dataDiNascita, luogoDiNascita, email, password);
        this.curriculum = curriculum;
    }

    public String getCurriculum()
    {
        return curriculum;
    }

    public void setCurriculum(String curriculum)
    {
        this.curriculum = curriculum;
    }

    public void aggiungiRicetta(Ricetta toAddRicetta)
    {
        ricette.add(toAddRicetta);
    }

    public void aggiungiCorso(Corso toAddCorso)
    {
        corsi.add(toAddCorso);
    }
}

package UninaFoodLab.DTO;

import java.util.ArrayList;
import java.time.LocalDate;

public class Partecipante extends Utente
{
    private int numeroCorsi;
    private ArrayList<Corso> corsi;
    private ArrayList<SessionePratica> sessioniPratiche;

    public Partecipante(String username, String nome, String cognome, String codiceFiscale, LocalDate dataDiNascita, String luogoDiNascita, String email, String password, ArrayList<Corso> corsi, ArrayList<SessionePratica> sessioniPratiche)
    {
        super(username, nome, cognome, codiceFiscale, dataDiNascita, luogoDiNascita, email, password);

        if(corsi != null)
        {
            this.corsi = corsi;
            this.numeroCorsi = corsi.size();
        }
        else
        {
            this.corsi = new ArrayList<>();
            this.numeroCorsi = 0;
        }

        this.sessioniPratiche = (sessioniPratiche != null) ? sessioniPratiche : new ArrayList<>();
    }

    public int getNumeroCorsi()
    {
        return numeroCorsi;
    }

    public ArrayList<Corso> getCorsi()
    {
        return corsi;
    }

    public void aggiungiCorso(Corso toAddCorso)
    {
        corsi.add(toAddCorso);
        ++numeroCorsi;
    }

    public ArrayList<SessionePratica> getSessioniPratiche()
    {
        return sessioniPratiche;
    }

    public void aggiungiSessionePratica(SessionePratica toAddSessionePratica)
    {
        sessioniPratiche.add(toAddSessionePratica);
    }
}
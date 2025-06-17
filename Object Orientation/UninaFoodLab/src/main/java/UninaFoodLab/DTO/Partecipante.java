package UninaFoodLab.DTO;

import java.util.Date;

public class Partecipante extends Utente
{
    private int numeroCorsi = 0;

    public Partecipante(String username, String nome, String cognome, String codiceFiscale, Date dataDiNascita, String luogoDiNascita, String email, String password)
    {
        super(username, nome, cognome, codiceFiscale, dataDiNascita, luogoDiNascita, email, password);
    }

    // Costruttore usato maggiormente per quando prendiamo i dati dal database
    public Partecipante(String username, String nome, String cognome, String codiceFiscale, Date dataDiNascita, String luogoDiNascita, String email, String password, int numeroCorsi)
    {
        super(username, nome, cognome, codiceFiscale, dataDiNascita, luogoDiNascita, email, password);
        this.numeroCorsi = numeroCorsi;
    }

    public int getNumeroCorsi()
    {
        return numeroCorsi;
    }

    public void setNumeroCorsi(int numeroCorsi)
    {
        this.numeroCorsi = numeroCorsi;
    }
}

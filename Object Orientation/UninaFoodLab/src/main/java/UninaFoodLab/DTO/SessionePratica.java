package UninaFoodLab.DTO;

import java.sql.Time;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Objects;

//associaricetta
public class SessionePratica extends Sessione
{
    private int numeroUtenti;
    private String indirizzo;
    ArrayList<Partecipante> utenti;
    ArrayList<Ricetta> ricette;
    ArrayList<Adesione> adesioni;

    //HEYYYYY SONO QUIIII
    public SessionePratica (int durata, Time orario, LocalDate data, String indirizzo)
    {
        super(durata, orario, data);
        this.utenti = new ArrayList<Partecipante>(0);
        this.indirizzo = indirizzo;
        this.numeroUtenti = 0;
        this.ricette = new ArrayList<Ricetta>(0);
        this
    }

    public SessionePratica (int durata, Time orario, LocalDate data, ArrayList<Partecipante> utenti, String indirizzo, ArrayList<Ricetta> ricette)
    {
        super(durata, orario, data);
        this.indirizzo = indirizzo;
        this.utenti = utenti;
        this.numeroUtenti = utenti.size();
        this.ricette = ricette;
    }

    public SessionePratica (int durata, Time orario, LocalDate data, ArrayList<Partecipante> utenti, String indirizzo)
    {
        super(durata, orario, data);
        this.indirizzo = indirizzo;
        this.ricette = new ArrayList<Ricetta>(0);
        this.utenti = utenti;
        this.numeroUtenti = utenti.size();
    }

    public SessionePratica (int durata, Time orario, LocalDate data, String indirizzo, ArrayList<Ricetta> ricette)
    {
        super(durata, orario, data);
        this.utenti = new ArrayList<Partecipante>(0);
        this.indirizzo = indirizzo;
        this.numeroUtenti = 0;
        this.ricette = ricette;
    }
    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        SessionePratica s = (SessionePratica) o;
        return getDurata() == s.getDurata() && getOrario().equals(s.getOrario()) && getData().equals(s.getData()) && numeroUtenti == s.numeroUtenti && indirizzo.equals(s.indirizzo) && utenti.equals(s.utenti) && ricette.equals(s.ricette);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getDurata(), getOrario(), getData(), numeroUtenti, indirizzo, utenti, ricette);
    }

    public void addPartecipante (Partecipante toAddPartecipante)
    {
        utenti.add(toAddPartecipante);
        numeroUtenti++;
    }

    public void addRicetta (Ricetta toAddRicetta)
    {
        ricette.add(toAddRicetta);
    }

    public void addAdesione (Adesione toAddAdesione)
    {
        adesioni.add(toAddAdesione);
    }

    public int getNumeroUtenti() {
        return numeroUtenti;
    }

    public void setNumeroUtenti(int numeroUtenti) {
        this.numeroUtenti = numeroUtenti;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public ArrayList<Partecipante> getUtenti() {
        return utenti;
    }

    public void setUtenti(ArrayList<Partecipante> utenti) {
        this.utenti = utenti;
    }

    public ArrayList<Ricetta> getRicette() {
        return ricette;
    }

    public int getNumeroRicette()
    {
        return ricette.size();
    }

    public void setRicette(ArrayList<Ricetta> ricette) {
        this.ricette = ricette;
    }

}

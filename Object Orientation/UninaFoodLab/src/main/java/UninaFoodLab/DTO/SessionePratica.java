package UninaFoodLab.DTO;

import java.sql.Time;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Objects;

//associaricetta
public class SessionePratica extends Sessione
{
    private int numeroPartecipanti;
    private String indirizzo;
    ArrayList<Partecipante> partecipanti;
    ArrayList<Ricetta> ricette;
    ArrayList<Adesione> adesioni;

    public SessionePratica(int durata, Time orario, LocalDate data, String indirizzo, ArrayList<Partecipante> partecipanti, ArrayList<Ricetta> ricette, ArrayList<Adesione> adesioni)
    {
        super(durata, orario, data);
        this.indirizzo = indirizzo;

        if(partecipanti != null)
        {
            this.partecipanti = partecipanti;
            this.numeroPartecipanti = partecipanti.size();
        }
        else
        {
            this.partecipanti = new ArrayList<>();
            this.numeroPartecipanti = 0;
        }

        this.ricette = ricette;
        this.adesioni = (adesioni != null) ? adesioni : new ArrayList<>();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
            return false;

        SessionePratica s = (SessionePratica) o;
        return getDurata() == s.getDurata() && getOrario().equals(s.getOrario()) && getData().equals(s.getData()) && numeroPartecipanti == s.numeroPartecipanti && indirizzo.equals(s.indirizzo) && partecipanti.equals(s.partecipanti) && ricette.equals(s.ricette);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getDurata(), getOrario(), getData(), numeroPartecipanti, indirizzo, partecipanti, ricette);
    }

    public int getNumeroPartecipanti()
    {
        return numeroPartecipanti;
    }

    public String getIndirizzo()
    {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo)
    {
        this.indirizzo = indirizzo;
    }

    public ArrayList<Partecipante> getPartecipanti()
    {
        return partecipanti;
    }

    public void addPartecipante (Partecipante toAddPartecipante)
    {
        partecipanti.add(toAddPartecipante);
        numeroPartecipanti++;
    }

    public ArrayList<Ricetta> getRicette()
    {
        return ricette;
    }

    public void addRicetta (Ricetta toAddRicetta)
    {
        ricette.add(toAddRicetta);
    }

    public int getNumeroRicette()
    {
        return ricette.size();
    }

    public ArrayList<Adesione> getAdesioni()
    {
        return adesioni;
    }

    public void addAdesione (Adesione toAddAdesione)
    {
        adesioni.add(toAddAdesione);
    }
}

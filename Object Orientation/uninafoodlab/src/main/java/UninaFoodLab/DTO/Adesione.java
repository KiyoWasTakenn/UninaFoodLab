package UninaFoodLab.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class Adesione
{
    private int id;
    private LocalDate dataAdesione;
    Partecipante partecipante;
    SessionePratica sessione;

    public Adesione(LocalDate dataAdesione, Partecipante partecipante, SessionePratica sessione)
    {
        this.dataAdesione = dataAdesione;
        this.partecipante = partecipante;
        this.sessione = sessione;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
            return false;
        
        Adesione ad = (Adesione) o;
        return dataAdesione.equals(ad.dataAdesione) && partecipante.equals(ad.partecipante) && sessione.equals(ad.sessione);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(dataAdesione, partecipante, sessione);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalDate getDataAdesione() {
        return dataAdesione;
    }

    public Partecipante getPartecipante() {
        return partecipante;
    }

    public SessionePratica getSessione() {
        return sessione;
    }

}
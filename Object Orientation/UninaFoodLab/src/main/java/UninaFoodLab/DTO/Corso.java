package UninaFoodLab.DTO;

import java.util.ArrayList;
import java.time.LocalDate;

public class Corso
{
    private String nome;
    private LocalDate dataInizio;
    private int numeroSessioni;
    private String frequenzaSessioni;
    private int limite;
    private String descrizione;
    private double costo;
    private boolean isPratico;
    private ArrayList<Sessione> sessioni;
    private ArrayList<Argomento> argomenti;

    public Corso(String nome, LocalDate data, String frequenzaSessioni, int limite, String descrizione, double costo, boolean isPratico, ArrayList<Sessione> sessioni, ArrayList<Argomento> argomenti)
    {
        this.nome = nome;
        this.dataInizio = data;
        this.frequenzaSessioni = frequenzaSessioni;
        this.limite = limite;
        this.descrizione = descrizione;
        this.costo = costo;
        this.isPratico = isPratico;

        if(sessioni != null)
        {
            this.sessioni = sessioni;
            this.numeroSessioni = sessioni.size();
        }
        else
        {
            this.sessioni = new ArrayList<>();
            this.numeroSessioni = 0;
        }

        this.argomenti = (argomenti != null) ? argomenti : new ArrayList<>();
    }

    public String getNome()
    {
        return nome;
    }

    public LocalDate getDataInizio()
    {
        return dataInizio;
    }

    public int getNumeroSessioni()
    {
        return numeroSessioni;
    }

    public String getFrequenzaSessioni()
    {
        return frequenzaSessioni;
    }

    public int getLimite()
    {
        return limite;
    }

    public void setLimite(int limite)
    {
        this.limite = limite;
    }

    public String getDescrizione()
    {
        return descrizione;
    }

    public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }

    public double getCosto()
    {
        return costo;
    }

    public boolean getIsPratico()
    {
        return isPratico;
    }

    public void setIsPratico(boolean isPratico)
    {
        this.isPratico = isPratico;
    }

    public int getReport()
    {
        int numSessionePratica=0;
        int numSessioneOnline=0;

        for (Sessione x: sessioni)
        {
            if(x.getData().isAfter(LocalDate.now().minusDays(30)) && x.getData().isBefore(LocalDate.now()))
                if(x instanceof SessionePratica)
                {
                    ++numSessionePratica;

                }
                else
                    ++numSessioneOnline;
        }
        return numSessionePratica;
    }

    public ArrayList<Sessione> getSessioni()
    {
        return sessioni;
    }

    public ArrayList<Argomento> getArgomenti()
    {
        return argomenti;
    }

    public void aggiungiSessione(Sessione toAddSessione)
    {
        sessioni.add(toAddSessione);
    }

    public void aggiungiArgomento(Argomento toAddArgomento)
    {
        argomenti.add(toAddArgomento);
    }
}
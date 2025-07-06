package UninaFoodLab.DTO;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import UninaFoodLab.Exceptions.RequiredArgomentoException;
import UninaFoodLab.Exceptions.RequiredChefException;

import java.time.LocalDate;

public class Corso
{
    private int id;
    private String nome;
    private LocalDate dataInizio;
    private int numeroSessioni;
    private String frequenzaSessioni;
    private int limite;
    private String descrizione;
    private double costo;
    private boolean isPratico;
    private Chef chef; 
    private ArrayList<Sessione> sessioni;
    private ArrayList<Argomento> argomenti;
    
    // Questi costruttori vengono chiamati quando dalla GUI viene aggiunta una sessione pratica/online
    public Corso(String nome, LocalDate data, String frequenzaSessioni, int limite, String descrizione, double costo, boolean isPratico, Chef chef,
    		     ArrayList<Argomento> argomenti, int durata, Time orario, LocalDate dataSessione, Corso corso, String linkRiunione)
    {
        this.nome = nome;
        this.dataInizio = data;
        this.frequenzaSessioni = frequenzaSessioni;
        this.limite = limite;
        this.descrizione = descrizione;
        this.costo = costo;
        this.isPratico = isPratico;
        
        if(chef == null) throw new RequiredChefException();
        this.chef = chef;
        
        this.sessioni.add( new SessioneOnline(durata, orario, dataSessione, corso, linkRiunione) );
        this.numeroSessioni = sessioni.size();
        
        if(argomenti == null || argomenti.isEmpty()) throw new RequiredArgomentoException();	
        this.argomenti = argomenti;  
    }

    public Corso(String nome, LocalDate data, String frequenzaSessioni, int limite, String descrizione, double costo, boolean isPratico, Chef chef, 
    			 ArrayList<Argomento> argomenti, int durata, Time orario, LocalDate dataSessione, Corso corso, String indirizzo, ArrayList<Ricetta> ricette)
    {
        this.nome = nome;
        this.dataInizio = data;
        this.frequenzaSessioni = frequenzaSessioni;
        this.limite = limite;
        this.descrizione = descrizione;
        this.costo = costo;
        this.isPratico = isPratico;
        
        if(chef == null) throw new RequiredChefException();
        this.chef = chef;
        
        this.sessioni.add( new SessionePratica(durata, orario, dataSessione, corso, indirizzo, ricette) );
        this.numeroSessioni = sessioni.size();
        
        if(argomenti == null || argomenti.isEmpty()) throw new RequiredArgomentoException();	
        this.argomenti = argomenti;  
    }
    
    public int getId()  
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public Date getDataInizio()
    {
        return Date.valueOf(dataInizio);
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
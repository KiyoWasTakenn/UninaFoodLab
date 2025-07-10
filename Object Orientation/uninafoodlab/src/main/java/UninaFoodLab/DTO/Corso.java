package UninaFoodLab.DTO;

import java.math.BigDecimal;
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
    private FrequenzaSessioni frequenzaSessioni;
    private int limite;
    private String descrizione;
    private BigDecimal costo;
    private boolean isPratico;
    private Chef chef; 
    private ArrayList<Sessione> sessioni;
    private ArrayList<Argomento> argomenti;
    
    
    public Corso(String nome, LocalDate dataInizio, int numeroSessioni, FrequenzaSessioni frequenzaSessioni, int limite, String descrizione, BigDecimal costo, boolean isPratico, Chef chef,
    		     ArrayList<Argomento> argomenti, ArrayList<Sessione> sessioniIniziali)
    {
        this.nome = nome;
        this.dataInizio = dataInizio;
        this.numeroSessioni = numeroSessioni;
        this.frequenzaSessioni = frequenzaSessioni;
        this.limite = limite;
        this.descrizione = descrizione;
        this.costo = costo;
        this.isPratico = isPratico;
        
        if(chef == null) throw new RequiredChefException();
        this.setChef(chef);
        
        this.sessioni;
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

    public FrequenzaSessioni getFrequenzaSessioni()
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

    public BigDecimal getCosto()
    {
        return costo;
    }

    public boolean getIsPratico()
    {
        return isPratico;
    }

    public Chef getChef()
	{
		return chef;
	}

	public void setChef(Chef chef)
	{
		this.chef = chef;
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
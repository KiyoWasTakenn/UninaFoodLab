package UninaFoodLab.DTO;

import java.util.ArrayList;
import java.time.LocalDate;

// aggiungisessione
public class Corso
{
    private String nome;
    private LocalDate data;
    private int numeroSessioni;
    private String frequenzaSessioni;
    private int limite;
    private String descrizione;
    private double costo;
    private boolean isPratico;
    private ArrayList<Sessione> sessioni;
    private ArrayList<Argomento> argomenti;

    public Corso( String nome, LocalDate data, String frequenzaSessioni, int limite, String descrizione, double costo, boolean isPratico)
    {
        this.nome = nome;
        this.data = data;
        this.frequenzaSessioni = frequenzaSessioni;
        this.limite = limite;
        this.descrizione = descrizione;
        this.costo = costo;
        this.isPratico = isPratico;
        this.sessioni  = new ArrayList<Sessione>(0);
        this.numeroSessioni = 0;
        this.argomenti = new ArrayList<Argomento>(0);
    }

    public Corso( String nome, LocalDate data, String frequenzaSessioni, int limite, String descrizione, double costo, boolean isPratico, ArrayList<Sessione> sessioni)
    {
        this.nome = nome;
        this.data = data;
        this.frequenzaSessioni = frequenzaSessioni;
        this.limite = limite;
        this.descrizione = descrizione;
        this.costo = costo;
        this.isPratico = isPratico;
        this.sessioni = sessioni;
        this.numeroSessioni = sessioni.size();
        this.argomenti = new ArrayList<Argomento>(0);
    }

    public Corso( String nome, LocalDate data, String frequenzaSessioni, int limite, String descrizione, double costo, boolean isPratico, ArrayList<Argomento> argomenti)
    {
        this.nome = nome;
        this.data = data;
        this.frequenzaSessioni = frequenzaSessioni;
        this.limite = limite;
        this.descrizione = descrizione;
        this.costo = costo;
        this.isPratico = isPratico;
        this.sessioni  = new ArrayList<Sessione>(0);
        this.numeroSessioni = 0;
        this.argomenti = argomenti;
    }

    public Corso( String nome, LocalDate data, String frequenzaSessioni, int limite, String descrizione, double costo, boolean isPratico, ArrayList<Sessione> sessioni, ArrayList<Argomento> argomenti)
    {
        this.nome = nome;
        this.data = data;
        this.frequenzaSessioni = frequenzaSessioni;
        this.limite = limite;
        this.descrizione = descrizione;
        this.costo = costo;
        this.isPratico = isPratico;
        this.sessioni = sessioni;
        this.numeroSessioni = sessioni.size();
        this.argomenti = argomenti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public int getNumeroSessioni() {
        return numeroSessioni;
    }

    public int getLimite() {
        return limite;
    }

    public String getFrequenzaSessioni() {
        return frequenzaSessioni;
    }

    public void setFrequenzaSessioni(String frequenzaSessioni) {
        this.frequenzaSessioni = frequenzaSessioni;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getCosto() {
        return costo;
    }

    public boolean isPratico() {
        return isPratico;
    }

    public void setPratico(boolean pratico) {
        isPratico = pratico;
    }

    public int getNumeroSessionePratica(){
        int numSessionePratica=0;

        for (Sessione x: sessioni)
        {
            if(x instanceof SessionePratica)
                if(x.getData().isAfter(LocalDate.now().minusDays(30)) && x.getData().isBefore(LocalDate.now()))
                    numSessionePratica ++;
        }
        return numSessionePratica;
    }

    public int getNumeroSessioneOnline(){
        int numSessioneOnline=0;

        for (Sessione x: sessioni)
        {
            if(x instanceof SessioneOnline)
                if(x.getData().isAfter(LocalDate.now().minusDays(30)) && x.getData().isBefore(LocalDate.now()))
                    numSessioneOnline ++;
        }
        return numSessioneOnline;
    }

    public void AggiungiSessione( Sessione toAddSessione)
    {
        sessioni.add(toAddSessione);
    }

    public void AggiungiArgomento(Argomento toAddArgomento)
    {
        argomenti.add(toAddArgomento);
    }

}

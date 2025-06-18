package UninaFoodLab.DTO;

import java.util.Objects;
import java.time.LocalDate;

public abstract class Utente
{
    private String username;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private LocalDate dataDiNascita;
    private String luogoDiNascita;
    private String email;
    private String password;

    protected Utente(String username, String nome, String cognome, String codiceFiscale, LocalDate dataDiNascita, String luogoDiNascita, String email, String password)
    {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita;
        this.luogoDiNascita = luogoDiNascita;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null || getClass() != o.getClass())
            return false;

        return username.equals(((Utente) o).username);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(username);
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCognome()
    {
        return cognome;
    }

    public void setCognome(String cognome)
    {
        this.cognome = cognome;
    }

    public String getCodiceFiscale()
    {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale)
    {
        this.codiceFiscale = codiceFiscale;
    }

    public LocalDate getDataDiNascita()
    {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita)
    {
        this.dataDiNascita = dataDiNascita;
    }

    public String getLuogoDiNascita()
    {
        return luogoDiNascita;
    }

    public void setLuogoDiNascita(String luogoDiNascita)
    {
        this.luogoDiNascita = luogoDiNascita;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
package UninaFoodLab.DTO;

import java.util.Date;

public abstract class Utente
{
    private String username;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private Date dataDiNascita;
    private String luogoDiNascita;
    private String email;
    private String password;

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

    public Date getDataDiNascita()
    {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita)
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
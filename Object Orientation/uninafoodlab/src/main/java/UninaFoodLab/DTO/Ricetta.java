package UninaFoodLab.DTO;

import java.util.ArrayList;
import java.util.Objects;

import UninaFoodLab.Exceptions.RequiredChefException;
import UninaFoodLab.Exceptions.RequiredIngredienteException;

public class Ricetta
{
    private int id;
    private String nome;
    private String provenienza;
    private int tempo;
    private int calorie;
    private LivelloDifficolta difficolta;
    private String allergeni;
    private ArrayList<Utilizzo> utilizzi;

    public Ricetta(String nome, String provenienza, int tempo, int calorie, LivelloDifficolta difficolta, String allergeni, Chef chef, ArrayList<Utilizzo> utilizzi)
    {
    	if(chef == null) throw new RequiredChefException();
    	if(utilizzi == null) throw new RequiredIngredienteException();
    	
        this.nome = nome;
        this.provenienza = provenienza;
        this.tempo = tempo;
        this.calorie = calorie;
        this.difficolta = difficolta;
        this.allergeni = allergeni;
        this.utilizzi = utilizzi;
    }

	@Override
    public String toString()
    {
        return nome;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
            return false;

        Ricetta ricetta = (Ricetta) o;
        return tempo == ricetta.tempo && calorie == ricetta.calorie && nome.equals(ricetta.nome) && provenienza.equals(ricetta.provenienza) && difficolta == ricetta.difficolta;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(nome, provenienza, tempo, calorie, difficolta, allergeni);
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

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getProvenienza()
    {
        return provenienza;
    }

    public void setProvenienza(String provenienza)
    {
        this.provenienza = provenienza;
    }

    public int getTempo()
    {
        return tempo;
    }

    public void setTempo(int tempo)
    {
        this.tempo = tempo;
    }

    public int getCalorie()
    {
        return calorie;
    }

    public void setCalorie(int calorie)
    {
        this.calorie = calorie;
    }

    public LivelloDifficolta getDifficolta()
    {
        return difficolta;
    }

    public void setDifficolta(LivelloDifficolta difficolta)
    {
        this.difficolta = difficolta;
    }

    public String getAllergeni()
    {
        return allergeni;
    }

    public void setAllergeni(String allergeni)
    {
        this.allergeni = allergeni;
    }

    public ArrayList<Utilizzo> getUtilizzi()
    {
        return utilizzi;
    }
}
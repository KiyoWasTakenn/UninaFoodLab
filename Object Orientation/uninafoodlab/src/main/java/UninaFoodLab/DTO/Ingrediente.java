package UninaFoodLab.DTO;

import java.util.Objects;

public class Ingrediente
{
    private int id;
    private String nome;
    private NaturaIngrediente origine;

    public Ingrediente(String nome, NaturaIngrediente origine)
    {
        this.nome = nome;
        this.origine = origine;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
            return false;

        Ingrediente ingrediente = (Ingrediente) o;
        return nome.equals(ingrediente.nome) && origine == ingrediente.origine;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(nome, origine);
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

    public NaturaIngrediente getOrigine()
    {
        return origine;
    }

}
package UninaFoodLab.DTO;

import java.util.Objects;

public class Utilizzo
{
    private int id;
    private double quantita;
    private UnitaDiMisura udm;
    private Ingrediente ingrediente;

    public Utilizzo(Double quantita, UnitaDiMisura udm, Ingrediente ingrediente)
    {
        this.quantita = quantita;
        this.udm = udm;
        this.ingrediente = ingrediente;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
            return false;

        Utilizzo utilizzo = (Utilizzo) o;
        return quantita == utilizzo.quantita && udm == utilizzo.udm && ingrediente == utilizzo.ingrediente;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(quantita, udm, ingrediente);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public double getQuantita()
    {
        return quantita;
    }

    public void setQuantita(double quantita)
    {
        this.quantita = quantita;
    }

    public UnitaDiMisura getUdm()
    {
        return udm;
    }

    public void setUdm(UnitaDiMisura udm)
    {
        this.udm = udm;
    }

    public Ingrediente getIngrediente()
    {
        return ingrediente;
    }

}
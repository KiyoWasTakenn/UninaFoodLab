package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Ingrediente;

import java.util.List;

public interface IngredienteDAO
{
    void save(Ingrediente toSaveIngrediente);
	public Ingrediente getIngredienteById(int idIngrediente);
    List<Ingrediente> getIngredientiByIdRicetta(int idRicetta);
}

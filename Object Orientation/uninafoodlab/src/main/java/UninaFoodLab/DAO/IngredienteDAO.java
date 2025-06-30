package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Ingrediente;

import java.sql.*;
import java.util.List;

public interface IngredienteDAO
{
    List<Ingrediente> getIngredientiByIdRicetta(int idIngrediente) throws SQLException;
    void save(Ingrediente toSaveIngrediente) throws SQLException;
}

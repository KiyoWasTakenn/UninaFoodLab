package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Utilizzo;

import java.sql.*;
import java.util.List;

public interface UtilizzoDAO
{
    List<Utilizzo> getUtilizziByIdRicetta(int idRicetta) throws SQLException;
    void save(Utilizzo toSaveUtilizzo) throws SQLException;
    void update(Utilizzo previousUtilizzo, Utilizzo updatedUtilizzo) throws SQLException;
    void delete(int idRicetta, int idIngrediente) throws SQLException;
}

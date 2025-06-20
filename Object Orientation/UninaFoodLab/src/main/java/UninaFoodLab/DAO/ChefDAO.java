package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Chef;

import java.util.List;
import java.sql.*;

public interface ChefDAO
{
    Chef getChefById(int idChef) throws SQLException;
    Chef getChefByUsername(String username) throws SQLException;
    List<Chef> getChefByName(String name, String surname) throws SQLException;
    List<Chef> getAllChefs() throws SQLException;
    void save(Chef toSaveChef) throws SQLException;
    void update(Chef previousChef, Chef updatedChef) throws SQLException;
    void delete(int idChef) throws SQLException;
}

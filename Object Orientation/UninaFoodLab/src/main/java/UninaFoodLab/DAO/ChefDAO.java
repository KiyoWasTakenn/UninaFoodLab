package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Chef;

import java.util.List;
import java.sql.*;

public interface ChefDAO
{
    Chef getChefById(int id) throws SQLException;
    List<Chef> getChefByName(String name, String surname) throws SQLException;
    List<Chef> getAllChefs() throws SQLException;
    void save(Chef toSaveChef) throws SQLException;
    void update(int id, String username) throws SQLException;

    void delete(int id) throws SQLException;
}

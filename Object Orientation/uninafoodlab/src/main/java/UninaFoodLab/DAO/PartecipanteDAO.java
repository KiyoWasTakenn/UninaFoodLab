package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Partecipante;

import java.sql.*;

public interface PartecipanteDAO
{
    Partecipante getPartecipanteById(int idPartecipante) throws SQLException;
    Partecipante getPartecipanteByUsername(String username) throws SQLException;
    void save(Partecipante toSavePartecipante) throws SQLException;
    void update(Partecipante previousPartecipante, Partecipante updatedPartecipante) throws SQLException;
    void delete(int idPartecipante) throws SQLException;
}

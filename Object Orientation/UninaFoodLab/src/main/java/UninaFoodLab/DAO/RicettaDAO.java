package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Ricetta;

import java.sql.*;
import java.util.List;

public interface RicettaDAO
{
    List<Ricetta> getRicetteByIdChef(int idChef) throws SQLException;
    List<Ricetta> getRicettaByIdSessione(int idSessione) throws SQLException;
    void save(Ricetta toSaveRicetta) throws SQLException;
    void update(Ricetta previousRicetta, Ricetta updatedRicetta) throws SQLException;
    void delete(int idRicetta) throws SQLException;
}

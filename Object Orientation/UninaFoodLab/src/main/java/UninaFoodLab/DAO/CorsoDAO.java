package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Argomento;
import UninaFoodLab.DTO.Corso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface CorsoDAO
{

    Corso getCorsoById(int idCorso) throws SQLException;
    Corso getCorsoByChef(int idChef) throws SQLException;
    List<Corso> getAllCorsi() throws SQLException;
    List<Corso> getCorsiByArgomento(List<Argomento> argomenti) throws SQLException;
    void save(Corso toSaveCorso) throws SQLException;
    void delete(int IdCorso) throws SQLException;
    void update(Corso oldCorso, Corso newCorso) throws SQLException;

}

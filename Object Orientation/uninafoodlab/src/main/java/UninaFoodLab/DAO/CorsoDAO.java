package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Argomento;
import UninaFoodLab.DTO.Corso;

import java.sql.*;
import java.util.List;

public interface CorsoDAO
{
	void save(Corso toSaveCorso);
    Corso getCorsoById(int idCorso);
    List<Corso> getCorsiByChef(int idChef);
    List<Corso> getCorsiByArgomenti(List<Argomento> argomenti);
    List<Corso> getAllCorsi() throws SQLException;
    void update(Corso oldCorso, Corso newCorso);
    void delete(int IdCorso);
}
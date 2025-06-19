package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Argomento;

import java.sql.*;
import java.util.List;

public interface ArgomentoDAO
{
    public List<Argomento> getAllArgomenti() throws SQLException;
}

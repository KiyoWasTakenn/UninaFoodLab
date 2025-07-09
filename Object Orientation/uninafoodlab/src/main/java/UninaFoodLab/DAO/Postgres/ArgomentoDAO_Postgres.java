package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DTO.Argomento;
import UninaFoodLab.Exceptions.DAOException;
import UninaFoodLab.DAO.ArgomentoDAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class ArgomentoDAO_Postgres implements ArgomentoDAO
{
    public List<Argomento> getAllArgomenti()
    {
        List<Argomento> argomenti = new ArrayList<>();
        String sql = "SELECT * FROM Argomento";

        try(Connection conn = ConnectionManager.getConnection(); Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql))
        {
            while(rs.next())
                argomenti.add(new Argomento(rs.getString("Nome")));
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante reperimento Argomenti", e);
        }
        
        return argomenti;
    }
}
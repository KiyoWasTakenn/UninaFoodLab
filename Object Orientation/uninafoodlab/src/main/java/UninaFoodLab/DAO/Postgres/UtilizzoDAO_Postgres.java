package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DAO.UtilizzoDAO;
import UninaFoodLab.DTO.Utilizzo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizzoDAO_Postgres implements UtilizzoDAO
{
    public List<Utilizzo> getUtilizziByIdRicetta(int idRicetta) throws SQLException
    {
        List<Utilizzo> utilizzi = new ArrayList<>();
        String sql = "SELECT * FROM Utilizzi WHERE IdRicetta = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(idRicetta);
            ResultSet rs = s.executeQuery();
            while(rs.next())
                utilizzi.add( new Utilizzo(
                							
                		
                						  );
                        )
                );
        }
        return utilizzi;
    }

    public void save(Utilizzo toSaveUtilizzo) throws SQLException
    {
        
    }

    public void update(Utilizzo previousUtilizzo, Utilizzo updatedUtilizzo) throws SQLException
    {

    }

    public void delete(int idRicetta, int idIngrediente) throws SQLException
    {
        String sql = "DELETE FROM Utilizzi WHERE IdRicetta = ? AND IdIngrediente = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idRicetta);
            s.setInt(2, idIngrediente);
            s.executeUpdate();
        }
    }

}

package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DAO.UtilizzoDAO;
import UninaFoodLab.DTO.Utilizzo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizzoDAO_Postgres implements UtilizzoDAO
{
    private Connection conn;

    public UtilizzoDAO_Postgres(Connection conn)
    {
        this.conn = conn;
    }

    public List<Utilizzo> getUtilizziByIdRicetta(int idRicetta) throws SQLException
    {
        List<Utilizzo> chefs = new ArrayList<>();
        String sql = "SELECT * FROM Utilizzi WHERE Nome = ? AND Cognome = ?";

        try(PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setString(1, name);
            s.setString(2, surname);
            ResultSet rs = s.executeQuery();
            while(rs.next())
                chefs.add( new Chef( rs.getString("Username"),
                                rs.getString("Nome"),
                                rs.getString("Cognome"),
                                rs.getString("CodiceFiscale"),
                                rs.getDate("DataDiNascita").toLocalDate(),
                                rs.getString("LuogoDiNascita"),
                                rs.getString("Email"),
                                rs.getString("Password"),
                                rs.getString("Curriculum"),
                                null,
                                null
                        )
                );
        }
        return chefs;
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

        try(PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idRicetta);
            s.setInt(2, idIngrediente);
            s.executeUpdate();
        }
    }

}

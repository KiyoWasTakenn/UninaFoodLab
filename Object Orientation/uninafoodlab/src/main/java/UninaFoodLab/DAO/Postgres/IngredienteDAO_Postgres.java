package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DAO.IngredienteDAO;
import UninaFoodLab.DTO.Ingrediente;
import UninaFoodLab.DTO.NaturaIngrediente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO_Postgres implements IngredienteDAO
{
    public List<Ingrediente> getIngredientiByIdRicetta(int idRicetta) throws SQLException
    {
        List<Ingrediente> ingredienti = new ArrayList<>();
        String sql = "SELECT * FROM Ingrediente NATURAL JOIN Utilizzi WHERE IdRicetta = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idRicetta);
            ResultSet rs = s.executeQuery();
            while(rs.next())
                ingredienti.add( new Ingrediente( rs.getString("Nome"),
                                 NaturaIngrediente.valueOf(rs.getString("Origine"))
                        )
                );
        }
        return ingredienti;
    }

    public void save(Ingrediente toSaveIngrediente) throws SQLException
    {
        String sql = "INSERT INTO Ingrediente(Nome, Origine) " +
                     "VALUES(?, ?)";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setString(1, toSaveIngrediente.getNome());
            s.setString(2, toSaveIngrediente.getOrigine().toString());
            s.executeUpdate();
        }
    }
}

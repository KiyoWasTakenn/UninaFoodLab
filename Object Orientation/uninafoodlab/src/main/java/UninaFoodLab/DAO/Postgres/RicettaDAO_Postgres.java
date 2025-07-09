package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DAO.RicettaDAO;
import UninaFoodLab.DTO.LivelloDifficolta;
import UninaFoodLab.DTO.Ricetta;
import UninaFoodLab.Exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RicettaDAO_Postgres implements RicettaDAO
{
    public List<Ricetta> getRicetteByIdChef(int idChef)
    {
        List<Ricetta> ricette = new ArrayList<>();
        String sql = "SELECT * FROM Ricetta WHERE IdChef = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idChef);
            ResultSet rs = s.executeQuery();
            while(rs.next())
                ricette.add( new Ricetta( rs.getString("Nome"),
                                          rs.getString("Provenienza"),
                                          rs.getInt("Tempo"),
                                          rs.getInt("Calorie"),
                                          LivelloDifficolta.valueOf(rs.getString("Difficolta")),
                                          null,
                                          rs.getString("Allergeni")
                                        )
                            );
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante getRicetteByIdchef", e);
        }
        
        return ricette;
    }

    public List<Ricetta> getRicettaByIdSessione(int idSessione)
    {
        List<Ricetta> ricette = new ArrayList<>();
        String sql = "SELECT * FROM Ricetta NATURAL JOIN Preparazioni WHERE IdSessionePratica = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idSessione);
            ResultSet rs = s.executeQuery();
            while(rs.next())
                ricette.add( new Ricetta( rs.getString("Nome"),
                                          rs.getString("Provenienza"),
                                          rs.getInt("Tempo"),
                                          rs.getInt("Calorie"),
                                          LivelloDifficolta.valueOf(rs.getString("Difficolta")),
                                          null,
                                          rs.getString("Allergeni")
                                        )
                           );
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante getRicettaByIdSessione", e);
        }
        
        return ricette;
    }

    public void save(Ricetta toSaveRicetta)
    {
        String sql = "INSERT INTO Ricetta(Nome, Provenienza, Tempo, Calorie, Difficolta, Allergeni) " +
                     "VALUES(?, ?, ?, ?, ?, ?)";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setString(1, toSaveRicetta.getNome());
            s.setString(2, toSaveRicetta.getProvenienza());
            s.setInt(3, toSaveRicetta.getTempo());
            s.setInt(4, toSaveRicetta.getCalorie());
            s.setString(5, toSaveRicetta.getDifficolta().toString());
            s.setString(6, toSaveRicetta.getAllergeni());
            s.executeUpdate();
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante salvataggio Ricetta", e);
        }
    }

    public void update(Ricetta previousRicetta, Ricetta updatedRicetta)
    {
        String sql = "UPDATE Ricetta SET";
        List<Object> param = new ArrayList<>();

        if(! (previousRicetta.getNome().equals(updatedRicetta.getNome())) )
        {
            sql += "Nome = ?, ";
            param.add(updatedRicetta.getNome());
        }

        if(! (previousRicetta.getProvenienza().equals(updatedRicetta.getProvenienza())) )
        {
            sql += "Provenienza = ?, ";
            param.add(updatedRicetta.getProvenienza());
        }

        if(previousRicetta.getTempo() != updatedRicetta.getTempo())
        {
            sql += "Tempo = ?, ";
            param.add(updatedRicetta.getTempo());
        }

        if(previousRicetta.getCalorie() != updatedRicetta.getCalorie())
        {
            sql += "Calorie = ?, ";
            param.add(updatedRicetta.getCalorie());
        }

        if(! (previousRicetta.getDifficolta().equals(updatedRicetta.getDifficolta())) )
        {
            sql += "Difficolta = ?, ";
            param.add(updatedRicetta.getDifficolta());
        }

        if(! (previousRicetta.getAllergeni().equals(updatedRicetta.getAllergeni())) )
        {
            sql += "Allergeni = ? ";
            param.add(updatedRicetta.getAllergeni());
        }

        if(!param.isEmpty())
        {
        	if(sql.endsWith(", ")) 
        		sql = sql.substring(0, sql.length() - 2);
        	
            sql += " WHERE IdRicetta = ?";
            param.add(previousRicetta.getId());

            try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
            {
                for(int i = 0; i < param.size(); i++)
                    s.setObject(i + 1, param.get(i));

                s.executeUpdate();
            }
            catch(SQLException e)
            {
            	throw new DAOException("Errore DB durante update Ricetta", e);
            }
        }
    }

    public void delete(int idRicetta)
    {
        String sql = "DELETE FROM Ricetta WHERE IdRicetta = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idRicetta);
            s.executeUpdate();
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante eliminazione Ricetta", e);
        }
    }
}
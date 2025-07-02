package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DTO.Chef;
import UninaFoodLab.Exceptions.ChefNotFoundException;
import UninaFoodLab.Exceptions.DAOException;
import UninaFoodLab.Exceptions.RecordNotFoundException;
import UninaFoodLab.DAO.ChefDAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class ChefDAO_Postgres implements ChefDAO
{
    public Chef getChefById(int idChef)
    {
        String sql = "SELECT * FROM Chef WHERE IdChef = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idChef);
            ResultSet rs = s.executeQuery();

            if(rs.next())
                return new Chef( rs.getString("Username"),
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
                               );
            else
            	throw new ChefNotFoundException("Chef con id " + idChef + " non trovato");
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante ricerca Chef per id", e);
        }
    }

    public Chef getChefByUsername(String username)
    {
        String sql = "SELECT * FROM Chef WHERE Username = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setString(1, username);
            ResultSet rs = s.executeQuery();

            if(rs.next())
                return new Chef( rs.getString("Username"),
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
                );
            else
            	throw new ChefNotFoundException("Chef con username " + username + " non trovato");
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante ricerca Chef per username", e);
        }
    }

    public List<Chef> getChefByName(String name, String surname)
    {
        List<Chef> chefs = new ArrayList<>();
        String sql = "SELECT * FROM Chef WHERE Nome = ? AND Cognome = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
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
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante ricerca Chef per nome", e);
        }
        
        return chefs;
    }

    public List<Chef> getAllChefs()
    {
        List<Chef> chefs = new ArrayList<>();
        String sql = "SELECT * FROM Chef";

        try(Connection conn = ConnectionManager.getConnection(); Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql))
        {
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
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante ricerca di tutti gli Chef", e);
        }
        
        return chefs;
    }

    public void save(Chef toSaveChef)
    {
        String sql = "INSERT INTO Chef(Username, Nome, Cognome, CodiceFiscale, DataDiNascita, Email, Password, Curriculum) " +
                     "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setString(1, toSaveChef.getUsername());
            s.setString(2, toSaveChef.getNome());
            s.setString(3, toSaveChef.getCognome());
            s.setString(4, toSaveChef.getCodiceFiscale());
            s.setDate(5, toSaveChef.getDataDiNascita());
            s.setString(6, toSaveChef.getEmail());
            s.setString(7, toSaveChef.getHashPassword());
            s.setString(8, toSaveChef.getCurriculum());
            s.executeUpdate();
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante salvataggio Chef", e);
        }
    }

    public void update(Chef previousChef, Chef updatedChef)
    {
        String sql = "UPDATE Chef SET";
        List<Object> param = new ArrayList<>();

        if(! (previousChef.getUsername().equals(updatedChef.getUsername())) )
        {
            sql += "Username = ?, ";
            param.add(updatedChef.getUsername());
        }

        if(! (previousChef.getNome().equals(updatedChef.getNome())) )
        {
            sql += "Nome = ?, ";
            param.add(updatedChef.getNome());
        }

        if(! (previousChef.getCognome().equals(updatedChef.getCognome())) )
        {
            sql += "Cognome = ?, ";
            param.add(updatedChef.getCognome());
        }

        if(! (previousChef.getDataDiNascita().equals(updatedChef.getDataDiNascita())) )
        {
            sql += "DataDiNascita = ?, ";
            param.add(updatedChef.getDataDiNascita());
        }

        if(! (previousChef.getEmail().equals(updatedChef.getEmail())) )
        {
            sql += "Email = ?, ";
            param.add(updatedChef.getEmail());
        }

        if(! (previousChef.getHashPassword().equals(updatedChef.getHashPassword())) )
        {
            sql += "Password = ?, ";
            param.add(updatedChef.getHashPassword());
        }

        if(! (previousChef.getCurriculum().equals(updatedChef.getCurriculum())) )
        {
            sql += "Curriculum = ? ";
            param.add(updatedChef.getCurriculum());
        }

        if(!param.isEmpty())
        {
        	if(sql.endsWith(", ")) 
        		sql = sql.substring(0, sql.length() - 2);
        	
            sql += " WHERE IdChef = ?";
            param.add(previousChef.getId());

            try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
            {
                for(int i = 0; i < param.size(); i++)
                    s.setObject(i + 1, param.get(i));

                s.executeUpdate();
            }
            catch(SQLException e)
            {
            	throw new DAOException("Errore DB durante aggiornamento Chef", e);
            }
        }
    }

    public void delete(int idChef)
    {
        String sql = "DELETE FROM Chef WHERE IdChef = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idChef);
            int done = s.executeUpdate();
            
            if(done == 0)
            	 throw new ChefNotFoundException("Chef con id " + idChef + " non trovato per l' eliminazione");
 
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante eliminazione Chef", e);
        }
    }
}
package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DAO.PartecipanteDAO;
import UninaFoodLab.DTO.Partecipante;
import UninaFoodLab.Exceptions.DAOException;
import UninaFoodLab.Exceptions.PartecipanteNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartecipanteDAO_Postgres implements PartecipanteDAO
{
	private Partecipante mapResultSetToPartecipante(ResultSet rs) throws SQLException
	{
	    Partecipante p = new Partecipante(
								           rs.getString("Username"),
								           rs.getString("Nome"),
								           rs.getString("Cognome"),
								           rs.getString("CodiceFiscale"),
								           rs.getDate("DataDiNascita").toLocalDate(),
								           rs.getString("LuogoDiNascita"),
								           rs.getString("Email"),
								       	   rs.getString("Password"),
								           null,
								           null
					    			   	 );
	    p.setId(rs.getInt("IdPartecipante"));	    
	    return p;
	}
	
	public void save(Partecipante toSavePartecipante)
    {
        String sql = "INSERT INTO Partecipante(Username, Nome, Cognome, CodiceFiscale, DataDiNascita, LuogoDiNascita, Email, Password) " +
                	 "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            s.setString(1, toSavePartecipante.getUsername());
            s.setString(2, toSavePartecipante.getNome());
            s.setString(3, toSavePartecipante.getCognome());
            s.setString(4, toSavePartecipante.getCodiceFiscale());
            s.setDate(5, toSavePartecipante.getDataDiNascita());
            s.setString(6, toSavePartecipante.getLuogoDiNascita());
            s.setString(7, toSavePartecipante.getEmail());
            s.setString(8, toSavePartecipante.getHashPassword());
            s.executeUpdate();
            
            try(ResultSet genKeys = s.getGeneratedKeys())
            {
            	if(genKeys.next())
            		toSavePartecipante.setId(genKeys.getInt(1));
            	else
            		throw new DAOException("Creazione partecipante fallita, nessun ID ottenuto.");
            }       
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante salvataggio Partecipante", e);
        }
    }
	
    public Partecipante getPartecipanteById(int idPartecipante)
    {	
        String sql = "SELECT * FROM Partecipante WHERE IdPartecipante = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idPartecipante);
            ResultSet rs = s.executeQuery();

            if(rs.next())
                return mapResultSetToPartecipante(rs);
            else
            	throw new PartecipanteNotFoundException("Partecipante con id " + idPartecipante + " non trovato");
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante ricerca Partecipante per id", e);
        }
    }
    
    public boolean getPartecipanteByCodEmail(String codFisc, String email)
    {
        String sql = "SELECT * FROM Partecipante WHERE CodiceFiscale = ? OR Email = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setString(1, codFisc);
            s.setString(2, email);
            ResultSet rs = s.executeQuery();

            if(rs.next())
            	return true;
            else
            	return false;
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante ricerca Partecipante per codice fiscale e email", e);
        }
    }

    public Partecipante getPartecipanteByUsername(String username)
    {
        String sql = "SELECT * FROM Partecipante WHERE Username = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setString(1, username);
            ResultSet rs = s.executeQuery();

            if(rs.next())
            	return mapResultSetToPartecipante(rs);
            else
            	throw new PartecipanteNotFoundException("Partecipante con username " + username + " non trovato");
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante ricerca Partecipante per username", e);
        }
    }

    public void update(Partecipante previousPartecipante, Partecipante updatedPartecipante)
    {
        String sql = "UPDATE Partecipante SET ";
        List<Object> param = new ArrayList<>();

        if(! (previousPartecipante.getUsername().equals(updatedPartecipante.getUsername())) )
        {
            sql += "Username = ?, ";
            param.add(updatedPartecipante.getUsername());
        }

        if(! (previousPartecipante.getNome().equals(updatedPartecipante.getNome())) )
        {
            sql += "Nome = ?, ";
            param.add(updatedPartecipante.getNome());
        }

        if(! (previousPartecipante.getCognome().equals(updatedPartecipante.getCognome())) )
        {
            sql += "Cognome = ?, ";
            param.add(updatedPartecipante.getCognome());
        }

        if(! (previousPartecipante.getDataDiNascita().equals(updatedPartecipante.getDataDiNascita())) )
        {
            sql += "DataDiNascita = ?, ";
            param.add(updatedPartecipante.getDataDiNascita());
        }
        
        if(! (previousPartecipante.getLuogoDiNascita().equals(updatedPartecipante.getLuogoDiNascita())) )
        {
            sql += "LuogoDiNascita = ?, ";
            param.add(updatedPartecipante.getLuogoDiNascita());
        }

        if(! (previousPartecipante.getEmail().equals(updatedPartecipante.getEmail())) )
        {
            sql += "Email = ?, ";
            param.add(updatedPartecipante.getEmail());
        }

        if(! (previousPartecipante.getHashPassword().equals(updatedPartecipante.getHashPassword())) )
        {
            sql += "Password = ? ";
            param.add(updatedPartecipante.getHashPassword());
        }

        if(!param.isEmpty())
        {
        	if(sql.endsWith(", ")) 
        		sql = sql.substring(0, sql.length() - 2);

            sql += " WHERE IdPartecipante = ?";
            param.add(previousPartecipante.getId());

            try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
            {
                for(int i = 0; i < param.size(); i++)
                    s.setObject(i + 1, param.get(i));

                s.executeUpdate();
            }
            catch(SQLException e)
            {
            	throw new DAOException("Errore DB durante aggiornamento Partecipante", e);
            }
        }
    }

    public void delete(int idPartecipante)
    {
        String sql = "DELETE FROM Partecipante WHERE IdPartecipante = ?";

        try(Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idPartecipante);
            int done = s.executeUpdate();
            
            if(done == 0)
            	 throw new PartecipanteNotFoundException("Partecipante con id " + idPartecipante + " non trovato per l' eliminazione");
        }
        catch(SQLException e)
        {
        	throw new DAOException("Errore DB durante eliminazione Partecipante", e);
        }
    }
}
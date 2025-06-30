package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DAO.PartecipanteDAO;
import UninaFoodLab.DTO.Partecipante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartecipanteDAO_Postgres implements PartecipanteDAO
{
    private Connection conn;

    public PartecipanteDAO_Postgres(Connection conn)
    {
        this.conn = conn;
    }

    public Partecipante getPartecipanteById(int idPartecipante) throws SQLException
    {
        String sql = "SELECT * FROM Partecipante WHERE IdPartecipante = ?";

        try(PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idPartecipante);
            ResultSet rs = s.executeQuery();

            while(rs.next())
                return new Partecipante( rs.getString("Username"),
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
        }
        return null;
    }

    public Partecipante getPartecipanteByUsername(String username) throws SQLException
    {
        String sql = "SELECT * FROM Partecipante WHERE Username = ?";

        try(PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setString(1, username);
            ResultSet rs = s.executeQuery();

            while(rs.next())
                return new Partecipante( rs.getString("Username"),
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
        }
        return null;
    }

    public void save(Partecipante toSavePartecipante) throws SQLException
    {
        String sql = "INSERT INTO Partecipante(Username, Nome, Cognome, CodiceFiscale, DataDiNascita, Email, Password) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setString(1, toSavePartecipante.getUsername());
            s.setString(2, toSavePartecipante.getNome());
            s.setString(3, toSavePartecipante.getCognome());
            s.setString(4, toSavePartecipante.getCodiceFiscale());
            s.setDate(5, toSavePartecipante.getDataDiNascita());
            s.setString(6, toSavePartecipante.getEmail());
            s.setString(7, toSavePartecipante.getHashPassword());
            s.executeUpdate();
        }
    }

    public void update(Partecipante previousPartecipante, Partecipante updatedPartecipante) throws SQLException
    {
        String sql = "UPDATE Partecipante SET";
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
            sql += "WHERE IdPartecipante = ?";
            param.add(previousPartecipante.getId());

            try(PreparedStatement s = conn.prepareStatement(sql))
            {
                for(int i = 0; i < param.size(); i++)
                    s.setObject(i + 1, param.get(i));

                s.executeUpdate();
            }
        }
    }

    public void delete(int idPartecipante) throws SQLException
    {
        String sql = "DELETE FROM Partecipante WHERE IdPartecipante = ?";

        try(PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idPartecipante);
            s.executeUpdate();
        }
    }
}

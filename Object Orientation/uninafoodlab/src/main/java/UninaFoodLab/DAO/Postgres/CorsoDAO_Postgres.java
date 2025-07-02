package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DAO.CorsoDAO;
import UninaFoodLab.DTO.Argomento;
import UninaFoodLab.DTO.Corso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CorsoDAO_Postgres implements CorsoDAO
{
    public Corso getCorsoById(int idCorso) throws SQLException{
        String sql ="SELECT * FROM Corso WHERE IdCorso = ?";
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idCorso);
            ResultSet rs = s.executeQuery();

            while(rs.next())
            {
                return new Corso(
                        rs.getString("Nome"),
                        rs.getDate("DataInizio").toLocalDate(),
                        rs.getString("FrequenzaSessioni"),
                        rs.getInt("Limite"),
                        rs.getString("Descrizione"),
                        rs.getDouble("Costo"),
                        rs.getBoolean("IsPratico"),
                        null,
                        null
                );
            }
        }
        return null;
    }

    public Corso getCorsoByChef(int idChef) throws SQLException{
        String sql ="SELECT * FROM Corso WHERE IdChef = ?";
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, idChef);
            ResultSet rs = s.executeQuery();

            while(rs.next())
            {
                return new Corso(
                        rs.getString("Nome"),
                        rs.getDate("DataInizio").toLocalDate(),
                        rs.getString("FrequenzaSessioni"),
                        rs.getInt("Limite"),
                        rs.getString("Descrizione"),
                        rs.getDouble("Costo"),
                        rs.getBoolean("IsPratico"),
                        null,
                        null
                );
            }
        }
        return null;
    }

    public List<Corso> getAllCorsi() throws SQLException{
        String sql ="SELECT * FROM Corso";
        List<Corso> ret =new ArrayList<Corso>();
        try(Connection conn = ConnectionManager.getConnection(); Statement s = conn.createStatement())
        {
            ResultSet rs = s.executeQuery(sql);

            while(rs.next())
            {
                ret.add(new Corso(
                        rs.getString("Nome"),
                        rs.getDate("DataInizio").toLocalDate(),
                        rs.getString("FrequenzaSessioni"),
                        rs.getInt("Limite"),
                        rs.getString("Descrizione"),
                        rs.getDouble("Costo"),
                        rs.getBoolean("IsPratico"),
                        null,
                        null)
                );

            }
        }
        return ret;
    }

    public List<Corso> getCorsiByArgomento(List<Argomento> argomenti) throws SQLException{

        if(argomenti.isEmpty())
                return this.getAllCorsi();

        String sql ="SELECT * FROM Corso WHERE IdCorso =((SELECT IdCorso FROM Argomenti_Corso WHERE IdArgomento = ?)";
        for (int i=1; i<argomenti.size(); i++)
        {
            sql += "INTERSECT (SELECT IdCorso FROM Argomenti_Corso WHERE IdArgomento = ?)";
        }
        sql += ")";

        int i=1;
        List<Corso> ret =new ArrayList<Corso>();
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            for (Argomento x:argomenti)
            {
                s.setInt(i, x.getId());
                i++;
            }

            ResultSet rs = s.executeQuery();

            while(rs.next())
            {
                ret.add(new Corso(
                        rs.getString("Nome"),
                        rs.getDate("DataInizio").toLocalDate(),
                        rs.getString("FrequenzaSessioni"),
                        rs.getInt("Limite"),
                        rs.getString("Descrizione"),
                        rs.getDouble("Costo"),
                        rs.getBoolean("IsPratico"),
                        null,
                        null)
                );
            }
        }
        return ret;
    }


    public void save(Corso toSaveCorso) throws SQLException {
        String sql = "INSERT INTO Corso (nome, data, frequenzaSessioni, limite, descrizione, costo, isPratico)  VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setString(1, toSaveCorso.getNome());
            s.setDate(2, toSaveCorso.getDataInizio());
            s.setString(3, toSaveCorso.getFrequenzaSessioni());
            s.setInt(4, toSaveCorso.getLimite());
            s.setString(5, toSaveCorso.getDescrizione());
            s.setDouble(6, toSaveCorso.getCosto());
            s.setBoolean(7, toSaveCorso.getIsPratico());
            s.executeUpdate();
        }
    }

    public void delete(int IdCorso) throws SQLException {
        String sql = "DELETE FROM Corso WHERE IdCorso = ?";
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
        {
            s.setInt(1, IdCorso);
            s.executeUpdate();
        }

    }

    public void update(Corso oldCorso, Corso newCorso) throws SQLException{
        if(!oldCorso.getDescrizione().equals(newCorso.getDescrizione()))
        {
            String sql = "UPDATE Corso SET Descrizione = ? WHERE IdCorso = ?";
            try (Connection conn = ConnectionManager.getConnection(); PreparedStatement s = conn.prepareStatement(sql))
            {
                s.setString(1, newCorso.getDescrizione());
                s.setInt(2, oldCorso.getId());
                s.executeUpdate();
            }
        }
    }
}

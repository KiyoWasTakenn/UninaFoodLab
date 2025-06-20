package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DAO.SessioneOnlineDAO;
import UninaFoodLab.DTO.SessioneOnline;

import java.sql.*;

public class SessioneOnlineDAO_Postgres implements SessioneOnlineDAO
{
    private Connection conn;

    public CorsoDAO_Postgres(Connection conn)
    {
        this.conn=conn;
    }

    public SessioneOnline getSessioneOnlineById(int id) throws SQLException
}

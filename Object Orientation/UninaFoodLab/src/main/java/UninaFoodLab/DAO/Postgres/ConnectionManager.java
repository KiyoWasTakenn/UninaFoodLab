package UninaFoodLab.DAO.Postgres;

import java.sql.*;

public class ConnectionManager
{
    private static ConnectionManager instance = null;
    private static Connection conn = null;

    private static final String url = "jdbc:postgresql://localhost:5432/UninaFoodLab";
    private static final String user = "postgres";
    private static final String password = "hello123";

    private ConnectionManager() {}

    public static ConnectionManager getInstance()
    {
        if(instance == null)
            instance = new ConnectionManager();
        return instance;
    }

    public static Connection getConnection() throws SQLException
    {
        try
        {
            if(conn == null || conn.isClosed())
                conn = DriverManager.getConnection(url + "?currentSchema=UninaFoodLab", user, password);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw ex;
        }

        return conn;
    }

    public static void closeConnection() throws SQLException
    {
        try
        {
            if(conn != null && !conn.isClosed())
                conn.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw ex;
        }
    }
}
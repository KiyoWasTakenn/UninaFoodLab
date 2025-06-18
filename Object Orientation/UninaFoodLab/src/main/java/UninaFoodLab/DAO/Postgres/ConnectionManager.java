package UninaFoodLab.DAO.Postgres;

import java.sql.*;

public class ConnectionManager
{
    private static Connection conn = null;
    private static final String url = "jdbc:postgresql://localhost:5432/PostgreSQL 17";
    private static final String user = "postgres";
    private static final String password = "hello123";

    private ConnectionManager() {}

    public static Connection getConnection() throws SQLException
    {
        if(conn == null || conn.isClosed())
        {
            try
            {
               conn = DriverManager.getConnection(url, user, password);
            }
            catch(SQLException ex)
            {

                throw ex;
            }
        }
        return conn;
    }

    public static void close() throws SQLException
    {
        if(conn != null && !conn.isClosed())
        {
            try
            {
                conn.close();
                conn = null;
            }
            catch(SQLException ex)
            {
                throw ex;
            }
        }
    }
}
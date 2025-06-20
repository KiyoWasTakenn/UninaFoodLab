package UninaFoodLab.DAO.Postgres;

import UninaFoodLab.DAO.UtilizzoDAO;
import java.sql.*;

public class UtilizzoDAO_Postgres implements UtilizzoDAO
{
    private Connection conn;

    public UtilizzoDAO_Postgres(Connection conn)
    {
        this.conn = conn;
    }



}

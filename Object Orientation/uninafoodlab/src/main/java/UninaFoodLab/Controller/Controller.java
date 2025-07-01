package UninaFoodLab.Controller;


import UninaFoodLab.DTO.*;
import UninaFoodLab.DAO.*;
import UninaFoodLab.DAO.Postgres.*;

import java.sql.Connection;

public class Controller
{
    private static Controller instance = null;

    private Controller() {}

    public static Controller getController()
    {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public static void main(String[] args)
    {

    }
    

	public bool checkLogin(String user, char[] pass)
	{
		
		
	}

}

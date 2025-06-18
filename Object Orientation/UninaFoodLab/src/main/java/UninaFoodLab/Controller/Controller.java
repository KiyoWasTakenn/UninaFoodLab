package UninaFoodLab.Controller;

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


}

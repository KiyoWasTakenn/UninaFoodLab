package UninaFoodLab.Exceptions;

public class ChefNotFoundException extends RecordNotFoundException
{
	public ChefNotFoundException(String msg) 
	{
        super(msg);
    }
}
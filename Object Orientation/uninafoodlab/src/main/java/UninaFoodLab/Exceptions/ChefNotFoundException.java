package UninaFoodLab.Exceptions;

public class ChefNotFoundException extends RecordNotFoundException
{
	private static final long serialVersionUID = 1L;

	public ChefNotFoundException(String msg) 
	{
        super(msg);
    }
}
package UninaFoodLab.Exceptions;

public class IngredienteNotFoundException extends RecordNotFoundException
{
	private static final long serialVersionUID = 1L;

	public IngredienteNotFoundException(String msg) 
    {
        super(msg);
    }
}
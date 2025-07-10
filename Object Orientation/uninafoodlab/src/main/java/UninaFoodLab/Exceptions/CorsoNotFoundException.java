package UninaFoodLab.Exceptions;

public class CorsoNotFoundException extends RecordNotFoundException
{
	private static final long serialVersionUID = 1L;

	public CorsoNotFoundException(String msg) 
    {
        super(msg);
    }
}
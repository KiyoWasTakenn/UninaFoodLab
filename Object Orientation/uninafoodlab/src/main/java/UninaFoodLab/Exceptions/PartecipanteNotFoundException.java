package UninaFoodLab.Exceptions;

public class PartecipanteNotFoundException extends RecordNotFoundException 
{
	private static final long serialVersionUID = 1L;

	public PartecipanteNotFoundException(String msg) 
    {
        super(msg);
    }
}
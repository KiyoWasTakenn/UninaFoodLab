package UninaFoodLab.Exceptions;

public class RequiredRicettaException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public RequiredRicettaException()
	{
		super("La sessione pratica deve trattare almeno una ricetta.");
	}
}

package UninaFoodLab.Exceptions;

public class RequiredArgomentoException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public RequiredArgomentoException()
	{
		super("Il corso deve avere almeno un argomento.");
	}
}
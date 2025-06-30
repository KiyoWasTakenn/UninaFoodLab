package UninaFoodLab.Exceptions;

public class RequiredChefException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public RequiredChefException()
	{
		super("Il Corso deve essere associato a uno chef.");
	}
}

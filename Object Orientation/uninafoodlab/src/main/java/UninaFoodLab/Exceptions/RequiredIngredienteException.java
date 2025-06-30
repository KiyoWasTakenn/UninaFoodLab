package UninaFoodLab.Exceptions;

public class RequiredIngredienteException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public RequiredIngredienteException()
	{
		super("La Ricetta deve avere almeno un ingrediente associato.");
	}
}

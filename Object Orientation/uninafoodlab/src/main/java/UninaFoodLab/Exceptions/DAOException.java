package UninaFoodLab.Exceptions;

public class DAOException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public DAOException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

	public DAOException(String msg)
	{
		super(msg);
	}
}
package UninaFoodLab.Exceptions;

public class DAOException extends RuntimeException
{
    public DAOException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

	public DAOException(String msg)
	{
		super(msg);
	}
}
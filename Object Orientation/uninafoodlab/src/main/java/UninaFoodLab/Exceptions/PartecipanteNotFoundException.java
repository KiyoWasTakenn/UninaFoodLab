package UninaFoodLab.Exceptions;

public class PartecipanteNotFoundException extends RecordNotFoundException 
{
    public PartecipanteNotFoundException(String msg) 
    {
        super(msg);
    }
}
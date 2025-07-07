package UninaFoodLab.DTO;

public class ReportMensile
{
	private int totCorsi;
    private int totOnline;
    private int totPratiche;
    private int minRicette;
    private int maxRicette;
    private double avgRicette;
    
    public ReportMensile(int totCorsi, int totOnline, int totPratiche, int minRicette, int maxRicette, double avgRicette) 
    {
        this.totCorsi = totCorsi;
        this.totOnline = totOnline;
        this.totPratiche = totPratiche;
        this.minRicette = minRicette;
        this.maxRicette = maxRicette;
        this.avgRicette = avgRicette;
    }
    
    public int getTotCorsi() 
    { 
    	return totCorsi; 
    }
    
    public int getTotOnline() 
    { 
    	return totOnline; 
    }
    
    public int getTotPratiche() 
    { 
    	return totPratiche; 
    }
    
    public int getMinRicette() 
    { 
    	return minRicette; 
    }
    
    public int getMaxRicette() 
    { 
    	return maxRicette; 
    }
    
    public double getAvgRicette() 
    {
    	return avgRicette; 
    }
}

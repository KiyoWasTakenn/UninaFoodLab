package UninaFoodLab.DAO;

import UninaFoodLab.DTO.ReportMensile;

public interface ReportMensileDAO
{
	ReportMensile getMonthlyReportByIdChef(int idChef);
}
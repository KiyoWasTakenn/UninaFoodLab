package UninaFoodLab.DAO;

import java.util.List;

import UninaFoodLab.DTO.Adesione;

public interface AdesioneDAO
{
	List<Adesione> getAdesioniByIdSessionePratica(int idSessionePratica);
	void save(Adesione toSaveAdesione);
	void delete(int idPartecipante, int idSessionePratica);
}
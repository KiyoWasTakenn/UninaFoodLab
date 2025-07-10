package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Ricetta;

import java.util.List;

public interface RicettaDAO
{
	void save(Ricetta toSaveRicetta);
	Ricetta getRicettaById(int idRicetta);
    List<Ricetta> getRicetteByIdChef(int idChef);
    List<Ricetta> getRicettaByIdSessione(int idSessione);
    void update(Ricetta previousRicetta, Ricetta updatedRicetta);
    void delete(int idRicetta);
}
package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Ricetta;

import java.util.List;

public interface RicettaDAO
{
    List<Ricetta> getRicetteByIdChef(int idChef);
    List<Ricetta> getRicettaByIdSessione(int idSessione);
    void save(Ricetta toSaveRicetta);
    void update(Ricetta previousRicetta, Ricetta updatedRicetta);
    void delete(int idRicetta);
}
GET ID RICETTA NOME RICETTA  PER LA GUI 
POI NEL CONTROLLER PRENDO ID RICETTE SELEEZIONATI E PER OGNI ID RICETTA FACCIO UN ARRAYLIST DI UTILIZZI E POI COSTRUISCO RICETTA CON QUELLI
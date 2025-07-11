package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Partecipante;

public interface PartecipanteDAO
{
    void save(Partecipante toSavePartecipante);
    Partecipante getPartecipanteById(int idPartecipante);
    Partecipante getPartecipanteByUsername(String username);
    boolean existsPartecipanteByCodiceFiscale(String codFisc);
    boolean existsPartecipanteByEmail(String email);
    void update(Partecipante previousPartecipante, Partecipante updatedPartecipante);
    void delete(int idPartecipante);
}
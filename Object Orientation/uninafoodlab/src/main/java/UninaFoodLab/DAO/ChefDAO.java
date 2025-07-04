package UninaFoodLab.DAO;

import UninaFoodLab.DTO.Chef;

import java.util.List;

public interface ChefDAO
{
	void save(Chef toSaveChef);
    Chef getChefById(int idChef);
    Chef getChefByUsername(String username);
    List<Chef> getChefsByName(String name, String surname);
    List<Chef> getAllChefs();
    void update(Chef previousChef, Chef updatedChef);
    void delete(int idChef);
}
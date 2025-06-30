package UninaFoodLab.DAO;

import UninaFoodLab.DTO.SessioneOnline;

import java.sql.*;
import java.util.List;

public interface SessioneOnlineDAO
{
        SessioneOnline getSessioneOnlineById(int id) throws SQLException;
        List<SessioneOnline> getSessioniOnlineByCorso(int idCorso) throws SQLException;
        void save(SessioneOnline toSaveSessione) throws SQLException;
        void delete(int IdSessioneOnline) throws SQLException;
        void update(SessioneOnline oldSessione, SessioneOnline newSessione) throws SQLException;

}

package tfg.eespunes.persistance;

import org.springframework.stereotype.Service;
import tfg.eespunes.persistance.controllers.DatabaseDAO;

@Service("DatabaseController")
public class DatabaseController {

    private final DatabaseDAO databaseDAO;

    public DatabaseController(DatabaseDAO databaseDAO) {
        this.databaseDAO = databaseDAO;
    }
}

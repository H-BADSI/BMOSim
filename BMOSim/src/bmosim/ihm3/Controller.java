package bmosim.ihm3;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public interface Controller {
    void initialize(URL url, ResourceBundle rb) throws SQLException, ClassNotFoundException;
}

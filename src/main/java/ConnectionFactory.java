
/*
 * @author allfel
 *
 * Data: 30/01/2023
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection recuperaConexao() throws SQLException {
        return DriverManager
            .getConnection("jdbc:mysql://localhost/cursos_alura?useTimezone=true&serverTimezone=UTC","root", "");

    }
}
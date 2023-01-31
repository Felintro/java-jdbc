
/*
 * @author allfel
 *
 * Data: 31/01/2023
 */

import java.sql.SQLException;

public class TestaPoolConexoes {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory= new ConnectionFactory();

        for(int i = 1; i <= 20; i++) {
            connectionFactory.recuperaConexao();
            System.out.println("Conex�o n�: " + i);
        }
    }
}
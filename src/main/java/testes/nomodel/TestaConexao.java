package testes.nomodel;
/*
 * @author allfel
 *
 * Data: 27/01/2023
 */

import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

@Deprecated
public class TestaConexao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperaConexao();

        System.out.println(connection.toString());
        connection.close();
    }
}

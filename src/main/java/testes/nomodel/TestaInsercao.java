package testes.nomodel;
/*
 * @author allfel
 *
 * Data: 30/01/2023
 */

import java.sql.*;

public class TestaInsercao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperaConexao();

        PreparedStatement statement = connection
            .prepareStatement("INSERT INTO produto (ds_nome, ds_descricao) VALUES ('PLACA DE VIDEO', 'GALAX RTX 2060 12GB')", Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            Integer id = rs.getInt(1);
            System.out.println("O ID criado foi: " + id);
        }
    }
}
package testes.nomodel;
/*
 * @author allfel
 *
 * Data: 30/01/2023
 */

import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Deprecated
public class TestaRemocao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperaConexao();

        int idProduto = 10;

        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM produto p WHERE p.id_produto > ?");
        pstmt.setInt(1, idProduto);
        pstmt.execute();

        System.out.println("Linhas modificadas: " + pstmt.getUpdateCount());

    }
}

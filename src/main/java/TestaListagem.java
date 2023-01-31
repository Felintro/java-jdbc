
/*
 * @author allfel
 *
 * Data: 30/01/2023
 */

import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperaConexao();

        PreparedStatement pstmt = connection.prepareStatement("SELECT id_produto, ds_nome, ds_descricao FROM produto");
        pstmt.execute();

        ResultSet rst = pstmt.getResultSet();

        while(rst.next()) {
            Integer idProduto = rst.getInt("id_produto");
            System.out.print(idProduto + " - ");
            String nomeProduto = rst.getString("ds_nome");
            System.out.print(nomeProduto + " - ");
            String descricaoProduto = rst.getString("ds_descricao");
            System.out.print(descricaoProduto + "\n");
        }

        connection.close();
    }
}

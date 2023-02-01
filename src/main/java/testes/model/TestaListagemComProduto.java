package testes.model;
/*
 * @author allfel
 *
 * Data: 31/01/2023
 */

import dao.ProdutoDAO;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaListagemComProduto {

    public static void main(String[] args) throws SQLException {
        try(Connection connection = new ConnectionFactory().recuperaConexao()) {
            ProdutoDAO dao = new ProdutoDAO(connection);
            System.out.println(dao.findAllProdutos());
            System.out.println("=====================================================================");
            System.out.println(dao.findProdutoById(5));

        }
    }

}

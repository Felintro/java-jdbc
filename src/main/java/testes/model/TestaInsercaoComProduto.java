package testes.model;
/*
 * @author allfel
 *
 * Data: 31/01/2023
 */

import dao.ProdutoDAO;
import factory.ConnectionFactory;
import model.Produto;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaInsercaoComProduto {

    public static void main(String[] args) throws SQLException {

        Produto produto = new Produto("CELULAR IPHONE 13", "CELULAR APPLE IPHONE 13 PRO MAX 8GB RAM + 256GB");

        try(Connection connection = new ConnectionFactory().recuperaConexao()) {
            ProdutoDAO dao = new ProdutoDAO(connection);
            dao.insertProduto(produto);
        }

    }

}

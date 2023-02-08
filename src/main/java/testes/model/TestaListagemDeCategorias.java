package testes.model;
/*
 * @author allfel
 *
 * Data: 01/02/2023
 */

import dao.CategoriaDAO;
import factory.ConnectionFactory;
import model.Categoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Deprecated
public class TestaListagemDeCategorias {

    public static void main(String[] args) throws SQLException {

        try(Connection connection = new ConnectionFactory().recuperaConexao()) {
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);

            List<Categoria> listaDeCategorias = categoriaDAO.findCategoriaJoinProduto();
            listaDeCategorias.stream()
                .forEach(e -> System.out.println(e.toString()));
        }
    }
}

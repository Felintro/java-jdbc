package testes.model;
/*
 * @author allfel
 *
 * Data: 01/02/2023
 */

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import factory.ConnectionFactory;
import model.Categoria;
import model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemDeCategorias {

    public static void main(String[] args) throws SQLException {

        try(Connection connection = new ConnectionFactory().recuperaConexao()) {
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);

            List<Categoria> listaDeCategorias = categoriaDAO.findAllCategorias();
            listaDeCategorias.stream()
                .forEach(
                    ct -> {
                        System.out.println(ct.getId() + " - " + ct.getNome());

                        try {
                            for(Produto produto : new ProdutoDAO(connection).findProdutoByCategoria(ct)) {
                                System.out.println(ct.getNome() + " - " + produto.getNome());
                            }
                        } catch(SQLException throwables) {
                            throwables.printStackTrace();
                        }


                    }




                );

        }




    }



}

package dao;
/*
 * @author allfel
 *
 * Data: 01/02/2023
 */

import lombok.AllArgsConstructor;
import model.Categoria;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CategoriaDAO {

    private Connection connection;

    private static final String SQL_DELETE_CATEGORIA_BY_ID = "DELETE FROM categoria c where c.id_categoria = ?";
    private static final String SQL_INSERT_CATEGORIA = "INSERT INTO categoria (ds_nome) VALUES (?);";
    private static final String SQL_UPDATE_CATEGORIA_BY_ID = "UPDATE categoria c SET c.ds_nome = ? WHERE c.id_categoria = ?;";
    private static final String SQL_SELECT_CATEGORIA_BY_ID = "SELECT * FROM produto p WHERE p.id_produto = ?";
    private static final String SQL_SELECT_CATEGORIA_ALL = "SELECT * FROM categoria;";
    private static final String SQL_SELECT_CATEGORIA_JOIN_PRODUTO = " SELECT c.id_categoria c_id_categoria, c.ds_nome c_ds_nome, " +
                                                                    " p.id_produto p_id_produto, p.ds_nome p_ds_nome, " +
                                                                    " p.ds_descricao p_ds_descricao, p.id_categoria p_id_categoria " +
                                                                    " FROM categoria c, produto p " +
                                                                    " WHERE c.id_categoria = p.id_categoria;";

    public Categoria findCategoriaById(Integer idCategoria) {
        try {
            Categoria categoria = null;

            try(PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_CATEGORIA_BY_ID)){
                pstmt.setInt(1, idCategoria);
                pstmt.execute();

                try(ResultSet rst = pstmt.getResultSet()) {
                    if(rst.next()) {
                        categoria = new Categoria(rst.getInt("id_categoria"), rst.getString("ds_nome"));
                    }
                }
            }
            return categoria;

        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public List<Categoria> findAllCategorias() {
        try {
            List<Categoria> categorias = new ArrayList<>();

            try(PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_CATEGORIA_ALL)) {
                pstmt.execute();

                try(ResultSet rst = pstmt.getResultSet()) {
                    while(rst.next()) {
                        Categoria categoria = new Categoria(rst.getInt("id_categoria"), rst.getString("ds_nome"));
                        categorias.add(categoria);
                    }
                }
            }
            return categorias;

        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<Categoria> findCategoriaJoinProduto() throws SQLException {
        Categoria ultima = null;
        List<Categoria> categorias = new ArrayList<>();

        try(PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_CATEGORIA_JOIN_PRODUTO)) {
            pstmt.execute();

            try(ResultSet rst = pstmt.getResultSet()) {
                while(rst.next()) {
                    if(ultima == null || ! ultima.getNome().equals(rst.getString("c_ds_nome"))) {
                        Categoria categoria = new Categoria(rst.getInt("c_id_categoria"), rst.getString("c_ds_nome"));
                        ultima = categoria;
                        categorias.add(categoria);
                    }

                    Integer idProduto = rst.getInt("p_id_produto");
                    String nomeProduto = rst.getString("p_ds_nome");
                    String descricaoProduto = rst.getString("p_ds_descricao");
                    Integer idCategoriaProduto = rst.getInt("p_id_categoria");
                    Produto produto = new Produto(idProduto, nomeProduto, descricaoProduto, idCategoriaProduto);

                    ultima.adicionarProduto(produto);
                }
            }
        }
        return categorias;
    }

    public void insertCategoria(Categoria categoria) {
        try {
            try(PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_CATEGORIA, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, categoria.getNome());
                pstmt.execute();

                try(ResultSet rst = pstmt.getGeneratedKeys()){
                    while(rst.next()) {
                        categoria.setId(rst.getInt(1));
                    }
                }
            }

        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateCategoriaById(Integer idCategoria, String novoNomeCategoria) {
        try {
            try(PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_CATEGORIA_BY_ID)) {
                pstmt.setString(1, novoNomeCategoria);
                pstmt.setInt(2, idCategoria);
                pstmt.execute();
            }

        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteCategoriaById(Integer idCategoria) {
        try {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE_CATEGORIA_BY_ID)){
                pstmt.setInt(1, idCategoria);
                pstmt.execute();
            }
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}

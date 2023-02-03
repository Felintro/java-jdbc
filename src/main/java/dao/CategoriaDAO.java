package dao;
/*
 * @author allfel
 *
 * Data: 01/02/2023
 */

import model.Categoria;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;
    private static final String SQL_INSERT_CATEGORIA = "INSERT INTO produto (ds_nome, ds_descricao) VALUES (?, ?);";
    private static final String SQL_SELECT_CATEGORIA_BY_ID = "SELECT * FROM produto p WHERE p.id_produto = ?";
    private static final String SQL_SELECT_CATEGORIA_ALL = "SELECT * FROM categoria;";
    private static final String SQL_SELECT_CATEGORIA_JOIN_PRODUTO = " SELECT c.id_categoria c_id_categoria, c.ds_nome c_ds_nome, " +
        " p.id_produto p_id_produto, p.ds_nome p_ds_nome, " +
        " p.ds_descricao p_ds_descricao, p.id_categoria p_id_categoria " +
        " FROM categoria c, produto p " +
        " WHERE c.id_categoria = p.id_categoria;";

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
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
                    Produto produto = new Produto(rst.getInt("p_id_produto"), rst.getString("p_ds_nome"), rst.getString("p_ds_descricao"));
                    ultima.adicionarProduto(produto);
                }
            }

        }

        return categorias;

    }


}

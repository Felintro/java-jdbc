package dao;
/*
 * @author allfel
 *
 * Data: 01/02/2023
 */

import model.Categoria;

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

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }


    public List<Categoria> findAllCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        try(PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_CATEGORIA_ALL)) {
            pstmt.execute();

            try(ResultSet rst = pstmt.getResultSet()) {
                while(rst.next()){
                    Categoria categoria = new Categoria(rst.getInt("id_categoria"), rst.getString("ds_nome"));
                    categorias.add(categoria);
                }
            }


        }

        return categorias;

    }


}

package dao;
/*
 * @author allfel
 *
 * Data: 31/01/2023
 */

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.Produto;

import java.sql.*;

@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDAO {

    private Connection connection;
    private static final String SQL_INSERT_PRODUTO = "INSERT INTO produto (ds_nome, ds_descricao) VALUES (?, ?);";
    private static final String SQL_SELECT_PRODUTO_BY_ID = "SELECT * FROM produto p WHERE p.id = ?";

    public void saveProduto(Produto produto) throws SQLException {
        try(PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_PRODUTO, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getDescricao());

            pstmt.execute();

            try(ResultSet rst = pstmt.getGeneratedKeys()) {
                while(rst.next()) {
                    produto.setId(rst.getInt(1));
                }
            }
        }
    }

    public Produto findProdutoById(int idProduto) throws SQLException {
        Produto produto = new Produto();

        try(PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PRODUTO_BY_ID)) {
            pstmt.setInt(1, idProduto);
            pstmt.executeQuery();

            try(ResultSet rst = pstmt.getResultSet()){
                if(rst.next()){
                    produto.setId(rst.getInt("id_produto"));
                    produto.setNome(rst.getString("ds_nome"));
                    produto.setDescricao(rst.getString("ds_descricao"));
                }
            }
        }

        return produto;
    }

    



}

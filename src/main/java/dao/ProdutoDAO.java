package dao;
/*
 * @author allfel
 *
 * Data: 31/01/2023
 */

import lombok.AllArgsConstructor;
import model.Categoria;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProdutoDAO {

    private Connection connection;

    private static final String SQL_INSERT_PRODUTO = "INSERT INTO produto (ds_nome, ds_descricao, id_categoria) VALUES (?, ?, ?);";
    private static final String SQL_SELECT_PRODUTO_BY_ID = "SELECT * FROM produto p WHERE p.id_produto = ?";
    private static final String SQL_SELECT_PRODUTO_ALL = "SELECT * FROM produto;";
    private static final String SQL_SELECT_PRODUTO_BY_CATEGORIA = "SELECT * FROM produto p WHERE p.id_categoria = ?;";
    private static final String SQL_UPDATE_PRODUTO_BY_ID = "UPDATE produto p SET p.ds_nome = ?, p.ds_descricao = ? WHERE p.id_produto = ?;";
    private static final String SQL_DELETE_PRODUTO_BY_ID = "DELETE FROM produto p WHERE p.id_produto = ?;";

    public void insertProduto(Produto produto) {
        try{
            try(PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_PRODUTO, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, produto.getNome());
                pstmt.setString(2, produto.getDescricao());
                pstmt.setInt(3, produto.getIdCategoria());

                pstmt.execute();

                try(ResultSet rst = pstmt.getGeneratedKeys()) {
                    while(rst.next()) {
                        produto.setId(rst.getInt(1));
                    }
                }
            }

        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Produto findProdutoById(int idProduto) throws SQLException {
        Produto produto = new Produto();

        try(PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PRODUTO_BY_ID)) {
            pstmt.setInt(1, idProduto);
            ResultSet rst = pstmt.executeQuery();

            if(rst.next()){
                produto.setId(rst.getInt("id_produto"));
                produto.setNome(rst.getString("ds_nome"));
                produto.setDescricao(rst.getString("ds_descricao"));
                produto.setIdCategoria(rst.getInt("id_categoria"));
            }
        }
        return produto;
    }

    public List<Produto> findAllProdutos() {
        try {
            List<Produto> produtos = new ArrayList<>();

            try(PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PRODUTO_ALL)) {
                ResultSet rst = pstmt.executeQuery();

                while(rst.next()) {
                    int idProduto = rst.getInt("id_produto");
                    String nomeProduto = rst.getString("ds_nome");
                    String descricaoProduto = rst.getString("ds_descricao");
                    int idCategoria = rst.getInt("id_categoria");
                    Produto produto = new Produto(idProduto, nomeProduto, descricaoProduto, idCategoria);

                    produtos.add(produto);
                }
            }
            return produtos;

        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Produto> findProdutoByCategoria(Categoria categoria) {
        try {
            List<Produto> produtos = new ArrayList<>();

            try(PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PRODUTO_BY_CATEGORIA)) {
                pstmt.setInt(1, categoria.getId());
                ResultSet rst = pstmt.executeQuery();

                while(rst.next()) {
                    int idProduto = rst.getInt("id_produto");
                    String nomeProduto = rst.getString("ds_nome");
                    String descricaoProduto = rst.getString("ds_descricao");
                    int idCategoria = rst.getInt("id_categoria");

                    Produto produto = new Produto(idProduto, nomeProduto, descricaoProduto, idCategoria);

                    produtos.add(produto);
                }
            }
            return produtos;

        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateProdutoById(Integer idProduto, String novoNome, String novaDescricao) {
        try {
            try(PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_PRODUTO_BY_ID)) {
                pstmt.setString(1, novoNome);
                pstmt.setString(2, novaDescricao);
                pstmt.setInt(3, idProduto);
                pstmt.execute();
            }
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteProdutoById(Integer idProduto) {
        try {
            try(PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE_PRODUTO_BY_ID)) {
                pstmt.setInt(1, idProduto);
                pstmt.execute();
            }
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
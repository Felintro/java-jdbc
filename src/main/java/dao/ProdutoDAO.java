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



    public Produto insertProduto(Produto produto) {
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

            return produto;

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

    public List<Produto> findAllProdutos() throws SQLException {
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

    }

    public List<Produto> findProdutoByCategoria(Categoria categoria) throws SQLException {
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

    }
}
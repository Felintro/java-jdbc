
/*
 * @author allfel
 *
 * Data: 31/01/2023
 */

import model.Produto;

import java.sql.*;

public class TestaInsercaoComProduto {
    public static void main(String[] args) throws SQLException {
        Produto celular = new Produto("CELULAR XIAOMI", "XIAOMI POCOPHONE F1 6GB RAM + 64GB ARMAZENAMENTO");

        try(Connection conn = new ConnectionFactory().recuperaConexao()) {
            String sql = "INSERT INTO produto (ds_nome, ds_descricao) VALUES (?, ?);";

            try(PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, celular.getNome());
                pstmt.setString(2, celular.getDescricao());

                pstmt.execute();

                try(ResultSet rst = pstmt.getGeneratedKeys()){
                    while(rst.next()){
                        celular.setId(rst.getInt(1));
                    }
                }

            }
        }

        System.out.println(celular.toString());

    }

}

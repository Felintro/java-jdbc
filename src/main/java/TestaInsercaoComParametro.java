
/*
 * @author allfel
 *
 * Data: 30/01/2023
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.recuperaConexao();
        con.setAutoCommit(false);
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO produto (ds_nome, ds_descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            adicionarVariavel("MONITOR ACER", "ACER V226HQL 21'", pstmt);
            adicionarVariavel("NOTEBOOK DELL", "NOTEBOOK DELL 14' INTEL I5 11ª GERACAO", pstmt);

            con.commit();

        } catch(Exception e) {
            con.rollback();
            System.out.println("Um erro foi identificado! \nRollback efetuado! \nErro:");
            e.printStackTrace();
        }
    }

    private static void adicionarVariavel(String nomeProduto, String descricaoProduto, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, nomeProduto);
        pstmt.setString(2, descricaoProduto);

        pstmt.execute();
    }
}

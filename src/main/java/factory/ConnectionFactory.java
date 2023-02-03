package factory;
/*
 * @author allfel
 *
 * Data: 30/01/2023
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/cursos_alura?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("admin");
        comboPooledDataSource.setPassword("admin");
        comboPooledDataSource.setMaxPoolSize(15);

        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperaConexao() {
        try{
            return this.dataSource.getConnection();
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }

    }
}
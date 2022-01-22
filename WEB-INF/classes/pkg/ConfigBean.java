package pkg;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class ConfigBean{

    private static final DataSource dataSource = makeDataSource();
    
    private static DataSource makeDataSource() {
        try {
            InitialContext ctx = new InitialContext();
            return (DataSource) ctx.lookup ("java:/comp/env/jdbc/MyConnection");
        }catch (NamingException e ) { throw new RuntimeException(e); }
    }
    public static Connection getConnection() throws SQLException    {
        return dataSource.getConnection();
    }

}

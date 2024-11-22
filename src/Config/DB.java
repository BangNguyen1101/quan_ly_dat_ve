package Config;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {
    public static java.sql.Connection getConnection() throws SQLException, ClassNotFoundException {
        java.sql.Connection oConn = null;
        Class.forName("org.mariadb.jdbc.Driver");        
        oConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/quan_ly_dat_ve?user=root&password=123456");
        return oConn;
    }

    public static void closeConnection(java.sql.Connection oConn) throws SQLException {
        if (oConn != null) {
            oConn.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public static void closeCallableStatement(CallableStatement callableStatement) throws SQLException {
        if (callableStatement != null) {
            callableStatement.close();
        }
    }
}

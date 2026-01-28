package B5;

import java.sql.*;

public class DbUtil {

    public static Connection open() throws SQLException {
        return DriverManager.getConnection(DbConfig.JDBC_URL, DbConfig.USER, DbConfig.PASS);
    }

    public static boolean exists(Connection conn, String sql, String value) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, value);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}

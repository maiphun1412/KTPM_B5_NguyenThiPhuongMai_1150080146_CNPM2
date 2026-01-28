package B5;

public class DbConfig {
    // SỬA CHO ĐÚNG MÁY BẠN
    public static final String SERVER = "LAPTOP-VDKBJUCL";
    public static final String DB_NAME = "Lab5_KiemThu";
    public static final String USER = "sa";
    public static final String PASS = "14122003";

    // Nếu bạn dùng SQL Server local, thường OK:
    public static final String JDBC_URL =
            "jdbc:sqlserver://" + SERVER +
            ";databaseName=" + DB_NAME +
            ";encrypt=true;trustServerCertificate=true;loginTimeout=8;";
}

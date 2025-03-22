package object.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KetNoiSQL {
	private static KetNoiSQL instance;
    private Connection cnn;

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ViDu;encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "123";

    private KetNoiSQL() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Kết nối SQL Server thành công!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("❌ Lỗi kết nối CSDL: " + ((Throwable) e).getMessage());
        }
    }

    public static KetNoiSQL getInstance() {
        if (instance == null) {
            instance = new KetNoiSQL();
        }
        return instance;
    }

    public Connection getCnn() {
        return cnn;
    }
}


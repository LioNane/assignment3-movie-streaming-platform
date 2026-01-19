import java.sql.*;

public class DatabaseConnection {
    private static final String connectionURL = "jdbc:postgresql://localhost:5432/movie-streaming-platform";
    private static final String user = "postgres";
    private static final String password = "asdf";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(connectionURL, user, password);
    }

}

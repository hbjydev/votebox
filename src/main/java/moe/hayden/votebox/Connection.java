package moe.hayden.votebox;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * The Connection class used by all database-related operations in the
 * application.
 */
public class Connection {
    private static Connection instance;

    public java.sql.Connection jdbcConnection;

    public void connect(String connUrl) throws SQLException {
        jdbcConnection = DriverManager.getConnection(connUrl);
    }

    public ResultSet select(String query) throws SQLException {
        var statement = jdbcConnection.createStatement();
        return statement.executeQuery(query);
    }

    public void init() throws SQLException, IOException {
        var statement = jdbcConnection.createStatement();
        var sql = getFileContents("initdb.sql");
        statement.execute(sql);
    }

    private File getFile(String fileName) {
        var url = VoteboxApplication.class.getResource(fileName);
        if (url == null) {
            throw new IllegalArgumentException(fileName + " is not found.");
        }
        return new File(url.getFile());
    }

    private String getFileContents(String fileName) throws IOException {
        var is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new IllegalArgumentException(fileName + " is not found.");
        }
        var reader = new BufferedReader(new InputStreamReader(is));

        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }
}

package moe.hayden.votebox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VoteboxApplication extends Application {

    public static void createNewDatabase()
    {
        String url = "jdbc:sqlite:votebox.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");

                conn.createStatement().execute("" +
                    "create table if not exists voter(" +
                    "   id integer primary key," +
                    "   registration varchar(255) unique" +
                    ");"
                );
                conn.createStatement().execute("" +
                    "create table if not exists vote(" +
                    "   id integer primary key," +
                    "   name varchar(255) unique," +
                    "   description varchar(255)," +
                    "   options varchar(255)" +
                    ");"
                );
                conn.createStatement().execute("" +
                    "create table if not exists ballot(" +
                    "   id integer primary key," +
                    "   voter_id integer," +
                    "   vote_id integer," +
                    "   option varchar(255)" +
                    ");"
                );
                conn.createStatement().execute("" +
                    "create table if not exists user(" +
                    "   id integer primary key," +
                    "   username varchar(255)," +
                    "   password varchar(255)," +
                    "   role varchar(255)" +
                    ");"
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
        // load the landing page FXML file
        var fxmlLoader = new FXMLLoader(VoteboxApplication.class.getResource("landing-view.fxml"));

        // create a scene with it
        var scene = new Scene(fxmlLoader.load(), 280, 103);
        stage.setScene(scene);

        // add some metadata to the stage
        stage.setTitle("Votebox");
        stage.setResizable(false);

        // render the stage
        stage.show();
    }

    public static void main(String[] args) {
        createNewDatabase();
        // run the main JavaFX application
        launch();
    }
}

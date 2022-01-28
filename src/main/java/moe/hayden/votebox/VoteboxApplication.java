package moe.hayden.votebox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class VoteboxApplication extends Application {
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

    public static void main(String[] args) throws SQLException, IOException {
        // initialize database connection to db.sqlite file, allowing for multi
        // statement queries to run
//        var connection = Connection.getInstance();
//        connection.connect("jdbc:sqlite:db.sqlite?allowMultiQueries=true");
//        connection.init();

        // run the main JavaFX application
        launch();
    }
}

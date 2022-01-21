package moe.hayden.votebox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class FXUtils {
    /**
     * Returns a scene from an FXML file path.
     * @param name The name of the FXML file to load
     * @return The built scene from FXML.
     */
    public static Scene getScene(String name) throws IOException {
        var fxmlLoader = new FXMLLoader(VoteboxApplication.class.getResource(name));
        return new Scene(fxmlLoader.load());
    }

    /**
     * Returns a scene with a provided size from an FXML file path.
     * @param name The name of the FXML file to load
     * @param width The width to create the scene with
     * @param height The height to create the scene with
     * @return The built scene from FXML.
     */
    public static Scene getScene(String name, double width, double height) throws IOException {
        var fxmlLoader = new FXMLLoader(VoteboxApplication.class.getResource(name));
        return new Scene(fxmlLoader.load(), width,height);
    }
}

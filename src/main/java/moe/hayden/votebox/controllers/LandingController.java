package moe.hayden.votebox.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import moe.hayden.votebox.FXUtils;
import moe.hayden.votebox.VoteboxApplication;

import java.io.IOException;

public class LandingController {
    @FXML
    private Label welcomeText;

    /** Swaps the view to the Voter page. */
    @FXML
    protected void onVoterButtonClick() throws IOException {
        var stage = (Stage) welcomeText.getScene().getWindow();
        stage.setScene(FXUtils.getScene("voter/index.fxml"));
        stage.setTitle("Administrative Login");
    }

    /** Swaps the view to the Admin Login page. */
    @FXML
    protected void onAdminButtonClick() throws IOException {
        var stage = (Stage) welcomeText.getScene().getWindow();
        stage.setScene(FXUtils.getScene("admin/index.fxml"));
        stage.setTitle("Administrative Login");
    }
}

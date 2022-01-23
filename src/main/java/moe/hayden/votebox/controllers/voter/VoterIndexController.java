package moe.hayden.votebox.controllers.voter;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import moe.hayden.votebox.FXUtils;
import moe.hayden.votebox.models.Vote;
import moe.hayden.votebox.models.Voter;

import java.io.IOException;

public class VoterIndexController {
    @FXML
    private TextField registrationField;

    @FXML
    protected void onSubmitButtonClick() throws Exception {
        var vote = new Vote();
        vote.save();
        System.out.println("Voter registration: " + registrationField.getText());
    }

    @FXML
    protected void onBackButtonClick() throws Exception {
        var vote = new Vote();
        vote.save();
        var stage = (Stage) registrationField.getScene().getWindow();
        stage.setScene(FXUtils.getScene("landing-view.fxml"));
        stage.setTitle("Votebox");
    }
}

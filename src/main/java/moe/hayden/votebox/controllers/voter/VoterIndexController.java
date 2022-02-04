package moe.hayden.votebox.controllers.voter;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import moe.hayden.votebox.ApplicationState;
import moe.hayden.votebox.FXUtils;
import moe.hayden.votebox.models.Vote;
import moe.hayden.votebox.models.Voter;
import moe.hayden.votebox.repositories.VoterRepository;

import java.io.IOException;

public class VoterIndexController {
    @FXML
    private TextField registrationField;

    @FXML
    protected void onSubmitButtonClick() throws Exception {
        Voter voter;
        VoterRepository repo = VoterRepository.getInstance();
        ApplicationState state = ApplicationState.getInstance();
        var user = repo.findByRegistration(registrationField.getText());

        if (user.isPresent()) {
            System.out.println("Voter registration: " + user.get().registration);
            voter = user.get();
        } else {
            System.out.println("[err] no such user found by registration " + registrationField.getText());
            voter = new Voter(registrationField.getText());
            repo.create(voter);
        }

        state.setVoter(voter);
        var stage = (Stage) registrationField.getScene().getWindow();
        stage.setScene(FXUtils.getScene("voter/votes-list.fxml"));
        stage.setTitle("Select Vote");
    }

    @FXML
    protected void onBackButtonClick() throws Exception {
        var stage = (Stage) registrationField.getScene().getWindow();
        stage.setScene(FXUtils.getScene("landing-view.fxml"));
        stage.setTitle("Votebox");
    }
}

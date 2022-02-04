package moe.hayden.votebox.controllers.voter.ballots;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import moe.hayden.votebox.ApplicationState;

public class BallotController {
    private ApplicationState state;

    @FXML
    private ListView<String> ballotListView;

    public void initialize() {
        state = ApplicationState.getInstance();
        var vote = state.getVote();
        var options = vote.getOptions();

        for (String option : options) {
            ballotListView.getItems().add(vote.name);
        }
    }

    @FXML
    public void onBallotSubmit() throws Exception {
        state.getVote().castVote(
            state.getVoter(),
            ballotListView.getSelectionModel().getSelectedItem()
        );
    }
}

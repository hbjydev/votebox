package moe.hayden.votebox.controllers.voter;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import moe.hayden.votebox.ApplicationState;
import moe.hayden.votebox.models.Vote;
import moe.hayden.votebox.repositories.VoteRepository;

public class VoteListController {
    private ApplicationState state;
    private VoteRepository voteRepository;

    @FXML
    private ListView<String> voteListView;

    public void initialize() {
        state = ApplicationState.getInstance();
        voteRepository = VoteRepository.getInstance();

        for (Vote vote : voteRepository.getAll()) {
            voteListView.getItems().add(vote.name);
        }
    }

    @FXML
    public void onSelectButtonClick() {
        var voter = state.getVoter();
        var selected = voteListView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            var alert = new Alert(Alert.AlertType.ERROR, "You must specify a vote to continue.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        var vote = voteRepository.findByName(selected);
        if (vote.isPresent()) {
            System.out.println("Voter " + voter.registration + " selected vote " + vote.get().name);
        } else {
            System.out.println("No such vote found!");
        }
    }
}

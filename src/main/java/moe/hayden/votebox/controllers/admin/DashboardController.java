package moe.hayden.votebox.controllers.admin;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import moe.hayden.votebox.ApplicationState;
import moe.hayden.votebox.models.Vote;
import moe.hayden.votebox.repositories.BallotRepository;
import moe.hayden.votebox.repositories.VoteRepository;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DashboardController {
    private Vote currentVote;
    private ApplicationState state;
    private VoteRepository voteRepository;

    @FXML
    public ListView<String> voteListView;

    @FXML
    public ListView<String> optionListView;

    @FXML
    public Button selectButton;
    @FXML
    public Button countButton;

    public void updateVoteList() throws SQLException {
        for (Vote vote : voteRepository.getAll()) {
            voteListView.getItems().add(vote.name);
        }
    }

    public void initialize() throws SQLException {
        state = ApplicationState.getInstance();
        voteRepository = VoteRepository.getInstance();

        updateVoteList();
    }

    @FXML
    public void onVoteSelect() throws SQLException {
        var item = voteListView.getSelectionModel().getSelectedItem();
        currentVote = voteRepository.findByName(item);
        var options = currentVote.getOptions();
        optionListView.getItems().clear();
        for (String option : options) {
            optionListView.getItems().add(option);
        }
    }

    @FXML
    public void onCountSelect() throws SQLException {
        var item = voteListView.getSelectionModel().getSelectedItem();
        currentVote = voteRepository.findByName(item);
        var options = currentVote.getOptions();
        var ballots = BallotRepository.getInstance().findByVote(currentVote);

        StringBuilder msg = new StringBuilder("Vote [" + currentVote.name + "] currently has ");

        for (String option : options) {
            var count = ballots.stream().filter(b -> b.option.equals(option)).toArray().length;
            msg.append(count).append(" votes for ").append(option).append(", ");
        }

        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg.toString());
        alert.show();
    }

    @FXML
    public void onAdd() throws SQLException
    {
        var optionAlert = new TextInputDialog();
        optionAlert.setHeaderText("Option:");
        var option = optionAlert.showAndWait().get();

        var options = Arrays.stream(currentVote.getOptions()).collect(Collectors.toList());
        options.add(option);
        currentVote.options = String.join(";", options);

        voteRepository.update(currentVote);
    }

    @FXML
    public void onCreate() throws SQLException
    {
        var nameAlert = new TextInputDialog();
        nameAlert.setHeaderText("Name:");
        var name = nameAlert.showAndWait().get();

        var vote = new Vote(name, "", "");

        voteRepository.create(vote);

        updateVoteList();
    }
}

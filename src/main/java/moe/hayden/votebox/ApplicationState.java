package moe.hayden.votebox;

import moe.hayden.votebox.models.Vote;
import moe.hayden.votebox.models.Voter;

public class ApplicationState {
    private static ApplicationState instance;

    private Voter voter;
    private Vote vote;

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public Vote getVote(Vote vote) {
        return vote;
    }

    public static ApplicationState getInstance() {
        if (instance == null) {
            instance = new ApplicationState();
        }
        return ApplicationState.instance;
    }
}

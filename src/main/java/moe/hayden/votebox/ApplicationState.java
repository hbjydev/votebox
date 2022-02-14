package moe.hayden.votebox;

import moe.hayden.votebox.models.User;
import moe.hayden.votebox.models.Vote;
import moe.hayden.votebox.models.Voter;

public class ApplicationState {
    private static ApplicationState instance;

    private Voter voter;
    private Vote vote;
    private User user;

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public Vote getVote() {
        return vote;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public static ApplicationState getInstance() {
        if (instance == null) {
            instance = new ApplicationState();
        }
        return ApplicationState.instance;
    }
}

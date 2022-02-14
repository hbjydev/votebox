package moe.hayden.votebox.models;

public class Ballot {
    public int id;
    public Voter voter;
    public Vote vote;
    public String option;

    public Ballot(Voter voter, Vote vote, String option) {
        this.voter = voter;
        this.vote = vote;
        this.option = option;
    }

    /**
     * Saves this instance of the Vote to the database.
     *
     * @throws Exception Any database error that saving incurs
     */
    public void save() throws Exception {
        // TODO implement entity saving
    }
}

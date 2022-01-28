package moe.hayden.votebox.models;

public class Vote {
    public int id;

    public String name;
    public String description;

    public String options;

    public Vote(String name, String description, String options) {
        this.name = name;
        this.description = description;
        this.options = options;
    }

    public void castVote(Voter voter, String option) throws Exception {
        var ballot = new Ballot(voter, this, option);
        ballot.save();
    }
}
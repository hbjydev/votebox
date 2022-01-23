package moe.hayden.votebox.models;

import moe.hayden.votebox.Connection;

import java.util.Arrays;
import java.util.Optional;

public class Vote {
    public int id;

    public String name;
    public String description;

    public String[] options;

    /**
     * Finds an instance of a Vote by its ID in the database.
     *
     * @param id The ID to look up in the database
     * @return The vote found (if any)
     */
    public static Optional<Vote> find(int id) {
        // TODO pull vote by ID from database
        var vote = new Vote();
        return Optional.of(vote);
    }

    public void castVote(Voter voter, String option) throws Exception {
        if (!Arrays.asList(options).contains(option)) {
            // TODO: handle invalid option
            return;
        }

        var ballot = new Ballot(voter, this, option);
        ballot.save();
    }

    /**
     * Saves this instance of the Vote to the database.
     *
     * @throws Exception Any database error that saving incurs
     */
    public void save() throws Exception {
        Connection conn = Connection.getInstance();
        conn.select("select * from votes");
    }
}
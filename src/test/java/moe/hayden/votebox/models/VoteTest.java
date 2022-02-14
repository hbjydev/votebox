package moe.hayden.votebox.models;

import moe.hayden.votebox.exceptions.InvalidBallotException;
import moe.hayden.votebox.repositories.BallotRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VoteTest {
    @Test
    public void test_ballot_validation_fails_on_invalid_value()
    {
        Voter voter = new Voter("ABC123");
        Vote vote = new Vote("Test Vote", "A vote for testing", "Option 1;Option 3");
        Exception ex = assertThrows(
            InvalidBallotException.class, () -> vote.castVote(voter, "Option 2")
        );
        assertEquals("Invalid ballot value!", ex.getMessage());
    }

    @Test
    public void test_ballot_validation_succeeds_on_valid_value() throws Exception {
        Voter voter = new Voter("ABC123");
        Vote vote = new Vote("Test Vote", "A vote for testing", "Option 1;Option 3");
        vote.castVote(voter, "Option 1");
    }
}

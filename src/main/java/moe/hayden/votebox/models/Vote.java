package moe.hayden.votebox.models;

import moe.hayden.votebox.exceptions.InvalidBallotException;
import moe.hayden.votebox.repositories.BallotRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        var valid = validateBallot(option);
        if (!valid) {
            throw new InvalidBallotException("Invalid ballot value!");
        }

        var ballot = new Ballot(voter, this, option);
        var repo = BallotRepository.getInstance();

        try {
            repo.create(ballot);
            System.out.println("Voter " + voter.registration + " voted " + option);
        } catch (Exception err) {
            System.out.println("[ERROR] " + err.getMessage());
            throw err;
        }
    }

    /**
     * Validates that the provided ballot is a valid option in the context of
     * the current list.
     * @param option The provided option
     * @return If the option is valid
     */
    private boolean validateBallot(String option) {
        List<String> list = Arrays.asList(getOptions());
        Predicate<String> valid = opt -> opt.equals(option);
        var result = list.stream().filter(valid).collect(Collectors.toList());
        return result.size() > 0;
    }

    public String[] getOptions() {
        return options.split(";");
    }
}
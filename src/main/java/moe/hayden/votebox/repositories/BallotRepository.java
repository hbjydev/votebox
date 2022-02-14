package moe.hayden.votebox.repositories;

import moe.hayden.votebox.models.Ballot;
import moe.hayden.votebox.models.Vote;
import moe.hayden.votebox.models.Voter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BallotRepository {
    private static BallotRepository instance;

    private final List<Ballot> data = new ArrayList<>();

    public void create(Ballot ballot) {
        data.add(ballot);
    }

    public List<Ballot> findByVote(Vote vote) {
        return data.stream().filter(b -> b.vote.equals(vote)).collect(Collectors.toList());
    }

    public static BallotRepository getInstance() {
        if (BallotRepository.instance == null) {
            BallotRepository.instance = new BallotRepository();
        }
        return BallotRepository.instance;
    }
}

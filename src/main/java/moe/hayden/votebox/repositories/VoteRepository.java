package moe.hayden.votebox.repositories;

import moe.hayden.votebox.models.Vote;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoteRepository {
    private static VoteRepository instance;

    private final List<Vote> data = new ArrayList<>();

    public VoteRepository() {
        data.add(new Vote("UKGE 42069", "UK General Elections #42069", "{}"));
        data.add(new Vote("Sheffield Ice Creams", null, "{}"));
    }

    public List<Vote> getAll() {
        return data;
    }

    public Optional<Vote> findByName(String name) {
        return data.stream()
                .filter(vote -> name.equals(vote.name))
                .findFirst();
    }

    public static VoteRepository getInstance() {
        if (VoteRepository.instance == null) {
            VoteRepository.instance = new VoteRepository();
        }
        return VoteRepository.instance;
    }
}

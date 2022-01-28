package moe.hayden.votebox.repositories;

import moe.hayden.votebox.models.Voter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoterRepository {
    private static VoterRepository instance;

    private final List<Voter> data = new ArrayList<>();

    public VoterRepository() {
        data.add(new Voter("ABC123"));
        data.add(new Voter("123ABC"));
        data.add(new Voter("DEF456"));
        data.add(new Voter("456DEF"));
    }

    public List<Voter> getAll() {
        return data;
    }

    public Optional<Voter> findByRegistration(String registration) {
        return data.stream()
            .filter(voter -> registration.equals(voter.registration))
            .findFirst();
    }

    public static VoterRepository getInstance() {
        if (VoterRepository.instance == null) {
            VoterRepository.instance = new VoterRepository();
        }
        return VoterRepository.instance;
    }
}

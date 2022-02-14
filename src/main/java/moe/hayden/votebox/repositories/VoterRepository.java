package moe.hayden.votebox.repositories;

import javafx.fxml.FXML;
import moe.hayden.votebox.SQLite;
import moe.hayden.votebox.models.Voter;

import java.sql.SQLException;
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

    public List<Voter> getAll() throws SQLException {
        String sql = "select id, registration from voter";
        var conn = SQLite.connect();
        var ps = conn.prepareStatement(sql);
        var rs = ps.executeQuery();
        List<Voter> list = new ArrayList<>();
        while (rs.next()) {
            var vt = new Voter(rs.getString("registration"));
            vt.id = rs.getInt("id");
            list.add(vt);
        }
        conn.close();
        return list;
    }

    public Optional<Voter> findByRegistration(String registration) {
        return data.stream()
            .filter(voter -> registration.equals(voter.registration))
            .findFirst();
    }

    public void create(Voter voter) throws SQLException {
        String sql = "insert into voter(registration) values (?) returning *";

        var conn = SQLite.connect();
        var ps = conn.prepareStatement(sql);
        ps.setString(1, voter.registration);
        var rs = ps.executeQuery();
        voter.id = rs.getInt(1);
        conn.close();
    }

    public static VoterRepository getInstance() {
        if (VoterRepository.instance == null) {
            VoterRepository.instance = new VoterRepository();
        }
        return VoterRepository.instance;
    }
}

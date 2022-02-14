package moe.hayden.votebox.repositories;

import moe.hayden.votebox.SQLite;
import moe.hayden.votebox.models.Vote;
import moe.hayden.votebox.models.Voter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoteRepository {
    private static VoteRepository instance;

    private final List<Vote> data = new ArrayList<>();

    public VoteRepository() {
        data.add(new Vote("UKGE 42069", "UK General Elections #42069", "Conservative;Labour;Liberal Democrats"));
        data.add(new Vote("Sheffield Ice Creams", null, "Chocolate;Vanilla;Strawberry"));
    }

    public void create(Vote vote) throws SQLException {
        String sql = "insert into vote(name, description, options) values (?, ?, ?) returning *";
        var conn = SQLite.connect();
        var ps = conn.prepareStatement(sql);
        ps.setString(1, vote.name);
        ps.setString(2, vote.description);
        ps.setString(3, vote.options);
        var rs = ps.executeQuery();
        vote.id = rs.getInt(1);
        conn.close();
    }

    public void update(Vote vote) throws SQLException {
        String sql = "update vote set name = ?, description = ?, options = ? where id = ?";
        var conn = SQLite.connect();
        var ps = conn.prepareStatement(sql);
        ps.setString(1, vote.name);
        ps.setString(2, vote.description);
        ps.setString(3, vote.options);
        ps.executeUpdate();
        conn.close();
    }

    public List<Vote> getAll() throws SQLException {
        String sql = "select id, name, description, options from vote";
        var conn = SQLite.connect();
        var ps = conn.prepareStatement(sql);
        var rs = ps.executeQuery();
        List<Vote> list = new ArrayList<>();
        while (rs.next()) {
            var vt = new Vote(rs.getString("name"), rs.getString("description"), rs.getString("options"));
            vt.id = rs.getInt("id");
            list.add(vt);
        }
        conn.close();
        return list;
    }

    public Vote findByName(String name) throws SQLException {
        String sql = "select id, name, description, options from vote where name = ? limit 1";
        var conn = SQLite.connect();
        var ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        var rs = ps.executeQuery();
        var vote = new Vote(rs.getString("name"), rs.getString("description"), rs.getString("options"));
        conn.close();
        return vote;
    }

    public static VoteRepository getInstance() {
        if (VoteRepository.instance == null) {
            VoteRepository.instance = new VoteRepository();
        }
        return VoteRepository.instance;
    }
}

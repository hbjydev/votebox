package moe.hayden.votebox.repositories;

import moe.hayden.votebox.SQLite;
import moe.hayden.votebox.models.Ballot;
import moe.hayden.votebox.models.Vote;
import moe.hayden.votebox.models.Voter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BallotRepository {
    private static BallotRepository instance;

    private final List<Ballot> data = new ArrayList<>();

    public void create(Ballot ballot) throws SQLException {
        String sql = "insert into ballot(voter_id, vote_id, option) values (?, ?, ?) returning id";
        var conn = SQLite.connect();
        var ps = conn.prepareStatement(sql);
        ps.setInt(1, ballot.voter.id);
        ps.setInt(2, ballot.vote.id);
        ps.setString(3, ballot.option);
        var rs = ps.executeQuery();
        ballot.id = rs.getInt("id");
        conn.close();
    }

    public List<Ballot> findByVote(Vote vote) throws SQLException {
        String sql = "select id, option from ballot where vote_id = ?";
        var conn = SQLite.connect();
        var ps = conn.prepareStatement(sql);
        ps.setInt(1, vote.id);
        var rs = ps.executeQuery();
        List<Ballot> list = new ArrayList<>();
        while (rs.next()) {
            var voter = new Voter("");
            var vote2 = new Vote("", "", "");
            var bt = new Ballot(voter, vote2, rs.getString("option"));
            bt.id = rs.getInt("id");
            list.add(bt);
        }
        conn.close();
        return list;
    }

    public static BallotRepository getInstance() {
        if (BallotRepository.instance == null) {
            BallotRepository.instance = new BallotRepository();
        }
        return BallotRepository.instance;
    }
}

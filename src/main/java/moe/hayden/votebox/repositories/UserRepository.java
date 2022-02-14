package moe.hayden.votebox.repositories;

import moe.hayden.votebox.enums.UserRole;
import moe.hayden.votebox.models.User;
import moe.hayden.votebox.models.Vote;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static UserRepository instance;

    private final List<User> data = new ArrayList<>();

    public UserRepository() {
        data.add(new User("hayden", "admin", UserRole.ADMIN));
        data.add(new User("joe", "audit", UserRole.AUDITOR));
        data.add(new User("ian", "audit", "AUDITOR"));
    }

    public List<User> getAll() {
        return data;
    }

    public Optional<User> findByName(String username) {
        return data.stream()
                .filter(u -> u.username.equals(username))
                .findFirst();
    }

    public static UserRepository getInstance() {
        if (UserRepository.instance == null) {
            UserRepository.instance = new UserRepository();
        }
        return UserRepository.instance;
    }
}

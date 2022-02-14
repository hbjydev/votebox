package moe.hayden.votebox.providers;

import moe.hayden.votebox.exceptions.InvalidUserException;
import moe.hayden.votebox.repositories.UserRepository;

/** Provides authentication methods to the rest of the application */
public class AuthenticationProvider {
    private UserRepository userRepo;

    public AuthenticationProvider() {
        userRepo = UserRepository.getInstance();
    }

    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Attempts to log in to the system with a given username and password.
     * @param username The username
     * @param password The password
     * @throws InvalidUserException The user does not exist.
     */
    public boolean login(String username, String password) throws InvalidUserException {
        // find the user by their username
        var userOpt = userRepo.findByName(username);

        // if they don't exist, throw an error
        if (userOpt.isEmpty()) throw new InvalidUserException();

        // get the user object
        var user = userOpt.get();

        // return a boolean based on if the password is valid
        return user.password.equals(password);
    }

}

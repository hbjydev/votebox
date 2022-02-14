package moe.hayden.votebox.providers;

import moe.hayden.votebox.enums.UserRole;
import moe.hayden.votebox.exceptions.InvalidUserException;
import moe.hayden.votebox.models.User;
import moe.hayden.votebox.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationProviderTest {
    @Mock
    UserRepository userRepository;

    @Test
    public void test_authenticates_successfully() throws InvalidUserException {
        when(userRepository.findByName("user1")).thenReturn(
            Optional.of(new User("user1", "p4ssw0rd", UserRole.ADMIN))
        );
        var authProvider = new AuthenticationProvider();
        authProvider.setUserRepo(userRepository);

        assertTrue(authProvider.login("user1", "p4ssw0rd"));
    }

    @Test
    public void test_authentication_failure() throws InvalidUserException {
        when(userRepository.findByName("user2")).thenReturn(
                Optional.of(new User("user2", "p4ssw0rd", UserRole.ADMIN))
        );
        var authProvider = new AuthenticationProvider();
        authProvider.setUserRepo(userRepository);

        assertFalse(authProvider.login("user2", "p4ssw1rd")); // use an invalid password
    }

    @Test
    public void test_authentication_invalid_user() {
        when(userRepository.findByName("user3")).thenReturn(Optional.empty());
        var authProvider = new AuthenticationProvider();
        authProvider.setUserRepo(userRepository);

        assertThrows(
            InvalidUserException.class,
            () -> authProvider.login("user3", "p4ssw0rd")
        );
    }
}

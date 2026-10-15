import org.example.dto.UserCreateRequest;
import org.example.dto.UserResponseDto;
import org.example.model.User;
import org.example.model.UserRole;
import org.example.repository.UserRepository;
import org.example.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private UserCreateRequest request;

    @BeforeEach
    void setUp(){
        request = new UserCreateRequest();

        request.setName("Aziz");
        request.setEmail("aziz@gmail.com");
        request.setPassword("aziz1234");
        request.setPhone("0645784512");
    }

    @Test
    void createUser_shouldEncodedPasswordAndSaveUser(){
        when(passwordEncoder.encode("aziz1234")).thenReturn("encoded-password");

        UserResponseDto response = authService.createUser(request);

        assertNotNull(response);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(captor.capture());

        User savedUser = captor.getValue();

        assertEquals("Aziz", savedUser.getName());
        assertEquals("aziz@gmail.com", savedUser.getEmail());
        assertEquals("encoded-password", savedUser.getPassword());
        assertEquals("0645784512", savedUser.getPhone());
        assertEquals(UserRole.USER, savedUser.getRole());
        assertTrue(savedUser.isActive());

        verify(passwordEncoder).encode("aziz1234");
        verifyNoMoreInteractions(userRepository, passwordEncoder);


    }
}

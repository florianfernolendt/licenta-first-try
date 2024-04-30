package ro.upt.ac.info.licenta;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepo userRepo;

@Override
public String login(String email, String parola) {
    Optional<User> user = userRepo.findById(email);
    if (user.isPresent() && user.get().getParola().equals(parola)) {
        return user.get().getRol();
    } else {
        return null;
    }
}
}

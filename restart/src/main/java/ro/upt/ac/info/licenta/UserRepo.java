package ro.upt.ac.info.licenta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    //String login(String email, String pass);
    //String findByEmail(String email);
}

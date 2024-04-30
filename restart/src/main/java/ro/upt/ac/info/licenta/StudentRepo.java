package ro.upt.ac.info.licenta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, String> {
    Student findByemail(String email);
}

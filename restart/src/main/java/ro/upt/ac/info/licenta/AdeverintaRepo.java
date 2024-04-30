package ro.upt.ac.info.licenta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdeverintaRepo extends JpaRepository<Adeverinta, String> {
    Adeverinta findByNumar(String numar);
    //Adeverinta findBystatus(Status s);
}

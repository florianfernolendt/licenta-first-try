package ro.upt.ac.info.licenta;

import java.util.Collection;
import java.util.List;

public interface AdeverintaService {
    Adeverinta create(Adeverinta adeverinta);
    Adeverinta aprobare_secretar(String numar);
    Adeverinta aprobare_decan(String numar);

    Adeverinta respingere(String numar);
    Collection<Adeverinta> list(int limit);

    List<Adeverinta> listAllAdeverinte();

    //Collection<Adeverinta> listbystatus(Status s);
    Adeverinta get(String numar);
    Boolean delete(String numar);
}

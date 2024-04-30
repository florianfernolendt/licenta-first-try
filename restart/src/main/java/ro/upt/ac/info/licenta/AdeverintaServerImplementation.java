package ro.upt.ac.info.licenta;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AdeverintaServerImplementation implements AdeverintaService{
    private final AdeverintaRepo adeverintaRepo;
    @Override
    public Adeverinta create(Adeverinta adeverinta) {
        log.info("Salvare adeverinta nr: {}", adeverinta.getNumar());
        adeverinta.setStatus(Status.VALIDARE_SECRETAR);
        return adeverintaRepo.save(adeverinta);
    }

    @Override
    public Adeverinta aprobare_secretar(String numar) {
        log.info("Adeverinta nr {} a fost aprobata de secretar", numar);
        Adeverinta adv = adeverintaRepo.findByNumar(numar);
        if (adv.getStatus() == Status.VALIDARE_SECRETAR) {
            adv.setStatus(Status.VALIDARE_DECAN);
            adeverintaRepo.save(adv);
        }
        return adv;
    }

    @Override
    public Adeverinta aprobare_decan(String numar) {
        log.info("Adeverinta nr {} a fost aprobata de decan", numar);
        Adeverinta adv = adeverintaRepo.findByNumar(numar);
        if (adv.getStatus() == Status.VALIDARE_DECAN) {
            adv.setStatus(Status.VALIDAT);
            adeverintaRepo.save(adv);
        }
        return adv;
    }

    @Override
    public Adeverinta respingere(String numar) {
        Adeverinta adv = adeverintaRepo.findByNumar(numar);
        adv.setStatus(Status.RESPINS);
        adeverintaRepo.save(adv);
        return adv;
    }

    @Override
    public Collection<Adeverinta> list(int limit) {
        log.info("Strangere toate adeverintele");
        return adeverintaRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public List<Adeverinta> listAllAdeverinte() {
        log.info("dfidsjfs");
        return adeverintaRepo.findAll().stream().toList();
    }

    /*
        public Collection<Adeverinta> listbystatus(Status s) {
            log.info("Strangere toate adeverintele");
            return (Collection<Adeverinta>) adeverintaRepo.findBystatus(s);
        }
    */
    @Override
    public Adeverinta get(String numar) {
        log.info("Strangere adresa dupa numar");
        return adeverintaRepo.findByNumar(numar);
    }

    @Override
    public Boolean delete(String numar) {
        log.info("Stergere adresa dupa numar");
        adeverintaRepo.deleteById(numar);
        return Boolean.TRUE;
    }
}

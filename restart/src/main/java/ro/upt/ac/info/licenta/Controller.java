package ro.upt.ac.info.licenta;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adeverinta")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class Controller {
    private final AdeverintaServerImplementation adeverintaservice;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    private Generator generator;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> logare(@RequestBody LoginInfo info) {
        String rol = userService.login(info.getEmail(), info.getParola());
        Map<String, String> response = new HashMap<>();
        response.put("role", rol);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list_all")
    public ResponseEntity<Response> getadeverinte() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("adeverinte", adeverintaservice.list(50)))
                        .message("Adeverinte stranse")
                        .status(HttpStatus.OK)
                        .statuscode(HttpStatus.OK.value())
                        .build()
        );

    }

    @GetMapping("/toate")
    public List<Adeverinta> returntoate() {
        return adeverintaservice.listAllAdeverinte();
    }

    @GetMapping("/test")
    public String test() throws MessagingException {
        this.emailService.trimitere("marius.ivan-fernolendt@student.upt.ro", "Adeverinta", "Adeverinta test", "/Users/florian/Desktop/Licenta/Adeverinta.pdf");
        return "trimis";
    }

    /*
        @GetMapping("/liststatus/{status}")
        public ResponseEntity<Response> getadeverintebystatus(@PathVariable("status") Status status) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("adeverinte", adeverintaservice.listbystatus(status)))
                            .message("Adeverinte stranse")
                            .status(HttpStatus.OK)
                            .statuscode(HttpStatus.OK.value())
                            .build()
            );
        }
    */
    @PostMapping("/aprobare_secretar/{numar}")
    public ResponseEntity<Response> aprobare_secretar(@PathVariable("numar") String nr) {
        Adeverinta adv = adeverintaservice.aprobare_secretar(nr);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("adeverinta", adv))
                        .message(adv.getStatus() == Status.VALIDARE_DECAN ? "Adeverinta aprobata" : "Nu a mers!")
                        .status(HttpStatus.OK)
                        .statuscode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/aprobare_decan/{numar}")
    public ResponseEntity<Response> aprobare_decan(@PathVariable("numar") String nr) throws MessagingException {//de verificat daca e aprobat de secretar
        Adeverinta adv = adeverintaservice.aprobare_decan(nr);
        Student student = adv.getStudent();

        generator.generare(student.getFacultate(), student.getSpecializare(), adv.getNr(), student.getNume(), "2022-2023", student.getAn(), student.getCursuri(), adv.getMotiv(), adv.getMentiuni());

        this.emailService.trimitere(adv.getEmail(), "Adeverinta", "Adeverinta dvs este atasata acestui email", "/Users/florian/Desktop/Licenta/Adeverinta.pdf");


        //generator.generare("","",5,"","","",4,"","", "");
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("adeverinta", adv))
                        .message(adv.getStatus() == Status.VALIDAT ? "Adeverinta aprobata" : "Nu a mers!")
                        .status(HttpStatus.OK)
                        .statuscode(HttpStatus.OK.value())
                        .build()
        );
    }

/*
    @PostMapping("/adv_noua/{email}/{motiv}/{mentiuni}")
    public String advnoua(@PathVariable("email") String email, @PathVariable("motiv") String motiv, @PathVariable("mentiuni") String mentiuni) {
        Student student = studentRepo.findByemail(email);
        Adeverinta adeverinta = new Adeverinta();
        adeverinta.setStudent(student);
        adeverinta.setMotiv(motiv);
        adeverinta.setMentiuni(mentiuni);
        adeverinta.setStatus(Status.VALIDARE_SECRETAR);
        adeverintaservice.create(adeverinta);
        return "Adeverinta creata";
    }
*/

    @PostMapping("/adv_noua")
    public String advnoua(@RequestBody Cerere cerere) {
        Student student = studentRepo.findByemail(cerere.getEmail());
        Adeverinta adeverinta = new Adeverinta();
        adeverinta.setStudent(student);
        adeverinta.setMotiv(cerere.getMotiv());
        adeverinta.setMentiuni(cerere.getMentiuni());
        adeverinta.setStatus(Status.VALIDARE_SECRETAR);
        adeverintaservice.create(adeverinta);
        return "Adeverinta creata";
    }

    @DeleteMapping("/stergere/{numar}")
    public ResponseEntity<Response> stergere(@PathVariable("numar") String nr) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("sters", adeverintaservice.delete(nr)))
                        .message("adv stearsa")
                        .status(HttpStatus.OK)
                        .statuscode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("respingere/{numar}/{motiv}")
    public ResponseEntity.BodyBuilder respingere(@PathVariable("numar") String nr, @PathVariable("motiv") String motiv) throws MessagingException {
        Adeverinta adv = adeverintaservice.respingere(nr);
        this.emailService.respingere(adv.getEmail(), "Adeverinta", "Adeverinta dvs a fost respinsa. Motivul respingerii este urmatorul: " + motiv);
        return ResponseEntity.ok();
    }

}


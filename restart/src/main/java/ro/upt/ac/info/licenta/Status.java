package ro.upt.ac.info.licenta;

import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;

public enum Status {
    VALIDARE_SECRETAR("VALIDARE SECRETARIAT"),
    VALIDARE_DECAN("VALIDARE DECANAT"),
    VALIDAT("VALIDAT"),
    RESPINS("RESPINS");
    private final String status;
    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}

package ro.upt.ac.info.licenta;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    private String email;
    private String nume;
    private String facultate;
    private String specializare;
    private String cursuri;
    private int an;

    public String getEmail() {
        return this.email;
    }

    public String getNume() {
        return this.nume;
    }

    public String getFacultate(){
        return this.facultate;
    }

    public String getSpecializare(){
        return this.specializare;
    }

    public String getCursuri() {
        return cursuri;
    }

    public int getAn(){
        return this.an;
    }
}

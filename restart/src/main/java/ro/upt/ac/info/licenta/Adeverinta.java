package ro.upt.ac.info.licenta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adeverinta {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String numar;
    private String motiv;
    private String mentiuni;
    private Status status;
    @ManyToOne
    private Student student;

    public String getEmail(){
        return this.student.getEmail();
    }

    public Student getStudent() {
        return this.student;
    }

    public String getNr(){
        return this.numar;
    }

}

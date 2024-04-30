package ro.upt.ac.info.licenta;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String email;
    private String parola;
    private String rol;

    public String getPassword(){
        return this.parola;
    }


}

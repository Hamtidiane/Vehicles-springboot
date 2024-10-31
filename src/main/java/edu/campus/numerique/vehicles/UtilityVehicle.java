package edu.campus.numerique.vehicles;

import jakarta.persistence.Entity;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;




@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class UtilityVehicle extends Vehicle {

    private Integer volumecm3;

    // La méthode `getType` est spécifique, donc elle reste dans la classe.

    @Override
    public String getType() {
        return "UtilityVehicle";
    }
}

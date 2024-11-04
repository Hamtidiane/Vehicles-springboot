package edu.campus.numerique.vehicles;

import jakarta.persistence.Entity;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;




@Getter  //lombok génère automatiquement les méthodes getMoteurCm3 et setMoteurCm3.
@Setter
@AllArgsConstructor // génère un constructeur avec tous les paramètres, y compris ceux de la classe parente Vehicule.
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) //inclut les champs de la classe parente (Vehicule) dans les méthodes equals et hashCode, ce qui est utile pour les comparaisons correctes d'objets.
@Entity
public class Motorcycle extends Vehicle {

    private Integer moteurcm3;

    // La méthode `getType` est spécifique, donc elle reste dans la classe.
    @Override
    public String getType() {
        return "Motorcycle";
    }

}

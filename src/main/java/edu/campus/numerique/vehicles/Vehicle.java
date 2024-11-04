package edu.campus.numerique.vehicles;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.OptBoolean;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Data
@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "car"),
        @JsonSubTypes.Type(value = Motorcycle.class, name = "motorcycle"),
        @JsonSubTypes.Type(value = UtilityVehicle.class, name = "Utilityvehicle")

})
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //immatriculation
    private String registration;


    //marque
    private String brand;

   //modèle
    private String model;

    //couleur
    private String color;

    //kilometrage
    private Double mileage;

    private int tax_horses;

   //prix au kilomètre
    private Double mileagePrice;

    //prix de base
    private Double basePrice;

    // La méthode `getType` est spécifique, donc elle reste dans la classe.
    public abstract String getType();

}

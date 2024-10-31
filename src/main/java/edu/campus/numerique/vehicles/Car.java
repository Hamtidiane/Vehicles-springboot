package edu.campus.numerique.vehicles;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonTypeInfo;



@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Car extends Vehicle {

    @Override
    public String getType() {
        return "Car";
    }
}

package edu.campus.numerique.vehicles.repository;

import edu.campus.numerique.vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // En réalité, @Repository est une spécialisation de @Component. Tout comme @Component, elle permet de déclarer auprès de Spring qu’une classe est un bean à exploiter.
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}

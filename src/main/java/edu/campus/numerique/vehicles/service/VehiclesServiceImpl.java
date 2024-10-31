package edu.campus.numerique.vehicles.service;



import edu.campus.numerique.vehicles.Vehicle;
import edu.campus.numerique.vehicles.repository.VehicleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class VehiclesServiceImpl implements VehiclesService {

    private  VehicleRepository vehicleRepository;

    public VehiclesServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    public List<Vehicle> getAllVehicles() {
        return List.of();
    }

    public Vehicle getVehicleById(Long id) {
        return null;
    }


    public Vehicle saveVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public void deleteVehicle(Long id) {

    }

    public List<Vehicle> getAllVehicules() {
        return vehicleRepository.findAll(); // Récupère tous les véhicules
    }

    public Vehicle getVehiculeById(Long id) {
        Optional<Vehicle> optionalVehicule = vehicleRepository.findById(id);
        return optionalVehicule.orElseThrow(() -> new RuntimeException("Véhicule non trouvé")); // Lève une exception si le véhicule n'est pas trouvé
    }


    public Vehicle saveVehicule(Vehicle vehicule) {
        return vehicleRepository.save(vehicule); // Enregistre ou met à jour le véhicule
    }


    public void deleteVehicule(Long id) {
        vehicleRepository.deleteById(id); // Supprime le véhicule par ID
    }

}

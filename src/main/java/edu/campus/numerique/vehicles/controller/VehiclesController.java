package edu.campus.numerique.vehicles.controller;

import edu.campus.numerique.vehicles.Car;
import edu.campus.numerique.vehicles.Motorcycle;
import edu.campus.numerique.vehicles.UtilityVehicle;
import edu.campus.numerique.vehicles.Vehicle;
import edu.campus.numerique.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
@Tag(name = "Vehicles", description = "API pour la gestion des véhicules")
public class VehiclesController {


    @Autowired
    private VehicleRepository vehicleRepository;

    //private RestTemplate restTemplate = new RestTemplate();

    @Operation(summary = "Récupérer tous les véhicules", description = "Renvoie la liste de tous les véhicules disponibles")
    @ApiResponse(responseCode = "200", description = "Liste de véhicules récupérée avec succès")
    @GetMapping
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }


    @Operation(summary = "Récupérer un véhicule par ID", description = "Renvoie les détails d'un véhicule spécifique par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Véhicule trouvé"),
            @ApiResponse(responseCode = "404", description = "Véhicule non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id){
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isPresent()){
            return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Créer un nouveau véhicule", description = "Ajoute un nouveau véhicule dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Véhicule créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle newVehicle){
            return new ResponseEntity<>(vehicleRepository.save(newVehicle), HttpStatus.CREATED);
    }

    @Operation(summary = "Mettre à jour un véhicule existant", description = "Modifie les informations d'un véhicule existant par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Véhicule mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Véhicule non trouvé")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle){

    return vehicleRepository.findById(id).map(vehicle -> {
        vehicle.setRegistration(updatedVehicle.getRegistration());
        //vehicle.setKind(updatedVehicle.getKind());
        vehicle.setBrand(updatedVehicle.getBrand());
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setColor(updatedVehicle.getColor());
        vehicle.setMileage(updatedVehicle.getMileage());
        vehicle.setMileagePrice(updatedVehicle.getMileagePrice());
        vehicle.setBasePrice(updatedVehicle.getBasePrice());

        if(vehicle instanceof Motorcycle && updatedVehicle instanceof Motorcycle){
            ((Motorcycle) vehicle).setMoteurcm3(((Motorcycle) updatedVehicle).getMoteurcm3());
        }else if(vehicle instanceof UtilityVehicle && updatedVehicle instanceof UtilityVehicle){
            ((UtilityVehicle)vehicle).setVolumecm3(((UtilityVehicle) updatedVehicle).getVolumecm3());
        }

        vehicleRepository.save(vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }).orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @Operation(summary = "Supprimer un véhicule par ID", description = "Supprime un véhicule existant par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Véhicule supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Véhicule non trouvé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable Long id){
        if(vehicleRepository.existsById(id)){
            vehicleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

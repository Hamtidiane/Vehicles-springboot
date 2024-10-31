package edu.campus.numerique.vehicles.controller;

import edu.campus.numerique.vehicles.Vehicle;
import edu.campus.numerique.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {


    @Autowired
    private VehicleRepository vehicleRepository;

    //private RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id){
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isPresent()){
            return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle newVehicle){
            return new ResponseEntity<>(vehicleRepository.save(newVehicle), HttpStatus.CREATED);
    }

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
        vehicleRepository.save(vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }).orElseGet(() -> ResponseEntity.notFound().build() );
    }

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

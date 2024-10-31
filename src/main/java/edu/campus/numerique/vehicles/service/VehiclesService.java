package edu.campus.numerique.vehicles.service;

import edu.campus.numerique.vehicles.Vehicle;

import java.util.List;




public interface VehiclesService {

   List<Vehicle> getAllVehicles();
   Vehicle getVehicleById(Long id);
   Vehicle saveVehicle(Vehicle vehicle);
   Vehicle updateVehicle(Vehicle vehicle);
   void deleteVehicle(Long id);


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author msi
 */
public class Assignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        List<Hospitals> hospitals = new ArrayList<>();
        List<Ambulances> ambulances = new ArrayList<>();
        AlertSystem system = new AlertSystem(hospitals, ambulances);
        
        try{
            BufferedReader hospitalReader = new BufferedReader(new FileReader("src/assignment2/hospitals.txt"));
            String line;
            
            while((line = hospitalReader.readLine()) != null){
                if(!line.isBlank()){
                    String[] parts = line.trim().split(" ");
                    String name = parts[0].replace("_", " ");
                    int capacity = Integer.parseInt(parts[1]);
                    Hospitals hospital = new Hospitals(name, capacity);
                    system.addHospital(hospital);
                    System.out.println("Registered hospital: " + name + " with capacity " + capacity);
                    
                }
                
                
            }
            
            hospitalReader.close();
            
            BufferedReader doctorReader = new BufferedReader(new FileReader("src/assignment2/doctors.txt"));
            while((line = doctorReader.readLine()) != null){
                String[] parts = line.split(" ");
                String name = parts[0].replace("_", " ");
                int efficiency = Integer.parseInt(parts[1]);
                Doctors doctor = new Doctors(name, efficiency);
                if(!system.getHospital().isEmpty()){
                    system.getHospital().get(0).registerDoctor(doctor);
                    System.out.println("Registered doctor: " + name + " with efficiency " + efficiency);
                }
            }
            
            doctorReader.close();
            
            
           BufferedReader ambulancesReader = new BufferedReader(new FileReader("src/assignment2/ambulances.txt"));

           while ((line = ambulancesReader.readLine()) != null) {
                String ID = line.trim();
                Ambulances ambulance = new Ambulances(ID, true, "");
                system.addAmbulance(ambulance);
                System.out.println("Registered ambulances: " + ID);
            }
           
            ambulancesReader.close();

            
            BufferedReader patientsReader = new BufferedReader(new FileReader("src/assignment2/patients.txt"));
            while((line = patientsReader.readLine()) != null){
                String[] parts = line.split(" ");
                String name = parts[0];
                int severityLevel = Integer.parseInt(parts[1]);
                Patient patient = new Patient(name, severityLevel);
                system.requestTransport(patient);
                System.out.println("Patient " + name + " severity level " + severityLevel);
            }
            
            patientsReader.close();
                    
                    
        }
        catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
        
        system.treatAllHospitals();

    }
    
}

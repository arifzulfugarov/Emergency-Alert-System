package assignment2;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * Represents a hospital that can register patients and doctors,
 * and provide treatment using its available medical staff.
 * The hospital has a limited capacity for patients.
 * 
 * @author msi
 */
public class Hospitals {

    private String name;
    private int capacity;
    List<Patient> patients = new ArrayList<>();
    List<Doctors> doctors = new ArrayList<>();

    /**
     * Constructs a hospital with the specified name and patient capacity.
     * 
     * @param name the name of the hospital
     * @param capacity the maximum number of patients the hospital can hold
     */
    public Hospitals(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }

    public List<Doctors> getDoctors() {
        return doctors;
    }

    public String getName(){
        return name;
    }

    public String getHospitalName(){
        return name;
    }

    public int getCapacity(){
        return capacity;
    }

    /**
     * Registers a patient in the hospital if capacity allows.
     * 
     * @param patient the patient to register
     * @return true if the patient was registered, false if the hospital is full
     */
    public boolean registerPatient(Patient patient){
        if(patients.size() < capacity ){
            patients.add(patient);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Registers a doctor to the hospital's medical staff.
     * 
     * @param doctor the doctor to add
     */
    public void registerDoctor(Doctors doctor){
        doctors.add(doctor);
    }

    /**
     * Provides care to all untreated patients using the available doctors.
     * Each patient is treated by every doctor until fully recovered.
     */
    public void careforalluntreatedpatients(){
        for(Patient patient : patients){
            if(!patient.fullyRecovered()){
                for(Doctors doctor : doctors){
                    if(!patient.fullyRecovered()){
                        doctor.treat(patient);
                    }
                }
            }
        }
    }

    public List<Patient> getPatients(){
        return patients;
    }
}

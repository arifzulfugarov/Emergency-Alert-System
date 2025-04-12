/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages coordination between hospitals, ambulances, and patients.
 * Handles registration, transportation, treatment, and waiting lists.
 * Facilitates communication between emergency components in the system.
 * 
 * @author msi
 */
public class AlertSystem {
    
    private List<Hospitals> hospitals = new ArrayList<>();
    private List<Ambulances> ambulances = new ArrayList<>();
    private List<Patient> waitingPatients = new ArrayList<>();

    /**
     * Constructs an AlertSystem with given hospitals and ambulances.
     * 
     * @param hospitals the list of hospitals to manage
     * @param ambulances the list of ambulances to manage
     */
    public AlertSystem(List<Hospitals> hospitals, List<Ambulances> ambulances){
        this.hospitals = hospitals;
        this.ambulances = ambulances;
    }

    /**
     * Adds a new hospital to the system.
     * 
     * @param hospital the hospital to add
     */
    public void addHospital(Hospitals hospital){
        hospitals.add(hospital);
    }

    /**
     * Adds a new ambulance to the system.
     * 
     * @param ambulance the ambulance to add
     */
    public void addAmbulance(Ambulances ambulance){
        ambulances.add(ambulance);
    }

    /**
     * Finds the first hospital with available capacity.
     * 
     * @return a hospital with space for a patient, or null if all are full
     */
    public Hospitals findAvailableHospital(){
        for(Hospitals hospital : hospitals){
            if(hospital.getPatients().size() < hospital.getCapacity()){
                return hospital;
            }
        }
        return null;
    }

    /**
     * Finds the first available ambulance capable of transport.
     * 
     * @return an available ambulance, or null if all are busy
     */
    public Ambulances findAvailableAmbulance(){
        for(Ambulances a : ambulances){
            if(a.canTransport()){
                return a;
            }
        }
        return null;
    }

    /**
     * Requests transportation for a patient to a hospital.
     * Adds the patient to the waiting list if transport or capacity is unavailable.
     * 
     * @param patient the patient needing transport
     */
    public void requestTransport(Patient patient) {
        Ambulances ambulance = findAvailableAmbulance();

        if (ambulance == null) {
            System.out.println("Transport request for " + patient.getName() + " delayed (no available ambulance).");
            waitingPatients.add(patient); 
            return;
        }

        for (Hospitals hospital : hospitals) {
            if (hospital.registerPatient(patient)) {
                ambulance.setDestination(hospital.getName());
                ambulance.transport(patient);
                return;
            }
        }

        System.out.println("Transport request for " + patient.getName() + " delayed (no hospital with capacity).");
        waitingPatients.add(patient);  
    }

    /**
     * Sends all untreated patients in hospitals for care.
     * Releases all ambulances and reprocesses the waiting list.
     */
    public void treatAllHospitals() {
        for (Hospitals hospital : hospitals) {
            hospital.careforalluntreatedpatients();
        }

        for (Ambulances ambulance : ambulances) {
            ambulance.release();
        }

        List<Patient> stillWaiting = new ArrayList<>();
        for (Patient patient : waitingPatients) {
            requestTransport(patient);
            if (!patient.fullyRecovered()) {
                stillWaiting.add(patient);
            }
        }

        waitingPatients = stillWaiting; 
    }

    public List<Hospitals> getHospital(){
        return hospitals;
    }

    public List<Ambulances> getAmbulances(){
        return ambulances;
    }
}

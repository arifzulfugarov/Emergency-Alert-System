/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package assignment2;

/**
 * Represents an ambulance capable of transporting patients to hospitals.
 * Each ambulance has an ID, destination, and transport availability status.
 * 
 * @author msi
 */
public class Ambulances {
    
    private boolean canTransport;
    private String destination;
    private String ID;

    /**
     * Constructs an Ambulance with the given ID, transport availability, and destination.
     * 
     * @param ID unique identifier of the ambulance
     * @param canTransport whether the ambulance is currently available for transport
     * @param destination initial destination hospital name
     */
    public Ambulances(String ID, boolean canTransport, String destination){
        this.canTransport = canTransport;
        this.destination = destination;
        this.ID = ID;
    }

    public String getDest(){
        return destination;
    }

    public String getID(){
        return ID;
    }

    /**
     * Attempts to transport a patient to the ambulance's destination.
     * Prints status based on patient recovery and ambulance availability.
     * 
     * @param patient the patient to transport
     */
    public void transport(Patient patient){
        if (!patient.fullyRecovered() && canTransport) {
            System.out.println("Ambulance " + ID + " is transporting " + patient.getName() + " to " + destination + ".");
            canTransport = false;
        } else if (!canTransport) {
            System.out.println("Ambulance " + ID + " is currently unavailable.");
        } else {
            System.out.println(patient.getName() + " is already recovered and doesn't need transport.");
        }
    }

    public boolean canTransport(){
        return canTransport;
    }

    /**
     * Sets a new destination hospital for the ambulance.
     * 
     * @param destination the hospital name to set as the destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Releases the ambulance, marking it as available for new transport tasks.
     */
    public void release() {
        this.canTransport = true;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

/**
 * Represents a doctor who can treat patients by reducing their severity level.
 * Each doctor has a name and an efficiency level that determines how much they can heal.
 * 
 * @author msi
 */
public class Doctors {

    private String name;
    private int efficiencyLevel;

    /**
     * Constructs a Doctor with the specified name and efficiency level.
     * 
     * @param name the name of the doctor
     * @param efficiencyLevel how effective the doctor is at treating patients
     */
    public Doctors(String name, int efficiencyLevel){
        this.name = name;
        this.efficiencyLevel = efficiencyLevel;
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return efficiencyLevel;
    }

    /**
     * Treats a patient by reducing their severity level based on this doctor's efficiency.
     * 
     * @param patient the patient to treat
     */
    public void treat(Patient patient){
        patient.reduceSeverity(efficiencyLevel);
    }
}

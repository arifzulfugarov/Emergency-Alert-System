/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

/**
 * Represents a patient with a name, severity level, and recovery status.
 * Patients can recover gradually through treatment from doctors.
 * 
 * @author msi
 */
public class Patient {

    private String name;
    private int severityLevel;
    private boolean fullyRecovered;

    /**
     * Constructs a patient with a given name and severity level.
     * 
     * @param name the name of the patient
     * @param severityLevel the initial severity of the patient's condition
     */
    public Patient(String name, int severityLevel){
        this.name = name;
        this.severityLevel = severityLevel;
    }

    public int getSeverityLevel(){
        return severityLevel;
    }

    /**
     * Reduces the severity of the patient's condition by a given amount.
     * If severity reaches zero or less, the patient is marked as fully recovered.
     * 
     * @param amount the amount to reduce from the severity level
     */
    public void reduceSeverity(int amount){
        if (!fullyRecovered && severityLevel > 0) {
            severityLevel -= amount;            
            if (severityLevel <= 0) {
                severityLevel = 0;
                fullyRecovered = true;
            }
        }       
    }

    public String getName(){
        return name;
    }

    public boolean fullyRecovered(){    
        return fullyRecovered;
    }
}

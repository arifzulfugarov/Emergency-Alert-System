/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assignment2;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msi
 */
public class AlertSystemTest {
    
    
    private Hospitals hospital;
    private Patient p1,p2,p3;
    private Doctors doctor1, doctor2;
    private AlertSystem system;
    private Ambulances a1, a2;
    
    @Before
    public void setUp(){
        
        List<Hospitals> hospitals = new ArrayList<>();
        List<Ambulances> ambulances = new ArrayList<>();
        
        system = new AlertSystem(hospitals, ambulances);
        hospital = new Hospitals("Test Hospital", 3);
        system.addHospital(hospital);

        p1 = new Patient("Alice", 5);
        p2 = new Patient("Bob", 4);
        p3 = new Patient("Charlie", 3);
        
        
        doctor1 = new Doctors("Dr. Smith", 2);
        doctor2 = new Doctors("Dr. John", 3);
//        hospital.registerDoctor(doctor1);
//        hospital.registerDoctor(doctor2);
        
        a1 = new Ambulances("A1", true, "");
        a2 = new Ambulances("A2", true, "");
        
        system.addAmbulance(a1);
        system.addAmbulance(a2); 
        
    }
    
    @Test
    public void testRegisteredPatients(){
        assertTrue(hospital.registerPatient(p1));
        assertTrue(hospital.registerPatient(p2));
        assertTrue(hospital.registerPatient(p3));  
    }
    
    @Test
    public void testRegisterDoctor(){
        hospital.registerDoctor(doctor1);
        hospital.registerDoctor(doctor2);
        List<Doctors> doctors = hospital.getDoctors();
        assertEquals(2, doctors.size());
        assertEquals("Dr. Smith", doctors.get(0).getName());
        
    }
    
    @Test
    public void testCareForAllUntreatedPatients(){
        hospital.registerDoctor(doctor1);
        hospital.registerPatient(p1);
        hospital.careforalluntreatedpatients();
        assertTrue(p1.getSeverityLevel() < 5);
    }
    
    @Test 
    public void testGetCapacity(){
        assertEquals(3, hospital.getCapacity());
    }
    
    @Test
    public void testGetName(){
        assertEquals("Test Hospital", hospital.getHospitalName());
    }
    
    @Test
    public void testTransport(){
        system.requestTransport(p1);
        system.requestTransport(p2);
        assertEquals(2, hospital.getPatients().size());
    }
    
    @Test
    public void testReduceSeverity(){
        p1.reduceSeverity(2);
        assertEquals(3, p1.getSeverityLevel());
        assertFalse(p1.fullyRecovered());
    }
    
    
    @Test
    public void testReduceSeverityFull(){
        p2.reduceSeverity(10);
        assertEquals(0, p2.getSeverityLevel());
        assertTrue(p2.fullyRecovered());
    }
    
    @Test
    public void testTreat(){
        doctor1.treat(p1);
        assertEquals(3, p1.getSeverityLevel());
        assertFalse(p1.fullyRecovered());
        
    }
    
    @Test
    public void testRelease(){
        a1.release();
        assertTrue(a1.canTransport());
    }
    
    @Test
    public void testCanTransport(){
        Ambulances a3 = new Ambulances("a3", false, "");
        Ambulances a4 = new Ambulances("a4", true, "");
        assertFalse(a3.canTransport());
        assertTrue(a4.canTransport());
    }
    
    @Test
    public void testTreatAllHospitals(){
         hospital.registerDoctor(doctor1); 
        hospital.registerDoctor(doctor2);  
        hospital.registerPatient(p1);      

        
        system.treatAllHospitals();

        
        assertTrue(p1.fullyRecovered());

     
        assertTrue(a1.canTransport());
        assertTrue(a2.canTransport());
            
    }
    
    
    
    
      
    
}

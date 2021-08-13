package com.company.Controllers;

import com.company.DbHelper.DbConnection;
import com.company.Objects.Doctors;
import com.company.Objects.Patients;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalController {
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean addNewPatient(){

        System.out.print("Enter the patient name: ");
        String name = scanner.next();

        System.out.print("Enter the patient age: ");
        int age = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter the patient diagnosis: ");
        String diagnosis = scanner.nextLine();


        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO patients(name, age, diagnosis) VALUES('" + name + "', " + age + ", '" + diagnosis + "')");
            ps.execute();
            System.out.println("Successfully added new patient");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add new patient");
            return false;
        }

    }

    public static void deletePatient() {
        System.out.print("Enter patient id to delete: ");
        int id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("DELETE FROM patients WHERE id=" + id);
            ps.execute();
            System.out.println("Successfully deleted patient");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete patient");
        }
    }

    public static boolean addNewDoctor(){

        System.out.print("Enter the doctor name: ");
        String name = scanner.next();

        scanner.nextLine();

        System.out.print("Enter the doctor speciality: ");
        String speciality = scanner.nextLine();


        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO doctors(name, speciality) VALUES('" + name + "', '" + speciality + "')");
            ps.execute();
            System.out.println("Successfully added new doctor");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add new doctor");
            return false;
        }

    }

    public static void deleteDoctor() {
        System.out.print("Enter doctor id to delete: ");
        int id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("DELETE FROM doctors WHERE id=" + id);
            ps.execute();
            System.out.println("Successfully deleted doctor");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete doctor");
        }
    }

    public static boolean addNewDrug(){

        System.out.print("Enter the drug name: ");
        String name = scanner.nextLine();


        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO drugs(name) VALUES('" + name + "')");
            ps.execute();
            System.out.println("Successfully added new drug");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add new drug");
            return false;
        }

    }

    public static void deleteDrug() {
        System.out.print("Enter drug id to delete: ");
        int id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("DELETE FROM drugs WHERE id=" + id);
            ps.execute();
            System.out.println("Successfully deleted drug");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete drug");
        }
    }

    public static Patients getPatientById(){
        System.out.print("Enter the patient id to see: ");
        int id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM patients WHERE id=" + id);
            rs = ps.executeQuery();

            int patientId, age;
            String name, diagnosis;

            Patients patient = new Patients();

            while (rs.next()){
                patientId = rs.getInt("id");
                name = rs.getString("name");
                age = rs.getInt("age");
                diagnosis = rs.getString("diagnosis");
                patient.setId(patientId);
                patient.setName(name);
                patient.setAge(age);
                patient.setDiagnosis(diagnosis);
                System.out.println("The patient is " + name +
                        " and the diagnosis is " + diagnosis);
            }
            return patient;


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get the patient");
            return null;
        }

    }

    public static void assignPatientToDoctor() {
        System.out.print("Enter patient's id for assigning to a doctor: ");
        int patient_id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM patients WHERE id=" + patient_id);
            rs = ps.executeQuery();

            int patientId;
            String name, diagnosis;

            Patients patient = new Patients();

            while (rs.next()){
                patientId = rs.getInt("id");
                name = rs.getString("name");
                diagnosis = rs.getString("diagnosis");
                patient.setId(patientId);
                patient.setName(name);
                patient.setDiagnosis(diagnosis);
                System.out.println("The patient is " + name + " and the diagnosis is " + diagnosis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get the patient");
        }

        System.out.println("The doctors available are:");
        System.out.println("id\t name\t speciality");
        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM doctors");
            rs = ps.executeQuery();

            int doctor_id;
            String name, speciality;

            Doctors doctor = new Doctors();

            while (rs.next()){
                doctor_id = rs.getInt("id");
                name = rs.getString("name");
                speciality = rs.getString("speciality");
                doctor.setId(doctor_id);
                doctor.setName(name);
                doctor.setSpeciality(speciality);
                System.out.println(doctor_id + " \t " + name + " \t " + speciality);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter the doctor's id to assign the patient to: ");
        int doctor_id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE doctors SET patient_id= " + patient_id + " WHERE id= " + doctor_id);
            ps.execute();
            System.out.println("Successfully assigned patient to the doctor");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to assign the patient to the doctor");
        }
    }

    public static void assignDrugToPatient() {
        System.out.print("Enter patient's id to assign a drug for: ");
        int patient_id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM patients WHERE id=" + patient_id);
            rs = ps.executeQuery();

            int patientId;
            String name, diagnosis;

            Patients patient = new Patients();

            while (rs.next()){
                patientId = rs.getInt("id");
                name = rs.getString("name");
                diagnosis = rs.getString("diagnosis");
                patient.setId(patientId);
                patient.setName(name);
                patient.setDiagnosis(diagnosis);
                System.out.println("The patient is " + name + " and the diagnosis is " + diagnosis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get the patient");
        }

        System.out.println("The drugs available are:");
        System.out.println("id\t name");
        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM drugs");
            rs = ps.executeQuery();

            int drug_id;
            String name;

            Patients patient = new Patients();

            while (rs.next()){
                drug_id = rs.getInt("id");
                name = rs.getString("name");
                patient.setId(drug_id);
                patient.setName(name);
                System.out.println(drug_id + " \t " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter the drug's id to assign to the patient: ");
        int drug_id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE patients SET drug_id= " + drug_id + " WHERE id= " + patient_id);
            ps.execute();
            System.out.println("Successfully assigned drug to the patient");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to assign the drug to the patient");
        }
    }
}

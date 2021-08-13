package com.company.Menu;

import com.company.Controllers.HospitalController;

import java.util.Scanner;

public class Menu {
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do?");
        System.out.println("1. Add a patient");
        System.out.println("2. Add a doctor");
        System.out.println("3. Add a drug");
        System.out.println("4. Delete a patient");
        System.out.println("5. Delete a doctor");
        System.out.println("6. Delete a drug");
        System.out.println("7. Get patient by id");
        System.out.println("8. Assign patient to a doctor");
        System.out.println("9. Assign drug to a patient");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option){
            case 1:
                HospitalController.addNewPatient();
                break;
            case 2:
                HospitalController.addNewDoctor();
                break;
            case 3:
                HospitalController.addNewDrug();
                break;
            case 4:
                HospitalController.deletePatient();
                break;
            case 5:
                HospitalController.deleteDoctor();
                break;
            case 6:
                HospitalController.deleteDrug();
                break;
            case 7:
                HospitalController.getPatientById();
                break;
            case 8:
                HospitalController.assignPatientToDoctor();
                break;
            case 9:
                HospitalController.assignDrugToPatient();
                break;
            default:
                System.out.println("Invalid option selected");
        }
    }
}

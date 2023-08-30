package com.jdbc.main;

import java.util.*;

import com.jdbc.model.Pet;
import com.jdbc.services.*;



public class Main {
	public static void main(String[] args) {
		Services service = new Services();
		Scanner scanner = new Scanner(System.in);
		int choice;
		try {
			do {
	            System.out.println("Enter your choice:");
	            System.out.println("1. Add new pet details");
	            System.out.println("2. Update pet price and vaccination status");
	            System.out.println("3. Delete pet details");
	            System.out.println("4. View all pets");
	            System.out.println("5. Count pets by category");
	            System.out.println("6. Find by price");
	            System.out.println("7. Find by vaccination status for pet type");
	            System.out.println("8. Exit");
	            
	            choice = scanner.nextInt();
	            scanner.nextLine();  // Consume the newline
	            
	            switch (choice) {
	                case 1:
	                    // Add new pet details
	                    System.out.println("Enter pet details:");
	                    System.out.print("Pet Category: ");
	                    String petCategory = scanner.nextLine();
	                    System.out.print("Pet Type: ");
	                    String petType = scanner.nextLine();
	                    System.out.print("Color: ");
	                    String color = scanner.nextLine();
	                    System.out.print("Age: ");
	                    int age = scanner.nextInt();
	                    System.out.print("Price: ");
	                    double price = scanner.nextDouble();
	                    System.out.print("Vaccinated (true/false): ");
	                    boolean isVaccinated = scanner.nextBoolean();
	                    scanner.nextLine();  // Consume the newline
	                    System.out.print("Food Habits: ");
	                    String foodHabits = scanner.nextLine();
	                    
	                    
	                    Pet pet = new Pet(petCategory,petType,color,age,price,isVaccinated,foodHabits);
	                	
	                	service.addPets(pet);
	                    
	                    break;
	                case 2:
	                	// Update pet price and vaccination status
	                	System.out.print("Enter pet ID to update: ");
	                    int updatePetId = scanner.nextInt();
	                    System.out.print("Enter new price: ");
	                    double newPrice = scanner.nextDouble();
	                    System.out.print("Is pet vaccinated (true/false): ");
	                    boolean newVaccinationStatus = scanner.nextBoolean();
	                	
	                    service.updatePetPriceAndVaccinationStatus(updatePetId, newPrice, newVaccinationStatus);
	                    
	                    break;

	                    
	                case 3:
	                	 //// Delete pet details
	                	System.out.println("Enter id of pet: ");
	                	int pet_id = scanner.nextInt();
	                	 service.deletePetById(pet_id);
	                    break;
	                case 4:
	                    /// view all pets
	                	service.getAllPets();
	                    break;
	                case 5:
	                	 	//// Count pets by category
	                		System.out.println("Enter Pet Category: ");
	                		String pet_category = scanner.nextLine();
	                		service.CountPetsByCategory(pet_category);
	                	    break;
	                case 6:
	                	//// Find by price
	                	System.out.println("Enter price range for pet: ");
	                	System.out.print("Enter 1st price: ");
	                	double petPriceOne = scanner.nextDouble();
	                	System.out.print("Enter 2nd price: ");
	                	double petPriceTwo = scanner.nextDouble();
	                	service.getPetByPrice(petPriceOne,petPriceTwo);
	                    break;
	                case 7:
	                	//// count vaccination status for pet type
	                	 System.out.println("Enter pet type: ");
	                	 String pet_type = scanner.nextLine();
	                	 service.CountVaccinatedStatusPetType(pet_type);
	                    break;
	                case 8:
	                    System.out.println("Exiting...");
	                    break;
	                default:
	                    System.out.println("Invalid choice");
	                    break;
	            }
	        } while (choice != 8);
			scanner.close();
		}
		catch(Exception e) {
			throw new RuntimeException("Something went wrong "+e);
		}
        

	}
}

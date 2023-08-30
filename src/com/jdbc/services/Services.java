package com.jdbc.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.database.DatabaseService;
import com.jdbc.database.QueryUtil;
import com.jdbc.model.Pet;


public class Services {
	DatabaseService databaseService = new DatabaseService();
	public void addPets(Pet pet) throws SQLException {
		try (Connection connection = databaseService.getConnection();
			PreparedStatement preparedStatement = 	connection.prepareStatement(QueryUtil.insertPetQuery());){
			preparedStatement.setString(1,  pet.getPetCategory());
			preparedStatement.setString(2,  pet.getPetType());
			preparedStatement.setString(3,  pet.getColor());
			preparedStatement.setInt(4,  pet.getAge());
			preparedStatement.setDouble(5,  pet.getPrice());
			preparedStatement.setBoolean(6,  pet.isVaccinated());
			preparedStatement.setString(7,  pet.getFoodHabits());
			
			int rows = preparedStatement.executeUpdate();
			if(rows>0) {
				System.out.println("Record Created successfully.");
			}
			else {
				System.out.println("Insert Record failed");
			}
		}
		
		
	}/// End of addPets()
	
	public void getAllPets() throws SQLException {
		
		try (Connection connection = databaseService.getConnection();
			Statement statement = 	connection.createStatement();
				ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllPetQuery())){
			
			while(resultSet.next()) {
				count++;
				printPet(new Pet(resultSet.getInt("petid"),
						resultSet.getString("petcategory"),
						resultSet.getString("pettype"),
						resultSet.getString("color"),
						resultSet.getInt("age"),
						resultSet.getDouble("petprice"),
						resultSet.getBoolean("isvaccinated"),
						resultSet.getString("foodhabits")));
			}
			
			
		}
	}///End of getAllPets()
	
	private void printPet(Pet pet) {
		System.out.println("Pet id: "+ pet.getPetId());
		System.out.println("Pet Category: "+ pet.getPetCategory());
		System.out.println("Pet Type: "+ pet.getPetType());
		System.out.println("Pet Color: "+ pet.getColor());
		System.out.println("Pet age: "+ pet.getAge());
		System.out.println("Pet Price: "+ pet.getPrice());
		System.out.println("Pet isVaccinated: "+ pet.isVaccinated());
		System.out.println("Pet foodHabits: "+ pet.getFoodHabits());
		System.out.println("--------------------------------------");
	}//// End of PrintPet()
	
	
	public void getPetByPrice(double priceone, double pricetwo ) throws SQLException {
			boolean isFound = false;
		try (Connection connection = databaseService.getConnection();
				PreparedStatement preparedStatement = 	connection.prepareStatement(QueryUtil.selectPetQueryByPrice());)
					{
			preparedStatement.setDouble(1,  priceone);
			preparedStatement.setDouble(2,  pricetwo);
			
			ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					isFound = true;
					printPet(new Pet(resultSet.getInt("petid"),
							resultSet.getString("petcategory"),
							resultSet.getString("pettype"),
							resultSet.getString("color"),
							resultSet.getInt("age"),
							resultSet.getDouble("petprice"),
							resultSet.getBoolean("isvaccinated"),
							resultSet.getString("foodhabits")));
				}
			}
			if(!isFound) {
				System.out.println("No Pets found at this price!!!!!");
			
		}
		
	}/// End of getPetByPrice()
	
	public void CountVaccinatedStatusPetType(String pet_type) throws SQLException {
		int count = 0;
		try (Connection connection = databaseService.getConnection();
				PreparedStatement preparedStatement = 	connection.prepareStatement(QueryUtil.selectPetQueryByVaccinatedPetType(pet_type));
				
				)
					{
			preparedStatement.setString(1, pet_type);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				System.out.println(resultSet.getString("pettype"));
				if(  resultSet.getBoolean("isvaccinated") ) {
					count++;
				}
			}
			if(count == 0) {
				System.out.println("No pets are vaccinated of pet type : "+ pet_type);
			}
			else {
				System.out.println(count + " pets are vaccinated of pet type: "+ pet_type);
				
			}
		}
	}//// End of CountVaccinatedStatusPetType()
	
	public void CountPetsByCategory(String pet_category) throws SQLException {
		int count =0;
		try(Connection connection = databaseService.getConnection();
				PreparedStatement preparedStatement = 	connection.prepareStatement(QueryUtil.selectPetByCategory());){
			preparedStatement.setString(1, pet_category);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				count++;
			}
			System.out.println("Total Count of pet of category "+ pet_category + " is "+ count);
		}
	}/// END of CountPetsByCategory()
	
	public void deletePetById(int pet_id) throws SQLException {
		try(Connection connection = databaseService.getConnection();
			Statement statement = 	connection.createStatement();
				){
			int rows = statement.executeUpdate(QueryUtil.deleteEmployeeById(pet_id));
			if(rows > 0) {
				System.out.println(rows + " Number of rows deleted ");
			}
			else {
				System.out.println("Something went wrong");
			}
		}
	}/// END of deleteByPetId();
	
	public void updatePetPriceAndVaccinationStatus(int pet_id,double price, boolean newVaccinationStatus) throws SQLException{
		try(Connection connection = databaseService.getConnection();
				PreparedStatement preparedStatement = 	connection.prepareStatement(QueryUtil.updatePetPriceVaccination(pet_id));
					){
			preparedStatement.setDouble(1, price);
			preparedStatement.setBoolean(2, newVaccinationStatus);
			int rows = preparedStatement.executeUpdate();
			
			if(rows>0) {
				System.out.println("price and vaccination status updated");
			}
			else {
				System.out.println("No pet found by this id");
			}
		}
	}
	
}
















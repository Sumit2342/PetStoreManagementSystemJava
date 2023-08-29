package com.jdbc.database;

public class QueryUtil {
	public static String insertPetQuery() {
		return	"INSERT INTO PETS_INFO VALUES(PETID_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
	}
	
	public static String selectAllPetQuery() {
		return "SELECT * FROM PETS_INFO ";
	}
	
	public static String selectPetQueryByPrice() {
		return "SELECT * FROM PETS_INFO WHERE PETPRICE BETWEEN ? and ? " ;
	}
	
	public static String selectPetQueryByVaccinatedPetType(String pet_type) {
		return "SELECT * FROM PETS_INFO WHERE PETTYPE = ?"; 
	}
	public static String selectPetByCategory() {
		return "SELECT * FROM PETS_INFO WHERE PETCATEGORY = ?"; 
	}
	public static String deleteEmployeeById(int pet_id) {
		return "DELETE FROM PETS_INFO WHERE PETID = " +pet_id;
	}
	public static String updatePetPriceVaccination(int pet_id) {
		return "UPDATE PETS_INFO SET PETPRICE = ?, ISVACCINATED = ? WHERE PETID = "+ pet_id;
	}
}

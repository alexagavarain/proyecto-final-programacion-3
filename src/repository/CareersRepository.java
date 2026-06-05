package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Career;

public class CareersRepository {
	
	public List<Career> getCareers() {
		List<Career> careers = new ArrayList<Career>();

		try(Connection connection = DatabaseConnection.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM carrera"); ) {
						
			while(rs.next()) {
			
				Career career = new Career(
					rs.getInt("id_carrera"),
					rs.getString("nombre"),
					rs.getString("siglas")
				);
				
				careers.add(career);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return careers;
	}

}

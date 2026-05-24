package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Professor;
import models.Subject;
import models.Task;
import models.User;

public class ClassesRepository {
	
	public List<Subject> getClasses(User user) {
		List<Subject> classes = new ArrayList<Subject>();

		String sql = "SELECT * FROM vista_materias WHERE id_usuario = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, user.getId());
			
			ResultSet rs = pst.executeQuery(); 
			
			while(rs.next()) {
				Professor professor = new Professor(
						rs.getString("profesor")
				);
				
				Subject subject = new Subject(
					rs.getInt("id_materia"),
					rs.getString("materia"),
					professor
				);
				
				classes.add(subject);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return classes;
	}


}

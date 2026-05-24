package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.GroupSubject;
import models.Professor;
import models.Session;
import models.Subject;
import models.SubjectProfessor;
import models.Task;
import models.User;

public class ClassesRepository {
	
	public List<Subject> getClasses(User user) {
		List<Subject> classes = new ArrayList<Subject>();

		String sql = "SELECT * FROM materia_data WHERE id_grupo = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, user.getGroup().getId());
			
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
	
	public GroupSubject getGroupSubject(User user, SubjectProfessor subjectProfessor) {
		GroupSubject groupSubject = null;
		
		String sql = "SELECT * FROM grupo_materia WHERE id_grupo = ? AND id_materia_profesor = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, user.getGroup().getId());
			pst.setInt(2, subjectProfessor.getId());
			
			ResultSet rs = pst.executeQuery(); 
			
			if(rs.next()) {
				groupSubject = new GroupSubject(
						rs.getInt("id_grupo_materia"),
						user.getGroup(),
						subjectProfessor.getSubject()
				);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return groupSubject;
	}
	
	public SubjectProfessor getSubjectProfessor(Subject subject) {
		SubjectProfessor subjectProfessor = null;
		
		String sql = "SELECT * FROM materia_profesor_data WHERE id_materia = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, subject.getId());
			
			ResultSet rs = pst.executeQuery(); 
			
			if(rs.next()) {
				Professor professor = new Professor(
						rs.getInt("id_profesor"),
						rs.getString("profesor")
				);
				
				subjectProfessor = new SubjectProfessor(
						rs.getInt("id_materia_profesor"),
						subject,
						professor
				);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return subjectProfessor;
	}


}

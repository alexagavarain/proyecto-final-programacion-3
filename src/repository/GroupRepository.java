package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Career;
import models.Group;

public class GroupRepository {
	
	public Group getGroup(Career career, String name, String shift, int semester){
		Group group = null;
		
		String sql = "SELECT * FROM grupo WHERE nombre = ? AND semestre = ? "
				+ "AND turno = ? AND id_carrera = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, name);
			pst.setInt(2, semester);
			pst.setString(3, shift);
			pst.setInt(4, career.getId());
			
			ResultSet rs = pst.executeQuery(); 
			
			if(rs.next()) {				
				group = new Group(
						rs.getInt("id_grupo"),
						rs.getString("nombre"),
						rs.getInt("semestre"),
						rs.getString("turno"),
						career
						);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return group;
	}
	
	public List<Integer> getGroupSemesters(int careerId){
		List<Integer> semesters = new ArrayList<Integer>();

		String sql = "SELECT DISTINCT semestre FROM grupo WHERE id_carrera = ? ORDER BY semestre";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, careerId);
			
			ResultSet rs = pst.executeQuery(); 
			
			while(rs.next()) {				
				semesters.add(rs.getInt("semestre"));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return semesters;
	}
	
	public List<String> getGroupNames(int careerId, int semester){
		List<String> groups = new ArrayList<String>();

		String sql = "SELECT DISTINCT nombre FROM grupo WHERE id_carrera = ? AND semestre = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, careerId);
			pst.setInt(2, semester);
			
			ResultSet rs = pst.executeQuery(); 
			
			while(rs.next()) {				
				groups.add(rs.getString("nombre"));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return groups;
	}
	
	public List<String> getGroupShift(int careerId, int semester, String name){
		List<String> shifts = new ArrayList<String>();

		String sql = "SELECT DISTINCT turno FROM grupo WHERE id_carrera = ? AND semestre = ? AND nombre = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, careerId);
			pst.setInt(2, semester);
			pst.setString(3, name);
			
			ResultSet rs = pst.executeQuery(); 
			
			while(rs.next()) {				
				shifts.add(rs.getString("turno"));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return shifts;
	}	

}

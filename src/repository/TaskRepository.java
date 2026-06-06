package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.GroupSubject;
import models.Professor;
import models.Subject;
import models.Task;
import models.User;
import utils.Session;

public class TaskRepository {
		
	public List<Task> getTasks(User user) {
		List<Task> tasks = new ArrayList<Task>();

		String sql = "SELECT * FROM vista_tarea WHERE id_usuario = ? "
				+ "ORDER BY fecha_entrega ASC";
		
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
								
				GroupSubject groupSubject = new GroupSubject(
						rs.getInt("id_grupo_materia"),
						user.getGroup(),						
						subject
				);
				
				Task task = new Task(
					rs.getInt("id_tarea"), 
					rs.getString("titulo"), 
					rs.getString("descripcion"),
					rs.getTimestamp("fecha_entrega").toLocalDateTime(),
					rs.getString("estado"),
					user,
					groupSubject
				);
				
				tasks.add(task);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return tasks;
	}
	
	public List<Task> getSubjectTasks(User user, Subject subject) {
		List<Task> tasks = new ArrayList<Task>();

		String sql = "SELECT * FROM vista_tarea WHERE id_usuario = ? AND id_materia = ? "
				+ "ORDER BY fecha_entrega ASC";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, user.getId());
			pst.setInt(2, subject.getId());
			
			ResultSet rs = pst.executeQuery(); 
			
			while(rs.next()) {
							
				GroupSubject groupSubject = new GroupSubject(
						rs.getInt("id_grupo_materia"),
						user.getGroup(),						
						subject
				);
				
				Task task = new Task(
					rs.getInt("id_tarea"), 
					rs.getString("titulo"), 
					rs.getString("descripcion"),
					rs.getTimestamp("fecha_entrega").toLocalDateTime(),
					rs.getString("estado"),
					user,
					groupSubject
				);
				
				tasks.add(task);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return tasks;
	}
	
	public Task getTaskData(int idTask) {
		Task taskData = new Task();

		String sql = "SELECT * FROM vista_tarea WHERE id_tarea = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, idTask);
			
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
								
				GroupSubject groupSubject = new GroupSubject(
						rs.getInt("id_grupo_materia"),
						Session.getCurrentUser().getGroup(),						
						subject
				);
				
				taskData = new Task(
					rs.getInt("id_tarea"), 
					rs.getString("titulo"), 
					rs.getString("descripcion"),
					rs.getTimestamp("fecha_entrega").toLocalDateTime(),
					rs.getString("estado"),
					Session.getCurrentUser(),
					groupSubject
				);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return taskData;
	}
	
	
	public boolean saveTask(Task task, User user) {
		String sql = "INSERT INTO tarea (titulo, descripcion, fecha_entrega, estado, id_usuario, id_grupo_materia) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
						
			pst.setString(1, task.getTitle());
			pst.setString(2, task.getDescription());
			pst.setTimestamp(3, Timestamp.valueOf(task.getDeadline()));
			pst.setString(4, task.getStatus());
			pst.setInt(5, user.getId());
			pst.setInt(6, task.getGroupSubject().getId());
			
			int affectedRows = pst.executeUpdate();			
			
			if(affectedRows > 0) {
				System.out.println("Tarea guardada");
				return true;
			}else {
				System.out.println("No se guardó la tarea");
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean updateTask(Task task, User user) {
		String sql = "UPDATE tarea SET titulo = ?, "
				+ "descripcion = ?, "
				+ "fecha_entrega = ?, "
				+ "estado = ?, "
				+ "id_usuario = ?, "
				+ "id_grupo_materia = ? "
				+ "WHERE id_tarea = ?;";
		
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, task.getTitle());
			pst.setString(2, task.getDescription());
			pst.setTimestamp(3, Timestamp.valueOf(task.getDeadline()));
			pst.setString(4, task.getStatus());
			pst.setInt(5, user.getId());
			pst.setInt(6, task.getGroupSubject().getId());
			pst.setInt(7, task.getId());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("Tarea editada");
				return true;
			}else {
				System.out.println("No se editó la tarea");
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteTask(Task task) {
		String sql = "DELETE FROM tarea WHERE id_tarea = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
				
			pst.setInt(1, task.getId());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("Tarea eliminada");
				return true;
			}else {
				System.out.println("No se eliminó la tarea");
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

}

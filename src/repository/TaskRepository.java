//package repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import config.DatabaseConnection;
//import models.Professor;
//import models.Session;
//import models.Subject;
//import models.Task;
//import models.User;
//
//public class TaskRepository {
//	
//	private User user = Session.getCurrentUser();
//	
//	public List<Task> getTasks() {
//		List<Task> tasks = new ArrayList<Task>();
//
//		String sql = "SELECT * FROM vista_tarea WHERE id_usuario = ?";
//		
//		try(Connection connection = DatabaseConnection.getConnection();
//			PreparedStatement pst = connection.prepareStatement(sql)) {
//			
//			pst.setInt(1, user.getId());
//			
//			ResultSet rs = pst.executeQuery(); 
//			
//			while(rs.next()) {
//				Subject subject = new Subject(
//					rs.getInt("id_materia"),
//					rs.getString("materia")
//				);
//				
//				
//				Task task = new Task(
//					rs.getInt("id_tarea"), 
//					rs.getString("titulo"), 
//					rs.getString("descripcion"),
//					rs.getTimestamp("fecha_entrega").toLocalDateTime(),
//					rs.getString("estado"),
//					user,
//					subject
//				);
//				
//				tasks.add(task);
//			}
//			
//		} catch(SQLException ex) {
//			ex.printStackTrace();
//		}
//		
//		return tasks;
//	}
//	
//	public void saveTask(Task task) {
//		String sql = "INSERT INTO tarea (titulo, descripcion, fecha_entrega, estado, id_usuario, id_grupo_materia) "
//				+ "VALUES(?, ?, ?, ?, ?, ?)";
//		
//		
//		try(Connection connection = DatabaseConnection.getConnection();
//			PreparedStatement pst = connection.prepareStatement(sql)) {
//			
////			int groupId = getGroupId(user);
////			System.out.println("grupoid: " + groupId);
//			
//			pst.setString(1, task.getTitle());
//			pst.setString(2, task.getDescription());
//			pst.setTimestamp(3, Timestamp.valueOf(task.getDeadline()));
//			pst.setString(4, task.getStatus());
//			pst.setInt(5, user.getId());
//			pst.setInt(6, groupId);
//			
//			int affectedRows = pst.executeUpdate();
//			
//			if(affectedRows > 0) {
//				System.out.println("Usuario guardado");
//			}else {
//				System.out.println("No se guardó al usuario");
//			}
//			
//		} catch(SQLException ex) {
//			ex.printStackTrace();
//		}
//	}
//
//}

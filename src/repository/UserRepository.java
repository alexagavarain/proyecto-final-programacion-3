package repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.DatabaseConnection;
import models.Career;
import models.Group;
import models.User;

public class UserRepository {
	
	public User login(String email, String password) {	
		User user = null;
		String sql = "SELECT * FROM user_data WHERE correo = ? and contrasena = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
					
			pst.setString(1, email);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery(); 
			
			if(rs.next()) {
				Career career = new Career(
						rs.getInt("id_carrera"),
						rs.getString("carrera"),
						rs.getString("siglas")
						);
				
				Group group = new Group(
						rs.getInt("id_grupo"),
						rs.getString("grupo"),
						rs.getInt("semestre"),
						rs.getString("turno"),
						career	
					);
				
				user = new User(
					rs.getInt("id_usuario"), 
					rs.getString("nombre"), 
					rs.getString("correo"),
					rs.getString("contrasena"),
					rs.getString("rol"),
					group
				);
			}
			
		} catch(SQLException ex ) {
			ex.printStackTrace();
		}
		
		return user;
	}
	
	public void save(User user) throws IOException {		
		String sql = "INSERT INTO usuario (nombre, correo, id_grupo, contrasena, rol) "
				+ "VALUES(?, ?, ?, ?, ?)";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
	
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setInt(3, user.getGroup().getId());
			pst.setString(4, user.getPassword());
			pst.setString(5, user.getRole());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("Usuario guardado");
			}else {
				System.out.println("No se guardó al usuario");
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}	
	}
	
	public User getUser(int id) throws IOException {
		User user = null;
		String sql = "SELECT * FROM user_data WHERE id_usuario = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery(); 
			
			if(rs.next()) {
				Career career = new Career(
						rs.getInt("id_carrera"),
						rs.getString("carrera"),
						rs.getString("siglas")
						);
				
				Group group = new Group(
						rs.getInt("id_grupo"),
						rs.getString("grupo"),
						rs.getInt("semestre"),
						rs.getString("turno"),
						career	
					);
				
				user = new User(
					rs.getInt("id_usuario"), 
					rs.getString("nombre"), 
					rs.getString("correo"),
					rs.getString("contrasena"),
					rs.getString("rol"),
					group
				);
			}
			
		}catch(SQLException ex ) {
			ex.printStackTrace();
		}
				
		return user;
	}
	
	public boolean userExists(String email) {	
		String sql = "SELECT * FROM user_data WHERE correo = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
					
			pst.setString(1, email);
			
			ResultSet rs = pst.executeQuery(); 
			
			if(rs.next()) {
				return true;
			}	
		} catch(SQLException ex ) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public List<User> getUsers() throws IOException {
		List<User> users = new ArrayList<User>();
		
		try(
			Connection connection = DatabaseConnection.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user_data"); 
		) {
			
			while(rs.next()) {
				Career career = new Career(
						rs.getInt("id_carrera"),
						rs.getString("carrera"),
						rs.getString("siglas")
				);
				
				Group group = new Group(
						rs.getInt("id_grupo"),
						rs.getString("grupo"),
						rs.getInt("semestre"),
						rs.getString("turno"),
						career	
				);
				
				User user = new User(
					rs.getInt("id_usuario"), 
					rs.getString("nombre"), 
					rs.getString("correo"),
					rs.getString("contrasena"),
					rs.getString("rol"),
					group
				);
				users.add(user);
			}
			
		}catch(SQLException ex ) {
			ex.printStackTrace();
		}
		
		return users;
	}
	
	public boolean delete(int id) {
		String sql = "DELETE FROM usuario WHERE id_usuario = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if(affectedRows > 0) {
				System.out.println("Se eliminó");
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean updateUser(User updatedUser){
		String sql = "UPDATE usuario SET nombre = ?, correo = ?, id_grupo = ?"
				+ " WHERE id_usuario = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, updatedUser.getName());
			pst.setString(2, updatedUser.getEmail());
			pst.setInt(3, updatedUser.getGroup().getId());
			pst.setInt(4, updatedUser.getId());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("Usuario editado");
				return true;
			}else {
				System.out.println("No se pudo editar al usuario");
			}
						
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public int getCarreerId(User user) throws SQLException {
		int carreerId = -1;
		String sql = "SELECT id_carrera FROM carrera WHERE nombre = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, user.getGroup().getCareer().getName());
			ResultSet rs = pst.executeQuery(); 
			
			if (rs.next()) {
					carreerId = rs.getInt("id_carrera");
				}
			
		} catch(SQLException ex ) {
			ex.printStackTrace();
		}
		
		return carreerId;
	}
}
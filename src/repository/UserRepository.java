package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import config.DatabaseConnection;
import models.Career;
import models.Group;
import models.User;

public class UserRepository {
	
	public User login(String email, String password) {
		
		String sql = "SELECT id_usuario, nombre, correo, rol, id_grupo FROM usuario "
				+ "WHERE correo = ? AND contrasena = ?";
		
		try (
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Group group = new Group(
						rs.getInt("id_grupo")
				);
				
				User user = new User(
						rs.getInt("id_usuario"),
						rs.getString("nombre"),
						rs.getString("correo"),
						rs.getString("rol"),
						group);
				return user;
			}
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public void save(User user) throws IOException {		
		String sql = "INSERT INTO usuario (nombre, correo, id_grupo, contrasena, rol) "
				+ "VALUES(?, ?, ?, ?)";
		
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
	
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setInt(3, user.getGroup().getId());
			pst.setString(4, user.getPassword());
			pst.setString(4, user.getRole());
			
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
	
	public void saveRegister(User user) throws IOException {		
		String sql = "INSERT INTO usuario (nombre, correo, id_grupo, contrasena) "
				+ "VALUES(?, ?, ?, ?)";
		
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setInt(3, user.getGroup().getId());
			pst.setString(4, user.getPassword());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("Usuario registrado");
			}else {
				System.out.println("No se registró al usuario");
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
						rs.getString("carrera")
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
					rs.getString("rol"),
					group
				);
			}
			
		}catch(SQLException ex ) {
			ex.printStackTrace();
		}
		
		return user;
				
	}
	
	public List<User> getUsers() throws IOException {
		List<User> users = new ArrayList<User>();
		
		try(
			Connection connection = DatabaseConnection.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuario"); 
		) {
			
			while(rs.next()) {
				
				User user = new User(
					rs.getInt("id_usuario"), 
					rs.getString("nombre"), 
					rs.getString("correo"),
					rs.getInt("id_grupo")
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
	
	public void update(int index, User updatedUser) throws IOException {
		List<User> users = getUsers();
		users.set(index, updatedUser);
		
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
				System.out.println("Cambios guardados");
			}else {
				System.out.println("No se hicieron cambios");
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public int getCarreerId(User user) throws SQLException {
		int carreerId = -1;
		
		String sql = "SELECT id_carrera FROM carrera  "
				+ "WHERE nombre = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, user.getGroup().getCareer().getName());
			
			ResultSet rs = pst.executeQuery(); 
			
			if (rs.next()) {
					carreerId = rs.getInt("id_carrera");
					System.out.println(carreerId);
				}
			
		} catch(SQLException ex ) {
			ex.printStackTrace();
		}
		
		return carreerId;
	}
	
	public int getGroupId(User user) throws SQLException {
		//TODO: agregar semestre
		
		int groupId = -1;
		int careerId = getCarreerId(user);
		
		String sql = "SELECT id_grupo FROM grupo  "
				+ "WHERE nombre = ? "
				+ "AND semestre = ? "
				+ "AND turno = ? "
				+ "AND id_carrera = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, user.getGroup().getName());
			pst.setInt(2, 1);
			pst.setString(3, user.getGroup().getName());
			pst.setInt(4, careerId);
			
			ResultSet rs = pst.executeQuery(); 
			
			if (rs.next()) {
					groupId = rs.getInt("id_grupo");
				}
			
		} catch(SQLException ex ) {
			ex.printStackTrace();
		}
		
		return groupId;
	}
	
//	public void returnGroupData(User user) {
//		
//		int idGroup = user.getIdGroup();
//				
//		String sql = "SELECT grupo.nombre AS grupo, "
//		        + "grupo.semestre, "
//		        + "grupo.turno, "
//		        + "carrera.nombre AS carrera "
//		        + "FROM grupo "
//		        + "INNER JOIN carrera "
//		        + "ON grupo.id_carrera = carrera.id_carrera "
//		        + "WHERE grupo.id_grupo =  ?";
//		
//		try (Connection connection = DatabaseConnection.getConnection();
//			PreparedStatement pst = connection.prepareStatement(sql))
//		{
//			pst.setInt(1, idGroup);
//			
//			ResultSet rs = pst.executeQuery(); 
//			
//			if (rs.next()) {
//					
//					
//					System.out.println("Turno: " + rs.getString("turno"));
//					user.setGrupo(rs.getString("grupo"));
//					user.setTurno(rs.getString("turno"));
//					user.setCareer(rs.getString("carrera"));
//			}
//			
//		} catch(SQLException ex ) {
//			ex.printStackTrace();
//		}
//	}

			
}











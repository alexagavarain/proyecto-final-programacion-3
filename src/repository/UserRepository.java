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
import models.User;

public class UserRepository {
	
	public void save(User user) throws IOException {		
		String sql = "INSERT INTO usuario (nombre, correo, id_grupo) "
				+ "VALUES(?, ?, ?)";
		
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			int groupId = getGroupId(user);
			System.out.println("grupoid: " + groupId);
			
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setInt(3, groupId);
			
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
			pst.setInt(3, updatedUser.getidGrupo());
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
			
			pst.setString(1, user.getCareer());
			
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
			
			pst.setString(1, user.getGrupo());
			pst.setInt(2, 1);
			pst.setString(3, user.getTurno());
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
	
	public void returnGroupData(User user) {
		
		int userGroup = user.getidGrupo();
		System.out.println("ID GRUPO: " + userGroup);
		
		String sql = "SELECT grupo.nombre AS grupo, "
		        + "grupo.semestre, "
		        + "grupo.turno, "
		        + "carrera.nombre AS carrera "
		        + "FROM grupo "
		        + "INNER JOIN carrera "
		        + "ON grupo.id_carrera = carrera.id_carrera "
		        + "WHERE grupo.id_grupo =  ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql))
		{
			pst.setInt(1, userGroup);
			
			ResultSet rs = pst.executeQuery(); 
			
			if (rs.next()) {
					System.out.println("Turno: " + rs.getString("turno"));
					user.setGrupo(rs.getString("grupo"));
					user.setTurno(rs.getString("turno"));
					user.setCareer(rs.getString("carrera"));
			}
			
		} catch(SQLException ex ) {
			ex.printStackTrace();
		}
	}

			
}











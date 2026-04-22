package controllers;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import tablemodel.UserTableModel;
import views.RegisterView;
import views.UserFormDialog;
import views.UsersView;

public class UserController {

	private UsersView view;
	private UserRepository repo;
	private UserTableModel model;
	
	public UserController(UsersView view) {
		this.view = view;
		repo = new UserRepository();
		
		this.view.getBtnAdd().addActionListener(e -> {
			System.out.println("Agregar user");
			openForm(null);
		});
		
		this.view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Selecciona un usuario");
				return;
			}
			
			System.out.println("Editar user");
			openForm(model.getUserAt(row));
		});
		
		//TODO terminar funcionalidad
		this.view.getBtnDelete().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Selecciona un usuario");
				return;
			}
			
			int option = JOptionPane.showConfirmDialog(view, "¿Seguro de que quieres eliminar al usuario?");
			
			if (option == JOptionPane.YES_OPTION) {
				System.out.println("Se borro al usuario");
				return;
			}
			
			if (option == JOptionPane.NO_OPTION || option == JOptionPane.CANCEL_OPTION) {
				System.out.println("Se cancelo el proceso");
				return;
			}
		});
	}
		
	public void openForm(User user) {
		UserFormDialog dialog = new UserFormDialog(null, user);
		dialog.setVisible(true);
		
		if(dialog.isSaved()) {
			User savedUser = dialog.getUser();
			
			try {
				if(user == null) {
					repo.save(savedUser);
				}else {
					int row = view.getSelectedRow();
					repo.update(row, savedUser);
				}
				
				loadUsers();
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, e.getMessage());
			}
			
		}
	}
	
	public void loadUsers() {	
		System.out.println("Carga usuarios");
		try {
			List<User> users = repo.getUsers();
			
			if(model == null) {
				model = new UserTableModel(users);
				view.setTableModel(model);
			}else {
				model.setUsers(users);
			}
			
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}
}



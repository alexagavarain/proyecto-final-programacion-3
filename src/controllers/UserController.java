package controllers;

import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import models.User;
import repository.UserRepository;
import tablemodel.UserTableModel;
import views.UserFormDialog;
import views.UsersView;
import services.PDFExporter;

public class UserController {

	private UsersView view;
	private UserRepository repo;
	private UserTableModel model;
	private PDFExporter pdfExporter;
	
	@SuppressWarnings("unused")
	public UserController(UsersView view) {
		this.view = view;
		repo = new UserRepository();
		pdfExporter = new PDFExporter();
		
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
		
		this.view.getBtnPdf().addActionListener(e -> generatePdf());
		
		this.view.getBtnDelete().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Selecciona un usuario");
				return;
			}
			
			int option = JOptionPane.showConfirmDialog(view, "¿Seguro de que quieres eliminar al usuario?");
			
			if (option == JOptionPane.YES_OPTION) {
				boolean deleted = repo.delete(model.getUserAt(view.getSelectedRow()).getId());
				if(deleted) {
					System.out.println("Se borro al usuario");
					loadUsers();
				}
				return;
			}
			
			if (option == JOptionPane.NO_OPTION || option == JOptionPane.CANCEL_OPTION) {
				System.out.println("Se cancelo el proceso");
				return;
			}
		});
		
		loadUsers();
	}
		
	public void openForm(User user) {
		UserFormDialog dialog = new UserFormDialog(null, user);
		new UserFormDialogController(dialog);
		dialog.setVisible(true);
		loadUsers();
	}
	
	public void deleteUser(User user) {
		System.out.println(repo.delete(user.getId()));
	}
	
	public void loadUsers() {	
		System.out.println("Carga usuarios");
		try {
			List<User> users = repo.getUsers();
			
			if(model == null) {
				model = new UserTableModel(users);
				view.setTableModel(model);
			} else {
				model.setUsers(users);
			}
			
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}

	public void generatePdf() {
		File file = view.selectPdfFile();
		
		if(file == null) {
			return;
		}
		
		try {
			pdfExporter.exportUsers(repo.getUsers(), file);
			if(Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Error al exportar");
		}
	}
}
//package controllers;
//
//import java.io.IOException;
//import java.awt.Desktop;
//import java.io.File;
//import java.util.List;
//
//import javax.swing.JOptionPane;
//
//import models.User;
//import repository.UserRepository;
//import tablemodel.UserTableModel;
//import views.RegisterView;
//import views.UserFormDialog;
//import views.UsersView;
//import services.PDFExporter;
//
//public class UserController {
//
//	private UsersView view;
//	private UserRepository repo;
//	private UserTableModel model;
//	private PDFExporter pdfExporter;
//	
//	public UserController(UsersView view) {
//		this.view = view;
//		repo = new UserRepository();
//		pdfExporter = new PDFExporter();
//		
//		this.view.getBtnAdd().addActionListener(e -> {
//			System.out.println("Agregar user");
//			openForm(null);
//		});
//		
//		this.view.getBtnEdit().addActionListener(e -> {
//			int row = view.getSelectedRow();
//			if(row == -1) {
//				JOptionPane.showMessageDialog(view, "Selecciona un usuario");
//				return;
//			}
//			
//			System.out.println("Editar user");
////			repo.returnGroupData(model.getUserAt(view.getSelectedRow()));
//			openForm(model.getUserAt(row));
//			
//		});
//		this.view.getBtnPdf().addActionListener(e -> generatePdf());
//		
//		this.view.getBtnDelete().addActionListener(e -> {
//			int row = view.getSelectedRow();
//			if(row == -1) {
//				JOptionPane.showMessageDialog(view, "Selecciona un usuario");
//				return;
//			}
//			
//			int option = JOptionPane.showConfirmDialog(view, "¿Seguro de que quieres eliminar al usuario?");
//			
//			if (option == JOptionPane.YES_OPTION) {
//				boolean deleted = repo.delete(model.getUserAt(view.getSelectedRow()).getId());
//				if(deleted) {
//					//Eliminamos de la tabla
//					//TODO: Eliminar una sola fila
//					System.out.println("Se borro al usuario");
//					loadUsers();
//				}
//				return;
//			}
//			
//			if (option == JOptionPane.NO_OPTION || option == JOptionPane.CANCEL_OPTION) {
//				System.out.println("Se cancelo el proceso");
//				return;
//			}
//		});
//	}
//		
//	public void openForm(User user) {
//		UserFormDialog dialog = new UserFormDialog(null, user);
//		new UserFormDialogController(dialog);
//		dialog.setVisible(true);
//		
//		if(dialog.isSaved()) {
//			User savedUser = dialog.getUser();
//			
//			try {
//				if(user == null) {
//					repo.save(savedUser);
//				}else {
//					int row = view.getSelectedRow();
//					repo.update(row, savedUser);
//				}
//				
//				loadUsers();
//			}catch(Exception e) {
//				e.printStackTrace();
//				JOptionPane.showMessageDialog(view, e.getMessage());
//			}
//			
//		}
//	}
//	
////	public void deleteUser(User user) {
////		int row = view.getSelectedRow();
////		
////		try {
////			repo.delete(row);
////			System.out.println("Usuario borrado");
////			loadUsers();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////	}
//	
//	public void loadUsers() {	
//		System.out.println("Carga usuarios");
//		try {
//			List<User> users = repo.getUsers();
//			
//			if(model == null) {
//				model = new UserTableModel(users);
//				view.setTableModel(model);
//			}else {
//				model.setUsers(users);
//			}
//			
//		} catch (IOException ex) {
//			JOptionPane.showMessageDialog(view, ex.getMessage());
//		}
//	}
//	public void generatePdf() {
//			
//			File file = view.selectPdfFile();
//			
//			if(file == null) {
//				return;
//			}
//			
//			try {
//				pdfExporter.exportUsers(repo.getUsers(), file);
//				if(Desktop.isDesktopSupported()) {
//					Desktop.getDesktop().open(file);
//				}
//			}catch(Exception ex) {
//				ex.printStackTrace();
//				JOptionPane.showMessageDialog(view, "Error al exportar");
//			}
//			
//			
//		}
//}
//
//

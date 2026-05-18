package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import models.User;
import views.UserFormDialog;

public class UserFormDialogController {
	
	public UserFormDialog view;
	private User user;
	
	public UserFormDialogController(UserFormDialog view) {
		this.view = view;
		saveListener();
		validateEmailCharacters();
		validateNameCharacters();
	}
	
	private void saveListener() {
		view.getBtnSave().addActionListener(e -> save());
	}
	
	private boolean validateRegister() {
	    view.getNameError().setText("");
	    view.getEmailError().setText("");
	    view.getPasswordError().setText("");
	    view.getCarreraError().setText("");
	    view.getTurnoError().setText("");
	    view.getGrupoError().setText("");
	    
	    System.out.println("Entro");

	    return validateName() & validateEmail() & validateCareer()
	    		& validateGroup() & validateTurn();

	}
	
	private boolean validateEmail() {
	    if (view.getTxtEmail().getText().trim().isEmpty()) {
	        view.getEmailError().setText("El correo es obligatorio");
	        return false;
	    }

	    if (!view.getTxtEmail().getText().contains("@")) {
	        view.getEmailError().setText("Correo inválido");
	        return false;
	    }

	    view.getEmailError().setText("");
	    return true;
	}
	
	private boolean validateName() {
	    if (view.getTxtName().getText().trim().isEmpty()) {
	        view.getNameError().setText("El nombre es obligatorio");
	        return false;
	    }

	    if (view.getTxtName().getText().trim().length() <= 3) {
	        view.getNameError().setText("Mínimo 4 caracteres");
	        return false;
	    }

	    view.getNameError().setText("");
	    return true;
	}
	
	private boolean validateCareer() {
		if (view.getCboCarrera().getSelectedIndex() == 0) {
			view.getCarreraError().setText("Seleccione una carrera");
			return false;
		}
		return true;
	}
	
	private boolean validateGroup() {
		if (view.getCboGrupo().getSelectedIndex() == 0) {
	        view.getGrupoError().setText("Selecciona un grupo.");
	        return false;
	    }
		return true;
	}
	
	private boolean validateTurn() {
		if (!view.getRbtnMatutino().isSelected() && !view.getRbtnVespertino().isSelected()) {
	        view.getTurnoError().setText("Selecciona un turno.");
	        return false;
	    }
		return true;
	}
	
	
	private void validateNameCharacters() {
		view.getTxtName().addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				if ( Character.isDigit( e.getKeyChar() ) || !Character.isAlphabetic( e.getKeyChar() ) && e.getKeyChar() != ' ' ){
					e.consume();
				}
				
				if (view.getTxtName().getText().length() >= 100 ) {
					e.consume();
				}
			};	
			
		});
	}
	private void validateEmailCharacters() {
		view.getTxtEmail().addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				if (!Character.isAlphabetic( e.getKeyChar()) && !Character.isDigit(e.getKeyChar())) {
					if (e.getKeyChar() != '@' && e.getKeyChar() != '.' && e.getKeyChar() != '_') {
						e.consume();
					}
				}
				
				if ( view.getTxtEmail().getText().length() >= 100 ) {
					e.consume();
				}
			};	
			
		});
	}
	
	private void save() {
	    String name = view.getTxtName().getText();
	    String email = view.getTxtEmail().getText();
	    String career = (String) view.getCboCarrera().getSelectedItem();
	    String grupo = (String) view.getCboGrupo().getSelectedItem();
	    String turno = view.getRbtnMatutino().isSelected() ? "M" : "V";

	    user = view.getUser();
	    
	    if (validateRegister()) {
	    	if (user == null) {
		        user = new User(name, email, career, turno, grupo);
		    } else {
		        user.setName(name);
		        user.setEmail(email);
		        user.setCareer(career);
		        user.setTurno(turno);
		        user.setGrupo(grupo);
		    }
	    	view.setUser(user);
		    view.setSaved(true);
		    view.dispose();
	    } else {
	    	System.out.println("No validada");
	    }
	    
	}

}

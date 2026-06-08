package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidEmailException;
import exceptions.InvalidPasswordException;
import models.User;
import repository.UserRepository;
import utils.Session;
import views.AdminView;
import views.HomeView;
import views.LoginView;

public class LoginController {
	
	private LoginView view;
	private UserRepository repository;
	
	public LoginController(LoginView view) {
		this.view = view;
		repository = new UserRepository();
	    loginListener();
 		validateEmailCharacters();	}
	
	@SuppressWarnings("unused")
	private void loginListener() {
		view.getLoginButton().addActionListener(e -> {
			try {
				handleLogin(view.getContentPane());
			} catch (InvalidEmailException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	private void handleLogin(JPanel panel) throws InvalidEmailException {
	    view.getEmailError().setText("");
	    view.getPasswordError().setText("");
	   	    
	    try {
	    	 if (!validateCredentials(
	 	            new User(
	 	                view.getEmailTextField().getText(),
	 	                String.valueOf(view.getPasswordField().getPassword())
	 	            )
	 	        )) {
	 		    return;
	 	    }
	    } catch(InvalidEmailException e) {
    		view.getEmailError().setText(e.getMessage());
    		return;
	    }

	    User user = repository.login(
	        view.getEmailTextField().getText(),
	        String.valueOf(view.getPasswordField().getPassword())
	    );
	    

	    if(user == null) {
	        view.getPasswordError().setText("Credenciales incorrectas" + "      ");
	        return;
	    }
	    
	    Session.setCurrentUser(user);
	    
	    if (user.getRole() != null && user.getRole().equals("Administrador")) {
	    	AdminView adminView = new AdminView();
	    	new AdminController(adminView);
	    	adminView.setVisible(true);
	    	Session.setMainFrame(adminView);
	    } else {
		    HomeView home = new HomeView();
	        HomeController appController = new HomeController(home);
	        Session.setAppController(appController);
	        home.setVisible(true);
	        Session.setMainFrame(home);
	    }

	    view.dispose();
	}
	
	private boolean validateCredentials(User user) throws InvalidEmailException {
		view.getEmailError().setText("");;
		view.getPasswordError().setText("");

		boolean valid = true;

		if (user.getEmail().trim().isEmpty()) {
			view.getEmailError().setText("El correo es obligatorio");
			valid = false;
		} else if (!user.getEmail().contains("@")) {
	        valid = false;
	        throw new InvalidEmailException("Correo inválido" + "             ");
	    }

		if (user.getPassword().trim().isEmpty()) {
			view.getPasswordError().setText("La contraseña es obligatoria");
			valid = false;
		}
		
		return valid;
	}
	
	private boolean validatePassword() throws InvalidPasswordException {
		if (String.valueOf(view.getPasswordField().getPassword()).trim().isEmpty()) {
			view.getPasswordError().setText("La contraseña es obligatoria");
			return false;
		}

	    return true;
	}
	
	private boolean validateEmail() throws InvalidEmailException {
		if (view.getEmailTextField().getText().trim().equals("")){
			view.getEmailError().setText("El correo es obligatorio");
			return false;
		}
		
		if (!view.getEmailTextField().getText().trim().equals("") && !view.getEmailTextField().getText().contains("@")) {
	        throw new InvalidEmailException("Correo inválido");
	    }

	    return true;
	}
	
	private void validateEmailCharacters() {
		view.getEmailTextField().addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				if (!Character.isAlphabetic( e.getKeyChar()) && !Character.isDigit(e.getKeyChar())) {
					if (e.getKeyChar() != '@' && e.getKeyChar() != '.' && e.getKeyChar() != '_') {
						e.consume();
					}
				}
				
				if ( view.getEmailTextField().getText().length() >= 100 ) {
					e.consume();
				}
			};	
			
		});
	}
	
	private void handleEmailValidation() {
		try {
			validateEmail();
		} catch (InvalidEmailException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void handlePasswordValidation() {
		try {
			validatePassword();
		} catch (InvalidPasswordException e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private void assignListeners() {
	    view.getEmailTextField().getDocument().addDocumentListener(new DocumentListener() {
	        public void insertUpdate(DocumentEvent e) {
	        	handleEmailValidation();
	        }

	        public void removeUpdate(DocumentEvent e) {
	        	handleEmailValidation();
	        }

	        public void changedUpdate(DocumentEvent e) {
	        	handleEmailValidation();
	        }
	    });

	    view.getPasswordField().getDocument().addDocumentListener(new DocumentListener() {
	        public void insertUpdate(DocumentEvent e) {
	        	handlePasswordValidation();
	        }

	        public void removeUpdate(DocumentEvent e) {
	        	handlePasswordValidation();
	        }

	        public void changedUpdate(DocumentEvent e) {
	        	handlePasswordValidation();
	        }
	    });
	}

	
	@SuppressWarnings("unused")
	private void windowStatus() {
		view.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("Ventana abierta");
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println("Ventana minimizada");
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println("Ventana reabrierta");
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("Ventana fuera de focus");
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Ventana cerrada");
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("Ventana dentro de focus");
				
			}
		});
	}
	
	private void handleClose() {
		int option = 0;
		option = JOptionPane.showConfirmDialog(view, this, "¿Seguro que deseas salir?", option);
		
		if(option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	

}

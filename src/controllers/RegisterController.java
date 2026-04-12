package controllers;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import exceptions.InvalidEmailException;
import exceptions.InvalidPasswordException;
import utils.AppColors;
import utils.InputField;
import views.HomeView;
import views.LoginView;
import views.RegisterView;


public class RegisterController {
	private RegisterView view;
	
	public RegisterController(RegisterView view) {
		
		this.view = view;
	    RegisterListener();
	    assignListeners();
	    BackListener();
	    validateNameCharacters();
 		validateEmailCharacters();
	}
	
	private void RegisterListener() {
		view.getRegisterButton().addActionListener(e -> Register(view.getContentPane()));
	}
	private void BackListener() {
	    view.getBackButton().addActionListener(e -> {
	        new LoginView().setVisible(true);
	        view.dispose();
	    });
	}
	
	private void Register(JPanel panel) {
		if (validateRegister()) {
			JOptionPane.showMessageDialog(panel, "Te has registrado correctamente", "Registrado", JOptionPane.INFORMATION_MESSAGE);
			
			new HomeView().setVisible(true);
			view.dispose();
		} else {
	        panel.revalidate();
	        panel.repaint();
	    }
	}
	
	
	private boolean validateRegister() {

	    // Limpiar errores anteriores
	    view.getNameError().setText("");
	    view.getEmailError().setText("");
	    view.getPasswordError().setText("");
	    view.getCarreraError().setText("");
	    view.getTurnoError().setText("");
	    view.getGrupoError().setText("");

	    boolean valid = true;

	    if (view.getName().trim().isEmpty()) {
	        view.getNameError().setText("El nombre es obligatorio.");
	        valid = false;
	    }

	    if (view.getEmailTextField().getText().trim().isEmpty()) {
	        view.getEmailError().setText("El correo es obligatorio.");
	        valid = false;
	    }

	    if (String.valueOf(view.getPasswordField().getPassword()).trim().isEmpty()) {
	        view.getPasswordError().setText("La contraseña es obligatoria.");
	        valid = false;
	    }

	    if (view.getListaCarreras().getSelectedIndex() == 0) {
	        view.getCarreraError().setText("Selecciona una carrera.");
	        valid = false;
	    }

	    if (!view.getMatutino().isSelected() && !view.getVespertino().isSelected()) {
	        view.getTurnoError().setText("Selecciona un turno.");
	        valid = false;
	    }

	    if (view.getListaGrupos().getSelectedIndex() == 0) {
	        view.getGrupoError().setText("Selecciona un grupo.");
	        valid = false;
	    }

	    return valid;
	}
	private void assignListeners() {


		 view.getEmailTextField().getDocument().addDocumentListener(new DocumentListener() {
		        public void insertUpdate(DocumentEvent e) {
		            validateEmail();
		        }

		        public void removeUpdate(DocumentEvent e) {
		            validateEmail();
		        }

		        public void changedUpdate(DocumentEvent e) {
		            validateEmail();
		        }
		    });

		    view.getPasswordField().getDocument().addDocumentListener(new DocumentListener() {
		        public void insertUpdate(DocumentEvent e) {
		            validatePassword();
		        }

		        public void removeUpdate(DocumentEvent e) {
		            validatePassword();
		        }

		        public void changedUpdate(DocumentEvent e) {
		            validatePassword();
		        }
		    });


	    KeyAdapter enterRegister = new KeyAdapter() {
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	                Register(view.getContentPane());
	            }
	        }
	    };

	    view.getEmailTextField().addKeyListener(enterRegister);
	    view.getPasswordField().addKeyListener(enterRegister);
	}
	
	
	private boolean validateEmail() {

	    if (view.getEmailTextField().getText().trim().isEmpty()) {
	        view.getEmailError().setText("El correo es obligatorio");
	        return false;
	    }

	    if (!view.getEmailTextField().getText().contains("@")) {
	        view.getEmailError().setText("Correo inválido");
	        return false;
	    }

	    view.getEmailError().setText("");
	    return true;
	}
	
	private boolean validateName() {

	    if (view.getName().trim().isEmpty()) {
	        view.getNameError().setText("El nombre es obligatorio");
	        return false;
	    }

	    if (view.getName().trim().length() <= 3) {
	        view.getNameError().setText("Mínimo 4 caracteres");
	        return false;
	    }

	    view.getNameError().setText("");
	    return true;
	}
	
	private boolean validateCareer() {
		if (view.getListaCarreras().getSelectedIndex() == 0) {
			view.getCarreraError().setText("Seleccione una carrera");
			return false;
		}
		return true;
	}
	
	private boolean validatePassword() {

	    if (String.valueOf(view.getPasswordField().getPassword()).trim().isEmpty()) {
	        view.getPasswordError().setText("La contraseña es obligatoria");
	        return false;
	    }

	    if (String.valueOf(view.getPasswordField().getPassword()).length() < 6) {
	        view.getPasswordError().setText("Mínimo 6 caracteres");
	        return false;
	    }

	    view.getPasswordError().setText("");
	    return true;
	}
	private void validateNameCharacters() {
		view.getNameField().addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				if ( Character.isDigit( e.getKeyChar() ) || !Character.isAlphabetic( e.getKeyChar() ) && e.getKeyChar() != ' ' ){
					e.consume();
				}
				
				if (view.getNameField().getText().length() >= 100 ) {
					e.consume();
				}
			};	
			
		});
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
	private void handleClose() {
		int option = 0;
		option = JOptionPane.showConfirmDialog(view, this, "¿Seguro que deseas salir?", option);
		
		if(option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
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
	
	private void fieldFocus(JTextComponent field) {
		view.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				field.requestFocusInWindow();
			}
		});
		
		field.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				field.selectAll();
		        field.setBorder(BorderFactory.createLineBorder(AppColors.primaryAccent, 1, true));
			}
			
			public void focusLost(FocusEvent e) {
		        field.setBorder(BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true));
			}
		});
	}
//    view.getName().getDocument().addDocumentListener(new DocumentListener() {
//        public void insertUpdate(DocumentEvent e) {
//            validateName();
//        }
//
//        public void removeUpdate(DocumentEvent e) {
//            validateName();
//        }
//
//        public void changedUpdate(DocumentEvent e) {
//            validateName();
//        }
//    });
}

package controllers;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import models.Career;
import models.Group;
import models.User;
import repository.CareersRepository;
import repository.GroupRepository;
import repository.UserRepository;
import utils.AppColors;
import utils.Session;
import views.HomeView;
import views.LoginView;
import views.RegisterView;

public class RegisterController {

    public UserRepository repository = new UserRepository();
    public CareersRepository careersRepo = new CareersRepository();
    public GroupRepository groupRepo = new GroupRepository();

    private RegisterView view;

    public RegisterController(RegisterView view) {
        this.view = view;

        disableCombos();
        loadCareers();
        careerListener();
        semesterListener();
        groupListener();

        backListener();
        registerBtnListener();
        assignListeners();
    }
    
    private void registerBtnListener() {
    	view.getRegisterButton().addActionListener(e -> {
    		register(view.getContentPane());
    	});
    }
    
    private void register(JPanel panel) {
	    if (validateRegister()) {
	    	Career selectedCareer = (Career) view.getCareers().getSelectedItem();
            int selectedSemester = (int) view.getSemesters().getSelectedItem();
            String selectedGroup = (String) view.getGroups().getSelectedItem();
            String selectedShift = (String) view.getShifts().getSelectedItem();
            
	    	Group group = groupRepo.getGroup(selectedCareer, selectedGroup, selectedShift,  selectedSemester);
	    	
	        User user = new User(
	        	view.getName(),
	            view.getEmailTextField().getText(),
	            String.valueOf(view.getPasswordField().getPassword()),
	            group
	        );

	        try {
	            repository.saveRegister(user); 
	            JOptionPane.showMessageDialog(panel, "Te has registrado correctamente");
	            
	            LoginView login = new LoginView();
	            new LoginController(login);
	            login.setVisible(true);
	            
	            view.dispose();

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(panel, "Error al guardar usuario");
	            e.printStackTrace();
	        }
	    } else {
	        panel.revalidate();
	        panel.repaint();
	    }
	}

    private void backListener() {
        view.getBackButton().addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(
			        view,
			        "¿Seguro que deseas regresar? Se perderán todos los datos",
			        "Confirmar regreso",
			        JOptionPane.YES_NO_OPTION
			    );
			
			if(option == JOptionPane.YES_OPTION) {
				LoginView login = new LoginView();
				new LoginController(login);
				login.setVisible(true);
				view.dispose();
			}
		});
    }

    private void disableCombos() {

        view.getSemesters().setEnabled(false);
        view.getGroups().setEnabled(false);
        view.getShifts().setEnabled(false);
    }

    private void clearCombo(JComboBox<?> combo) {

        combo.removeAllItems();
        combo.setEnabled(false);
    }

    private void loadCareers() {

        List<Career> careers = careersRepo.getCareers();

        view.getCareers().removeAllItems();

        Career placeholder = new Career(0, "Selecciona una carrera");

        view.getCareers().addItem(placeholder);

        for(Career career : careers) {
            view.getCareers().addItem(career);
        }
    }

    private void careerListener() {

        view.getCareers().addActionListener(e -> {

            Career selectedCareer = (Career) view.getCareers().getSelectedItem();

            if(selectedCareer == null || selectedCareer.getId() == 0) {
                return;
            }

            clearCombo(view.getSemesters());
            clearCombo(view.getGroups());
            clearCombo(view.getShifts());

            loadSemesters(selectedCareer.getId());

            view.getSemesters().setEnabled(true);
        });
    }

    private void loadSemesters(int idCareer) {
        List<Integer> semesters = groupRepo.getGroupSemesters(idCareer);

        for(Integer semester : semesters) {
            view.getSemesters().addItem(semester);
        }
    }

    private void semesterListener() {

        view.getSemesters().addActionListener(e -> {

            Career selectedCareer = (Career) view.getCareers().getSelectedItem();
            Integer selectedSemester = (Integer) view.getSemesters().getSelectedItem();

            if(selectedCareer == null || selectedSemester == null) {
                return;
            }

            clearCombo(view.getGroups());
            clearCombo(view.getShifts());

            loadGroups(selectedCareer.getId(), selectedSemester);

            view.getGroups().setEnabled(true);
        });
    }

    private void loadGroups(int idCareer, int semester) {

        List<String> groups = groupRepo.getGroupNames(idCareer, semester);

        for(String group : groups) {
            view.getGroups().addItem(group);
        }
    }

    private void groupListener() {

        view.getGroups().addActionListener(e -> {

            Career selectedCareer = (Career) view.getCareers().getSelectedItem();
            Integer selectedSemester = (Integer) view.getSemesters().getSelectedItem();
            String selectedGroup = (String) view.getGroups().getSelectedItem();

            if(selectedCareer == null || selectedSemester == null || selectedGroup == null) {
                return;
            }

            clearCombo(view.getShifts());

            loadShifts(selectedCareer.getId(), selectedSemester, selectedGroup);

            view.getShifts().setEnabled(true);
        });
    }

    private void loadShifts(int idCareer, int semester, String name) {

        List<String> shifts = groupRepo.getGroupShift(idCareer, semester, name);

        for(String shift : shifts) {
            view.getShifts().addItem(shift);
        }
    }
	
	private boolean validateRegister() {
	    view.getNameError().setText("");
	    view.getEmailError().setText("");
	    view.getPasswordError().setText("");
	    view.getCareerError().setText("");
	    

	    boolean valid = true;

	    if (!validateName() | !validateEmail() | !validatePassword() | !validateCareer()){
	    	valid = false;
	    }

	    return valid;
	}

	private void assignListeners() {
		 view.getEmailTextField().getDocument().addDocumentListener(new DocumentListener() {
		        public void insertUpdate(DocumentEvent e) {
		        	validateEmailCharacters();
		            validateEmail();
		        }

		        public void removeUpdate(DocumentEvent e) {
		        	validateEmailCharacters();
		            validateEmail();
		        }

		        public void changedUpdate(DocumentEvent e) {
		        	validateEmailCharacters();
		            validateEmail();
		        }
		    });
		 
		 view.getNameField().getDocument().addDocumentListener(new DocumentListener() {
		        public void insertUpdate(DocumentEvent e) {
		        	validateNameCharacters();
		            validateName();
		        }

		        public void removeUpdate(DocumentEvent e) {
		        	validateNameCharacters();
		            validateName();
		        }

		        public void changedUpdate(DocumentEvent e) {
		        	validateNameCharacters();
		            validateName();
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
	                register(view.getContentPane());
	            }
	        }
	    };

	    view.getEmailTextField().addKeyListener(enterRegister);
	    view.getPasswordField().addKeyListener(enterRegister);
	}
	
	private boolean validateCareer() {
		if (view.getCareers().getSelectedIndex() == 0) {
			view.getCareerError().setText("Seleccione una carrera");
			return false;
		}
		return true;
	}
	
	private boolean validateEmail() {
	    if (view.getEmailTextField().getText().trim().isEmpty()) {
	        view.getEmailError().setText("El correo es obligatorio");
	        return false;
	    }

	    if (!view.getEmailTextField().getText().contains("@alu.uabcs.mx")) {
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
}

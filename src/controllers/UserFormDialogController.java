package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.Career;
import models.Group;
import models.User;
import repository.CareersRepository;
import repository.GroupRepository;
import repository.UserRepository;
import utils.Session;
import views.UserFormDialog;

public class UserFormDialogController {
	
	public UserRepository repo = new UserRepository();
    public CareersRepository careersRepo = new CareersRepository();
    public GroupRepository groupRepo = new GroupRepository();
    
	public UserFormDialog view;
	private User user;
	
	public UserFormDialogController(UserFormDialog view) {
		this.view = view;
		this.user = Session.getCurrentUser();
		
		loadData();
	    disableCombos();
	    
	    loadCareers();
	    
	    careerListener();
	    semesterListener();
	    groupListener();
	    saveListener();
	    assignListeners();
	}
	
	private void saveListener() {
		view.getSaveButton().addActionListener(e -> editUser());
	}
	
	private void editUser() {
	    if (validateEdit()) {
	    	int option = JOptionPane.showConfirmDialog(
	                view,
	                "Si actualizas tus datos puedes perder información de tus tareas. ¿Continuar?",
	                "Advertencia",
	                JOptionPane.YES_NO_OPTION
	            );

	            if (option == JOptionPane.YES_OPTION) {
	            	updateUser();
	            }
	    } else {
	        view.getContentPane().revalidate();
	        view.getContentPane().repaint();
	    }
	}
	
	private void updateUser() {
		String name = view.getNameField().getText();
    	String email = view.getEmailField().getText();
    	String password = String.valueOf(view.getPasswordField().getPassword());
    	Career selectedCareer = (Career) view.getCareers().getSelectedItem();
        Integer selectedSemester = (Integer) view.getSemesters().getSelectedItem();
        String selectedGroup = (String) view.getGroups().getSelectedItem();
        String selectedShift = (String) view.getShifts().getSelectedItem();
        
        Group group = null;
        
        if (selectedSemester != null) {
        	group = groupRepo.getGroup(selectedCareer, selectedGroup, selectedShift, selectedSemester);
        }
    	
    	user.setName(name);
    	user.setEmail(email);
    	user.setPassword(password);
    	if (group != null) {
    		user.setGroup(group);
    	}
    	
		if (repo.updateUser(user)) {
            JOptionPane.showMessageDialog(view.getContentPane(), "Se han actualizado tus datos");
//            view.dispose();
		} else {
        JOptionPane.showMessageDialog(view.getContentPane(), "Error al actualizar datos");
        }
	}
	
	private void loadData() {
		view.getNameField().setText(user.getName());
		view.getEmailField().setText(user.getEmail());
		view.getPasswordField().setText(user.getPassword());
		System.out.println(user.getPassword());
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
            
            if(user.getGroup().getCareer().getName().equals(career.getName())) {
            	view.getCareers().setSelectedItem(career);
            }
        }
    }

    private void careerListener() {
        view.getCareers().addActionListener(e -> {
            Career selectedCareer = (Career) view.getCareers().getSelectedItem();
            
            if (selectedCareer == null || selectedCareer.getId() == 0) {
                clearCombo(view.getSemesters());
                clearCombo(view.getGroups());
                clearCombo(view.getShifts());
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
            
            if(user.getGroup().getSemester() == semester) {
            	view.getSemesters().setSelectedItem(semester);
            }
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
            
            if(user.getGroup().getName().equals(group)) {
            	view.getGroups().setSelectedItem(group);
            }
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
            
            if(user.getGroup().getShift().equals(shift)) {
            	view.getShifts().setSelectedItem(shift);
            }
        }
    }
	
	private boolean validateEdit() {
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
		 view.getEmailField().getDocument().addDocumentListener(new DocumentListener() {
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
	}
	
	private boolean validateCareer() {
		if (view.getCareers().getSelectedIndex() == 0) {
			view.getCareerError().setText("Seleccione una carrera");
			return false;
		}
		return true;
	}
	
	private boolean validateEmail() {
	    if (view.getEmailField().getText().trim().isEmpty()) {
	        view.getEmailError().setText("El correo es obligatorio");
	        return false;
	    }

	    if (!view.getEmailField().getText().contains("@alu.uabcs.mx")) {
	        view.getEmailError().setText("Correo inválido");
	        return false;
	    }

	    view.getEmailError().setText("");
	    return true;
	}
	
	private boolean validateName() {
	    if (view.getNameField().getText().trim().isEmpty()) {
	        view.getNameError().setText("El nombre es obligatorio");
	        return false;
	    }

	    if (view.getNameField().getText().trim().length() <= 3) {
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
		view.getEmailField().addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				if (!Character.isAlphabetic( e.getKeyChar()) && !Character.isDigit(e.getKeyChar())) {
					if (e.getKeyChar() != '@' && e.getKeyChar() != '.' && e.getKeyChar() != '_') {
						e.consume();
					}
				}
				
				if ( view.getEmailField().getText().length() >= 100 ) {
					e.consume();
				}
			};	
			
		});
	}

}

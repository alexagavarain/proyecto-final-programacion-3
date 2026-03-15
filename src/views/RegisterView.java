package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import utils.AppColors;
import utils.CreateFont;
import utils.InputField;
import utils.Label;
import utils.TextPrompt;

public class RegisterView extends JFrame{
	boolean error = false;
	InputField name;
	InputField emailTextField;
	JPasswordField passwordField;
	private JPanel contentPane;
	JComboBox<String> listaGrupos;
	private JComboBox<String> listaCarreras;
	private JRadioButton matutino;
	private JRadioButton vespertino;
	private Label emailError = createErrorLabel("");
	private Label passwordError = createErrorLabel("");
	private Label nameError = createErrorLabel("");
	private Label carreraError = createErrorLabel("");
	private Label grupoError =  createErrorLabel("");
	private Label turnoError = createErrorLabel("");
	
	public RegisterView() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Registro");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(
					RegisterView.this,
					"¿Seguro que deseas salir?",
					"Confirmar",
					JOptionPane.YES_NO_OPTION
				);

				if(option == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(AppColors.background);
		
		createSpace(100, contentPane);
		
		showAppName(contentPane, "Registro", 30);
		createSpace(60, contentPane);
		
		createLabel(contentPane, "Nombre", 14, 245);
		name = new InputField();
		validateNameCharacters(name);
		TextPrompt namePrompt = new TextPrompt("nombre apellido", name);
		namePrompt.setForeground(AppColors.subtitle);
		contentPane.add(name);
		contentPane.add(nameError);
		fieldFocus(name);
		
		createSpace(10, contentPane);
		
		createLabel(contentPane, "Correo electrónico", 14, 180);
		
		emailTextField = new InputField();
		validateEmailCharacters(emailTextField);
		
		TextPrompt promptEmail = new TextPrompt("estudiante@alu.uabcs.mx", emailTextField);
		promptEmail.setForeground(AppColors.subtitle);
		contentPane.add(emailTextField);
		contentPane.add(emailError);
		fieldFocus(emailTextField);
		
		createSpace(10, contentPane);
		createLabel(contentPane, "Contraseña", 14, 220);
		passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(300, 28));
		passwordField.setAlignmentX(CENTER_ALIGNMENT);
		passwordField.setFont(new Font ("Arial", Font.PLAIN, 15));

		passwordField.setBorder(
			    BorderFactory.createCompoundBorder(
			        BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true),
			        BorderFactory.createEmptyBorder(5, 5, 5, 5)
			    )
			);
		
		TextPrompt promptPassword = new TextPrompt("•••••••••••", passwordField);
		promptPassword.setForeground(AppColors.subtitle);
		contentPane.add(passwordField);
		contentPane.add(passwordError);
		fieldFocus(passwordField);
				
		
		createSpace(10, contentPane);
		createLabel(contentPane, "Carrera", 14, 245);

		String[] carreras = {
		    "Ingeniería en Desarrollo de Software (IDS)",
		    "Licenciatura en Tecnologías de la Información (LATI)",
		    "Ingeniería en Tecnologia Computacional (ITC)",
		    "Ingeniería en Ciberseguridad"
		};


		listaCarreras = new JComboBox<>(carreras);
		listaCarreras.setMaximumSize(new Dimension(300, 30));
		listaCarreras.setAlignmentX(CENTER_ALIGNMENT);
		listaCarreras.insertItemAt("Selecciona tu carrera", 0);
		listaCarreras.setSelectedIndex(0);
		contentPane.add(listaCarreras);
		contentPane.add(carreraError);
		
	
		createSpace(10, contentPane);
		JPanel secondaryPanel = new JPanel();
		secondaryPanel.setOpaque(false);
		secondaryPanel.setMaximumSize(new Dimension(300, 80));
		secondaryPanel.setLayout(new GridLayout(1, 2, 20, 0));


		// PANEL TURNO
		JPanel turnoPanel = new JPanel();
		turnoPanel.setOpaque(false);
		turnoPanel.setLayout(new BoxLayout(turnoPanel, BoxLayout.Y_AXIS));

		createLabel(turnoPanel,"Turno",13,0);

		JPanel radios = new JPanel();
		radios.setOpaque(false);

		ButtonGroup grupoBotones = new ButtonGroup();

		matutino = new JRadioButton("M");
		vespertino = new JRadioButton("V");

		matutino.setOpaque(false);
		vespertino.setOpaque(false);

		grupoBotones.add(matutino);
		grupoBotones.add(vespertino);

		radios.add(matutino);
		radios.add(vespertino);

		turnoPanel.add(radios);
		turnoPanel.add(turnoError);


		// PANEL GRUPO
		JPanel grupoPanel = new JPanel();
		grupoPanel.setOpaque(false);
		grupoPanel.setLayout(new BoxLayout(grupoPanel, BoxLayout.Y_AXIS));

		createLabel(grupoPanel,"Grupo",13,0);

		String[] grupos = {"Selecciona", "A", "B"};
		listaGrupos = new JComboBox<>(grupos);
		listaGrupos.setPreferredSize(new Dimension(60,25));
		listaGrupos.setMaximumSize(new Dimension(60,25));

		grupoPanel.add(listaGrupos);
		grupoPanel.add(grupoError);


		// agregar ambos paneles
		secondaryPanel.add(turnoPanel);
		secondaryPanel.add(grupoPanel);

		contentPane.add(secondaryPanel);
		assignListeners();
			
		createSpace(30, contentPane);
		createRegisterButton();
		
		createSpace(30, contentPane);
		createBackButton();
		
	}
	
	
	
	private void Register(JPanel panel) {
		if (validateRegister()) {
			JOptionPane.showMessageDialog(panel, "Te has registrado correctamente", "Registrado", JOptionPane.INFORMATION_MESSAGE);
			
			new HomeView().setVisible(true);
			dispose();
		} else {
	        panel.revalidate();
	        panel.repaint();
	    }
	}
	
	private void assignListeners() {


		 emailTextField.getDocument().addDocumentListener(new DocumentListener() {
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

		    passwordField.getDocument().addDocumentListener(new DocumentListener() {
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

		    name.getDocument().addDocumentListener(new DocumentListener() {
		        public void insertUpdate(DocumentEvent e) {
		            validateName();
		        }

		        public void removeUpdate(DocumentEvent e) {
		            validateName();
		        }

		        public void changedUpdate(DocumentEvent e) {
		            validateName();
		        }
		    });

	    KeyAdapter enterRegister = new KeyAdapter() {
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	                Register(contentPane);
	            }
	        }
	    };

	    emailTextField.addKeyListener(enterRegister);
	    passwordField.addKeyListener(enterRegister);
	}
	private boolean validateEmail() {

	    if (emailTextField.getText().trim().isEmpty()) {
	        emailError.setText("El correo es obligatorio");
	        return false;
	    }

	    if (!emailTextField.getText().contains("@")) {
	        emailError.setText("Correo inválido");
	        return false;
	    }

	    emailError.setText("");
	    return true;
	}
	private boolean validateRegister() {

	    // Limpiar errores anteriores
	    nameError.setText("");
	    emailError.setText("");
	    passwordError.setText("");
	    carreraError.setText("");
	    turnoError.setText("");
	    grupoError.setText("");

	    boolean valid = true;

	    if (name.getText().trim().isEmpty()) {
	        nameError.setText("El nombre es obligatorio.");
	        valid = false;
	    }

	    if (emailTextField.getText().trim().isEmpty()) {
	        emailError.setText("El correo es obligatorio.");
	        valid = false;
	    }

	    if (String.valueOf(passwordField.getPassword()).trim().isEmpty()) {
	        passwordError.setText("La contraseña es obligatoria.");
	        valid = false;
	    }

	    if (listaCarreras.getSelectedIndex() == 0) {
	        carreraError.setText("Selecciona una carrera.");
	        valid = false;
	    }

	    if (!matutino.isSelected() && !vespertino.isSelected()) {
	        turnoError.setText("Selecciona un turno.");
	        valid = false;
	    }

	    if (listaGrupos.getSelectedIndex() == 0) {
	        grupoError.setText("Selecciona un grupo.");
	        valid = false;
	    }

	    return valid;
	}
	
	private boolean validateName() {

	    if (name.getText().trim().isEmpty()) {
	        nameError.setText("El nombre es obligatorio");
	        return false;
	    }

	    if (name.getText().trim().length() <= 3) {
	        nameError.setText("Mínimo 4 caracteres");
	        return false;
	    }

	    nameError.setText("");
	    return true;
	}
	
	private boolean validateCareer() {
		if (listaCarreras.getSelectedIndex() == 0) {
			carreraError.setText("Seleccione una carrera");
			return false;
		}
		return true;
	}
	
	private boolean validatePassword() {

	    if (String.valueOf(passwordField.getPassword()).trim().isEmpty()) {
	        passwordError.setText("La contraseña es obligatoria");
	        return false;
	    }

	    if (String.valueOf(passwordField.getPassword()).length() < 6) {
	        passwordError.setText("Mínimo 6 caracteres");
	        return false;
	    }

	    passwordError.setText("");
	    return true;
	}
	
	
	public void createLabel(JPanel container, String containerName, float fontSize, int rightBorder) {
		JLabel label = new JLabel(containerName);
		label.setFont(CreateFont.DEFAULT.deriveFont(fontSize));		
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, rightBorder));
		label.setAlignmentX(CENTER_ALIGNMENT);
		container.add(label);
	}
	
	public void showText(JPanel container, String text, int fontSize) {
		JLabel loginText = new JLabel(text);
		loginText.setToolTipText("");
		loginText.setAlignmentX(CENTER_ALIGNMENT);
		container.add(loginText);
	}
	
	public void showAppName(JPanel container, String name, float fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		appName.setAlignmentX(CENTER_ALIGNMENT);
		container.add(appName);
	}
	
	public void createSpace(int pixels, JPanel container) {
		container.add(Box.createVerticalStrut(pixels));
	}
	
	public JButton createButton(JPanel container, String name, int width, int length) {
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setMaximumSize(new Dimension(width, length));
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);  
		button.setBorderPainted(false);
		button.setAlignmentX(CENTER_ALIGNMENT);
		container.add(button);
		return button;
	}
	
	private JButton createRegisterButton() {
		JButton registerButton = createButton(contentPane, "Registrarme", 300, 30, AppColors.primaryAccent, Color.WHITE);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerButton.setContentAreaFilled(true);
		
		registerButton.addActionListener(e -> Register(contentPane));
		
		registerButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				registerButton.setBackground(AppColors.background);
				registerButton.setForeground(AppColors.primaryAccent);
				registerButton.setBorderPainted(true);
				registerButton.setBorder(BorderFactory.createLineBorder(AppColors.primaryAccent, 1));
			}
			
			public void mouseExited(MouseEvent e) {
				registerButton.setBackground(AppColors.primaryAccent);
				registerButton.setForeground(Color.WHITE);
				registerButton.setBorderPainted(false);
			}
		});
		
		return registerButton;
	}
	
	private JButton createBackButton() {
		JButton btnReturn = createButton(contentPane, "Regresar", 100, 20, AppColors.primaryAccent, Color.WHITE);
		btnReturn.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(
			        this,
			        "¿Seguro que deseas regresar? Se perderán todos los datos",
			        "Confirmar regreso",
			        JOptionPane.YES_NO_OPTION
			    );
			
			if(option == JOptionPane.YES_OPTION) {
				new LoginView().setVisible(true);
				dispose();
			}
		});
		
		btnReturn.addActionListener(e -> Register(contentPane));
		
		btnReturn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnReturn.setBackground(AppColors.background);
				btnReturn.setForeground(AppColors.primaryAccent);
				btnReturn.setBorderPainted(true);
				btnReturn.setBorder(BorderFactory.createLineBorder(AppColors.primaryAccent, 1));
			}
			
			public void mouseExited(MouseEvent e) {
				btnReturn.setBackground(AppColors.primaryAccent);
				btnReturn.setForeground(Color.WHITE);
				btnReturn.setBorderPainted(false);
			}
		});
		
		return btnReturn;
	}
	
	
	private void resetErrorLabel(JLabel errorLabel) {
		errorLabel.setText("");
	}
	
	public JButton createButton(JPanel container, String name, int width, int length, Color background, Color foreground) {
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setMaximumSize(new Dimension(width, length));
		button.setBackground(background);
		button.setForeground(foreground);
		button.setFocusPainted(false);  
		button.setBorderPainted(false);
		button.setAlignmentX(CENTER_ALIGNMENT);
		container.add(button);
		return button;
	}
	public Label createErrorLabel(String text) {
		Label errorLabel = new Label(text, 12, false);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		errorLabel.setMaximumSize(new Dimension(300,20));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		return errorLabel;
	}
	private void validateEmailCharacters(InputField field) {
		field.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				if ( Character.isDigit( e.getKeyChar() ) || !Character.isAlphabetic( e.getKeyChar() ) && e.getKeyChar() != '@') {
					e.consume();
				}
				
				if ( field.getText().length() >= 30 ) {
					e.consume();
				}
			};	
			
		});
	}
	
	private void validateNameCharacters(InputField field) {
		field.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				if ( Character.isDigit( e.getKeyChar() ) || !Character.isAlphabetic( e.getKeyChar() ) && e.getKeyChar() != ' ' ){
					e.consume();
				}
				
				if ( field.getText().length() >= 100 ) {
					e.consume();
				}
			};	
			
		});
	}
	private void windowStatus() {
		addWindowListener(new WindowListener() {
			
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
		int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir?");
		
		if(option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	private void fieldFocus(JTextComponent field) {
		addWindowListener(new WindowAdapter() {
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

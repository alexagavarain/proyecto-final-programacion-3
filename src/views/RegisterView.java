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

import controllers.RegisterController;
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
	private JPanel secondaryPanel;
	private JPanel grupoPanel;
	private JPanel turnoPanel;
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
	private JButton RegisterButton;
	private JButton BackButton;
	
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
		initializeComponents();
		
	}
	
	public void initializeComponents() {
		contentPane = createMainPanel();
		setContentPane(contentPane);
		
		createSpace(100, contentPane);
		
		showAppName(contentPane, "Registro", 30);
		createSpace(60, contentPane);
		
		createLabel(contentPane, "Nombre", 14, 245);
		name = createNameField();
		contentPane.add(name);
		contentPane.add(nameError);
		
		createSpace(10, contentPane);
		
		createLabel(contentPane, "Correo electrónico", 14, 180);
		
		emailTextField = createEmailField();
		contentPane.add(emailTextField);
		contentPane.add(emailError);

		createSpace(10, contentPane);
		createLabel(contentPane, "Contraseña", 14, 220);
		passwordField = createPasswordField();
		contentPane.add(passwordField);
		contentPane.add(passwordError);
				
		
		createSpace(10, contentPane);
		createLabel(contentPane, "Carrera", 14, 245);

		listaCarreras = createListaCarreras();
		contentPane.add(listaCarreras);
		contentPane.add(carreraError);
		
	
		createSpace(10, contentPane);
		secondaryPanel = createSecondaryPanel();
		
		turnoPanel = createListaTurno();
		grupoPanel = createListaGrupo();
		// agregar ambos paneles
		secondaryPanel.add(turnoPanel);
		secondaryPanel.add(grupoPanel);

		contentPane.add(secondaryPanel);

			
		createSpace(30, contentPane);
		RegisterButton = createRegisterButton();
		
		createSpace(30, contentPane);
		BackButton =createBackButton();
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	public Label getEmailError() {
		return emailError;
	}
	public Label getPasswordError() {
		return passwordError;
	}
	public Label getNameError() {
		return nameError;
	}
	public Label getCarreraError() {
		return carreraError;
	}
	public Label getGrupoError() {
		return grupoError;
	}
	public Label getTurnoError() {
		return turnoError;
	}
	public JButton getRegisterButton() {
		return RegisterButton;
	}
	
	public boolean isError() {
		return error;
	}
	public String getName() {
		return name.getText();
	}
	public InputField getNameField() {
		return name;
	}
	public InputField getEmailTextField() {
		return emailTextField;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public JComboBox<String> getListaGrupos() {
		return listaGrupos;
	}
	public JRadioButton getMatutino() {
		return matutino;
	}
	public JRadioButton getVespertino() {
		return vespertino;
	}
	public JComboBox<String> getListaCarreras() {
		return listaCarreras;
	}
	public JButton getBackButton() {
		return BackButton;
	}

	private JPanel createMainPanel() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(AppColors.background);
		return contentPane;
	}

	public void createLabel(JPanel container, String containerName, float fontSize, int rightBorder) {
		JLabel label = new JLabel(containerName);
		label.setFont(CreateFont.DEFAULT.deriveFont(fontSize));		
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, rightBorder));
		label.setAlignmentX(CENTER_ALIGNMENT);
		container.add(label);
	}
	private JPasswordField createPasswordField() {
		passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(300, 28));
		passwordField.setAlignmentX(CENTER_ALIGNMENT);
		passwordField.setBorder(
			    BorderFactory.createCompoundBorder(
			        BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true),
			        BorderFactory.createEmptyBorder(5, 5, 5, 5)
			    )
			);
		TextPrompt promptPassword = new TextPrompt("•••••••••••", passwordField);
		promptPassword.setForeground(AppColors.subtitle);
		return passwordField;
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
	
	public JComboBox<String> createListaCarreras(){
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
			return listaCarreras;
	}
	public JPanel createSecondaryPanel() {
		JPanel secondaryPanel = new JPanel();
		secondaryPanel.setOpaque(false);
		secondaryPanel.setMaximumSize(new Dimension(300, 80));
		secondaryPanel.setLayout(new GridLayout(1, 2, 20, 0));
		return secondaryPanel;
	}
	
	public JPanel createListaGrupo() {
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
		return grupoPanel;
	}
	
	public JPanel createListaTurno() {
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
		return turnoPanel;
	}
	
	
	public InputField createEmailField() {
		emailTextField = new InputField();
		
		TextPrompt promptEmail = new TextPrompt("estudiante@alu.uabcs.mx", emailTextField);
		promptEmail.setForeground(AppColors.subtitle);
		return emailTextField;
	}
	public InputField createNameField() {
		name = new InputField();
		TextPrompt namePrompt = new TextPrompt("nombre apellido", name);
		namePrompt.setForeground(AppColors.subtitle);
		return name;
	}
	
}

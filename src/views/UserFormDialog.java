package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component; 
import java.awt.Dimension; 
import java.awt.FlowLayout; 
import javax.swing.BorderFactory; 
import javax.swing.BoxLayout; 
import javax.swing.ButtonGroup; 
import javax.swing.JButton; 
import javax.swing.JComboBox; 
import javax.swing.JDialog; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JRadioButton; 
import javax.swing.JScrollPane; 
import javax.swing.JTextArea; 
import javax.swing.JTextField; 
import javax.swing.SwingConstants; 
import models.User;
import utils.Label; 

public class UserFormDialog extends JDialog{ 
	
	private JTextField txtName; 
	private JTextField txtEmail; 
	private JComboBox<String> cboCarrera; 
	private JRadioButton rbtnMatutino; 
	private JRadioButton rbtnVespertino; 
	private ButtonGroup turnoGroup; 
	private JComboBox<String> cboGrupo; 
	private JButton btnSave; 
	private JButton btnCancel; 
	private User user; 
	private boolean saved = false; 
	
	private Label emailError = createErrorLabel("");
	private Label passwordError = createErrorLabel("");
	private Label nameError = createErrorLabel("");
	private Label carreraError = createErrorLabel("");
	private Label grupoError =  createErrorLabel("");
	private Label turnoError = createErrorLabel("");
	
	public UserFormDialog(JFrame parent, User user) { 
		super(parent, true); 
		this.user = user; 
		String title = user == null ? "Agregar usuario" : "Editar usuario";
		
		setSize(400, 500);
    	setTitle(title);
		setLocationRelativeTo(parent); 
		setLayout(new BorderLayout()); 
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
		add(createTitlePanel(title), BorderLayout.NORTH); 
		add(createFormPanel(), BorderLayout.CENTER); 
		add(createButtonPanel(), BorderLayout.SOUTH); 
		
		loadData();
	} 
	
	public JButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public User getUser() {
		return user;
	}

	public boolean isSaved() {
		return saved;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JComboBox<String> getCboCarrera() {
		return cboCarrera;
	}

	public JRadioButton getRbtnMatutino() {
		return rbtnMatutino;
	}

	public JRadioButton getRbtnVespertino() {
		return rbtnVespertino;
	}

	public ButtonGroup getTurnoGroup() {
		return turnoGroup;
	}

	public JComboBox<String> getCboGrupo() {
		return cboGrupo;
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

	private JPanel createTitlePanel(String title) { 
		JPanel panel = new JPanel(); 
		panel.add(new JLabel(title)); 
		return panel; 
	} 
	
	private JPanel createButtonPanel() { 
		JPanel panel = new JPanel(); 
		btnSave = new JButton("Guardar"); 
		btnCancel = new JButton("Cancelar"); 
		
		panel.add(btnSave); 
		panel.add(btnCancel); 
		
		btnCancel.addActionListener(e -> dispose()); 
		
		return panel;
	} 
	
	private JPanel createField(String labelText, Component field) { 
		Dimension fieldSize = new Dimension(250, 30); 
		
		JPanel panel = new JPanel(); 
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
		panel.setAlignmentX(Component.CENTER_ALIGNMENT); 
		
		JLabel label = new JLabel(labelText); 
		label.setMaximumSize(fieldSize); 
		label.setHorizontalAlignment(SwingConstants.LEFT); 
		label.setAlignmentX(Component.CENTER_ALIGNMENT); 
		
		panel.add(label); 
		panel.add(field); 
		
		return panel; 
	}
	
	private JScrollPane createFormPanel() { 
		JPanel panel = new JPanel(); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
		panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20)); 
		
		JScrollPane scroll = new JScrollPane(panel); 
		scroll.setBorder(null); 
		scroll.setHorizontalScrollBar(null); 
		
		Dimension fieldSize = new Dimension(250, 30); 

		txtName = new JTextField(); 
		txtName.setMaximumSize(fieldSize); 
		
		txtEmail = new JTextField(); 
		txtEmail.setMaximumSize(fieldSize);
		
		cboCarrera = new JComboBox<>(new String[] { 
				"Seleccione", 
				"Ingeniería en Desarrollo de Software (IDS)",
			    "Licenciatura en Tecnologías de la Información (LATI)",
			    "Ingeniería en Tecnologia Computacional (ITC)",
			    "Ingeniería en Ciberseguridad (IC)"}); 
		cboCarrera.setMaximumSize(fieldSize);
		
		rbtnMatutino = new JRadioButton("Matutino"); 
		rbtnMatutino.setActionCommand("M"); 
		rbtnVespertino = new JRadioButton("Vespertino"); 
		rbtnVespertino.setActionCommand("V");
		turnoGroup = new ButtonGroup(); 
		turnoGroup.add(rbtnMatutino); 
		turnoGroup.add(rbtnVespertino); 
		
		cboGrupo = new JComboBox<>(new String[] { "Seleccione", "A", "B" });
		cboGrupo.setMaximumSize(fieldSize);
		
		panel.add(createField("Nombre:", txtName)); 
		panel.add(nameError);
		panel.add(createField("Correo:", txtEmail)); 
		panel.add(emailError);
		panel.add(createField("Carrera:", cboCarrera)); 
		panel.add(carreraError);
		
		JPanel turnoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		turnoPanel.setMaximumSize(fieldSize);
		turnoPanel.setPreferredSize(fieldSize);
		turnoPanel.add(rbtnMatutino); 
		turnoPanel.add(rbtnVespertino); 
		
		panel.add(createField("Turno:", turnoPanel));
		panel.add(turnoError);
		panel.add(createField("Grupo:", cboGrupo));
		panel.add(grupoError);	
			 
		return scroll; 
	}
	
	private void loadData() {
	    if (user != null) {
	        txtName.setText(user.getName());
	        txtEmail.setText(user.getEmail());

	        cboCarrera.setSelectedItem(user.getGroup().getCareer().getName());
	        cboGrupo.setSelectedItem(user.getGroup().getName());

	        if (user.getGroup().getShift().equals("M")) {
	            rbtnMatutino.setSelected(true);
	        } else {
	            rbtnVespertino.setSelected(true);
	        }
	    }
	}
	
	public Label createErrorLabel(String text) {
		Label errorLabel = new Label("", 12, false);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBackground(Color.BLUE);		
		errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		errorLabel.setMaximumSize(new Dimension(250,20));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		return errorLabel;
	}


}

package views;

import java.awt.BorderLayout; 
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
import javax.swing.JPanel; 
import javax.swing.JRadioButton; 
import javax.swing.JScrollPane; 
import javax.swing.JTextArea; 
import javax.swing.JTextField; 
import javax.swing.SwingConstants; 
import models.User; 

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
	
	public UserFormDialog(JFrame parent, User user) { 
		super(parent, true); 
		this.user = user; setSize(400, 500); 
		setLocationRelativeTo(parent); 
		setLayout(new BorderLayout()); 
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
		add(createTitlePanel(), BorderLayout.NORTH); 
		add(createFormPanel(), BorderLayout.CENTER); 
		add(createButtonPanel(), BorderLayout.SOUTH); 
	} 
	
	private JPanel createTitlePanel() { 
		JPanel panel = new JPanel(); 
		panel.add(new JLabel("Formulario de Usuario")); 
		return panel; 
	} 
	
	private JPanel createButtonPanel() { 
		JPanel panel = new JPanel(); 
		btnSave = new JButton("Guardar"); 
		btnCancel = new JButton("Cancelar"); 
		panel.add(btnSave); panel.add(btnCancel); 
		//btnSave.addActionListener(e -> save()); 
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
		txtName = new JTextField(); 
		txtEmail = new JTextField(); 
		Dimension fieldSize = new Dimension(250, 30); 
		txtName.setMaximumSize(fieldSize); 
		txtEmail.setMaximumSize(fieldSize); 
		cboCarrera = new JComboBox<>(new String[] { "Seleccione", "IDS", "LATI", "ITC" }); 
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
		panel.add(createField("Email:", txtEmail)); 
		panel.add(createField("Carrera:", cboCarrera)); 
		JPanel turnoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));  
		turnoPanel.add(rbtnMatutino); 
		turnoPanel.add(rbtnVespertino); 
		panel.add(createField("Turno:", turnoPanel)); 
		panel.add(createField("Grupo:", cboGrupo));
			
			 
		return scroll; 
	} 

}

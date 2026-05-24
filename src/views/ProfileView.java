package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.User;
import utils.CreateFont;

public class ProfileView extends JPanel {

    private JTextField txtName;
    private JTextField txtEmail;

    private JComboBox<String> cboCarrera;
    private JComboBox<String> cboGrupo;

    private JRadioButton rbtnMatutino;
    private JRadioButton rbtnVespertino;

    private ButtonGroup turnoGroup;

    private JButton btnSave;

    public ProfileView() {
        this(null);
    }

    public ProfileView(User user) {

        setLayout(new BorderLayout());

        createTitle("Perfil", 25);

        add(createFormPanel(user), BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();

        buttonPanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 20, 0));

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void createTitle( String name, float fontSize) {

        JLabel appName = new JLabel( name, SwingConstants.CENTER);

        appName.setFont(CreateFont.DEFAULT_BOLD .deriveFont(fontSize));

        appName.setBorder(BorderFactory.createEmptyBorder( 15, 0, 10, 0));

        add(appName, BorderLayout.NORTH);
    }

    private JPanel createFormPanel(User user) {

        JPanel panel = new JPanel();

        panel.setLayout(  new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder( BorderFactory.createEmptyBorder( 20, 90, 20, 90));

        Dimension fieldSize = new Dimension(250, 35);

        txtName = new JTextField();
        txtName.setPreferredSize(fieldSize);
        txtName.setMaximumSize(fieldSize);

        txtEmail = new JTextField();
        txtEmail.setPreferredSize(fieldSize);
        txtEmail.setMaximumSize(fieldSize);

        cboCarrera = new JComboBox<>(new String[] {
                "Seleccione",
                "IDS",
                "LATI",
                "ITC",
                "IC"
        });

        cboCarrera.setPreferredSize(fieldSize);
        cboCarrera.setMaximumSize(fieldSize);

        cboGrupo = new JComboBox<>(new String[] {
                "Seleccione",
                "A",
                "B"
        });

        cboGrupo.setPreferredSize(fieldSize);
        cboGrupo.setMaximumSize(fieldSize);

        rbtnMatutino = new JRadioButton("Matutino");

        rbtnVespertino =new JRadioButton("Vespertino");

        turnoGroup = new ButtonGroup();

        turnoGroup.add(rbtnMatutino);
        turnoGroup.add(rbtnVespertino);

        JPanel turnoPanel = new JPanel();

        turnoPanel.setPreferredSize( new Dimension(300, 50));

        turnoPanel.setMaximumSize( new Dimension(300, 50));

        turnoPanel.add(rbtnMatutino);

        turnoPanel.add(rbtnVespertino);

        panel.add(createField( "Nombre", txtName));

        panel.add(createField( "Correo", txtEmail));

        panel.add(createField( "Carrera",cboCarrera));

        panel.add(createField( "Turno", turnoPanel));

        panel.add(createField(  "Grupo", cboGrupo));

        loadData(user);

        return panel;
    }

    private JPanel createField(  String text, Component component) {

        JPanel panel = new JPanel();

        panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS));

        panel.setBorder( BorderFactory.createEmptyBorder( 8, 0, 8, 0));

        panel.setAlignmentX( Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(text);

        label.setAlignmentX( Component.CENTER_ALIGNMENT);


        panel.add(label);

        panel.add(component);

        return panel;
    }

    private JPanel createButtonPanel() {

        JPanel panel = new JPanel();

        btnSave = new JButton("Guardar cambios");

        btnSave.setPreferredSize( new Dimension(170, 35));

        btnSave.setMaximumSize( new Dimension(170, 35));

        panel.add(btnSave);

        return panel;
    }

    private void loadData(User user) {

        if (user == null)
            return;

        txtName.setText(user.getName());

        txtEmail.setText(user.getEmail());

        cboCarrera.setSelectedItem(user.getGroup().getCareer().getName());

        cboGrupo.setSelectedItem(
                user.getGroup().getName());

        if ("M".equals(user.getGroup().getShift())) {

            rbtnMatutino.setSelected(true);

        } else {

            rbtnVespertino.setSelected(true);
        }
    }

    public JButton getBtnSave() {
        return btnSave;
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

    public JComboBox<String> getCboGrupo() {
        return cboGrupo;
    }

    public ButtonGroup getTurnoGroup() {
        return turnoGroup;
    }
    public JRadioButton getRbtnMatutino() {
        return rbtnMatutino;
    }

    public JRadioButton getRbtnVespertino() {
        return rbtnVespertino;
    }
}
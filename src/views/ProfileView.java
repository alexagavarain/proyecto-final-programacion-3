package views;

import java.awt.*;
import javax.swing.*;

import utils.AppColors;
import utils.CreateFont;
import utils.InputField;
import utils.Label;
import utils.RoundedButton;
import utils.TextPrompt;

public class ProfileView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel avatar;
	
	private InputField nameField;
	private InputField emailField;
	private InputField careerField;
	private InputField semesterField;
	private InputField groupField;
	private InputField shiftField;
	
	private TextPrompt namePrompt; 
	private TextPrompt emailPrompt;
	private TextPrompt careerPrompt;
	private TextPrompt semesterPrompt;
	private TextPrompt groupPrompt;
	private TextPrompt shiftPrompt;
	
	private Label nameLbl;
	private Label careerLbl;
	
	private JButton editBtn;
	
	private Dimension fieldSize;

    public ProfileView() {    	
        setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));

		fieldSize = new Dimension (240, 40);

        createHeader(); 
        createCard();
    }
   
    public JButton getEditBtn() {
		return editBtn;
	}

	public InputField getNameField() {
		return nameField;
	}

	public InputField getEmailField() {
		return emailField;
	}

	public InputField getCareerField() {
		return careerField;
	}

	public InputField getSemesterField() {
		return semesterField;
	}

	public InputField getGroupField() {
		return groupField;
	}

	public InputField getShiftField() {
		return shiftField;
	}

	public Label getNameLbl() {
		return nameLbl;
	}

	public Label getCareerLbl() {
		return careerLbl;
	}

	public JLabel getAvatar() {
		return avatar;
	}

	public TextPrompt getNamePrompt() {
		return namePrompt;
	}

	public TextPrompt getEmailPrompt() {
		return emailPrompt;
	}

	public TextPrompt getCareerPrompt() {
		return careerPrompt;
	}

	public TextPrompt getSemesterPrompt() {
		return semesterPrompt;
	}

	public TextPrompt getGroupPrompt() {
		return groupPrompt;
	}

	public TextPrompt getShiftPrompt() {
		return shiftPrompt;
	}

	private void createHeader() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
		
		Label title = new Label("Mi Perfil", 23, false);
		Label subtitle = new Label("Actualiza tu información personal", 14, true, AppColors.menuItem);
		
		title.setAlignmentX(LEFT_ALIGNMENT);
		subtitle.setAlignmentX(LEFT_ALIGNMENT);
		
		titlePanel.add(title);
		titlePanel.add(Box.createVerticalStrut(5));
		titlePanel.add(subtitle);
		
		add(titlePanel, BorderLayout.NORTH);
	}

    private void createCard() {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        wrapper.setOpaque(false);

        JPanel card = buildRoundedCardPanel();
        
        card.add(createUserHeader());
        card.add(Box.createVerticalStrut(20));
        
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        card.add(separator);
        card.add(Box.createVerticalStrut(20));

        JPanel formGrid = createFormFields();
        card.add(formGrid);
        
        card.add(Box.createVerticalStrut(25));

        createEditBtn();
        card.add(editBtn);
        
        card.add(Box.createVerticalGlue());

        wrapper.add(card);

        add(wrapper, BorderLayout.CENTER);
    }

    private JPanel buildRoundedCardPanel() {
        int cornerRadius = 20;

        JPanel roundedCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();

                java.awt.geom.RoundRectangle2D.Double roundRect = 
                    new java.awt.geom.RoundRectangle2D.Double(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

                g2.setColor(Color.WHITE);
                g2.fill(roundRect);

                g2.setColor(AppColors.iceGrey);
                g2.setStroke(new java.awt.BasicStroke(1));
                g2.draw(roundRect);

                g2.dispose();
            }
        };

        roundedCard.setLayout(new BoxLayout(roundedCard, BoxLayout.Y_AXIS));
        roundedCard.setOpaque(false);
        
        Dimension size = new Dimension(580, 480);
        roundedCard.setPreferredSize(new Dimension(size));
        roundedCard.setMaximumSize(new Dimension(size));
        roundedCard.setMinimumSize(new Dimension(size));
        roundedCard.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        return roundedCard;
    }
    
    private JPanel createAvatar() {
        avatar = new JLabel("", SwingConstants.CENTER);
        avatar.setOpaque(true);
        avatar.setBackground(new Color(0xBFD3F6));
        avatar.setForeground(new Color(0x2563EB));
        avatar.setFont(CreateFont.DEFAULT_BOLD.deriveFont(18f));
        avatar.setPreferredSize(new Dimension(60, 60));
        
        JPanel avatarWrap = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0xBFD3F6));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        avatarWrap.setOpaque(false);
        avatarWrap.setPreferredSize(new Dimension(60, 60));
        avatar.setOpaque(false);
        avatarWrap.add(avatar);
        
        return avatarWrap;
    }

    private JPanel createUserHeader() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        p.setBackground(Color.WHITE);
        p.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Color.WHITE);
        
        nameLbl = new Label("", 15, true);
        careerLbl = new Label("", 12, true, AppColors.subtitle);

        nameLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        careerLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        info.add(nameLbl);
        info.add(Box.createVerticalStrut(5));
        info.add(careerLbl);

        p.add(createAvatar());
        p.add(info);
        return p;
    }
    
    public JPanel createFormFields() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);
        container.setAlignmentX(Component.LEFT_ALIGNMENT);
    	
        int gapHorizontal = 15;
        int gapVertical = 12;

        nameField = createField();
        emailField = createField();
        careerField = createField();
        semesterField = createField();
        groupField = createField();
        shiftField = createField();
        
        namePrompt = createTextPrompt("", nameField);
		emailPrompt = createTextPrompt("", emailField);
		careerPrompt = createTextPrompt("", careerField);
		semesterPrompt = createTextPrompt("", semesterField);
		groupPrompt = createTextPrompt("", groupField);
		shiftPrompt = createTextPrompt("", shiftField);
    	
        JPanel row1 = new JPanel(new GridLayout(1, 2, gapHorizontal, 0));
        row1.setOpaque(false);
        row1.setAlignmentX(Component.LEFT_ALIGNMENT);
        row1.add(createFieldBlock("NOMBRE COMPLETO", nameField));
        row1.add(createFieldBlock("CORREO ELECTRÓNICO", emailField));
        container.add(row1);
        container.add(Box.createVerticalStrut(gapVertical));

        JPanel row2 = new JPanel(new GridLayout(1, 2, gapHorizontal, 0));
        row2.setOpaque(false);
        row2.setAlignmentX(Component.LEFT_ALIGNMENT);
        row2.add(createFieldBlock("CARRERA", careerField));
        row2.add(createFieldBlock("SEMESTRE", semesterField));
        container.add(row2);
        container.add(Box.createVerticalStrut(gapVertical));

        JPanel row3 = new JPanel(new GridLayout(1, 2, gapHorizontal, 0));
        row3.setOpaque(false);
        row3.setAlignmentX(Component.LEFT_ALIGNMENT);
        row3.add(createFieldBlock("GRUPO", groupField));
        row3.add(createFieldBlock("TURNO", shiftField));
        container.add(row3);
        
        return container;
    }

    private JPanel createFieldBlock(String labelText, JTextField field) {
        JPanel block = new JPanel();
        block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
        block.setOpaque(false);
        block.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        Label lbl = new Label(labelText, 11, true, AppColors.darkBlue);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));
        
        block.add(lbl);
        block.add(field);
        return block;
    }
    
    private TextPrompt createTextPrompt(String text, InputField field) {
    	TextPrompt prompt = new TextPrompt(text, field);
		prompt.setForeground(AppColors.subtitle);
		return prompt;
    }
    
    private InputField createField(){
    	InputField field = new InputField();
		field.setMaximumSize(new Dimension(Integer.MAX_VALUE, fieldSize.height));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setEditable(false);
		return field;
    }
    
    public void loadPrompt(InputField field, String text) {
    	TextPrompt prompt = new TextPrompt(text, field);
		prompt.setForeground(AppColors.subtitle);
    }
    
    private void createEditBtn() {
    	editBtn = new RoundedButton("Editar perfil", 15);
    	editBtn.setFont(CreateFont.DEFAULT_BOLD.deriveFont(13f));
    	editBtn.setBackground(AppColors.primaryAccent);
    	editBtn.setForeground(Color.WHITE);
    	editBtn.setFocusPainted(false);
        editBtn.setPreferredSize(new Dimension(520, 38));
        editBtn.setMaximumSize(new Dimension(520, 38));
        editBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

}
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utils.AppColors;
import utils.CreateFont;

public class TasksView extends JPanel {
	
	public JButton addBtn;
	private JPanel monColumn;
	private JPanel tueColumn;
	private JPanel wedColumn;
	private JPanel thuColumn;
	private JPanel friColumn;

	
	public TasksView() { 
		setLayout(new BorderLayout());	
		setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));
		header();
		weekBoard();
	}
	
	
	public JLabel createTitle(String name, float fontSize) {
		JLabel title = new JLabel(name);
		title.setToolTipText("");
		title.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		return title;
	}
	
	public JButton createAddBtn() {
		addBtn = new JButton ("Agregar tarea");
		addBtn.setBackground(AppColors.primaryAccent);
		addBtn.setForeground(Color.WHITE);
		return addBtn;
	}
	
	public void header() {
		JPanel header = new JPanel(new BorderLayout());
		header.add(createTitle("Tareas", 23), BorderLayout.WEST);
		header.add(createAddBtn(), BorderLayout.EAST);
		header.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		add(header, BorderLayout.NORTH);
	}
	
	public void createSpace(JPanel container, int pixels) {
		container.add(Box.createVerticalStrut(pixels));
	}
	
	public void weekBoard() {
		JPanel board = new JPanel(new GridLayout(1, 5, 10, 10));
		
		monColumn = createDayColumn("Lunes");
		tueColumn = createDayColumn("Martes");
		wedColumn = createDayColumn("Miércoles");
		thuColumn = createDayColumn("Jueves");
		friColumn = createDayColumn("Viernes");
		
		board.add(monColumn);
		board.add(tueColumn);
		board.add(wedColumn);
		board.add(thuColumn);
		board.add(friColumn);
		
		add(board, BorderLayout.CENTER);
	}
	
	public JPanel createDayColumn(String day) {

	    JPanel dayColumn = new JPanel();
	    dayColumn.setLayout(new BoxLayout(dayColumn, BoxLayout.Y_AXIS));

	    JLabel dayLbl = new JLabel(day, SwingConstants.LEFT);

	    dayLbl.setFont(CreateFont.DEFAULT.deriveFont(13f));
	    dayLbl.setOpaque(true);
	    dayLbl.setBackground(AppColors.bluegray);

	    dayLbl.setBorder(
	        BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(AppColors.bluegray),
	            BorderFactory.createEmptyBorder(5, 10, 5, 0)
	        )
	    );

	    dayLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
	    dayLbl.setMaximumSize(new Dimension(Integer.MAX_VALUE,
	                                        dayLbl.getPreferredSize().height));

	    dayColumn.add(dayLbl);

	    return dayColumn;
	}

	public JButton getAddBtn() {
		return addBtn;
	}

	public void setAddBtn(JButton addBtn) {
		this.addBtn = addBtn;
	}

	public JPanel getMonColumn() {
		return monColumn;
	}

	public void setMonColumn(JPanel monColumn) {
		this.monColumn = monColumn;
	}

	public JPanel getTueColumn() {
		return tueColumn;
	}

	public void setTueColumn(JPanel tueColumn) {
		this.tueColumn = tueColumn;
	}

	public JPanel getWedColumn() {
		return wedColumn;
	}

	public void setWedColumn(JPanel wedColumn) {
		this.wedColumn = wedColumn;
	}

	public JPanel getThuColumn() {
		return thuColumn;
	}

	public void setThuColumn(JPanel thuColumn) {
		this.thuColumn = thuColumn;
	}

	public JPanel getFriColumn() {
		return friColumn;
	}

	public void setFriColumn(JPanel friColumn) {
		this.friColumn = friColumn;
	}
	
}

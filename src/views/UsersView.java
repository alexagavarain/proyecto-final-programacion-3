package views;

import java.awt.BorderLayout;
import java.io.File;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import config.Config;

import javax.swing.filechooser.FileNameExtensionFilter;
import tablemodel.UserTableModel;
import utils.AppColors;
import utils.CreateFont;

public class UsersView extends JPanel{

	private JTable table;
	private JButton btnEdit;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnPdf;
	
	public UsersView() {
		setLayout(new BorderLayout());
		table = new JTable();
		styleTable();
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnAdd = new JButton("Agregar");
        btnEdit = new JButton("Editar");
        btnDelete = new JButton("Eliminar");
        btnPdf = new JButton("Exportar a PDF");

        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnPdf);
        
        add(panelButtons, BorderLayout.NORTH);

	}
	
	public void styleTable() {
		table.setRowHeight(27);
		table.setShowGrid(true);
		//Lineas
		table.setGridColor(new Color(230, 230, 230));
		table.setBackground(AppColors.background);
		table.setForeground(Color.BLACK);
		table.setFont(CreateFont.DEFAULT.deriveFont(14f));
		
		table.setSelectionBackground(new Color(231, 238, 252));
		table.setSelectionForeground(new Color(75, 116, 204));
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(AppColors.subtitle);
		header.setForeground(Color.WHITE);
		header.setFont(CreateFont.DEFAULT_BOLD.deriveFont(14f));
		header.setPreferredSize(new Dimension(0, 30));
		header.setReorderingAllowed(false);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c = super.getTableCellRendererComponent(
                        table,
                        value,
                        isSelected,
                        hasFocus,
                        row,
                        column);
				
				if(column == 0) {
					c.setFont(CreateFont.DEFAULT_BOLD);
					if(!isSelected) {
						c.setForeground(AppColors.primaryAccent);
					}
				} else {
					if (!isSelected) {
						c.setForeground(Color.BLACK);
					}
					c.setFont(CreateFont.DEFAULT);
				}
			
				
				return c;
				
			}
			
		});	
	}
	
	public void setTableModel(UserTableModel model) {
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);	
		table.getColumnModel().getColumn(2).setPreferredWidth(200);	

		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		
		table.getColumnModel().getColumn(2).setCellRenderer(center);
		table.getColumnModel().getColumn(3).setCellRenderer(center);
		table.getColumnModel().getColumn(4).setCellRenderer(center);
	}
	public File selectPdfFile() {
			
			String path = Config.get("users.export.pdf", System.getProperty("user.home"));
			JFileChooser chooser = new JFileChooser(path);
			
			chooser.setSelectedFile(new File("reporte-usuarios.pdf"));
			
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos PDF",  "pdf");
			chooser.addChoosableFileFilter(filter);
			chooser.setFileFilter(filter);
			
			int option = chooser.showDialog(this, "Exportar PDF de usuarios");
			
			if(option != JFileChooser.APPROVE_OPTION) {
				return null;
			}
			
			File file = chooser.getSelectedFile();
			Config.set("users.export.pdf", file.getParent());
			
			if(!file.getName().toLowerCase().endsWith(".pdf")) {
				file = new File(file.getAbsolutePath() + ".pdf");
			}
			
			return file;
		}
	
	public JButton getBtnPdf() {
	    	return btnPdf;
	    }
	public JTable getTable() {
		return table;
	}
	
	public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }
	
    public int getSelectedRow() {
    	return table.getSelectedRow();
    }
}



















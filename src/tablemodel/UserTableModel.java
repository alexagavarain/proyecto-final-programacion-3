package tablemodel;

import java.util.List;
import models.User;
import javax.swing.table.AbstractTableModel;

public class UserTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<User> users;
	
	private final String[] columns = {
			"Correo",
			"Nombre",
			"Carrera",
			"Turno",
			"Grupo"
		};
		
		public UserTableModel(List<User> users) {
			this.users = users;
		}
		
		@Override
		public int getRowCount() {
			return users.size();
		}

		@Override
		public int getColumnCount() {
			return columns.length;
		}
		
		@Override
		public String getColumnName(int column) {
			return columns[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			User user = users.get(rowIndex);
			
			switch(columnIndex) {
			case 0:
				return user.getEmail();
			case 1:
				return user.getName();
			case 2:
				return user.getGroup().getCareer().getName();
			case 3:
				return user.getGroup().getShift();
			case 4:
				return user.getGroup().getName();
			}
			
			return null;
			
		}
		public User getUserAt(int row) {
			return users.get(row);
		}
		
		public void setUsers(List<User> users) {
			this.users = users;
			fireTableDataChanged();
		}

}

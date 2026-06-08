package tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.User;

public class UserTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private List<User> users;
    private final String[] columns = {"ID", "Nombre", "Correo", "Semestre", "Grupo", "Turno", "Carrera"};

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

        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getName();
            case 2:
                return user.getEmail();
            case 3:
                return (user.getGroup() != null) ? user.getGroup().getSemester() : "N/A";
            case 4:
                return (user.getGroup() != null) ? user.getGroup().getName() : "Sin grupo";
            case 5:
                return (user.getGroup() != null) ? user.getGroup().getShift() : "N/A";
            case 6:
                return (user.getGroup() != null && user.getGroup().getCareer() != null) 
                        ? user.getGroup().getCareer().getName() 
                        : "Sin carrera";
            default:
                return null;
        }
    }

    public User getUserAt(int rowIndex) {
        return users.get(rowIndex);
    }

    public void setUsers(List<User> users) {
        this.users = users;
        fireTableDataChanged();
    }
}
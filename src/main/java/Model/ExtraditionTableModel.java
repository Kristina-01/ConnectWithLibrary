package Model;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExtraditionTableModel extends AbstractTableModel {

   // private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private String[] columnNames = {
            "Фамилия читателя",
            "Имя читателя",
            "№ читательского билета",
            "Шифр книги",
            "Книга",
            "Автор",
            "Дата взятия",
            "Дата возврата"
    };

    private List<Extradition> extraditions;

    public  ExtraditionTableModel(List<Extradition> extraditions){
        this.extraditions=extraditions;
    }

    @Override
    public int getRowCount() {
        return extraditions.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }


    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Extradition getExtradition(int row){
        return extraditions.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Extradition extradition = getExtradition(rowIndex);
        switch (columnIndex){
            case 0:
                return extradition.getReader_name();
            case 1:
                return extradition.getReader_surname();
            case 2:
                return extradition.getReader_numbeer_card();
            case 3:
                return extradition.getCopy_book_id();
            case 4:
                return extradition.getBook_name();
            case 5:
                return extradition.getAuthor();
            case 6:
                return extradition.getExtradition_date_of_issue();
            case 7:
                return extradition.getExtradition_return_date();
        }
        return "";

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Extradition extradition = getExtradition(rowIndex);
            switch (columnIndex){
                case 0:
                     extradition.setReader_name((String) aValue);break;
                case 1:
                     extradition.setReader_surname((String) aValue);break;
                case 2:
                     extradition.setReader_numbeer_card((String) aValue);break;
                case 3:
                     extradition.setCopy_book_id((String) aValue);break;
                case 4:
                     extradition.setBook_name((String) aValue);break;
                case 5:
                     extradition.setAuthor((String) aValue);break;
                case 6:
                     extradition.setExtradition_date_of_issue((String) aValue);break;
                case 7:
                     extradition.setExtradition_return_date((String) aValue);break;
            }
        fireTableCellUpdated(rowIndex, columnIndex);
    }


    public void insertExtradition(int row, Extradition extradition) {
        extraditions.add(row,extradition);
        fireTableRowsInserted(row,row);
    }

    public void addExtradition( Extradition extradition) {
        insertExtradition(getRowCount(),extradition);
    }
}

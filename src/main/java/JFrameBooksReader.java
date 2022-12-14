import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JFrameBooksReader extends JFrame{
    Color clr1 = new Color(190, 209, 232);
    String url = "jdbc:postgresql://127.0.0.1:5432/library";
    public String name;
    String password = null;
    Connection connection= null;

    public  JFrameBooksReader() throws ClassNotFoundException, SQLException {
        super("Мои книги");
        super.setSize(1000,450);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout(0,0));
        setResizable(false);

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel){
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        table.setSelectionForeground(new Color (203,221,245));
        table.setSelectionBackground(new Color(70,136,227));

        Button back = new Button("На глаавную");
        back.addActionListener(new ButtonBack());
        back.setPreferredSize(new Dimension(150,40));

        tableModel.addColumn("Название");
        tableModel.addColumn("Дата взятия");
        tableModel.addColumn("Дата возврата");

        String l = Main.Data.data != null ? Main.Data.data.login : "";
        String p = Main.Data.data != null ? Main.Data.data.password : "";
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url,name,p);

        Statement statement = null;
        statement = connection.createStatement();

        String sql1 = "SELECT ex_book, ex_date_of_issue, ex_return_date FROM reader_view_their_extradition('"+l +"');";
        var tmpres1 = statement.executeQuery(sql1);

        while (tmpres1.next())
        {
            Object [] objects = new Object[]{
                    tmpres1.getString(1),
                    tmpres1.getString(2),
                    tmpres1.getString(3),

            };
            tableModel.insertRow(0,objects);
            objects = null;

        }

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setPreferredSize(new Dimension(900,350));
        add(jScrollPane);
        add(back);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        super.getContentPane().setBackground(clr1);
        super.setVisible(true);

    }

    class ButtonBack implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            JFrameMainWindowReader jFrameReader = new JFrameMainWindowReader();
        }
    }


}

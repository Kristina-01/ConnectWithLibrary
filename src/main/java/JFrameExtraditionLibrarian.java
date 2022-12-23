import Model.Data;
import Model.Extradition;
import Model.ExtraditionTableModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.postgresql.util.JdbcBlackHole.close;

public class JFrameExtraditionLibrarian extends JFrame {

    Color clr1 = new Color(190, 209, 232);
    String url = "jdbc:postgresql://127.0.0.1:5432/library";
    public String name;
    String password = null;
    Connection connection= null;
    TableModel tableModel = null;
    JTable table = null;

    private  void GetDataAndLoader()
    {
        ArrayList<Extradition> extraditions = new ArrayList<>();

        // запрос
        String sql1 = "select * from view_extradition\n";

        String l = Main.Data.data != null ? Main.Data.data.login : "";
        String p = Main.Data.data != null ? Main.Data.data.password : "";
        Statement statement = null;
        ResultSet tmpres1 = null;


        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,name,p);
            statement = connection.createStatement();
            tmpres1 = statement.executeQuery(sql1);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


        try {


            if (tmpres1 != null) {
                while (tmpres1.next()) {
                    String authorname = tmpres1.getString(6);
                    String authorsurname = tmpres1.getString(7);
                    String authorpatrinymic = tmpres1.getString(8);
                    String authordone = authorname +" " + authorsurname+" " +authorpatrinymic;
                    extraditions.add(new Extradition(tmpres1.getString(1),
                            tmpres1.getString(2),
                            tmpres1.getString(3),
                            tmpres1.getString(4),
                            tmpres1.getString(5),
                            authordone,
                            tmpres1.getString(9),
                            tmpres1.getString(10)));


                }
            }
            tableModel = new ExtraditionTableModel(extraditions);
            table = new JTable(tableModel);
            JScrollPane jScrollPane = new JScrollPane(table);
            getContentPane().add(jScrollPane);
            jScrollPane.setPreferredSize(new Dimension(900,350));
            add(jScrollPane);
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  JFrameExtraditionLibrarian() throws ClassNotFoundException, SQLException {
        super("Просмотр выдачи всех книг");
        super.setSize(1000,450);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout(10,0));
        setResizable(false);

        GetDataAndLoader();

        Button returnbook = new Button("Вернуть книгу");
        returnbook.setForeground(new Color(70,130,180));
        returnbook.setPreferredSize(new Dimension(150,40));
        returnbook.addActionListener(new ReturnBook());

        Button back = new Button("На глаавную");
        back.addActionListener(new ButtonBack());
        back.setForeground(new Color(70,130,180));
        back.setPreferredSize(new Dimension(150,40));

        Button newExtadition = new Button("Выдать книгу ");
        newExtadition.addActionListener(new ButtonNewExtadition());
        newExtadition.setForeground(new Color(70,130,180));
        newExtadition.setPreferredSize(new Dimension(150,40));


        add(returnbook);
        add(back);
        add(newExtadition);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        super.getContentPane().setBackground(clr1);
        super.setVisible(true);
    }

    public void _JFrameExtraditionLibrarian() throws ClassNotFoundException, SQLException {
        //super("Просмотр выдачи всех книг");
        super.setSize(1000,450);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout(10,0));
        setResizable(false);


        Button back = new Button("На глаавную");
        back.addActionListener(new ButtonBack());
        back.setForeground(new Color(70,130,180));
        back.setPreferredSize(new Dimension(150,40));

        Button newExtadition = new Button("Выдать книгу ");
        newExtadition.addActionListener(new ButtonNewExtadition());
        newExtadition.setForeground(new Color(70,130,180));
        newExtadition.setPreferredSize(new Dimension(150,40));

        String l = Main.Data.data != null ? Main.Data.data.login : "";
        String p = Main.Data.data != null ? Main.Data.data.password : "";
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url,name,p);

        Statement statement = null;
        statement = connection.createStatement();

        String sql1 = "SELECT reader_surname,reader_name, reader_number_card,copy_book_id, book_name,(author_name, author_patrinymic, author_surname)AS author1, extradition_date_of_issue, extradition_return_date \n" +
                "FROM extradition\n" +
                "JOIN reader ON (extradition_reader = reader_id)\n" +
                "JOIN copy_book ON (copy_book_id = extradition_book)\n" +
                "JOIN book ON (copy_book_book = book_id)\n" +
                "JOIN author_book ON (ab_book = book_id)\n" +
                "JOIN author ON (author_id= ab_author)";
        var tmpres1 = statement.executeQuery(sql1);


        //Main.Extradition.extradition.Init();
        while (tmpres1.next())
        {
            Object [] objects = new Object[]{
                            tmpres1.getString(1),
                            tmpres1.getString(2),
                            tmpres1.getString(3),
                            tmpres1.getString(4),
                            tmpres1.getString(5),
                            tmpres1.getString(6),
                            tmpres1.getString(7),
                            tmpres1.getString(8)

            };
           // tableModel.insertRow(0,objects);
            objects = null;

        }


        Button returnbook = new Button("Вернуть книгу");
        returnbook.setForeground(new Color(70,130,180));
        returnbook.setPreferredSize(new Dimension(150,40));
        returnbook.addActionListener(new ReturnBook());


        Statement finalStatement = statement;
        Statement finalStatement1 = statement;
        returnbook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                String value = table.getModel().getValueAt(row, 3).toString();
                System.out.println(value);

                String sql = "CALL update_date_return("+ value + ")";
                //CallableStatement cstmt = null;
                try {
                    connection.prepareCall(sql);
                   // cstmt.executeQuery();
                } catch (SQLException ex) {
                   // tableModel.fireTableCellUpdated(row, 3);
                    throw new RuntimeException(ex);
                }

            }

        });

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setPreferredSize(new Dimension(900,350));
        add(jScrollPane);
        add(returnbook);
        add(back);
        add(newExtadition);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        super.getContentPane().setBackground(clr1);
        super.setVisible(true);

    }



    class ButtonBack implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            JFrameMainWindowLibrarian jFrameReader = new JFrameMainWindowLibrarian();
        }
    }

    class ButtonNewExtadition implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            try {
                JFrameNewExtradition jFrameNewExtradition = new JFrameNewExtradition();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    class ReturnBook implements ActionListener{
        Connection connection= null;
        String url = "jdbc:postgresql://127.0.0.1:5432/library";
        public String name;
        String password = null;
        @Override
        public void actionPerformed(ActionEvent e) {

            int row = table.getSelectedRow();
            String value = tableModel.getValueAt(row, 3).toString();
            System.out.println(value);

            String l = Main.Data.data != null ? Main.Data.data.login : "";
            String p = Main.Data.data != null ? Main.Data.data.password : "";
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url,name,p);

                PreparedStatement statement1 = null;


                String sql = "CALL update_date_return(?)";
                statement1 = connection.prepareStatement(sql);
                statement1.setInt(1, Integer.parseInt(value));
                statement1.executeUpdate();


                String localDate = LocalDate.now().toString();

                tableModel.setValueAt(localDate, row, 7);


            } catch (ClassNotFoundException |SQLException ex) {

                throw new RuntimeException(ex);
            }
            finally {
                close(connection);
            }
        }
    }
}




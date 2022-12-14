import ModelView.ExtraditionService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static org.postgresql.util.JdbcBlackHole.close;

public class JFrameExtraditionLibrarian extends JFrame {

    Color clr1 = new Color(190, 209, 232);
    String url = "jdbc:postgresql://127.0.0.1:5432/library";
    public String name;
    String password = null;
    Connection connection= null;


    DefaultTableModel tableModel = null;
    JTable table = null;

    private  void GetDataAndLoader()
    {
        tableModel = null;
        table = null;
        // очистка таблицы
        if(tableModel == null) {
            tableModel = new DefaultTableModel();
             table = new JTable(tableModel) {
                private static final long serialVersionUID = 1L;
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            tableModel.addColumn("Фамилия читателя");
            tableModel.addColumn("Имя читателя");
            tableModel.addColumn("№ читательского билета");
            tableModel.addColumn("Шифр книги");
            tableModel.addColumn("Книга");
            tableModel.addColumn("Автор");
            tableModel.addColumn("Дата взятия");
            tableModel.addColumn("Дата возврата");


        }else tableModel.setRowCount(0);
        if(table == null) table = new JTable(tableModel) {
        private static final long serialVersionUID = 1L;
        public boolean isCellEditable ( int row, int column){
            return false;
        }
    };
        // запрос
        String sql1 = "SELECT reader_surname,reader_name, reader_number_card,copy_book_id, book_name,(author_name, author_patrinymic, author_surname)AS author1, extradition_date_of_issue, extradition_return_date \n" +
                "FROM extradition\n" +
                "JOIN reader ON (extradition_reader = reader_id)\n" +
                "JOIN copy_book ON (copy_book_id = extradition_book)\n" +
                "JOIN book ON (copy_book_book = book_id)\n" +
                "JOIN author_book ON (ab_book = book_id)\n" +
                "JOIN author ON (author_id= ab_author)";

        String l = Main.Data.data != null ? Main.Data.data.login : "";
        String p = Main.Data.data != null ? Main.Data.data.password : "";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,name,p);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        Statement statement = null;
        ResultSet tmpres1 = null;
        try {
            statement = connection.createStatement();
             tmpres1 = statement.executeQuery(sql1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
           // table.setModel(tableModel);
            if (tmpres1 != null) {
                while (tmpres1.next()) {
                    Object[] objects = new Object[]{
                            tmpres1.getString(1),
                            tmpres1.getString(2),
                            tmpres1.getString(3),
                            tmpres1.getString(4),
                            tmpres1.getString(5),
                            tmpres1.getString(6),
                            tmpres1.getString(7),
                            tmpres1.getString(8)

                    };
                    tableModel.insertRow(0, objects);
                }
            }

            JScrollPane jScrollPane = new JScrollPane(table);
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


        add(returnbook);
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
/*
        DefaultTableModel tableModel = new DefaultTableModel();
         JTable table = new JTable(tableModel){
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        table.setSelectionForeground(new Color (203,221,245));
        table.setSelectionBackground(new Color(70,136,227));

        tableModel.addColumn("Фамилия читателя");
        tableModel.addColumn("Имя читателя");
        tableModel.addColumn("№ читательского билета");
        tableModel.addColumn("Шифр книги");
        tableModel.addColumn("Книга");
        tableModel.addColumn("Автор");
        tableModel.addColumn("Дата взятия");
        tableModel.addColumn("Дата возврата");

 */

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
            tableModel.insertRow(0,objects);
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
/*
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                model.setRowCount(0);

                int row = table.getSelectedRow();
                String value = table.getModel().getValueAt(row, 3).toString();
                System.out.println(value);
                String sql = "CALL update_date_return("+ value + ")";

                String sql1 = "SELECT reader_surname,reader_name, reader_number_card,copy_book_id, book_name,(author_name, author_patrinymic, author_surname)AS author1, extradition_date_of_issue, extradition_return_date \n" +
                        "FROM extradition\n" +
                        "JOIN reader ON (extradition_reader = reader_id)\n" +
                        "JOIN copy_book ON (copy_book_id = extradition_book)\n" +
                        "JOIN book ON (copy_book_book = book_id)\n" +
                        "JOIN author_book ON (ab_book = book_id)\n" +
                        "JOIN author ON (author_id= ab_author)";

                try {
                    var tmpres1 = finalStatement.executeQuery(sql);
                    var tmpres2 = finalStatement1.executeQuery(sql1);

                    tableModel.setRowCount(0);
                    while (tmpres2.next())
                    {
                        Object [] objects = new Object[]{
                                tmpres2.getString(1),
                                tmpres2.getString(2),
                                tmpres2.getString(3),
                                tmpres2.getString(4),
                                tmpres2.getString(5),
                                tmpres2.getString(6),
                                tmpres2.getString(7),
                                tmpres2.getString(8),
                        };
                        model.insertRow(0,objects);
                        objects = null;

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

 */
                int row = table.getSelectedRow();
                String value = table.getModel().getValueAt(row, 3).toString();
                System.out.println(value);

                String sql = "CALL update_date_return("+ value + ")";
                //CallableStatement cstmt = null;
                try {
                    connection.prepareCall(sql);
                   // cstmt.executeQuery();
                } catch (SQLException ex) {
                    tableModel.fireTableCellUpdated(row, 3);
                    throw new RuntimeException(ex);
                }
               // TableModel model = table.getModel();




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
            String value=null;
            int row = table.getSelectedRow();
            System.out.println(row);
            value = table.getModel().getValueAt(row, 3).toString();
            //System.out.println(value);

            String l = Main.Data.data != null ? Main.Data.data.login : "";
            String p = Main.Data.data != null ? Main.Data.data.password : "";
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url,name,p);

                Statement statement = null;
                statement = connection.createStatement();

                String sql = "UPDATE extradition SET extradition_return_date = CURRENT_DATE";
                var resultSet = statement.executeUpdate(sql);

                //CallableStatement cstmt = connection.prepareCall(sql);
                //cstmt.executeQuery();
            } catch (ClassNotFoundException |SQLException ex) {

                throw new RuntimeException(ex);
            }
            finally {
                close(connection);
            }
            GetDataAndLoader();

        }



    }



}




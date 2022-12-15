import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JFrameNewExtradition extends JFrame {
    Color clr1 = new Color(190, 209, 232);
    String url = "jdbc:postgresql://127.0.0.1:5432/library";
    public String name;
    String password = null;
    Connection connection= null;
    JLabel number_card = new JLabel("№ читательского билета:");
    JTextField insert_nc = new JTextField();
    JLabel number_book = new JLabel("Шифр книги:");
    JTextField insert_nb = new JTextField();
    JButton button = new JButton("Добавить");
    Button back = new Button("Назад");
    JLabel errortext;
    public JFrameNewExtradition() throws ClassNotFoundException, SQLException {
        super("Выдача книг");
        super.setSize(1000,450);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(null);
        setResizable(false);

        number_card.setBounds(350,150, 200,40);
        insert_nc.setBounds(520,150,150,40);

        number_book.setBounds(350,200, 150,40);
        insert_nb.setBounds(520,200,150,40);

        back.setForeground(new Color(70,130,180));
        back.setBounds(395,250,100,40);
        back.addActionListener(new BackEx());

        button.setForeground(new Color(70,130,180));
        button.setBounds(515,250,100,40);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String l = Main.Data.data != null ? Main.Data.data.login : "";
                String p = Main.Data.data != null ? Main.Data.data.password : "";
                boolean hm = true;
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url,name,p);

                   try(CallableStatement statement = connection.prepareCall("call create_extradition(?,?,?)")) {
                        statement.setInt(1, Integer.parseInt(insert_nc.getText()));
                        statement.setInt(2, Integer.parseInt(insert_nb.getText()));
                        statement.setString(3, Main.Data.data.login);
                        statement.execute();
                    };

                } catch (ClassNotFoundException | SQLException ex) {

                    throw new RuntimeException(ex);
                }
            }
        });



        add(back);
        add(number_card);
        add(insert_nc);
        add(number_book);
        add(insert_nb);
        add(button);
        getContentPane().setBackground(clr1);
        super.setVisible(true);
    }

    class Insertextradition implements ActionListener{
        JLabel errortext;
        @Override
        public void actionPerformed(ActionEvent e) {
            String l = Main.Data.data != null ? Main.Data.data.login : "";
            String p = Main.Data.data != null ? Main.Data.data.password : "";
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url,name,p);
                Statement statement = null;
                statement = connection.createStatement();
                System.out.println(insert_nc.getText());//номер читатальского билета
                System.out.println(insert_nb.getText());//шифр книги
                System.out.println(Main.Data.data.login);

                String query = "CALL create_extradition(" + insert_nc.getText() + "," + insert_nb.getText() + ",'" + Main.Data.data.login + "')";
                int resultSet = statement.executeUpdate(query);

                //Extradition extradition = new Extradition();

            } catch (ClassNotFoundException | SQLException ex) {
                errortext = new JLabel("Данные введены неверно");
                errortext.setForeground(new Color(227,66,52));
                add(errortext);
                throw new RuntimeException(ex);
            }
        }
    }

    class BackEx implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            try {
                JFrameExtraditionLibrarian jFrameExtraditionLibrarian = new JFrameExtraditionLibrarian();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}

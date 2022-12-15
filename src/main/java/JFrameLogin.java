import Model.Data;
import Model.Reader;
import org.apache.commons.codec.binary.Hex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



public class JFrameLogin  extends JFrame{
    Color clr1 = new Color(190, 209, 232);
    JLabel l = new JLabel("Логин:");
    JTextField login = new JTextField();
    JLabel p = new JLabel("Пароль:");
    JTextField r_password = new JPasswordField();
    JButton button = new JButton("Войти");


    public JFrameLogin(){


        super("Welcome");
        setSize(1000,450);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(null);
        setResizable(false);

        l.setBounds(350,150, 150,40);
        login.setBounds(420,150,150,40);


        p.setBounds(350,200, 150,40);
        r_password.setBounds(420,200,150,40);

        button.setForeground(new Color(70,130,180));

        button.setBounds(430,250,100,40);
        button.addActionListener(new BAction());

        super.add(login);
        super.add(l);
        super.add(p);
        super.add(r_password);
        super.add(button);

        super.getContentPane().setBackground(clr1);
        super.setVisible(true);
    }
    static Reader r;
    class BAction implements ActionListener{
        String url = "jdbc:postgresql://127.0.0.1:5432/library";
        public String name;
        String password = null;
        Connection connection= null;


        JLabel errortext;
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);

            name = login.getText();
            password = r_password.getText();
            HashPassword hashPassword = new HashPassword(password);
            byte[] hashedBytes = hashPassword.hashPassword();
            String hashedString = Hex.encodeHexString(hashedBytes);

            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url,name,hashedString);

                Statement statement = null;
                statement = connection.createStatement();

                String check1 = "SELECT COUNT(*)=1 AS b FROM reader WHERE reader_login ='"+ name+"'"+ "AND reader_password=" +"'"+ hashedString +"'";
                String check2 = "SELECT COUNT(*)=1 AS b FROM employee WHERE ((employee_login = '"+ name+"') AND (employee_post = 'librarian') AND (employee_password="+"'"+ hashedString +"'))";
                String check3 = "SELECT COUNT(*)=1 as b FROM employee WHERE ((employee_login = '"+ name+"') AND (employee_post = 'cheif_librarian') AND (employee_password="+"'"+ hashedString +"'))";



                var tmpres1 = statement.executeQuery(check1);
                boolean isres1= false;
                while (tmpres1.next()) {
                     Reader r = new Reader();
                     r.Init(connection,name);
                     Main.TOwn.reader = r;
                     isres1 = tmpres1.getBoolean(1);
                     Data data = new Data(name, hashedString);
                     Main.Data.data =data;
                }

                boolean isres2 = false;
                if (isres1==false){
                    var tmpres2 = statement.executeQuery(check2);
                    while (tmpres2.next()) {
                        isres2 = tmpres2.getBoolean(1);
                        Data data = new Data(name, hashedString);
                        Main.Data.data =data;
                    }
                }

                boolean isres3 = false;
                if((isres1==false) & (isres2==false)){
                    var tmpres3 = statement.executeQuery(check3);
                    while (tmpres3.next()) {
                        isres3 = tmpres3.getBoolean(1);
                    }
                }
                if(isres1){
                    JFrameMainWindowReader jFrameReader = new JFrameMainWindowReader();
                }
                else if (isres2){
                    JFrameMainWindowLibrarian jFrameLibrarian = new JFrameMainWindowLibrarian();
                }
                else if(isres3){
                    JFrameMainWindowCheifLibrarian jFrameСрушаLibrarian = new JFrameMainWindowCheifLibrarian();
                }

            } catch (Exception ex ) {
                errortext = new JLabel("Данные введены неверно");
                errortext.setBounds(385,100, 200,50);
                errortext.setForeground(new Color(227,66,52));
                add(errortext);
                setVisible(true);
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

            }finally {
                if(connection!=null){
                    try {
                        connection.close();
                    }catch (SQLException ex){
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }



        }
    }




}

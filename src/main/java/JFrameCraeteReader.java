import org.apache.commons.codec.binary.Hex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class JFrameCraeteReader extends JFrame {


    JLabel name;
    JTextField r_name;

    JLabel surname;
    JTextField r_surname;

    JLabel patronymic;
    JTextField r_patronymic;

    JLabel dateofbirth;
    JTextField r_dateofbirth;

    JLabel address;
    JTextField r_address;

    JLabel telephone;
    JTextField r_telephone;

    JLabel passportseries;
    JTextField r_passportseries;

    JLabel passportnumbers;
    JTextField r_passportnumbers;

    JLabel cardnumber;
    JTextField r_cardnumber;

    JLabel email;
    JTextField r_email;

    JLabel login;
    JTextField r_login;

    JLabel password;
    JTextField r_password;

    Button addreader;

    Color clr1 = new Color(190, 209, 232);
    String url = "jdbc:postgresql://127.0.0.1:5432/library";
    Connection connection= null;

    public JFrameCraeteReader() throws ClassNotFoundException, SQLException {
        super("Новый читатель");
        setSize(1000,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(clr1);

        name = new JLabel("Имя");
        r_name =  new JTextField();
        r_name.setSize(100,20);

        surname = new JLabel("Фамилия");
        r_surname = new JTextField();

        patronymic = new JLabel("Отчество");
        r_patronymic = new JTextField();

        dateofbirth = new JLabel("Дата рождания");
        r_dateofbirth = new JTextField();

        address = new JLabel("Адрес проживания");
        r_address = new JTextField();

        telephone = new JLabel("Номер телефона");
        r_telephone = new JTextField();

        passportseries = new JLabel("Серия паспорта");
        r_passportseries = new JTextField();

        passportnumbers = new JLabel("Номер паспорта");
        r_passportnumbers = new JTextField();

        cardnumber = new JLabel("№ читательского билета");
        r_cardnumber = new JTextField();

        email = new JLabel("Почта");
        r_email = new JTextField();

        login = new JLabel("Логин");
        r_login = new JTextField();

        password = new JLabel("Пароль");
        r_password = new JTextField();

        addreader = new Button("Зарегестрировать");
        addreader.addActionListener(new AddReader());

        add(name);
        add(r_name);

        add(surname);
        add(r_surname);

        add(patronymic);
        add(r_patronymic);

        add(dateofbirth);
        add(r_dateofbirth);

        add(address);
        add(r_address);

        add(telephone);
        add(r_telephone);

        add(passportseries);
        add(r_passportseries);

        add(passportnumbers);
        add(r_passportnumbers);

        add(cardnumber);
        add(r_cardnumber);

        add(email);
        add(r_email);

        add(login);
        add(r_login);

        add(password);
        add(r_password);

        add(addreader);

        setLayout(new GridLayout(13,2));
        setVisible(true);

    }

    public class AddReader implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            HashPassword hashPassword = new HashPassword(r_password.getText());
            byte[] hashedBytes = hashPassword.hashPassword();
            String hashedString = Hex.encodeHexString(hashedBytes);

            String ourdate = r_dateofbirth.getText();



            PreparedStatement preparedStatement = null;

            String sql = "CALL create_reader(?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                //java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(ourdate);
                //java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url,Main.Data.data.login, Main.Data.data.password);
                preparedStatement = connection.prepareStatement(sql);

               /* String string_date = "12-December-2001";
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                long milliseconds;
                try{
                    Date d = f.parse(string_date);
                     milliseconds =d.getTime();
                } catch (ParseException e){
                    e.printStackTrace();
                }

                */
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Date utildate =new Date(0) ;
                java.sql.Date date = new Date(utildate.getTime());

                String sd= new String(r_dateofbirth.getText());
                long milliseconds = LocalDate.parse(sd, dateFormat).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
                Date donedate = new Date(milliseconds);
                java.sql.Date dd= new Date(donedate.getTime());

                preparedStatement.setString(1, r_name.getText());
                preparedStatement.setString(2,r_surname.getText());
                preparedStatement.setString(3,  r_patronymic.getText());
                preparedStatement.setDate(4, dd);
                preparedStatement.setString(5, r_address.getText());
                preparedStatement.setString(6, r_telephone.getText());
                preparedStatement.setShort(7,Short.valueOf(r_passportseries.getText()));
                preparedStatement.setInt(8, Integer.parseInt(r_passportnumbers.getText()));
                preparedStatement.setString(9, r_email.getText());
                preparedStatement.setInt(10, Integer.parseInt(r_cardnumber.getText()));
                preparedStatement.setString(11,r_login.getText());
                preparedStatement.setString(12, hashedString);

/*
                preparedStatement.setString(1, r_surname.getText());
                preparedStatement.setString(2,r_name.getText());
                preparedStatement.setString(3, r_patronymic.getText());
                preparedStatement.setDate(4, java.sql.Date.valueOf(r_dateofbirth.getText()));
                preparedStatement.setString(5, r_address.getText());
                preparedStatement.setString(6, r_telephone.getText());
                preparedStatement.setObject(7, Integer.parseInt(r_passportseries.getText()));
                preparedStatement.setInt(8, Integer.parseInt(r_passportnumbers.getText()));
                preparedStatement.setString(9, r_email.getText());
                preparedStatement.setInt(10, Integer.parseInt(r_cardnumber.getText()));
                preparedStatement.setString(11,r_login.getText());
                preparedStatement.setString(12, hashedString);

 */

                preparedStatement.executeUpdate();

            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }









        }
    }



}

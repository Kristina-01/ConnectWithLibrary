import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class JFrameReader extends JFrame  {
    Color clr1 = new Color(190, 209, 232);

    JFrameReader(){
        super("Profile");
        setSize(500,350);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout(0,0));
        setResizable(false);


        JLabel Lname = new JLabel("Имя: ");
        String name = Main.TOwn.reader != null ? Main.TOwn.reader.res_.get(0).name : "";
        JLabel r_name = new JLabel(name);

        JLabel Lsurname = new JLabel("Фамилия: ");
        String surname = Main.TOwn.reader != null ? Main.TOwn.reader.res_.get(0).surname : "";
        JLabel r_surname = new JLabel(surname);

        JLabel Lpatrinymic = new JLabel("Отчество: ");
        String patrinymic = Main.TOwn.reader != null ? Main.TOwn.reader.res_.get(0).patrinymic : "";
        JLabel r_patrinymic = new JLabel(patrinymic);

        JLabel Ldateofbirth = new JLabel("Дата Рождения: ");
        String dateofbirth = Main.TOwn.reader != null ? Main.TOwn.reader.res_.get(0).date_of_birth : "";
        JLabel r_dateofbirth = new JLabel(dateofbirth);

        JLabel Lactual_address = new JLabel("Адрес проживания: ");
        String actual_address = Main.TOwn.reader != null ? Main.TOwn.reader.res_.get(0).actual_address : "";
        JLabel r_actual_address = new JLabel(actual_address);

        JLabel Ltelephone_number = new JLabel("Номер телефона: ");
        String telephone_number = Main.TOwn.reader != null ? Main.TOwn.reader.res_.get(0).telephone : "";
        JLabel r_telephone_number = new JLabel(telephone_number);

        JLabel Lemail = new JLabel("E-mail: ");
        String email = Main.TOwn.reader != null ? Main.TOwn.reader.res_.get(0).email : "";
        JLabel r_email = new JLabel(email);

        JLabel Llibrary_card_number = new JLabel("Номер читательского билета: ");
        String card_number = Main.TOwn.reader != null ? Main.TOwn.reader.res_.get(0).number_card : "";
        JLabel r_library_card_number = new JLabel(card_number);

        Button back = new Button("На главную");
        back.addActionListener(new ButtonBack());

        add(Lname);
        add(r_name);

        add(Lsurname);
        add(r_surname);

        add(Lpatrinymic);
        add(r_patrinymic);

        add(Ldateofbirth);
        add(r_dateofbirth);

        add(Lactual_address);
        add(r_actual_address);

        add(Ltelephone_number);
        add(r_telephone_number);

        add(Lemail);
        add(r_email);

        add(Llibrary_card_number);
        add(r_library_card_number);

        add(back);

        setLayout(new GridLayout(9,2));

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

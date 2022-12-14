import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class JFrameMainWindowReader extends JFrame {
    Color clr1 = new Color(190, 209, 232);
    Owner own;

    public JFrameMainWindowReader(){


        super("Главная");
        super.setSize(1000,450);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Connection connection1 = connection;
        this.own = own;
        setResizable(false);


        JPanel panel = new JPanel(new GridLayout(3,1,10,40));
        panel.setBackground(clr1);

        Button button1 = new Button("Профиль");
        button1.setPreferredSize(new Dimension(300,60));
        button1.addActionListener(new ButtonProfile());
        Button button2 = new Button("Мои книги");
        button2.setPreferredSize(new Dimension(300,60));
        button2.addActionListener(new ButtonMyBooks());
        Button button3 = new Button("Книги в библиотеки");
        button3.setPreferredSize(new Dimension(300,60));
        button3.addActionListener(new ButtonAllBooks());

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        super.setLayout(new GridBagLayout());
        super.getContentPane().add(panel);
        super.getContentPane().setBackground(clr1);
        super.setVisible(true);

    }



    class ButtonProfile implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);

            JFrameReader jFrameReader = new JFrameReader();
        }
    }

    class ButtonAllBooks implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);

            try {
                AllBookForReaderJFrame allBookForReaderJFrame = new AllBookForReaderJFrame();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class ButtonMyBooks implements ActionListener{
        public  void actionPerformed(ActionEvent e){
            setVisible(false);

            try {
                JFrameBooksReader jFrameBooksReader = new JFrameBooksReader();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


}

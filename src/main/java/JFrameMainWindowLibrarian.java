import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class JFrameMainWindowLibrarian extends JFrame {
    Color clr1 = new Color(190, 209, 232);

    public JFrameMainWindowLibrarian(){
        super("Главная");
        super.setSize(1000,450);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3,1,10,40));
        panel.setBackground(clr1);
        setResizable(false);


        Button button1 = new Button("Выдача");
        button1.setPreferredSize(new Dimension(350,60));
        button1.addActionListener(new ButtonExtradition());

        Button button2 = new Button("Книги в библиотеки");
        button2.setPreferredSize(new Dimension(350,60));
        button2.addActionListener(new ButtonAllBooks());

        Button button3 = new Button("Новый читатель");
        button3.setPreferredSize(new Dimension(350,60));
        button3.addActionListener(new ButtonCreateNewReader());


        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        super.setLayout(new GridBagLayout());
        super.getContentPane().add(panel);
        super.getContentPane().setBackground(clr1);
        super.setVisible(true);
    }

    class ButtonAllBooks implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            try {
                AllBookForLibrarianJFrame allBookForLibrarianJFrame = new AllBookForLibrarianJFrame();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class ButtonExtradition implements ActionListener{

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

    class  ButtonCreateNewReader implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            try {
                JFrameCraeteReader jFrameCraeteReader = new JFrameCraeteReader();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameMainWindowReader extends JFrame {
    Color clr1 = new Color(190, 209, 232);
    Owner own;

    public JFrameMainWindowReader(){

        super("Main Window");
        super.setSize(500,350);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.own = own;

        JPanel panel = new JPanel(new GridLayout(3,1,10,10));
        panel.setBackground(clr1);

        Button button1 = new Button("Профиль");
        button1.setPreferredSize(new Dimension(300,60));
        button1.addActionListener(new ButtonProfile());
        Button button2 = new Button("Мои книги");
        button2.setPreferredSize(new Dimension(300,60));
        Button button3 = new Button("Книги в библиотеки");
        button3.setPreferredSize(new Dimension(300,60));

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


}

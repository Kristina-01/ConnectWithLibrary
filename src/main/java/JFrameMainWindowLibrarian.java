import javax.swing.*;
import java.awt.*;

public class JFrameMainWindowLibrarian extends JFrame {
    Color clr1 = new Color(190, 209, 232);

    public JFrameMainWindowLibrarian(){
        super("Main Window");
        super.setSize(500,350);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2,1,10,30));
        panel.setBackground(clr1);

        Button button2 = new Button("Выдача");
        button2.setPreferredSize(new Dimension(300,60));
        Button button3 = new Button("Книги в библиотеки");
        button3.setPreferredSize(new Dimension(300,60));


        panel.add(button2);
        panel.add(button3);

        super.setLayout(new GridBagLayout());
        super.getContentPane().add(panel);
        super.getContentPane().setBackground(clr1);
        super.setVisible(true);
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.io.IOException;

public class frame{
    private JFrame frame;

    public frame(){
        frame = new JFrame("LESCO Billing System");
        frame.setSize(1366,768);

        BufferedImage icon = null;
        try {
            icon = ImageIO.read(new File("Images\\FrameIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (icon != null) {
            frame.setIconImage(icon);
        }

        JPanel contentPane = (JPanel) frame.getContentPane();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        contentPane.setBorder(border);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);
    }

    public void addPanel(JPanel p)
    {
        frame.add(p);
        frame.revalidate();
        frame.repaint();
    }

    public void replacePanel(JPanel old, JPanel latest){
        frame.remove(old);
        frame.add(latest);
        frame.revalidate();
        frame.repaint();
    }

    public void show()
    {
        frame.setVisible(true);
    }

    public void destroy(){
        frame.dispose();
    }
}

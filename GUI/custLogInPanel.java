import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class custLogInPanel extends JPanel
{
    private BufferedImage image;
    private JButton empButton;
    private JButton loginButton;
    private JTextField textField1 = new JTextField("Type your ID");
    private JTextField textField2 = new JTextField("Type your CNIC");

    public custLogInPanel() {
        setLayout(null);
        setImage();
        setFields();
        setButtons();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    private void setImage() {
        try {
            image = ImageIO.read(new File("Images/LogIn Customer.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int imgWidth = getWidth();
            int imgHeight = getHeight();
            g2d.drawImage(image, 0, 0, imgWidth, imgHeight, this);
        }
    }

    private void setFields()
    {
        textField1.setFont(new Font("Inter",Font.BOLD,25));

        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField1.getText().equals("Type your ID")) {
                    textField1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField1.getText().isEmpty()) {
                    textField1.setText("Type your ID");
                }
            }
        });

        textField1.setBorder(BorderFactory.createEmptyBorder());
        textField1.setBounds(661,433,567,40);
        textField1.setForeground(new Color(173,173,173));


        textField2.setFont(new Font("Inter",Font.BOLD,25));

        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField2.getText().equals("Type your CNIC")) {
                    textField2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField2.getText().isEmpty()) {
                    textField2.setText("Type your CNIC");
                }
            }
        });

        textField2.setBorder(BorderFactory.createEmptyBorder());
        textField2.setBounds(661,564,567,40);
        textField2.setForeground(new Color(173,173,173));


        add(textField1);
        add(textField2);
    }

    private void setButtons()
    {
        empButton = new JButton();
        empButton.setBounds((int) 699.71, (int) 218.62, (int) 183.82, (int) 117.86);
        empButton.setBorderPainted(false);
        empButton.setContentAreaFilled(false);
        empButton.setOpaque(false);

        loginButton = new JButton();
        loginButton.setBounds( 570, 644, 676, 58);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(false);

        add(empButton);
        add(loginButton);
    }

    public String getUserID()
    {
        return textField1.getText();
    }
    public String getCNIC()
    {
        return textField2.getText();
    }

    public JButton getLoginButton(){
        return loginButton;
    }

    public JButton getEmpButton()
    {
        return empButton;
    }

    public JPanel getPanel() {
        return this;
    }
}

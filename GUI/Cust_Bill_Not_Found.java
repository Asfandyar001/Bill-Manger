import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cust_Bill_Not_Found extends JPanel{
    private BufferedImage image;
    private JButton searchButton;
    private JButton logoutButton;
    private JButton updateCNICButton;
    private JTextField textField1 = new JTextField("Type your ID");
    private JTextField textField2 = new JTextField("Type your CNIC");
    private JTextField textField3 = new JTextField("Enter Bill Month");
    private JTextField textField4 = new JTextField("Enter Bill Year");
    private JLabel name1 = new JLabel();
    private JLabel name2 = new JLabel();

    public Cust_Bill_Not_Found(String name){
        setLayout(null);
        setNames(name);
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

    private void setNames(String name)
    {
        name1.setText(name);
        name1.setFont(new Font("Inter",Font.BOLD,15));
        name1.setForeground(Color.white);
        name1.setBounds(12,189,250,18);
        name1.setVerticalAlignment(JLabel.CENTER);
        name1.setHorizontalAlignment(JLabel.CENTER);

        name2.setText(name + "!");
        name2.setFont(new Font("Inter",Font.BOLD,25));
        name2.setForeground(Color.BLACK);
        name2.setBounds(380,41,152,38);

        add(name1);
        add(name2);
    }

    private void setImage() {
        try {
            image = ImageIO.read(new File("Images/BillNotFound.png"));
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
        textField1.setFont(new Font("Inter",Font.BOLD,14));
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
        textField1.setBounds(369, 129,107,21);
        textField1.setForeground(new Color(173,173,173));

        textField2.setFont(new Font("Inter",Font.BOLD,14));
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
        textField2.setBounds(613, 129,177,21);
        textField2.setForeground(new Color(173,173,173));


        textField3.setFont(new Font("Inter",Font.BOLD,14));
        textField3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField3.getText().equals("Enter Bill Month")) {
                    textField3.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField3.getText().isEmpty()) {
                    textField3.setText("Enter Bill Month");
                }
            }
        });
        textField3.setBorder(BorderFactory.createEmptyBorder());
        textField3.setBounds(849, 129,118,21);
        textField3.setForeground(new Color(173,173,173));


        textField4.setFont(new Font("Inter",Font.BOLD,14));
        textField4.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField4.getText().equals("Enter Bill Year")) {
                    textField4.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField4.getText().isEmpty()) {
                    textField4.setText("Enter Bill Year");
                }
            }
        });
        textField4.setBorder(BorderFactory.createEmptyBorder());
        textField4.setBounds(1048, 129,118,21);
        textField4.setForeground(new Color(173,173,173));

        add(textField1);
        add(textField2);
        add(textField3);
        add(textField4);
    }

    private void setButtons()
    {
        searchButton = new JButton();
        searchButton.setBounds(1212, 126, 53, 27);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setOpaque(false);

        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

        updateCNICButton = new JButton();
        updateCNICButton.setBounds(85, 326, 97, 17);
        updateCNICButton.setBorderPainted(false);
        updateCNICButton.setContentAreaFilled(false);
        updateCNICButton.setOpaque(false);

        add(updateCNICButton);
        add(searchButton);
        add(logoutButton);
    }

    public String getID(){
        return textField1.getText();
    }
    public String getCNIC(){
        return textField2.getText();
    }
    public String getMonth(){
        return textField3.getText();
    }
    public String getYear(){
        return textField4.getText();
    }
    public JButton getSearchButton()
    {
        return searchButton;
    }
    public JButton getLogoutButton()
    {
        return logoutButton;
    }
    public JButton getUpdateCNICButton()
    {
        return updateCNICButton;
    }
    public JPanel getPanel() {
        return this;
    }
}

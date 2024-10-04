import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Emp_Update_Password extends JPanel{
    private BufferedImage image;
    private JButton updateButton;
    private JButton logoutButton;
    private JTextField textField1 = new JTextField("Type your current Name");
    private JTextField textField2 = new JTextField("Type your current Password");
    private JTextField textField3 = new JTextField("Type your new Password");
    private JLabel name1 = new JLabel();
    private JLabel name2 = new JLabel();

    private JButton customerInfo;
    private JButton billInfo;
    private JButton changeStatus;
    private JButton taxesInfo;
    private JButton viewBill;
    private JButton viewReport;
    private JButton viewExpiringCNIC;

    public Emp_Update_Password(String name)
    {
        setLayout(null);
        setNames(name);
        setImage();
        setFields();
        setButtons();
        setSideMenuButtons();

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
            image = ImageIO.read(new File("Images/UpdatePassword.png"));
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
        textField1.setFont(new Font("Inter",Font.BOLD,20));
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField1.getText().equals("Type your current Name")) {
                    textField1.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField1.getText().isEmpty()) {
                    textField1.setText("Type your current Name");
                }
            }
        });
        textField1.setBorder(BorderFactory.createEmptyBorder());
        textField1.setBounds(391, 316,246,30);
        textField1.setForeground(new Color(173,173,173));

        textField2.setFont(new Font("Inter",Font.BOLD,20));
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField2.getText().equals("Type your current Password")) {
                    textField2.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField2.getText().isEmpty()) {
                    textField2.setText("Type your current Password");
                }
            }
        });
        textField2.setBorder(BorderFactory.createEmptyBorder());
        textField2.setBounds(391, 406,312,30);
        textField2.setForeground(new Color(173,173,173));


        textField3.setFont(new Font("Inter",Font.BOLD,20));
        textField3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField3.getText().equals("Type your new Password")) {
                    textField3.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField3.getText().isEmpty()) {
                    textField3.setText("Type your new Password");
                }
            }
        });
        textField3.setBorder(BorderFactory.createEmptyBorder());
        textField3.setBounds(391, 496,312,30);
        textField3.setForeground(new Color(173,173,173));

        add(textField1);
        add(textField2);
        add(textField3);
    }

    private void setButtons()
    {
        updateButton = new JButton();
        updateButton.setBounds(555, 194, 110, 30);
        updateButton.setBorderPainted(false);
        updateButton.setContentAreaFilled(false);
        updateButton.setOpaque(false);

        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

        add(updateButton);
        add(logoutButton);
    }

    private void setSideMenuButtons(){
        customerInfo = new JButton();
        customerInfo.setBounds(79, 325, 101, 20);
        customerInfo.setBorderPainted(false);
        customerInfo.setContentAreaFilled(false);
        customerInfo.setOpaque(false);

        billInfo = new JButton();
        billInfo.setBounds(79, 374, 70, (int) 17.81);
        billInfo.setBorderPainted(false);
        billInfo.setContentAreaFilled(false);
        billInfo.setOpaque(false);

        changeStatus = new JButton();
        changeStatus.setBounds(79, 420, 122, 18);
        changeStatus.setBorderPainted(false);
        changeStatus.setContentAreaFilled(false);
        changeStatus.setOpaque(false);

        taxesInfo = new JButton();
        taxesInfo.setBounds(79, 467, 83, 17);
        taxesInfo.setBorderPainted(false);
        taxesInfo.setContentAreaFilled(false);
        taxesInfo.setOpaque(false);

        viewBill = new JButton();
        viewBill.setBounds(79, 516, 75, 14);
        viewBill.setBorderPainted(false);
        viewBill.setContentAreaFilled(false);
        viewBill.setOpaque(false);

        viewReport = new JButton();
        viewReport.setBounds(82, 562, 91, 15);
        viewReport.setBorderPainted(false);
        viewReport.setContentAreaFilled(false);
        viewReport.setOpaque(false);

        viewExpiringCNIC = new JButton();
        viewExpiringCNIC.setBounds(82, 610, 131, 15);
        viewExpiringCNIC.setBorderPainted(false);
        viewExpiringCNIC.setContentAreaFilled(false);
        viewExpiringCNIC.setOpaque(false);


        add(customerInfo);
        add(billInfo);
        add(changeStatus);
        add(taxesInfo);
        add(viewBill);
        add(viewReport);
        add(viewExpiringCNIC);
    }

    public String getUserName(){
        return textField1.getText();
    }
    public String getOld(){
        return textField2.getText();
    }
    public String getNewPass(){
        return textField3.getText();
    }
    public JButton getUpdateButton()
    {
        return updateButton;
    }
    public JButton getLogoutButton()
    {
        return logoutButton;
    }
    public JButton getCustomerInfoButton(){return customerInfo;}
    public JButton getBillInfoButton(){return billInfo;}
    public JButton gettaxesButton(){return taxesInfo;}
    public JButton getChangeStatusButton(){return changeStatus;}
    public JButton getViewBillButton(){return viewBill;}
    public JButton getViewReportButton(){return viewReport;}
    public JButton getViewExpireButton(){return viewExpiringCNIC;}
    public JPanel getPanel() {
        return this;
    }
}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Emp_View_Report extends JPanel{
    private BufferedImage image;
    private JButton logoutButton;
    private JLabel name1 = new JLabel();
    private JLabel name2 = new JLabel();
    private JLabel output1 = new JLabel();
    private JLabel output2 = new JLabel();

    private JButton updatePassword;
    private JButton customerInfo;
    private JButton billInfo;
    private JButton changeStatus;
    private JButton taxesInfo;
    private JButton viewBill;
    private JButton viewExpiringCNIC;

    public Emp_View_Report(String name)
    {
        setLayout(null);
        setNames(name);
        setImage();
        setButtons();
        setSideMenuButtons();
        setValues("0","0");

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
            image = ImageIO.read(new File("Images/Report.png"));
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

    private void setButtons()
    {
        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

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

        updatePassword = new JButton();
        updatePassword.setBounds(79, 278, 117, 19);
        updatePassword.setBorderPainted(false);
        updatePassword.setContentAreaFilled(false);
        updatePassword.setOpaque(false);

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

        changeStatus = new JButton();
        changeStatus.setBounds(79, 420, 122, 18);
        changeStatus.setBorderPainted(false);
        changeStatus.setContentAreaFilled(false);
        changeStatus.setOpaque(false);

        viewExpiringCNIC = new JButton();
        viewExpiringCNIC.setBounds(82, 610, 131, 15);
        viewExpiringCNIC.setBorderPainted(false);
        viewExpiringCNIC.setContentAreaFilled(false);
        viewExpiringCNIC.setOpaque(false);


        add(customerInfo);
        add(billInfo);
        add(updatePassword);
        add(taxesInfo);
        add(viewBill);
        add(changeStatus);
        add(viewExpiringCNIC);
    }

    public void setValues(String value1, String value2){
        output1.setText(value1);
        output1.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,20));
        output1.setForeground(new Color(93,93,93));
        output1.setBounds(910,303,356,22);
        output1.setVerticalAlignment(JLabel.CENTER);
        output1.setHorizontalAlignment(JLabel.CENTER);

        output2.setText(value2);
        output2.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,20));
        output2.setForeground(new Color(93,93,93));
        output2.setBounds(910,347,356,22);
        output2.setVerticalAlignment(JLabel.CENTER);
        output2.setHorizontalAlignment(JLabel.CENTER);

        add(output1);
        add(output2);
    }

    public JButton getLogoutButton()
    {
        return logoutButton;
    }
    public JButton getCustomerInfoButton(){return customerInfo;}
    public JButton getBillInfoButton(){return billInfo;}
    public JButton gettaxesButton(){return taxesInfo;}
    public JButton getUpdatePasswordButton(){return updatePassword;}
    public JButton getViewBillButton(){return viewBill;}
    public JButton getChangeStatusButton(){return changeStatus;}
    public JButton getViewExpireButton(){return viewExpiringCNIC;}
    public JPanel getPanel() {
        return this;
    }
}

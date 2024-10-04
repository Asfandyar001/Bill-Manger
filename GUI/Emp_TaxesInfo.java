import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Emp_TaxesInfo extends JPanel{
    private BufferedImage image;
    private JButton logoutButton;
    private JLabel name1 = new JLabel();
    private JLabel name2 = new JLabel();

    private JTextField r1 = new JTextField();
    private JTextField r2 = new JTextField();
    private JTextField r3 = new JTextField();
    private JTextField r4 = new JTextField();

    private JTextField p3 = new JTextField();
    private JTextField p4 = new JTextField();

    private JTextField t1 = new JTextField();
    private JTextField t2 = new JTextField();
    private JTextField t3 = new JTextField();
    private JTextField t4 = new JTextField();

    private JTextField fc1 = new JTextField();
    private JTextField fc2 = new JTextField();
    private JTextField fc3 = new JTextField();
    private JTextField fc4 = new JTextField();

    private JLabel edit1 = new JLabel();
    private JLabel edit2 = new JLabel();
    private JLabel edit3 = new JLabel();
    private JLabel edit4 = new JLabel();

    private JButton updatePassword;
    private JButton customerInfo;
    private JButton billInfo;
    private JButton changeStatus;
    private JButton viewReport;
    private JButton viewBill;
    private JButton viewExpiringCNIC;

    public Emp_TaxesInfo(String name)
    {
        setLayout(null);
        setNames(name);
        setImage();
        setButtons();
        setSideMenuButtons();
        setEditButtons();

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
            image = ImageIO.read(new File("Images/TaxesInfo.png"));
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

        viewReport = new JButton();
        viewReport.setBounds(82, 562, 91, 15);
        viewReport.setBorderPainted(false);
        viewReport.setContentAreaFilled(false);
        viewReport.setOpaque(false);

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
        add(viewReport);
        add(viewBill);
        add(changeStatus);
        add(viewExpiringCNIC);
    }

    public void removeVaalues(){
        r1.setText("");
        t1.setText("");
        fc1.setText("");

        r2.setText("");
        t2.setText("");
        fc2.setText("");

        r3.setText("");
        p3.setText("");
        t3.setText("");
        fc3.setText("");

        r4.setText("");
        p4.setText("");
        t4.setText("");
        fc4.setText("");
    }

    public void setValues(ArrayList<String> list)
    {
        String[] data1 = list.get(0).split(",");

        r1.setText(data1[1]);
        r1.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        r1.setForeground(new Color(93,93,93));
        r1.setBounds(531,302,144,22);
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBorder(BorderFactory.createEmptyBorder());
        r1.setEditable(false);
        r1.setBackground(new Color(255,255,255));
        r1.setFocusable(false);

        t1.setText(data1[3]);
        t1.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        t1.setForeground(new Color(93,93,93));
        t1.setBounds(965,302,53,22);
        t1.setHorizontalAlignment(JLabel.CENTER);
        t1.setBorder(BorderFactory.createEmptyBorder());
        t1.setEditable(false);
        t1.setBackground(new Color(255,255,255));
        t1.setFocusable(false);

        fc1.setText(data1[4]);
        fc1.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        fc1.setForeground(new Color(93,93,93));
        fc1.setBounds(1071,302,125,22);
        fc1.setHorizontalAlignment(JLabel.CENTER);
        fc1.setBorder(BorderFactory.createEmptyBorder());
        fc1.setEditable(false);
        fc1.setBackground(new Color(255,255,255));
        fc1.setFocusable(false);

        String[] data2 = list.get(1).split(",");

        r2.setText(data2[1]);
        r2.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        r2.setForeground(new Color(93,93,93));
        r2.setBounds(531,347,144,22);
        r2.setHorizontalAlignment(JLabel.CENTER);
        r2.setBorder(BorderFactory.createEmptyBorder());
        r2.setEditable(false);
        r2.setBackground(new Color(217,217,217));
        r2.setFocusable(false);

        t2.setText(data2[3]);
        t2.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        t2.setForeground(new Color(93,93,93));
        t2.setBounds(965,347,53,22);
        t2.setHorizontalAlignment(JLabel.CENTER);
        t2.setBorder(BorderFactory.createEmptyBorder());
        t2.setEditable(false);
        t2.setBackground(new Color(217,217,217));
        t2.setFocusable(false);

        fc2.setText(data2[4]);
        fc2.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        fc2.setForeground(new Color(93,93,93));
        fc2.setBounds(1071,347,125,22);
        fc2.setHorizontalAlignment(JLabel.CENTER);
        fc2.setBorder(BorderFactory.createEmptyBorder());
        fc2.setEditable(false);
        fc2.setBackground(new Color(217,217,217));
        fc2.setFocusable(false);

        String[] data3 = list.get(2).split(",");

        r3.setText(data3[1]);
        r3.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        r3.setForeground(new Color(93,93,93));
        r3.setBounds(531,392,144,22);
        r3.setHorizontalAlignment(JLabel.CENTER);
        r3.setBorder(BorderFactory.createEmptyBorder());
        r3.setEditable(false);
        r3.setBackground(new Color(255,255,255));
        r3.setFocusable(false);

        p3.setText(data3[2]);
        p3.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        p3.setForeground(new Color(93,93,93));
        p3.setBounds(738,392,144,22);
        p3.setHorizontalAlignment(JLabel.CENTER);
        p3.setBorder(BorderFactory.createEmptyBorder());
        p3.setEditable(false);
        p3.setBackground(new Color(255,255,255));
        p3.setFocusable(false);

        t3.setText(data3[3]);
        t3.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        t3.setForeground(new Color(93,93,93));
        t3.setBounds(965,392,53,22);
        t3.setHorizontalAlignment(JLabel.CENTER);
        t3.setBorder(BorderFactory.createEmptyBorder());
        t3.setEditable(false);
        t3.setBackground(new Color(255,255,255));
        t3.setFocusable(false);

        fc3.setText(data3[4]);
        fc3.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        fc3.setForeground(new Color(93,93,93));
        fc3.setBounds(1071,392,125,22);
        fc3.setHorizontalAlignment(JLabel.CENTER);
        fc3.setBorder(BorderFactory.createEmptyBorder());
        fc3.setEditable(false);
        fc3.setBackground(new Color(255,255,255));
        fc3.setFocusable(false);

        String[] data4 = list.get(3).split(",");

        r4.setText(data4[1]);
        r4.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        r4.setForeground(new Color(93,93,93));
        r4.setBounds(531,437,144,22);
        r4.setHorizontalAlignment(JLabel.CENTER);
        r4.setBorder(BorderFactory.createEmptyBorder());
        r4.setEditable(false);
        r4.setBackground(new Color(217,217,217));
        r4.setFocusable(false);

        p4.setText(data4[2]);
        p4.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        p4.setForeground(new Color(93,93,93));
        p4.setBounds(738,437,144,22);
        p4.setHorizontalAlignment(JLabel.CENTER);
        p4.setBorder(BorderFactory.createEmptyBorder());
        p4.setEditable(false);
        p4.setBackground(new Color(217,217,217));
        p4.setFocusable(false);

        t4.setText(data4[3]);
        t4.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        t4.setForeground(new Color(93,93,93));
        t4.setBounds(965,437,53,22);
        t4.setHorizontalAlignment(JLabel.CENTER);
        t4.setBorder(BorderFactory.createEmptyBorder());
        t4.setEditable(false);
        t4.setBackground(new Color(217,217,217));
        t4.setFocusable(false);

        fc4.setText(data4[4]);
        fc4.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        fc4.setForeground(new Color(93,93,93));
        fc4.setBounds(1071,437,125,22);
        fc4.setHorizontalAlignment(JLabel.CENTER);
        fc4.setBorder(BorderFactory.createEmptyBorder());
        fc4.setEditable(false);
        fc4.setBackground(new Color(217,217,217));
        fc4.setFocusable(false);

        add(r1);
        add(t1);
        add(fc1);
        add(r2);
        add(t2);
        add(fc2);
        add(r3);
        add(p3);
        add(t3);
        add(fc3);
        add(r4);
        add(p4);
        add(t4);
        add(fc4);
    }

    private void setEditButtons(){
        edit1.setText("<html><u>Edit</u></html>");
        edit1.setFont(new Font("Inter",Font.BOLD,13));
        edit1.setForeground(new Color(3,149,255));
        edit1.setBounds(1215,303,47,18);
        edit1.setVerticalAlignment(JLabel.CENTER);
        edit1.setHorizontalAlignment(JLabel.CENTER);

        edit2.setText("<html><u>Edit</u></html>");
        edit2.setFont(new Font("Inter",Font.BOLD,13));
        edit2.setForeground(new Color(3,149,255));
        edit2.setBounds(1215,348,47,18);
        edit2.setVerticalAlignment(JLabel.CENTER);
        edit2.setHorizontalAlignment(JLabel.CENTER);

        edit3.setText("<html><u>Edit</u></html>");
        edit3.setFont(new Font("Inter",Font.BOLD,13));
        edit3.setForeground(new Color(3,149,255));
        edit3.setBounds(1215,393,47,18);
        edit3.setVerticalAlignment(JLabel.CENTER);
        edit3.setHorizontalAlignment(JLabel.CENTER);

        edit4.setText("<html><u>Edit</u></html>");
        edit4.setFont(new Font("Inter",Font.BOLD,13));
        edit4.setForeground(new Color(3,149,255));
        edit4.setBounds(1215,438,47,18);
        edit4.setVerticalAlignment(JLabel.CENTER);
        edit4.setHorizontalAlignment(JLabel.CENTER);

        add(edit1);
        add(edit2);
        add(edit3);
        add(edit4);
    }

    public JButton getLogoutButton()
    {
        return logoutButton;
    }
    public JButton getCustomerInfoButton(){return customerInfo;}
    public JButton getBillInfoButton(){return billInfo;}
    public JButton getViewReportButton(){return viewReport;}
    public JButton getUpdatePasswordButton(){return updatePassword;}
    public JButton getViewBillButton(){return viewBill;}
    public JButton getChangeStatusButton(){return changeStatus;}
    public JButton getViewExpireButton(){return viewExpiringCNIC;}
    public JTextField getR1(){return r1;};
    public JTextField getR2(){return r2;};
    public JTextField getR3(){return r3;};
    public JTextField getR4(){return r4;};
    public JTextField getP3(){return p3;};
    public JTextField getP4(){return p4;};
    public JTextField getT1(){return t1;};
    public JTextField getT2(){return t2;};
    public JTextField getT3(){return t3;};
    public JTextField getT4(){return t4;};
    public JTextField getFC1(){return fc1;};
    public JTextField getFC2(){return fc2;};
    public JTextField getFC3(){return fc3;};
    public JTextField getFC4(){return fc4;};
    public JLabel getEdit1(){return edit1;};
    public JLabel getEdit2(){return edit2;};
    public JLabel getEdit3(){return edit3;};
    public JLabel getEdit4(){return edit4;};
    public JPanel getPanel() {
        return this;
    }
}

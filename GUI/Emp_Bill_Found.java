import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Emp_Bill_Found extends JPanel{
    private BufferedImage image;
    private JButton gobackButton;
    private JButton logoutButton;
    private JLabel name1;
    private JLabel name2;


    JLabel id = new JLabel();
    JLabel costOfElectrcity = new JLabel();
    JLabel salesTaxAmount = new JLabel();
    JLabel fixedCharges = new JLabel();
    JLabel billMonth = new JLabel();
    JLabel cmrr = new JLabel();
    JLabel cmrp = new JLabel();
    JLabel totalDue = new JLabel();
    JLabel dueDate = new JLabel();
    JLabel PayStatus = new JLabel();
    JLabel PayDate = new JLabel();

    private JButton updatePassword;
    private JButton customerInfo;
    private JButton billInfo;
    private JButton changeStatus;
    private JButton taxesInfo;
    private JButton viewReport;
    private JButton viewExpiringCNIC;

    public Emp_Bill_Found(String name){
        name1 = new JLabel();
        name2 = new JLabel();
        setLayout(null);
        setNames(name);
        setButtons();
        setSideMenuButtons();
    }

    public void setImg(String name,String status){
        setNames(name);
        setImage(status);
    }

    public void setData(String[] list){
        id.setText(list[0]);
        costOfElectrcity.setText(list[5]);
        salesTaxAmount.setText(list[6]);
        fixedCharges.setText(list[7]);
        billMonth.setText(list[1]);
        cmrr.setText(list[2] + " units");
        cmrp.setText(list[3] + " units");
        totalDue.setText(list[8]);
        dueDate.setText(list[9]);
        PayStatus.setText(list[10]);
        PayDate.setText(list[11]);

        id.setFont(new Font("Inter",Font.BOLD,15));
        costOfElectrcity.setFont(new Font("Inter",Font.BOLD,15));
        salesTaxAmount.setFont(new Font("Inter",Font.BOLD,15));
        fixedCharges.setFont(new Font("Inter",Font.BOLD,15));
        billMonth.setFont(new Font("Inter",Font.BOLD,15));
        cmrr.setFont(new Font("Inter",Font.BOLD,15));
        cmrp.setFont(new Font("Inter",Font.BOLD,15));
        totalDue.setFont(new Font("Inter",Font.BOLD,15));
        dueDate.setFont(new Font("Inter",Font.BOLD,15));
        PayStatus.setFont(new Font("Inter",Font.BOLD,15));
        PayDate.setFont(new Font("Inter",Font.BOLD,15));

        id.setBounds(668,269,129,14);
        costOfElectrcity.setBounds(691,434,91,14);
        salesTaxAmount.setBounds(691,459,91,14);
        fixedCharges.setBounds(691,484,91,14);
        billMonth.setBounds(764,326,146,20);
        cmrr.setBounds(764,354,146,14);
        cmrp.setBounds(764,379,146,14);
        totalDue.setBounds(1029,434,96,14);
        dueDate.setBounds(1003,454,122,16);
        PayStatus.setBounds(1003,474,126,14);
        PayDate.setBounds(1003,494,122,16);

        if(list[10].equals("Paid")){
            id.setForeground(new Color(2,120,40));
            costOfElectrcity.setForeground(new Color(2,120,40));
            salesTaxAmount.setForeground(new Color(2,120,40));
            fixedCharges.setForeground(new Color(2,120,40));
            billMonth.setForeground(new Color(2,120,40));
            cmrr.setForeground(new Color(2,120,40));
            cmrp.setForeground(new Color(2,120,40));
            totalDue.setForeground(new Color(2,120,40));
            dueDate.setForeground(new Color(2,120,40));
            PayStatus.setForeground(new Color(2,120,40));
            PayDate.setForeground(new Color(2,120,40));
        }
        else{
            id.setForeground(new Color(120,2,2));
            costOfElectrcity.setForeground(new Color(120,2,2));
            salesTaxAmount.setForeground(new Color(120,2,2));
            fixedCharges.setForeground(new Color(120,2,2));
            billMonth.setForeground(new Color(120,2,2));
            cmrr.setForeground(new Color(120,2,2));
            cmrp.setForeground(new Color(120,2,2));
            totalDue.setForeground(new Color(120,2,2));
            dueDate.setForeground(new Color(120,2,2));
            PayStatus.setForeground(new Color(120,2,2));
            PayDate.setForeground(new Color(120,2,2));
        }

        add(id);
        add(costOfElectrcity);
        add(salesTaxAmount);
        add(fixedCharges);
        add(billMonth);
        add(cmrr);
        add(cmrp);
        add(totalDue);
        add(dueDate);
        add(PayStatus);
        add(PayDate);
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

    private void setImage(String status) {
        try {
            if(status.equals("Paid")){
                image = ImageIO.read(new File("Images/View Bill Paid.png"));
            }
            else{
                image = ImageIO.read(new File("Images/View Bill UnPaid.png"));
            }
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
        gobackButton = new JButton();
        gobackButton.setBounds(1181, 126, 84, 27);
        gobackButton.setBorderPainted(false);
        gobackButton.setContentAreaFilled(false);
        gobackButton.setOpaque(false);

        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

        add(gobackButton);
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

        viewReport = new JButton();
        viewReport.setBounds(82, 562, 91, 15);
        viewReport.setBorderPainted(false);
        viewReport.setContentAreaFilled(false);
        viewReport.setOpaque(false);

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
        add(viewReport);
        add(changeStatus);
        add(viewExpiringCNIC);
    }

    public JButton getGobackButton()
    {
        return gobackButton;
    }
    public JButton getLogoutButton()
    {
        return logoutButton;
    }
    public JButton getCustomerInfoButton(){return customerInfo;}
    public JButton getBillInfoButton(){return billInfo;}
    public JButton gettaxesButton(){return taxesInfo;}
    public JButton getUpdatePasswordButton(){return updatePassword;}
    public JButton getViewReportButton(){return viewReport;}
    public JButton getChangeStatusButton(){return changeStatus;}
    public JButton getViewExpireButton(){return viewExpiringCNIC;}
    public JPanel getPanel() {
        return this;
    }

    public void clearData() {
        id.setText("");
        costOfElectrcity.setText("");
        salesTaxAmount.setText("");
        fixedCharges.setText("");
        billMonth.setText("");
        cmrr.setText("");
        cmrp.setText("");
        totalDue.setText("");
        dueDate.setText("");
        PayStatus.setText("");
        PayDate.setText("");

        name1.setText("");
        name2.setText("");
    }
}

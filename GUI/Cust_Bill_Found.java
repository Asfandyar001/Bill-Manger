import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Cust_Bill_Found extends JPanel{
    private BufferedImage image;
    private JButton gobackButton;
    private JButton logoutButton;
    private JButton updateCNICButton;
    private JLabel name1;
    private JLabel name2;


    JLabel id = new JLabel();
    JLabel name = new JLabel();
    JLabel cnic = new JLabel();
    JLabel address = new JLabel();
    JLabel pNum = new JLabel();
    JLabel cType = new JLabel();
    JLabel mType = new JLabel();
    JLabel costOfElectrcity = new JLabel();
    JLabel salesTaxAmount = new JLabel();
    JLabel fixedCharges = new JLabel();
    JLabel billMonth = new JLabel();
    JLabel cmrr = new JLabel();
    JLabel cmrp = new JLabel();
    JLabel regularPrice = new JLabel();
    JLabel peakPrice = new JLabel();
    JLabel precTax = new JLabel();
    JLabel totalDue = new JLabel();
    JLabel dueDate = new JLabel();
    JLabel PayStatus = new JLabel();

    public Cust_Bill_Found(String name, String status){
        name1 = new JLabel();
        name2 = new JLabel();
        setLayout(null);
        setNames(name);
        setImage(status);
        setButtons();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    public void setNameStatus(String name, String status){
        name1 = new JLabel();
        name2 = new JLabel();
        setLayout(null);
        setNames(name);
        setImage(status);
        setButtons();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    public void setData(ArrayList<String> list){
        id.setText(list.get(0));
        name.setText(list.get(1));
        cnic.setText(list.get(2));
        address.setText(list.get(3));
        pNum.setText(list.get(4));
        cType.setText(list.get(5));
        mType.setText(list.get(6));
        costOfElectrcity.setText(list.get(7));
        salesTaxAmount.setText(list.get(8));
        fixedCharges.setText(list.get(9));
        billMonth.setText(list.get(10));
        cmrr.setText(list.get(11));
        cmrp.setText(list.get(12));
        regularPrice.setText(list.get(13));
        peakPrice.setText(list.get(14));
        precTax.setText(list.get(15));
        totalDue.setText(list.get(16));
        dueDate.setText(list.get(17));
        PayStatus.setText(list.get(18));

        id.setFont(new Font("Inter",Font.BOLD,15));
        name.setFont(new Font("Inter",Font.BOLD,15));
        cnic.setFont(new Font("Inter",Font.BOLD,15));
        address.setFont(new Font("Inter",Font.BOLD,15));
        pNum.setFont(new Font("Inter",Font.BOLD,15));
        cType.setFont(new Font("Inter",Font.BOLD,15));
        mType.setFont(new Font("Inter",Font.BOLD,15));
        costOfElectrcity.setFont(new Font("Inter",Font.BOLD,15));
        salesTaxAmount.setFont(new Font("Inter",Font.BOLD,15));
        fixedCharges.setFont(new Font("Inter",Font.BOLD,15));
        billMonth.setFont(new Font("Inter",Font.BOLD,15));
        cmrr.setFont(new Font("Inter",Font.BOLD,15));
        cmrp.setFont(new Font("Inter",Font.BOLD,15));
        regularPrice.setFont(new Font("Inter",Font.BOLD,15));
        peakPrice.setFont(new Font("Inter",Font.BOLD,15));
        precTax.setFont(new Font("Inter",Font.BOLD,15));
        totalDue.setFont(new Font("Inter",Font.BOLD,15));
        dueDate.setFont(new Font("Inter",Font.BOLD,15));
        PayStatus.setFont(new Font("Inter",Font.BOLD,15));

        id.setBounds(586,265-1,129,20);
        name.setBounds(586,292-1,129,20);
        cnic.setBounds(586,319-1,129,20);
        address.setBounds(586,344,129,20);
        pNum.setBounds(586,373-1,129,20);
        cType.setBounds(586,400-1,129,20);
        mType.setBounds(586,427-1,129,20);
        costOfElectrcity.setBounds(615,499-1,91,20);
        salesTaxAmount.setBounds(615,524-1,91,20);
        fixedCharges.setBounds(615,549-1,91,20);
        billMonth.setBounds(1046,269-1,146,20);
        cmrr.setBounds(1046,294-1,146,20);
        cmrp.setBounds(1046,319-1,146,20);
        regularPrice.setBounds(1067,367-1,125,20);
        peakPrice.setBounds(1067,392-1,125,20);
        precTax.setBounds(1070, 416,131,20);
        totalDue.setBounds(925,499-1,177,20);
        dueDate.setBounds(899,524-1,177,20);
        PayStatus.setBounds(899,549-1,153,20);

        if(list.get(18).equals("Paid")){
            id.setForeground(new Color(2,120,40));
            name.setForeground(new Color(2,120,40));
            cnic.setForeground(new Color(2,120,40));
            address.setForeground(new Color(2,120,40));
            pNum.setForeground(new Color(2,120,40));
            cType.setForeground(new Color(2,120,40));
            mType.setForeground(new Color(2,120,40));
            costOfElectrcity.setForeground(new Color(2,120,40));
            salesTaxAmount.setForeground(new Color(2,120,40));
            fixedCharges.setForeground(new Color(2,120,40));
            billMonth.setForeground(new Color(2,120,40));
            cmrr.setForeground(new Color(2,120,40));
            cmrp.setForeground(new Color(2,120,40));
            regularPrice.setForeground(new Color(2,120,40));
            peakPrice.setForeground(new Color(2,120,40));
            precTax.setForeground(new Color(2,120,40));
            totalDue.setForeground(new Color(2,120,40));
            dueDate.setForeground(new Color(2,120,40));
            PayStatus.setForeground(new Color(2,120,40));
        }
        else{
            id.setForeground(new Color(120,2,2));
            name.setForeground(new Color(120,2,2));
            cnic.setForeground(new Color(120,2,2));
            address.setForeground(new Color(120,2,2));
            pNum.setForeground(new Color(120,2,2));
            cType.setForeground(new Color(120,2,2));
            mType.setForeground(new Color(120,2,2));
            costOfElectrcity.setForeground(new Color(120,2,2));
            salesTaxAmount.setForeground(new Color(120,2,2));
            fixedCharges.setForeground(new Color(120,2,2));
            billMonth.setForeground(new Color(120,2,2));
            cmrr.setForeground(new Color(120,2,2));
            cmrp.setForeground(new Color(120,2,2));
            regularPrice.setForeground(new Color(120,2,2));
            peakPrice.setForeground(new Color(120,2,2));
            precTax.setForeground(new Color(120,2,2));
            totalDue.setForeground(new Color(120,2,2));
            dueDate.setForeground(new Color(120,2,2));
            PayStatus.setForeground(new Color(120,2,2));
        }

        add(id);
        add(name);
        add(cnic);
        add(address);
        add(pNum);
        add(cType);
        add(mType);
        add(costOfElectrcity);
        add(salesTaxAmount);
        add(fixedCharges);
        add(billMonth);
        add(cmrr);
        add(cmrp);
        add(regularPrice);
        add(peakPrice);
        add(precTax);
        add(totalDue);
        add(dueDate);
        add(PayStatus);
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
                image = ImageIO.read(new File("Images/Paid.png"));
            }
            else{
                image = ImageIO.read(new File("Images/UnPaid.png"));
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
        gobackButton.setBounds(1206, 126, 59, 27);
        gobackButton.setBorderPainted(false);
        gobackButton.setContentAreaFilled(false);
        gobackButton.setOpaque(false);

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
        add(gobackButton);
        add(logoutButton);
    }

    public JButton getGobackButton()
    {
        return gobackButton;
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

    public void clearData() {
        id.setText("");
        name.setText("");
        cnic.setText("");
        address.setText("");
        pNum.setText("");
        cType.setText("");
        mType.setText("");
        costOfElectrcity.setText("");
        salesTaxAmount.setText("");
        fixedCharges.setText("");
        billMonth.setText("");
        cmrr.setText("");
        cmrp.setText("");
        regularPrice.setText("");
        peakPrice.setText("");
        precTax.setText("");
        totalDue.setText("");
        dueDate.setText("");
        PayStatus.setText("");

        name1.setText("");
        name2.setText("");
    }
}

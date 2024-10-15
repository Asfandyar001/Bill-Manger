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
import java.util.ArrayList;

public class Emp_ViewNadraData extends JPanel{
    private BufferedImage image;
    private JButton logoutButton;
    private JButton backButton;
    private JButton searchButton;
    private JLabel name1 = new JLabel();
    private JLabel name2 = new JLabel();

    private JTextField textField1 = new JTextField("Search");

    private JButton updatePassword;
    private JButton customerInfo;
    private JButton billInfo;
    private JButton changeStatus;
    private JButton viewReport;
    private JButton viewBill;
    private JButton taxesInfo;

    private JScrollPane scroll;

    public Emp_ViewNadraData(String name)
    {
        setLayout(null);
        setNames(name);
        setImage();
        setButtons();
        setFields();
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
            image = ImageIO.read(new File("Images/ViewNadra.png"));
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
        textField1.setFont(new Font("Inter",Font.BOLD,15));
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField1.getText().equals("Search")) {
                    textField1.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField1.getText().isEmpty()) {
                    textField1.setText("Search");
                }
            }
        });
        textField1.setBorder(BorderFactory.createEmptyBorder());
        textField1.setBounds(623, 198,328,18);
        textField1.setForeground(new Color(173,173,173));

        add(textField1);
    }

    private void setButtons()
    {
        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

        backButton = new JButton();
        backButton.setBounds(1172, 191, 90, 27);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(false);

        searchButton = new JButton();
        searchButton.setBounds(953, 200, 15, 15);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setOpaque(false);

        add(logoutButton);
        add(backButton);
        add(searchButton);
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

        taxesInfo = new JButton();
        taxesInfo.setBounds(79, 467, 83, 17);
        taxesInfo.setBorderPainted(false);
        taxesInfo.setContentAreaFilled(false);
        taxesInfo.setOpaque(false);


        add(customerInfo);
        add(billInfo);
        add(updatePassword);
        add(viewReport);
        add(viewBill);
        add(changeStatus);
        add(taxesInfo);
    }

    public void setValues(ArrayList<String> list)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.white);
        panel.setBorder(null);

        int value = 0;

        for(int i=0; i< list.size(); i++){
            String[] data = list.get(i).split(",");

            JLabel label = new JLabel();
            label.setLayout(null);
            label.setOpaque(true);
            label.setPreferredSize(new Dimension(920,40));
            label.setMaximumSize(new Dimension(920, 40));
            label.setBorder(null);

            if(value==0){
                label.setBackground(new Color(255,255,255));
                value=1;
            }
            else {
                value=0;
                label.setBackground(new Color(217,217,217));
            }

            JLabel cnic = new JLabel(data[0]);
            cnic.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
            cnic.setForeground(new Color(93,93,93));
            cnic.setBounds(0, 8, 265, 22);
            cnic.setVerticalAlignment(JLabel.CENTER);
            cnic.setHorizontalAlignment(JLabel.CENTER);
            cnic.setBorder(null);
            label.add(cnic);

            JLabel issueDate = new JLabel(data[1]);
            issueDate.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
            issueDate.setForeground(new Color(93,93,93));
            issueDate.setBounds(265, 8, 327, 22);
            issueDate.setVerticalAlignment(JLabel.CENTER);
            issueDate.setHorizontalAlignment(JLabel.CENTER);
            issueDate.setBorder(null);
            label.add(issueDate);

            JLabel expireDate = new JLabel(data[2]);
            expireDate.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
            expireDate.setForeground(new Color(93,93,93));
            expireDate.setBounds(592, 8, 332, 22);
            expireDate.setVerticalAlignment(JLabel.CENTER);
            expireDate.setHorizontalAlignment(JLabel.CENTER);
            expireDate.setBorder(null);
            label.add(expireDate);

            panel.add(label);
        }

        scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(924, 431));
        scroll.setBounds(343, 299, 924, 431);
        scroll.setBorder(null);

        add(scroll);
    }

    public void refreshPanel(ArrayList<String> newList) {
        if (scroll != null) {
            remove(scroll);
        }
        setValues(newList);
        revalidate();
        repaint();
    }


    public String getSearched(){
        return textField1.getText();
    }
    public JButton getBackButton(){
        return backButton;
    }
    public JButton getSearchButton(){
        return searchButton;
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
    public JButton gettaxesButton(){return taxesInfo;}
    public JPanel getPanel() {
        return this;
    }
}

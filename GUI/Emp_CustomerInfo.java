import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Emp_CustomerInfo extends JPanel{
    private BufferedImage image;
    private JButton logoutButton;
    private JButton searchButton;
    private JButton addButton;
    private JLabel name1 = new JLabel();
    private JLabel name2 = new JLabel();

    private JTextField textField1 = new JTextField("Search");
    private JTextField textField2 = new JTextField("Type CNIC");
    private JTextField textField3 = new JTextField("Type Name");
    private JTextField textField4 = new JTextField("Type Address");
    private JTextField textField5 = new JTextField("Type Phone No.");

    private JComboBox box1;
    private JComboBox box2;

    private JButton updatePassword;
    private JButton viewExpiringCNIC;
    private JButton billInfo;
    private JButton changeStatus;
    private JButton viewReport;
    private JButton viewBill;
    private JButton taxesInfo;

    private JScrollPane scroll;

    public Emp_CustomerInfo(String name)
    {
        setLayout(null);
        setNames(name);
        setImage();
        setButtons();
        setFields();
        setSideMenuButtons();
        setComboBox();

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
            image = ImageIO.read(new File("Images/CustomerInfo.png"));
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
        textField1.setBounds(623, 197,328,22);
        textField1.setForeground(new Color(173,173,173));

        textField2.setFont(new Font("Inter",Font.BOLD,15));
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField2.getText().equals("Type CNIC")) {
                    textField2.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField2.getText().isEmpty()) {
                    textField2.setText("Type CNIC");
                }
            }
        });
        textField2.setBorder(BorderFactory.createEmptyBorder());
        textField2.setBounds(337, 133,138,22);
        textField2.setForeground(new Color(173,173,173));

        textField3.setFont(new Font("Inter",Font.BOLD,15));
        textField3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField3.getText().equals("Type Name")) {
                    textField3.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField3.getText().isEmpty()) {
                    textField3.setText("Type Name");
                }
            }
        });
        textField3.setBorder(BorderFactory.createEmptyBorder());
        textField3.setBounds(522, 133,138,22);
        textField3.setForeground(new Color(173,173,173));

        textField4.setFont(new Font("Inter",Font.BOLD,15));
        textField4.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField4.getText().equals("Type Address")) {
                    textField4.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField4.getText().isEmpty()) {
                    textField4.setText("Type Address");
                }
            }
        });
        textField4.setBorder(BorderFactory.createEmptyBorder());
        textField4.setBounds(707, 133,138,22);
        textField4.setForeground(new Color(173,173,173));

        textField5.setFont(new Font("Inter",Font.BOLD,15));
        textField5.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField5.getText().equals("Type Phone No.")) {
                    textField5.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField5.getText().isEmpty()) {
                    textField5.setText("Type Phone No.");
                }
            }
        });
        textField5.setBorder(BorderFactory.createEmptyBorder());
        textField5.setBounds(892, 133,138,22);
        textField5.setForeground(new Color(173,173,173));

        add(textField1);
        add(textField2);
        add(textField3);
        add(textField4);
        add(textField5);
    }

    private void setButtons()
    {
        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

        searchButton = new JButton();
        searchButton.setBounds(953, 200, 15, 15);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setOpaque(false);

        addButton = new JButton();
        addButton.setBounds(1251, 132, 65, 23);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setOpaque(false);

        add(logoutButton);
        add(searchButton);
        add(addButton);
    }

    private void setSideMenuButtons(){
        viewExpiringCNIC = new JButton();
        viewExpiringCNIC.setBounds(82, 610, 131, 15);
        viewExpiringCNIC.setBorderPainted(false);
        viewExpiringCNIC.setContentAreaFilled(false);
        viewExpiringCNIC.setOpaque(false);

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


        add(viewExpiringCNIC);
        add(billInfo);
        add(updatePassword);
        add(viewReport);
        add(viewBill);
        add(changeStatus);
        add(taxesInfo);
    }

    public void setValues(ArrayList<String> list, Customer obj_c)
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
            label.setPreferredSize(new Dimension(996,40));
            label.setMaximumSize(new Dimension(996, 40));
            label.setBorder(null);


            JLabel edit = new JLabel();
            edit.setText("<html><u>Edit</u></html>");
            edit.setFont(new Font("Inter",Font.BOLD,12));
            edit.setForeground(new Color(3,149,255));
            edit.setBounds((int) 905.5,13,46,14);
            edit.setVerticalAlignment(JLabel.CENTER);
            edit.setHorizontalAlignment(JLabel.CENTER);
            label.add(edit);

            JLabel delete = new JLabel();
            delete.setText("<html><u>Delete</u></html>");
            delete.setFont(new Font("Inter",Font.BOLD,12));
            delete.setForeground(new Color(3,149,255));
            delete.setBounds((int) 950,13,46,14);
            delete.setVerticalAlignment(JLabel.CENTER);
            delete.setHorizontalAlignment(JLabel.CENTER);
            label.add(delete);

            JTextField id = new JTextField();
            id.setText(data[0]);
            id.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            id.setForeground(new Color(93,93,93));
            id.setBounds((int) 7.5, 13, 46, 14);
            id.setHorizontalAlignment(JLabel.CENTER);
            id.setBorder(BorderFactory.createEmptyBorder());
            id.setEditable(false);
            id.setFocusable(false);

            JTextField cnic = new JTextField();
            cnic.setText(data[1]);
            cnic.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            cnic.setForeground(new Color(93,93,93));
            cnic.setBounds((int) 59.5, 13, 106, 14);
            cnic.setHorizontalAlignment(JLabel.CENTER);
            cnic.setBorder(BorderFactory.createEmptyBorder());
            cnic.setEditable(false);
            cnic.setFocusable(false);

            JTextField name = new JTextField();
            name.setText(data[2]);
            name.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            name.setForeground(new Color(93,93,93));
            name.setBounds((int) 173.5, 11, 75, 18);
            name.setHorizontalAlignment(JLabel.CENTER);
            name.setBorder(BorderFactory.createEmptyBorder());
            name.setEditable(false);
            name.setFocusable(false);

            JTextField address = new JTextField();
            address.setText(data[3]);
            address.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            address.setForeground(new Color(93,93,93));
            address.setBounds((int) 262.5, 11, 90, 18);
            address.setHorizontalAlignment(JLabel.CENTER);
            address.setBorder(BorderFactory.createEmptyBorder());
            address.setEditable(false);
            address.setFocusable(false);

            JTextField phone = new JTextField();
            phone.setText(data[4]);
            phone.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            phone.setForeground(new Color(93,93,93));
            phone.setBounds((int) 363.5, 13, 106, 14);
            phone.setHorizontalAlignment(JLabel.CENTER);
            phone.setBorder(BorderFactory.createEmptyBorder());
            phone.setEditable(false);
            phone.setFocusable(false);

            String strC;
            if(data[5].equals("C")){
                strC="Commercial";
            }
            else{
                strC = "Domestic";
            }
            JTextField cType = new JTextField();
            cType.setText(strC);
            cType.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            cType.setForeground(new Color(93,93,93));
            cType.setBounds((int) 470.5, 13, 76, 14);
            cType.setHorizontalAlignment(JLabel.CENTER);
            cType.setBorder(BorderFactory.createEmptyBorder());
            cType.setEditable(false);
            cType.setFocusable(false);

            String strM;
            if(data[6].equals("S"))
            {
                strM="1-Phase";
            }
            else
            {
                strM = "3-Phase";
            }
            JTextField mType = new JTextField();
            mType.setText(strM);
            mType.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            mType.setForeground(new Color(93,93,93));
            mType.setBounds((int) 565.5, 13, 53, 14);
            mType.setHorizontalAlignment(JLabel.CENTER);
            mType.setBorder(BorderFactory.createEmptyBorder());
            mType.setEditable(false);
            mType.setFocusable(false);

            JTextField conDate = new JTextField();
            conDate.setText(data[7]);
            conDate.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            conDate.setForeground(new Color(93,93,93));
            conDate.setBounds((int) 648.5, 13, 80, 14);
            conDate.setHorizontalAlignment(JLabel.CENTER);
            conDate.setBorder(BorderFactory.createEmptyBorder());
            conDate.setEditable(false);
            conDate.setFocusable(false);

            JTextField ruc = new JTextField();
            ruc.setText(data[8]);
            ruc.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            ruc.setForeground(new Color(93,93,93));
            ruc.setBounds((int) 728.5, 13, 102, 14);
            ruc.setHorizontalAlignment(JLabel.CENTER);
            ruc.setBorder(BorderFactory.createEmptyBorder());
            ruc.setEditable(false);
            ruc.setFocusable(false);

            String str_phuc = data[9];
            if(data[9].equals("not_supported")){
                str_phuc = " ";
            }
            JTextField phuc = new JTextField();
            phuc.setText(str_phuc);
            phuc.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,12));
            phuc.setForeground(new Color(93,93,93));
            phuc.setBounds((int) 830.5, 13, 75, 14);
            phuc.setHorizontalAlignment(JLabel.CENTER);
            phuc.setBorder(BorderFactory.createEmptyBorder());
            phuc.setEditable(false);
            phuc.setFocusable(false);

            edit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String currentText = edit.getText();
                    if (currentText.contains("Edit")) {
                        edit.setText("<html><u>Update</u></html>");
                        name.setFocusable(true);
                        name.setEditable(true);
                        address.setFocusable(true);
                        address.setEditable(true);
                        phone.setFocusable(true);
                        phone.setEditable(true);
                    } else {
                        name.setFocusable(false);
                        name.setEditable(false);
                        address.setFocusable(false);
                        address.setEditable(false);
                        phone.setFocusable(false);
                        phone.setEditable(false);
                        edit.setText("<html><u>Edit</u></html>");
//                        t.updateTaxMenu(1,taxesInfo);
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    edit.setForeground(new Color(210,26,26));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    edit.setForeground(new Color(3,149,255));
                }
            });

            delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int result = JOptionPane.showConfirmDialog(null,"Delete ID: "+ id.getText() +"\nDo you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("Yes selected");
                    } else if (result == JOptionPane.NO_OPTION) {
                        System.out.println("No selected");
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    delete.setForeground(new Color(210,26,26));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    delete.setForeground(new Color(3,149,255));
                }
            });

            if(value==0){
                label.setBackground(new Color(255,255,255));
                id.setBackground(new Color(255,255,255));
                cnic.setBackground(new Color(255,255,255));
                name.setBackground(new Color(255,255,255));
                address.setBackground(new Color(255,255,255));
                phone.setBackground(new Color(255,255,255));
                cType.setBackground(new Color(255,255,255));
                mType.setBackground(new Color(255,255,255));
                ruc.setBackground(new Color(255,255,255));
                conDate.setBackground(new Color(255,255,255));
                phuc.setBackground(new Color(255,255,255));
                value=1;
            }
            else {
                value=0;
                label.setBackground(new Color(217,217,217));
                id.setBackground(new Color(217,217,217));
                cnic.setBackground(new Color(217,217,217));
                name.setBackground(new Color(217,217,217));
                address.setBackground(new Color(217,217,217));
                phone.setBackground(new Color(217,217,217));
                cType.setBackground(new Color(217,217,217));
                mType.setBackground(new Color(217,217,217));
                ruc.setBackground(new Color(217,217,217));
                conDate.setBackground(new Color(217,217,217));
                phuc.setBackground(new Color(217,217,217));
            }

            label.add(id);
            label.add(cnic);
            label.add(name);
            label.add(address);
            label.add(phone);
            label.add(cType);
            label.add(mType);
            label.add(conDate);
            label.add(ruc);
            label.add(phuc);

            panel.add(label);
        }

        scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(1000, 431));
        scroll.setBounds((int) 315.5, 299, 1000, 431);
        scroll.setBorder(null);

        add(scroll);
    }

    public void refreshPanel(ArrayList<String> newList, Customer obj_c) {
        if (scroll != null) {
            remove(scroll);
        }
        setValues(newList,obj_c);
        revalidate();
        repaint();
    }

    private void setComboBox() {
        String[] options1 = {"Domestic", "Commercial"};
        String[] options2 = {"1-Phase", "3-Phase"};

        box1 = new JComboBox<>(options1);
        box2 = new JComboBox<>(options2);

        box1.setBounds(1056, 130, 89, 27);
        box1.setFont(new Font("Inter", Font.BOLD, 12));
        box1.setForeground(new Color(173, 173, 173));
        box1.setBackground(Color.white);

        box2.setBounds(1152, 130, 89, 27);
        box2.setFont(new Font("Inter", Font.BOLD, 12));
        box2.setForeground(new Color(173, 173, 173));
        box2.setBackground(Color.white);

        add(box1);
        add(box2);
    }


    public String getSearched(){
        return textField1.getText();
    }
    public String getCNIC(){
        return textField2.getText();
    }
    public String getName(){
        return textField3.getText();
    }
    public String getAddress(){
        return textField4.getText();
    }
    public String getPhone(){
        return textField5.getText();
    }
    public String getCType(){
        return (String) box1.getSelectedItem();
    }
    public String getMType(){
        return (String) box2.getSelectedItem();
    }

    public JButton getSearchButton(){
        return searchButton;
    }
    public JButton getAddButton(){
        return addButton;
    }
    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getViewExpireButton(){return viewExpiringCNIC;}
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class GUI_Manager
{
    Billing b;
    TaxManager t;
    Customer obj_c;
    frame f;
    private JPanel oldPanel;
    private Emp_Update_Password updatePassword;
    private Emp_Change_Bill_Status changeStatus;
    private Emp_View_Report viewReport;
    private Emp_TaxesInfo taxesInfo;
    private Emp_ExpiringCNIC expiringCNIC;
    private Emp_ViewBill_NoBill viewNoneBill;
    private Emp_Bill_Found foundBill;

    public GUI_Manager() {
        b = new Billing();
        t = new TaxManager();
        obj_c = new Customer();
        Employees obj_e = new Employees();
        f = new frame();
        f.show();

        empLogInPanel emp = new empLogInPanel();
        custLogInPanel cust = new custLogInPanel();

        f.addPanel(emp.getPanel());

        emp.getCustButton().addActionListener(eListen -> {
            f.replacePanel(emp.getPanel(), cust.getPanel());
        });
        emp.getLoginButton().addActionListener(eListen -> {
            if (obj_e.validateEmployee(emp.getUserName(), emp.getPass())) {
                String[] pass= new String[]{emp.getPass()};
                EmpOptionMenu(f,obj_e, emp.getUserName(), pass,emp);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cust.getEmpButton().addActionListener(eListen -> {
            f.replacePanel(cust.getPanel(), emp.getPanel());
        });
        cust.getLoginButton().addActionListener(eListen -> {
            if (obj_c.isCustomerValid(cust.getUserID(), cust.getCNIC())) {
                CustOptionMenu(f, obj_c.getUserName(), cust);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect ID or CNIC", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void CustOptionMenu(frame f, String name, custLogInPanel old)
    {
        Cust_Bill_Not_Found noBill = new Cust_Bill_Not_Found(name);
        f.replacePanel(old.getPanel(), noBill.getPanel());

        obj_c.CustInMenu(f, name, noBill);
    }

    private void EmpOptionMenu(frame f, Employees obj_e, String name, String[] pass, empLogInPanel old) {

        updatePassword = new Emp_Update_Password(name);
        changeStatus = new Emp_Change_Bill_Status(name);
        viewReport = new Emp_View_Report(name);
        taxesInfo = new Emp_TaxesInfo(name);
        expiringCNIC = new Emp_ExpiringCNIC(name);
        viewNoneBill = new Emp_ViewBill_NoBill(name);
        foundBill = new Emp_Bill_Found(name);


        f.replacePanel(old, updatePassword.getPanel());
        oldPanel = updatePassword.getPanel();

        //---------------------Update Password Panel Settings----------------------//

        updatePassword.getUpdateButton().addActionListener(e->{
            String newPass = obj_e.updateMenu(name, pass[0], updatePassword);
            if (!newPass.equals("no change")) {
                pass[0] = newPass;
            }
        });
        updatePassword.getLogoutButton().addActionListener(this::ActionPerformer);
        updatePassword.getCustomerInfoButton().addActionListener(this::ActionPerformer);
        updatePassword.getBillInfoButton().addActionListener(this::ActionPerformer);
        updatePassword.getChangeStatusButton().addActionListener(this::ActionPerformer);
        updatePassword.gettaxesButton().addActionListener(this::ActionPerformer);
        updatePassword.getViewBillButton().addActionListener(this::ActionPerformer);
        updatePassword.getViewReportButton().addActionListener(this::ActionPerformer);
        updatePassword.getViewExpireButton().addActionListener(this::ActionPerformer);

        //---------------------Change Bill Status Panel Settings----------------------//

        changeStatus.getUpdateButton().addActionListener(e->{
            if(b.changePaidStatus(changeStatus))
            {
                changeStatus.setOutput("Bill Status Updated Successfully!");
            }
            else{
                changeStatus.setOutput("No Bill Status Changed!");
            }
        });
        changeStatus.getLogoutButton().addActionListener(this::ActionPerformer);
        changeStatus.getCustomerInfoButton().addActionListener(this::ActionPerformer);
        changeStatus.getBillInfoButton().addActionListener(this::ActionPerformer);
        changeStatus.getUpdatePasswordButton().addActionListener(this::ActionPerformer);
        changeStatus.gettaxesButton().addActionListener(this::ActionPerformer);
        changeStatus.getViewBillButton().addActionListener(this::ActionPerformer);
        changeStatus.getViewReportButton().addActionListener(this::ActionPerformer);
        changeStatus.getViewExpireButton().addActionListener(this::ActionPerformer);

        //---------------------View Report Panel Settings--------------------------//

        viewReport.getLogoutButton().addActionListener(this::ActionPerformer);
        viewReport.getCustomerInfoButton().addActionListener(this::ActionPerformer);
        viewReport.getBillInfoButton().addActionListener(this::ActionPerformer);
        viewReport.getUpdatePasswordButton().addActionListener(this::ActionPerformer);
        viewReport.gettaxesButton().addActionListener(this::ActionPerformer);
        viewReport.getViewBillButton().addActionListener(this::ActionPerformer);
        viewReport.getChangeStatusButton().addActionListener(this::ActionPerformer);
        viewReport.getViewExpireButton().addActionListener(this::ActionPerformer);

        //---------------------Tax Info Panel Settings-------------------------------//

        taxesInfo.getEdit1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String currentText = taxesInfo.getEdit1().getText();
                if (currentText.contains("Edit")) {
                    taxesInfo.getEdit1().setText("<html><u>Update</u></html>");
                    taxesInfo.getR1().setFocusable(true);
                    taxesInfo.getR1().setEditable(true);
                    taxesInfo.getT1().setFocusable(true);
                    taxesInfo.getT1().setEditable(true);
                    taxesInfo.getFC1().setFocusable(true);
                    taxesInfo.getFC1().setEditable(true);
                } else {
                    taxesInfo.getR1().setFocusable(false);
                    taxesInfo.getR1().setEditable(false);
                    taxesInfo.getT1().setFocusable(false);
                    taxesInfo.getT1().setEditable(false);
                    taxesInfo.getFC1().setFocusable(false);
                    taxesInfo.getFC1().setEditable(false);
                    taxesInfo.getEdit1().setText("<html><u>Edit</u></html>");
                    t.updateTaxMenu(1,taxesInfo);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                taxesInfo.getEdit1().setForeground(new Color(210,26,26));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                taxesInfo.getEdit1().setForeground(new Color(3,149,255));
            }
        });
        taxesInfo.getEdit2().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String currentText = taxesInfo.getEdit2().getText();
                if (currentText.contains("Edit")) {
                    taxesInfo.getEdit2().setText("<html><u>Update</u></html>");
                    taxesInfo.getR2().setFocusable(true);
                    taxesInfo.getR2().setEditable(true);
                    taxesInfo.getT2().setFocusable(true);
                    taxesInfo.getT2().setEditable(true);
                    taxesInfo.getFC2().setFocusable(true);
                    taxesInfo.getFC2().setEditable(true);
                } else {
                    taxesInfo.getEdit2().setText("<html><u>Edit</u></html>");
                    taxesInfo.getR2().setFocusable(false);
                    taxesInfo.getR2().setEditable(false);
                    taxesInfo.getT2().setFocusable(false);
                    taxesInfo.getT2().setEditable(false);
                    taxesInfo.getFC2().setFocusable(false);
                    taxesInfo.getFC2().setEditable(false);
                    t.updateTaxMenu(2,taxesInfo);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                taxesInfo.getEdit2().setForeground(new Color(210,26,26));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                taxesInfo.getEdit2().setForeground(new Color(3,149,255));
            }
        });
        taxesInfo.getEdit3().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String currentText = taxesInfo.getEdit3().getText();
                if (currentText.contains("Edit")) {
                    taxesInfo.getEdit3().setText("<html><u>Update</u></html>");
                    taxesInfo.getR3().setFocusable(true);
                    taxesInfo.getR3().setEditable(true);
                    taxesInfo.getP3().setFocusable(true);
                    taxesInfo.getP3().setEditable(true);
                    taxesInfo.getT3().setFocusable(true);
                    taxesInfo.getT3().setEditable(true);
                    taxesInfo.getFC3().setFocusable(true);
                    taxesInfo.getFC3().setEditable(true);
                } else {
                    taxesInfo.getR3().setFocusable(false);
                    taxesInfo.getR3().setEditable(false);
                    taxesInfo.getP3().setFocusable(false);
                    taxesInfo.getP3().setEditable(false);
                    taxesInfo.getT3().setFocusable(false);
                    taxesInfo.getT3().setEditable(false);
                    taxesInfo.getFC3().setFocusable(false);
                    taxesInfo.getFC3().setEditable(false);
                    taxesInfo.getEdit3().setText("<html><u>Edit</u></html>");
                    t.updateTaxMenu(3,taxesInfo);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                taxesInfo.getEdit3().setForeground(new Color(210,26,26));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                taxesInfo.getEdit3().setForeground(new Color(3,149,255));
            }
        });
        taxesInfo.getEdit4().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String currentText = taxesInfo.getEdit4().getText();
                if (currentText.contains("Edit")) {
                    taxesInfo.getEdit4().setText("<html><u>Update</u></html>");
                    taxesInfo.getR4().setFocusable(true);
                    taxesInfo.getR4().setEditable(true);
                    taxesInfo.getP4().setFocusable(true);
                    taxesInfo.getP4().setEditable(true);
                    taxesInfo.getT4().setFocusable(true);
                    taxesInfo.getT4().setEditable(true);
                    taxesInfo.getFC4().setFocusable(true);
                    taxesInfo.getFC4().setEditable(true);
                } else {
                    taxesInfo.getEdit4().setText("<html><u>Edit</u></html>");
                    taxesInfo.getR4().setFocusable(false);
                    taxesInfo.getR4().setEditable(false);
                    taxesInfo.getP4().setFocusable(false);
                    taxesInfo.getP4().setEditable(false);
                    taxesInfo.getT4().setFocusable(false);
                    taxesInfo.getT4().setEditable(false);
                    taxesInfo.getFC4().setFocusable(false);
                    taxesInfo.getFC4().setEditable(false);
                    t.updateTaxMenu(4,taxesInfo);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                taxesInfo.getEdit4().setForeground(new Color(210,26,26));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                taxesInfo.getEdit4().setForeground(new Color(3,149,255));
            }
        });
        taxesInfo.getLogoutButton().addActionListener(this::ActionPerformer);
        taxesInfo.getCustomerInfoButton().addActionListener(this::ActionPerformer);
        taxesInfo.getBillInfoButton().addActionListener(this::ActionPerformer);
        taxesInfo.getUpdatePasswordButton().addActionListener(this::ActionPerformer);
        taxesInfo.getViewReportButton().addActionListener(this::ActionPerformer);
        taxesInfo.getViewBillButton().addActionListener(this::ActionPerformer);
        taxesInfo.getChangeStatusButton().addActionListener(this::ActionPerformer);
        taxesInfo.getViewExpireButton().addActionListener(this::ActionPerformer);

        //---------------------Expiring Cnic Panel Settings-------------------------------//

        expiringCNIC.getLogoutButton().addActionListener(this::ActionPerformer);
        expiringCNIC.getCustomerInfoButton().addActionListener(this::ActionPerformer);
        expiringCNIC.getBillInfoButton().addActionListener(this::ActionPerformer);
        expiringCNIC.getUpdatePasswordButton().addActionListener(this::ActionPerformer);
        expiringCNIC.getViewReportButton().addActionListener(this::ActionPerformer);
        expiringCNIC.getViewBillButton().addActionListener(this::ActionPerformer);
        expiringCNIC.getChangeStatusButton().addActionListener(this::ActionPerformer);
        expiringCNIC.gettaxesButton().addActionListener(this::ActionPerformer);

        //---------------------View Bill Panel Settings-----------------------------------//

        foundBill.getLogoutButton().addActionListener(this::ActionPerformer);
        foundBill.getCustomerInfoButton().addActionListener(this::ActionPerformer);
        foundBill.getBillInfoButton().addActionListener(this::ActionPerformer);
        foundBill.getUpdatePasswordButton().addActionListener(this::ActionPerformer);
        foundBill.getViewReportButton().addActionListener(this::ActionPerformer);
        foundBill.getViewExpireButton().addActionListener(this::ActionPerformer);
        foundBill.getChangeStatusButton().addActionListener(this::ActionPerformer);
        foundBill.gettaxesButton().addActionListener(this::ActionPerformer);
        foundBill.getGobackButton().addActionListener(e->{
            f.replacePanel(oldPanel,viewNoneBill.getPanel());
            oldPanel = viewNoneBill.getPanel();
        });

        viewNoneBill.getSearchButton().addActionListener(e->{
            if(b.viewBill(f,viewNoneBill)){
                String[] data = b.getBillList();
                foundBill.clearData();
                foundBill.setImg(name,data[10]);
                foundBill.setData(data);
                f.replacePanel(oldPanel,foundBill.getPanel());
                oldPanel = foundBill.getPanel();
            }
        });
        viewNoneBill.getLogoutButton().addActionListener(this::ActionPerformer);
        viewNoneBill.getCustomerInfoButton().addActionListener(this::ActionPerformer);
        viewNoneBill.getBillInfoButton().addActionListener(this::ActionPerformer);
        viewNoneBill.getUpdatePasswordButton().addActionListener(this::ActionPerformer);
        viewNoneBill.getViewReportButton().addActionListener(this::ActionPerformer);
        viewNoneBill.getViewExpireButton().addActionListener(this::ActionPerformer);
        viewNoneBill.getChangeStatusButton().addActionListener(this::ActionPerformer);
        viewNoneBill.gettaxesButton().addActionListener(this::ActionPerformer);

        //---------------------Customer Info Panel Settings-------------------------------//



//            if(input.equals("2"))
//            {
//                if(c.addCustomer())
//                {
//                    System.out.println("\nCustomer Added Successfully!");
//                }
//            }
//            else if(input.equals("3"))
//            {
//                if(b.addNewBill())
//                {
//                    System.out.println("\nBill Added Successfully!");
//                }
//            }
    }

    private void ActionPerformer(ActionEvent e)
    {
        if (e.getSource() == updatePassword.getLogoutButton() || e.getSource() == changeStatus.getLogoutButton() || e.getSource() == viewReport.getLogoutButton() || e.getSource() == taxesInfo.getLogoutButton() || e.getSource() == expiringCNIC.getLogoutButton() || e.getSource() == viewNoneBill.getLogoutButton() || e.getSource() == foundBill.getLogoutButton())
        {
            f.destroy();
            GUI_Manager g = new GUI_Manager();
        }
        else if(e.getSource() == changeStatus.getUpdatePasswordButton() || e.getSource() == viewReport.getUpdatePasswordButton() || e.getSource() == taxesInfo.getUpdatePasswordButton() || e.getSource() == expiringCNIC.getUpdatePasswordButton() || e.getSource() == viewNoneBill.getUpdatePasswordButton() || e.getSource() == foundBill.getUpdatePasswordButton()){
            f.replacePanel(oldPanel,updatePassword.getPanel());
            oldPanel=updatePassword.getPanel();
        }
        else if(e.getSource() == updatePassword.getCustomerInfoButton() || e.getSource() == changeStatus.getCustomerInfoButton() || e.getSource() == viewReport.getCustomerInfoButton() || e.getSource() == taxesInfo.getCustomerInfoButton() || e.getSource() == expiringCNIC.getCustomerInfoButton() || e.getSource() == viewNoneBill.getCustomerInfoButton() || e.getSource() == foundBill.getCustomerInfoButton()){
            JOptionPane.showMessageDialog(null,"Customer Info Button Pressed","Message",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getSource() == updatePassword.getBillInfoButton() || e.getSource() == changeStatus.getBillInfoButton() || e.getSource() == viewReport.getBillInfoButton() || e.getSource() == taxesInfo.getBillInfoButton() || e.getSource() == expiringCNIC.getBillInfoButton() || e.getSource() == viewNoneBill.getBillInfoButton() || e.getSource() == foundBill.getBillInfoButton()){
            JOptionPane.showMessageDialog(null,"Bill Info Button Pressed","Message",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getSource() == updatePassword.getChangeStatusButton() || e.getSource() == viewReport.getChangeStatusButton() || e.getSource() == taxesInfo.getChangeStatusButton() || e.getSource() == expiringCNIC.getChangeStatusButton() || e.getSource() == viewNoneBill.getChangeStatusButton() || e.getSource() == foundBill.getChangeStatusButton()){
            f.replacePanel(oldPanel,changeStatus.getPanel());
            oldPanel=changeStatus.getPanel();
        }
        else if(e.getSource() == updatePassword.gettaxesButton() || e.getSource() == changeStatus.gettaxesButton() || e.getSource() == viewReport.gettaxesButton() || e.getSource() == expiringCNIC.gettaxesButton() || e.getSource() == viewNoneBill.gettaxesButton() || e.getSource() == foundBill.gettaxesButton()){
            taxesInfo.setValues(t.getData());
            f.replacePanel(oldPanel,taxesInfo.getPanel());
            oldPanel = taxesInfo.getPanel();
        }
        else if(e.getSource() == updatePassword.getViewBillButton() || e.getSource() == changeStatus.getViewBillButton() || e.getSource() == viewReport.getViewBillButton() || e.getSource() == taxesInfo.getViewBillButton() || e.getSource() == expiringCNIC.getViewBillButton()){
            f.replacePanel(oldPanel,viewNoneBill.getPanel());
            oldPanel = viewNoneBill.getPanel();
        }
        else if(e.getSource() == updatePassword.getViewReportButton() || e.getSource() == changeStatus.getViewReportButton() || e.getSource() == taxesInfo.getViewReportButton() || e.getSource() == expiringCNIC.getViewReportButton() || e.getSource() == viewNoneBill.getViewReportButton() || e.getSource() == foundBill.getViewReportButton()){
            b.viewReport(viewReport);
            f.replacePanel(oldPanel,viewReport.getPanel());
            oldPanel = viewReport.getPanel();
        }
        else if(e.getSource() == updatePassword.getViewExpireButton() || e.getSource() == changeStatus.getViewExpireButton() || e.getSource() == viewReport.getViewExpireButton() || e.getSource() == taxesInfo.getViewExpireButton() || e.getSource() == viewNoneBill.getViewExpireButton() || e.getSource() == foundBill.getViewExpireButton()){
            expiringCNIC.setValues(obj_c.viewExpireCnic());
            f.replacePanel(oldPanel,expiringCNIC.getPanel());
            oldPanel = expiringCNIC.getPanel();
        }
    }
}

import java.util.*;

public class Main
{
    public static void CustOptionMenu(){
        Customer c = new Customer();
        Scanner scanner = new Scanner(System.in);
        String input;

        while(true)
        {
            System.out.println("\n\n\t\t\tCustomer Menu\n");
            System.out.print("1) View Bill\n2) Update CNIC Expiry Date\n3) Go Back\n\nChoice: ");
            input = scanner.nextLine();

            if(input.equals("1"))
            {
                c.CustInMenu();
            }
            else if(input.equals("2"))
            {
                System.out.println("\nGo Back -> 00\n");
                if(c.updateCNIC())
                {
                    System.out.println("CNIC Updated Successfully!");
                }
            }
            else if(input.equals("3"))
            {
                break;
            }
            else{
                System.out.println("Incorrect Choice: Try Again");
            }
        }
    }

    public static void EmpOptionMenu(Employees e,String name, String pass)
    {
        Billing b = new Billing();
        TaxManager t = new TaxManager();
        Customer c = new Customer();
        t.addTaxFile();

        Scanner scanner = new Scanner(System.in);
        String input;
        String newPass;

        while(true)
        {
            System.out.println("\n\n\t\t\tEmployee Menu\n");
            System.out.print("1) Update Password\n2) Add Customer\n3) Add Bill\n4) Update Bill Paid Status\n5) Change Taxes\n6) View Bill\n7) View Paid/UnPaid Report\n8) View Expiring CNIC's\n9) Go Back\n\nChoice: ");
            input = scanner.nextLine();

            if(input.equals("1"))
            {
                newPass = e.updateMenu(name,pass);
                if(!newPass.equals("no change"))
                {
                    pass = newPass;
                }
            }
            else if(input.equals("2"))
            {
                System.out.println("\nGo Back -> 00\n");
                if(c.addCustomer())
                {
                    System.out.println("\nCustomer Added Successfully!");
                }
            }
            else if(input.equals("3"))
            {
                System.out.println("\nGo Back -> 00\n");
                if(b.addNewBill())
                {
                    System.out.println("\nBill Added Successfully!");
                }
            }
            else if(input.equals("4")) // get input of month and reading entry date as well to chnage status
            {
                System.out.println("\nGo Back -> 00\n");
                if(b.changePaidStatus())
                {
                    System.out.println("\nBill Paid Status Updated Successfully!");
                }
            }
            else if(input.equals("5"))
            {
                System.out.println("\nGo Back -> 00\n");
                t.updateTaxMenu();
            }
            else if(input.equals("6")) // get input of month and reading entry date as well to view bill
            {
                System.out.println("\nGo Back -> 00\n");
                b.viewBill();
            }
            else if(input.equals("7"))
            {
                b.viewReport();
            }
            else if(input.equals("8"))
            {
                c.viewExpireCnic();
            }
            else if(input.equals("9"))
            {
                break;
            }
            else{
                System.out.println("Incorrect Choice: Try Again");
            }
        }
    }

    public static void LogInMenuEmp()
    {
        Employees e = new Employees();

        Scanner scanner = new Scanner(System.in);
        String name;
        String pass;

        while(true) {
            System.out.println("\nGo Back -> 00\n");
            System.out.print("Enter Username: ");
            name = scanner.nextLine();

            if(name.equals("00"))
            {
                break;
            }

            System.out.print("Enter Password: ");
            pass = scanner.nextLine();

            if(pass.equals("00"))
            {
                break;
            }

            if (e.validateEmployee(name, pass)) {
                EmpOptionMenu(e,name,pass);
            } else {
                System.out.print("\n\nIncorrect Username or Password");
            }
        }
    }

    public static void mainMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String input;

        while(true)
        {
            System.out.print("\n\n--------------------\n\t\tMENU\n--------------------\n1) Employee LogIn\n2) Customer Page\n3) Exit\n\nChoice: ");
            input = scanner.nextLine();

            if (input.equals("1"))
            {
                LogInMenuEmp();
            }
            else if (input.equals("2"))
            {
                CustOptionMenu();
            }
            else if (input.equals("3"))
            {
                break;
            }
            else
            {
                System.out.print("Incorrect Choice: Try Again");
            }
        }
    }

    public static void main(String[] args)
    {
        mainMenu();
    }
}
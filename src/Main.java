import java.util.*;

public class Main
{

    public static void EmpOptionMenu(Employees e,String name, String pass)
    {
        Billing b = new Billing();
        TaxManager t = new TaxManager();
        t.addTaxFile();

        Scanner scanner = new Scanner(System.in);
        String input;
        String newPass;

        while(true)
        {
            System.out.println("\n\n\t\t\tEmployee Menu\n");
            System.out.print("1) Update Password\n2) Add Customer\n3) Add Bill\n4) Update Bill Paid Status\n5) Change Taxes\n6) View Bill\n7) Go Back\n\nChoice: ");
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
                Customer c = new Customer();
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
            else if(input.equals("4"))
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
            else if(input.equals("6"))
            {
                System.out.println("\nGo Back -> 00\n");
                b.viewBill();
            }
            else if(input.equals("7"))
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
            System.out.print("1) Employee LogIn\n2) LogIn Customer\n3) Exit\n\nChoice: ");
            input = scanner.nextLine();

            if (input.equals("1"))
            {
                LogInMenuEmp();
            }
            else if (input.equals("2"))
            {

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
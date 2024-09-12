import java.io.*;
import java.util.*;

public class Employees
{
    String filename = "EmployeesData.txt";

    public boolean validateEmployee(String username, String pass)
    {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                String[] namePass = line.split(",");
                if(namePass[0].equals(username) && namePass[1].equals(pass))
                {
                    return true;
                }
            }
            fr.close();
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: File Reading: " + e.getMessage());
        }

        return false;
    }

    public String updateMenu(String username, String oldPass)
    {
        Scanner input = new Scanner(System.in);
        String inName;
        String inPass;
        String newPass;

        System.out.print("Enter current Username: ");
        inName = input.nextLine();

        System.out.print("Enter current Password: ");
        inPass = input.nextLine();

        System.out.print("Enter new Password: ");
        newPass = input.nextLine();

        if(newPass.equals(inPass))
        {
            System.out.println("\nNew Password cannot be same as Old Password");
        }
        else if(inName.equals(username) && oldPass.equals(inPass))
        {
            updatePass(username,newPass);
            System.out.println("\nPassword Updated Successfully");
            return newPass;
        }
        else
        {
            System.out.println("\nUsername or Password Do not Match");
        }
        return "no change";
    }

    void updatePass(String username, String newPass)
    {
        ArrayList<String> data = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                String[] namePass = line.split(",");
                if (namePass[0].equals(username)) {
                    namePass[1] = newPass;
                    data.add(namePass[0] + "," + namePass[1]);
                } else {
                    data.add(line);
                }
            }
            fr.close();
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: File Reading: " + e.getMessage());
        }

        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < data.size(); i++) {
                bw.write(data.get(i));
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("Error: File Writing");
        }
    }
}

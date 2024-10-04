import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    public String updateMenu(String username, String oldPass,Emp_Update_Password updatePassword)
    {
        String inName = updatePassword.getUserName();
        String inPass = updatePassword.getOld();
        String newPass = updatePassword.getNewPass();

        if(newPass.equals(inPass))
        {
            JOptionPane.showMessageDialog(null,"New Password cannot be same as Old Password","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(newPass.isEmpty() || newPass.equals("Type your new Password")){
            JOptionPane.showMessageDialog(null,"New Password is Empty or Invalid","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(inName.equals(username) && oldPass.equals(inPass))
        {
            updatePass(username,newPass);
            JOptionPane.showMessageDialog(null,"Password Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
            return newPass;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Username or Password Do not Match","Error",JOptionPane.ERROR_MESSAGE);
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

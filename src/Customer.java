import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Customer
{
    private String filename = "CustomerInfo.txt";

    public boolean addCustomer()
    {
        Scanner scanner = new Scanner(System.in);

        String cnic;
        String name;
        String address;
        String phone;
        String custType;
        String meterType;
        String RUC = "0";
        String PHUC;

        while(true) {
            System.out.print("Enter CNIC: ");
            cnic = scanner.nextLine();

            int count = cnic_count(cnic);

            if(cnic.equals("00"))
            {
                return false;
            }
            else if(count >=3)
            {
                System.out.println("Not Allowed! Maximum 3 meters allowed per CNIC");
                return false;
            }
            else if(cnic.length()==13 && isDigits(cnic) && searchNadraFile(cnic))
            {
                break;
            }
            System.out.println("Invalid CNIC : Try Again");
        }
        while(true) {
            System.out.print("Enter Name: ");
            name = scanner.nextLine();

            if(name.equals("00"))
            {
                return false;
            }
            else if(isAlphabets(name))
            {
                break;
            }
            System.out.println("Incorrect Name Input : Try Again");
        }

        System.out.print("Enter Address: ");
        address = scanner.nextLine();
        if(address.equals("00"))
        {
            return false;
        }

        while(true) {
            System.out.print("Enter Phone Number: ");
            phone = scanner.nextLine();

            if(phone.equals("00"))
            {
                return false;
            }
            else if(phone.length()==11 && isDigits(phone))
            {
                break;
            }
            System.out.println("Incorrect Phone Number : Try Again");
        }

        while(true) {
            System.out.print("Customer Type (Commercial -> C / Domestic -> D): ");
            custType = scanner.nextLine();

            if(custType.equals("00"))
            {
                return false;
            }
            else if(custType.equals("C") || custType.equals("c") || custType.equals("d") ||custType.equals("D"))
            {
                break;
            }
            System.out.println("Incorrect Type : Try Again");
        }

        while(true) {
            System.out.print("Enter Meter Type (Single -> S / Three -> T): ");
            meterType = scanner.nextLine();

            if(meterType.equals("00"))
            {
                return false;
            }
            else if(meterType.equals("S") || meterType.equals("s") || meterType.equals("t") || meterType.equals("T"))
            {
                if(meterType.equals("S") || meterType.equals("s"))
                {
                    PHUC = "not_supported";
                }
                else
                {
                    PHUC = "0";
                }
                break;
            }
            System.out.println("Incorrect Meter Type : Try Again");
        }
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = currentDate.format(formatter);

        String id;
        while(true) {
            Random r = new Random();
            id = String.valueOf(1000 + r.nextInt(9000));
            if(isUnique(id,0))
            {
                break;
            }
        }

        String data = id + "," + cnic + "," + name + "," + address + "," + phone + "," + custType + "," + meterType + "," + date + "," + RUC + "," + PHUC;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true))) {
            bw.write(data);
            bw.newLine();
        }
        catch(IOException e)
        {
            System.out.println("Error while writing to File: " + e.getMessage());
        }
        return true;
    }

    public boolean validateCustomer(String id, String cnic)
    {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                String[] idCnic = line.split(",");
                if(idCnic[0].equals(id) && idCnic[1].equals(cnic))
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

    public int cnic_count(String cnic)
    {
        String line="";
        int count=0;
        String[] data;
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            while((line=br.readLine())!=null)
            {
                data = line.split(",");
                if(data[1].equals(cnic))
                {
                    count++;
                }
            }
        }catch (IOException e){
            System.out.println("Error while reading file: " + e.getMessage());
        }
        return count;
    }

    public boolean searchNadraFile(String cnic)
    {
        String line;
        String[] data;
        try(BufferedReader br = new BufferedReader(new FileReader("NADRADBfile.txt")))
        {
            while((line= br.readLine())!=null)
            {
                data = line.split(",");
                if(data[0].equals(cnic))
                {
                    return true;
                }
            }
        }catch (IOException e) {
            System.out.println("Error while reading : " + e.getMessage());
        }
        return false;
    }

    public boolean isAlphabets(String str)
    {
        for(int i=0; i<str.length();i++)
        {
            if(!Character.isLetter(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    public boolean isDigits(String str)
    {
        for(int i=0; i<str.length();i++)
        {
            if(!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    public boolean isUnique(String str, int index)
    {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[index].equals(str)) {
                    return false;
                }
            }
            fr.close();
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Reading Error: " + e.getMessage());
        }
        return true;
    }
}

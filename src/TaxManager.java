import java.io.*;
import java.util.*;

public class TaxManager {
    private String filename = "TariffTaxInfo.txt";

    public void addTaxFile()
    {
        String line1 = "1Phase,5,,17,150";
        String line2 = "1Phase,15,,20,250";
        String line3 = "3Phase,8,12,17,150";
        String line4 = "3Phase,18,25,20,250";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            bw.write(line1);
            bw.newLine();
            bw.write(line2);
            bw.newLine();
            bw.write(line3);
            bw.newLine();
            bw.write(line4);
            bw.newLine();
        }catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean updateTaxMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String meterType;
        String custType;

        while(true) {
            System.out.print("\n\nChoose the Customer Type:\n1) Domestic\n2) Commercial\n\nChoice: ");
            custType = scanner.nextLine();

            if(custType.equals("00"))
            {
                return false;
            }
            else if(custType.equals("1"))
            {
                custType = "d";
                break;
            }
            else if(custType.equals("2"))
            {
                custType = "c";
                break;
            }
            else{
                System.out.println("Invalid Choice: Try Again");
            }
        }

        while(true) {
            System.out.print("\n\nChoose the Meter Type:\n1) Single\n2) Three\n\nChoice: ");
            meterType = scanner.nextLine();

            if(meterType.equals("00"))
            {
                return false;
            }
            else if(meterType.equals("1"))
            {
                meterType = "s";
                break;
            }
            else if(meterType.equals("2"))
            {
                meterType = "t";
                break;
            }
            else{
                System.out.println("Invalid Choice: Try Again");
            }
        }

        int row=0;
        int col = 0;
        if(custType.equals("d") && meterType.equals("s"))
        {
            row = 1;
        }
        else if(custType.equals("c") && meterType.equals("s"))
        {
            row = 2;
        }
        else if(custType.equals("d") && meterType.equals("t"))
        {
            row=3;
        }
        else if(custType.equals("c") && meterType.equals("t"))
        {
            row=4;
        }

        if(meterType.equals("s")) {
            String value="";
            String input;
            while (true) {
                System.out.print("\n\nYou want to Change:\n1) Regular Unit Price\n2) Percentage of Tax\n3) Fixed Charged\n4) Exit\n\nChoice: ");
                input = scanner.nextLine();
                if (input.equals("4")) {
                    return false;
                }

                System.out.print("Enter Value: ");
                value = scanner.nextLine();

                if(!isDigits(value))
                {
                    System.out.println("Invalid Choice: Try Again");
                    continue;
                }
                if(Integer.parseInt(value)<0)
                {
                    System.out.println("Invalid Choice: Try Again");
                    continue;
                }

                if (input.equals("1")) {
                    col=1;
                    performTaxChanges(row,col,value);
                }
                else if (input.equals("2")) {
                    col=3;
                    performTaxChanges(row,col,value);
                }
                else if (input.equals("3")) {
                    col=4;
                    performTaxChanges(row,col,value);
                }
                else {
                    System.out.println("Invalid Choice: Try Again");
                }
            }
        }
        else if(meterType.equals("t")) {
            String value="";
            String input;
            while (true) {
                System.out.print("\n\nYou want to Change:\n1) Regular Unit Price\n2) Peak Hour Unit Price\n3) Percentage of Tax\n4) Fixed Charged\n5) Exit\n\nChoice: ");
                input = scanner.nextLine();
                if (input.equals("5")) {
                    return false;
                }
                System.out.print("Enter Value: ");
                value = scanner.nextLine();

                if(!isDigits(value))
                {
                    System.out.println("Invalid Choice: Try Again");
                    continue;
                }
                if(Integer.parseInt(value)<0)
                {
                    System.out.println("Invalid Choice: Try Again");
                    continue;
                }

                if (input.equals("1")) {
                    col=1;
                    performTaxChanges(row,col,value);
                }
                else if (input.equals("2")) {
                    col=2;
                    performTaxChanges(row,col,value);
                }
                else if (input.equals("3")) {
                    col=3;
                    performTaxChanges(row,col,value);
                }
                else if (input.equals("4")) {
                    col=4;
                    performTaxChanges(row,col,value);
                }
                else if (input.equals("5")) {
                    return false;
                }
                else {
                    System.out.println("Invalid Choice: Try Again");
                }
            }
        }
        return true;
    }

    public void performTaxChanges(int row, int col, String value)
    {
        String line;
        String[] data;
        ArrayList<String> array = new ArrayList<>();
        int count=1;

        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            while((line= br.readLine())!=null)
            {
                if(count == row)
                {
                    data = line.split(",");
                    data[col] = value;
                    array.add(data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4]);
                }
                else
                {
                    array.add(line);
                }
                count++;
            }
        }catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            for(int i=0;i<array.size();i++)
            {
                bw.write(array.get(i));
                bw.newLine();
            }
        }catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n\nTaxes Updated Successfully!");
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
}

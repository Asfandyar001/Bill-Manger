import java.io.*;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Customer
{
    private String custFilename = "CustomerInfo.txt";
    private String billFilename = "BillingInfo.txt";
    private String tariffFilename = "TariffTaxInfo.txt";
    private String[] custInfo;
    private String[] billInfo;
    private String[] tariffInfo;
    private String[] nadraInfo;

    public void CustInMenu(){
        Scanner scanner = new Scanner(System.in);
        String id;
        String cnic;
        String month;
        String year;
        int intYear=0;

        Customer c = new Customer();

        while(true) {
            System.out.println("\nGo Back -> 00\n");
            System.out.print("Enter ID: ");
            id = scanner.nextLine();

            if(id.equals("00"))
            {
                break;
            }

            System.out.print("Enter CNIC: ");
            cnic = scanner.nextLine();

            if(cnic.equals("00"))
            {
                break;
            }

            while(true)
            {
                System.out.print("Enter Bill Month: ");
                month = scanner.nextLine();

                if(month.equals("00"))
                {
                    break;
                }
                if(month.equals("Jan") || month.equals("Feb") || month.equals("Mar") || month.equals("April") || month.equals("May") || month.equals("June") || month.equals("July") || month.equals("August") || month.equals("Sept") || month.equals("Oct") || month.equals("Nov") || month.equals("Dec"))
                {
                    break;
                }
                System.out.println("Incorrect Month: Try Again");
            }
            if(month.equals("00"))
            {
                break;
            }

            while(true) {
                System.out.print("Enter Year: ");
                year = scanner.nextLine();

                if (year.equals("00")) {
                    break;
                }
                if(year.matches("\\d{4}") && Integer.parseInt(year) > 0)
                {
                    intYear = Integer.parseInt(year);
                    break;
                }
                System.out.println("Invalid Year Try Again");
            }
            if (year.equals("00")) {
                break;
            }

            if (validateCustomer(id, cnic,month,intYear)) {
                viewBill();
            } else {
                System.out.print("\n\nIncorrect ID or CNIC");
            }
        }
    }

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
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(custFilename,true))) {
            bw.write(data);
            bw.newLine();
        }
        catch(IOException e)
        {
            System.out.println("Error while writing to File: " + e.getMessage());
        }
        return true;
    }

    public boolean validateCustomer(String id, String cnic, String month, int year)
    {
        boolean valid = false;
        try {
            FileReader fr = new FileReader(custFilename);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                String[] idCnic = line.split(",");
                if(idCnic[0].equals(id) && idCnic[1].equals(cnic))
                {
                    custInfo = idCnic;
                    getTaxData(idCnic[5],idCnic[6]);
                    valid = true;
                }
            }
            fr.close();
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: File Reading: " + e.getMessage());
        }

        if(valid)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String temp = id + ",Not Found,Not Found,Not Found,Not Found,Not Found,Not Found,Not Found,Not Found,Not Found,Not Found,Not Found";
            try(BufferedReader br = new BufferedReader(new FileReader(billFilename))){
                String line;
                String[] data;
                while((line=br.readLine())!=null){
                    data = line.split(",");
                    if(data[0].equals(id) && data[1].equals(month)){
                        LocalDate date = LocalDate.parse(data[4],formatter);
                        int fileYear = date.getYear();
                        if(fileYear==year)
                        {
                            billInfo=data;
                            break;
                        }
                    }
                    else{
                        billInfo=temp.split(",");
                    }
                }
            }catch (IOException e){
                System.out.println("Error:" + e.getMessage());
            }
            return true;
        }

        return false;
    }

    public boolean updateCNIC()
    {
        Scanner scanner = new Scanner(System.in);
        String id;
        String cnic;
        while(true) {
            System.out.print("Enter ID: ");
            id = scanner.nextLine();

            if (id.equals("00")) {
                return false;
            }

            System.out.print("Enter CNIC: ");
            cnic = scanner.nextLine();

            if (cnic.equals("00")) {
                return false;
            }

            if(isDigits(id) && isDigits(cnic) && searchNadraFile(cnic) && validateCustomer(id,cnic,"Jan",2024))
            {
                break;
            }
            System.out.println("Invalid ID or CNIC");
        }
        String newDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate issueDate = LocalDate.parse(nadraInfo[1],formatter);
        while(true)
        {
            System.out.print("Enter New Expiry Date (dd/MM/yyyy): ");
            newDate = scanner.nextLine();
            if(newDate.equals("00"))
            {
                return false;
            }
            try{
                LocalDate date = LocalDate.parse(newDate,formatter);
                if (date.isBefore(issueDate)) {
                    System.out.println("Error: New Expiry Date cannot be before the Issue Date: " + nadraInfo[1]);
                }
                else
                {
                    break;
                }
            }catch(DateTimeParseException e)
            {
                System.out.println("Invalid Date : Try Again");
            }
        }

        ArrayList<String> array = new ArrayList<>();
        String line;
        String[] data;
        try(BufferedReader br = new BufferedReader(new FileReader("NADRADBfile.txt"))){
            while((line= br.readLine())!=null){
                data = line.split(",");
                if(data[0].equals(cnic))
                {
                    array.add(data[0]+","+data[1]+","+newDate);
                }
                else{
                    array.add(line);
                }
            }
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("NADRADBfile.txt"))){
            String line2;
            for(int i=0;i<array.size();i++)
            {
                bw.write(array.get(i));
                bw.newLine();
            }
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }
    public void viewExpireCnic()
    {
        LocalDate today = LocalDate.now();
        LocalDate expiry;
        long daysInBetween;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ArrayList<String> list = new ArrayList<>();

        String line;
        String[] data;
        try(BufferedReader br = new BufferedReader(new FileReader("NADRADBfile.txt"))){
            while((line=br.readLine())!=null){
                data = line.split(",");
                expiry = LocalDate.parse(data[2],formatter);

                daysInBetween = ChronoUnit.DAYS.between(today,expiry);
                if(daysInBetween<=30 && daysInBetween>0)
                {
                    list.add(data[0]+"          "+data[2]);
                }
            }
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nTotal CNIC's Expiring in 30 Days: " + list.size() + "\n");
        System.out.println("    CNIC\t\t\t   Expiry Date");
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }
    }

    public int cnic_count(String cnic)
    {
        String line="";
        int count=0;
        String[] data;
        try(BufferedReader br = new BufferedReader(new FileReader(custFilename))){
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
                    nadraInfo=data;
                    return true;
                }
            }
        }catch (IOException e) {
            System.out.println("Error while reading : " + e.getMessage());
        }
        return false;
    }

    public void viewBill()
    {
        String customerType="";
        if(custInfo[5].equals("d") || custInfo[5].equals("D"))
        {
            customerType="Domestic";
        }
        else if(custInfo[5].equals("c") || custInfo[5].equals("C"))
        {
            customerType="Commercial";
        }

        String meterType="";
        if(custInfo[6].equals("s") || custInfo[6].equals("S"))
        {
            meterType="Single Phase";
        }
        else if(custInfo[6].equals("t") || custInfo[6].equals("T"))
        {
            meterType="Three Phase";
        }

        float due=0;
        if(!billInfo[10].equals("Paid") && !billInfo[10].equals("Not Found"))
        {
            due = Float.parseFloat(billInfo[8]);
        }

        System.out.println("========================================");
        System.out.println("               LESCO BILL                ");
        System.out.println("========================================");
        System.out.println("Customer ID       : " + custInfo[0]);
        System.out.println("Customer Name     : " + custInfo[2]);
        System.out.println("CNIC Number       : " + custInfo[1]);
        System.out.println("Address           : " + custInfo[3]);
        System.out.println("Phone Number      : " + custInfo[4]);
        System.out.println("Customer Type     : " + customerType);
        System.out.println("Meter Type        : " + meterType);
        System.out.println("----------------------------------------");
        System.out.println("Regular Unit Price  : Rs. " + tariffInfo[1]);
        System.out.println("Peak Hour Unit Price: Rs. " + tariffInfo[2]);
        System.out.println("Percentage Of Tax   : Rs. " + tariffInfo[3]);
        System.out.println("----------------------------------------");
        System.out.println("Bill Month : " + billInfo[1]);
        System.out.println("Current Meter Reading Regular : " + billInfo[2] + " units");
        System.out.println("Current Meter Reading Peak    : " + billInfo[3] + " units");
        System.out.println("----------------------------------------");
        System.out.println("Cost of Electricity : Rs. " + billInfo[5]);
        System.out.println("Sales Tax Amount    : Rs. " + billInfo[6]);
        System.out.println("Fixed Charges       : Rs. " + tariffInfo[4]);
        System.out.println("----------------------------------------");
        System.out.println("Total Amount Due    : Rs. " + due);
        System.out.println("Due Date            : " + billInfo[9]);
        System.out.println("Payment Status      : " + billInfo[10]);
    }
    public void getTaxData(String custType, String phase)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(tariffFilename))){

            String line1 = br.readLine();
            String line2 = br.readLine();
            String line3 = br.readLine();
            String line4 = br.readLine();

            if((custType.equals("D") || custType.equals("d")) && (phase.equals("s") || phase.equals("S")))
            {
                tariffInfo = line1.split(",");
            }
            else if((custType.equals("c") || custType.equals("C")) && (phase.equals("s") || phase.equals("S")))
            {
                tariffInfo = line2.split(",");
            }
            else if((custType.equals("d") || custType.equals("D")) && (phase.equals("t") || phase.equals("T")))
            {
                tariffInfo = line3.split(",");
            }
            else if((custType.equals("c") || custType.equals("C")) && (phase.equals("t") || phase.equals("T")))
            {
                tariffInfo = line4.split(",");
            }
        }catch (IOException e)
        {
            System.out.println("Error Reading Tax File");
        }
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
            FileReader fr = new FileReader(custFilename);
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

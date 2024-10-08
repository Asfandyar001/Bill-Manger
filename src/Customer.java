import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Customer
{
    private String custFilename = "CustomerInfo.txt";
    private String billFilename = "BillingInfo.txt";
    private String tariffFilename = "TariffTaxInfo.txt";
    private String[] custInfo;
    private String[] billInfo = new String[]{"Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found"};
    private String[] tariffInfo;
    private String[] nadraInfo;
    private String userName;

    public boolean isCustomerValid(String id, String cnic)
    {
        boolean valid = false;
        try(BufferedReader br = new BufferedReader(new FileReader(custFilename))){
            String line;
            while ((line=br.readLine())!=null){
                String[] data = line.split(",");
                if(data[0].equals(id) && data[1].equals(cnic)){
                    valid=true;
                    userName = data[2];
                    break;
                }
            }
        }catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        return valid;
    }

    public String getUserName()
    {
        return userName;
    }

    public void CustInMenu(frame f, String name, Cust_Bill_Not_Found noBill)
    {
        Customer c = new Customer();
        Cust_Bill_Found yesBill = new Cust_Bill_Found("","");
        Cust_CNIC_Not_Updated noCNICupdate = new Cust_CNIC_Not_Updated(name);
        Cust_CNIC_Updated yesCNICupdated = new Cust_CNIC_Updated(name);

        //----------------------Bill Not Found Screen Settings---------------//

        noBill.getLogoutButton().addActionListener(eListen->{
            f.destroy();
            GUI_Manager g = new GUI_Manager();
        });
        noBill.getSearchButton().addActionListener(eListen->{

            if(!isDigits(noBill.getYear())){
                JOptionPane.showMessageDialog(null,"Invalid Inputs", "Error",JOptionPane.ERROR_MESSAGE);
            }
            else if (validateCustomer(noBill.getID(), noBill.getCNIC(),noBill.getMonth(), Integer.parseInt(noBill.getYear()))) {
                yesBill.clearData();
                ArrayList<String> list = viewBill();
                yesBill.setNameStatus(name,list.get(18));
                yesBill.setData(list);
                f.replacePanel(noBill,yesBill);
                yesBill.revalidate();
                yesBill.repaint();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Incorrect ID or CNIC", "Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        noBill.getUpdateCNICButton().addActionListener(eListen->{
            f.replacePanel(noBill,noCNICupdate);
        });

        //----------------------Bill Found Screen Settings---------------//

        yesBill.getGobackButton().addActionListener(eListen3->{
            f.replacePanel(yesBill,noBill);
        });
        yesBill.getLogoutButton().addActionListener(eListen2->{
            f.destroy();
            GUI_Manager g = new GUI_Manager();
        });
        yesBill.getUpdateCNICButton().addActionListener(eListen1->{
            f.replacePanel(yesBill,noCNICupdate);
        });

        //----------------------Cnic Not Updated Screen Settings---------------//

        noCNICupdate.getUpdateButton().addActionListener(eListen2->{
            if(c.updateCNIC(noCNICupdate.getID(),noCNICupdate.getCNIC(),noCNICupdate.getMonth()))
            {
                f.replacePanel(noCNICupdate,yesCNICupdated);
            }
        });
        noCNICupdate.getLogoutButton().addActionListener(eListen3->{
            f.destroy();
            GUI_Manager g = new GUI_Manager();
        });
        noCNICupdate.getViewBillButton().addActionListener(eListen4->{
            f.replacePanel(noCNICupdate,noBill);
        });

        //----------------------Cnic Updated Screen Settings---------------//

        yesCNICupdated.getUpdateButton().addActionListener(eListen3 ->{
            if(c.updateCNIC(yesCNICupdated.getID(), yesCNICupdated.getCNIC(), yesCNICupdated.getMonth()))
            {
            }
            else{
                f.replacePanel(yesCNICupdated,noCNICupdate);
            }
        });
        yesCNICupdated.getLogoutButton().addActionListener(eListen4->{
            f.destroy();
            GUI_Manager g = new GUI_Manager();
        });
        yesCNICupdated.getViewBillButton().addActionListener(eListen5->{
            f.replacePanel(yesCNICupdated,noBill);
        });
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
        billInfo = new String[]{"Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found"};
        if(valid)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
                }
            }catch (IOException e){
                System.out.println("Error:" + e.getMessage());
            }
            return true;
        }

        return false;
    }

    public boolean updateCNIC(String id, String cnic, String newDate)
    {
            if(isDigits(id) && isDigits(cnic) && searchNadraFile(cnic) && isCustomerValid(id,cnic))
            {
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid ID or CNIC","Error",JOptionPane.ERROR_MESSAGE);
            return false;
            }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate issueDate = LocalDate.parse(nadraInfo[1],formatter);
            if(newDate.equals("00"))
            {
                return false;
            }
            try{
                LocalDate date = LocalDate.parse(newDate,formatter);
                if (date.isBefore(issueDate)) {
                    JOptionPane.showMessageDialog(null,"Error: New Expiry Date cannot be before the Issue Date: " + nadraInfo[1],"Error",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }catch(DateTimeParseException e)
            {
                JOptionPane.showMessageDialog(null,"Invalid Date : Try Again","Error",JOptionPane.ERROR_MESSAGE);
                return false;
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
    public ArrayList<String> viewExpireCnic()
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
                    list.add(data[0] + "," + data[2]);
                }
            }
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return list;
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

    public ArrayList<String> viewBill()
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

        ArrayList<String> list = new ArrayList<>();
        list.add(custInfo[0]);
        list.add(custInfo[2]);
        list.add(custInfo[1]);
        list.add(custInfo[3]);
        list.add(custInfo[4]);
        list.add(customerType);
        list.add(meterType);
        list.add(billInfo[5]);
        list.add(billInfo[6]);
        list.add(tariffInfo[4]);
        list.add(billInfo[1]);
        list.add(billInfo[2] + " units");
        list.add(billInfo[3] + " units");
        list.add(tariffInfo[1]);
        list.add(tariffInfo[2]);
        list.add(tariffInfo[3]);
        list.add(String.valueOf(due));
        list.add(billInfo[9]);
        list.add(billInfo[10]);

        return list;
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

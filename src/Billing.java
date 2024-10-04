import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Billing
{
    private String billFilename = "BillingInfo.txt";
    private String custFilename = "CustomerInfo.txt";
    private String taxFilename = "TariffTaxInfo.txt";
    private String[] arrayList;
    private String[] billList;

    public boolean addNewBill()
    {
        Scanner scanner = new Scanner(System.in);

        String custID;
        String billingMonth;
        String currentMeterReading;
        String currentMeterReadingPeak = "not_supported";
        String paidStatus = "UnPaid";
        String paymentDate = "Not Paid";

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String readingEntryDate = currentDate.format(formatter);

        LocalDate reading = LocalDate.parse(readingEntryDate,formatter);
        LocalDate due = reading.plusDays(7);
        String dueDate = due.format(formatter);


        while(true)
        {
            System.out.print("Enter Customer ID: ");
            custID = scanner.nextLine();
            if(custID.equals("00"))
            {
                return false;
            }
            if(validateCustomerID(custID))
            {
                break;
            }
            System.out.println("Customer ID Invalid: Try Again");
        }

        while(true)
        {
            System.out.print("Enter Billing Month: ");
            billingMonth = scanner.nextLine();

            if(billingMonth.equals("00"))
            {
                return false;
            }
            if(billingMonth.equals("Jan") || billingMonth.equals("Feb") || billingMonth.equals("Mar") || billingMonth.equals("April") || billingMonth.equals("May") || billingMonth.equals("June") || billingMonth.equals("July") || billingMonth.equals("August") || billingMonth.equals("Sept") || billingMonth.equals("Oct") || billingMonth.equals("Nov") || billingMonth.equals("Dec"))
            {
                break;
            }
            System.out.println("Incorrect Month: Try Again");
        }

        String line;
        String[] data;
        LocalDate now = LocalDate.parse(readingEntryDate,formatter);
        int current_year = now.getYear();

        try(BufferedReader br = new BufferedReader(new FileReader(billFilename)))
        {
            while((line=br.readLine())!=null)
            {
                data = line.split(",");
                if(data[0].equals(custID) && data[1].equals(billingMonth))
                {
                    LocalDate fileDate = LocalDate.parse(data[4],formatter);
                    int billYear = fileDate.getYear();
                    if(billYear == current_year) {
                        System.out.println("\nThe Bill For the Month Issued Already");
                        return false;
                    }
                }
            }
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        while(true){
            System.out.print("Enter Current Meter Reading: ");
            currentMeterReading = scanner.nextLine();
            if(currentMeterReading.equals("00"))
            {
                return false;
            }
            if(!currentMeterReading.equals("0")  && isDigits(currentMeterReading))
            {
                break;
            }
            System.out.println("Invalid Current Meter Readings: Try Again");
        }

        String meter = arrayList[6];

        if(meter.equals("T") || meter.equals("t"))
        {
            while(true)
            {
                System.out.print("Enter Current Meter Reading Peak: ");
                currentMeterReadingPeak = scanner.nextLine();
                if(currentMeterReadingPeak.equals("00"))
                {
                    return false;
                }
                if(!currentMeterReadingPeak.equals("0")  && isDigits(currentMeterReadingPeak))
                {
                    break;
                }
                System.out.println("Invalid Current Meter Peak Readings: Try Again");
            }
        }

        String[] tax = getTaxData(arrayList[5], arrayList[6]);

        float costOfElectricity = 0;
        if(meter.equals("s") || meter.equals("S"))
        {
            costOfElectricity = Float.parseFloat(currentMeterReading) - Float.parseFloat(arrayList[8]);
            costOfElectricity = costOfElectricity * Float.parseFloat(tax[1]);
        }
        else if(meter.equals("t") || meter.equals("T"))
        {
            float regular = (Float.parseFloat(currentMeterReading) - Float.parseFloat(arrayList[8])) * Float.parseFloat(tax[1]);
            float peak = (Float.parseFloat(currentMeterReadingPeak) - Float.parseFloat(arrayList[9])) * Float.parseFloat(tax[2]);
            costOfElectricity = regular + peak;
        }

        float salesTaxAmount = costOfElectricity * (Float.parseFloat(tax[3])/100);
        float fixedCharges = Float.parseFloat(tax[4]);
        float totalBillingAmount = costOfElectricity + salesTaxAmount + fixedCharges;

        String billData = custID + "," + billingMonth + "," + currentMeterReading + "," + currentMeterReadingPeak + "," + readingEntryDate + "," + costOfElectricity + "," + salesTaxAmount + "," + fixedCharges + "," + totalBillingAmount + "," + dueDate + "," + paidStatus + "," + paymentDate;
        appendFile(billFilename,billData);
        return true;
    }

    public boolean changePaidStatus(Emp_Change_Bill_Status changeStatus)
    {
        String custID = changeStatus.getCustID();
        String billingMonth = changeStatus.getBillingMonth();

        if(billingMonth.equals("Jan") || billingMonth.equals("Feb") || billingMonth.equals("Mar") || billingMonth.equals("April") || billingMonth.equals("May") || billingMonth.equals("June") || billingMonth.equals("July") || billingMonth.equals("August") || billingMonth.equals("Sept") || billingMonth.equals("Oct") || billingMonth.equals("Nov") || billingMonth.equals("Dec"))
        {
        }
        else{
            JOptionPane.showMessageDialog(null,"Incorrect Month","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String entryDate = changeStatus.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{
            LocalDate date = LocalDate.parse(entryDate,formatter);
        }catch(DateTimeParseException e)
        {
            JOptionPane.showMessageDialog(null,"Invalid Date: dd/MM/yyyy","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if(validateCustomerID(custID))
        {

        }
        else{
            JOptionPane.showMessageDialog(null,"Customer ID Invalid","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String readingEntryDate="";
        String RUC="";
        String PHUC="";
        String status="";

        String line;
        String[] data;
        boolean found = false;
        try(BufferedReader br = new BufferedReader(new FileReader(billFilename))){
            while((line = br.readLine())!=null)
            {
                data = line.split(",");
                if(data[0].equals(custID) && data[1].equals(billingMonth) && data[4].equals(entryDate))
                {
                    readingEntryDate = data[4];
                    RUC = data[2];
                    PHUC = data[3];
                    status=data[10];
                    found = true;
                }
            }
        }catch (IOException e){
            System.out.println("Error Reading File: " + e.getMessage());
        }

        if(!found)
        {
            JOptionPane.showMessageDialog(null,"No Such Bill Found","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(status.equals("Paid"))
        {
            JOptionPane.showMessageDialog(null,"The Status was Already Updated to Paid","Error",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        String paymentDate= changeStatus.getReceivedDate();
        LocalDate readingDate = LocalDate.parse(readingEntryDate,formatter);

        try{
            LocalDate date = LocalDate.parse(paymentDate,formatter);
            if(date.isBefore(readingDate))
            {
                JOptionPane.showMessageDialog(null,"Payment Date is before Reading Date","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }catch(DateTimeParseException e)
        {
            JOptionPane.showMessageDialog(null,"Invalid Received Date : dd/MM/yyyy","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        ArrayList<String> array = new ArrayList<>();
        try {
            FileReader fr = new FileReader(billFilename);
            BufferedReader br = new BufferedReader(fr);

            String line2;

            while ((line2 = br.readLine()) != null) {
                String[] getLine = line2.split(",");
                if (getLine[0].equals(custID) && getLine[1].equals(billingMonth) && getLine[4].equals(entryDate)) {
                    getLine[11] = paymentDate;
                    array.add(getLine[0] + "," + getLine[1] + "," + getLine[2] + "," + getLine[3] + "," + getLine[4] + "," + getLine[5] + "," + getLine[6] + "," + getLine[7] + "," + getLine[8] + "," + getLine[9] + "," + "Paid" + "," + getLine[11]);
                } else {
                    array.add(line2);
                }
            }
            fr.close();
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: File Reading: " + e.getMessage());
        }

        writeFile(array,billFilename);

        updateCustomerFile(custID,RUC,PHUC);
        return true;
    }

    public void updateCustomerFile(String custID, String RUC, String PHUC)
    {
        ArrayList<String> array = new ArrayList<>();
        try {
            FileReader fr = new FileReader(custFilename);
            BufferedReader br = new BufferedReader(fr);

            String line2;

            while ((line2 = br.readLine()) != null) {
                String[] getLine = line2.split(",");
                if (getLine[0].equals(custID)) {
                    getLine[8] = RUC;
                    getLine[9] = PHUC;
                    array.add(getLine[0] + "," + getLine[1] + "," + getLine[2] + "," + getLine[3] + "," + getLine[4] + "," + getLine[5] + "," + getLine[6] + "," + getLine[7] + "," + getLine[8] + "," + getLine[9]);
                } else {
                    array.add(line2);
                }
            }
            fr.close();
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: File Reading: " + e.getMessage());
        }

        writeFile(array,custFilename);
    }

    public void writeFile(ArrayList<String> array, String filename)
    {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < array.size(); i++) {
                bw.write(array.get(i));
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("Error: File Writing");
        }
    }

    public void appendFile(String filename,String data)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true))){
            bw.write(data);
            bw.newLine();
        }catch(IOException e)
        {
            System.out.println("Error Writing to file: " + e.getMessage());
        }
    }

    public String[] getTaxData(String custType, String phase)
    {
        String[] data = new String[]{""};
        try(BufferedReader br = new BufferedReader(new FileReader(taxFilename))){

            String line1 = br.readLine();
            String line2 = br.readLine();
            String line3 = br.readLine();
            String line4 = br.readLine();

            if((custType.equals("D") || custType.equals("d")) && (phase.equals("s") || phase.equals("S")))
            {
                data = line1.split(",");
            }
            else if((custType.equals("c") || custType.equals("C")) && (phase.equals("s") || phase.equals("S")))
            {
                data = line2.split(",");
            }
            else if((custType.equals("d") || custType.equals("D")) && (phase.equals("t") || phase.equals("T")))
            {
                data = line3.split(",");
            }
            else if((custType.equals("c") || custType.equals("C")) && (phase.equals("t") || phase.equals("T")))
            {
                data = line4.split(",");
            }
        }catch (IOException e)
        {
            System.out.println("Error Reading Tax File");
        }
        return data;
    }

    public boolean validateCustomerID(String id)
    {
        try {
            FileReader fr = new FileReader(custFilename);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] index = line.split(",");
                if (index[0].equals(id)) {
                    arrayList = index;
                    return true;
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Error File Reading: " + e.getMessage());
        }

        return false;
    }

    public boolean validateCustomerIDfromBillFile(String id,String month,String date)
    {
        try {
            FileReader fr = new FileReader(billFilename);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] index = line.split(",");
                if (index[0].equals(id) && index[1].equals(month) && index[4].equals(date)) {
                    billList = index;
                    return true;
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Error File Reading: " + e.getMessage());
        }

        return false;
    }

    public boolean viewBill(frame f, Emp_ViewBill_NoBill noBill)
    {
        String custID = noBill.getCustID();
        String billingMonth = noBill.getBillingMonth();

            if(billingMonth.equals("Jan") || billingMonth.equals("Feb") || billingMonth.equals("Mar") || billingMonth.equals("April") || billingMonth.equals("May") || billingMonth.equals("June") || billingMonth.equals("July") || billingMonth.equals("August") || billingMonth.equals("Sept") || billingMonth.equals("Oct") || billingMonth.equals("Nov") || billingMonth.equals("Dec"))
            {
            }
            else{
                JOptionPane.showMessageDialog(null,"Incorrect Billing Month","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }

        String entryDate = noBill.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{
            LocalDate date = LocalDate.parse(entryDate,formatter);
        }catch(DateTimeParseException e) {
            JOptionPane.showMessageDialog(null,"Invalid Date : dd/MM/yyyy","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(custID.isEmpty() || custID.equals("Type Customer ID")){
            JOptionPane.showMessageDialog(null,"No Such Bill Found","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(validateCustomerIDfromBillFile(custID,billingMonth,entryDate))
        {}
        else{
            JOptionPane.showMessageDialog(null,"No Such Bill Found","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public String[] getBillList(){
        return billList;
    }

    public void viewReport(Emp_View_Report viewReport)
    {
        float sum_paid=0;
        float sum_unpaid=0;
        String line;
        String[] data;

        try(BufferedReader br = new BufferedReader(new FileReader(billFilename)))
        {
            while ((line=br.readLine())!=null)
            {
                data=line.split(",");
                if(data[10].equals("Paid"))
                {
                    sum_paid = sum_paid + Float.parseFloat(data[8]);
                }
                else if(data[10].equals("UnPaid"))
                {
                    sum_unpaid = sum_unpaid + Float.parseFloat(data[8]);
                }
            }
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        viewReport.setValues(String.valueOf(sum_paid), String.valueOf(sum_unpaid));
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

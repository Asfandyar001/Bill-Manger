import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaxManager {
    private String filename = "TariffTaxInfo.txt";

    public ArrayList<String> getData(){
        ArrayList<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line=br.readLine())!=null){
                list.add(line);
            }
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    public boolean updateTaxMenu(int lineCount, Emp_TaxesInfo taxesInfo)
    {
        if(lineCount==1 && isDigits(taxesInfo.getR1().getText()) && isDigits(taxesInfo.getT1().getText()) && isDigits(taxesInfo.getFC1().getText())){
            String newline = "1Phase," + taxesInfo.getR1().getText() + ",," + taxesInfo.getT1().getText() + "," + taxesInfo.getFC1().getText();
            performTaxChanges(1,newline);
        }
        else if(lineCount==2 && isDigits(taxesInfo.getR2().getText()) && isDigits(taxesInfo.getT2().getText()) && isDigits(taxesInfo.getFC2().getText())){
            String newline = "1Phase," + taxesInfo.getR2().getText() + ",," + taxesInfo.getT2().getText() + "," + taxesInfo.getFC2().getText();
            performTaxChanges(2,newline);
        }
        else if(lineCount==3 && isDigits(taxesInfo.getR3().getText()) && isDigits(taxesInfo.getP3().getText()) && isDigits(taxesInfo.getT3().getText()) && isDigits(taxesInfo.getFC3().getText())){
            String newline = "3Phase," + taxesInfo.getR3().getText() + "," + taxesInfo.getP3().getText() + "," + taxesInfo.getT3().getText() + "," + taxesInfo.getFC3().getText();
            performTaxChanges(3,newline);
        }
        else if(lineCount==4 && isDigits(taxesInfo.getR4().getText()) && isDigits(taxesInfo.getP4().getText()) && isDigits(taxesInfo.getT4().getText()) && isDigits(taxesInfo.getFC4().getText())){
            String newline = "3Phase," + taxesInfo.getR4().getText() + "," + taxesInfo.getP4().getText() + "," + taxesInfo.getT4().getText() + "," + taxesInfo.getFC4().getText();
            performTaxChanges(4,newline);
        }
        else{
            JOptionPane.showMessageDialog(null,"Invalid Values Entered: Unable to Update","Error",JOptionPane.ERROR_MESSAGE);
            taxesInfo.removeVaalues();
            taxesInfo.setValues(getData());
            return false;
        }
        return true;
    }

    public void performTaxChanges(int row, String value)
    {
        ArrayList<String> list = getData();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            for(int i=0; i< list.size(); i++) {
                if (i+1 == row) {
                    bw.write(value);
                    bw.newLine();
                } else {
                    bw.write(list.get(i));
                    bw.newLine();
                }
            }
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean isDigits(String str)
    {
        if(str.isBlank() || str.isEmpty()){
            return false;
        }
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

import Model.entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Product> list = new ArrayList<>();

        System.out.println("Enter file path: ");
        String pathSTR = sc.nextLine();

        File file =  new File(pathSTR);
        String path = file.getParent();

        boolean success = new File(path + "\\out").mkdir();

        String fileStr = path + "\\out\\sumarry.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(pathSTR)) ){
            String itemCsv = br.readLine();
            while(itemCsv != null){
                String[] fields = itemCsv.split(",");
                String name = fields[0];
                double price = Double.parseDouble(fields[1]);
                int quantity = Integer.parseInt(fields[2]);

                list.add(new Product(name,price,quantity));

                itemCsv = br.readLine();
            }

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileStr))){
                for(Product item : list){
                    bw.write(item.getName() + "," + String.format("%.2f",item.total()));
                    bw.newLine();
                }
                System.out.println(fileStr + " CREATED");
            }
            catch(IOException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
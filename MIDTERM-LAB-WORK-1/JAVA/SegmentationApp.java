import java.io.*;
import java.util.*;

// Modular Design: Separate class for data
class Customer {
    String name;
    double revenue;

    public Customer(String name, double revenue) {
        this.name = name;
        this.revenue = revenue;
    }
}

public class SegmentationApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file;
        String filePath;

        // 1. Validation Loop
        while (true) {
            System.out.print("Enter dataset file path: ");
            filePath = sc.nextLine();
            file = new File(filePath);

            if (file.exists() && file.canRead() && filePath.endsWith(".csv")) {
                break;
            } else {
                System.out.println("Error: Invalid file. Please ensure the path is correct and it's a CSV.");
            }
        }

        // 2. Data Processing
        List<Customer> platinum = new ArrayList<>();
        List<Customer> gold = new ArrayList<>();
        List<Customer> silver = new ArrayList<>();
        List<Customer> bronze = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                double revenue = Double.parseDouble(values[1]);
                
                Customer c = new Customer(name, revenue);

                if (revenue > 100000) platinum.add(c);
                else if (revenue >= 50000) gold.add(c);
                else if (revenue >= 10000) silver.add(c);
                else bronze.add(c);
            }

            // 3. Output
            displaySegment("Platinum", platinum);
            displaySegment("Gold", gold);
            displaySegment("Silver", silver);
            displaySegment("Bronze", bronze);

        } catch (Exception e) {
            System.out.println("Error processing data: " + e.getMessage());
        }
    }

    private static void displaySegment(String label, List<Customer> list) {
        System.out.println("\n--- " + label + " (" + list.size() + " customers) ---");
        for (Customer c : list) System.out.println("- " + c.name);
    }
}
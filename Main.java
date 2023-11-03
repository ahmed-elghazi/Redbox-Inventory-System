import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    
    // Builds a binary tree inventory of DVDs from a given file
    public static BinTree<DVD> buildInventory(String inv){
        // Create an empty binary tree for DVDs
        BinTree<DVD> A = new BinTree<>();
        try {
            // Open the inventory file for reading
            Scanner in = new Scanner(new File(inv));
            String line;
            int firstComa, secondComa;
            // An array to parse the DVD information from the line
            String[] Parse = new String[3];
            // Loop through each line of the file
            while(in.hasNextLine()){
                line = in.nextLine(); // Read the line
                // Find the positions of the first two commas for parsing
                firstComa = line.indexOf(",");
                secondComa = line.indexOf(",", firstComa + 1);
                // Parse the title, available count, and rented count from the line
                Parse[0] = line.substring(1, firstComa - 1);
                Parse[1] = line.substring(firstComa + 1, secondComa);
                Parse[2] = line.substring(secondComa + 1);
                // Insert the new DVD into the binary tree
                A.insert(new DVD(Parse[0], Integer.parseInt(Parse[1]), Integer.parseInt(Parse[2])));
            }
        }
        catch(FileNotFoundException B){
            // Inform the user if the file does not exist
            System.out.println("file no exist lol");
        }
        return A; // Return the populated binary tree
    }

    // Processes a transaction log against the binary tree of DVDs
    public static void transLog(String trans, BinTree<DVD> A){
        try{
            // Open the transaction log file for reading
            Scanner in = new Scanner(new File(trans));
            String line;
            int firstComa, secondComa;
            // An array to parse the transaction information
            String[] Parse = new String[3];
            // Loop through each line of the file
            while(in.hasNextLine()){
                line = in.nextLine();
                // Find the positions of the first space and second quotation mark for parsing
                firstComa = line.indexOf(" ");
                secondComa = line.indexOf("\"", firstComa + 2);
                // Parse the transaction type and the DVD title
                Parse[0] = line.substring(0,firstComa);
                Parse[1] = line.substring(firstComa + 2, secondComa);
                // Search for the DVD object in the tree
                DVD FoundObj = A.search(new DVD(Parse[1]));
                
                // Handle rental and return transactions
                if (Parse[0].equals("rent") || Parse[0].equals("return")) {
                    if(Parse[0].equals("rent")){
                        FoundObj.rent(); // Rent the DVD
                    }
                    else{
                        FoundObj.Ree(); // Presumably this should be a return method
                    }
                }
                // Handle add and remove transactions
                else{
                    Parse[2] = line.substring(secondComa + 2);
                    if(Parse[0].equals("add")){
                        if(FoundObj!=null){
                            FoundObj.addCopies(Integer.parseInt(Parse[2])); // Add copies if DVD exists
                        }
                        else{
                            A.insert(new DVD(Parse[1], Integer.parseInt(Parse[2]), 0)); // Insert new DVD if it does not exist
                        }
                    }
                    if(Parse[0].equals("remove")){
                        FoundObj.removeCopies(Integer.parseInt(Parse[2])); // Remove copies of the DVD
                        // Remove the DVD from the tree if there are no available or rented copies left
                        if(FoundObj.getA() <=  0 && FoundObj.getR() <= 0){
                            A.remove(FoundObj);
                        }
                    }
                }
            }
        }
        catch(FileNotFoundException C){
            // Inform the user if the transaction log file does not exist
            System.out.println("file no exist lol");
        }
    }

    // The main method to run the program
    public static void main(String[] args) {
        // Variables to hold file names for inventory and transactions
        String inv, trans;
        // Scanner to read input from the user
        Scanner in = new Scanner(System.in);
        // Read the inventory file name
        inv = in.nextLine();
        // Read the transaction log file name
        trans = in.nextLine();
        // Build the DVD inventory tree
        BinTree<DVD> A = buildInventory(inv);
        // Process the transaction log
        transLog(trans, A);
        // Print out the inventory report header
        System.out.printf("%-30s %-15s %-15s%n", "Title", "Available", "Rented");
        System.out.println("------------------------------------------------------------");
        // Print out the inventory report
        A.displayReport();
    }
}

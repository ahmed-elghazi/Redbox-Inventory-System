public class DVD implements Comparable<DVD> {
    // Class fields for title, number of available copies, and number of rented copies
    private String title;
    private int avail;
    private int rented;

    // Default constructor setting all fields to default values
    DVD(){
        title = "";
        avail = 0;
        rented = 0;
    }
    
    // Constructor with parameters to set title, available copies, and rented copies
    DVD(String T, int A, int R){
        title = T;
        avail = A;
        rented = R;
    }

    // Constructor with only title parameter, sets available and rented copies to 0
    DVD(String T){
        title = T;
        avail = 0;
        rented = 0;
    }

    // Getter method for title
    String getT(){
        return title;
    }

    // Getter method for available copies
    int getA(){
        return avail;
    }

    // Getter method for rented copies
    int getR(){
        return rented;
    }

    // Method to simulate renting a DVD, decreases available and increases rented
    void rent(){
        if (avail > 0) { // Check to ensure availability before renting
            rented++;
            avail--;
        }
    }

    // Method to simulate returning a DVD, increases available and decreases rented
    void Ree(){ // The method name should be more descriptive like 'returnDVD'
        if (rented > 0) { // Check to ensure there's a rented copy before returning
            rented--;
            avail++;
        }
    }

    // Method to add additional copies to the available count
    void addCopies(int A) {
        if (A > 0) { // Check to ensure a positive number of copies is added
            avail += A;
        }
    }

    // Method to remove copies from the available count
    void removeCopies(int A) {
        if (A > 0 && avail >= A) { // Check to ensure a positive number and enough available copies to remove
            avail -= A;
        }
    }

    // Overridden toString method to provide a formatted string representation of the DVD
    @Override
    public String toString(){
        return String.format("%-30s %-15d %-15d", title, avail, rented);
    }

    // Overridden compareTo method to compare DVDs based on their title for sorting
    @Override
    public int compareTo(DVD o) {
        return title.compareTo(o.getT());
    }
}

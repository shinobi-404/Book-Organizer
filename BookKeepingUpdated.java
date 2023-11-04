
// imported liabaries 
import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;

public class BookKeeping1{

    public static void main(String[] args){

        // list of variables and scanners 
        BookList booklist = new BookList();
        Scanner myScan = new Scanner(System.in);
        String answer = "yes";
        
        System.out.println("Welcome to the book program!");

        while(true){

            System.out.println("Would you like to create a book object? (yes/no): "); // prints to user asking for name 
            answer = myScan.nextLine().toLowerCase(); //convets input to lower case
            
            if(answer.equals("yes")){
         
                System.out.println("Please enter the author, title and the isbn of the book separated by /: "); // prints to user asking for name 
                String bookinfo = myScan.nextLine();

                String bookInfoArray[] = bookinfo.split("/"); // splits input into different parts of the array
                
                // organizes strings into arrays 
                String author = bookInfoArray[0];
                String title = bookInfoArray[1];
                String isbn = bookInfoArray[2];

                System.out.println("Got it!");

                System.out.println("Now, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book):");// prompts user for input 
                String bookType = myScan.nextLine().toUpperCase(); // converts input to upper case

                // keepss asking user to input correct answer until it is correct
                while(!bookType.equals("BB") && !bookType.equals("LB")){ //works with && not ||
                
                    System.out.println("Oops! Thats not a valid entry. Please try again:");
                    bookType = myScan.nextLine().toUpperCase(); //converts input to upper case
                }
                
                // The Book Store 
                if(bookType.equals("BB")){

                    System.out.println("Please enter the list price of "+title+" by "+author+":");
                    double aprice = myScan.nextDouble();

                    myScan.nextLine(); //clears the \n in the scanner

                    System.out.println("Is it on sale? (y/n):");
                    String ifSale = myScan.nextLine();
                
                    boolean sale = false; // put boolean here for correft foramt 
                    double discount = 0; // put double here so it is a local variabl in correct format 
                    
                    // if the book is discouted 
                    if(ifSale.equals("y")){
                        
                        System.out.println("Deduction percentage: ");
                        String dis = myScan.nextLine();
                        
                        dis = dis.replaceAll("[^0-9]", "");
                        discount = Integer.parseInt(dis);

                        //myScan.nextLine();
                        sale = true;
                        System.out.println("Got it!");

                    }

                    System.out.println("Here is your bookstore information ");
                    BookstoreBook book = new BookstoreBook(author, title, isbn, aprice, sale, discount);
                    System.out.println(book);
                    booklist.addBook(book);

                }
                
                // Libary function
                else{

                   LibraryBook book = new LibraryBook(author, title, isbn);
                   System.out.println("Here is your library book information");
                   System.out.println(book);
                   booklist.addBook(book);

                }
            }
            else if(answer.equals("no")){
                break;
            }
            else{
                continue;
            }
            
       }

       // lists books
       System.out.println("Here are all your books...");
       booklist.printBooks();

       System.out.println("----");
       System.out.println("Take care now!");

       myScan.close();
       

    }
}

// used as a ref not as a class 
abstract class Book {  
    
    // variables 
   private String author;
   private String title;
   private String isbn;

    // setters and getters 
   public String getAuthor(){
       return author;   
   }
   public void setAuthor(String author){
       this.author = author;
   }
   public String getTitle(){
       return title;
   }
   public void setTitle(String title){
       this.title = title;
   }
   public String getISBN(){
       return isbn;
   }
   public void setISBN(String isbn){
       this.isbn = isbn;
   }
   // constructers 
   public Book (String author, String title, String isbn){
        
       super();
       this.author = author;
       this.title = title;
       this.isbn = isbn;
   }
   public Book(String title){
       this.author = "";
       this.title = title;
       this.isbn = "";
   }

   public Book(){

       this.author = "";
       this.title = "";
       this.isbn = "";
   }

   @Override
   public String toString() {
       
       //[458792132-JAVA MADE EASY by ERICKA JONES, $14.99 listed for $12.74]

       return "["+ isbn+"-"+title+" by "+author;
   }
}

// book store class 
class BookstoreBook extends Book{ //public static class didnt work 
    
    // variables 
    private double price;
    private double discount;
    private boolean sale;
    
    // constructors 
    public BookstoreBook(){
       this.price = 0;
       this.sale = false;
    }

    public BookstoreBook(String title){
       this.price = 0;
       this.sale = false;
    }


    public BookstoreBook (String author, String title, String isbn, double price, boolean sale, double discount){
        
       super(author,title,isbn);
       this.price = price;
       this.discount = discount;
       this.sale = sale;

   }
   
   // setters and getters 
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public double getDiscount(){
        return discount;
    }
    public void setDiscount(double discount){
        this.discount = discount;
    }
    public boolean isSale(){
        return sale;
    }
    public void setSale(Boolean sale){
        this.sale = sale;
    }
    
    
    // gets discounted price 
    public double getPriceAfterDiscount (){

        return price - (price * discount / 100);
        
    }
   
    public String toString(){
        
       DecimalFormat decimal = new DecimalFormat("00.00");// converts to a decimal format 
       return super.toString()+", $"+decimal.format(price)+" listed for $"+decimal.format(getPriceAfterDiscount())+"]";

    }

}


 // Liabary class
class LibraryBook extends Book{

    // variables
   private String callNumber;
   private static int numofBooks;
   private int floorNumber;


    // constructors 
    public LibraryBook (String author, String title, String isbn){
        
        super(author, title, isbn);
        floorNumber = randomNumGen();
        this.callNumber = floorNumber  +"."+author.substring(0,3)+"."+isbn.substring(isbn.length()-1);
        numofBooks++;
    }

    // setters and getters
    public String getcallNumber() {
       return callNumber;
    }

    public void setcallNumber(String callNumber) {
       this.callNumber = callNumber;
    }

    public static int getnumofbooks() {
       return numofBooks;
    }

    public static void setNumOfBooks(int numofBooks) {
       LibraryBook.numofBooks = numofBooks;
    }

    // generates random number for the floor of the libary book
    private int randomNumGen(){

        Random rand = new Random();

        int floorNumber = rand.nextInt(1,99);
        return floorNumber;


        // int floor = ((int)Math.random() * 99)+1;
        // return floor;

    //     int max = 99;
    //     int min = 1;
    //     int range = max - min + 1;
 
    //     // generate random numbers within 1 to 10
    //     for (int i = 0; i < 99; i++) {
    //         int rand = (int)(Math.random() * range) + min;
 
    //         // Output is different everytime this code is executed
    //         System.out.println(rand);
    //     }
    //    return rand;
    }
    
    public String toString(){
        
        //return "["+isbn+"-"+title+" by "+author+", "+randomNumGen()+"."+author.substring(0,3)+"."+isbn.substring(isbn.length()-1)+"]";
        //xx.yyy.c   xx = floor (99 floors) yyy= 1st 3 letters of authors lastname, c is the last character of isbn 

        return super.toString()+"-"+ callNumber+"]";
    }
   

    

}

// book list class 
class BookList{
   private Book[] list; 
   private int count = 0; 

   public BookList(){

       list = new Book[100];
   }

   public void addBook(Book abook){
       if (count<list.length){

           list[count] = abook;
           count++;
       }
   }

   public void printBooks(){

       int libCounter = 0;
       int bbCounter = 0; 

       for(int i = 0; i <count; i++){

           Book b = list[i];
           if(b instanceof LibraryBook){
               libCounter++;
           }
           else{
               bbCounter++;
           }
       }
       System.out.println("Library Books ("+libCounter+")");
       
       for(Book abook : list){

           if (abook instanceof LibraryBook){
               LibraryBook book = (LibraryBook) abook;
               System.out.println(book);
           }

       }
       System.out.println("----");
       System.out.println("Bookstore Books ("+bbCounter+")");
       for(Book abook: list)
       {
           if(abook instanceof BookstoreBook) {
               BookstoreBook book=(BookstoreBook) abook;
               System.out.println(book);
           }
       }
   }
}
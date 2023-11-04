


 // whats the difference between public and static?

 import java.util.Scanner;
 import java.util.Random;
 public class BookKeeper{
 
     public static void main(String[] args){
         
         Scanner myScan = new Scanner(System.in);
         String answer = "yes";
         int storeBooksNum = 0;
         int liabaryBooksNum = 0;
 
 
         // arraries for listign 
         BookstoreBook[] storeBooks = new BookstoreBook[100];
         LibraryBook[] libraryBooks = new LibraryBook[200];
 
 
         System.out.println("Welcome to the book program!");
 
 
         while(true){
             
 
             System.out.println("Would you like to create a book object? (yes/no): "); //prints to user asking for name 
             answer = myScan.nextLine().toLowerCase();
             
             if(answer.equals("yes")){
          
                 System.out.println("Please enter the author, title and the isbn of the book separated by /: "); //prints to user asking for name 
                 String bookinfo = myScan.nextLine();
 
                 String bookInfoArray[] = bookinfo.split("/");
             
                 String author = bookInfoArray[0];
                 String title = bookInfoArray[1];
                 String isbn = bookInfoArray[2];
 
                 System.out.println("Got it!");
 
                 System.out.println("Now, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book):");
                 String bookType = myScan.nextLine().toUpperCase();
 
                 while(!bookType.equals("BB") && !bookType.equals("LB")){ //works with && not ||
                 
                     System.out.println("Oops! Thats not a valid entry. Please try again:");
                     bookType = myScan.nextLine().toUpperCase();
                 }
 
                 if(bookType.equals("BB")){
 
                     System.out.println("Please enter the list price of "+title+" by "+author+":");
                     double aprice = myScan.nextDouble();
 
                     myScan.nextLine(); //clears the \n in the scanner
 
                     System.out.println("Is it on sale? (y/n):");
                     String ifSale = myScan.nextLine();
                 
                     boolean sale = false; // put boolean here for correft foramt 
                     double discount = 0; // put double here so it is a local variabl in correct format 
                 
                     if(ifSale.equals("y")){
 
                         System.out.println("Deduction percentage: ");
                         discount = myScan.nextDouble();
                         myScan.nextLine();
                         sale = true;
                         System.out.println("Got it!");
 
                     }
 
                     storeBooks[storeBooksNum] = new BookstoreBook(author, title, isbn, aprice, sale, discount);
 
                     // System.out.println(storeBooksNum);
                     System.out.println(storeBooks[storeBooksNum].toString());
                     storeBooksNum++;
 
                 }
 
                 else{
 
                     libraryBooks[liabaryBooksNum] = new LibraryBook(author, title, isbn);
 
                     System.out.println("Here is your library book information");
                     System.out.println(libraryBooks[liabaryBooksNum].toString());
                     liabaryBooksNum++;  
 
                 }
 
             }
             else if(answer.equals("no")){
                 System.out.println("test");
                 break;
             }
             else{
 
                 System.out.println("testing 1");
                 continue;
             }
             
        }
        
 
        System.out.println("GOODBYE!");
 
        // book store array would return here (i) tht stored in array 
       
        System.out.println("Here are all your books...");
 
        System.out.println("You have store book ("+storeBooksNum+")");
 
        for(int i = 0; i < storeBooksNum;i++){
 
         System.out.println(storeBooks[i].toString());
 
        }
 
        System.out.println("----");
        System.out.println("You have Liabary book ("+liabaryBooksNum+")");
 
        for(int i = 0; i < liabaryBooksNum; i++){
 
         System.out.println(libraryBooks[i].toString());
 
        }
 
        System.out.println("----");
        System.out.println("Take care now!");
 
        myScan.close();
        
 
     }
 }
 
 
 class BookstoreBook { //public static class didnt work 
     
     private String author;
     private String title;
     private String isbn;
     private double price;
     private double discount;
     private boolean sale;


     public BookstoreBook (String author, String title, String isbn, double price, boolean sale, double discount){
         
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.discount = discount;
        this.sale = sale;

    }
    public BookstoreBook (String author, String title, String isbn, double price){
       this(author, title, isbn, price, false, 0);
    }
 
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
     
     
 
     public double getPriceAfterDiscount (){
 
         return price - (price * discount / 100);
         
     }
 
     public String toString(){
         
        return "["+isbn+"-"+title+" by "+author+", "+price+" listed for $"+getPriceAfterDiscount()+"]";
     }
 
 
 
 // Liabary class
 }
 class LibraryBook{
 
     private String author;
     private String title;
     private String isbn;
 
 
     public LibraryBook (String author, String title, String isbn){
         
         this.author = author;
         this.title = title;
         this.isbn = isbn;
 
     }
     public LibraryBook(){
         author = "invalid";
     }
 
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
     
     public String toString(){
         
 
         return "["+isbn+"-"+title+" by "+author+", "+randomNumGen()+"."+author.substring(0,3)+"."+isbn.substring(isbn.length()-1)+"]";
         //xx.yyy.c   xx = floor (99 floors) yyy= 1st 3 letters of authors lastname, c is the last character of isbn 
     }
    
 
     
 
 }
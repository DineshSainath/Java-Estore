/*
 Dinesh Sainath Koti Reddy
 1025287
 */
package estore;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The eStoreSearch.java file consists of a eStoreSearch class, that has the search methods to perform the search function for various paremeters, and the main class that uses all the other classes in the 'estore' package to act like a miniature database that stores details of books and electronics, that also allows us to add and search within the existing records.
 *
 * @author Dinesh Sainath
 * @version 1.0
 */
public class eStoreSearch {                                // search class that contains search methods

    private static ArrayList<Book> books = new ArrayList<Book>();
    private static ArrayList<Electronic> electronics = new ArrayList<Electronic>();

    /**
     * Gets the ArrayList<Book> that has the records of the books
     * @return the ArrayList<Book>
     */
    public static ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Gets the ArrayList<Electronic> that has the records of the electronics
     * @return the ArrayList<Electronic>
     */
    public static ArrayList<Electronic> getElectronics() {
        return electronics;
    }

    /**
     * Performs search with respect to the product id of the book/electronic
     * @param productID - product ID
     * @return the record that satisfies the search
     */
    public static ArrayList<Product> search(int productID) {                //product ID as a parameter to search
        ArrayList<Product> ls = new ArrayList<Product>();
        for (Book book : eStoreSearch.books) {
            if (book.getProductID() == productID) {
                ls.add(book);
                return ls;
            }
        }
        for (Electronic electronic : eStoreSearch.electronics) {
            if (electronic.getProductID() == productID) {
                ls.add(electronic);
                return ls;
            }
        }
        return ls;
    }

    /**
     * Performs search with respect to the keywords of the book/electronic
     * 
     * @param keywordsForDesc
     * @return the record that satisfies the search
     */
    public static ArrayList<Product> search(String[] keywordsForDesc) {    // search using keywords
        ArrayList<Product> ls = new ArrayList<Product>();
        boolean keywordMatching;
        for (Book book : eStoreSearch.books) {
            keywordMatching = true;
            for (String keyword : keywordsForDesc) {
                if ((keyword != null) && !book.getDescription().contains(keyword)) {
                    keywordMatching = false;
                    break;
                }
            }
            if (keywordMatching) {
                ls.add(book);
            }
        }
        for (Electronic electronic : eStoreSearch.electronics) {
            keywordMatching = true;
            for (String keyword : keywordsForDesc) {
                if (!electronic.getDescription().contains(keyword)) {
                    keywordMatching = false;
                    break;
                }
            }
            if (keywordMatching) {
                ls.add(electronic);
            }
        }
        return ls;
    }

    /**
     * Performs search with respect to the time period of the book/electronic
     * 
     * @param timePeriod
     * @return the record that satisfies the search
     */
    public static ArrayList<Product> search(String timePeriod) {               // search using time period
        ArrayList<Product> pT = new ArrayList<Product>();
        int check = timePeriod.indexOf('-');
        if(check > -1) {
        ArrayList<Product> ls = new ArrayList<Product>();
        StringTokenizer strtok = new StringTokenizer(timePeriod, "-");
        int year1 = Integer.parseInt(strtok.nextToken()), year2 = year1;
        try {
            year2 = Integer.parseInt(strtok.nextToken());
        } catch (NoSuchElementException e) {
            if (timePeriod.charAt(timePeriod.length() - 1) == '-') {
                year2 = -1;
            } else if (timePeriod.charAt(0) == '-') {
                year2 = -2;
            }
        }
        for (Book book : eStoreSearch.books) {
            if ((year2 == -1 && book.getYear() >= year1)
                    || (year2 == -2 && book.getYear() <= year1)
                    || (book.getYear() >= year1 && book.getYear() <= year2)) {
                ls.add(book);
            }
        }
        for (Electronic electronic : eStoreSearch.electronics) {
            if ((year2 == -1 && electronic.getYear() >= year1)
                    || (year2 == -2 && electronic.getYear() <= year1)
                    || (electronic.getYear() >= year1 && electronic.getYear() <= year2)) {
                ls.add(electronic);
            }
        }
        pT = ls; 
    }

    else if(check == -1){
            ArrayList<Product> ls2 = new ArrayList<Product>();
            int year = Integer.parseInt(timePeriod);
            for (Book book : eStoreSearch.books) {
                if ( year == book.getYear() )
                    ls2.add(book);
            }
            for (Electronic electronic : eStoreSearch.electronics) {
                if ( year ==  electronic.getYear() ) {
                    ls2.add(electronic);
                }
            }
            pT = ls2;
    }

    return pT;
    }

    /**
     *  Performs search with respect to the product id and keywords of the book/electronic
     * 
     * @param productID - product ID
     * @param keywordsForDesc - keywords
     * @return the record that satisfies the search
     */
    public static ArrayList<Product> search(int productID, String[] keywordsForDesc) {       // search using product ID and keywords
        ArrayList<Product> ls = new ArrayList<Product>();
        ls.addAll(eStoreSearch.search(productID));
        boolean keywordMatching = true;
        for (String keyword : keywordsForDesc) {
            System.out.println("keyword searching : "+keyword);
            if(ls.get(0).getDescription() == null){
                System.out.println("\n\nFUCK\n\n");
            }
            if (!ls.get(0).getDescription().contains(keyword)) {
                keywordMatching = false;
                break;
            }
        }
        if (!keywordMatching) {
            ls.remove(0);
        }
        return ls;
    }


    /**
     *  Performs search with respect to keywords and time period of the book/electronic
     * 
     * @param keywordsForDesc - Keywords for description
     * @param timePeriod - time period
     * @return the record that satisfies the search
     */
    public static ArrayList<Product> search(String[] keywordsForDesc, String timePeriod) {      // search using Keywords and time period
        ArrayList<Product> pT = new ArrayList<Product>();
        int check = timePeriod.indexOf('-');
        if(check > -1) {
        ArrayList<Product> ls = new ArrayList<Product>();
        ls.addAll(eStoreSearch.search(keywordsForDesc));
        StringTokenizer strtok = new StringTokenizer(timePeriod, "-");
        int year1 = Integer.parseInt(strtok.nextToken()), year2 = year1;
        try {
            year2 = Integer.parseInt(strtok.nextToken());
        } catch (NoSuchElementException e) {
            if (timePeriod.charAt(timePeriod.length() - 1) == '-') {
                year2 = -1;
            } else if (timePeriod.charAt(0) == '-') {
                year2 = -2;
            }
        }
        for (Product product : ls) {
            if (!((year2 == -1 && product.getYear() >= year1)
                    || (year2 == -2 && product.getYear() <= year1)
                    || (product.getYear() > year1 && product.getYear() < year2))) {
                ls.remove(product);
            }
        }
        pT = ls;
    }
    else if(check == -1){
        ArrayList<Product> ls2 = new ArrayList<Product>();
        ls2.addAll(eStoreSearch.search(keywordsForDesc));
        int year = Integer.parseInt(timePeriod);
        for (Book book : eStoreSearch.books) {
            if ( year == book.getYear() )
                ls2.add(book);
        }
        for (Electronic electronic : eStoreSearch.electronics) {
            if ( year ==  electronic.getYear() ) {
                ls2.add(electronic);
            }
        }
        pT = ls2;
        }

        return pT;
    }

    /**
     * Performs search with respect to product id and time period of the book/electronic
     *  
     * @param productID - product ID
     * @param timePeriod - time period
     * @return the record that satisfies the search
     */
    public static ArrayList<Product> search(int productID, String timePeriod) {          // search using product ID and time period
        ArrayList<Product> pT = new ArrayList<Product>();
        int check = timePeriod.indexOf('-');
        if(check > -1) {
        ArrayList<Product> ls = new ArrayList<Product>();
        ls.addAll(eStoreSearch.search(productID));
        StringTokenizer strtok = new StringTokenizer(timePeriod, "-");
        int year1 = Integer.parseInt(strtok.nextToken()), year2 = year1;
        try {
            year2 = Integer.parseInt(strtok.nextToken());
        } catch (NoSuchElementException e) {
            if (timePeriod.charAt(timePeriod.length() - 1) == '-') {
                year2 = -1;
            } else if (timePeriod.charAt(0) == '-') {
                year2 = -2;
            }
        }
        if (!((year2 == -1 && ls.get(0).getYear() >= year1)
                || (year2 == -2 && ls.get(0).getYear() <= year1)
                || (ls.get(0).getYear() > year1 && ls.get(0).getYear() < year2))) {
            ls.remove(0);
        }
        pT = ls;
    }
    else if(check == -1){
        ArrayList<Product> ls2 = new ArrayList<Product>();
        ls2.addAll(eStoreSearch.search(productID));
        int year = Integer.parseInt(timePeriod);
        for (Book book : eStoreSearch.books) {
            if ( year == book.getYear() )
                ls2.add(book);
        }
        for (Electronic electronic : eStoreSearch.electronics) {
            if ( year ==  electronic.getYear() ) {
                ls2.add(electronic);
            }
        }
        pT = ls2;
        }

        return pT;
    }

    /**
     * Performs search with respect to product id, keywords and time period of the book/electronic
     * 
     * @param productID - Product Id
     * @param keywordsForDesc - Keywords for description
     * @param timePeriod - time Period
     * @return the record that matches the search.
     */
    public static ArrayList<Product> search(int productID, String[] keywordsForDesc, String timePeriod) {    // search using productID, keywords and timeperiod
        ArrayList<Product> pT = new ArrayList<Product>();
        int check = timePeriod.indexOf('-');
        if(check > -1) {
        ArrayList<Product> ls = new ArrayList<Product>();
        ls.addAll(eStoreSearch.search(productID, keywordsForDesc));
        StringTokenizer strtok = new StringTokenizer(timePeriod, "-");
        int year1 = Integer.parseInt(strtok.nextToken()), year2 = year1;
        try {
            year2 = Integer.parseInt(strtok.nextToken());
        } catch (NoSuchElementException e) {
            if (timePeriod.charAt(timePeriod.length() - 1) == '-') {
                year2 = -1;
            } else if (timePeriod.charAt(0) == '-') {
                year2 = -2;
            }
        }
        Product product = ls.get(0);
        if (!((year2 == -1 && product.getYear() >= year1)
                || (year2 == -2 && product.getYear() <= year1)
                || (product.getYear() > year1 && product.getYear() < year2))) {
            ls.remove(product);
        }

        pT = ls;
    }
    else if(check == -1){
        ArrayList<Product> ls2 = new ArrayList<Product>();
        ls2.addAll(eStoreSearch.search(productID, keywordsForDesc));
        int year = Integer.parseInt(timePeriod);
        for (Book book : eStoreSearch.books) {
            if ( year == book.getYear() )
                ls2.add(book);
        }
        for (Electronic electronic : eStoreSearch.electronics) {
            if ( year ==  electronic.getYear() ) {
                ls2.add(electronic);
            }
        }
        pT = ls2;
        }
        return pT;
    }

    /**
     * Checks if a record exists, if it doesn't then adds to the ArrayList.
     * 
     * @param p - product
     * @param t - product type
     * @return false if record exists, true if record does not exists after adding it.
     */
    public static boolean add(Product p, ProductType t) {             // checks if a record exists, if it doesn't then adds to the ArrayList.
        for (Book b : eStoreSearch.books) {
            if (b.getProductID() == p.getProductID()) {
                return false;
            }
        }
        for (Electronic electronic : eStoreSearch.electronics) {
            if (electronic.getProductID() == p.getProductID()) {
                return false;
            }
        }
        if (t == t.BOOK) {
            eStoreSearch.books.add((Book) p);
        } else {
            eStoreSearch.electronics.add((Electronic) p);
        }
        return true;
    }


     /**
     * Prompts the user for data. Calls the search methods according to the parameter input by the user.
     * @param args
     * @throws InputMismatchException
     */
    public static void main(String[] args) {                                     // main
        Scanner sc = new Scanner(System.in);
        while (true) {                                                          // loop menu
            System.out.println("Hello Please Enter either add or search or quit command then press enter");
            String command = sc.next();
            switch (command) {                                                  // switch method for corresponding user input
                case "add":                                                     // to add data
                    String type = "";
                    do {
                        System.out.println("Please enter type of product to add it's either book or electronic");
                        type = sc.next();
                    } while ((type.compareToIgnoreCase(ProductType.BOOK.toString()) != 0) && (type.compareToIgnoreCase(ProductType.ELECTRONIC.toString()) != 0));
                    System.out.println("Enter the product id please:");
                    int id = 0;
                    boolean retry;
                    do {
                        try {
                            retry = false;
                            id = sc.nextInt();
                        } catch (InputMismatchException e) {
                            retry = true;
                            System.out.println("you did not enter a number try again with a number please");
                        }
                    } while (retry);

                    System.out.println("Enter the product's description please");
                    sc.nextLine();
                    String desc = sc.nextLine();

                    System.out.println("Enter the product's publication year please:");
                    int year = 0;
                    do {
                        try {
                            retry = false;
                            year = sc.nextInt();
                            if (year <= 1000 || year >= 9999) {
                                retry = true;
                                System.out.println("you did not enter a number try again with a number please");
                            }
                        } catch (InputMismatchException e) {
                            retry = true;
                            System.out.println("you did not enter a number try again with a number please");
                        }
                    } while (retry);
                    System.out.println("Enter Yes if you want to specify price and anything else if you don't:");
                    sc.nextLine();
                    String answer = sc.nextLine();
                    int price = 0;
                    if (answer.compareToIgnoreCase("yes") == 0) {
                        System.out.println("Enter the price then :");
                        do {
                            try {
                                retry = false;
                                price = sc.nextInt();
                            } catch (InputMismatchException e) {
                                retry = true;
                                System.out.println("you did not enter a number try again with a number please");
                            }
                        } while (retry);
                    }
                    if ((type.compareToIgnoreCase(ProductType.BOOK.toString()) == 0)) {
                        //sc.nextLine();
                        System.out.println("Enter Yes if you want to specify the publisher and anything else if you don't:");
                        answer = sc.nextLine();
                        String publisher = "";
                        if (answer.compareToIgnoreCase("yes") == 0) {
                            System.out.println("Enter the publisher then:");
                            publisher = sc.nextLine();
                        }
                        System.out.println("Enter Yes if you want to specify the authors and anything else if you don't:");
                        answer = sc.nextLine();
                        String[] authors = {};
                        if (answer.compareToIgnoreCase("yes") == 0) {
                            int n = 0;
                            System.out.println("Enter the number of the authors to enter:");
                            do {
                                try {
                                    retry = false;
                                    n = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    retry = true;
                                    System.out.println("you did not enter a number try again with a number please");
                                }
                            } while (retry);
                            authors = new String[n];
                            sc.nextLine();
                            for (int i = 0; i < n; i++) {
                                System.out.println("Enter an author:");
                                authors[i] = sc.nextLine();
                            }
                        }
                        if (!add(new Book(publisher, authors, id, price, year, desc), ProductType.BOOK)) {
                            System.out.println("id already added");
                        }
                    } else {
                        System.out.println("Enter Yes if you want to specify the maker and anything else if you don't:");
                        answer = sc.next();
                        String maker = "";
                        if (answer.compareToIgnoreCase("yes") == 0) {
                            maker = sc.next();
                        }
                        if (!add(new Electronic(maker, id, price, year, desc), ProductType.ELECTRONIC)) {
                            System.out.println("id already added");
                        }
                    }
                    break;
                case "search":                                                 // to search
                    String input = "some";
                    System.out.println("Enter the product id please:");
                    sc.nextLine();
                    input = sc.nextLine();
                    int PID = 0;
                    boolean takePID = (!input.equals(""));
                    retry = false;
                    if (takePID) {
                        try {
                            PID = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            retry = true;
                            System.out.println("you did not enter a number try again with a number please");
                        }
                    }
                    if (takePID && retry) {
                        do {
                            retry = false;
                            input = sc.nextLine();
                            takePID = (input.equals(""));
                            if (!takePID) {
                                break;
                            }
                            try {
                                PID = Integer.parseInt(input);
                            } catch (NumberFormatException e) {
                                retry = true;
                                System.out.println("you did not enter a number try again with a number please");
                            }
                        } while (retry);
                    }
                    System.out.println("Enter the keywords to search in a sentance delimitered by spaces please:");
                    input = sc.nextLine();
                    String[] keyWords = {};
                    boolean takeKeyWords = (!input.equals(""));
                    if (takeKeyWords) {
                        StringTokenizer strtok = new StringTokenizer(input, " ");
                        keyWords = new String[strtok.countTokens()];
                        int i = 0;
                        while (strtok.hasMoreTokens()) {
                            keyWords[i] = strtok.nextToken();
                            System.out.println("Token is "+keyWords[i]);
                            i++;
                        }
                    }
                    System.out.println("Enter the time period of publication please:");
                    String timePeriod = sc.nextLine();
                    boolean takeTimePeriod = (!timePeriod.equals(""));
                    ArrayList<Product> ls = new ArrayList<>();
                    if (takeKeyWords && takePID && takeTimePeriod) {
                        ls = search(PID, keyWords, timePeriod);
                    } else if (takePID && takeTimePeriod) {
                        ls = search(PID, timePeriod);
                    } else if (takePID && takeKeyWords) {
                        ls = search(PID, keyWords);
                    } else if (takeKeyWords && takeTimePeriod) {
                        ls = search(keyWords, timePeriod);
                    } else if (takeKeyWords) {
                        ls = search(keyWords);
                    } else if (takePID) {
                        ls = search(PID);
                    } else if (takeTimePeriod) {
                        ls = search(timePeriod);
                    } else {
                        ls.addAll(books);
                        ls.addAll(electronics);
                    }
                    if (ls.isEmpty()) {
                        System.out.println("The Search didn't give any result !");
                    } else {
                        System.out.println("the results of the search are :");
                        for (Product p : ls) {
                            boolean found = false;
                            for (Book b : books) {
                                if (b.equals(p)) {
                                    found = true;
                                    System.out.println(b);
                                    break;
                                }
                            }
                            if (!found) {
                                for (Electronic e : electronics) {
                                    if (e.equals(p)) {
                                        System.out.println(e);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "quit":                                       // for various ways to quit the program
                    return;
                case "Quit":
                    return;
                case "QUIT":
                    return;
                case "q":
                    return;
                case "Q":
                    return;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }
}

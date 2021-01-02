/*
 Dinesh Sainath Koti Reddy
 1025287
 */
package eStoreSearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The eStoreSearch.java file consists of a eStoreSearch class, that has the
 * search methods to perform the search function for various parameters, and the
 * main class that uses all the other classes in the 'estore' package to act
 * like a miniature database that stores details of books and electronics, that
 * also allows us to add and search within the existing records.
 *
 * @author Dinesh Sainath
 * @version 1.0
 */
public class eStoreSearch {                                // search class that contains search methods

    private static ArrayList<Product> ProductList = new ArrayList<Product>();

    private static HashMap<String, ArrayList<Integer>> keywordIndex = new HashMap<String, ArrayList<Integer>>();

    /**
     * Performs search with respect to the product id of the book/electronic
     *
     * @param productID - product ID
     * @return the record that satisfies the search
     */
    public static ArrayList<Product> search(int productID) {                //product ID as a parameter to search
        ArrayList<Product> ls = new ArrayList<Product>();
        for (Product p : eStoreSearch.ProductList) {
            if (p.getProductID() == productID) {
                ls.add(p);
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
        ArrayList<Product> ls = new ArrayList();
        ArrayList<Integer> als = eStoreSearch.keywordIndex.get(keywordsForDesc[0].toLowerCase());
        for (int i = 1; i < keywordsForDesc.length; i++) {
            als.retainAll(eStoreSearch.keywordIndex.get(keywordsForDesc[i].toLowerCase()));
        }
        for(Integer index : als){
            ls.add(eStoreSearch.ProductList.get(index));
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
        if (check > -1) {
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
            for (Product p : eStoreSearch.ProductList) {
                if ((year2 == -1 && p.getYear() >= year1)
                        || (year2 == -2 && p.getYear() <= year1)
                        || (p.getYear() >= year1 && p.getYear() <= year2)) {
                    ls.add(p);
                }
            }
            pT = ls;
        } else if (check == -1) {
            ArrayList<Product> ls2 = new ArrayList<Product>();
            int year = Integer.parseInt(timePeriod);
            for (Product p : eStoreSearch.ProductList) {
                if (year == p.getYear()) {
                    ls2.add(p);
                }
            }
            pT = ls2;
        }

        return pT;
    }

    /**
     * Performs search with respect to the product id and keywords of the
     * book/electronic
     *
     * @param productID - product ID
     * @param keywordsForDesc - keywords
     * @return the record that satisfies the search
     */
    public static ArrayList<Product> search(int productID, String[] keywordsForDesc) {       // search using product ID and keywords
        ArrayList<Product> ls = new ArrayList<Product>();
        ls.addAll(eStoreSearch.search(productID));
        ArrayList<Integer> als = eStoreSearch.keywordIndex.get(keywordsForDesc[0].toLowerCase());
        for (int i = 1; i < keywordsForDesc.length; i++) {
            als.retainAll(eStoreSearch.keywordIndex.get(keywordsForDesc[i].toLowerCase()));
        }
        if (!als.contains(eStoreSearch.ProductList.indexOf(ls.get(0)))) {
            ls.remove(0);
        }
        return ls;
    }

    /**
     * Performs search with respect to keywords and time period of the
     * book/electronic
     *
     * @param keywordsForDesc - Keywords for description
     * @param timePeriod - time period
     * @return the record that satisfies the search
     */
    public static ArrayList<Product> search(String[] keywordsForDesc, String timePeriod) {      // search using Keywords and time period
        ArrayList<Product> pT = new ArrayList<Product>();
        int check = timePeriod.indexOf('-');
        if (check > -1) {
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
        } else if (check == -1) {
            ArrayList<Product> ls2 = new ArrayList<Product>();
            ls2.addAll(eStoreSearch.search(keywordsForDesc));
            int year = Integer.parseInt(timePeriod);
            for (Product p : eStoreSearch.ProductList) {
                if (year == p.getYear()) {
                    ls2.add(p);
                }
            }
            pT = ls2;
        }

        return pT;
    }

    /**
     * Performs search with respect to product id and time period of the
     * book/electronic
     *
     * @param productID - product ID
     * @param timePeriod - time period
     * @return the record that satisfies the search
     */
    public static ArrayList<Product> search(int productID, String timePeriod) {          // search using product ID and time period
        ArrayList<Product> pT = new ArrayList<Product>();
        int check = timePeriod.indexOf('-');
        if (check > -1) {
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
        } else if (check == -1) {
            ArrayList<Product> ls2 = new ArrayList<Product>();
            ls2.addAll(eStoreSearch.search(productID));
            int year = Integer.parseInt(timePeriod);
            for (Product p : eStoreSearch.ProductList) {
                if (year == p.getYear()) {
                    ls2.add(p);
                }
            }
            pT = ls2;
        }

        return pT;
    }

    /**
     * Performs search with respect to product id, keywords and time period of
     * the book/electronic
     *
     * @param productID - Product Id
     * @param keywordsForDesc - Keywords for description
     * @param timePeriod - time Period
     * @return the record that matches the search.
     */
    public static ArrayList<Product> search(int productID, String[] keywordsForDesc, String timePeriod) {    // search using productID, keywords and timeperiod
        ArrayList<Product> pT = new ArrayList<Product>();
        int check = timePeriod.indexOf('-');
        if (check > -1) {
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
        } else if (check == -1) {
            ArrayList<Product> ls2 = new ArrayList<Product>();
            ls2.addAll(eStoreSearch.search(productID, keywordsForDesc));
            int year = Integer.parseInt(timePeriod);
            for (Product p : eStoreSearch.ProductList) {
                if (year == p.getYear()) {
                    ls2.add(p);
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
     * @return false if record exists, true if record does not exists after
     * adding it.
     */
    public static boolean add(Product p, ProductType t) {             // checks if a record exists, if it doesn't then adds to the ArrayList.
        for (Product b : eStoreSearch.ProductList) {
            if (b.getProductID() == p.getProductID()) {
                return false;
            }
        }
        if (t == t.BOOK) {
            eStoreSearch.ProductList.add((Book) p);
        } else {
            eStoreSearch.ProductList.add((Electronic) p);
        }
        StringTokenizer tok = new StringTokenizer(p.getDescription());
        while (tok.hasMoreTokens()) {
            String keyword = tok.nextToken();
            if (!eStoreSearch.keywordIndex.containsKey(keyword.toLowerCase())) {
                ArrayList<Integer> als = new ArrayList<Integer>();
                als.add(eStoreSearch.ProductList.indexOf(p));
                eStoreSearch.keywordIndex.put(keyword.toLowerCase(), als);
            } else {
                ArrayList<Integer> als = eStoreSearch.keywordIndex.get(keyword.toLowerCase());
                als.add(eStoreSearch.ProductList.indexOf(p));
            }
        }
        return true;
    }

    /**
     *
     * @param fileName
     */
    public static void saveToFile(String fileName) {
        PrintWriter out = new PrintWriter(System.out);
        try {
            out = new PrintWriter(new FileOutputStream(fileName), true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(eStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Product p : eStoreSearch.ProductList) {
            if (p.getClass() == Book.class) {
                Book b = (Book) p;
                out.print("type = book\n"
                        + "productID = " + b.getProductID() + "\n"
                        + "description = " + b.getDescription() + "\n"
                        + "price = " + b.getPrice() + "\n"
                        + "year = " + b.getYear() + "\n"
                        + "authors = " + b.getAuthorsString() + "\n"
                        + "publisher = " + b.getPublisher() + "\n");
            } else {
                Electronic e = (Electronic) p;
                out.print("type = electronics\n"
                        + "productID = " + e.getProductID() + "\n"
                        + "description = " + e.getDescription() + "\n"
                        + "price = " + e.getPrice() + "\n"
                        + "year = " + e.getYear() + "\n"
                        + "maker = " + e.getMaker() + "\n");
            }
            out.flush();
        }
    }

    /**
     *
     * @param fileName
     */
    public static void loadFromFile(String fileName) throws IOException{
        BufferedReader reader;
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer tok = new StringTokenizer(line, "");
                if (tok.hasMoreTokens()) {
                    tok.nextToken();
                    if (tok.hasMoreTokens()) {
                        String type = tok.nextToken();
                        if (type.equals("book")) {
                            Book b = new Book();
                            line = reader.readLine();//ID
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            b.setProductID(Integer.parseInt(tok.nextToken()));
                            line = reader.readLine();//Description
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            b.setDescription(tok.nextToken());
                            line = reader.readLine();//price
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            if (tok.hasMoreTokens()) {
                                b.setPrice(Integer.parseInt(tok.nextToken()));
                            } else {
                                b.setPrice(0);
                            }
                            line = reader.readLine();//year
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            b.setYear(Integer.parseInt(tok.nextToken()));
                            line = reader.readLine();//authors
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            String[] authors = new String[0];
                            if (tok.hasMoreTokens()) {
                                StringTokenizer tok2 = new StringTokenizer(tok.nextToken(), ", ");
                                authors = new String[tok2.countTokens()];
                                int ind = 0;
                                while (tok2.hasMoreTokens()) {
                                    authors[ind] = tok2.nextToken();
                                    ind++;
                                }
                            }
                            b.setAuthors(authors);
                            line = reader.readLine();//publisher
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            if (tok.hasMoreTokens()) {
                                b.setPublisher(tok.nextToken());
                            } else {
                                b.setPublisher("");
                            }
                            add(b, ProductType.BOOK);
                        } else {
                            Electronic e = new Electronic();
                            line = reader.readLine();//ID
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            e.setProductID(Integer.parseInt(tok.nextToken()));
                            line = reader.readLine();//Description
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            e.setDescription(tok.nextToken());
                            line = reader.readLine();//price
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            if (tok.hasMoreTokens()) {
                                e.setPrice(Integer.parseInt(tok.nextToken()));
                            } else {
                                e.setPrice(0);
                            }
                            line = reader.readLine();//year
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            e.setYear(Integer.parseInt(tok.nextToken()));
                            line = reader.readLine();//maker
                            tok = new StringTokenizer(line, "");
                            tok.nextToken();
                            if (tok.hasMoreTokens()) {
                                e.setMaker(tok.nextToken());
                            } else {
                                e.setMaker("");
                            }
                            add(e, ProductType.ELECTRONIC);
                        }
                    }
                }
                line = reader.readLine();
            }
            reader.close();
    }

    /**
     * Prompts the user for data.Calls the search methods according to the
     * parameter input by the user.
     *
     * @param args
     * @throws InputMismatchException
     */
    public static void main(String[] args) {
        //String fileName = "DATA.txt";
        String fileName = args[0];
        if (args.length != 1) {
            System.out.println("you should pass one parameter which is the file name to save the data on");
            return;
        }
        try{
        	loadFromFile(fileName);
        }catch(IOException e){
        	System.out.println("file not found");return;
        }
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
                                System.out.println("year not valid");
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
                        sc.nextLine();
                    }
                    if ((type.compareToIgnoreCase(ProductType.BOOK.toString()) == 0)) {
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
                            sc.nextLine();
                        }
                        if (!add(new Book(publisher, authors, id, price, year, desc), ProductType.BOOK)) {
                            System.out.println("id already added");
                        }
                    } else {
                        System.out.println("Enter Yes if you want to specify the maker and anything else if you don't:");
                        answer = sc.nextLine();
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
                            i++;
                        }
                    }
                    System.out.println("Enter the time period of publication please:");
                    String timePeriod = sc.nextLine();
                    boolean takeTimePeriod = (!timePeriod.equals(""));
                    ArrayList<Product> ls = new ArrayList();
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
                        ls.addAll(eStoreSearch.ProductList);
                    }
                    if (ls.isEmpty()) {
                        System.out.println("The Search didn't give any result !");
                    } else {
                        System.out.println("the results of the search are :");
                        for (Product p : ls) {
                            System.out.println(p.toString());
                        }
                    }
                    break;
                case "quit":                                       // for various ways to quit the program
                    saveToFile(fileName);
                    return;
                case "Quit":
                    saveToFile(fileName);
                    return;
                case "QUIT":
                    saveToFile(fileName);
                    return;
                case "q":
                    saveToFile(fileName);
                    return;
                case "Q":
                    saveToFile(fileName);
                    return;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }
}

/*
 Dinesh Sainath Koti Reddy
 1025287
 */
package eStoreSearch;

/**
 * 
 *The Book class contains the member variables related to the book and their respective set and get methods.
 * Using inheritance, the Book class is extended from the Product class 
 * @author Dinesh Sainath
 */
public class Book extends Product{
    private String publisher;
    private String[] authors;

    /**
     * Default constructor
     */
    public Book() {
        super(0,0, 0, null);
        this.publisher = null;
        this.authors = null;
    }

    /**
     * Constructor to assign the data to a book
     * @param publisher - publisher's name - string
     * @param authors - authors' name(s) - string
     * @param productID - product id - int
     * @param price - price - int
     * @param year - year of publication - int
     * @param Description - description of the product - string
     */
    public Book(String publisher, String[] authors, int productID, int price, int year, String Description) {
        super(productID, price, year, Description);
        this.publisher = publisher;
        this.authors = authors;
    }


    /**
     * To get the publisher's name
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * To set the publisher's name
     * @param publisher - string
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * To get the authors' name(s)
     * @return the authors
     */
    public String[] getAuthors() {
        return authors;
    }

    public String getAuthorsString(){
        String s = "";
        for (int i = 0; i < this.authors.length; i++) {
            s+=this.authors[i];
            if (i != this.authors.length -1) {
                s+=", ";
            }
        }
        return s;
    }
    
    /**
     * TO set the authors' name(s)
     * @param authors - string
     */
    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    
    @Override
    public String toString() {
        String authorsString = "";
        for(int i = 0 ; i < this.getAuthors().length;i++){
            if (i == 0) {
                authorsString = " "+this.getAuthors()[0];
            }else
                authorsString+=", "+this.getAuthors()[i];
        }
        return "Book{ "+ super.toString() +", publisher = " + this.getPublisher() + ", authors=" + authorsString + '}';
    }
    
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }
    
}

/*
 Dinesh Sainath Koti Reddy
 1025287
 */
package estore;

/**
 * 
 *The Electronic class contains the member variables related to the book and their respective set and get methods
 * Using inheritance the Electronic class is extended from the Product class
 * @author Dinesh Sainath
 */
public class Electronic extends Product {

    private String maker;

    /**
     * Default constructor
     */
    public Electronic(){
        super(0,0,0,null);
        this.maker = null;
    }

    /**
     * Constructor to assign the make, product ID, price, year and description of the electronic
     * @param maker 
     * @param productID
     * @param price
     * @param year
     * @param Description
     */
    public Electronic(String maker, int productID, int price, int year, String Description) {
        super(productID, price, year, Description);
        this.maker = maker;
    }

    /**
     * Gets the maker of the electronic
     * @return maker - string
     */
    public String getMaker() {
        return maker;
    }

    /**
     * Sets the maker of the electronic
     * @param maker
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "Electronic{" + super.toString() + ", maker=" + maker + '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}

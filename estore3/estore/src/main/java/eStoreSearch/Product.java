/*
 Dinesh Sainath Koti Reddy
 1025287
 */
package eStoreSearch;

/**
 * The product class contains common member variables and methods for both books and electronics
 *
 * @author Dinesh Sainath
 */
public class Product {
    private int productID,price,year;
    private String Description;

    /**
     * Constructer method for Product class that assigns Product id, price, year and description to the product
     * @param productID
     * @param price
     * @param year
     * @param Description
     */
    public Product(int productID, int price, int year, String Description) {
        this.productID = productID;
        this.price = price;
        this.year = year;
        this.Description = Description;
    }

    /**
     * Gets the product id of the product(book/electronic)
     * 
     * @return product id - int
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Sets the product ID
     * @param productID - int
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Gets the price of the product(book/electronic)
     * @return price - int
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the product(book/electronic)
     * @param price - int
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the year of the product(book/electronic)
     * @return year - int
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of the product(book/electronic)
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the description of the product(book/electronic)
     * @return description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Sets the description of the product(book/electronic)
     * @param Description - string
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.productID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.getProductID() != other.getProductID()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "productID=" + productID + ", price=" + price + ", year=" + year + ", Description=" + Description;
    }
    
    
    
}

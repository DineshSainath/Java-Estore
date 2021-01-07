//package estore;
//
//import eStoreSearch.Book;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class BookTest {
//    @Test public void testSetandGetPublisher() {
//        Book b = new Book();
//        b.setPublisher("ABC Publications");
//        assertEquals("Publisher should be: ABC Publications ","ABC Publications",b.getPublisher());
//    }
//
//   @Test public void testSetandGetAuthors() {
//        Book b = new Book();
//        String[] a = {"Syd Field", "John Truby"};
//        b.setAuthors(a);
//        assertEquals("Authors should be: Syd Field and John Truby ",a,b.getAuthors());
//    }
//
//    //To check productID, price, year and description from the super class - product through the subclass - Book
//
//    @Test public void testSetandGetProductID() {
//        Book b = new Book();
//        b.setProductID(01);
//        assertEquals("Product ID should be: 01",01,b.getProductID());
//    }
//
//    @Test public void testSetandGetPrice() {
//        Book b = new Book();
//        b.setPrice(40);
//        assertEquals("Price should be: 40",40,b.getPrice());
//    }
//
//    @Test public void testSetandGetYear() {
//        Book b = new Book();
//        b.setYear(1999);
//        assertEquals("Year should be: 1999",1999,b.getYear());
//    }
//
//    @Test public void testSetandGetDescription() {
//        Book b = new Book();
//        b.setDescription("Screenplay");
//        assertEquals("Description should be: Screenplay","Screenplay",b.getDescription());
//    }
//    
//
//}

Name: Dinesh Sainath Koti Reddy ID: 1025287

CIS* 2430 ASSIGNMENT #1

The submitted project is an "eStore" that stores the data records of multiple books and electronics. The details for each product include product id, description, price, year, (maker) or (authors & publishers). It also performs a search function by using any of its details.

The program uses 5 java files and 6 classes: Product - A class that contains common member variables for both the types of product - Books and electronics Book - To store the details of a book Electronic - To store the details of an electronic ProductType - To bring both the products, books and electronic, under one roof eStoreSearch.java : eStoreSearch - it performs the search function according to different parameters, input entered by the user. main - the main class prompts the user for data and the necessary requirements to perform a search within the records.

Assumptions:

That all the input is correct. Some invalid input are given the error messages.
In case of invalid input in the details of the product - book or electronic, the program might crash.
Case sensitive search
Printing the search as it is, without formatting.
Less internal comments because the necessary is explained in the Javadoc comments
q, Q, Quit, quit to exit the program
To run and execute:

To run the project, extract the zip folder, navigate to the folders outermost folder which contains all the other gradle folders. Then in the command line/terminal use 'gradle run' - to compile and execute the program
To run the test cases use 'gradle test'
To delete the class files for a fresh compile use 'gradle clean' and then run. Testing: BookTest.java tests the Get and Set methods of all the member variables in the Book class as well as the super class - Product ElectronicTest.java tests the Get and Set methods of all the member variables in the Book class as well as the super class - Product
I have already accumulated the common member variables between Book and Electronic and their respective get and set methods for effective coding and to avoid rewriting code. If more time and knowledge was present, I would have added more try and catch exceptions to avoid errors.

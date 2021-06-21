/* ***************************************************
 * Final project in cryptographic course             *
 * a program to register new users to MySql Database *
 * The program will encrypt the passwords            *
 * by using Sha256 with salted hash function         *
 * --------------------------------------------------*
 * The Program was coded by:                         *
 * Nasir Alden : ID-035689678                        *
 * Ron Hovara : ID-208902510						 *
 * Maaor Sivoni: ID-204583371						 *
 * ***************************************************/
 

import java.io.*;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class Main {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/usersDB";  // connect to our MySql Database.
	static final String USER = "root";  //User name to login to the database 
	static final String PASS = "";  // Password of connection to database


	// *************** Main Function ***************************
	
  public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, IOException {        
	  System.out.println("Loading JDBC driver...");  // Load the JDBC driver that make java connect to Mysql
	  try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      System.out.println("JDBC Driver loaded!");  // JDBC Successfully loaded
	  } catch (ClassNotFoundException e) {
	      throw new IllegalStateException("Cannot find the driver in the classpath!", e); // JDBC Failed to load
	  }
	  
	  Scanner input = new Scanner(System.in);  // Create a Scanner object
		while(true){    // The main menu loop
			createConfigFile();
			System.out.println("\n\nChoose option please:\n1) Add new table to database.");
			System.out.println("2) Add new user to database.");
			System.out.println("3) Exit.");
            String op = input.nextLine();  // Read user input

			switch(op.charAt(0)) {
			case '1':{        //Add new table to the database
				addTable();
			}
			break;	
			case '2':{	   // Add a new user to 'users' table
				addUser();
			}
			break;
			case '3': {    // Exit option
				System.out.println("Mission Completed , Good Bye!\n");
				return;
			}
			
			default:    // Invalid option choose
				System.out.println("Option not found ,Please try again ...\n");
			}
		}					

  }  

 
//**************** Create New Table in database ******************
 public static void addTable() {

	  System.out.println("Insert name of table to add to database : ");
	  Scanner input = new Scanner(System.in);  // Create a Scanner object
      String table = input.nextLine();  // Read user input
           
	  try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  // connect to database
		  Statement stmt = conn.createStatement();) 
	  			{		      
		          String sql = "CREATE TABLE "+ table +               // Build the Query to send
		                   "(id INTEGER not NULL AUTO_INCREMENT, " +
		                   " username VARCHAR(255), " + 
		                   " password VARCHAR(255), " + 
		                   " salt VARCHAR(255), " + 
		                   " PRIMARY KEY ( id ))"; 

		         stmt.executeUpdate(sql);
		         System.out.println("\""+ table + "\" table created in given database...");  //Table Successfully created
		         conn.close();   //Close the connection
		      } catch (Exception e) {
		    	  System.out.println("Table \""+ table + "\" is exist , No table was added."); // Table creation failed
		      }
  			}
		      	 
// **************** Add User to 'users' table ******************
  public static void addUser() throws InvalidKeyException, NoSuchAlgorithmException {
	  Scanner input = new Scanner(System.in);  // Create a Scanner object
	  System.out.println("Insert username : ");
      String userToAdd = input.nextLine();  // Read user input = new user name to add     
      System.out.println("Insert password : ");
      String passToAdd = input.nextLine();  // Read user input = new user password .
      while(!isValid(passToAdd)) {
    	  System.out.println("Invalid password , Please insert again :");
    	  passToAdd = input.nextLine();  // Read user input = new user password .
      }
      String salt =new String(generateSalt());
	  String hashedPass=HMAC256(passToAdd, salt);
      System.out.println("Salt Used: " + salt);
	  System.out.println("Hashed password: " + hashedPass);
      
	  
	  try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  // connect to database
		  Statement stmt = conn.createStatement();) 
	  			{		      
	         	String sql = "INSERT INTO users (username,password,salt) VALUES ('" +   // Build a Query to send
	         				 userToAdd + "', '" +
	         				 hashedPass + "', '" +
	         				 salt + "')";
	         	stmt.executeUpdate(sql);
		        conn.close();			//Close the connection
		      } catch (Exception e) {

		      }
  			}
 
// ************** Generate Random Salt to hash the passwords with it *****************
  
  public static byte[] generateSalt() {
      SecureRandom random = new SecureRandom();
      byte bytes[] = new byte[20];
      random.nextBytes(bytes);
      return bytes;
  }
  
//*******************((( HMAC256 Function ))) ****************

	public static String HMAC256(String message,String key) throws NoSuchAlgorithmException, InvalidKeyException{
		final Charset asciiCs = Charset.forName("US-ASCII");
		final Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		final SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(asciiCs.encode(key).array(), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		final byte[] mac_data = sha256_HMAC.doFinal(asciiCs.encode(message).array());
		String result = "";
		for (final byte element : mac_data)
		{
		    result += Integer.toString((element & 0xff) + 0x100, 16).substring(1);
		}

		return result;
	}

	//******************* Valid Password Check **********************
	public static boolean isValid(String password)
    {
        // check if password length less than 10
		int minLength = getProperties();
        if (password.length() < minLength ){
            return false;
        }
  
        // check if password contains spaces
        if (password.contains(" ")) {
            return false;
        }
        if (true) {
            int count = 0;
  
            // check if password contains digits from 0 to 9
            for (int i = 0; i <= 9; i++) {
  
                // to convert int to string
                String str1 = Integer.toString(i);
  
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }
  
        // check if password contains special characters
        if (!(password.contains("@") || password.contains("#")
              || password.contains("!") || password.contains("~")
              || password.contains("$") || password.contains("%")
              || password.contains("^") || password.contains("&")
              || password.contains("*") || password.contains("(")
              || password.contains(")") || password.contains("-")
              || password.contains("+") || password.contains("/")
              || password.contains(":") || password.contains(".")
              || password.contains(", ") || password.contains("<")
              || password.contains(">") || password.contains("?")
              || password.contains("|"))) {
            return false;
        }
  
        if (true) {
            int count = 0;
  
            // checking capital letters
            for (int i = 65; i <= 90; i++) {
  
                // type casting
                char c = (char)i;
  
                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }
  
        if (true) {
            int count = 0;
  
            // checking small letters
            for (int i = 90; i <= 122; i++) {
  
                // type casting
                char c = (char)i;
                String str1 = Character.toString(c);
  
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }
  
        // if all conditions fails
        return true;
    }
 //****************** create configuration file **************

	public static void createConfigFile() throws IOException {
	
		File folder = new File("Config"); 
		folder.mkdir(); // create a folder in your current work space
		File file = new File(folder, "app.config"); // put the file inside the folder
		if(file.createNewFile()){
            System.out.println("New Config file was successfully created.");
            try {
    			FileWriter writer = new FileWriter(file, true);
    			writer.write("");
                writer.flush();
                writer.write("app.MinLength=10");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        	}else{
        		System.out.println("File name \""+ file + "\" already exists.");
        		file.delete();
        		}
		
		
	}
//****************** Get properties from Config File ***************	
public static int getProperties() {	
	
	Properties prop = new Properties();
	String fileName = "Config\\app.config";
	InputStream is = null;
	try {
	    is = new FileInputStream(fileName);
	} catch (FileNotFoundException ex1) {
	    ex1.getMessage();
	}
	try {
	    prop.load(is);
	} catch (IOException ex1) {
	    ex1.getMessage();
	}
	int minLength = Integer.parseInt(prop.getProperty("app.MinLength"));
	
	return minLength;
}
	
}
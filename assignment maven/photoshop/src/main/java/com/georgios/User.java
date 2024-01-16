package com.georgios;
import java.util.Map;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.georgios.service.Contains;



public class User {
    
    private String name;
    private String password;
    private String address;
    private String houseNo;
    private String postCode;
    private String city;
    private String country;
    private String phoneNumber;
    private String IBAN;
    private String email;

    public User (String name, String password, String address, String houseNo, String postCode, String city, String country, String phoneNumber, String IBAN, String email) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.houseNo = houseNo;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.IBAN = IBAN;
        this.email = email;
    }

    public User () {

    }


    public String getName(){
        return this.name;                                              //a whole lot of getters and setters, with data validation rules. All of them private, as I
    }                                                                  //wouldn't like anyone to interfere with them. The logic is the same everywhere

    public void setName(String name) {      //a setter for the name, which depends on user input

        if (!Contains.IntegerOrPunctuation(name)) {   //check if the original input conforms with our data validation rule
            this.name = name;
        } else {
            
            Scanner scanner = new Scanner (System.in);
            String effort = name;

            while (Contains.IntegerOrPunctuation(effort) && !effort.equals("exit")) {    //if not, force the user to input again or leave
                System.out.println("Incorrect statement! Please try again, or press \"exit\" to leave the input section.");
                effort = scanner.nextLine();
                this.name = effort;
            } 

        }
    
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {

        while (true) {

        System.out.println("Please, verify your password:");
        Scanner scan = new Scanner(System.in);
        String verification = scan.nextLine();

        
        if (password.equals(verification)) {this.password = verification; break;} else {
            System.out.println("Invalid input; please try verification again");
            }
        }

    }


    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {      //a setter for the name, which depends on user input

        if (!Contains.IntegerOrPunctuation(address)) {   //check if the original input conforms with our data validation rule
            this.address = address;
        } else {
            
            Scanner scanner = new Scanner (System.in);
            String effort = address;

            while ((effort.equals(this.address) && Contains.IntegerOrPunctuation(effort)) && !effort.equals("exit")) {    //if not, force the user to input again or leave
                System.out.println("Incorrect statement! Please try again, or press \"exit\" to leave the input section.");
                effort = scanner.nextLine();
                this.address = effort;
            } 
        }
    
    }

    public String getHouseNo() {
        return this.houseNo;
    }

    public void setHouseNo(String houseNo) {
        if (!Contains.Punctuation(houseNo) && Character.isDigit(houseNo.charAt(0))) {
            this.houseNo = houseNo;
        } else {
            Scanner scanner = new Scanner(System.in);
            String effort = houseNo;
    
            while ((!effort.equals(this.houseNo) || Contains.Punctuation(effort) || !Character.isDigit(effort.charAt(0))) && !effort.equalsIgnoreCase("exit")) {
                System.out.println("Incorrect statement! Please try again, or type 'exit' to leave the input section.");
                effort = scanner.nextLine();
            }
            
            if (!effort.equalsIgnoreCase("exit")) {
                this.houseNo = effort;
            }
        }
    }
    

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String postcode) {      //a setter for the name, which depends on user input

        if (!Contains.Punctuation(postcode) 
           && postcode.length() == 6 
           && Contains.Integer(postcode.substring(0, 3)) 
           && !Contains.Integer(postcode.substring(4, 5))) {   //check if the original input conforms with our data validation rule
            this.postCode = postcode;
        } else {
            
            Scanner scanner = new Scanner (System.in);
            String effort = postcode;

            while ((Contains.Punctuation(effort) 
                    || effort.length() != 6 
                    || !Contains.Integer(effort.substring(0, 3)) 
                    || Contains.Integer(effort.substring(4, 5))) && !effort.equals("exit")) {    //if not, force the user to input again or leave
                System.out.println("Incorrect statement! Please try again, or press \"exit\" to leave the input section.");
                effort = scanner.nextLine();
                this.postCode = effort;
            } 
        }
    
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {      //a setter for the name, which depends on user input

        if (!Contains.IntegerOrPunctuation(city) 
           && city.length() <= 85 ) {   //check if the original input conforms with our data validation rule
            this.city = city;
        } else {
            
            Scanner scanner = new Scanner (System.in);
            String effort = city;

            while ((Contains.IntegerOrPunctuation(effort) 
                    || effort.length() > 85) && !effort.equals("exit")) {    //if not, force the user to input again or leave
                System.out.println("Incorrect statement! Please try again, or press \"exit\" to leave the input section.");
                effort = scanner.nextLine();
                this.city = effort;
            } 
        }
    
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {      //a setter for the name, which depends on user input

        if (!Contains.IntegerOrPunctuation(country) 
           && country.length() <= 56 ) {   //check if the original input conforms with our data validation rule
            this.country = country;
        } else {
            
            Scanner scanner = new Scanner (System.in);
            String effort = country;

            while ((Contains.IntegerOrPunctuation(effort) 
                    || effort.length() > 56) && !effort.equals("exit")) {    //if not, force the user to input again or leave
                System.out.println("Incorrect statement! Please try again, or press \"exit\" to leave the input section.");
                effort = scanner.nextLine();
                this.country = effort;
            } 
        }
    
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phonenumber) {      //a setter for the name, which depends on user input

        if (!Contains.Punctuation(phonenumber) 
           && 8 < phonenumber.length() && phonenumber.length() < 13
           && Contains.Integer(phonenumber)) {   //check if the original input conforms with our data validation rule
            this.phoneNumber = phonenumber;
        } else {
            
            Scanner scanner = new Scanner (System.in);
            String effort = phonenumber;

            while ((Contains.Punctuation(effort) 
                    || 8 > effort.length() || effort.length() > 13
                    || !Contains.Integer(effort)) && !effort.equals("exit")) {    //if not, force the user to input again or leave
                System.out.println("Incorrect statement! Please try again, or press \"exit\" to leave the input section.");
                effort = scanner.nextLine();
                this.postCode = effort;
            } 
            
            //Third time the cat passed by. Even Trinity knows by now.
        }
    
    }
    public String getIBAN() {
        return this.IBAN;
    }

    public void setIBAN(String IBAN) {      //a setter for the name, which depends on user input

        if (!Contains.Punctuation(IBAN) 
           && IBAN.length() <= 34
           && Contains.Integer(IBAN.substring(2)) 
           && !Contains.Integer(IBAN.substring(0, 1))) {   //check if the original input conforms with our data validation rule
            this.IBAN = IBAN;
        } else {
            
            Scanner scanner = new Scanner (System.in);
            String effort = IBAN;

            while ((Contains.Punctuation(effort) 
                    || effort.length() > 34 
                    || !Contains.Integer(effort.substring(2)) 
                    || Contains.Integer(effort.substring(0, 1))) && !effort.equals("exit")) {    //if not, force the user to input again or leave
                System.out.println("Incorrect statement! Please try again, or press \"exit\" to leave the input section.");
                effort = scanner.nextLine();
                this.IBAN = effort;
            } 
        }
    
    }

    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {      //a setter for the name, which depends on user input

        if (email.contains(String.valueOf('@')) && email.contains(String.valueOf('.'))) {   //check if the original input conforms with our data validation rule
            this.email = email;
        } else {
            
            Scanner scanner = new Scanner (System.in);
            String effort = email;

            while ((!email.contains(String.valueOf('@')) || !email.contains(String.valueOf('.'))) && !effort.equals("exit")) {    //if not, force the user to input again or leave
                System.out.println("Incorrect statement! Please try again, or press \"exit\" to leave the input section.");
                effort = scanner.nextLine();
                this.postCode = effort;
            } 
        }
    
    }


    public static boolean logIn(String username) {

        
        String jsonFilePath =  "C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\user_info\\" + username + ".json";
        
        try (Reader reader = new FileReader(jsonFilePath)) {
            // Parse JSON file into a Map using Gson
            Gson gson = new Gson();
            Map<String, Object> jsonMap = gson.fromJson(reader, Map.class);

            // Retrieve value associated with a specific key
            String password = "password";

            if (jsonMap.containsKey(password)) {
                Object valuePassword = jsonMap.get(password);
                Scanner scan = new Scanner(System.in);
                System.out.println("I can only show you the door. You're the one that has to walk through it");
                System.out.println("Please insert your password");
                String valPassword = scan.nextLine();

                if (valPassword.equals(valuePassword)) {return true;} else {return false;}

            } else {
                System.out.println("Key '" + password + "' not found in the JSON.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error occurred while reading/parsing the user's information. An act of sabotage?.");
            return false;
        }

    }


    public static User getInformation(String username) {
        String jsonFilePath = "C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\user_info\\" + username + ".json";
    
        try (Reader reader = new FileReader(jsonFilePath)) {
            // Parse JSON file into a User object using Gson
            Gson gson = new Gson();
            User user = gson.fromJson(reader, User.class);
            return user;
    
        } catch (Exception e) {
            return null; // Return null or handle error case for user not found
        }
    }
    
 

    public static User setFirstInformation () {

        User user = new User (null, null, null, null, null, null, null, null, null, null);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your full legal name:");   //name definition
        String input = scanner.nextLine();
        user.setName(input);
        String username = user.getName(); //we're gonna need that for naming the json

        System.out.println("Please enter your preferred password:");   //password definition
        input = scanner.nextLine();
        user.setPassword(input);

        System.out.println("Please enter your address:");   //address definition
        input = scanner.nextLine();
        user.setAddress(input);

        System.out.println("Please enter your house number:");   //house number definition
        input = scanner.nextLine();
        user.setHouseNo(input);

        System.out.println("Please enter your postcode:");   //postcode definition
        input = scanner.nextLine();
        user.setPostCode(input);

        System.out.println("Please enter your city:");   //city definition
        input = scanner.nextLine();
        user.setCity(input);

        System.out.println("Please enter your country:");   //country definition
        input = scanner.nextLine();
        user.setCountry(input);

        System.out.println("Please enter your phone number:");   //phone number definition
        input = scanner.nextLine();
        user.setPhoneNumber(input);

        System.out.println("Please enter your IBAN:");   //IBAN definition
        input = scanner.nextLine();
        user.setIBAN(input);

        System.out.println("Please enter your e-mail:");   //Email definition
        input = scanner.nextLine();
        user.setEmail(input);

        
        Gson jsonuser = new Gson(); //Making new Gson object, and parsing the class information to it
        String newUser = jsonuser.toJson(user);

        String json_file_path = "C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\user_info\\" + username + ".json";

        try {
            // Check if the file exists
            if (Files.notExists(java.nio.file.Paths.get(json_file_path))) {
                // Create the file if it does not exist
                Files.createFile(java.nio.file.Paths.get(json_file_path));
            }
        } catch (IOException e) {
            e.printStackTrace();}

        try (FileWriter writer = new FileWriter(json_file_path)) {
            writer.write(newUser);
            System.out.println("Your user information was stored successfully!");
        } catch (IOException er) {
            System.out.println("The user information could not be stored. Try again!");
            }

        return user;

    }


    public void setInformation () {    //Supermethod, providing an interface for changing user information

        while (true) {

            System.out.println("Your current information:\n\n\n\n");
            returnInformation(this);
            System.out.println("\n\n\n\nType which one of the following that you would like to change:\n\nAddress\nHouse No\nPostcode\nCity\nCountry\nPhone Number\nIBAN\nEmail\n\n\n");
            
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            System.out.println("Now type in the changed value:");
            String value = scanner.nextLine();

            switch (choice) {
                case "Address": this.setAddress(value); break;
                case "address": this.setAddress(value); break;
                case "House No": this.setHouseNo(value); break;
                case "house no": this.setHouseNo(value); break;
                case "House no": this.setHouseNo(value); break;
                case "Postcode": this.setPostCode(value); break;
                case "postcode": this.setPostCode(value); break;
                case "City": this.setCity(value); break;
                case "city": this.setCity(value); break;
                case "Country": this.setCountry(value); break;
                case "country": this.setCountry(value); break;
                case "Phone Number": this.setPhoneNumber(value); break;
                case "Phone number": this.setPhoneNumber(value); break;
                case "phone number": this.setPhoneNumber(value); break;
                case "IBAN": this.setIBAN(value); break;
                case "Email": this.setEmail(value); break;
                case "email": this.setEmail(value); break;
                case "e-mail": this.setEmail(value); break;
                default: System.out.println("Invalid choice. Please, select again");}

            System.out.println("Would you like to keep editing?");

            value = scanner.nextLine();
            if (!value.equalsIgnoreCase("yes")) {
                break;
            }

            }
        
        System.out.println("Your new information\n\n\n");

        returnInformation(this);

        Gson jsonuser = new Gson(); //Making new Gson object, and parsing the class information to it
        String newUser = jsonuser.toJson(this);

        System.out.println("\n\n\n");

        String json_file_path = "C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\user_info\\" + this.getName() + ".json";

        try {
            // Check if the file exists
            if (Files.notExists(java.nio.file.Paths.get(json_file_path))) {
                // Create the file if it does not exist
                Files.createFile(java.nio.file.Paths.get(json_file_path));
            }
        } catch (IOException e) {
            e.printStackTrace();}

        try (FileWriter writer = new FileWriter(json_file_path)) {
            writer.write(newUser);
            System.out.println("Your user information was stored successfully!");
        } catch (IOException er) {
            System.out.println("The user information could not be stored. Try again!");
            }
    }

    public static void returnInformation (User user) {

        System.out.println( "Full Name: " + user.getName() + "\n" +
               "Address: " + user.getAddress() + " " + user.getHouseNo() + "\n" +    //a private method to print information- will come in handy later
               "         " + user.getPostCode() + " " + user.getCity() + "\n" +
               "         " + user.getCountry() + "\n" +
               "Phone number: " + user.getPhoneNumber() + "\n" +
               "IBAN: " + user.getIBAN() + "\n");

    }


    public static void deleteInformation (String username) {

        String jsonFilePath =  "C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\user_info\\" + username + ".json";
        Path path = Paths.get(jsonFilePath);

        try {
            // Use Files.delete() to delete the file
            Files.delete(path);
            System.out.println("JSON file deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while deleting the JSON file.");
        }

    }
    
        
    public static User confirmEditDefault() {//an environment to change user information, based on our private getters and setters

        String userChoice = new String();

        while (!userChoice.equals("return")){       //close the interface, if someone wants to stop editing stuff


        System.out.println("Type in \"new user\" if you want to create a new account, your username to login your own account, or \"return\" to get back to main");
        Scanner scan = new Scanner(System.in);
        userChoice = scan.nextLine();        //setting up an input object

            if (userChoice.equals("new user")) {
                System.out.println ("Welcome to the real world. A world without rules and controls, without borders or boundaries; "+ 
                                   "a world where anything is possible. \nI mean, except filling in your user information.");
                userChoice = "return";  // back to main
                User userDef = setFirstInformation();  //create a json with the user's information
                System.out.println("\n\nYour new information:\n\n\n");
                User.returnInformation(userDef);
                return userDef;


            } else {

                String usernameNow = userChoice;   //store selected username

                User userDef = User.getInformation(usernameNow); //get the information of the user
                
                if (logIn(usernameNow)) {
                    System.out.println("Now, select \"edit\" to edit your informationm, \"delete\" to delete your user, or \"return\" to return to main");
                    
                    Scanner scanning = new Scanner(System.in);
                    userChoice = scanning.nextLine();

                    while (userChoice.equals("edit") || userChoice.equals("delete")) {  //stay inside the loop, so long as one types anything but "edit", "delete" or "return"

                        
                        if (userChoice.equals("edit")) {

                            userDef.setInformation();
                            userChoice = "none";

                        } else if (userChoice.equals("delete")) {
                            deleteInformation(usernameNow);
                            usernameNow = null;
                            userDef = new User();
                            userChoice = "return";
                            return userDef;
                        }
                    }

                    return userDef;
                }
            }
        }
        User userDef = new User();
        return userDef;
    }
}

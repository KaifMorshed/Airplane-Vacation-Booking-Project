//Program Name: Airline Booking System
//Program Description: Allows users to create accounts, and book flights as well as plan their trips
//Program Authors: Kaif and Gourob
//Program Created: June 7 2022
//Program Last Edited: June 20 2022

//import libraries
import java.util.*;
import java.io.*;

class Main { //start main class

  //declare colours
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

  //Scanner for user input
  public static Scanner in = new Scanner(System.in);
  
  public static void main(String[] args)throws IOException { //start main method
    //Clear console
    System.out.print("\033[H\033[2J");  
    System.out.flush();

    //menu method
    menu();
    
  }//end main method

  public static void menu()throws IOException{ //starts menu method

    //declare variables
    int choice;

    do{ //asks user what they want to do
      //title and asks user what they want to do
      System.out.println(ANSI_CYAN + "\n\n░█─▄▀ ░█▀▀█ 　 ─█▀▀█ ▀█▀ ░█▀▀█ ░█─── ▀█▀ ░█▄─░█ ░█▀▀▀ ░█▀▀▀█ ─ ░█▀▀█ ░█▀▀▀█ ░█▀▄▀█\n░█▀▄─ ░█─▄▄ 　 ░█▄▄█ ░█─ ░█▄▄▀ ░█─── ░█─ ░█░█░█ ░█▀▀▀ ─▀▀▀▄▄ ▄ ░█─── ░█──░█ ░█░█░█\n░█─░█ ░█▄▄█ 　 ░█─░█ ▄█▄ ░█─░█ ░█▄▄█ ▄█▄ ░█──▀█ ░█▄▄▄ ░█▄▄▄█ █ ░█▄▄█ ░█▄▄▄█ ░█──░█\n" + ANSI_RESET);
      System.out.println(ANSI_YELLOW + "What would you like to do?" + ANSI_RESET);
      System.out.println(ANSI_PURPLE + "1. Create Account\n2. Login" + ANSI_RESET);

      choice = in.nextInt();

      //clear console
      System.out.print("\033[H\033[2J");  
      System.out.flush();
      
      if(choice == 1){
        //allows user to create account
        createAccount();
      }

      else if(choice == 2){
        //allows user to login if they have an account
        login();
      }

      else{
        //if user inputs invalid option
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println(ANSI_RED + "Please pick an available option" + ANSI_RESET);
      }
      
    }while(choice != 1 && choice != 2); //while user inputs an invalid option, loop will repeat
  } //end menu


  
  public static void createAccount()throws IOException{ //allows user to create account

    //declare variables
    String username, password1, password;
    boolean same=true, sameUser=false;
    //arraylist to hold all usernames that exist in accounts file
    ArrayList<String> users = new ArrayList<String>();

    //clear buffer
    in.nextLine();
    
    do{ //start do loop
      do{ //start do loop

        //resets to false unless conditions are met
        sameUser = false;

        do{
          //asks for user input
          System.out.println(ANSI_YELLOW + "What would you like as your username?" + ANSI_RESET);
          username = in.nextLine();

          if(username.length()<1){
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println(ANSI_RED + "Username cannot be empty\n" + ANSI_RESET);
          }//end if
        }while(username.length()<1);
 
      
        try{ //reads from account file
          File myFile = new File("accounts.csv");
          Scanner myFileReader = new Scanner (myFile);
            
          while (myFileReader.hasNextLine()) {
            String split = myFileReader.nextLine();
            String [] data = split.split(",");

            //adds username to arraylist of users
            if(data.length==2){
              users.add(data[0]);
            } //end if
          }//end while
          //close file
          myFileReader.close();
        }
        catch (FileNotFoundException a){ //catch 
          System.out.println(ANSI_RED + "Error:" + ANSI_RESET);
          System.out.println(ANSI_RED + "Could not access file: accounts.csv" + ANSI_RESET);
        }

        //loops through users arraylist and compares to user input
        for(int i = 0; i<users.size(); i++){
          if(users.get(i).equals(username)){ //if username already exists
            System.out.println(ANSI_RED + "This username already exists" + ANSI_RESET);
            sameUser = true;
            break;
          } //end if
        } //end for
      }while(sameUser); //while the username is already taken, loop will repeat

      do{ //start do loop

        do{
          //asks for users password
          System.out.println(ANSI_YELLOW + "What would you like as your password?" + ANSI_RESET);
          password1 = in.nextLine();

          if(password1.length()<1){
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println(ANSI_RED + "Password cannot be empty" + ANSI_RESET);
          }//end if
          
        }while(password1.length()<1);

        //asks to confirm password
        System.out.println(ANSI_YELLOW + "Confirm Password" + ANSI_RESET);
        password = in.nextLine();

        if(password.equals(password1)){ //if passwords are equal
          System.out.println(ANSI_GREEN + "Account Created..." + ANSI_RESET);
          same = false;
        } //end if
          
        else{ //if passwords dont match
          System.out.print("\033[H\033[2J");  
          System.out.flush();
          System.out.println(ANSI_RED + "Passwords do not match please retry: " + ANSI_RESET);
        } //end else
      }while(same); //repeats while passwords are different

      //close console
      System.out.print("\033[H\033[2J");  
      System.out.flush();

      //adds the username and password as a new line in accounts.csv
      try(FileWriter fw = new FileWriter("accounts.csv", true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter out = new PrintWriter(bw))
      {
        out.println(username + "," + password);
      } catch (IOException a) { //catch
        System.out.println(ANSI_RED + "An error occurred." + ANSI_RESET);
        a.printStackTrace();
      }
    }while(same); //while the user has not successfully created an account

    //creates a new file with the users name for the users information
    String fileName = username +".txt";
     try {
      File File = new File(fileName);
      File.createNewFile(); //creates file
    } 
     catch (IOException a) { //catch
      System.out.println(ANSI_RED + "An error occurred." + ANSI_RESET);
      a.printStackTrace();
    }

    try{ //adds the default statement to users file
      FileWriter myWriter = new FileWriter(username + ".txt");
      myWriter.write("");
      myWriter.write("You Currently have no booked flights");
      myWriter.close();
    }
    catch(IOException a){ //catch
      System.out.println(ANSI_RED + "An error occurred." + ANSI_RESET);
      a.printStackTrace();
    }

    //returns to menu
    menu();
  } //end create account method

  
  public static void login()throws IOException{ //allows user to access their created account

    //declare variables
    String username, password;
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<String> passwords = new ArrayList<String>();
    boolean valid;

    try{ //reads through accounts file
      File myFile = new File("accounts.csv");
      Scanner myFileReader = new Scanner (myFile);
            
      while (myFileReader.hasNextLine()) {
        String split = myFileReader.nextLine();
        String [] data = split.split(",");
          
        usernames.add(data[0]);
        passwords.add(data[1]);
      }
      myFileReader.close();
    }//end try
    catch (FileNotFoundException a){ //catch
      System.out.println(ANSI_RED + "An error occurred." +ANSI_RESET);
      a.printStackTrace();
    }

    //clear buffer
    in.nextLine();
    
    do{ //start do loop
      //declare variables
      int namePosition = 0, passPosition = 0;

      //asks for username and password
      System.out.print(ANSI_YELLOW + "\nUsername: " + ANSI_RESET);
      username = in.nextLine();
      System.out.print(ANSI_YELLOW + "\nPassword: " + ANSI_RESET);
      password = in.nextLine();

      //loops through file contents to compare username and then password
      //if they match they can access their account file
      for(int i = 0; i<usernames.size(); i++){
        if(usernames.get(i).equals(username)){
          namePosition += i + 1;
          if(passwords.get(namePosition - 1).equals(password)){
            passPosition += i + 1;
          }//end if
        }//end if
      }//end for

      //if the position of the username and password matches, they access their account
      //if the position values remain 0, the username and password dont exist in the file
      if(passPosition == 0 || namePosition == 0){
        valid = false;
      }

      //if they are equal, then the account details match the user input and they access their account file
      else if(passPosition == namePosition){
        valid = true;
      }

      // else there was an error and they must retry
      else{
        valid = false;
      }

      //if the users input does not match data from accounts.csv, they must retry
      if(valid == false){
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println(ANSI_RED + "Incorrect Username or Password!" + ANSI_RESET);
      } //end if

      else{ //if they entered correct details
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println(ANSI_GREEN +"Logged in successfully" + ANSI_RESET);
        //opens the account menu for that user
        accountMenu(username);
      } //end else
      
    }while(!valid); //while the user has input the wrong details the loop repeats
  } //end login() class


  public static void accountMenu(String username)throws IOException{ //displays the account menu after a user has logged in

    //declare variables
    int choice;
    boolean valid = true;

    do{ //start do loop

      //asks user for what they want to do
      System.out.println(ANSI_YELLOW + "\nWhat would you like to do?" + ANSI_RESET);
      System.out.println(ANSI_PURPLE + "1. Manage Trips\n2. Account Settings \n3. Sign Out" + ANSI_RESET);

      choice = in.nextInt();

      //clear console
      System.out.print("\033[H\033[2J");  
      System.out.flush();
      
      if(choice == 1){ //opens the trip menu
        tripMenu(username);
      } //end if

      else if(choice == 2){ //opens the account settings menu
        accountSettings(username);
      } //end else if

      else if(choice == 3){ //if the user wants to sign out of account
        System.out.print(ANSI_GREEN + "Signing Out..." + ANSI_RESET); 
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        //returns to menu
        menu();
      }//end else if

      else{ //if user inputs invalid option
        System.out.print("\033[H\033[2J");  
        //clear console
        System.out.flush();
        System.out.println(ANSI_RED + "Please pick an available option" + ANSI_RESET);
        valid = false;
      } //end else
      
    }while(!valid); //while the user hasnt picked a correct option the loop repeats
  } //end account menu method


  public static void accountSettings(String username)throws IOException{ //accountSettings menu method

    //declare variables
    int choice;
    
    do{ //start do while loop

      //asks user what they would like to do
      System.out.println(ANSI_YELLOW + "\nWhat would you like to do?" + ANSI_RESET);
      System.out.println(ANSI_PURPLE + "1. Change Username\n2. Change Password\n3. Return" + ANSI_RESET);
      
      choice = in.nextInt();

      //if user wants to change username
      if(choice == 1){
        changeUser(username); //opens change username method
      }

      //if user wants to change password
      else if(choice == 2){
        changePass(username); //opens change password method
      }

      //if user wants to return to main menu
      else if(choice == 3){
        System.out.print(ANSI_GREEN + "Changes were saved..." + ANSI_RESET); 
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        accountMenu(username); //opens account menu
      }

      //if user inputted invalid option, returns them to account menu
      else{
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.print(ANSI_RED + "Invalid option, returned to account menu" + ANSI_RESET);  
        accountMenu(username); //opens account menu
      }
      
    }while(choice != 1 && choice != 2 && choice != 3); //while the choice is invalid the loop repeats
  } //end account settings method
  

  public static void changeUser(String username) throws IOException{ //allows user to change username

    //declare variables
    boolean check = false;
    //arraylist for usernames from accounts.csv
    ArrayList<String> users = new ArrayList<String>();
    String user = "";

    try{ //reads from accounts.csv and adds all usernames to users arraylist
      File myFile = new File("accounts.csv");
      Scanner myFileReader = new Scanner (myFile);
            
      while (myFileReader.hasNextLine()) { //while there is something to read
        String split = myFileReader.nextLine();
        String [] data = split.split(",");
          
        if(data.length==2){ //if there are 2 things on the line
          users.add(data[0]); //adds the username
        }
      } //end while
          
      myFileReader.close(); //close file reader
    }
    catch (FileNotFoundException a){ //catch
      System.out.println(ANSI_RED + "An error occurred." + ANSI_RESET);
      a.printStackTrace();
    }
    
    try{ //reads from accounts.csv and
      File myFile = new File("accounts.csv");
      Scanner myFileReader= new Scanner(myFile); 
      String information = ""; //empty string to hold new contents

      //clear buffer
      in.nextLine();
      while(myFileReader.hasNextLine()){
        String line = myFileReader.nextLine();
        String[] data = line.split(",");
        
        do{ //starts do while loop
          check = false; //resets check value

          //if the username equals the username on a line, uses that lines data
          if(data[0].equals(username)){
            do{
              //asks for user input
              System.out.println(ANSI_YELLOW + "What would you like as your username?" + ANSI_RESET);
              username = in.nextLine();

              if(username.length()<1){
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println(ANSI_RED + "Username cannot be empty\n" + ANSI_RESET);
              }//end if
            }while(username.length()<1);

            //loops through users arraylist and compares to see if new username exists
            for(int i = 0; i<users.size(); i++){
              if(users.get(i).equals(user)){ //if the username exists
                System.out.println(ANSI_RED + "This username already exists" + ANSI_RESET);
                check = true;
              }
            } //end for
            
            if (check == false){ //if it remains false after the loop, the username does not exist
              data[0] = user; //sets new username
            }
          } //end if
        }while(check); //while the username exists already

        //adds new username to information along with password
        information += data[0] + "," + data[1] + "\n";
      } //end while

      //rewrites line on accounts.csv
      FileOutputStream newFile = new FileOutputStream(myFile);
      newFile.write(information.getBytes());
      newFile.close();
      myFileReader.close();
    } //end try
    catch (FileNotFoundException a){ //catch
      System.out.println(ANSI_RED + "An error occurred." + ANSI_RESET);
      a.printStackTrace();
    }


    //renames the old users file to new username
    File file = new File(username + ".txt");
    File file1 = new File (user + ".txt");
    file.renameTo(file1);

    //clear console
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    System.out.println(ANSI_GREEN + "Username Successfully Changed" + ANSI_RESET);

    //returns to account settings menu
    accountSettings(user);
  } // end change username method


  
  public static void changePass(String username) throws IOException{ //allows user to change their password

    //declare variables
    boolean samePass = true;
    String password = "", password1 = "";
    
    try{ //start try loop
      //reads from accounts.csv
      File myFile = new File("accounts.csv");
      Scanner myFileReader= new Scanner(myFile); 
      String information = ""; //holds new information

      //clear buffer
      in.nextLine();
      
      while(myFileReader.hasNextLine()){ //while there is information to read from file
        String line = myFileReader.nextLine();
        String[] data = line.split(",");
        samePass = true; //resets value each loop
        
        if(data[0].equals(username)){ //if the usernames match continues

          do{
            //asks for users password
            System.out.println(ANSI_YELLOW + "What would you like as your password?" + ANSI_RESET);
            password1 = in.nextLine();

            if(password1.length()<1){
              System.out.print("\033[H\033[2J");  
              System.out.flush();
              System.out.println(ANSI_RED + "Password cannot be empty" + ANSI_RESET);
            }//end if
          }while(password1.length()<1);
          
          //confirms new password
          System.out.println(ANSI_YELLOW + "Confirm new Password: " + ANSI_RESET);
          password = in.nextLine();

          //if the two passwords match
          if(password.equals(password1)){
            //changes value of password
            System.out.println(ANSI_GREEN + "Password Successfully Changed" + ANSI_RESET);
            samePass = false;
            information += data[0] + "," + password + "\n"; //adds password to  information
          } //end if

          //if the passwords dont match
          else{
            //clear console
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println(ANSI_RED + "Passwords do not match please retry:" + ANSI_RESET);
          } //end else
        }//end if
          
        else{ //if the usernames dont match, doesnt change anything abou that line
          information += data[0] + "," + data[1] + "\n"; //saves old data back into information
        } //end else

      }//end while
      // writes information back into the file for each line
      FileOutputStream newFile = new FileOutputStream(myFile);
      newFile.write(information.getBytes());
      newFile.close();
      myFileReader.close();
    } //end try
    catch (FileNotFoundException a){//catch
      System.out.println(ANSI_RED + "An error occurred." + ANSI_RESET);
      a.printStackTrace();
    }

    //returns to account settings menu
    accountSettings(username);
  } //end Change password menu

  

  public static void tripMenu(String username) throws IOException{ //opens the trip menu 

    //declare variables
    int choice;
    boolean valid = true;

    do{ //starts do while loop

      //asks user what they want to do
      System.out.println(ANSI_YELLOW + "\nWhat would you like to do?" + ANSI_RESET);
      System.out.println(ANSI_PURPLE + "1. Book Trip\n2. Cancel Trip\n3. View Trip\n4. Return to Menu" + ANSI_RESET);

      choice = in.nextInt();

      //clear console
      System.out.print("\033[H\033[2J");  
      System.out.flush();

      //if they want to book a trip
      if(choice == 1){
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        //book a trip method
        bookTrip(username);
      } //end if

      //if they want to cancel existing trip
      else if(choice == 2){
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        //cancel trip method
        cancelTrip(username);
      } //end else if

      //if they want to view current trip details
      else if(choice == 3){
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        //view trip method
        viewTrip(username);
      } //end else if

      //if they want to return
      else if(choice == 4){
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        //returns to account menu method
        accountMenu(username);
      } //end else if

      //if user inputs invalid option
      else{
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println(ANSI_RESET + "Please pick an available option" + ANSI_RESET);
        valid = false;
      } //end else

    }while(!valid); //while the user hasnt picked a correct option
  } //end trip menu method


  
  public static void viewTrip(String username) throws IOException{ //allows user to see the details of their current trip
    //displays title for flight information
    System.out.println(ANSI_GREEN + "Flight Information\n------------------\n" + ANSI_RESET);

    //reads from the users file
    try (BufferedReader br = new BufferedReader(new FileReader(username + ".txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
       //prints out each line of the file
       System.out.println(ANSI_BLUE + line + ANSI_RESET);
      } //end while
    } //end try
    catch (IOException a) { //catch
      System.out.println(ANSI_RED + "An error occurred." + ANSI_RESET);
      a.printStackTrace();  
    }

    //Ask user when they would like to leave
    String answer;
    System.out.println(ANSI_YELLOW + "\nPress enter when you would like to return " + ANSI_RESET);
    in.nextLine();
    answer = in.nextLine();

    //if they want to leave
    if(answer.equals("")){
      //clear console
      System.out.print("\033[H\033[2J");  
      System.out.flush();
      //returns to trip menu
      tripMenu(username);
    } //end if

    //if the user input is invalid
    else{
      //clear console
      System.out.print("\033[H\033[2J");  
      System.out.flush();
      //returns to menu
      System.out.println(ANSI_RED + "Error: Invalid input\nReturning to menu" + ANSI_RESET);
      tripMenu(username);
    } //end else
  } //end view trip method


  
  public static void cancelTrip(String username) throws IOException{ //allows user to cancel their currently booked flight

    //declare variables
    String answer = "";

    //prints info about the flight they are attempting to cancel
    System.out.println(ANSI_GREEN + "Flight Information\n------------------\n" + ANSI_RESET);
    
    try (BufferedReader br = new BufferedReader(new FileReader(username + ".txt"))) { //reads from their user file and prints each line
     String line;
     while ((line = br.readLine()) != null) { //while there is stuff to read
      System.out.println(ANSI_BLUE + line + ANSI_RESET); //prints each line
      } //end while
    } //end try
    catch (IOException a) { //catch
      System.out.println(ANSI_RED + "An error occurred." + ANSI_RESET);
      a.printStackTrace();
    }

    //refresh buffer
    in.nextLine();
    do{ //start do loop
      
      //asks them if they are sure they want to cancel
      System.out.println(ANSI_YELLOW + "\nCancel this flight? (y or n)" + ANSI_RESET);
      answer = in.nextLine();

      //if they want to cancel
      if(answer.equals("y")){
        
        try{ //reads from user file and replaces all lines with empty statement
          FileWriter myWriter = new FileWriter(username + ".txt");
          myWriter.write(""); //empties the file
          myWriter.write("You Currently have no booked flights"); //rewrites file
          myWriter.close(); 
        } //end try
        catch(IOException a){ //catch
          System.out.println(ANSI_RESET + "An error occurred." + ANSI_RESET);
          a.printStackTrace();
        }

        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        
        System.out.println(ANSI_GREEN + "Flight Cancelled Successfully" + ANSI_RESET);
      } //end if

      else if(answer.equals("n")){ //if they change their mind and want to keep it booked
        //clear console
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println(ANSI_GREEN + "Returning to menu" + ANSI_RESET);
        break; 
      } //end else if

      else{ //if they input an invalid answer
        System.out.println(ANSI_RED + "\nPlease enter y or n: " + ANSI_RESET);
      } //end else
      
    }while(!answer.equals("y") && !answer.equals("n")); //while the answer is not y or n it continues

    //returns to trip menu
    tripMenu(username);
  } //end view trip method


  
  public static void bookTrip(String username) throws IOException{ //allows user to book a flight and plan their trip

    //declare variables
    String flight = "";
    int choice = 0;
    
    do{ //start do while loop
      //prints all options
      System.out.println(ANSI_GREEN + "Available Flights\n" + ANSI_RESET + ANSI_PURPLE + "\n1. Paris, France\n2. Milan, Italy\n3. Dhaka, Bangladesh\n4. Monte Carlo, Monaco\n5. New York, USA\n6. Bangkok, Thailand\n7. Hawaii, USA\n8. Cancun, Mexico\n9. Lisbon, Portugal\n10. Havana, Cuba" + ANSI_RESET);

      //asks where user wants to go
      System.out.println(ANSI_YELLOW + "\nWhere would you like to go?" + ANSI_RESET);
      choice = in.nextInt();

      //clear console
      System.out.print("\033[H\033[2J");  
      System.out.flush();

      //if their answer is valid
      if(choice >= 1 && choice <= 10){
        System.out.println(ANSI_GREEN + "Processing...\n" + ANSI_RESET);
      } //end if

      //if invalid answer
      else{ 
        System.out.println(ANSI_RED + "Please select an available option" + ANSI_RESET);
      } //end else
      
    }while(choice < 1 || choice > 10); //while answer is not between 1 and 10

    //reads from flights.csv to gather information about users choice
    try { //start try
      File myFile = new File("flights.csv");
      Scanner myFileReader = new Scanner(myFile);
      
      while (myFileReader.hasNextLine()) { //while theres info to read     
        String[] data = myFileReader.nextLine().split(",");

        //when it reaches the line containing the users destination, continue
        if(Integer.parseInt(data[0]) == choice){ 

          //declare variables
          String answer = "";
          String hotelStatus = "", hotel = "";
          int class1 = 0, cost = 0, hotelChoice = 0, year = 0, month = 0, day = 0;
          boolean yearCheck = true;
          
          
          while (!answer.equals("y") && !answer.equals("n")){ //while the answers asnwer is invalid, repeat

            //displays info about their trip
            System.out.println(ANSI_BLUE + "Location: " + data[1] + ", " + data[2] + "\nDistance: " + data[3] + "km\nEstimated Time: " + data[4] + " Hours, " + data[5] + " Minutes" + ANSI_RESET);

            //ask to confirm trip
            System.out.println(ANSI_YELLOW + "\nConfirm flight booking? (Answer with y or n): " + ANSI_RESET);
            in.nextLine();
            answer = in.nextLine();   

            //if they confirm the booking
            if(answer.equals("y")){
              //set cost of flight from file
              cost = Integer.parseInt(data[6]);

              //asks user what class they want to fly
              System.out.println(ANSI_YELLOW + "\nWhat class would you like to fly?" + ANSI_RESET);
              System.out.println(ANSI_PURPLE + "1. Economy - $" + cost + "\n2. Business - $" + cost*3 + "\n3. First Class - $" + cost*7 + ANSI_RESET);

              do{ //start do while loop
                //choose class
                class1 = in.nextInt();

                //if they want to fly business
                if(class1 == 2){
                  cost = cost*3; //cost * 3
                } //end if

                //else if they want to fly first class
                else if(class1 == 3){
                  cost = cost*7; //cost * 7
                } //end else if

                //if input is invalid
                else{
                  System.out.println(ANSI_RED + "Invalid option. Please select what class you would like to fly: " + ANSI_RESET);
                } //end else
              }while(class1 != 1 && class1 != 2 && class1 != 3); //while they input an invalid option

              //refresh buffer
              in.nextLine();

              //asks if they want to book a hotel for the trip
              System.out.println(ANSI_YELLOW + "\nWould you like to Book a spot in our complementary hotels for your trip? (y or n)" + ANSI_RESET);
              
              do{ //start do while loop
                //user input
                hotelStatus = in.nextLine();

                //if they want to book a hotel
                if(hotelStatus.equals("y")){ 
                  //asks which hotel they would like
                  System.out.println(ANSI_YELLOW + "\nWhich hotel Would you like to stay at?" + ANSI_RESET);
                  System.out.println(ANSI_PURPLE + "1. Grand Hotel (5 Star) - $1000\n2. Ordinary Hotel (3 Star) - $500\n3. Grim Hotel (1 Star) - $200" + ANSI_RESET);

                  do{ //start do while loop
                    //user input
                    hotelChoice = in.nextInt();

                    //if they choose grand hotel
                    if(hotelChoice == 1){
                      hotel = "Grand Hotel"; //set value
                      cost += 1500; //add to cost
                    } //end if

                    //if they choose ordinary hotel
                    else if(hotelChoice == 2){
                      hotel = "Ordinary Hotel"; //set value
                      cost += 800; //add to cost
                    } //end else if

                    //if they choose grim hotel
                    else if(hotelChoice == 3){
                      hotel = "Grim Hotel"; //set value
                      cost += 200; //add to cost
                    } //end else if

                    //if input is invalid
                    else{
                      System.out.println(ANSI_RED + "Please select an available hotel" + ANSI_RESET);
                    } //end else
                  }while(hotelChoice !=1 && hotelChoice != 2 && hotelChoice != 3); //while their input is invalid loop repeats
                } //end if

                //if they dont want to reserve a hotel
                else if(hotelStatus.equals("n")){
                  hotel = "No Hotel Booked"; //set value
                } //end else if

                //if input is invalid
                else{
                  System.out.println(ANSI_RED + "Please select if you want to book a hotel (y or n)" + ANSI_RESET);
                } //end else
                
              }while(!hotelStatus.equals("y") && !hotelStatus.equals("n")); //while the answers are invalid loop repeats

              do{ //start do loop
                //asks what year they want to go
                System.out.println(ANSI_YELLOW + "\nWhat year would you like to go on this trip? (bookings start 2023)" + ANSI_RESET);
                year = in.nextInt();
                //checks for leap year
                boolean leapYear = false;

                //if its a leap year leapYear = true
                if(year % 4 == 0){
                  yearCheck = false;
                  leapYear = true;
                } //end else

                //if year is after 2022
                if(year>2022){
                  yearCheck = false;
                  //checks if month is valid
                  boolean monthCheck = true;
                  
                  do{ //start do loop
                    //asks what month they want to go
                    System.out.println(ANSI_YELLOW + "\nWhat month would you like to go on this trip? (1-12)" + ANSI_RESET);
                    month = in.nextInt();

                    //if month is between 1 and 12
                    if(month >= 1 && month <=12){
                      monthCheck = false;
                      
                      //checks if day is valid
                      boolean dayCheck = true;
                      do{ //start do loop
                        //asks user what day they are going
                        System.out.println(ANSI_YELLOW + "\nWhat day of the month would you like to go on this trip?" + ANSI_RESET);
                        day = in.nextInt();

                        //if the day falls within the limits of the month provided, allows user to continue
                        if((month == 1 || month == 3 || month == 7 || month == 8 || month == 10 || month == 12) && (day <=31 && day>=1)){
                          dayCheck = false;
                        } //end if
  
                        else if((month == 4 || month == 5 || month == 6 || month == 9 || month == 11) && (day <=30 && day>=1)){
                          dayCheck = false;
                        } //end else if

                        //special conditions for February and leap year
                        else if(!leapYear && ((month == 2 && (day <= 28 && day >=1)))){
                          dayCheck = false;
                        } //end else if

                        else if(leapYear && ((month == 2 && (day <= 29 && day >=1)))){
                          dayCheck = false;
                        } //end else if

                        //if day is invalid
                        else{ 
                          System.out.println(ANSI_RED + "\nThat date does not exist" + ANSI_RESET);
                        } //end else
                        
                      }while(dayCheck); //while the day is invalid
                    } //end if

                    //if month is invalid
                    else{
                      System.out.println(ANSI_RED + "\nPlease eneter a month between 1-12" + ANSI_RESET);
                    } //end else
                    
                  }while(monthCheck); //while month is invalid
                } //end if

                //if year is invalid
                else{
                  System.out.println(ANSI_RED + "\nInvalid Year" + ANSI_RESET);
                } //end else
                
              }while(yearCheck); //while year is invalid

              //adds all the data to the String flight
              flight += "Destination: " + data[1] + ", " + data[2] + "\nDistance: " + data[3] + " kilometers \nEst. Flight Time: " + data[4] + " hours, " + data[5] + " minutes" + "\nDate: " + year + "/" + month +"/"+ day +"\nCost: $" + cost + "\nHotel Status: " + hotel;

              //clear console
              System.out.print("\033[H\033[2J");  
              System.out.flush();
              System.out.println(ANSI_GREEN + "Confirmed" + ANSI_RESET);

              //writes flight into the users file
              try{
                FileWriter myWriter = new FileWriter(username + ".txt");
                myWriter.write(flight);
                myWriter.close();
              } //end try
              catch(IOException a){ //catch
                System.out.println(ANSI_RED + "An error occurred." +  ANSI_RESET);
                a.printStackTrace();
              }
              break;
            } //end if

            //if they want to cancel their flight booking
            else if(answer.equals("n")){ 
              //clear console
              System.out.print("\033[H\033[2J");  
              System.out.flush();
              System.out.println( ANSI_GREEN + "Flight Cancelled" + ANSI_RESET);
              break;
            } //end else if

            //if input is invalid
            else{
              //clear console
              System.out.print("\033[H\033[2J");  
              System.out.flush();
              System.out.println(ANSI_RED + "Please confirm your booking\n" +  ANSI_RESET);
            } //end else
          } //end while
        } //end if
      } //end while
      //close user file
      myFileReader.close();
      
    } //end try
    catch (FileNotFoundException a) { //catch
      System.out.println(ANSI_RED + "An error occurred." + ANSI_RESET);
      a.printStackTrace();
    }

    //return to trip menu
    tripMenu(username);
    
  } //end book trip method
}//end main class
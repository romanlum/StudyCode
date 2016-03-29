package swe4.dal;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import swe4.dal.Person;
import swe4.dal.PersonDao;
import swe4.dal.PersonDaoJdbc;

public class PhoneBookBL {
  // private static final String CONNECTION_STRING =
  // "jdbc:derby://localhost/PhoneBookDb";
  private static final String CONNECTION_STRING = "jdbc:mysql://localhost/PhoneBookDb";
  private static final String USER_NAME = "root";
  private static final String PASSWORD = null;
  

  public static String promptFor(BufferedReader in, String p) {
    System.out.print(p + "> ");
    try {
      return in.readLine();
    }
    catch (Exception e) {
      return promptFor(in, p);
    } // try/catch
  } // prompt

  public static void main(String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));;
    String userCmd;

    try (PersonDao personDao = new PersonDaoJdbc(CONNECTION_STRING, USER_NAME,PASSWORD)) {	

      System.out.println();
      System.out.println("Currently. " + personDao.getCount() + " stored");
  
      System.out.println();
      System.out.println("valid commands: quit, list, find, insert, update and delete");
      System.out.println();
  
      userCmd = promptFor(in, "");
      while (!userCmd.equals("quit")) {
  
        if (userCmd.equals("list")) {
        	personDao.getAll().stream().forEach(System.out::println);
  
        }
        else if (userCmd.equals("find")) {
  
          String lastName = promptFor(in, "  last name ");
          personDao.get(lastName).stream().forEach(System.out::println);
  
        }
        else if (userCmd.equals("insert")) {
  
          Person p =
              new Person(promptFor(in, "  first name   "), promptFor(in, "  last name    "),
                  promptFor(in, "  address      "), promptFor(in, "  phone number "));
          personDao.store(p);
        }
        else if (userCmd.equals("update")) {
  
          int id = Integer.parseInt(promptFor(in, "  id "));
          Person p = personDao.get(id);
          if(p != null) {
        	  System.out.println("Person: "+p);
        	  p.setAddress( promptFor(in, "  new Address      "));
        	  personDao.update(p);
          } else {
        	  System.out.println("Person not found");
          }
          
              
  
        }
        else if (userCmd.equals("delete")) {
  
          int id = Integer.parseInt(promptFor(in, "  id "));
          personDao.delete(id);
        }
        else {
          System.out.println("ERROR: invalid command");
        } // else
  
        userCmd = promptFor(in, "");
  
      } // while  
      System.out.println();
    }
    catch(SQLException e) {
    	while(e!= null) {
    		System.out.println(e);
    		e = e.getNextException();
    	}
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    

  } // main

} // PhoneBookApplicationDAL


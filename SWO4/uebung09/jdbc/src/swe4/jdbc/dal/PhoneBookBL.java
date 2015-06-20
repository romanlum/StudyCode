package swe4.jdbc.dal;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import swe4.jdbc.dal.Person;
import swe4.jdbc.dal.PersonDao;
import swe4.jdbc.dal.PersonDaoJdbc;

public class PhoneBookBL {

  private static final String CONNECTION_STRING = "jdbc:mysql://localhost/PhoneBookDb";
  private static final String USER_NAME = "root";
  private static final String PASSWORD = null;

  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  public static String prompt(String p) {
    System.out.print(p + "> ");
    try {
      return in.readLine();
    } catch (Exception e) {
      return prompt(p);
    }
  }

  public static void main(String[] args) {
    String userCmd;

    try (PersonDao personDao = new PersonDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD)) {

      System.out.printf("%ncurrently %d entries in phone book%n", personDao.getCount());
      System.out.printf("%nvalid commands: quit, list, find, insert, update and delete%n");

      userCmd = prompt("");
      while (!userCmd.equals("quit")) {

        if (userCmd.equals("list")) {
          for (Person p : personDao.getAll()) {
            System.out.println(p);
          }

        } else if (userCmd.equals("find")) {
          String lastName = prompt("last name ");
          Collection<Person> c = personDao.get(lastName);
          for (Person p : c) {
            System.out.println(p);
          }
          if (c.isEmpty())
            System.out.printf("  no entries with last name %s found%n",  lastName);

        } else if (userCmd.equals("insert")) {
          Person p = new Person(
              prompt("first name   "),
              prompt("last name    "),
              prompt("address      "),
              prompt("phone number "));
          personDao.store(p);
          System.out.printf("inserted new person <%s>%n", p);

        } else if (userCmd.equals("update")) {
          int id = Integer.parseInt(prompt("id "));
          Person p = personDao.get(id);
          if (p == null) {
            System.out.println("  no entry with id " + id);
          } else {
            System.out.println(p);
            p.setAddress(prompt("new address "));
            personDao.update(p);
          }

        } else if (userCmd.equals("delete")) {
          int id = Integer.parseInt(prompt("id "));
          personDao.delete(id);

        } else {
          System.out.println("ERROR: invalid command");
        }

        userCmd = prompt("");

      }
      System.out.println();
    } catch (SQLException e) {
      while (e != null) {
        System.out.println(e);
        e = e.getNextException();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  } 

}


package swe4.jdbc.simple_dal;

import java.io.*;
import java.sql.*;

public class PhoneBookApplication {

  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  

  private static String prompt(String p) {
    System.out.print(p + "> ");
    try {
      return in.readLine();
    } catch (Exception e) {
      return prompt(p);
    } // try/catch

  } // prompt

  public static void main(String args[]) {
    String allowedCmds = "commands: quit, list, find, insert, update, delete, meta";
    String userCmd;

    Connection connection = null;

    System.out.printf("%n%s%n", allowedCmds);

    userCmd = prompt("");

    while (!userCmd.equals("quit")) {

      if (userCmd.equals("insert")) {

      } else if (userCmd.equals("list")) {

      } else if (userCmd.equals("find")) {

      } else if (userCmd.equals("update")) {

      } else if (userCmd.equals("delete")) {

      } else if (userCmd.equals("meta")) {

      } else {
        System.out.println("ERROR: invalid command; " + allowedCmds);
      } // else

      userCmd = prompt("");
    } // while

  } // main
} // PhoneBookApplication

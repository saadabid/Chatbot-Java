/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saad
 */
public class Database {
   
      FileOutputStream out = null;

      public void convohistory(String str) throws IOException
      {
        try {
    BufferedWriter out = new BufferedWriter(new FileWriter("history.txt",true));
    out.append(str);  
    out.newLine();
    out.close();
}
catch (IOException e)
{
    System.out.println("Exception ");

}

      }   
     
}
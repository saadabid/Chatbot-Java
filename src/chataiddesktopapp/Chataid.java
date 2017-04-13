/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chataiddesktopapp;

import dal.Database;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.regex.Pattern.quote;
/**
 *
 * @author Saad
 */
public class Chataid extends JFrame implements KeyListener {

        JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(32,35);
	JTextArea input=new JTextArea(2,35);
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
        
        String[][] userAwnser = {
                {"hi"},
		{"Welcome to Go Shop"},
		{"I want to buy wallet"},
		{"Mention product Code"},
		{"GSW-501"},
		{"Product price: Rs 1200"},
                {"any delivery charges?"},
                {"Delivery charges Rs 150"},
		{"how to order?"},
                {"To book your order mention product code, color, name, contact \n\t\tnumber and complete address."},
        };
        String defaultMsg = "Thanks for messaging us. We try to be as responsive \nas possible. We'll respond to you soon.";
        Database db = new Database();
        
        public static void main(String[] args)
    {
        new Chataid();
    }
    
        public Chataid()
    {
        super("CHATAID");
        setSize(400,600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dialog.setEditable(false);
	input.addKeyListener(this);
	input.setBackground(Color.WHITE);
        dialog.setBackground(new Color(228, 235, 255));
        dialog.setForeground(Color.darkGray);
	p.add(scroll);
	p.add(input);
	p.setBackground(new Color(0, 164, 255));
	add(p);
		
	setVisible(true);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_ENTER)
        {
            input.setEditable(false);
            String user_input = input.getText();
            input.setText("");
            addText("\n      User :   " + user_input);
            try {
                db.convohistory("\nUser :   " + user_input);
            } catch (IOException ex) {
                Logger.getLogger(Chataid.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
          //  System.out.println(userAwnser[0][0]);
//            if("hi".equals(user_input))
//            {
//                System.out.println("saad");
//                addText("\nBot : \t" + Arrays.toString(userAwnser[1]));
//            }
            byte response =0;
            	int j=0;//which group we're checking
			for(int i=0 ; i<userAwnser.length;i++)
                        {
				if(userAwnser[j][0].equals(user_input))
                                {
                                    addText("\n\n\t\t       "+userAwnser[j+1][0]+"   : Bot");
                                    try {
                                        db.convohistory("\nBOT :  "+userAwnser[j+1][0]);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Chataid.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    response=2;
                                }
                                j++;
                                //response=1;
                                //System.out.println(j);
			}
			
			//-----default--------------
	if(response != 2)
        {
            addText("\n\n\t\t       "+defaultMsg+"   : Bot");
                try {
                    db.convohistory("\n-->BOT\t"+defaultMsg);
                } catch (IOException ex) {
                    Logger.getLogger(Chataid.class.getName()).log(Level.SEVERE, null, ex);
                }
	
        }
	    		
            addText("\n");
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_ENTER)
        {
            input.setEditable(true);
            //input.setText("");
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addText(String query)
    {
        dialog.setText(dialog.getText() + query);
    }
    
   
}

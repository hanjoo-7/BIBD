package TCP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChatClientApp extends JFrame{
   
	public ChatClientApp() {
	       
	    }
    /**
     * 메인 함수
     * @param args
     */
    public static void main(String[] args) {
    	new ChatClient();
    	
    }

}




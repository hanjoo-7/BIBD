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
    private static final String SERVER_IP = "192.168.0.16";
    private static final int SERVER_PORT = 5000;
    
	public ChatClientApp() {
	        
	        setTitle("버튼으로 띄우는 새로운 창");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        JPanel MainContainer = new JPanel();
	        setContentPane(MainContainer);
	        
	        JButton OpenWindow = new JButton("새 창 띄우기");
	        
	        OpenWindow.addActionListener(new ActionListener() {
	            // 만들어진 버튼 "새 창 띄우기"에 버튼이 눌러지면 발생하는 행동을 정의
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	                new newWindow(); // 클래스 newWindow를 새로 만들어낸다
	            }
	            
	        });
	        
	        MainContainer.add(OpenWindow);
	        
	        setSize(300,100);
	        setResizable(false);
	        setVisible(true);
	    }



    
    
    public static void main(String[] args) {
        String name = null;
        Scanner scanner = new Scanner(System.in);
        
        new ChatClientApp();
        
        while( true ) {

            System.out.println("대화명을 입력하세요.");
            System.out.print(">>> ");
            name = scanner.nextLine();

            if (name.isEmpty() == false ) {
                break;
            }

            System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
        }

        scanner.close();

        Socket socket = new Socket();
        try {
            socket.connect( new InetSocketAddress(SERVER_IP, SERVER_PORT) );
            consoleLog("채팅방에 입장하였습니다.");
            new ChatWindow(name, socket).show();

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String request = "join:" + name + "\r\n";
            pw.println(request);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void consoleLog(String log) {
        System.out.println(log);
    }
}

class newWindow extends JFrame {
    // 버튼이 눌러지면 만들어지는 새 창을 정의한 클래스
    newWindow() {
        setTitle("새로 띄운 창");
        // 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("새 창을 띄우는데 성공!");
        
        NewWindowContainer.add(NewLabel);
        
        setSize(300,100);
        setResizable(false);
        setVisible(true);
    }
}


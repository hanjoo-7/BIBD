package TCP;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;

public class CalendarController {
	
	String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	//String jdbcServer = "com.microsoft.sqlerver.jdbc.SQLServerDriver";
	String jdbcUrl = "jdbc:mysql://bibd.cw1fnax2ura3.ap-northeast-2.rds.amazonaws.com/couple_bus?&serverTimezone=Asia/Seoul&useSSL=false";
	Connection conn;
	String id="sodam";
	String pw="12341234";

	Calendar cal;
	
	Statement stmt;
	ResultSet rs;
	

	
	
	private CalendarView v;
	public CalendarController(CalendarView v) {
		this.v=v;
		
		
		cal = new GregorianCalendar();
		conDB();
		gridInit();
		showTable();
		hidebtn();
		
		v.addButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj==v.btnBefore) {
					v.centerPanel.removeAll();
					calInput(-1);
					gridInit();
					GridLayout gridLayout1 = new GridLayout(7,7);
					v.centerPanel.setLayout(gridLayout1);
					showTable();
					hidebtn();
					v.lblYear.setText(v.year+" 년 ");
					v.lblMonth.setText(v.month+" 월 ");
					
				}
				else if(obj==v.btnAfter) {
					v.centerPanel.removeAll();
					calInput(1);
					gridInit();
					GridLayout gridLayout1 = new GridLayout(7,7);
					v.centerPanel.setLayout(gridLayout1);
					showTable();
					hidebtn();
					v.lblYear.setText(v.year+" 년 ");
					v.lblMonth.setText(v.month+" 월 ");
				}
				
				else if(obj==v.btnBefore2) {
					v.centerPanel.removeAll();
					calInput(-2);
					gridInit();
					GridLayout gridLayout1 = new GridLayout(7,7);
					v.centerPanel.setLayout(gridLayout1);
					showTable();
					hidebtn();
					v.lblYear.setText(v.year+" 년 ");
					v.lblMonth.setText(v.month+" 월 ");
				}
				else if(obj==v.btnAfter2) {
					v.centerPanel.removeAll();
					calInput(2);
					gridInit();
					GridLayout gridLayout1 = new GridLayout(7,7);
					v.centerPanel.setLayout(gridLayout1);
					showTable();
					hidebtn();
					v.lblYear.setText(v.year+" 년 ");
					v.lblMonth.setText(v.month+" 월 ");
				}
				
			}
		});
	}
	
	public void gridInit() {
		for(int i =0;i<v.days.length;i++) {
    		v.centerPanel.add(v.calBtn[i] = new JButton(v.days[i]));
    		v.calBtn[i].setBackground(Color.white);
    	}
    	for(int i = v.days.length;i<49;i++) {
    		v.centerPanel.add(v.calBtn[i]= new JButton(""));
    		//v.calBtn.addActionListener();
    	}
	}
	
	 public void showTable() {
	    	cal.set(Calendar.YEAR, v.year);
	    	cal.set(Calendar.MONTH, (v.month-1));
	    	cal.set(Calendar.DATE,1);
	    	int dayofWeek = cal.get(Calendar.DAY_OF_WEEK);
	    	
	    	
	    	int j = 0;
	    	int hopping = 0;
	    	v.calBtn[0].setForeground(Color.RED);  
	    	v.calBtn[6].setForeground(Color.blue);
	    	
	    	
	    	
	    	for(int i=cal.getFirstDayOfWeek();i<dayofWeek;i++) {j++;}
	    	// 일요일부터 그달의 첫시작 요일까지 빈칸으로 셋팅하기 위해서 
	    	
	    	hopping = j;
	    	for(int i=0;i<hopping;i++) {
	    		v.calBtn[i+7].setText("");
	    	}
	    	for(int i=cal.getMinimum(Calendar.DAY_OF_MONTH);
	    			i<=cal.getMaximum(Calendar.DAY_OF_MONTH);i++) {
	    		cal.set(Calendar.DATE, i);
	    		if(cal.get(Calendar.MONTH)!=v.month-1) {
	    			break;
	    		}
	    		v.calBtn[i+hopping+6].setBackground(Color.white);
	    		v.todays = i;
	    		checkday();
	    		if(v.memoday==1) {
	    			v.calBtn[i+6+hopping].setForeground(Color.pink);
	    			
	    		}
	    		else {
	    			v.calBtn[i+6+hopping].setForeground(Color.black);
	    			if((i+hopping-1)%7==0){
	    				v.calBtn[i+6+hopping].setForeground(Color.red);
	    			}
	    			if((i+hopping)%7==0) {
	    				v.calBtn[i+6+hopping].setForeground(Color.blue);
	    			}
	    		}
	    		v.calBtn[i+6+hopping].setText((i)+"");
	    		
	    	}
	    	
	    	
	    }
	 
	 public void checkday() {
		 String sql="select * from calendar where year="+v.year+
				 " and month="+v.month+" and day="+v.todays+";";
		 try {
			 rs=stmt.executeQuery(sql);
			 if(rs.next()) {
				 
				 v.memoday =1;
			 }
			 else v.memoday = 0;
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void hidebtn() {
		 for(int i=0;i<v.calBtn.length;i++) {
			 if((v.calBtn[i].getText()).equals(""))
				 v.calBtn[i].setEnabled(false);
		 }
	 }
	 
	 
	 
	 public void conDB() {
		 try {
		 Class.forName(jdbcDriver);
		 conn = DriverManager.getConnection(jdbcUrl,id,pw);
		 stmt = conn.createStatement();
		 }catch(Exception e) { e.printStackTrace(); }
	 }
	 public void calInput(int data) {
		 if(data ==-1||data ==1) {
			 v.month +=(data);
			 if(v.month<=0) {
				 v.month = 12;
				 v.year--;
			 }
			 else if(v.month>12) {
				 v.month =1;
				 v.year++;
			 }
		 }
		 else if(data == 2) {v.year ++;}
		 else if(data ==-2 ) {v.year--;}
			 
	 }
	 
	 
	 
	 
	 
	 public static void main(String arg[]) {
	    	new CalendarController(new CalendarView());
	    	
	    }
}

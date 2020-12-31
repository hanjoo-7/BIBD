package TCP;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CalendarCRUD extends JFrame {

	CalendarController CC;
	CalendarView V;
	JButton btnInsert,btnDelete,btnUpdate;
	
	
	CalendarCRUD(CalendarController CC,CalendarView V){
		this.CC = CC;
		
		
	}
}

package img_gallery;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ImgGalleryView extends JFrame {

	protected JPanel pbutton;
	protected JScrollPane scroll;
	protected JButton b1, b2, b3, b4;
	protected FileDialog dialog;
	protected JComboBox cb;
	protected JLabel simg;
	protected JPanel p1;

	
	public ImgGalleryView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("이미지 갤러리");
		b1 = new JButton("게시물 업로드");
		b2 = new JButton("수정");
		b3 = new JButton("삭제");
		b4 = new JButton("조회");
		cb = new JComboBox();
		p1 = new JPanel();

		simg = new JLabel();

		pbutton = new JPanel();
		pbutton.setLayout(new GridLayout(1, 3));
		pbutton.add(b1);
		pbutton.add(b2);
		pbutton.add(b3);
		this.add(pbutton, BorderLayout.PAGE_START);

		p1.add(cb);
		p1.add(b4);
		this.add(p1, BorderLayout.PAGE_END);
		this.add(simg, BorderLayout.CENTER);

		dialog = new FileDialog(this, "업로드 할 게시물 선택", FileDialog.LOAD);

		setSize(700, 500);
		setVisible(true);
	}

	public void addActionListener(ActionListener listener) {
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		b3.addActionListener(listener);
		b4.addActionListener(listener);
		cb.addActionListener(listener);
	}

	// 이미지의 제목을 입력받고 제목업로드
	class Insimgname extends JFrame {
		protected JTextField nameField;
		protected JButton b5;
		protected String name;
		public Insimgname() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			nameField = new JTextField(20);
			setTitle("제목입력창");
			b5 = new JButton("제목입력");
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			c.add(new JLabel("제목을 입력하세요 "));
			c.add(nameField);
			c.add(b5);
			setSize(300, 150);
			setVisible(true);
		}
		public void addActionListener(ActionListener listener) {
			b5.addActionListener(listener);	
		}
	}

	// 이미지의 제목을 입력받고 해당하는 이미지를 삭제할 창
	class Delimgname extends JFrame {
		protected JTextField dnameField;
		protected JButton b6;
		protected String name;
		public Delimgname() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dnameField = new JTextField(20);
			setTitle("제목입력창");
			b6 = new JButton("삭제");
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			c.add(new JLabel("제목을 입력하세요 "));
			c.add(dnameField);
			c.add(b6);
			setSize(300, 150);
			setVisible(true);
		}
		public void addActionListener(ActionListener listener) {
			b6.addActionListener(listener);	
		}
	}
	
	//업데이트
	class updateImgname extends JFrame {
		protected JTextField n1,n2;
		protected JButton b7;
			public updateImgname() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			n1 = new JTextField(20);
			n2 = new JTextField(20);
			setTitle("수정");
			b7 = new JButton("수정");
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			c.add(new JLabel("기존 제목을 입력하세요 "));
			c.add(n1);
			c.add(new JLabel("바꿀 제목을 입력하세요"));
			c.add(n2);
			c.add(b7);
			setSize(300, 150);
			setVisible(true);
		}
		public void addActionListener(ActionListener listener) {
			b7.addActionListener(listener);	
		}
	}
}




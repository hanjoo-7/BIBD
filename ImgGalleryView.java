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
		setTitle("�̹��� ������");
		b1 = new JButton("�Խù� ���ε�");
		b2 = new JButton("����");
		b3 = new JButton("����");
		b4 = new JButton("��ȸ");
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

		dialog = new FileDialog(this, "���ε� �� �Խù� ����", FileDialog.LOAD);

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

	// �̹����� ������ �Է¹ް� ������ε�
	class Insimgname extends JFrame {
		protected JTextField nameField;
		protected JButton b5;
		protected String name;
		public Insimgname() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			nameField = new JTextField(20);
			setTitle("�����Է�â");
			b5 = new JButton("�����Է�");
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			c.add(new JLabel("������ �Է��ϼ��� "));
			c.add(nameField);
			c.add(b5);
			setSize(300, 150);
			setVisible(true);
		}
		public void addActionListener(ActionListener listener) {
			b5.addActionListener(listener);	
		}
	}

	// �̹����� ������ �Է¹ް� �ش��ϴ� �̹����� ������ â
	class Delimgname extends JFrame {
		protected JTextField dnameField;
		protected JButton b6;
		protected String name;
		public Delimgname() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dnameField = new JTextField(20);
			setTitle("�����Է�â");
			b6 = new JButton("����");
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			c.add(new JLabel("������ �Է��ϼ��� "));
			c.add(dnameField);
			c.add(b6);
			setSize(300, 150);
			setVisible(true);
		}
		public void addActionListener(ActionListener listener) {
			b6.addActionListener(listener);	
		}
	}
	
	//������Ʈ
	class updateImgname extends JFrame {
		protected JTextField n1,n2;
		protected JButton b7;
			public updateImgname() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			n1 = new JTextField(20);
			n2 = new JTextField(20);
			setTitle("����");
			b7 = new JButton("����");
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			c.add(new JLabel("���� ������ �Է��ϼ��� "));
			c.add(n1);
			c.add(new JLabel("�ٲ� ������ �Է��ϼ���"));
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




package img_gallery;

import java.sql.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImgGalleryDAO {
	String jdbcDriver = "com.mysql.jdbc.Driver";
	//String jdbcUrl = "jdbc:mysql://localhost/javadb?serverTimezone=Asia/Seoul&useSSL=false";
	String jdbcUrl = "jdbc:mysql://bibd.cw1fnax2ura3.ap-northeast-2.rds.amazonaws.com:3306/couple_bus";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	int r;
	String sql;

	public void connectDB() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl, "sodam", "12341234");
			//conn = DriverManager.getConnection(jdbcUrl, "root", "0718");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeDB() {
		try {
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �̹��� ���ε�
	public boolean uploadContent(String name,String p ) {
		sql = "insert into content(content_title, content_file ,couple_seq) values(?, ?, ? )";
		connectDB();
		try {
			pstmt = conn.prepareStatement(sql);
			File file = new File(p);
			InputStream is = new FileInputStream(file);
			int fileSize = (int) file.length();
			byte[] buffer = new byte[fileSize];
			is.read(buffer);
			pstmt.setString(1, name);
			pstmt.setBytes(2, buffer);
			pstmt.setInt(3, 1);
			pstmt.executeUpdate();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return r != 0 ? true : false;
	}

	public boolean delimg(String a) {
		sql = "delete from content where content_title = ?";
		connectDB();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return r != 0 ? true : false;
	}
	
	public boolean updateimg(String s1, String s2) {
		sql = "update content set content_title = ? where content_title = ?";
		connectDB();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, s1);
			pstmt.setString(1,s2);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return r != 0 ? true : false;
	}
	
	// db�� �ִ� ��� ����� �����ͼ� ����Ʈ�� ��ȯ
	// ���ڿ��� ����Ʈ�� �Ͽ� ������� �޾ƿ��� ����
	public ArrayList<String> getlist() {
		ArrayList<String> datas = new ArrayList<String>();
		try {
			sql = "select * from content";
			connectDB();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				datas.add(rs.getString("content_title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		// ������ �� �������� �ִ��� Ȯ�ο�
		//for (int i = 0; i < datas.size(); i++) {
		//	System.out.println(datas.get(i));
		//}
		return datas;
	}

	// �޺��ڽ����� ������ �Խù��� �������� �޾ư� ������ ���� �ش� �̹��� ���
	public Image getIMG(String selected) {
		InputStream input;
		FileOutputStream output;
		Image img = null;
		try {
			sql = "select * from content where content_title = ?" ;
			connectDB();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selected);
			ResultSet rs = pstmt.executeQuery();
			File theFile = new File("test.png");
			output = new FileOutputStream(theFile);
			if (rs.next()) {
				// db�� �÷�
				input = rs.getBinaryStream("content_file");
				byte buffer[] = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}
				String path = theFile.getAbsolutePath();
				ImageIcon myImage = new ImageIcon(path);
				img = myImage.getImage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return img;
	}
}

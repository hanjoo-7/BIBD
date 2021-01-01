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

	// 이미지 업로드
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
	
	// db에 있는 모든 목록을 가져와서 리스트로 반환
	// 문자열로 리스트로 하여 제목들을 받아와줄 예정
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
		// 제목을 잘 가져오고 있는지 확인용
		//for (int i = 0; i < datas.size(); i++) {
		//	System.out.println(datas.get(i));
		//}
		return datas;
	}

	// 콤보박스에서 선택한 게시물을 문지열로 받아고 쿼리를 통해 해당 이미지 출력
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
				// db의 컬럼
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

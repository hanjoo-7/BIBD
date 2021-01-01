package img_gallery;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class imgController {
	
}


private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {
	PreparedStatement pst;
	try {
		con = Connection.getConnection();
		pst = con.prepareStatement("Select * From phototable");
		Resultset rs = pst.executeQuery();
		
		while(rs.next) {
			jComboBox1.addItem(rs.getString("pid"));
		}catch(Exception ex) {
			System.out.println("Button Load + " :: + ex);
		}
	}
}


private void jcomboBoxChange(java.awt.event.ItemEvent evt) {
	ss= "" + jComboBox1.getSelectedItem();
	InputStream input;
	FileOutputStream output;
	try {
		con = Connection.getConnection();
		PreparedStatement pst = con.prepareStatement("select * from phototable where pid =" + ss);
		Resultset rs = pst.executeQuery();
		File theFile = new File("studyviral_in.png");
		output = new FileOutputStream(theFile);
		
		if(rs.next()) {
			input = rs.getBianaryStream("photo");
			byte buffer[] = new byte[1024];
			while(input.read(buffer) > 0) {
				ouput.write(buffer);
			}
		String path = theFile.getAbsolutePath();
		ImageIcon myImage = new ImageIcon(path);
		Image img = myImage.getImage();
		Image newImg = img.getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = newImageIcon(newImg);
		jlabel12.setIcon(image);
		}
	}catch (Exception ex) {
		System.out.println("" + ex);
	}
}
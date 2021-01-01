package img_gallery;

import java.awt.FileDialog;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class ImgGalleryController {

	private ImgGalleryDAO imgGalleryDAO;
	private ImgGalleryView imgGalleryView;

	public ImgGalleryController() {
		imgGalleryDAO = new ImgGalleryDAO();
		imgGalleryView = new ImgGalleryView();
		imgGalleryView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();	
				//�Խù� ���ε�
				if (obj == imgGalleryView.b1) {
					ImgGalleryView.Insimgname insimgname = imgGalleryView.new Insimgname();
					insimgname.addActionListener(new ActionListener() {
						 public void actionPerformed(ActionEvent e) {
							 String name = new String(insimgname.nameField.getText());
							 insimgname.dispose();
							 if (!name.equals("") ) {
			                       FileDialog dialog = new FileDialog(imgGalleryView, "���ε� �� �Խù� ����", FileDialog.LOAD);
			                       dialog.setVisible(true);
			                       String path = dialog.getDirectory() + dialog.getFile();
			                       imgGalleryDAO.uploadContent(name, path);
						 }
					}			
				});
				}
				// ����
				else if (obj == imgGalleryView.b2) {
					ImgGalleryView.updateImgname uimgname = imgGalleryView.new updateImgname();
					uimgname.addActionListener(new ActionListener() {
						 public void actionPerformed(ActionEvent e) {
							 String name1 = new String(uimgname.n1.getText());
							 String name2 = new String(uimgname.n2.getText());
							 uimgname.dispose();
							 imgGalleryDAO.updateimg(name1,name2);
						 }
						 });
						 }
				

				// ����
				else if (obj == imgGalleryView.b3) {
					ImgGalleryView.Delimgname delimgname = imgGalleryView.new Delimgname();
					delimgname.name = delimgname.dnameField.getText();
					delimgname.addActionListener(new ActionListener() {
						 public void actionPerformed(ActionEvent e) {
							 String name = new String(delimgname.dnameField.getText());
							 delimgname.dispose();
							 imgGalleryDAO.delimg(name);
					 }
				});
				}

				// ��ȸ
				else if (obj == imgGalleryView.b4) {
					imgGalleryView.cb.removeAllItems();
					ArrayList<String> combo = new ArrayList<String>();
					combo = imgGalleryDAO.getlist();
					for (int i = 0; i < combo.size(); i++) {
						imgGalleryView.cb.addItem(combo.get(i));
					}
				}
				
				// �޺��ڽ�
				else if (obj == imgGalleryView.cb) {
					Image selctedimg;
					String value = imgGalleryView.cb.getSelectedItem().toString();
					selctedimg = imgGalleryDAO.getIMG(value);
					// DAO���� �޾ƿ� �̹����� Icon���� ��ȯ
					Image newImg = selctedimg.getScaledInstance(500, 300, Image.SCALE_SMOOTH);
					ImageIcon image = new ImageIcon(newImg);
					imgGalleryView.simg.setIcon(image);
				}
			}
		});
	}

	public static void main(String[] args) {
		ImgGalleryController imgGalleryController = new ImgGalleryController();
	}

}

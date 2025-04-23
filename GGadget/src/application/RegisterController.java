package application;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hql.information.entity.Member;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

public class RegisterController {
	@FXML
	private TextField text_email;
	@FXML
	private TextField text_password;
	@FXML
	private TextField text_lname;
	@FXML
	private TextField text_fname;
	@FXML
	private TextField text_phone;

	Main m = new Main();
	// Event Listener on Button.onAction
	@FXML
	public void btnRegister(ActionEvent event) throws IOException{
		if(text_fname.getText()!="" && text_lname.getText()!="" && text_email.getText()!="" && text_phone.getText()!="" && text_password.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Member.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Member mem = new Member(text_fname.getText(),text_lname.getText(),text_phone.getText()
						,text_email.getText(),text_password.getText());
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(mem);
				//commit transaction
				session.getTransaction().commit();
			}finally {
				Alert a = new Alert(Alert.AlertType.NONE,"ลงทะเบียนสำเร็จ",ButtonType.OK);
				a.show();
				m.changeScene("/fxml/Login.fxml");
				factory.close();
			}
		}else {
			Alert a = new Alert(Alert.AlertType.NONE,"กรุณาใส่ข้อมูลให้ครบทุกช่อง",ButtonType.OK);
			a.show();
		}
	}
}

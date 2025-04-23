package application;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.information.entity.Member;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class RegisterController {
	@FXML
	private TextField text_fname;
	@FXML
	private TextField tect_lname;
	@FXML
	private TextField text_email;
	@FXML
	private TextField text_gender;
	@FXML
	private TextField text_age;
	@FXML
	private TextField text_phone;
	@FXML
	private TextField text_password;
	
	Main m = new Main();

	// Event Listener on Button.onAction
	@FXML
	public void btnRegister(ActionEvent event) throws IOException{
		if(text_fname.getText()!="" && tect_lname.getText()!="" && text_email.getText()!="" && text_gender.getText()!="" && text_age.getText()!="" 
				&& text_phone.getText()!="" && text_password.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Member.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Member member = new Member(text_fname.getText(),tect_lname.getText(),text_email.getText()
						,text_gender.getText(),Integer.parseInt(text_age.getText()),text_phone.getText(),text_password.getText());
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(member);
				//commit transaction
				session.getTransaction().commit();
			}finally {
				Alert a = new Alert(Alert.AlertType.NONE,"ลงทะเบียนสำเร็จ",ButtonType.OK);
				a.show();
				m.changeSceneToLogin("Login.fxml");
				factory.close();
			}
		}else {
			Alert a = new Alert(Alert.AlertType.NONE,"กรุณาใส่ข้อมูลให้ครบทุกช่อง",ButtonType.OK);
			a.show();
		}
	}
}

package application;

import com.information.entity.UserLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.information.entity.Laptop;
import com.information.entity.Member;

import javafx.event.ActionEvent;

public class LoginController {
	@FXML
	private TextField text_email_m;
	@FXML
	private TextField text_password_m;
	
	Main m = new Main();
	// Event Listener on Button.onAction
	@FXML
	public void btnLogin(ActionEvent event) throws IOException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Member.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		List<Member>emptylist = Collections.emptyList();  
		List<Member>member = session.createQuery("from Member").list();
		member = session.createQuery("from Member a where a.email_member='"+text_email_m.getText()+"' AND a.password_member='"+text_password_m.getText()+"'").list();
		
		for(Member mem : member) {
			 setUserName(mem.getFirstname_member(),mem.getLastname_member(),mem.getEmail_member(),mem.getTelephone_member());
		}
		
		if(member.equals(emptylist)) {
			Alert a = new Alert(Alert.AlertType.NONE,"รหัสผ่านไม่ถูกต้อง",ButtonType.OK);
			a.show();
			System.out.println("Failed");
		}else {
			Alert a = new Alert(Alert.AlertType.NONE,"เข้าสู่ระบบสำเร็จ",ButtonType.OK);
			a.show();
			System.out.println("Success");
			m.changeSceneToSystem("ShopPhone.fxml");
		}
		//commit transaction
		session.getTransaction().commit();
		factory.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnRegister(ActionEvent event) throws IOException{
		m.changeSceneToLogin("Register.fxml");
	}
	
	public void setUserName(String f,String l,String e,String t) throws IOException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserLogin.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		UserLogin user = new UserLogin(f,l,e,t);
			
		//start a transaction
		session = factory.getCurrentSession();
		session.beginTransaction();
			
		//save 
		session.save(user);
			
		//commit transaction
		session.getTransaction().commit();
		factory.close();
	}
}

package application;

import com.information.entity.Cart;
import com.information.entity.User;
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

import javafx.event.ActionEvent;

public class LoginController {
	@FXML
	private TextField text_email;
	@FXML
	private TextField text_password;

	Main m = new Main();
	// Event Listener on Button.onAction
	@FXML
	public void btnSignIn(ActionEvent event) throws IOException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		List<User>emptylist = Collections.emptyList();  
		List<User>user = session.createQuery("from User").list();
		user = session.createQuery("from User u where u.email_user='"+text_email.getText()+"' AND u.password_user='"+text_password.getText()+"'").list();
		
		if((text_email.getText()).equals("admin.com")&&(text_password.getText()).equals("admin")) {
			m.changeScene("/fxml/Cartoon.fxml");
			Alert a = new Alert(Alert.AlertType.NONE,"เข้าสู่ระบบ Admin สำเร็จ!",ButtonType.OK);
			a.show();
		}
		else if(user.equals(emptylist)) {
			Alert a = new Alert(Alert.AlertType.NONE,"รหัสผ่านไม่ถูกต้อง",ButtonType.OK);
			a.show();
			System.out.println("Failed");
		}else {
			Alert a = new Alert(Alert.AlertType.NONE,"เข้าสู่ระบบสำเร็จ",ButtonType.OK);
			a.show();
			clearCart();
			System.out.println("Success");
			m.changeScene("/fxml/ShopCartoon.fxml");
		}
		//commit transaction
		session.getTransaction().commit();
		factory.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnSignUp(ActionEvent event) throws IOException{
		m.changeScene("/fxml/SignUp.fxml");
	}
	
	public void clearCart() throws IOException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
		Session session = factory.getCurrentSession();	
		//start a transaction
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		//Clear all database user_login
		session.createSQLQuery("truncate table cart").executeUpdate();
		
		//commit transaction
		session.getTransaction().commit();
		factory.close();
	}
}

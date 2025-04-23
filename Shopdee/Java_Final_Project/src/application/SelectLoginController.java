package application;

import javafx.fxml.FXML;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.information.entity.Cart;
import com.information.entity.UserLogin;

import javafx.event.ActionEvent;

public class SelectLoginController {
	Main m = new Main();
	// Event Listener on Button.onAction
	@FXML
	public void member_login(ActionEvent event) throws IOException{
		clearCart();
		clearUser();
		m.changeSceneToLogin("Login.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void admin_login(ActionEvent event) throws IOException{
		m.changeSceneToLogin("LoginAdmin.fxml");
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
	
		m.changeSceneToLogin("Login.fxml");
	}
	
	public void clearUser() throws IOException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserLogin.class).buildSessionFactory();
		Session session = factory.getCurrentSession();	
		//start a transaction
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		//Clear all database user_login
		session.createSQLQuery("truncate table user_login").executeUpdate();
		
		//commit transaction
		session.getTransaction().commit();
		factory.close();
	}
}

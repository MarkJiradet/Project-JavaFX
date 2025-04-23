package application;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import com.information.entity.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class LoginAdminController {
	@FXML
	private TextField text_email_a;
	@FXML
	private TextField text_password_a;
	
	Main m = new Main();
	// Event Listener on Button.onAction
	@FXML
	public void btnLoginAdmin(ActionEvent event) throws IOException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Admin.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		List<Admin>emptylist = Collections.emptyList();  
		List<Admin>admins = session.createQuery("from Admin").list();
		admins = session.createQuery("from Admin a where a.email_admin='"+text_email_a.getText()+
				"' AND a.password_admin='"+text_password_a.getText()+"'").list();
		
		if(admins.equals(emptylist)) {
			Alert a = new Alert(Alert.AlertType.NONE,"รหัสผ่านไม่ถูกต้อง",ButtonType.OK);
			a.show();
			System.out.println("Failed");
		}else {
			Alert a = new Alert(Alert.AlertType.NONE,"เข้าสู่ระบบสำเร็จ",ButtonType.OK);
			a.show();
			System.out.println("Success");
			m.changeSceneToSystem("Smartphone.fxml");
		}
		//commit transaction
		session.getTransaction().commit();
		factory.close();
	}
}


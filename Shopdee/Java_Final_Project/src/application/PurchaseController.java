package application;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.information.entity.Invoice;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class PurchaseController implements Initializable{
	@FXML
	private Label LabelName;
	@FXML
	private Label LabelPhone;
	@FXML
	private Label LabelEmail;
	@FXML
	private Label LabelTotal;
	@FXML
	private Label LabelTID;

	Main m = new Main();
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Invoice.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Invoice>inv = session.createQuery("from Invoice").list();
			for(Invoice i:inv) {
				LabelName.setText(i.getInvoice_fname()+" "+i.getInvoice_lname());
				LabelPhone.setText(i.getInvoice_phone());
				LabelEmail.setText(i.getInvoice_email());
				LabelTotal.setText(String.valueOf(i.getInvoice_total()));
				LabelTID.setText(String.valueOf(i.getInvoice_id()));
			}

			//commit transaction
			session.getTransaction().commit();
			factory.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// Event Listener on Button.onAction
}

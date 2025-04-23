package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.information.entity.Invoice;
import javafx.scene.control.Label;


public class InvoiceController implements Initializable{
	@FXML
	private Label label_id;
	@FXML
	private Label label_total;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Invoice.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Invoice>invoice = session.createQuery("from Invoice").list();
			for(Invoice inv:invoice) {
				label_id.setText(String.valueOf(inv.getId_invoice()));
				label_total.setText(String.valueOf(inv.getPrice_invoice()));
			}
			session.getTransaction().commit();
			factory.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

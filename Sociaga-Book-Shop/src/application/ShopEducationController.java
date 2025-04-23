package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.information.entity.Education;
import com.information.entity.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class ShopEducationController implements Initializable{
	@FXML
	private TableView <Education>table;
	@FXML
	private TableColumn<Education,String> col_id;
	@FXML
	private TableColumn<Education,String> col_name;
	@FXML
	private TableColumn<Education,Integer> col_price;
	@FXML
	private Label label_id;
	@FXML
	private Label label_name;
	@FXML
	private Label label_author;
	@FXML
	private Label label_publisher;
	@FXML
	private Label label_price;
	@FXML
	private TextField text_search;

	Main m = new Main();
	Cart cart;
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Education.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Education>ed = session.createQuery("from Education").list();
			ObservableList<Education>edList = FXCollections.observableArrayList(ed);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Education,String>("e_id"));
			col_name.setCellValueFactory(new PropertyValueFactory<Education,String>("e_name"));
			col_price.setCellValueFactory(new PropertyValueFactory<Education,Integer>("e_price"));
			
			table.setItems(edList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void btnAddToCart(ActionEvent event) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
				
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
				
			//save the student object
			session.save(cart);
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Alert a = new Alert(Alert.AlertType.NONE,"เพิ่ม "+cart.getName_cart()+" เข้าสู่ตะกร้าสำเร็จ",ButtonType.OK);
			a.show();
			initialize(null,null);
			factory.close();
		}
	}
	
	@FXML
	public void rowClick(MouseEvent event) throws IOException{
		Education click = table.getSelectionModel().getSelectedItem();
		label_id.setText(String.valueOf(click.getE_id()));
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Education.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Education>ed = session.createQuery("from Education").list();
		ed = session.createQuery("from Education e where e.e_id='"+label_id.getText()+"'").list();
		
		for(Education e : ed) {
			 label_id.setText("ID : "+e.getE_id());
			 label_name.setText("Name : "+e.getE_name());
			 label_author.setText("Author : "+e.getE_author());
			 label_publisher.setText("Publisher : "+e.getE_publisher());
			 label_price.setText("Price : "+e.getE_price());
			 cart = new Cart(e.getE_id(),e.getE_name(),e.getE_author(),e.getE_publisher(),e.getE_price());
		}
		
		session.getTransaction().commit();
		factory.close();
	}
	@FXML
	public void refresh(MouseEvent event) {
		text_search.clear();
		initialize(null,null);
	}
	
	@FXML
	public void btnSearch(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Education.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Education>ed = session.createQuery("from Education").list();
			ed = session.createQuery("from Education e where e.e_id='"+text_search.getText()+
					"' OR e.e_name='"+text_search.getText()+"' OR e.e_price='"+text_search.getText()+"'").list();
			
			ObservableList<Education>edList = FXCollections.observableArrayList(ed);
			table.setItems(FXCollections.observableArrayList(edList));
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}

	@FXML
	public void btnCart(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Cart.fxml");
	}
	@FXML
	public void btnCartoon(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopCartoon.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnNovel(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopNovel.fxml");
	}
}

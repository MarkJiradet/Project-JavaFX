package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.information.entity.Cartoon;
import com.information.entity.Cart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ShopCartoonController implements Initializable{
	@FXML
	private TableView<Cartoon> table;
	@FXML
	private TableColumn<Cartoon,String> col_id;
	@FXML
	private TableColumn<Cartoon,String> col_name;
	@FXML
	private TableColumn<Cartoon,Integer> col_price;
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
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cartoon.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Cartoon>cartoon = session.createQuery("from Cartoon").list();
			ObservableList<Cartoon>cartoonList = FXCollections.observableArrayList(cartoon);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Cartoon,String>("c_id"));
			col_name.setCellValueFactory(new PropertyValueFactory<Cartoon,String>("c_name"));
			col_price.setCellValueFactory(new PropertyValueFactory<Cartoon,Integer>("c_price"));
			
			table.setItems(cartoonList);
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
		Cartoon click = table.getSelectionModel().getSelectedItem();
		label_id.setText(String.valueOf(click.getC_id()));
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cartoon.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Cartoon>cartoon = session.createQuery("from Cartoon").list();
		cartoon = session.createQuery("from Cartoon c where c.c_id='"+label_id.getText()+"'").list();
		
		for(Cartoon c : cartoon) {
			 label_id.setText("ID : "+c.getC_id());
			 label_name.setText("Name : "+c.getC_name());
			 label_author.setText("Author : "+c.getC_author());
			 label_publisher.setText("Publisher : "+c.getC_publisher());
			 label_price.setText("Price : "+c.getC_price());
			 cart = new Cart(c.getC_id(),c.getC_name(),c.getC_author(),c.getC_publisher(),c.getC_price());
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cartoon.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Cartoon>cartoon = session.createQuery("from Cartoon").list();
			cartoon = session.createQuery("from Cartoon c where c.c_id='"+text_search.getText()+
					"' OR s.c_name='"+text_search.getText()+"' OR s.c_price='"+text_search.getText()+"'").list();
			
			ObservableList<Cartoon>cartoonList = FXCollections.observableArrayList(cartoon);
			table.setItems(FXCollections.observableArrayList(cartoonList));
			
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
	public void btnNovel(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopNovel.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnEducation(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopEducation.fxml");
	}
}

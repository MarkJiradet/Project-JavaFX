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

import com.information.entity.Novel;
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

public class ShopNovelController implements Initializable{
	@FXML
	private TableView<Novel>table;
	@FXML
	private TableColumn<Novel,String> col_id;
	@FXML
	private TableColumn <Novel,String>col_name;
	@FXML
	private TableColumn <Novel,Integer>col_price;
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
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Novel.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Novel>novel = session.createQuery("from Novel").list();
			ObservableList<Novel>novelList = FXCollections.observableArrayList(novel);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Novel,String>("n_id"));
			col_name.setCellValueFactory(new PropertyValueFactory<Novel,String>("n_name"));
			col_price.setCellValueFactory(new PropertyValueFactory<Novel,Integer>("n_price"));
			
			table.setItems(novelList);
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
		Novel click = table.getSelectionModel().getSelectedItem();
		label_id.setText(String.valueOf(click.getN_id()));
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Novel.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Novel>novel = session.createQuery("from Novel").list();
		novel = session.createQuery("from Novel n where n.n_id='"+label_id.getText()+"'").list();
		
		for(Novel n : novel) {
			 label_id.setText("ID : "+n.getN_id());
			 label_name.setText("Name : "+n.getN_name());
			 label_author.setText("Author : "+n.getN_author());
			 label_publisher.setText("Publisher : "+n.getN_publisher());
			 label_price.setText("Price : "+n.getN_price());
			 cart = new Cart(n.getN_id(),n.getN_name(),n.getN_author(),n.getN_publisher(),n.getN_price());
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Novel.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Novel>novel = session.createQuery("from Novel").list();
			novel = session.createQuery("from Novel n where n.n_id='"+text_search.getText()+
					"' OR n.n_name='"+text_search.getText()+"' OR n.n_price='"+text_search.getText()+"'").list();
			
			ObservableList<Novel>novelList = FXCollections.observableArrayList(novel);
			table.setItems(FXCollections.observableArrayList(novelList));
			
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
	public void btnEducation(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopEducation.fxml");
	}
}

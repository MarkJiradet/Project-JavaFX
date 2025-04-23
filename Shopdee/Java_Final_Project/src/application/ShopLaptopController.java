package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.information.entity.Laptop;
import com.information.entity.UserLogin;
import com.information.entity.Cart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class ShopLaptopController implements Initializable{
	@FXML
	private Label name_user;
	@FXML
	private TableView <Laptop>tableLaptop;
	@FXML
	private TableColumn<Laptop,String> col_id_l;
	@FXML
	private TableColumn <Laptop,String>col_name_l;
	@FXML
	private TableColumn <Laptop,String>col_brand_l;
	@FXML
	private TableColumn <Laptop,String>col_size_l;
	@FXML
	private TableColumn<Laptop,Integer> col_price_l;
	
	Main m = new Main();
	String id_l;
	String name_l;
	String brand_l;
	String size_l;
	String price_l;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Laptop.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Laptop>laptop = session.createQuery("from Laptop").list();
			ObservableList<Laptop>laptopList = FXCollections.observableArrayList(laptop);
			session.getTransaction().commit();
			
			col_id_l.setCellValueFactory(new PropertyValueFactory<Laptop,String>("id_laptop"));
			col_name_l.setCellValueFactory(new PropertyValueFactory<Laptop,String>("name_laptop"));
			col_brand_l.setCellValueFactory(new PropertyValueFactory<Laptop,String>("brand_laptop"));
			col_size_l.setCellValueFactory(new PropertyValueFactory<Laptop,String>("size_laptop"));
			col_price_l.setCellValueFactory(new PropertyValueFactory<Laptop,Integer>("price_laptop"));
			
			tableLaptop.setItems(laptopList);
			
			setLabel();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	@FXML
	public void rowSelect(MouseEvent event) {
		Laptop click = tableLaptop.getSelectionModel().getSelectedItem();
		id_l = String.valueOf(click.getId_laptop());
		name_l = String.valueOf(click.getName_laptop());
		brand_l =String.valueOf(click.getBrand_laptop());
		size_l = String.valueOf(click.getSize_laptop());
		price_l = String.valueOf(click.getPrice_laptop());
	}
	
	@FXML
	public void btnAddToCart(ActionEvent event) {
		if(id_l!=null && name_l!=null && brand_l!=null && size_l!=null && price_l!=null) {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Cart cart = new Cart(id_l,name_l,brand_l,size_l,Integer.parseInt(price_l));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(cart);
				
				//commit transaction
				session.getTransaction().commit();
			}finally {
				Alert a = new Alert(Alert.AlertType.NONE,"เพิ่ม "+name_l+" เข้าสู่ตะกร้าสำเร็จ",ButtonType.OK);
				a.show();
				factory.close();
			}
		}
	}
	
	@FXML
	public void refreshTable(MouseEvent event) {
		id_l = null;
		name_l = null;
		brand_l = null;
		size_l = null;
		price_l = null;
		
		initialize(null,null);
	}
	
	@FXML
	public void btnCart(ActionEvent event) throws IOException{
		m.changeSceneToSystem("Cart.fxml");
	}

	@FXML
	public void btnPhone(ActionEvent event) throws IOException{
		m.changeSceneToSystem("ShopPhone.fxml");
	}

	@FXML
	public void btnGame(ActionEvent event) throws IOException{
		m.changeSceneToSystem("ShopGame.fxml");
	}
	
	public void setLabel() throws IOException {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserLogin.class).buildSessionFactory();
		Session session = factory.getCurrentSession();	
		//start a transaction
		session = factory.getCurrentSession();
		session.beginTransaction();
		List<UserLogin>user = session.createQuery("from UserLogin").list();
		for(UserLogin use : user) {
			name_user.setText(use.getUser_fname()+" "+use.getUser_lname());
		}
		
		//commit transaction
		session.getTransaction().commit();
		factory.close();
	}

}

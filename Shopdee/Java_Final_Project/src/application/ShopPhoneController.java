package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.information.entity.Smartphone;
import com.information.entity.UserLogin;
import com.information.entity.Cart;
import com.information.entity.Member;

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

public class ShopPhoneController implements Initializable{
	@FXML
	private Label name_user;
	@FXML
	private TableView <Smartphone>tablePhone;
	@FXML
	private TableColumn<Smartphone,String> col_id_p;
	@FXML
	private TableColumn <Smartphone,String>col_name_p;
	@FXML
	private TableColumn <Smartphone,String>col_brand_p;
	@FXML
	private TableColumn<Smartphone,String> col_size_p;
	@FXML
	private TableColumn <Smartphone,Integer>col_price_p;
	
	Main m = new Main();
	String id_p;
	String name_p;
	String brand_p;
	String size_p;
	String price_p;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Smartphone.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Smartphone>smartphone = session.createQuery("from Smartphone").list();
			ObservableList<Smartphone>smartPhoneList = FXCollections.observableArrayList(smartphone);
			session.getTransaction().commit();
			
			col_id_p.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("id_phone"));
			col_name_p.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("name_phone"));
			col_brand_p.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("brand_phone"));
			col_size_p.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("size_phone"));
			col_price_p.setCellValueFactory(new PropertyValueFactory<Smartphone,Integer>("price_phone"));
			tablePhone.setItems(smartPhoneList);
			
			setLabel();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	public void btnAddToCart(ActionEvent event) {
		if(id_p!=null && name_p!=null && brand_p!=null && size_p!=null && price_p!=null) {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Cart cart = new Cart(id_p,name_p,brand_p,size_p,Integer.parseInt(price_p));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(cart);
				
				//commit transaction
				session.getTransaction().commit();
			}finally {
				Alert a = new Alert(Alert.AlertType.NONE,"เพิ่ม "+name_p+" เข้าสู่ตะกร้าสำเร็จ",ButtonType.OK);
				a.show();
				factory.close();
			}
		}
	}
	@FXML
	public void rowSelect(MouseEvent event) {
		Smartphone click = tablePhone.getSelectionModel().getSelectedItem();
		id_p = String.valueOf(click.getId_phone());
		name_p = String.valueOf(click.getName_phone());
		brand_p =String.valueOf(click.getBrand_phone());
		size_p = String.valueOf(click.getSize_phone());
		price_p = String.valueOf(click.getPrice_phone());
	}
	
	@FXML
	public void refreshTable(MouseEvent event) {
		id_p = null;
		name_p = null;
		brand_p = null;
		size_p = null;
		price_p = null;
		
		initialize(null,null);
	}
	
	@FXML
	public void btnCart(ActionEvent event) throws IOException{
		m.changeSceneToSystem("Cart.fxml");
	}

	@FXML
	public void btnLaptop(ActionEvent event) throws IOException{
		m.changeSceneToSystem("ShopLaptop.fxml");
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

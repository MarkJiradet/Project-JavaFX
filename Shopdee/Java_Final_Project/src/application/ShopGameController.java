package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.information.entity.GamingGear;
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

public class ShopGameController implements Initializable{
	@FXML
	private Label name_user;
	@FXML
	private TableView <GamingGear>tableGame;
	@FXML
	private TableColumn<GamingGear,String> col_id_g;
	@FXML
	private TableColumn <GamingGear,String>col_name_g;
	@FXML
	private TableColumn<GamingGear,String> col_brand_g;
	@FXML
	private TableColumn <GamingGear,String>col_type_g;
	@FXML
	private TableColumn<GamingGear,Integer> col_price_g;
	
	Main m = new Main();
	String id_g;
	String name_g;
	String brand_g;
	String type_g;
	String price_g;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(GamingGear.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<GamingGear>game = session.createQuery("from GamingGear").list();
			ObservableList<GamingGear>gameList = FXCollections.observableArrayList(game);
			session.getTransaction().commit();
			
			col_id_g.setCellValueFactory(new PropertyValueFactory<GamingGear,String>("id_game"));
			col_name_g.setCellValueFactory(new PropertyValueFactory<GamingGear,String>("name_game"));
			col_brand_g.setCellValueFactory(new PropertyValueFactory<GamingGear,String>("brand_game"));
			col_type_g.setCellValueFactory(new PropertyValueFactory<GamingGear,String>("type_game"));
			col_price_g.setCellValueFactory(new PropertyValueFactory<GamingGear,Integer>("price_game"));
			tableGame.setItems(gameList);
			
			setLabel();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	public void btnAddToCart(ActionEvent event) {
		if(id_g!=null && name_g!=null && brand_g!=null && type_g!=null && price_g!=null) {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Cart cart = new Cart(id_g,name_g,brand_g,type_g,Integer.parseInt(price_g));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(cart);
				
				//commit transaction
				session.getTransaction().commit();
			}finally {
				Alert a = new Alert(Alert.AlertType.NONE,"เพิ่ม "+name_g+" เข้าสู่ตะกร้าสำเร็จ",ButtonType.OK);
				a.show();
				factory.close();
			}
		}
	}
	
	@FXML
	public void rowSelect(MouseEvent event) {
		GamingGear click = tableGame.getSelectionModel().getSelectedItem();
		id_g = String.valueOf(click.getId_game());
		name_g = String.valueOf(click.getName_game());
		brand_g =String.valueOf(click.getBrand_game());
		type_g = String.valueOf(click.getType_game());
		price_g = String.valueOf(click.getPrice_game());
	}

	// Event Listener on AnchorPane.onMouseClicked
	@FXML
	public void refreshTable(MouseEvent event) {
		id_g = null;
		name_g = null;
		brand_g = null;
		type_g = null;
		price_g = null;
		
		initialize(null,null);
	}
	
	@FXML
	public void btnPhone(ActionEvent event) throws IOException{
		m.changeSceneToSystem("ShopPhone.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnLaptop(ActionEvent event)  throws IOException{
		m.changeSceneToSystem("ShopLaptop.fxml");
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void btnCart(ActionEvent event)  throws IOException{
		m.changeSceneToSystem("Cart.fxml");
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

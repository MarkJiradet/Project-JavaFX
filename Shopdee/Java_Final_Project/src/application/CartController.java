package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.information.entity.Invoice;
import com.information.entity.Cart;
import com.information.entity.UserLogin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.TableColumn;

public class CartController implements Initializable{
	
	@FXML
	private TableView<Cart>table;
	@FXML
	private TableColumn<Cart,Integer>col_id;
	@FXML
	private TableColumn<Cart,String>col_id_c;
	@FXML
	private TableColumn<Cart,String> col_name_c;
	@FXML
	private TableColumn <Cart,String>col_brand_c;
	@FXML
	private TableColumn<Cart,String> col_ts_c;
	@FXML
	private TableColumn<Cart,Integer> col_price_c;
	@FXML
	private Label name_user;
	@FXML
	private Label Label_total;
	
	Main m = new Main();
	int id;
	
	String fname,lname,telephone,email;
	Image img = new Image("/picture/Logo.png");
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			
			
			List<Cart>cart = session.createQuery("from Cart").list();
			ObservableList<Cart>cartList = FXCollections.observableArrayList(cart);
	
			col_id.setCellValueFactory(new PropertyValueFactory<Cart,Integer>("id_cart"));
			col_id_c.setCellValueFactory(new PropertyValueFactory<Cart,String>("id_product"));
			col_name_c.setCellValueFactory(new PropertyValueFactory<Cart,String>("name_product"));
			col_brand_c.setCellValueFactory(new PropertyValueFactory<Cart,String>("brand_product"));
			col_ts_c.setCellValueFactory(new PropertyValueFactory<Cart,String>("ts_product"));
			col_price_c.setCellValueFactory(new PropertyValueFactory<Cart,Integer>("price_product"));
			table.setItems(cartList);
			
			session.getTransaction().commit();
			setTotal();
			setLabel();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void btnDelete(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Cart cart = session.get(Cart.class,id);
			
			session.delete(cart);
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			initialize(null,null);
			Alert a = new Alert(Alert.AlertType.NONE,"ลบรายการสินค้าสำเร็จ",ButtonType.OK);
			a.show();
			factory.close();
		}
	}
	
	@FXML
	public void rowSelect(MouseEvent event) {
		Cart click = table.getSelectionModel().getSelectedItem();
		id = Integer.parseInt(String.valueOf(click.getId_cart()));
	}
	// Event Listener on AnchorPane.onMouseClicked
	@FXML
	public void refreshTable(MouseEvent event) {
		initialize(null, null);
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
			fname = use.getUser_fname();
			lname = use.getUser_lname();
			telephone = use.getUser_telephone();
			email = use.getUser_email();
			}
		
		//commit transaction
		session.getTransaction().commit();
		factory.close();
	}
	
	public void setTotal() throws IOException {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cart.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Cart>cart = session.createQuery("from Cart").list();
			
			int sum = 0;
			for(Cart c : cart) {
				 sum += c.getPrice_product();
				 System.out.println(c);
			}
			
			Label_total.setText(String.valueOf(sum));

			//commit transaction
			session.getTransaction().commit();
		}finally {
			initialize(null,null);
			factory.close();
		}
	}
	@FXML
	public void btnPurchase(ActionEvent event) throws IOException{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Invoice.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Invoice inv = new Invoice(fname,lname,telephone,email,Integer.parseInt(Label_total.getText()));
			
			session.save(inv);
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Parent root = FXMLLoader.load(getClass().getResource("Purchase.fxml"));
			Stage stg = new Stage();
			stg.setScene(new Scene(root));
			stg.getIcons().add(img);
			stg.setTitle("Shopdee");
			stg.show();
			initialize(null,null);
			factory.close();
		}
	}
	
	@FXML
	public void btnPhone(ActionEvent event) throws IOException{
		m.changeSceneToSystem("ShopPhone.fxml");
	}
	
	@FXML
	public void btnLaptop(ActionEvent event) throws IOException{
		m.changeSceneToSystem("ShopLaptop.fxml");
	}
	
	@FXML
	public void btnGame(ActionEvent event) throws IOException{
		m.changeSceneToSystem("ShopGame.fxml");
	}

}

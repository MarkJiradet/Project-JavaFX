package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hql.information.entity.Keyboard;
import com.hql.information.entity.Cart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class ShopKeyboardController implements Initializable{
	@FXML
	private TableView <Keyboard>table;
	@FXML
	private TableColumn<Keyboard,String> col_id;
	@FXML
	private TableColumn<Keyboard,String> col_name;
	@FXML
	private TableColumn<Keyboard,String> col_brand;
	@FXML
	private TableColumn<Keyboard,Integer> col_price;

	Main m = new Main();
	Cart c;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Keyboard.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Keyboard>k = session.createQuery("from Keyboard").list();
			ObservableList<Keyboard>kList = FXCollections.observableArrayList(k);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Keyboard,String>("id_k"));
			col_name.setCellValueFactory(new PropertyValueFactory<Keyboard,String>("name_k"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Keyboard,String>("brand_k"));
			col_price.setCellValueFactory(new PropertyValueFactory<Keyboard,Integer>("price_k"));
			
			table.setItems(kList);
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
			
			session.save(c);
		
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Alert a = new Alert(Alert.AlertType.NONE,"เพิ่มเข้าสู่ตะกร้าสำเร็จ",ButtonType.OK);
			a.show();
			initialize(null,null);
			factory.close();
		}
	}
	
	@FXML
	public void refresh(MouseEvent event) {
		initialize(null, null);
	}
	
	@FXML
	public void rowClick(MouseEvent event) {
		Keyboard click = table.getSelectionModel().getSelectedItem();
		c = new Cart(click.getId_k(),click.getName_k(),click.getBrand_k(),click.getPrice_k());
	}

	@FXML
	public void btnCart(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Cart.fxml");
	}
	@FXML
	public void btnMonitor(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopMonitor.fxml");
	}

	@FXML
	public void btnHeadphone(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopHeadphone.fxml");
	}

	@FXML
	public void btnMouse(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopMouse.fxml");
	}
	
	@FXML
	public void btnFlash(ActionEvent event) throws IOException {
		m.changeScene("/fxml/ShopFlashdrive.fxml");
	}
}

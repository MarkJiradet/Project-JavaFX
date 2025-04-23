package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hql.information.entity.Headphone;
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

public class ShopHeadphoneController implements Initializable{
	@FXML
	private TableView <Headphone>table;
	@FXML
	private TableColumn<Headphone,String> col_id;
	@FXML
	private TableColumn<Headphone,String> col_name;
	@FXML
	private TableColumn<Headphone,String> col_brand;
	@FXML
	private TableColumn<Headphone,Integer> col_price;

	Main m = new Main();
	Cart c;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Headphone.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Headphone>h = session.createQuery("from Headphone").list();
			ObservableList<Headphone>hList = FXCollections.observableArrayList(h);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Headphone,String>("id_h"));
			col_name.setCellValueFactory(new PropertyValueFactory<Headphone,String>("name_h"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Headphone,String>("brand_h"));
			col_price.setCellValueFactory(new PropertyValueFactory<Headphone,Integer>("price_h"));
			
			table.setItems(hList);
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
		Headphone click = table.getSelectionModel().getSelectedItem();
		c = new Cart(click.getId_h(),click.getName_h(),click.getBrand_h(),click.getPrice_h());
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
	public void btnMouse(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopMouse.fxml");
	}

	@FXML
	public void btnKeyboard(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopKeyboard.fxml");
	}
	
	@FXML
	public void btnFlash(ActionEvent event) throws IOException {
		m.changeScene("/fxml/ShopFlashdrive.fxml");
	}
}

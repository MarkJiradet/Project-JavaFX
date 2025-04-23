package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hql.information.entity.Monitor;
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

public class ShopMonitorController implements Initializable{
	@FXML
	private TableView<Monitor>table;
	@FXML
	private TableColumn <Monitor,String>col_id;
	@FXML
	private TableColumn<Monitor,String> col_name;
	@FXML
	private TableColumn <Monitor,String>col_brand;
	@FXML
	private TableColumn <Monitor,Integer>col_price;

	Main m = new Main();
	Cart c;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Monitor.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Monitor>monitor = session.createQuery("from Monitor").list();
			ObservableList<Monitor>monitorList = FXCollections.observableArrayList(monitor);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Monitor,String>("id_mo"));
			col_name.setCellValueFactory(new PropertyValueFactory<Monitor,String>("name_mo"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Monitor,String>("brand_mo"));
			col_price.setCellValueFactory(new PropertyValueFactory<Monitor,Integer>("price_mo"));
			
			table.setItems(monitorList);
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
		Monitor click = table.getSelectionModel().getSelectedItem();
		c = new Cart(click.getId_mo(),click.getName_mo(),click.getBrand_mo(),click.getPrice_mo());
	}
	@FXML
	public void btnCart(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Cart.fxml");
	}
	@FXML
	public void btnMouse(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopMouse.fxml");
	}

	@FXML
	public void btnHeadphone(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopHeadphone.fxml");
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

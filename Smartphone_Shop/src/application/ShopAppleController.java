package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.information.hql.entity.Apple;
import com.information.hql.entity.Cart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class ShopAppleController implements Initializable{
	@FXML
	private TableView <Apple>table;
	@FXML
	private TableColumn <Apple,String>col_id;
	@FXML
	private TableColumn <Apple,String>col_name;
	@FXML
	private TableColumn <Apple,String>col_brand;
	@FXML
	private TableColumn <Apple,String>col_size;
	@FXML
	private TableColumn<Apple,Integer> col_price;
	
	Main m = new Main();
	Cart c;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Apple.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Apple>apple = session.createQuery("from Apple").list();
			ObservableList<Apple>appleList = FXCollections.observableArrayList(apple);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Apple,String>("id_a"));
			col_name.setCellValueFactory(new PropertyValueFactory<Apple,String>("name_a"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Apple,String>("brand_a"));
			col_size.setCellValueFactory(new PropertyValueFactory<Apple,String>("size_a"));
			col_price.setCellValueFactory(new PropertyValueFactory<Apple,Integer>("price_a"));
			
			table.setItems(appleList);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	@FXML
	public void btnAddCart(ActionEvent event) {
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
			Alert a = new Alert(Alert.AlertType.NONE,"เพิ่ม "+c.getName_c()+" เข้าสู่ตะกร้าสำเร็จ",ButtonType.OK);
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
		Apple click = table.getSelectionModel().getSelectedItem();
		c = new Cart(click.getId_a(),click.getName_a(),click.getBrand_a(),click.getSize_a(),click.getPrice_a());
	}
	
	@FXML
	public void btnCart(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Cart.fxml");
	}
	
	@FXML
	public void btnHuawei(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopHuawei.fxml");
	}
	
	@FXML
	public void btnSamsung(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopSamsung.fxml");
	}
	
	@FXML
	public void btnXiaomi(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopXiaomi.fxml");
	}
}

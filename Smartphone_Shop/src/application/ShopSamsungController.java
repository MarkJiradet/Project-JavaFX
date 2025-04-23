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

import com.information.hql.entity.Cart;
import com.information.hql.entity.Samsung;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class ShopSamsungController implements Initializable{
	@FXML
	private TableView<Samsung> table;
	@FXML
	private TableColumn <Samsung,String>col_id;
	@FXML
	private TableColumn <Samsung,String>col_name;
	@FXML
	private TableColumn <Samsung,String>col_brand;
	@FXML
	private TableColumn <Samsung,String>col_size;
	@FXML
	private TableColumn <Samsung,Integer>col_price;

	Main m = new Main();
	Cart c;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Samsung.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Samsung>samsung = session.createQuery("from Samsung").list();
			ObservableList<Samsung>samsungList = FXCollections.observableArrayList(samsung);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Samsung,String>("id_s"));
			col_name.setCellValueFactory(new PropertyValueFactory<Samsung,String>("name_s"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Samsung,String>("brand_s"));
			col_size.setCellValueFactory(new PropertyValueFactory<Samsung,String>("size_s"));
			col_price.setCellValueFactory(new PropertyValueFactory<Samsung,Integer>("price_s"));
			
			table.setItems(samsungList);
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
		Samsung click = table.getSelectionModel().getSelectedItem();
		c = new Cart(click.getId_s(),click.getName_s(),click.getBrand_s(),click.getSize_s(),click.getPrice_s());
	}
	
	@FXML
	public void btnCart(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Cart.fxml");
	}
	
	@FXML
	public void btnApple(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopApple.fxml");
	}
	@FXML
	public void btnHuawei(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopHuawei.fxml");
	}
	@FXML
	public void btnXiaomi(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopXiaomi.fxml");
	}

}

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
import com.information.hql.entity.Huawei;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class ShopHuaweiController implements Initializable{
	@FXML
	private TableView<Huawei> table;
	@FXML
	private TableColumn<Huawei,String> col_id;
	@FXML
	private TableColumn <Huawei,String>col_name;
	@FXML
	private TableColumn <Huawei,String>col_brand;
	@FXML
	private TableColumn<Huawei,String> col_size;
	@FXML
	private TableColumn <Huawei,Integer>col_price;


	Main m = new Main();
	Cart c;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Huawei.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Huawei>huawei = session.createQuery("from Huawei").list();
			ObservableList<Huawei>huaweiList = FXCollections.observableArrayList(huawei);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Huawei,String>("id_h"));
			col_name.setCellValueFactory(new PropertyValueFactory<Huawei,String>("name_h"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Huawei,String>("brand_h"));
			col_size.setCellValueFactory(new PropertyValueFactory<Huawei,String>("size_h"));
			col_price.setCellValueFactory(new PropertyValueFactory<Huawei,Integer>("price_h"));
			
			table.setItems(huaweiList);
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
		Huawei click = table.getSelectionModel().getSelectedItem();
		c = new Cart(click.getId_h(),click.getName_h(),click.getBrand_h(),click.getSize_h(),click.getPrice_h());
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
	public void btnSamsung(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopSamsung.fxml");
	}
	
	@FXML
	public void btnXiaomi(ActionEvent event) throws IOException{
		m.changeScene("/fxml/ShopXiaomi.fxml");
	}

}

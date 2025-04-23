package application;

import com.information.entity.GamingGear;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class GamingGearController implements Initializable{
	@FXML
	private TableView <GamingGear>tableGG;
	@FXML
	private TableColumn<GamingGear,String> col_id_gg;
	@FXML
	private TableColumn<GamingGear,String> col_name_gg;
	@FXML
	private TableColumn<GamingGear,String> col_brand_gg;
	@FXML
	private TableColumn <GamingGear,String>col_type_gg;
	@FXML
	private TableColumn<GamingGear,Integer> col_price_gg;
	@FXML
	private TextField text_search_gg;
	@FXML
	private TextField text_id_gg;
	@FXML
	private TextField text_price_gg;
	@FXML
	private TextField text_name_gg;
	@FXML
	private TextField text_brand_gg;
	@FXML
	private TextField text_type_gg;
	
	Main m = new Main();
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(GamingGear.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<GamingGear>gg = session.createQuery("from GamingGear").list();
			ObservableList<GamingGear>ggList = FXCollections.observableArrayList(gg);
			session.getTransaction().commit();
			
			col_id_gg.setCellValueFactory(new PropertyValueFactory<GamingGear,String>("id_game"));
			col_name_gg.setCellValueFactory(new PropertyValueFactory<GamingGear,String>("name_game"));
			col_brand_gg.setCellValueFactory(new PropertyValueFactory<GamingGear,String>("brand_game"));
			col_type_gg.setCellValueFactory(new PropertyValueFactory<GamingGear,String>("type_game"));
			col_price_gg.setCellValueFactory(new PropertyValueFactory<GamingGear,Integer>("price_game"));
			
			tableGG.setItems(ggList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// Event Listener on AnchorPane.onMouseClicked
	@FXML
	public void refreshTable(MouseEvent event) {
		initialize(null,null);
		text_id_gg.clear();
		text_name_gg.clear();
		text_brand_gg.clear();
		text_type_gg.clear();
		text_price_gg.clear();
		text_search_gg.clear();
	}
	// Event Listener on TableView[#tableGG].onMouseClicked
	@FXML
	public void rowSelect(MouseEvent event) {
		GamingGear click = tableGG.getSelectionModel().getSelectedItem();
		text_id_gg.setText(String.valueOf(click.getId_game()));
		text_name_gg.setText(String.valueOf(click.getName_game()));
		text_brand_gg.setText(String.valueOf(click.getBrand_game()));
		text_type_gg.setText(String.valueOf(click.getType_game()));
		text_price_gg.setText(String.valueOf(click.getPrice_game()));
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnAdd_gg(ActionEvent event) {
		if(text_id_gg.getText()!="" && text_name_gg.getText()!="" && text_brand_gg.getText()!="" 
				&& text_type_gg.getText()!="" && text_price_gg.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(GamingGear.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				GamingGear gg = new GamingGear(text_id_gg.getText(),text_name_gg.getText(),text_brand_gg.getText()
						,text_type_gg.getText(),Integer.parseInt(text_price_gg.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(gg);
				
				initialize(null,null);
				//commit transaction
				session.getTransaction().commit();
			}finally {
				Alert a = new Alert(Alert.AlertType.NONE,"เพิ่มข้อมูลสำเร็จ",ButtonType.OK);
				a.show();
				factory.close();
			}
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnEdit_gg(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(GamingGear.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			GamingGear gg = session.get(GamingGear.class,text_id_gg.getText());
			
			gg.setId_game(text_id_gg.getText());
			gg.setName_game(text_name_gg.getText());
			gg.setBrand_game(text_brand_gg.getText());
			gg.setType_game(text_type_gg.getText());
			gg.setPrice_game(Integer.parseInt(text_price_gg.getText()));
			
			initialize(null,null);
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Alert a = new Alert(Alert.AlertType.NONE,"แก้ไขข้อมูลสำเร็จ",ButtonType.OK);
			a.show();
			factory.close();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnDelete_gg(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(GamingGear.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			GamingGear gg = session.get(GamingGear.class,text_id_gg.getText());
			
			session.delete(gg);
			
			initialize(null,null);
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Alert a = new Alert(Alert.AlertType.NONE,"ลบข้อมูลสำเร็จ",ButtonType.OK);
			a.show();
			factory.close();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnSearch_gg(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(GamingGear.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<GamingGear>gg = session.createQuery("from GamingGear").list();
			gg = session.createQuery("from GamingGear g where g.id_game='"+text_search_gg.getText()+
					"' OR g.name_game='"+text_search_gg.getText()+
					"' OR g.brand_game='"+text_search_gg.getText()+
					"' OR g.type_game='"+text_search_gg.getText()+
					"' OR g.price_game='"+text_search_gg.getText()+"'").list();
			ObservableList<GamingGear>ggList = FXCollections.observableArrayList(gg);
			tableGG.setItems(FXCollections.observableArrayList(ggList));
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnSceneMAA(ActionEvent event) throws IOException{
		m.changeSceneToSystem("AdminAccount.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnScenePhone(ActionEvent event)  throws IOException{
		m.changeSceneToSystem("Smartphone.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnSceneLaptop(ActionEvent event)  throws IOException{
		m.changeSceneToSystem("Laptop.fxml");
	}
}

package application;

import com.information.entity.Laptop;
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

public class LaptopController implements Initializable{
	@FXML
	private TableView <Laptop>tableLaptop;
	@FXML
	private TableColumn <Laptop,String>col_id_l;
	@FXML
	private TableColumn<Laptop,String> col_name_l;
	@FXML
	private TableColumn<Laptop,String> col_brand_l;
	@FXML
	private TableColumn<Laptop,String> col_size_l;
	@FXML
	private TableColumn<Laptop,Integer> col_price_l;
	@FXML
	private TextField text_search_laptop;
	@FXML
	private TextField text_id_laptop;
	@FXML
	private TextField text_price_laptop;
	@FXML
	private TextField text_name_laptop;
	@FXML
	private TextField text_brand_laptop;
	@FXML
	private TextField text_size_laptop;
	
	Main m = new Main();
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Laptop.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Laptop>laptop = session.createQuery("from Laptop").list();
			ObservableList<Laptop>laptopList = FXCollections.observableArrayList(laptop);
			session.getTransaction().commit();
			
			col_id_l.setCellValueFactory(new PropertyValueFactory<Laptop,String>("id_laptop"));
			col_name_l.setCellValueFactory(new PropertyValueFactory<Laptop,String>("name_laptop"));
			col_brand_l.setCellValueFactory(new PropertyValueFactory<Laptop,String>("brand_laptop"));
			col_size_l.setCellValueFactory(new PropertyValueFactory<Laptop,String>("size_laptop"));
			col_price_l.setCellValueFactory(new PropertyValueFactory<Laptop,Integer>("price_laptop"));
			
			tableLaptop.setItems(laptopList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// Event Listener on AnchorPane.onMouseClicked
	@FXML
	public void refreshTable(MouseEvent event) {

		text_id_laptop.clear();
		text_name_laptop.clear();
		text_brand_laptop.clear();
		text_size_laptop.clear();
		text_price_laptop.clear();
		text_search_laptop.clear();
		
		initialize(null,null);

	}
	// Event Listener on TableView[#tableLaptop].onMouseClicked
	@FXML
	public void rowSelect(MouseEvent event) {
		Laptop click = tableLaptop.getSelectionModel().getSelectedItem();
		text_id_laptop.setText(String.valueOf(click.getId_laptop()));
		text_name_laptop.setText(String.valueOf(click.getName_laptop()));
		text_brand_laptop.setText(String.valueOf(click.getBrand_laptop()));
		text_size_laptop.setText(String.valueOf(click.getSize_laptop()));
		text_price_laptop.setText(String.valueOf(click.getPrice_laptop()));
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnAdd_laptop(ActionEvent event) {
		if(text_id_laptop.getText()!="" && text_name_laptop.getText()!="" && text_brand_laptop.getText()!="" 
				&& text_size_laptop.getText()!="" && text_price_laptop.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Laptop.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Laptop laptop = new Laptop(text_id_laptop.getText(),text_name_laptop.getText(),text_brand_laptop.getText()
						,text_size_laptop.getText(),Integer.parseInt(text_price_laptop.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(laptop);
				
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
	public void btnEdit_laptop(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Laptop.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Laptop laptop = session.get(Laptop.class,text_id_laptop.getText());
			
			laptop.setId_laptop(text_id_laptop.getText());
			laptop.setName_laptop(text_name_laptop.getText());
			laptop.setBrand_laptop(text_brand_laptop.getText());
			laptop.setSize_laptop(text_size_laptop.getText());
			laptop.setPrice_laptop(Integer.parseInt(text_price_laptop.getText()));
			
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
	public void btnDelete_laptop(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Laptop.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Laptop laptop = session.get(Laptop.class,text_id_laptop.getText());
			
			session.delete(laptop);
			
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
	public void btnSearch_laptop(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Laptop.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Laptop>laptop = session.createQuery("from Laptop").list();
			laptop = session.createQuery("from Laptop l where l.id_laptop='"+text_search_laptop.getText()+
					"' OR l.name_laptop='"+text_search_laptop.getText()+
					"' OR l.brand_laptop='"+text_search_laptop.getText()+
					"' OR l.size_laptop='"+text_search_laptop.getText()+
					"' OR l.price_laptop='"+text_search_laptop.getText()+"'").list();
			ObservableList<Laptop>laptopList = FXCollections.observableArrayList(laptop);
			tableLaptop.setItems(FXCollections.observableArrayList(laptopList));
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
	public void btnScenePhone(ActionEvent event) throws IOException{
		m.changeSceneToSystem("Smartphone.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnSceneGG(ActionEvent event) throws IOException{
		m.changeSceneToSystem("GamingGear.fxml");
	}
}

package application;

import com.information.entity.Smartphone;

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

public class SmartphoneController implements Initializable{
	@FXML
	private TableView <Smartphone>tablePhone;
	@FXML
	private TableColumn <Smartphone,String>col_id_phone;
	@FXML
	private TableColumn <Smartphone,String>col_name_phone;
	@FXML
	private TableColumn<Smartphone,String> col_brand_phone;
	@FXML
	private TableColumn <Smartphone,String>col_size_phone;
	@FXML
	private TableColumn <Smartphone,Integer>col_price_phone;
	@FXML
	private TextField text_search_phone;
	@FXML
	private TextField text_id_phone;
	@FXML
	private TextField text_price_phone;
	@FXML
	private TextField text_name_phone;
	@FXML
	private TextField text_brand_phone;
	@FXML
	private TextField text_size_phone;
	
	Main m = new Main();
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Smartphone.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Smartphone>smartphone = session.createQuery("from Smartphone").list();
			ObservableList<Smartphone>smartPhoneList = FXCollections.observableArrayList(smartphone);
			session.getTransaction().commit();
			
			col_id_phone.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("id_phone"));
			col_name_phone.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("name_phone"));
			col_brand_phone.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("brand_phone"));
			col_size_phone.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("size_phone"));
			col_price_phone.setCellValueFactory(new PropertyValueFactory<Smartphone,Integer>("price_phone"));
			
			tablePhone.setItems(smartPhoneList);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	public void rowSelect(MouseEvent event) {
		Smartphone click = tablePhone.getSelectionModel().getSelectedItem();
		text_id_phone.setText(String.valueOf(click.getId_phone()));
		text_name_phone.setText(String.valueOf(click.getName_phone()));
		text_brand_phone.setText(String.valueOf(click.getBrand_phone()));
		text_size_phone.setText(String.valueOf(click.getSize_phone()));
		text_price_phone.setText(String.valueOf(click.getPrice_phone()));
	}
	
	@FXML
	public void refreshTable(MouseEvent event) {

		text_id_phone.clear();
		text_name_phone.clear();
		text_brand_phone.clear();
		text_size_phone.clear();
		text_price_phone.clear();
		text_search_phone.clear();
		
		initialize(null,null);
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void btnAdd_phone(ActionEvent event) {
		if(text_id_phone.getText()!="" && text_name_phone.getText()!="" && text_brand_phone.getText()!="" && text_size_phone.getText()!="" && text_price_phone.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Smartphone.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Smartphone smartphone = new Smartphone(text_id_phone.getText(),text_name_phone.getText(),text_brand_phone.getText()
						,text_size_phone.getText(),Integer.parseInt(text_price_phone.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(smartphone);
				
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
	public void btnEdit_phone(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Smartphone.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Smartphone smartphone = session.get(Smartphone.class,text_id_phone.getText());
			
			smartphone.setId_phone(text_id_phone.getText());
			smartphone.setName_phone(text_name_phone.getText());
			smartphone.setBrand_phone(text_brand_phone.getText());
			smartphone.setSize_phone(text_size_phone.getText());
			smartphone.setPrice_phone(Integer.parseInt(text_price_phone.getText()));
			//save the student object
			
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
	public void btnDelete_phone(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Smartphone.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Smartphone smartphone = session.get(Smartphone.class,text_id_phone.getText());
			//delete
			session.delete(smartphone);
			
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
	public void btnSearch_phone(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Smartphone.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Smartphone>smartphone = session.createQuery("from Smartphone").list();
			smartphone = session.createQuery("from Smartphone s where s.id_phone='"+text_search_phone.getText()+
					"' OR s.name_phone='"+text_search_phone.getText()+
					"' OR s.brand_phone='"+text_search_phone.getText()+
					"' OR s.size_phone='"+text_search_phone.getText()+
					"' OR s.price_phone='"+text_search_phone.getText()+"'").list();
			
			ObservableList<Smartphone>phoneList = FXCollections.observableArrayList(smartphone);
			tablePhone.setItems(FXCollections.observableArrayList(phoneList));
			
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
	public void btnSceneLaptop(ActionEvent event) throws IOException{
		m.changeSceneToSystem("Laptop.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnSceneGG(ActionEvent event) throws IOException{
		m.changeSceneToSystem("GamingGear.fxml");
	}
	
}

package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hql.information.entity.Keyboard;

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

public class KeyboardController implements Initializable{
	@FXML
	private TableView <Keyboard>table;
	@FXML
	private TableColumn<Keyboard,String> col_id;
	@FXML
	private TableColumn <Keyboard,String>col_name;
	@FXML
	private TableColumn <Keyboard,String>col_brand;
	@FXML
	private TableColumn <Keyboard,Integer>col_price;
	@FXML
	private TextField text_id;
	@FXML
	private TextField text_name;
	@FXML
	private TextField text_brand;
	@FXML
	private TextField text_price;
	@FXML
	private TextField text_search;

	Main m = new Main();
	
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
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_brand.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Keyboard.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Keyboard k = new Keyboard(text_id.getText(),text_name.getText(),text_brand.getText()
						,Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				session.save(k);
			
				//commit transaction
				session.getTransaction().commit();
			}finally {
				Alert a = new Alert(Alert.AlertType.NONE,"เพิ่มข้อมูลสำเร็จ",ButtonType.OK);
				a.show();
				initialize(null,null);
				factory.close();
			}
		}
	}

	@FXML
	public void btnEdit(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Keyboard.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Keyboard k = session.get(Keyboard.class,text_id.getText());
			
			k.setId_k(text_id.getText());
			k.setName_k(text_name.getText());
			k.setBrand_k(text_brand.getText());
			k.setPrice_k(Integer.parseInt(text_price.getText()));
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Alert a = new Alert(Alert.AlertType.NONE,"แก้ไขข้อมูลสำเร็จ",ButtonType.OK);
			a.show();
			initialize(null,null);
			factory.close();
		}
	}
	@FXML
	public void btnDelete(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Keyboard.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Keyboard k = session.get(Keyboard.class,text_id.getText());
			//delete
			session.delete(k);
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Alert a = new Alert(Alert.AlertType.NONE,"ลบข้อมูลสำเร็จ",ButtonType.OK);
			a.show();
			initialize(null,null);
			factory.close();
		}
	}

	@FXML
	public void btnSearch(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Keyboard.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Keyboard>k = session.createQuery("from Keyboard").list();
			k = session.createQuery("from Keyboard c where c.id_k='"+text_search.getText()+
					"' OR c.name_k='"+text_search.getText()+
					"' OR c.brand_k='"+text_search.getText()+
					"' OR c.price_k='"+text_search.getText()+"'").list();
			
			ObservableList<Keyboard>kList = FXCollections.observableArrayList(k);
			table.setItems(FXCollections.observableArrayList(kList));
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
	@FXML
	public void refresh(MouseEvent event) {
		text_id.clear();
		text_name.clear();
		text_brand.clear();
		text_price.clear();
		text_search.clear();
		initialize(null,null);
	}
	
	@FXML
	public void rowClick(MouseEvent event) {
		Keyboard click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getId_k()));
		text_name.setText(String.valueOf(click.getName_k()));
		text_brand.setText(String.valueOf(click.getBrand_k()));
		text_price.setText(String.valueOf(click.getPrice_k()));
	}
	@FXML
	public void btnMouse(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Mouse.fxml");
	}

	@FXML
	public void btnMonitor(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Monitor.fxml");
	}

	@FXML
	public void btnHeadphone(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Headphone.fxml");
	}

	@FXML
	public void btnFlash(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Flashdrive.fxml");
	}
}

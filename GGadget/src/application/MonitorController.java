package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hql.information.entity.Monitor;

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

public class MonitorController implements Initializable{
	@FXML
	private TableView <Monitor>table;
	@FXML
	private TableColumn<Monitor,String> col_id;
	@FXML
	private TableColumn<Monitor,String> col_name;
	@FXML
	private TableColumn <Monitor,String>col_brand;
	@FXML
	private TableColumn <Monitor,Integer>col_price;
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
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_brand.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Monitor.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Monitor monitor = new Monitor(text_id.getText(),text_name.getText(),text_brand.getText()
						,Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				session.save(monitor);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Monitor.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Monitor monitor = session.get(Monitor.class,text_id.getText());
			
			monitor.setId_mo(text_id.getText());
			monitor.setName_mo(text_name.getText());
			monitor.setBrand_mo(text_brand.getText());
			monitor.setPrice_mo(Integer.parseInt(text_price.getText()));
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Monitor.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Monitor monitor = session.get(Monitor.class,text_id.getText());
			//delete
			session.delete(monitor);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Monitor.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Monitor>monitor = session.createQuery("from Monitor").list();
			monitor = session.createQuery("from Monitor c where c.id_mo='"+text_search.getText()+
					"' OR c.name_mo='"+text_search.getText()+
					"' OR c.brand_mo='"+text_search.getText()+
					"' OR c.price_mo='"+text_search.getText()+"'").list();
			
			ObservableList<Monitor>monitorList = FXCollections.observableArrayList(monitor);
			table.setItems(FXCollections.observableArrayList(monitorList));
			
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
		Monitor click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getId_mo()));
		text_name.setText(String.valueOf(click.getName_mo()));
		text_brand.setText(String.valueOf(click.getBrand_mo()));
		text_price.setText(String.valueOf(click.getPrice_mo()));
	}
	@FXML
	public void btnMouse(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Mouse.fxml");
	}

	@FXML
	public void btnHeadphone(ActionEvent event)  throws IOException{
		m.changeScene("/fxml/Headphone.fxml");
	}

	@FXML
	public void btnKeyboard(ActionEvent event)  throws IOException{
		m.changeScene("/fxml/Keyboard.fxml");
	}

	@FXML
	public void btnFlash(ActionEvent event)  throws IOException{
		m.changeScene("/fxml/Flashdrive.fxml");
	}
}

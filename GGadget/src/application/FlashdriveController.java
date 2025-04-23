package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hql.information.entity.FlashDrive;

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

public class FlashdriveController implements Initializable{
	@FXML
	private TableView <FlashDrive>table;
	@FXML
	private TableColumn<FlashDrive,String> col_id;
	@FXML
	private TableColumn <FlashDrive,String>col_name;
	@FXML
	private TableColumn <FlashDrive,String>col_brand;
	@FXML
	private TableColumn <FlashDrive,Integer>col_price;
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
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FlashDrive.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<FlashDrive>f = session.createQuery("from FlashDrive").list();
			ObservableList<FlashDrive>fList = FXCollections.observableArrayList(f);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<FlashDrive,String>("id_f"));
			col_name.setCellValueFactory(new PropertyValueFactory<FlashDrive,String>("name_f"));
			col_brand.setCellValueFactory(new PropertyValueFactory<FlashDrive,String>("brand_f"));
			col_price.setCellValueFactory(new PropertyValueFactory<FlashDrive,Integer>("price_f"));
			
			table.setItems(fList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_brand.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FlashDrive.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				FlashDrive f = new FlashDrive(text_id.getText(),text_name.getText(),text_brand.getText()
						,Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				session.save(f);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FlashDrive.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			FlashDrive f = session.get(FlashDrive.class,text_id.getText());
			
			f.setId_f(text_id.getText());
			f.setName_f(text_name.getText());
			f.setBrand_f(text_brand.getText());
			f.setPrice_f(Integer.parseInt(text_price.getText()));
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FlashDrive.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			FlashDrive f = session.get(FlashDrive.class,text_id.getText());
			//delete
			session.delete(f);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FlashDrive.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<FlashDrive>f = session.createQuery("from FlashDrive").list();
			f = session.createQuery("from FlashDrive c where c.id_f='"+text_search.getText()+
					"' OR c.name_f='"+text_search.getText()+
					"' OR c.brand_f='"+text_search.getText()+
					"' OR c.price_f='"+text_search.getText()+"'").list();
			
			ObservableList<FlashDrive>fList = FXCollections.observableArrayList(f);
			table.setItems(FXCollections.observableArrayList(fList));
			
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
		FlashDrive click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getId_f()));
		text_name.setText(String.valueOf(click.getName_f()));
		text_brand.setText(String.valueOf(click.getBrand_f()));
		text_price.setText(String.valueOf(click.getPrice_f()));
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
	public void btnKeyboard(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Keyboard.fxml");
	}
}

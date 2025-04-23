package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hql.information.entity.Mouse;

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

public class MouseController implements Initializable{
	@FXML
	private TableView<Mouse> table;
	@FXML
	private TableColumn <Mouse,String>col_id;
	@FXML
	private TableColumn<Mouse,String> col_name;
	@FXML
	private TableColumn <Mouse,String>col_brand;
	@FXML
	private TableColumn <Mouse,Integer>col_price;
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
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Mouse.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Mouse>mouse = session.createQuery("from Mouse").list();
			ObservableList<Mouse>mouseList = FXCollections.observableArrayList(mouse);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Mouse,String>("id_m"));
			col_name.setCellValueFactory(new PropertyValueFactory<Mouse,String>("name_m"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Mouse,String>("brand_m"));
			col_price.setCellValueFactory(new PropertyValueFactory<Mouse,Integer>("price_m"));
			
			table.setItems(mouseList);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	@FXML
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_brand.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Mouse.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Mouse mouse = new Mouse(text_id.getText(),text_name.getText(),text_brand.getText()
						,Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(mouse);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Mouse.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Mouse mouse = session.get(Mouse.class,text_id.getText());
			
			mouse.setId_m(text_id.getText());
			mouse.setName_m(text_name.getText());
			mouse.setBrand_m(text_brand.getText());
			mouse.setPrice_m(Integer.parseInt(text_price.getText()));
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Mouse.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Mouse mouse = session.get(Mouse.class,text_id.getText());
			//delete
			session.delete(mouse);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Mouse.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Mouse>mouse = session.createQuery("from Mouse").list();
			mouse = session.createQuery("from Mouse c where c.id_m='"+text_search.getText()+
					"' OR c.name_m='"+text_search.getText()+
					"' OR c.brand_m='"+text_search.getText()+
					"' OR c.price_m='"+text_search.getText()+"'").list();
			
			ObservableList<Mouse>mouseList = FXCollections.observableArrayList(mouse);
			table.setItems(FXCollections.observableArrayList(mouseList));
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
	
	@FXML
	public void rowClick(MouseEvent event) {
		Mouse click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getId_m()));
		text_name.setText(String.valueOf(click.getName_m()));
		text_brand.setText(String.valueOf(click.getBrand_m()));
		text_price.setText(String.valueOf(click.getPrice_m()));
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

	@FXML
	public void btnFlash(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Flashdrive.fxml");
	}


}

package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hql.information.entity.Headphone;

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
public class HeadphoneController implements Initializable{
	@FXML
	private TableView <Headphone>table;
	@FXML
	private TableColumn<Headphone,String> col_id;
	@FXML
	private TableColumn<Headphone,String> col_name;
	@FXML
	private TableColumn<Headphone,String> col_brand;
	@FXML
	private TableColumn<Headphone,Integer> col_price;
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
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Headphone.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Headphone>h = session.createQuery("from Headphone").list();
			ObservableList<Headphone>hList = FXCollections.observableArrayList(h);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Headphone,String>("id_h"));
			col_name.setCellValueFactory(new PropertyValueFactory<Headphone,String>("name_h"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Headphone,String>("brand_h"));
			col_price.setCellValueFactory(new PropertyValueFactory<Headphone,Integer>("price_h"));
			
			table.setItems(hList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_brand.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Headphone.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Headphone h = new Headphone(text_id.getText(),text_name.getText(),text_brand.getText()
						,Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				session.save(h);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Headphone.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Headphone h = session.get(Headphone.class,text_id.getText());
			
			h.setId_h(text_id.getText());
			h.setName_h(text_name.getText());
			h.setBrand_h(text_brand.getText());
			h.setPrice_h(Integer.parseInt(text_price.getText()));
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Headphone.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Headphone h = session.get(Headphone.class,text_id.getText());
			//delete
			session.delete(h);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Headphone.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Headphone>h = session.createQuery("from Headphone").list();
			h = session.createQuery("from Headphone c where c.id_h='"+text_search.getText()+
					"' OR c.name_h='"+text_search.getText()+
					"' OR c.brand_h='"+text_search.getText()+
					"' OR c.price_h='"+text_search.getText()+"'").list();
			
			ObservableList<Headphone>hList = FXCollections.observableArrayList(h);
			table.setItems(FXCollections.observableArrayList(hList));
			
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
		Headphone click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getId_h()));
		text_name.setText(String.valueOf(click.getName_h()));
		text_brand.setText(String.valueOf(click.getBrand_h()));
		text_price.setText(String.valueOf(click.getPrice_h()));
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
	public void btnKeyboard(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Keyboard.fxml");
	}

	@FXML
	public void btnFlash(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Flashdrive.fxml");
	}
}

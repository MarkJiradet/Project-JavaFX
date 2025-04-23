package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.information.hql.entity.Apple;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class AppleController implements Initializable{
	@FXML
	private TableView<Apple> table;
	@FXML
	private TableColumn <Apple,String>col_id;
	@FXML
	private TableColumn <Apple,String>col_name;
	@FXML
	private TableColumn<Apple,String> col_brand;
	@FXML
	private TableColumn <Apple,String>col_size;
	@FXML
	private TableColumn<Apple,Integer> col_price;
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
	@FXML
	private TextField text_size;

	Main m = new Main();
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Apple.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Apple>apple = session.createQuery("from Apple").list();
			ObservableList<Apple>appleList = FXCollections.observableArrayList(apple);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Apple,String>("id_a"));
			col_name.setCellValueFactory(new PropertyValueFactory<Apple,String>("name_a"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Apple,String>("brand_a"));
			col_size.setCellValueFactory(new PropertyValueFactory<Apple,String>("size_a"));
			col_price.setCellValueFactory(new PropertyValueFactory<Apple,Integer>("price_a"));
			
			table.setItems(appleList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_brand.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Apple.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Apple apple = new Apple(text_id.getText(),text_name.getText(),text_brand.getText(),text_size.getText()
						,Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				session.save(apple);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Apple.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Apple apple = session.get(Apple.class,text_id.getText());
			
			apple.setId_a(text_id.getText());
			apple.setName_a(text_name.getText());
			apple.setBrand_a(text_brand.getText());
			apple.setSize_a(text_size.getText());
			apple.setPrice_a(Integer.parseInt(text_price.getText()));
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Apple.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Apple apple = session.get(Apple.class,text_id.getText());
			//delete
			session.delete(apple);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Apple.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Apple>apple = session.createQuery("from Apple").list();
			apple = session.createQuery("from Apple a where a.id_a='"+text_search.getText()+
					"' OR a.name_a='"+text_search.getText()+
					"' OR a.brand_a='"+text_search.getText()+
					"' OR a.size_a='"+text_search.getText()+
					"' OR a.price_a='"+text_search.getText()+"'").list();
			
			ObservableList<Apple>appleList = FXCollections.observableArrayList(apple);
			table.setItems(FXCollections.observableArrayList(appleList));
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
	
	@FXML
	public void rowClick(MouseEvent event) {
		Apple click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getId_a()));
		text_name.setText(String.valueOf(click.getName_a()));
		text_brand.setText(String.valueOf(click.getBrand_a()));
		text_size.setText(String.valueOf(click.getSize_a()));
		text_price.setText(String.valueOf(click.getPrice_a()));
	}
	
	@FXML
	public void refresh(MouseEvent event) {
		text_id.clear();
		text_name.clear();
		text_brand.clear();
		text_size.clear();
		text_price.clear();
		text_search.clear();
		initialize(null,null);
	}

	@FXML
	public void btnHuawei(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Huawei.fxml");
	}

	@FXML
	public void btnSamsung(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Samsung.fxml");
	}

	@FXML
	public void btnXiaomi(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Xiaomi.fxml");
	}

}

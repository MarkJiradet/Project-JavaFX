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
import com.information.hql.entity.Samsung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class SamsungController implements Initializable{
	@FXML
	private TableView <Samsung>table;
	@FXML
	private TableColumn <Samsung,String>col_id;
	@FXML
	private TableColumn <Samsung,String>col_name;
	@FXML
	private TableColumn <Samsung,String>col_brand;
	@FXML
	private TableColumn<Samsung,String> col_size;
	@FXML
	private TableColumn<Samsung,Integer>col_price;
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
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Samsung.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Samsung>samsung = session.createQuery("from Samsung").list();
			ObservableList<Samsung>samsungList = FXCollections.observableArrayList(samsung);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Samsung,String>("id_s"));
			col_name.setCellValueFactory(new PropertyValueFactory<Samsung,String>("name_s"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Samsung,String>("brand_s"));
			col_size.setCellValueFactory(new PropertyValueFactory<Samsung,String>("size_s"));
			col_price.setCellValueFactory(new PropertyValueFactory<Samsung,Integer>("price_s"));
			
			table.setItems(samsungList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_brand.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Samsung.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Samsung samsung = new Samsung(text_id.getText(),text_name.getText(),text_brand.getText(),text_size.getText()
						,Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				session.save(samsung);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Samsung.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Samsung samsung = session.get(Samsung.class,text_id.getText());
			
			samsung.setId_s(text_id.getText());
			samsung.setName_s(text_name.getText());
			samsung.setBrand_s(text_brand.getText());
			samsung.setSize_s(text_size.getText());
			samsung.setPrice_s(Integer.parseInt(text_price.getText()));
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Samsung.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Samsung samsung = session.get(Samsung.class,text_id.getText());
			//delete
			session.delete(samsung);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Samsung.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Samsung>samsung = session.createQuery("from Samsung").list();
			samsung = session.createQuery("from Samsung s where s.id_s='"+text_search.getText()+
					"' OR s.name_s='"+text_search.getText()+
					"' OR s.brand_s='"+text_search.getText()+
					"' OR s.size_s='"+text_search.getText()+
					"' OR s.price_s='"+text_search.getText()+"'").list();
			
			ObservableList<Samsung>samsungList = FXCollections.observableArrayList(samsung);
			table.setItems(FXCollections.observableArrayList(samsungList));
			
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
		text_size.clear();
		text_price.clear();
		text_search.clear();
		initialize(null,null);
	}

	@FXML
	public void rowClick(MouseEvent event) {
		Samsung click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getId_s()));
		text_name.setText(String.valueOf(click.getName_s()));
		text_brand.setText(String.valueOf(click.getBrand_s()));
		text_size.setText(String.valueOf(click.getSize_s()));
		text_price.setText(String.valueOf(click.getPrice_s()));
	}
	
	@FXML
	public void btnApple(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Apple.fxml");
	}
	
	@FXML
	public void btnHuawei(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Huawei.fxml");
	}
	
	@FXML
	public void btnXiaomi(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Xiaomi.fxml");
	}
}

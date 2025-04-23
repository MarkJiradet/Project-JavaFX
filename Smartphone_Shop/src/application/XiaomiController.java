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
import com.information.hql.entity.Xiaomi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class XiaomiController implements Initializable{
	@FXML
	private TableView<Xiaomi>table;
	@FXML
	private TableColumn <Xiaomi,String>col_id;
	@FXML
	private TableColumn <Xiaomi,String>col_name;
	@FXML
	private TableColumn <Xiaomi,String>col_brand;
	@FXML
	private TableColumn<Xiaomi,String> col_size;
	@FXML
	private TableColumn <Xiaomi,Integer>col_price;
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
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Xiaomi.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Xiaomi>xiaomi = session.createQuery("from Xiaomi").list();
			ObservableList<Xiaomi>xiaomiList = FXCollections.observableArrayList(xiaomi);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Xiaomi,String>("id_x"));
			col_name.setCellValueFactory(new PropertyValueFactory<Xiaomi,String>("name_x"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Xiaomi,String>("brand_x"));
			col_size.setCellValueFactory(new PropertyValueFactory<Xiaomi,String>("size_x"));
			col_price.setCellValueFactory(new PropertyValueFactory<Xiaomi,Integer>("price_x"));
			
			table.setItems(xiaomiList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_brand.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Xiaomi.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Xiaomi xiaomi = new Xiaomi(text_id.getText(),text_name.getText(),text_brand.getText(),text_size.getText()
						,Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				session.save(xiaomi);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Xiaomi.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Xiaomi xiaomi = session.get(Xiaomi.class,text_id.getText());
			
			xiaomi.setId_x(text_id.getText());
			xiaomi.setName_x(text_name.getText());
			xiaomi.setBrand_x(text_brand.getText());
			xiaomi.setSize_x(text_size.getText());
			xiaomi.setPrice_x(Integer.parseInt(text_price.getText()));
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Xiaomi.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Xiaomi xiaomi = session.get(Xiaomi.class,text_id.getText());
			//delete
			session.delete(xiaomi);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Xiaomi.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Xiaomi>xiaomi = session.createQuery("from Xiaomi").list();
			xiaomi = session.createQuery("from Xiaomi x where x.id_x='"+text_search.getText()+
					"' OR x.name_x='"+text_search.getText()+
					"' OR x.brand_x='"+text_search.getText()+
					"' OR x.size_x='"+text_search.getText()+
					"' OR x.price_x='"+text_search.getText()+"'").list();
			
			ObservableList<Xiaomi>xiaomiList = FXCollections.observableArrayList(xiaomi);
			table.setItems(FXCollections.observableArrayList(xiaomiList));
			
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
		Xiaomi click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getId_x()));
		text_name.setText(String.valueOf(click.getName_x()));
		text_brand.setText(String.valueOf(click.getBrand_x()));
		text_size.setText(String.valueOf(click.getSize_x()));
		text_price.setText(String.valueOf(click.getPrice_x()));
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
	public void btnSamsung(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Samsung.fxml");
	}
}

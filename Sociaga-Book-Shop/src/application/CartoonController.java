package application;

import com.information.entity.Cartoon;

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

public class CartoonController implements Initializable{
	@FXML
	private TableView<Cartoon>table;
	@FXML
	private TableColumn<Cartoon,String>col_id;
	@FXML
	private TableColumn<Cartoon,String> col_name;
	@FXML
	private TableColumn <Cartoon,String>col_author;
	@FXML
	private TableColumn <Cartoon,String>col_publisher;
	@FXML
	private TableColumn <Cartoon,Integer>col_price;
	@FXML
	private TextField text_id;
	@FXML
	private TextField text_name;
	@FXML
	private TextField text_author;
	@FXML
	private TextField text_price;
	@FXML
	private TextField text_publisher;
	@FXML
	private TextField text_search;

	Main m = new Main();
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cartoon.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Cartoon>cartoon = session.createQuery("from Cartoon").list();
			ObservableList<Cartoon>cartoonList = FXCollections.observableArrayList(cartoon);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Cartoon,String>("c_id"));
			col_name.setCellValueFactory(new PropertyValueFactory<Cartoon,String>("c_name"));
			col_author.setCellValueFactory(new PropertyValueFactory<Cartoon,String>("c_author"));
			col_publisher.setCellValueFactory(new PropertyValueFactory<Cartoon,String>("c_publisher"));
			col_price.setCellValueFactory(new PropertyValueFactory<Cartoon,Integer>("c_price"));
			
			table.setItems(cartoonList);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	@FXML
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_author.getText()!="" && text_publisher.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cartoon.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Cartoon cartoon = new Cartoon(text_id.getText(),text_name.getText(),text_author.getText()
						,text_publisher.getText(),Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(cartoon);
			
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
	// Event Listener on Button.onAction
	@FXML
	public void btnEdit(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cartoon.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Cartoon cartoon = session.get(Cartoon.class,text_id.getText());
			
			cartoon.setC_id(text_id.getText());
			cartoon.setC_name(text_name.getText());
			cartoon.setC_author(text_author.getText());
			cartoon.setC_publisher(text_publisher.getText());
			cartoon.setC_price(Integer.parseInt(text_price.getText()));
			//save the student object
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Alert a = new Alert(Alert.AlertType.NONE,"แก้ไขข้อมูลสำเร็จ",ButtonType.OK);
			a.show();
			initialize(null,null);
			factory.close();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnDelete(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cartoon.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Cartoon cartoon = session.get(Cartoon.class,text_id.getText());
			//delete
			session.delete(cartoon);
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Alert a = new Alert(Alert.AlertType.NONE,"ลบข้อมูลสำเร็จ",ButtonType.OK);
			a.show();
			initialize(null,null);
			factory.close();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnSearch(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cartoon.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Cartoon>cartoon = session.createQuery("from Cartoon").list();
			cartoon = session.createQuery("from Cartoon c where c.c_id='"+text_search.getText()+
					"' OR c.c_name='"+text_search.getText()+
					"' OR c.c_author='"+text_search.getText()+
					"' OR c.c_publisher='"+text_search.getText()+
					"' OR c.c_price='"+text_search.getText()+"'").list();
			
			ObservableList<Cartoon>cartoonList = FXCollections.observableArrayList(cartoon);
			table.setItems(FXCollections.observableArrayList(cartoonList));
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
	@FXML
	public void rowClick(MouseEvent event) {
		Cartoon click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getC_id()));
		text_name.setText(String.valueOf(click.getC_name()));
		text_author.setText(String.valueOf(click.getC_author()));
		text_publisher.setText(String.valueOf(click.getC_publisher()));
		text_price.setText(String.valueOf(click.getC_price()));
	}
	
	@FXML
	public void refresh(MouseEvent event) {

		text_id.clear();
		text_name.clear();
		text_author.clear();
		text_publisher.clear();
		text_price.clear();
		text_search.clear();
		initialize(null,null);
	}
	@FXML
	public void btnNovel(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Novel.fxml");
	}
	@FXML
	public void btnEducation(ActionEvent event) throws IOException {
		m.changeScene("/fxml/Education.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnHome(ActionEvent event) throws IOException {
		m.changeScene("/fxml/Login.fxml");
	}
}

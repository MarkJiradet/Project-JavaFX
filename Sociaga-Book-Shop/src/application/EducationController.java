package application;

import com.information.entity.Education;

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


public class EducationController implements Initializable{
	@FXML
	private TableView<Education>table;
	@FXML
	private TableColumn<Education,String> col_id;
	@FXML
	private TableColumn <Education,String>col_name;
	@FXML
	private TableColumn <Education,String>col_author;
	@FXML
	private TableColumn<Education,String> col_publisher;
	@FXML
	private TableColumn <Education,Integer>col_price;
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
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Education.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Education>ed = session.createQuery("from Education").list();
			ObservableList<Education>edList = FXCollections.observableArrayList(ed);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Education,String>("e_id"));
			col_name.setCellValueFactory(new PropertyValueFactory<Education,String>("e_name"));
			col_author.setCellValueFactory(new PropertyValueFactory<Education,String>("e_author"));
			col_publisher.setCellValueFactory(new PropertyValueFactory<Education,String>("e_publisher"));
			col_price.setCellValueFactory(new PropertyValueFactory<Education,Integer>("e_price"));
			
			table.setItems(edList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_author.getText()!="" && text_publisher.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Education.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Education ed = new Education(text_id.getText(),text_name.getText(),text_author.getText()
						,text_publisher.getText(),Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(ed);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Education.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Education ed = session.get(Education.class,text_id.getText());
			
			ed.setE_id(text_id.getText());
			ed.setE_name(text_name.getText());
			ed.setE_author(text_author.getText());
			ed.setE_publisher(text_publisher.getText());
			ed.setE_price(Integer.parseInt(text_price.getText()));
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Education.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Education ed = session.get(Education.class,text_id.getText());
			//delete
			session.delete(ed);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Education.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Education>ed = session.createQuery("from Education").list();
			ed = session.createQuery("from Education e where e.e_id='"+text_search.getText()+
					"' OR e.e_name='"+text_search.getText()+
					"' OR e.e_author='"+text_search.getText()+
					"' OR e.e_publisher='"+text_search.getText()+
					"' OR e.e_price='"+text_search.getText()+"'").list();
			
			ObservableList<Education>edList = FXCollections.observableArrayList(ed);
			table.setItems(FXCollections.observableArrayList(edList));
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
	@FXML
	public void rowClick(MouseEvent event) {
		Education click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getE_id()));
		text_name.setText(String.valueOf(click.getE_name()));
		text_author.setText(String.valueOf(click.getE_author()));
		text_publisher.setText(String.valueOf(click.getE_publisher()));
		text_price.setText(String.valueOf(click.getE_price()));
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
	public void btnCartoon(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Cartoon.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnNovel(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Novel.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnHome(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Login.fxml");
	}
}

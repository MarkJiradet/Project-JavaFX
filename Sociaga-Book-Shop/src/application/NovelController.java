package application;

import com.information.entity.Novel;

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

public class NovelController implements Initializable{
	@FXML
	private TableView<Novel> table;
	@FXML
	private TableColumn <Novel,String>col_id;
	@FXML
	private TableColumn <Novel,String>col_name;
	@FXML
	private TableColumn <Novel,String>col_author;
	@FXML
	private TableColumn <Novel,String>col_publisher;
	@FXML
	private TableColumn<Novel,Integer> col_price;
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
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Novel.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Novel>novel = session.createQuery("from Novel").list();
			ObservableList<Novel>novelList = FXCollections.observableArrayList(novel);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Novel,String>("n_id"));
			col_name.setCellValueFactory(new PropertyValueFactory<Novel,String>("n_name"));
			col_author.setCellValueFactory(new PropertyValueFactory<Novel,String>("n_author"));
			col_publisher.setCellValueFactory(new PropertyValueFactory<Novel,String>("n_publisher"));
			col_price.setCellValueFactory(new PropertyValueFactory<Novel,Integer>("n_price"));
			
			table.setItems(novelList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_author.getText()!="" && text_publisher.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Novel.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Novel novel = new Novel(text_id.getText(),text_name.getText(),text_author.getText()
						,text_publisher.getText(),Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(novel);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Novel.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Novel novel = session.get(Novel.class,text_id.getText());
			
			novel.setN_id(text_id.getText());
			novel.setN_name(text_name.getText());
			novel.setN_author(text_author.getText());
			novel.setN_publisher(text_publisher.getText());
			novel.setN_price(Integer.parseInt(text_price.getText()));
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

	@FXML
	public void btnDelete(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Novel.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Novel novel = session.get(Novel.class,text_id.getText());
			//delete
			session.delete(novel);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Novel.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Novel>novel = session.createQuery("from Novel").list();
			novel = session.createQuery("from Novel n where n.n_id='"+text_search.getText()+
					"' OR n.n_name='"+text_search.getText()+
					"' OR n.n_author='"+text_search.getText()+
					"' OR n.n_publisher='"+text_search.getText()+
					"' OR n.n_price='"+text_search.getText()+"'").list();
			
			ObservableList<Novel>novelList = FXCollections.observableArrayList(novel);
			table.setItems(FXCollections.observableArrayList(novelList));
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
	@FXML
	public void rowClick(MouseEvent event) {
		Novel click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getN_id()));
		text_name.setText(String.valueOf(click.getN_name()));
		text_author.setText(String.valueOf(click.getN_author()));
		text_publisher.setText(String.valueOf(click.getN_publisher()));
		text_price.setText(String.valueOf(click.getN_price()));
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
	public void btnEducation(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Education.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnHome(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Login.fxml");
	}
}

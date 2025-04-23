package application;

import com.information.entity.Admin;

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

public class AdminAccountController implements Initializable{
	@FXML
	private TableView<Admin>tableAdmin;
	@FXML
	private TableColumn<Admin,Integer>col_id_a;
	@FXML
	private TableColumn<Admin,String> col_fname_a;
	@FXML
	private TableColumn <Admin,String>col_lname_a;
	@FXML
	private TableColumn <Admin,String>col_email_a;
	@FXML
	private TableColumn <Admin,String>col_gender_a;
	@FXML
	private TableColumn <Admin,Integer>col_age_a;
	@FXML
	private TableColumn<Admin,String> col_telephone_a;
	@FXML
	private TableColumn <Admin,String>col_password_a;
	@FXML
	private TextField text_search_a;
	@FXML
	private TextField text_firstname_a;
	@FXML
	private TextField text_password_a;
	@FXML
	private TextField text_lastname_a;
	@FXML
	private TextField text_email_a;
	@FXML
	private TextField text_telephone_a;
	@FXML
	private TextField text_gender_a;
	@FXML
	private TextField text_age_a;
	@FXML
	private TextField text_id_a;

	Main m = new Main();
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Admin.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Admin>admin = session.createQuery("from Admin").list();
			ObservableList<Admin>adminList = FXCollections.observableArrayList(admin);
			session.getTransaction().commit();
			
			col_id_a.setCellValueFactory(new PropertyValueFactory<Admin,Integer>("id_admin"));
			col_fname_a.setCellValueFactory(new PropertyValueFactory<Admin,String>("firstname_admin"));
			col_lname_a.setCellValueFactory(new PropertyValueFactory<Admin,String>("lastname_admin"));
			col_email_a.setCellValueFactory(new PropertyValueFactory<Admin,String>("email_admin"));
			col_gender_a.setCellValueFactory(new PropertyValueFactory<Admin,String>("gender_admin"));
			col_age_a.setCellValueFactory(new PropertyValueFactory<Admin,Integer>("age_admin"));
			col_telephone_a.setCellValueFactory(new PropertyValueFactory<Admin,String>("telephone_admin"));
			col_password_a.setCellValueFactory(new PropertyValueFactory<Admin,String>("password_admin"));
			
			tableAdmin.setItems(adminList);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	// Event Listener on Button.onAction
	@FXML
	public void btnAdd_admin(ActionEvent event) {
		if(text_firstname_a.getText()!="" && text_lastname_a.getText()!="" && text_email_a.getText()!="" && text_gender_a.getText()!="" 
				&& text_age_a.getText()!="" && text_telephone_a.getText()!="" && text_password_a.getText()!="" ) {
			
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Admin.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Admin admin = new Admin(text_firstname_a.getText(),text_lastname_a.getText()
						,text_email_a.getText(),text_gender_a.getText(),Integer.parseInt(text_age_a.getText())
						,text_telephone_a.getText(),text_password_a.getText());
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				//save the student object
				session.save(admin);
				
				initialize(null,null);
				//commit transaction
				session.getTransaction().commit();
			}finally {
				Alert a = new Alert(Alert.AlertType.NONE,"เพิ่มข้อมูลสำเร็จ",ButtonType.OK);
				a.show();
				factory.close();
			}
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnEdit_admin(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Admin.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Admin admin = session.get(Admin.class,Integer.parseInt(text_id_a.getText()));
			
			admin.setFirstname_admin(text_firstname_a.getText());
			admin.setLastname_admin(text_lastname_a.getText());
			admin.setEmail_admin(text_email_a.getText());
			admin.setGender_admin(text_gender_a.getText());
			admin.setAge_admin(Integer.parseInt(text_age_a.getText()));
			admin.setTelephone_admin(text_telephone_a.getText());
			admin.setPassword_admin(text_password_a.getText());
			
			initialize(null,null);
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Alert a = new Alert(Alert.AlertType.NONE,"แก้ไขข้อมูลสำเร็จ",ButtonType.OK);
			a.show();
			factory.close();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnDelete_admin(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Admin.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Admin admin = session.get(Admin.class,Integer.parseInt(text_id_a.getText()));
			
			session.delete(admin);
			
			initialize(null,null);
			//commit transaction
			session.getTransaction().commit();
		}finally {
			Alert a = new Alert(Alert.AlertType.NONE,"ลบข้อมูลสำเร็จ",ButtonType.OK);
			a.show();
			factory.close();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnSearch_admin(ActionEvent event) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Admin.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Admin>admin = session.createQuery("from Admin").list();
			admin = session.createQuery("from Admin a where a.id_admin='"+text_search_a.getText()+
					"' OR a.firstname_admin='"+text_search_a.getText()+
					"' OR a.lastname_admin='"+text_search_a.getText()+
					"' OR a.email_admin='"+text_search_a.getText()+
					"' OR a.gender_admin='"+text_search_a.getText()+
					"' OR a.age_admin='"+text_search_a.getText()+
					"' OR a.telephone_admin='"+text_search_a.getText()+
					"' OR a.password_admin='"+text_search_a.getText()+"'").list();
			
			ObservableList<Admin>adminList = FXCollections.observableArrayList(admin);
			tableAdmin.setItems(FXCollections.observableArrayList(adminList));
			
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
	@FXML
	public void rowSelect(MouseEvent event) {
		Admin click = tableAdmin.getSelectionModel().getSelectedItem();
		text_id_a.setText(String.valueOf(click.getId_admin()));
		text_firstname_a.setText(String.valueOf(click.getFirstname_admin()));
		text_lastname_a.setText(String.valueOf(click.getLastname_admin()));
		text_email_a.setText(String.valueOf(click.getEmail_admin()));
		text_gender_a.setText(String.valueOf(click.getGender_admin()));
		text_age_a.setText(String.valueOf(click.getAge_admin()));
		text_telephone_a.setText(String.valueOf(click.getTelephone_admin()));
		text_password_a.setText(String.valueOf(click.getPassword_admin()));
	}
	
	@FXML
	public void refreshTable(MouseEvent event) {

		text_id_a.clear();
		text_firstname_a.clear();
		text_lastname_a.clear();
		text_email_a.clear();
		text_gender_a.clear();
		text_age_a.clear();
		text_telephone_a.clear();
		text_password_a.clear();
			
		initialize(null,null);
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnScenePhone(ActionEvent event) throws IOException{
		m.changeSceneToSystem("Smartphone.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnSceneLaptop(ActionEvent event) throws IOException{
		m.changeSceneToSystem("Laptop.fxml");
	}
	// Event Listener on Button.onAction
	@FXML
	public void btnSceneGG(ActionEvent event) throws IOException{
		m.changeSceneToSystem("GamingGear.fxml");
	}
}

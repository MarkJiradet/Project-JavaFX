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
import com.information.hql.entity.Huawei;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class HuaweiController implements Initializable{
	@FXML
	private TableView <Huawei>table;
	@FXML
	private TableColumn<Huawei,String> col_id;
	@FXML
	private TableColumn<Huawei,String> col_name;
	@FXML
	private TableColumn <Huawei,String>col_brand;
	@FXML
	private TableColumn<Huawei,String> col_size;
	@FXML
	private TableColumn<Huawei,Integer> col_price;
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
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Huawei.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Huawei>huawei = session.createQuery("from Huawei").list();
			ObservableList<Huawei>huaweiList = FXCollections.observableArrayList(huawei);
			session.getTransaction().commit();
			
			col_id.setCellValueFactory(new PropertyValueFactory<Huawei,String>("id_h"));
			col_name.setCellValueFactory(new PropertyValueFactory<Huawei,String>("name_h"));
			col_brand.setCellValueFactory(new PropertyValueFactory<Huawei,String>("brand_h"));
			col_size.setCellValueFactory(new PropertyValueFactory<Huawei,String>("size_h"));
			col_price.setCellValueFactory(new PropertyValueFactory<Huawei,Integer>("price_h"));
			
			table.setItems(huaweiList);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void btnAdd(ActionEvent event) {
		if(text_id.getText()!="" && text_name.getText()!="" && text_brand.getText()!="" && text_price.getText()!="") {
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Huawei.class).buildSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				Huawei huawei = new Huawei(text_id.getText(),text_name.getText(),text_brand.getText(),text_size.getText()
						,Integer.parseInt(text_price.getText()));
				
				//start a transaction
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				session.save(huawei);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Huawei.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Huawei huawei = session.get(Huawei.class,text_id.getText());
			
			huawei.setId_h(text_id.getText());
			huawei.setName_h(text_name.getText());
			huawei.setBrand_h(text_brand.getText());
			huawei.setSize_h(text_size.getText());
			huawei.setPrice_h(Integer.parseInt(text_price.getText()));
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Huawei.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Huawei huawei = session.get(Huawei.class,text_id.getText());
			//delete
			session.delete(huawei);
			
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Huawei.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
	
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Huawei>huawei = session.createQuery("from Huawei").list();
			huawei = session.createQuery("from Huawei h where h.id_h='"+text_search.getText()+
					"' OR h.name_h='"+text_search.getText()+
					"' OR h.brand_h='"+text_search.getText()+
					"' OR h.size_h='"+text_search.getText()+
					"' OR h.price_h='"+text_search.getText()+"'").list();
			
			ObservableList<Huawei>huaweiList = FXCollections.observableArrayList(huawei);
			table.setItems(FXCollections.observableArrayList(huaweiList));
			
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
		Huawei click = table.getSelectionModel().getSelectedItem();
		text_id.setText(String.valueOf(click.getId_h()));
		text_name.setText(String.valueOf(click.getName_h()));
		text_brand.setText(String.valueOf(click.getBrand_h()));
		text_size.setText(String.valueOf(click.getSize_h()));
		text_price.setText(String.valueOf(click.getPrice_h()));
	}
	
	@FXML
	public void btnApple(ActionEvent event) throws IOException{
		m.changeScene("/fxml/Apple.fxml");
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

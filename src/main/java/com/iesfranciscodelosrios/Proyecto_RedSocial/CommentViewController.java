package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.CommentDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * Clase CommentViewController
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey  
 *
 */
public class CommentViewController implements Initializable {
	@FXML
	private Label name;
	
	@FXML
	private Label post;
	
	@FXML
	private ImageView image1;
	
	@FXML
	private ImageView image2;
	
	@FXML
	private GridPane commentGrid;
	
	@FXML
	private AnchorPane a1;
	
	@FXML
	private TextArea text;
	
	private List<CommentDAO> comment;
	
	CommentDAO cDAO = new CommentDAO();
	PostDAO pDAO = new PostDAO();
	
	/**
	 * Método para cambiar al FXML de 'Main'
	 * @throws IOException Excepción en caso de que exista
	 */
	@FXML
	private void switchToMain() throws IOException {
		App.setRoot("MenuPrincipal");
	}
	
	/**
	 * Método para cambiar al FXML de 'Profile'
	 * @throws IOException Excepción en caso de que exista
	 */
	@FXML
	private void switchToProfile() throws IOException {
		App.setRoot("Perfil");
	}
	
	/**
	 * Método para cambiar al FXML de 'Login'
	 * @throws IOException Excepción en caso de que exista
	 */
	@FXML
	private void switchToConf() throws IOException {
		App.setRoot("Conf");
	}
	
	/**
	 * Método para cambiar al FXML de 'Login'
	 * @throws IOException Excepción en caso de que exista
	 */
	@FXML
	private void switchToLogin() throws IOException {
		App.setRoot("Login");
	}
	
	/**
	 * Método para mostrar el AnchorPane para añadir un comentario
	 */
	@FXML
	private void showAddComment() {
		a1.setVisible(true);
	}
	
	/**
	 * Método para crear un nuevo comentario 
	 */
	@FXML
	private void AddComment() {
		String message = text.getText();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = DataService.userLogeado;
		Post post = DataService.p;
		CommentDAO c = new CommentDAO(-1, message, timestamp, user, post);
		c.create();
		a1.setVisible(false);
	}
	
	/**
	 * Método que ejecuta todas las instrucciones contenidas al inicializar
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comment = new ArrayList<>(comments());
		
		int columns = 0;
		int row = 1;
		
		try {
			for (int i = 0; i < comment.size(); i++) {
				FXMLLoader f = new FXMLLoader();
				f.setLocation(getClass().getResource("comment.fxml"));
				AnchorPane a = f.load();
				CommentController c = f.getController();
				c.setData(comment.get(i));
				
				if (columns == 1) {
					columns = 0;
					++row;
				}
				
				commentGrid.add(a, columns++, row);
				GridPane.setMargin(a, new Insets(10));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		name.setText(DataService.p.getUser().getNickname());
		post.setText(DataService.p.getText());
	}
	
	/**
	 * Método para obtener una lista de comentarios de un post en concreto
	 * @return Lista de comentarios
	 */
	private List<CommentDAO> comments() {
		List<CommentDAO> list = cDAO.getAllCommentsByIdPost(DataService.p.getId());
		
		return list;
	}
}

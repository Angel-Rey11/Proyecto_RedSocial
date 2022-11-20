package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Dialog;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Loggers;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.CommentDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Clase CommentController
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey  
 *
 */
public class CommentController {
	private CommentDAO cd;
	@FXML
	private Label name;
	@FXML
	private Label comment2;
	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;
	@FXML
	private Button mg;
	@FXML
	private Button dmg;
	@FXML
	private Label fecha;
	@FXML
	private AnchorPane an;
	@FXML
	private TextArea ta;
	
	/**
	 * Método para setear los datos de un comentario
	 * @param c Comentario del que se obtiene la información
	 */
	public void setData(CommentDAO c) {
		name.setText(c.getUser().getNickname()); 
		comment2.setText(c.getText());
		this.cd = c;
		String s = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(c.getDate());
		fecha.setText(s);
	}
	
	/**
	 * Método que realiza la funcionalidad de 'Me Gusta'
	 */
	@FXML
	private void mg() {
		img1.setVisible(false);
		img2.setVisible(true);
		mg.setDisable(true);
		dmg.setDisable(false);
	}
	
	/**
	 * Método que realiza la funcionalidad de 'No Me Gusta'
	 */
	@FXML
	private void dmg() {
		img1.setVisible(true);
		img2.setVisible(false);
		dmg.setDisable(true);
		mg.setDisable(false);
	}
	
	/**
	 * Método para eliminar un comentario
	 */
	@FXML
	private void deleteComment() {
		cd.delete();
		Dialog.showConfirm("OPERACIÓN EXITOSA", "COMENTARIO ELIMINADO", "El comentario se ha eliminado correctamente");
		Loggers.LogsInfo("COMENTARIO ELIMINADO");
	}
	
	/**
	 * Método para mostrar el AnchorPane del comentario modificado
	 */
	@FXML
	private void showModifyComment() {
		an.setVisible(true);
		ta.setText(this.cd.getText());
		Loggers.LogsInfo("MODIFICANDO COMENTARIO EXISTENTE");
	}
	
	/**
	 * Método para modicar un comentario
	 */
	@FXML
	private void modifyComment() {
		if (DataService.userLogeado.getId() == this.cd.getUser().getId()) {
			if (!ta.getText().equals("")) {
				Timestamp date = new Timestamp(System.currentTimeMillis());;
				CommentDAO newComment = new CommentDAO(this.cd.getId(),ta.getText(),date,DataService.userLogeado,DataService.p);
				newComment.update();
				an.setVisible(false);
			} else {
				Dialog.showError("ERROR", "MOFICIACIÓN ERRÓNEA", "El campo texto debe ser rellenado");
				Loggers.LogsSevere("ERROR. NO SE HA PODIDO MOFICAR EL COMENTARIO.");
			}
		} else {
			Dialog.showError("ERROR","MODIFICACIÓN ERRÓNEA", "Este comentario solo puede ser moficado por el autor");
		}
	}
}

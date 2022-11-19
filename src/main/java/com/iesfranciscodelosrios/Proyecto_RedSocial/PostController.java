package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.LikeDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PostController implements Initializable {
	private PostDAO post;
	private LikeDAO like;
	@FXML
	private Label name;
	@FXML
	private Label post2;
	@FXML
	private Label id;
	@FXML
	private ImageView img1; //Corazon vacio
	@FXML
	private ImageView img2; //Corazon lleno
	@FXML
	private Button mg; //Me gusta
	@FXML
	private Button dmg; //No me gusta

	@FXML
	private void mg() {
		mg.setDisable(true);
		dmg.setDisable(false);
		img1.setVisible(false);
		img2.setVisible(true);
		/*like = new LikeDAO(-1,DataService.userLogeado,post.find(this.post.getId()));
		System.out.println(this.post.getId());
		if(like.create()){
			mg.setDisable(true);
			dmg.setDisable(false);
			img1.setVisible(false);
			img2.setVisible(true);
		}*/
	}

	@FXML
	private void dmg() {
		mg.setDisable(false);
		dmg.setDisable(true);
		img1.setVisible(true);
		img2.setVisible(false);
		/*like = new LikeDAO(-1,DataService.userLogeado,this.post);
		if(like.delete()){
			mg.setDisable(false);
			dmg.setDisable(true);
			img1.setVisible(true);
			img2.setVisible(false);
		}*/
	}

	public void setData(PostDAO post) {
		name.setText(post.getUser().getNickname());
		post2.setText(post.getText());
		this.post = post;
	}

	@FXML
	private void switchToComments() throws IOException {
		DataService.p = this.post;
		App.setRoot("CommentView");
	}

	@FXML
	private void switchToProfile() throws IOException {
		DataService.pAux = this.post;
		App.setRoot("PerfilAux");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*if (this.like.getAllLikesbyPost(this.post.getId()).contains(DataService.userLogeado)) {
			img1.setVisible(false);
			img2.setVisible(true);
			mg.setDisable(true);
			dmg.setDisable(false);
		}else{
			img1.setVisible(true);
			img2.setVisible(false);
			mg.setDisable(false);
			dmg.setDisable(true);
		}*/
	}
}

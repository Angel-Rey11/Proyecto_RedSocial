package com.iesfranciscodelosrios.Proyecto_RedSocial.Assets;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;


public class DataService {

	public static UserDAO userLogeado = new UserDAO();
	public static PostDAO p = new PostDAO();
}
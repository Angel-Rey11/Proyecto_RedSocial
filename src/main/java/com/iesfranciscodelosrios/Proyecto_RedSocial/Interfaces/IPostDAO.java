package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;

public interface IPostDAO {
    boolean create();
    boolean delete();
    boolean update();
    PostDAO find(int id);
}

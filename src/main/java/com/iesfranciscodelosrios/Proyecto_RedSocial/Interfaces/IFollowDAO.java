package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;


import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.FollowDAO;


public interface IFollowDAO {
    boolean create();
    boolean delete(int id);
    boolean update();
    FollowDAO find(int id);

}

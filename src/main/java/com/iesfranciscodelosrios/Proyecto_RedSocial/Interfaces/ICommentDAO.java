package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

import java.util.List;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.CommentDAO;

public interface ICommentDAO {
    boolean create();
    boolean delete();
    boolean update();
    CommentDAO find(int id);
    List<CommentDAO> getAllCommentsByIdPost(int id);
}
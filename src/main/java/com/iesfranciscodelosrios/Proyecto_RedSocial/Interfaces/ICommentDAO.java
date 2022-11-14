package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

public interface ICommentDAO {
    boolean create(ICommentDAO c);
    boolean delete(ICommentDAO c);
    boolean update(ICommentDAO c);
    ICommentDAO find(ICommentDAO c);
}
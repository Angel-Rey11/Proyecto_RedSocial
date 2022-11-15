package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import java.util.List;

public interface IUserDAO {
    boolean create(User u);
    boolean delete(User u);
    boolean update(User u);
    List<User> getAllFollower();
    List<User> getAllFollowing();
    boolean follow(User u, User u2);
    boolean unfollow(User u, User u2);
    boolean like(User u, IPostDAO p);
    boolean unlike(User u, IPostDAO p);
    IUserDAO find(User u);

}

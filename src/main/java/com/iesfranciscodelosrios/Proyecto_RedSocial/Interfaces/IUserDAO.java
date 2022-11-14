package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

import java.util.List;

public interface IUserDAO {
    boolean create(IUserDAO u);
    boolean delete(IUserDAO u);
    boolean update(IUserDAO u);
    IUserDAO find(IUserDAO u);
    List<IUserDAO> getAllFollower();
    List<IUserDAO> getAllFollowing();
    boolean follow(IUserDAO u, IUserDAO u2);
    boolean unfollow(IUserDAO u, IUserDAO u2);
    boolean like(IUserDAO u, IPostDAO p);
    boolean unlike(IUserDAO u, IPostDAO p);
}

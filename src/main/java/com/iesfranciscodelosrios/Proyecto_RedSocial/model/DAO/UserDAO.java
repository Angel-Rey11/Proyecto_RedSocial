package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IPostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IUserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import java.util.List;

public class UserDAO extends User implements IUserDAO {
	
    @Override
    public boolean create(IUserDAO u) {
        return false;
    }

    @Override
    public boolean delete(IUserDAO u) {
        return false;
    }

    @Override
    public boolean update(IUserDAO u) {
        return false;
    }

    @Override
    public IUserDAO find(IUserDAO u) {
        return null;
    }

    @Override
    public List<IUserDAO> getAllFollower() {
        return null;
    }

    @Override
    public List<IUserDAO> getAllFollowing() {
        return null;
    }

    @Override
    public boolean follow(IUserDAO u, IUserDAO u2) {
        return false;
    }

    @Override
    public boolean unfollow(IUserDAO u, IUserDAO u2) {
        return false;
    }

    @Override
    public boolean like(IUserDAO u, IPostDAO p) {
        return false;
    }

    @Override
    public boolean unlike(IUserDAO u, IPostDAO p) {
        return false;
    }
}

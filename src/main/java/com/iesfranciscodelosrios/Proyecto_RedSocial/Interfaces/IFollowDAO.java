package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

import java.util.List;

public interface IFollowDAO {
    boolean create(IFollowDAO f);
    boolean delete(IFollowDAO f);
    boolean update(IFollowDAO f);
    IFollowDAO find(IFollowDAO f);
    List<IFollowDAO> getAllFollower();
    List<IFollowDAO> getAllFollowing();

}

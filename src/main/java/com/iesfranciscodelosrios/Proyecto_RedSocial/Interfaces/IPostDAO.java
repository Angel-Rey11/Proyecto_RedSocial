package com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces;

public interface IPostDAO {
    boolean create(IPostDAO p);
    boolean delete(IPostDAO p);
    boolean update(IPostDAO p);
    IPostDAO find(IPostDAO p);
}

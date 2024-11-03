package com.alesandro.instagramhibernate.model.dao;

import com.alesandro.instagramhibernate.model.entity.Contenido;
import com.alesandro.instagramhibernate.model.entity.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DaoUsuario {
    public void insertar(Usuario usuario, Session session) {
        Transaction transaction = null;
        // Obtener la sesión de Hibernate
        transaction = session.beginTransaction(); // Iniciar la transacción
        session.persist(usuario); // Guardar el objeto Olimpiada
        transaction.commit(); // Confirmar la transacción
    }

    public List<Usuario> obtenerTodos(Session session) {
        String hql = "FROM Usuario";
        Query<Usuario> query = session.createQuery(hql, Usuario.class);
        return query.getResultList();
    }

    public Usuario obtenerUsuarioPorEmail(String email, Session session) {
        String hql = "FROM Usuario WHERE email = :email";
        Query<Usuario> query = session.createQuery(hql, Usuario.class);
        query.setParameter("email", email);
        return query.uniqueResult(); // Devuelve null si no existe
    }

    public void aniadirContenido(Contenido contenido, Usuario usuario, Session session) {
        Transaction transaction = session.beginTransaction();
        usuario.setContenido(contenido);
        session.merge(usuario);
        transaction.commit();
    }
}

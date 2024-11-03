package com.alesandro.instagramhibernate.model.dao;

import com.alesandro.instagramhibernate.model.entity.Contenido;
import com.alesandro.instagramhibernate.model.entity.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DaoContenido {
    public void insertar(Contenido contenido, Session session) {
        Transaction transaction = null;
        // Obtener la sesión de Hibernate
        transaction = session.beginTransaction(); // Iniciar la transacción
        session.persist(contenido); // Guardar el objeto Olimpiada
        transaction.commit(); // Confirmar la transacción
    }

    public List<Contenido> obtenerContenidosPorUsuario(Usuario usuario, Session session) {
        String hql = "FROM Contenido c JOIN c.usuarios u WHERE u.id_usuario = :id_usuario";
        List<Contenido> contenidos = session.createSelectionQuery(hql, Contenido.class)
                .setParameter("id_usuario", usuario.getId_usuario())
                .getResultList();
        return contenidos;
    }

    public Contenido obtenerContenidoPorNombre(String nombre, Session session) {
        String hql = "FROM Contenido WHERE nombre = :nombre";
        Query<Contenido> query = session.createQuery(hql, Contenido.class);
        query.setParameter("nombre", nombre);
        return query.uniqueResult(); // Devuelve null si no existe
    }

    public void insertartRecomendacion(Usuario usuario, Contenido contenido, Session session) {
        Transaction transaction = session.beginTransaction();
        usuario.setContenido(contenido);
        contenido.setUsuario(usuario);
        session.persist(contenido);
        transaction.commit();
    }

    public List<Contenido> findAll(Session session) {
        // Consulta JPQL para obtener todos los contenidos
        return session.createSelectionQuery("SELECT c FROM Contenido c", Contenido.class).getResultList();
    }

}

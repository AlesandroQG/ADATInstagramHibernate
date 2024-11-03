package com.alesandro.instagramhibernate.model.dao;

import com.alesandro.instagramhibernate.model.entity.CuentaCreadora;
import com.alesandro.instagramhibernate.model.entity.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DaoCuentaCreadora {
    public void insertar(CuentaCreadora cuentaCreadora, Session session) {
        Transaction transaction = null;
        // Obtener la sesión de Hibernate
        transaction = session.beginTransaction(); // Iniciar la transacción
        session.persist(cuentaCreadora); // Guardar el objeto Olimpiada
        transaction.commit(); // Confirmar la transacción
    }

    public CuentaCreadora obtenerCuentasPorNombre(String cuenta, Session session) {
        String hql = "FROM CuentaCreadora WHERE cuenta = :cuenta";
        Query<CuentaCreadora> query = session.createQuery(hql, CuentaCreadora.class);
        query.setParameter("cuenta", cuenta);
        return query.uniqueResult(); // Devuelve null si no existe
    }

    public List<CuentaCreadora> obtenerCuentasPorUsuario(Usuario usuario, Session session) {
        String hql = "FROM CuentaCreadora cc JOIN cc.contenidos c JOIN c.usuarios u WHERE cc.id = c.id AND u.id_usuario = :id_usuario";
        List<CuentaCreadora> cuentas = session.createSelectionQuery(hql, CuentaCreadora.class)
                .setParameter("id_usuario", usuario.getId_usuario())
                .getResultList();
        return cuentas;
    }

}

package com.alesandro.instagramhibernate;

import com.alesandro.instagramhibernate.model.entity.*;
import com.alesandro.instagramhibernate.model.dao.*;
import org.hibernate.Session;

import java.io.*;
import java.util.List;

public class GestionInstagram {
    private static final String FICHERO = "ficheros/instagram_posts_with_location.csv";

    private static double validateDouble(String value) {
        if (value.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(value);
    }

    private static String validateString(String value) {
        if (value.isEmpty()) {
            return "N/A";
        }
        return value.strip();
    }

    public static void cargarCsv() {
        // nombre,mail,edad,tag,tiempo,cuenta_instagram,año_creación,ubicacion,nombre_publi
        File f = new File(FICHERO);
        Session session = HibernateUtil.getSessionFactory().openSession();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoContenido daoContenido = new DaoContenido();
        DaoCuentaCreadora daoCuentaCreadora = new DaoCuentaCreadora();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea = br.readLine();
            linea = br.readLine();
            while (linea!=null && !linea.isEmpty()) {
                String[] partes = linea.split(",");
                String nombre = partes[0].strip();
                String email = partes[1].strip();
                double edad = validateDouble(partes[2]);
                String tag = validateString(partes[3]);
                double tiempo = validateDouble(partes[4]);
                String cuenta_instagram = partes[5].strip();
                double anio = validateDouble(partes[6]);
                String ubicacion = validateString(partes[7]);
                String nombre_publi = partes[8].replace("\"", "").strip();
                Usuario usuario = daoUsuario.obtenerUsuarioPorEmail(email, session);
                if (usuario == null) {
                    usuario = new Usuario(email, nombre, edad);
                    daoUsuario.insertar(usuario, session);
                    usuario = daoUsuario.obtenerUsuarioPorEmail(email, session);
                }
                CuentaCreadora cuentaCreadora = daoCuentaCreadora.obtenerCuentasPorNombre(cuenta_instagram, session);
                if (cuentaCreadora == null) {
                    cuentaCreadora = new CuentaCreadora(cuenta_instagram,anio);
                    daoCuentaCreadora.insertar(cuentaCreadora, session);
                    cuentaCreadora = daoCuentaCreadora.obtenerCuentasPorNombre(cuenta_instagram, session);
                }
                Contenido contenido = daoContenido.obtenerContenidoPorNombre(nombre_publi, session);
                if (contenido == null) {
                    contenido = new Contenido(nombre_publi, tag, tiempo, ubicacion, cuentaCreadora);
                    daoContenido.insertar(contenido, session);
                    daoUsuario.aniadirContenido(contenido, usuario, session);
                    contenido = daoContenido.obtenerContenidoPorNombre(nombre_publi, session);
                }
                daoContenido.insertartRecomendacion(usuario, contenido, session);
                linea = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoContenido daoContenido = new DaoContenido();
        DaoCuentaCreadora daoCuentaCreadora = new DaoCuentaCreadora();
        List<Usuario> usuarios = daoUsuario.obtenerTodos(session);
        for (Usuario usuario:usuarios) {
            System.out.println(usuario);
            List<CuentaCreadora> cuentas = daoCuentaCreadora.obtenerCuentasPorUsuario(usuario, session);
            System.out.println("Cuentas:");
            for (CuentaCreadora cuenta:cuentas) {
                System.out.println("\t- " + cuenta);
            }
            System.out.println("\n");
            List<Contenido> contenidos = daoContenido.obtenerContenidosPorUsuario(usuario, session);
            System.out.println("Contenidos:");
            for (Contenido contenido:contenidos) {
                System.out.println("\t- " + contenido);
            }
            System.out.println("\n\n");
        }
    }

    public static void main(String[] args) {
        //cargarCsv();
        // Select de todos datos
        selectAll();
    }
}
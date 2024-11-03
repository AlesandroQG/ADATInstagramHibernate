package com.alesandro.instagramhibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();
                if (registry == null) {
                    System.out.println("Registry null");
                }

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);
                // sources.addAnnotatedClass(Employee.class);
                if (sources == null) {
                    System.out.println("sources null");
                }
                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();
                if (metadata == null) {
                    System.out.println("metadata null");
                }
                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
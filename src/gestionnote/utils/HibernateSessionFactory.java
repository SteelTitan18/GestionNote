package gestionnote.utils;



import static java.util.Objects.isNull;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author djim
 */
public final class HibernateSessionFactory {
//    private static final Logger LOGGER = LogManager.getLogger(HibernateSessionFactory.class);

    // A SessionFactory is set up once for an application!
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (isNull(sessionFactory)) {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
//                    .loadProperties("conf.properties")
                     .configure() /* configures settings from hibernate.cfg.xml placed in resources folder */
                    .build();

            try {
                MetadataSources metadataSources = new MetadataSources(standardRegistry);
                AnnotatedClassCollection.getAnnotatedClasses().forEach(metadataSources::addAnnotatedClass);
                sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
            } catch (Exception e) {
//                LOGGER.error(e);
                // The registry would be destroyed by the SessionFactory, but we 
                // had trouble building the SessionFactory
                // so destroy it manually.
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy(standardRegistry);
            }
        }
        return sessionFactory;
    }

    private HibernateSessionFactory() {
        throw new AssertionError("No need to instantiate this class !");
    }
}

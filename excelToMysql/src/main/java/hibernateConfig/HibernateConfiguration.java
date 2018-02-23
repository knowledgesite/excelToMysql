package hibernateConfig;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entity.Person;
import entity.Sample;


public class HibernateConfiguration {
	private Configuration configuration = null;
	private SessionFactory factory = null;
	private StandardServiceRegistry registry = null;

	public HibernateConfiguration() {
		if (configuration == null) {
			configuration = new Configuration().configure();
			addEntityClass(configuration);
		}
	}
	
	public SessionFactory getSessionFactory() {
		if (registry == null) {
			registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		}
		if (factory == null) {
			factory = configuration.buildSessionFactory(registry);
		}

		return factory;
	}
	
	public Configuration addEntityClass(Configuration configuration) {

		configuration.addAnnotatedClass(Sample.class);
		configuration.addAnnotatedClass(Person.class);
		return configuration;

	}
	
}

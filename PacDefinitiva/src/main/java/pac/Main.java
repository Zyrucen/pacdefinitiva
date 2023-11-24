package pac;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {

		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg
				.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
		Session session = sessionFactory.openSession();


		// Llamar metodos para insertar modulos, profesores y alumnos
		meterModulos(session);
		meterProfesor(session);
		meterAlumnos(session);
		
		
		// Cerrar sesion de Hibernate
		sessionFactory.close();
		session.close();

	}
	
	// Metodo para insertar modulos en la base de datos

	public static void meterModulos(Session session) {
		Transaction tx = session.beginTransaction();
		try {

			// Crear modulos y los sube a la base de datos con persist
			Modulo modulo1 = new Modulo();
			modulo1.setNombre("programacion B");
			modulo1.setCodigo("M03B");
			session.persist(modulo1);
			System.out.println(modulo1.toString());

			Modulo modulo2 = new Modulo();
			modulo2.setNombre("Acceso a Datos");
			modulo2.setCodigo("M06");
			session.persist(modulo2);
			System.out.println(modulo2.toString());

			Modulo modulo3 = new Modulo();
			modulo3.setNombre("Desarrollo de Interfaces");
			modulo3.setCodigo("M07");
			session.persist(modulo3);
			System.out.println(modulo3.toString());

			Modulo modulo4 = new Modulo();
			modulo4.setNombre("Proyecto DAM");
			modulo4.setCodigo("M13");
			session.persist(modulo4);
			System.out.println(modulo4.toString());

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

	}

	public static void meterProfesor(Session session) {
		Transaction tx = session.beginTransaction();
		try {
			
			// Crear profesores y los sube a la base de datos con persist
			Profesor profesor = new Profesor();
			profesor.setNombre("Hassabis");
			profesor.setSexo("Mujer");
			session.persist(profesor);

			tx.commit();
			System.out.println(profesor.toString());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

	}

	public static void meterAlumnos(Session session) {
		Transaction tx = session.beginTransaction();
		
		
		// Miro en la base de datos a ver los modulos 
		//para asignarlos a una variable y 
		//poder asignarlos a los alumnos.

		Modulo minsert1 = session.get(Modulo.class, 1L);
		Modulo minsert2 = session.get(Modulo.class, 2L);
		Modulo minsert3 = session.get(Modulo.class, 3L);
		Modulo minsert4 = session.get(Modulo.class, 4L);
		
		try {
		// Crear alumnos y los sube a la base de datos con persist
		// ----------------------------------------------------------------------------------------------
		Alumno alumno1 = new Alumno();
		alumno1.setNombre("Sandberg");
		alumno1.setNacionalidad("USA");
		alumno1.setEdad(26);
		alumno1.setSexo("Mujer");
		java.util.Set<Modulo> setalum1 = new java.util.HashSet<>();
		setalum1.add(minsert1);
		setalum1.add(minsert2);
		setalum1.add(minsert3);
		setalum1.add(minsert4);
		alumno1.setModulos(setalum1);
		session.persist(alumno1);
		
		// ------------------------------------------------------------------------------------
		Alumno alumno2 = new Alumno();
		alumno2.setNombre("Fei-Fei Li");
		alumno2.setNacionalidad("China");
		alumno2.setEdad(21);
		alumno2.setSexo("Mujer");
		java.util.Set<Modulo> setalum2 = new java.util.HashSet<>();
		setalum2.add(minsert1);
		setalum2.add(minsert2);
		setalum2.add(minsert3);
		alumno2.setModulos(setalum2);
		session.persist(alumno2);
		// ----------------------------------------------------------------------------------------------
		Alumno alumno3 = new Alumno();
		alumno3.setNombre("Sohpie Wilson");
		alumno3.setNacionalidad("Reino Unido");
		alumno3.setEdad(19);
		alumno3.setSexo("Mujer");
		java.util.Set<Modulo> setalum3 = new java.util.HashSet<>();
		setalum3.add(minsert3);
		setalum3.add(minsert4);
		alumno3.setModulos(setalum3);
		session.persist(alumno3);
		// ----------------------------------------------------------------------------------------------
		Alumno alumno4 = new Alumno();
		alumno4.setNombre("Aya Soffer");
		alumno4.setNacionalidad("Israel");
		alumno4.setEdad(35);
		alumno4.setSexo("Mujer");
		java.util.Set<Modulo> setalum4 = new java.util.HashSet<>();
		setalum4.add(minsert2);
		setalum4.add(minsert3);
		setalum4.add(minsert4);
		alumno4.setModulos(setalum4);
		session.persist(alumno4);
		// ----------------------------------------------------------------------------------------------

		tx.commit();

		System.out.println(alumno1.toString()+ ", modulos: " + setalum1.size());
		System.out.println(alumno2.toString()+ ", modulos: " + setalum2.size());
		System.out.println(alumno3.toString()+ ", modulos: " + setalum3.size());
		System.out.println(alumno4.toString()+ ", modulos: " + setalum4.size());

	} catch (Exception e) {
		tx.rollback();
		e.printStackTrace();
	}

}

}

package test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import beans.Student;

public class Client {

	public static void main(String[] args) {

		Student st=new Student();
		st.setId(111);
		st.setName("sravan");
		st.setEmail("s@gmail.com");
		st.setMarks(500);
//		Now its a transient object
		Configuration cfg=new Configuration();
		cfg.configure("resources/hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.save(st);  //returns serializable primary key
//	 Now Student object state is persistent
	    t.commit();
//		Student object will move to databases(permanent state) when commited
		s.saveOrUpdate(st);
//		This line will check the object data with table data with Pk(select * from Student where id=111)
//		 if it already exists(A record with same id in a table) it  will update or else insert a new record in the table
		s.persist(st); //returns void and same as save()
//		this line will also do the save 
		s.update(st);
//		
		s.evict(st);
//		Now Student object(Detached state) will be removed from persistent and the GC(remove from jvm) can collect our Student object
	}

}

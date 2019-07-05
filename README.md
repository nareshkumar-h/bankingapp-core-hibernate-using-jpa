# bankingapp-core-hibernate-using-jpa

#### Configuration Details ( resources/META-INF/persistence.xml)

```
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
        version="2.0">
    <persistence-unit name="myjpa-app" transaction-type="RESOURCE_LOCAL">
      <class>com.naresh.bankingapp.model.User</class>

        <properties>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/bankdb" />
            <property name="javax.persistence.jdbc.user" value="root" />
            <property name="javax.persistence.jdbc.password" value="root" />

            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
            <property name="hibernate.show_sql" value="true" />
            <!-- <property name="hibernate.hbm2ddl.auto" value="create" /> -->
        </properties>
        
	
    </persistence-unit>
</persistence>
```

#### JPAUtil - To get Connection
```
package com.naresh.bankingapp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf;

	private JPAUtil() {
	};

	static {

		try {
			emf = Persistence.createEntityManagerFactory("myjpa-app");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static EntityManager getEntityManager() {

		return emf.createEntityManager();
	}

}
```

#### Insert
```
public void insert(User user) throws DBException {
		
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			System.out.println("UserDAO->insert");
		} catch (HibernateException e) {
			throw new DBException("Unable to insert user", e);
		} 
	}
```

#### Update
```
em.merge(user);
```

#### Delete
```
em.remove(user);
```
#### List
```
Query createQuery = em.createQuery("from User u", User.class);
List<User> userList = createQuery.getResultList();
```

#### Find by id
```
int userId = 1;
User user = em.find(User.class, userId);
```
#### Find by Name
```
String name = "naresh";
Query createQuery = em.createQuery("from User u where name =?1", User.class);
createQuery.setParameter(1, name);
User user = (User) createQuery.getSingleResult();
```

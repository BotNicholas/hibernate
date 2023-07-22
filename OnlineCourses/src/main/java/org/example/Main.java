package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();

        //================GETTING SOME INFORMATION FROM DATABASE==========================

        //Getting all the students from the first Course
//        session.get(Course.class, 1).getStudents().forEach((s)-> System.out.println(s.getName()));

        //obtaining Subscription object
//        System.out.println(session.get(Subscription.class, new SubscriptionId(session.get(Student.class, 2), session.get(Course.class, 11))).getSubscriptionDate());

        //obtaining PurchaseList object
//        System.out.println(session.get(PurchaseList.class, new PurchaseListId("Фуриков Эрнст", "Data Scientist с 0 до PRO")).getPrice());


        //================USING CRITERIA API TO BUILD QUERIES==========================

        //Query Builder (Criteria API) (working with it when dynamic queries are required)

        //Obtaining all Courses
        //SQL Query: select * from Courses;
        //to build a query we need criteria (Criteria for a Query (CriteriaQuery) is obtaining from CriteriaBuilder object)
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Course> criteria = cb.createQuery(Course.class);

        Root<Course> from = criteria.from(Course.class); //Получаем источник (то отношение, из которого будем брать данные). Мы должны его явно установить, так как тут (CriteriaQuery<Course> criteria = cb.createQuery(Course.class)) он не устанавливается сам!
        // Это как from Courses в запросе SQl! (возвращаются все атрибуты (колонки) => не просто from Courses, а именно * from courses (если нужны именно конкретные, а не все, можно воспользоваться методом get, но уже в select-e).
        //При помощи метода get мы можем выбрать конкретные колонки для выборки)

        criteria.select(from); //говорим, что осуществляем выборку из этого источника.
        //Получается так, что мы добавляем к нашему критерию запроса различные составляющие.
        // Например, select. Также мы можем добавить и where, и group by, и order by и другие части запроса, которые мы можем использовать в обычном запросе
        //criteria.orderBy(cb.asc(from.get("name")));   -   итог: из CriteriaQuery мы получаем основные "части запросов", а уже из CriteriaBuilder мы получаем "дополнительные компоненты", такие, как вид сортировки, тип сравнения greatherThan или lessThan и т.д.
        //по итогу мы можем сделать вывод, что мы этим критерием попросту строим запрос к БД, но по частям. Именно по этой причине Criteria API применяется для динамических запросов!!!

        Query<Course> query = session.createQuery(criteria);//Фактически мы строим запрос по какому-либо критерию (так называемый критерий запроса (то есть и есть это самый select * from where))
        // (запрос с критерием), либо по какой-то строке запроса (HQL запрос). В случае запроса с критерием нам для начала нужно создать такой критерий запроса, из которого мы потом и
        // построим этот запрос. По сути тот же HQL query, но составляется модульно...

        //И вывод результата
        query.getResultList().forEach((c) -> System.out.println(c.getName()));

        System.out.println("----------------------");


        //Obtaining all the Students
        CriteriaQuery<Student> studentsCriteria = cb.createQuery(Student.class);

        Root<Student> fromStudents = studentsCriteria.from(Student.class);
        studentsCriteria.select(fromStudents);
//        studentsCriteria.select(fromStudents).where(cb.equal(fromStudents.get("id"), 1));

        Query<Student> studentsQuery = session.createQuery(studentsCriteria);

        List<Student> students = studentsQuery.getResultList();
        students.forEach((s) -> System.out.println(s.getName()));


        //================COMPLICATED QUERIES==========================
        //Obtaining first five courses that are over 100000
        System.out.println("--------------------COURSES OVER 100000---------------------");
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> root = criteriaQuery.from(Course.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.greaterThan(root.get("price"), 100000));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("price")));

        Query<Course> complicatedQuery = session.createQuery(criteriaQuery);

        complicatedQuery.setMaxResults(3).getResultList().forEach((c) -> System.out.println(c.getName() + " - " + c.getPrice()));


        //Obtaining all courses for Teacher with id = 1
        System.out.println("--------------------COURSES FOR 1'ST TEACHER---------------------");
        criteriaQuery = criteriaBuilder.createQuery(Course.class);

        root = criteriaQuery.from(Course.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("teacher"), session.get(Teacher.class, 1))); //=> поля берутся не из таблицы, но из класса-отношения

        complicatedQuery = session.createQuery(criteriaQuery);
        session.createQuery(criteriaQuery).getResultList().forEach((c) -> System.out.println(c.getName()));


        //================COMPLICATED QUERIES==========================
        System.out.println("\n\n===========================+HQL+===============================");

        //Selecting all courses using HQL
        System.out.println("--------------------ALL THE COURSES (via HQL)---------------------");
        String hql = "from Course";//Here we should use name of an entity but not of a table!!!
        Query<Course> hqlQuery = session.createQuery(hql);
//        OR
//        Query<Course> hqlQuery = session.createQuery(hql, Course.class);

        hqlQuery.getResultList().forEach((c) -> System.out.println(c.getName()));


        //============CREATING NEW TABLE (Entity) VIA HIBERNATE===============


        //Here I've changed the hibernate.cfg.xml "hbm2ddl.auto" property's value from "validate" to "update". Thus, if I will add new "Entity class" new table will be added in the Database!
        //Thus lets try to create a new entity named "LinkedPurchaseList", which will be created automatically by hibernate (because of auto value instead of validate) and will be filled...


            //After hibernate.cfg.xml property file has been changed and the entity has been created we can fill it...
        //firstly we are obtaining all the purchases
        hql = "from PurchaseList";
        Query<PurchaseList> hqlPL = session.createQuery(hql);
        List<PurchaseList> purchaseList = hqlPL.getResultList();

        //Now filling the table... To do that we have to obtain all the students and courses from purchase list:
        for(PurchaseList l: purchaseList){
            //Obtaining student from a single purchase
            hql = "from Student where name = '" + l.getStudentName() + "'";
            Query<Student> student = session.createQuery(hql);

            //Obtaining course from a single purchase
            hql = "from Course where name = '" + l.getCourseName() + "'";
            Query<Course> course = session.createQuery(hql);

            //checking
//            System.out.println(student.getResultList().get(0).getId() + "(" + student.getResultList().get(0).getName() + ")");
//            System.out.println(course.getResultList().get(0).getId() + "(" + course.getResultList().get(0).getName() + ")");


            //Saving data
            session.beginTransaction();

            try {
                session.save(new LinkedPurchaseList(new LinkedPurchaseListId(student.getResultList().get(0), course.getResultList().get(0))));

                session.getTransaction().commit();
            }catch(Exception e){
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
            }
    }


        sessionFactory.close();
        session.close();
    }

    public static SessionFactory getSessionFactory(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}
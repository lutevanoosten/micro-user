package com.luteflex.microservices.user.DataAccess;

import com.luteflex.microservices.user.Models.User;

import javax.persistence.*;

public class UserRepo {
    private EntityManager em;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("repoPU");

    private void createEm(){
        if (em == null)
            em = emf.createEntityManager();
    }

    public void create(User user) {
        createEm();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }


    public User login(User user) {
        createEm();

        try{
            TypedQuery<User> query = em.createQuery(
                    "select u from User u where u.email = :email and u.password = :password", User.class);
            query.setParameter("email", user.getEmail());
            query.setParameter("password", user.getPassword());
            return query.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }

    }
}

package SessionBean;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.User;

import javax.ejb.LocalBean;


/**
 * Session Bean implementation class User
 */
@Stateless
@LocalBean
public class UserService implements UserServiceRemote, UserServiceLocal {

	@PersistenceContext
    EntityManager em;
	
	public UserService() {
        
    }
	
	@Override
	public void add(User usr) {
		em.persist(usr);	
	}

	@Override
	public void delete(User usr) {
		em.remove(em.contains(usr) ? usr : em.merge(usr));
	}

	@Override
	public void edit(User usr) {
		em.merge(usr);		
	}
	@Override
	public void Update(User usr) {
	
	        em.merge(usr);
	        
	    }

	
	
	@Override
	public List<User> ListUsers() {
		Query q =  em.createQuery("select u from Users u");
	    return q.getResultList();
	}

	@Override
	public User FindById(int id) {
		return em.find(User.class,id);
	}
	
	@Override
	public User loginUser(String email, String password) 
	{	
		
			Query q = em.createQuery("select p from User p where p.email=:email and p.password2=:password");
			q.setParameter("email", email);
			q.setParameter("password", password);
			return (User) q.getSingleResult();
		
	}
	@Override
	public void updatePatient(User user) {
	User u = em.find(User.class, user.getId());
	u.setFirstName(user.getFirstName()); u.setLastName(user.getLastName());
	u.setAddress(user.getEmail()); u.setPhoneNumber(user.getPassword());
	
	}
}

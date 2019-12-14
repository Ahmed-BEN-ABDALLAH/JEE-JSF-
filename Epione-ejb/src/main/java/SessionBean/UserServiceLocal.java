package SessionBean;

import java.util.List;

import javax.ejb.Local;

import model.User;

@Local
public interface UserServiceLocal {
	void add(User usr);

	void delete(User usr);

	void edit(User usr);

	List<User> ListUsers();

	User FindById(int id);
    void Update(User usr) ;
	
	 User loginUser(String email, String password) ;
		public void updatePatient(User user) ;

}

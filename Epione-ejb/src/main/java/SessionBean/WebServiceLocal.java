package SessionBean;

import java.util.List;

import javax.ejb.Local;

import model.User;

@Local
public interface WebServiceLocal {
	List<model.User> getUsers();
	 

}

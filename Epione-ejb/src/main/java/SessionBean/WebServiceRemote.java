package SessionBean;

import java.util.List;

import javax.ejb.Remote;

import model.User;

@Remote
public interface WebServiceRemote {
	List<model.User> getUsers();
	 
}

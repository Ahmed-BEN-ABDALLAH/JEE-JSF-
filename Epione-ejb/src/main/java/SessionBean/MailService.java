package SessionBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class MailService
 */
@Stateless
@LocalBean
public class MailService implements MailServiceRemote, MailServiceLocal {

    /**
     * Default constructor. 
     */
    public MailService() {
        // TODO Auto-generated constructor stub
    }

}

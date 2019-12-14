package SessionBean;

import java.util.List;

import javax.ejb.Local;
import model.*;
@Local
public interface AppointementServiceLocal {
public  void Add( Appointment A) ;
public List<Intervention> ListReason(User u) ;
public String ConsommationMyAppointemeny(int id);
public String ConsommationMyVisit(int id);
public String ConsommationDelete(int id);
public List<Availability> HourList(String date , User u) ;
public void Update(Appointment a) ;
public Appointment MAxApp(User u) ;
public List<Appointment> SetSearchApp(String date , User u) ;
public List<Appointment> SetSearchVisit(String date , User u) ;
public void confirmAccount(int id) ;
public List<User> SetSearchDoc(String firstName , String lastName) ;
public List<User> Doctors() ;
public String deleteApp(int id) ;
public List<User> SetSearchAdresse(String VilleId) ;
public String deleteToutApp(int id) ;

}

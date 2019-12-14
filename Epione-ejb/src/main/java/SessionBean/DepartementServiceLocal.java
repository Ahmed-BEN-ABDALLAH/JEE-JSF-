package SessionBean;

import javax.ejb.Local;


@Local
public interface DepartementServiceLocal {
	public String ConsommationHematologie();
public String ConsommationRadio();
public String ConsommationDermatology();
public String ConsommationChirurigie();
public String ConsommationOphtalmo();
public String ConsommationDentaire();
public String ConsommationAnestesie();

}

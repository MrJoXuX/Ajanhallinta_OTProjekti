package Tietokanta.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class KayttajaAccessObject {
	SessionFactory istuntotehdas = Istuntotehdas.annaIstuntotehdas();
	Transaction transaktio = null;
	
	public boolean createKayttaja(Kayttaja kayttaja) {
		Session istunto = istuntotehdas.openSession();
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(kayttaja);
			transaktio.commit();
			return true;
		}catch(Exception e) {
			if(transaktio != null) 
				transaktio.rollback();
			throw e;
			
		}finally {
			istunto.close();
		}
	}

	public Kayttaja readKayttaja(String user_name) {
		Session istunto = istuntotehdas.openSession();
		try {
			transaktio = istunto.beginTransaction();
			Kayttaja kayttaja = new Kayttaja();
			istunto.load(kayttaja, user_name);
			transaktio.commit();
			return kayttaja;
		}catch(Exception e) {
			if(transaktio != null)
				transaktio.rollback();
			throw e;
		}finally {
			istunto.close();
		}
		
	}

	public Kayttaja[] readKayttajat() {
		Session istunto = istuntotehdas.openSession();
		try {
			transaktio = istunto.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Kayttaja> kayttajat = istunto.createQuery("from Kayttaja").getResultList();
			transaktio.commit();
			Kayttaja[] returnArray = new Kayttaja[kayttajat.size()];
			return (Kayttaja[]) kayttajat.toArray(returnArray);
		}catch (Exception e) {
			if(transaktio != null)
				transaktio.rollback();
			throw e;
		}finally {
			istunto.close();
		}
	}

	public boolean updateKayttaja(Kayttaja kayttaja) {
		Session istunto = istuntotehdas.openSession();
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(kayttaja);
			
			transaktio.commit();
			return true;
		}catch (Exception e) {
			if(transaktio != null)
				transaktio.rollback();
			throw e;
		}finally {
			istunto.close();
		}
	}

	public boolean deleteKayttaja(String user_name) {
		Session istunto = istuntotehdas.openSession();
		try {
			Kayttaja kayt = readKayttaja(user_name);
			transaktio = istunto.beginTransaction();
			istunto.delete(kayt);
			transaktio.commit();
			return true;
		}catch (Exception e) {
			if(transaktio != null)
				transaktio.rollback();
			throw e;
		}finally {
			istunto.close();
		}
	}
	
	public boolean updateSitting(String userName) {
		Session istunto = istuntotehdas.openSession();
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(userName);
			
			transaktio.commit();
			return true;
		}catch (Exception e) {
			if(transaktio != null)
				transaktio.rollback();
			throw e;
		}finally {
			istunto.close();
		}
	}
}

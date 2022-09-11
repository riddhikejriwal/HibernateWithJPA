package co.riddhi.booksmanager;

import java.util.List;

import co.riddhi.booksmanager.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class BooksManager {
	static EntityManagerFactory factory;
	static EntityManager entityManager;

	public static void main(String[] args) {
		begin();

		//create();
		update();
		//find();
		//query();
		//remove();

		end();
	}
	
	private static void remove() {
		Integer primaryKey=1;
		Book reference=entityManager.getReference(Book.class, primaryKey);
		entityManager.remove(reference);
	}
	
	private static void query() {
		String jpql="Select b from Book b Where b.price<480";
		Query query = entityManager.createQuery(jpql);
		List<Book> list = query.getResultList();
		for(Book book:list) {
			System.out.println(book.getTitle()+"::"+book.getAuthor()+"::"+book.getPrice());
		}
	}
	
	private static void update() {
		Book existBook = new Book();
		existBook.setBookId(3);
		existBook.setTitle("To kill a Mockingbird");
		existBook.setAuthor("Herper Lee");
		existBook.setPrice(490);
		entityManager.merge(existBook);
	}
	
	private static void find() {
		Integer primaryKey=1;
		Book book = entityManager.find(Book.class, primaryKey);
		System.out.println(book.getTitle()+"::"+book.getAuthor()+"::"+book.getPrice());
	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

	private static void begin() {
		factory = Persistence.createEntityManagerFactory("BookUnit");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	private static void create() {
		Book newBook = new Book();
		newBook.setTitle("Life is what you make it");
		newBook.setAuthor("Preety Shenoy");
		newBook.setPrice(450);

		entityManager.persist(newBook);
	}
	
	

}

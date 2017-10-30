package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

@Entity
public class Book extends Model {

    @Id
    private int id;

    private String title;

    private int price;

    private String author;


    public static Finder<Integer,Book> find = new Finder<>(Book.class);

    public Book(){}

    public Book(int id, String title, int price, String author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    /*private static Set<Book> books;


    static {
        books = new HashSet<Book>();
        books.add(new Book(1, "The Alchemist", 120, "Paulo Cohelo"));
        books.add(new Book(2, "Five point someone", 150, "Chethan Bhagat"));

    }


    public static Set<Book> getAllBooks(){
        return books;
    }

    public static Book getBookById( int id ){

        for( Book book : books ){

            if( book.id == id ){
                return book;
            }
        }

        return null;
    }


    public static boolean addBook(Book book){

        return books.add(book);
    }

    public static boolean deleteBook(Book book){

        return books.remove(book);
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



}

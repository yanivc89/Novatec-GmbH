package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import views.html.book.*;

import javax.inject.Inject;


public class BookController extends Controller {

    @Inject
    private FormFactory formFactory;


    public Result index(){

        List<Book> books = Book.find.all();
        return ok(bookstore.render(books));
    }

    public Result create(){

        Form<Book> bookForm = formFactory.form(Book.class);

        return ok(createbook.render(bookForm));
    }

    public Result save(){

        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();

        Book.db().save(book);

        return redirect(routes.BookController.index());
    }

    public Result edit(int id){

        Book book = Book.find.byId(id);
        Form<Book> bookForm = formFactory.form(Book.class).fill(book);

        if( book == null )
            return notFound("Book not found by this id");

        return ok(editbook.render(bookForm));
    }

    public Result view(int id){

        Book book = Book.find.byId(id);

        if( book == null )
            return notFound("Book not found by this id");

        return ok(viewbook.render(book));
    }

    public Result delete(int id){

        Book book = Book.find.byId(id);

        Book.db().delete(book);

        if( book == null )
            return notFound("Book not found by this id");

        return redirect(routes.BookController.index());
    }

    public Result update(){
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();

        Book oldBook = Book.find.byId(book.getId());

        if( oldBook == null )
            return notFound("Id of the book cannot be changed");
        else{
            oldBook.setTitle(book.getTitle());
            oldBook.setAuthor(book.getAuthor());
            oldBook.setPrice(book.getPrice());
            Book.db().update(oldBook);
        }

        return redirect(routes.BookController.index());

    }
}

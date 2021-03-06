package com.maxclay.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.maxclay.model.Book;

@Repository
public class BookDaoImpl implements BookDao {

	 
	private final MongoOperations mongoOperations;
	
	@Autowired
	public BookDaoImpl(MongoTemplate mongoTemplate) {
		
		this.mongoOperations = mongoTemplate;
		
	}
	
	@Override
	public void add(Book book) {
		
		mongoOperations.save(book);
	}

	@Override
	public Book get(String id) {
		
		return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), Book.class);
	}

	@Override
	public List<Book> getAll() {
		
		return mongoOperations.findAll(Book.class);
	}

	@Override
	public List<Book> getByTitle(String title) {
		
		return mongoOperations.find(Query.query(Criteria.where("title").is(title)), Book.class);
	}

	@Override
	public List<Book> getByAuthor(String author) {
		
		return mongoOperations.find(Query.query(Criteria.where("author").is(author)), Book.class);
	}

	@Override
	public List<Book> getByYear(short year) {
		
		return mongoOperations.find(Query.query(Criteria.where("year").is(year)), Book.class);
	}

	@Override
	public void delete(Book book) {
		
		mongoOperations.remove(book);
	}

	@Override
	public void delete(String id) {
		
		mongoOperations.remove(Query.query(Criteria.where("id").is(id)), Book.class);
	}

	@Override
	public List<Book> find(String... words) {
		
		List<Book> books = new ArrayList<Book>();

		for(String word : words) {
			
			Criteria criteria = new Criteria();
			criteria.orOperator(Criteria.where("description").regex(word, "i"), Criteria.where("title").regex(word, "i"));
			
			books.addAll(mongoOperations.find(Query.query(criteria), Book.class));
		}
		
		return books;
	}

	@Override
	public long count() {

		return mongoOperations.count(null, Book.class);
	}

	@Override
	public List<Book> get(int fromIndex, int toIndex) {

		long bookCount = count();
		if(toIndex > bookCount)
			toIndex = (int) bookCount;
		
		Query query =  new Query();
		query.skip(fromIndex);
		query.limit(toIndex - fromIndex);
		
		return mongoOperations.find(query, Book.class);
	}

}

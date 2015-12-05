package com.maxclay.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection = Book.COLLECTION_NAME)
public class Book implements Serializable {
	
	public static final String COLLECTION_NAME = "books";
	
	@Id
	private String id;
	private String source;

    private String title;
    private String author;
    private short year;
    private short pages;
    private String language;
    private String description;
    
    private String path;
    
    private List<Comment> comments;
    private HashMap<String, Integer> rates;
    
    public Book() {
    }

    public Book(String source, String title, String author, short year, short pages, 
    			String language, String description, String path, List<Comment> comments, HashMap<String, Integer> rates) {
        
    	this.source = source;
    	this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.language = language;
        this.description = description;
        this.path = path;
        this.comments = comments;
        this.rates = rates;

    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }
    
    public short getPages() {
        return pages;
    }

    public void setPages(short pages) {
        this.pages = pages;
    }
    
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public void setComments(List<Comment> comments) {
    	this.comments = comments;
    }
    
    public List<Comment> getComments() {
    	return comments;
    }
    
    public void addComment(Comment comment) {
    	
    	if(comments == null)
    		comments = new ArrayList<Comment>();
    	
    	comments.add(0, comment);
    }
    
	 public void setRates(HashMap<String, Integer> rates) {
		 this.rates = rates;
	 }
	 
	 public HashMap<String, Integer> getRates() {
		 return rates;
	 }
	 
	 public void rate(String userId, int score) {
		 if(rates == null)
			 rates = new HashMap<String, Integer>();
		 rates.put(userId, new Integer(score));
	 }

    @Override
    public String toString() {
        return String.format(
                "Book [\n id=%s,\n source_id='%s',\n title='%s',\n author='%s',\n year='%d',"
                + "\n pages='%d',\n language='%s,\n description='%s',\n image_path='%s']",
                id, source, title, author, year, pages, language, description, path);
    }

}

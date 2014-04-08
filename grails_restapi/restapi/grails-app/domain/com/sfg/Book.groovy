package com.sfg

class Book {

	long id
	String title
	String isbn
	int numberOfPages
	
    static constraints = {
		title blank:false
    }
}

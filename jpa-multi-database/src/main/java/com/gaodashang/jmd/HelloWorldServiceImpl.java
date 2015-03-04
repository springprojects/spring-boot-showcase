package com.gaodashang.jmd;

import com.gaodashang.jmd.book.Book;
import com.gaodashang.jmd.book.BookRepository;
import com.gaodashang.jmd.person.Person;
import com.gaodashang.jmd.person.PersonRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * comments.
 *
 * @author eva
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public String helloWorld() {
        Person person = new Person();
        person.setFirstName("ccc");
        personRepository.save(person);
        //logger.debug(personRepository.findAll());
        bookRepository.save(new Book());
        //logger.debug(bookRepository.findAll());

        return "Hello World.";
    }
}

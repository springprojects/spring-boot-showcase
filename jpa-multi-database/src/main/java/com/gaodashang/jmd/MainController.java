package com.gaodashang.jmd;

import com.gaodashang.jmd.book.Book;
import com.gaodashang.jmd.book.BookRepository;
import com.gaodashang.jmd.person.Person;
import com.gaodashang.jmd.person.PersonRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * comments.
 *
 * @author eva
 */
@Controller
public class MainController {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/helloWorld")
    @ResponseBody
    public String helloWorld() {
        personRepository.save(new Person());
        logger.debug(personRepository.findAll());
        bookRepository.save(new Book());
        logger.debug(bookRepository.findAll());
        return "Hello World.";
    }
}

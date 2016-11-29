package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.CrudCreateException;
import com.epam.newsmanagement.exception.CrudDeleteException;
import com.epam.newsmanagement.exception.CrudUpdateException;
import com.epam.newsmanagement.service.CrudService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addAuthors")
public class NewsmanagementAuthorsController {

    private static final Logger logger = Logger.getLogger(NewsmanagementAuthorsController.class);

    private static final String ITEMS_ON_PAGE = "itemsOnPage";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String ALL_AUTHORS = "allAuthors";
    private static final String SUCCESS = "success";

    private static final int ITEMS_ON_PAGINATION = 5;

    @Autowired
    private CrudService<Author> authorService;

    @RequestMapping()
    public String addAuthorsPage(Model model) {
        logger.info("NewsmanagementAuthorsController.addAuthorsPage()");

        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(TOTAL_COUNT, authorService.totalCount());
        model.addAttribute(ALL_AUTHORS, authorService.read(0, ITEMS_ON_PAGINATION));

        return "addAuthors";
    }

    @RequestMapping("/page-{pageNumber}")
    public String addAuthorsPagination(@PathVariable int pageNumber,
                                       Model model) {
        logger.info("NewsmanagementAuthorsController.addAuthorsPagination(" + pageNumber-- + ")");

        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(TOTAL_COUNT, authorService.totalCount());
        model.addAttribute(ALL_AUTHORS, authorService.read(ITEMS_ON_PAGINATION * pageNumber + 1, ITEMS_ON_PAGINATION * pageNumber + ITEMS_ON_PAGINATION));

        return "addAuthors";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addAuthors(@RequestParam("name") String name,
                             @RequestParam("surname") String surname) {
        logger.info("NewsmanagementAuthorsController.addAuthors(" + name + " , " + surname + ")");

        if (authorService.create(new Author(name, surname))) {
            return "redirect:/addAuthors";
        } else {
            throw new CrudCreateException(name + surname);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Author author) {
        logger.info("NewsmanagementAuthorsController.update(" + author.getId() + " , " + author.getName() + " , " + author.getSurname() + ")");

        if (authorService.update(author)) {
            return SUCCESS;
        } else {
            throw new CrudUpdateException(author.toString());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(int id) {
        logger.info("NewsmanagementAuthorsController.delete(" + id + ")");

        if (authorService.delete(id)) {
            return SUCCESS;
        } else {
            throw new CrudDeleteException("Author id : " + id);
        }
    }

}

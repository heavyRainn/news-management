package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.CrudException;
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

    private static final int ITEMS_ON_CRUD_PAGE = 5;

    @Autowired
    private CrudService<Author> authorService;

    @RequestMapping()
    public String addAuthorsPage(Model model) {
        logger.info("NewsmanagementAuthorsController.addAuthorsPage()");

        model.addAttribute("itemsOnPage", ITEMS_ON_CRUD_PAGE);
        model.addAttribute("totalCount", authorService.totalCount());
        model.addAttribute("allAuthors", authorService.read(0, ITEMS_ON_CRUD_PAGE));

        return "addAuthors";
    }

    @RequestMapping("/page-{pageNumber}")
    public String addAuthorsPagination(@PathVariable int pageNumber,
                                       Model model) {
        logger.info("NewsmanagementAuthorsController.addAuthorsPagination(" + pageNumber-- + ")");

        model.addAttribute("itemsOnPage", ITEMS_ON_CRUD_PAGE);
        model.addAttribute("totalCount", authorService.totalCount());
        model.addAttribute("allAuthors", authorService.read(ITEMS_ON_CRUD_PAGE * pageNumber + 1, ITEMS_ON_CRUD_PAGE * pageNumber + ITEMS_ON_CRUD_PAGE));

        return "addAuthors";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addAuthors(@RequestParam("name") String name,
                             @RequestParam("surname") String surname) {
        logger.info("NewsmanagementAuthorsController.addAuthors(" + name + " , " + surname + ")");

        if (authorService.create(new Author(name, surname))) {
            return "redirect:/addAuthors";
        } else {
            throw new CrudException(name + surname);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Author author) {
        logger.info("NewsmanagementAuthorsController.update(" + author.getId() + " , " + author.getName() + " , " + author.getSurname() + ")");

        if (authorService.update(author)) {
            return "success";
        } else {
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(int id) {
        logger.info("NewsmanagementAuthorsController.delete(" + id + ")");

        if (authorService.delete(id)) {
            return "success";
        } else {
            return "error";
        }
    }

}

package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/addTags")
public class NewsmanagementTagsController {

    private static final int ITEMS_ON_CRUD_PAGE = 5;

    @Autowired
    CrudService<Tag> tagService;

    @RequestMapping()
    public String addTagsPage(Model model) {

        model.addAttribute("itemsOnPage", ITEMS_ON_CRUD_PAGE);
        model.addAttribute("totalCount", tagService.totalCount());
        model.addAttribute("allTags", tagService.read(0, ITEMS_ON_CRUD_PAGE));

        return "addTags";
    }

    @RequestMapping("/page-{pageNumber}")
    public String addTagsPagination(@PathVariable int pageNumber,
                                    Model model) {

        pageNumber--;
        model.addAttribute("itemsOnPage", ITEMS_ON_CRUD_PAGE);
        model.addAttribute("totalCount", tagService.totalCount());
        model.addAttribute("allTags", tagService.read(ITEMS_ON_CRUD_PAGE * pageNumber + 1, ITEMS_ON_CRUD_PAGE * pageNumber + ITEMS_ON_CRUD_PAGE));

        return "addTags";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addAuthors(@RequestParam("value") String value) {

        System.out.println("New Tag : " + value);

        return "home";
    }

}

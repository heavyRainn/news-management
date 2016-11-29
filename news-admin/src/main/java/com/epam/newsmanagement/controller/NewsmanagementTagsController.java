package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Tag;
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
@RequestMapping("/addTags")
public class NewsmanagementTagsController {

    private static final Logger logger = Logger.getLogger(NewsmanagementTagsController.class);

    private static final String ITEMS_ON_PAGE = "itemsOnPage";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String ALL_TAGS = "allTags";

    private static final int ITEMS_ON_PAGINATION = 5;

    @Autowired
    private CrudService<Tag> tagService;

    @RequestMapping()
    public String addTagsPage(Model model) {
        logger.info("NewsmanagementTagsController.addAuthorsPage()");

        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(TOTAL_COUNT, tagService.totalCount());
        model.addAttribute(ALL_TAGS, tagService.read(0, ITEMS_ON_PAGINATION));

        return "addTags";
    }

    @RequestMapping("/page-{pageNumber}")
    public String addTagsPagination(@PathVariable int pageNumber,
                                    Model model) {
        logger.info("NewsmanagementTagsController.addTagsPagination(" + pageNumber-- + ")");

        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(TOTAL_COUNT, tagService.totalCount());
        model.addAttribute(ALL_TAGS, tagService.read(ITEMS_ON_PAGINATION * pageNumber + 1, ITEMS_ON_PAGINATION * pageNumber + ITEMS_ON_PAGINATION));

        return "addTags";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String add(@RequestParam("value") String value) {
        logger.info("NewsmanagementTagsController.addAuthors(" + value + ")");

        if (tagService.create(new Tag(value))) {
            return "redirect:/";
        } else {
            throw new CrudCreateException(value);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Tag tag) {
        logger.info("NewsmanagementTagsController.update(" + tag.getId() + " , " + tag.getText() + ")");

        if (tagService.update(tag)) {
            return "success";
        } else {
            throw new CrudUpdateException(tag.toString());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(int id) {
        logger.info("NewsmanagementTagsController.delete(" + id + ")");

        if (tagService.delete(id)) {
            return "success";
        } else {
            throw new CrudDeleteException("Tag id : " + id);
        }
    }

}

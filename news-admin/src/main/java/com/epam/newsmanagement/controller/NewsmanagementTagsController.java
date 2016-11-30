package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tags")
public class NewsmanagementTagsController {

    private static final String ITEMS_ON_PAGE = "itemsOnPage";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String ALL_TAGS = "allTags";

    private static final int ITEMS_ON_PAGINATION = 5;

    @Autowired
    private CrudService<Tag> tagService;

    @GetMapping
    public String addTagsPage(Model model) {
        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(TOTAL_COUNT, tagService.totalCount());
        model.addAttribute(ALL_TAGS, tagService.read(0, ITEMS_ON_PAGINATION));

        return "tags";
    }

    @GetMapping("/page-{pageNumber}")
    public String addTagsPagination(@PathVariable int pageNumber,
                                    Model model) {
        pageNumber--;
        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(TOTAL_COUNT, tagService.totalCount());
        model.addAttribute(ALL_TAGS, tagService.read(ITEMS_ON_PAGINATION * pageNumber + 1, ITEMS_ON_PAGINATION * pageNumber + ITEMS_ON_PAGINATION));

        return "tags";
    }

    @PostMapping
    public String add(@RequestParam("value") String value) {
        tagService.create(new Tag(value));
        return "redirect:/tags";
    }

    @ResponseBody
    @PostMapping("/update")
    public String update(Tag tag) {
        tagService.update(tag);
        return "success";

    }

    @ResponseBody
    @PostMapping("/delete")
    public String delete(int id) {
        tagService.delete(id);
        return "success";
    }

}

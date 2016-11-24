package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.util.search.NewsSearchCriteria;
import com.epam.newsmanagement.util.search.NewsSearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class NewsmanagementNewsController {

    private static final int ITEMS_ON_PAGE = 3;

    @Autowired
    NewsService newsService;

    @RequestMapping()
    public String initHomePage(Model model) {
        List<News> news = newsService.viewAllNews(0, ITEMS_ON_PAGE);

        model.addAttribute("allNews", news);
        model.addAttribute("totalCount", newsService.totalCount());
        model.addAttribute("itemsOnPage", ITEMS_ON_PAGE);

        return "home";
    }

    @RequestMapping("/page-{pageNumber}")
    public String paginationNews(@PathVariable int pageNumber,
                                 Model model) {
        pageNumber--;
        List<News> news = newsService.viewAllNews(ITEMS_ON_PAGE * pageNumber + 1, ITEMS_ON_PAGE * pageNumber + ITEMS_ON_PAGE);

        model.addAttribute("allNews", news);
        model.addAttribute("totalCount", newsService.totalCount());
        model.addAttribute("itemsOnPage", ITEMS_ON_PAGE);

        return "home";
    }

    @RequestMapping("/seeNews/{idNews}")
    public String seeNews(@PathVariable int idNews,
                          Model model) {

        NewsSearchCriteria newsSearchCriteria = new NewsSearchCriteria(NewsSearchType.BY_ID);
        newsSearchCriteria.setId(idNews);

        News news = newsService.viewASingleNews(newsSearchCriteria).get(0);

        model.addAttribute("concreteNews", news);

        return "concreteNews";
    }

    @RequestMapping("/addNews")
    public String addNewsPage(Model model) {
        model.addAttribute("allThemes", newsService.viewAllThemes());
        return "addNews";
    }

    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public String addNews(@RequestParam("title") String title,
                          @RequestParam("date") Date date,
                          @RequestParam("brief") String brief,
                          @RequestParam("content") String content) {

        System.out.println("New news : " + title + " , " + date + " , " + brief + " , " + content);

        return "home";
    }


}

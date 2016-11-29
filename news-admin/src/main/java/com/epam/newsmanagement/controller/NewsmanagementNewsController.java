package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Theme;
import com.epam.newsmanagement.exception.NewsCreateException;
import com.epam.newsmanagement.exception.NewsNotFoundException;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.util.search.NewsSearchCriteria;
import com.epam.newsmanagement.util.search.NewsSearchType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class NewsmanagementNewsController {

    private static final Logger logger = Logger.getLogger(NewsmanagementNewsController.class);


    private static final String ITEMS_ON_PAGE = "itemsOnPage";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String ALL_AUTHORS = "allAuthors";
    private static final String ALL_NEWS = "allNews";
    private static final String ALL_THEMES = "allThemes";
    private static final String CONCRETE_NEWS = "concreteNews";
    private static final String THEME = "theme";
    private static final String AUTHOR = "author";

    private static final int ITEMS_ON_PAGINATION = 3;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CrudService<Author> authorService;

    @RequestMapping
    public String initHomePage(Model model) {
        logger.info("NewsmanagementNewsController.intHomePage()");

        List<News> news = newsService.viewAllNews(0, ITEMS_ON_PAGINATION);

        model.addAttribute(ALL_NEWS, news);
        model.addAttribute(TOTAL_COUNT, newsService.totalCount());
        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(ALL_THEMES, newsService.viewAllThemes());
        model.addAttribute(ALL_AUTHORS, authorService.read());

        return "home";
    }

    @RequestMapping("/page-{pageNumber}")
    public String paginationNews(@PathVariable int pageNumber,
                                 Model model) {
        logger.info("NewsmanagementNewsController.paginationNews(" + pageNumber-- + ")");

        List<News> news = newsService.viewAllNews(ITEMS_ON_PAGINATION * pageNumber + 1, ITEMS_ON_PAGINATION * pageNumber + ITEMS_ON_PAGINATION);

        model.addAttribute(ALL_NEWS, news);
        model.addAttribute(TOTAL_COUNT, newsService.totalCount());
        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(ALL_THEMES, newsService.viewAllThemes());
        model.addAttribute(ALL_AUTHORS, authorService.read());

        return "home";
    }

    @RequestMapping("/filterNews")
    public String filterNews(Model model,
                             HttpSession session,
                             @RequestParam("theme") Theme theme,
                             @RequestParam("author") String author) {
        logger.info("NewsmanagementNewsController.filterNews(" + theme + ")");

        List<News> news = newsService.viewAllNews(theme, 0, ITEMS_ON_PAGINATION);
        model.addAttribute(ALL_NEWS, news);
        model.addAttribute(TOTAL_COUNT, newsService.totalCount(theme));
        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(ALL_THEMES, newsService.viewAllThemes());
        model.addAttribute(ALL_AUTHORS, authorService.read());

        session.setAttribute(THEME, theme);
        session.setAttribute(AUTHOR, author);

        logger.info("AUTHOR : " + author);

        return "filterHome";
    }

    @RequestMapping("/filterNews/page-{pageNumber}")
    public String filterNewsPagination(Model model,
                                       HttpSession session,
                                       @PathVariable int pageNumber) {
        logger.info("NewsmanagementNewsController.filterNewsPagination(" + pageNumber-- + ")");

        pageNumber--;

        Theme theme = (Theme) session.getAttribute(THEME);
        List<News> news = newsService.viewAllNews(theme, ITEMS_ON_PAGINATION * pageNumber + 1, ITEMS_ON_PAGINATION * pageNumber + ITEMS_ON_PAGINATION);

        if (news.isEmpty()) {
            throw new NewsNotFoundException(news.toString());
        }

        model.addAttribute(ALL_NEWS, news);
        model.addAttribute(TOTAL_COUNT, newsService.totalCount(theme));
        model.addAttribute(ITEMS_ON_PAGE, ITEMS_ON_PAGINATION);
        model.addAttribute(ALL_THEMES, newsService.viewAllThemes());
        model.addAttribute(ALL_AUTHORS, authorService.read());

        return "filterHome";
    }

    @RequestMapping("/seeNews/{idNews}")
    public String seeNews(@PathVariable int idNews,
                          Model model) {
        logger.info("NewsmanagementNewsController.seeNews(" + idNews + ")");

        NewsSearchCriteria newsSearchCriteria = new NewsSearchCriteria(NewsSearchType.BY_ID);
        newsSearchCriteria.setId(idNews);

        News news = newsService.viewASingleNews(newsSearchCriteria).get(0);

        if (news.equals(null)) {
            throw new NewsNotFoundException(news.toString());
        }

        model.addAttribute(CONCRETE_NEWS, news);

        return "concreteNews";
    }

    @RequestMapping("/addNews")
    public String addNewsPage(Model model) {
        logger.info("NewsmanagementNewsController.addNewsPage()");

        model.addAttribute(ALL_THEMES, newsService.viewAllThemes());
        model.addAttribute(ALL_AUTHORS, authorService.read());

        return "addNews";
    }

    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public String addNews(@RequestParam("title") String title,
                          @RequestParam("date") Date date,
                          @RequestParam("brief") String brief,
                          @RequestParam("content") String content,
                          @RequestParam("theme") Theme theme,
                          @RequestParam("author") String author) {
        logger.info("NewsmanagementNewsController.addNewsPage() : " + title + " , " + date + " , " + brief + " , " + content + " , " + theme + " , " + author);

        News news = new News();

        news.setMainTitle(title);
        news.setShortTitle(brief);
        news.setDate(date);
        news.setTheme(theme);
        news.setNewsText(content);
        //news.setAuthors();

        if (!newsService.addNews(news)) {
            throw new NewsCreateException(news.toString());
        }

        return "redirect:/";
    }

}

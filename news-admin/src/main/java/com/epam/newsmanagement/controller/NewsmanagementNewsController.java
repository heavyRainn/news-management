package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.News;
import com.epam.newsmanagement.entity.Theme;
import com.epam.newsmanagement.exception.NewsNotFoundException;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.util.search.NewsSearchCriteria;
import com.epam.newsmanagement.util.search.NewsSearchType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class NewsmanagementNewsController {

    private static final Logger logger = Logger.getLogger(NewsmanagementNewsController.class);

    private static final int ITEMS_ON_PAGE = 3;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CrudService<Author> authorService;

    @RequestMapping()
    public String initHomePage(Model model) {
        logger.info("NewsmanagementNewsController.intHomePage()");

        List<News> news = newsService.viewAllNews(0, ITEMS_ON_PAGE);

        model.addAttribute("allNews", news);
        model.addAttribute("totalCount", newsService.totalCount());
        model.addAttribute("itemsOnPage", ITEMS_ON_PAGE);
        model.addAttribute("allThemes", newsService.viewAllThemes());
        model.addAttribute("allAuthors", authorService.read());

        return "home";
    }

    @RequestMapping("/page-{pageNumber}")
    public String paginationNews(@PathVariable int pageNumber,
                                 Model model) {
        logger.info("NewsmanagementNewsController.paginationNews(" + pageNumber-- + ")");

        List<News> news = newsService.viewAllNews(ITEMS_ON_PAGE * pageNumber + 1, ITEMS_ON_PAGE * pageNumber + ITEMS_ON_PAGE);

        model.addAttribute("allNews", news);
        model.addAttribute("totalCount", newsService.totalCount());
        model.addAttribute("itemsOnPage", ITEMS_ON_PAGE);
        model.addAttribute("allThemes", newsService.viewAllThemes());
        model.addAttribute("allAuthors", authorService.read());

        return "home";
    }

    @RequestMapping("/filterNews")
    public String filterNews(Model model,
                             HttpSession session,
                             @RequestParam("theme") Theme theme,
                             @RequestParam("author") String author) {
        logger.info("NewsmanagementNewsController.filterNews(" + theme + ")");

        List<News> news = newsService.viewAllNews(theme, 0, ITEMS_ON_PAGE);
        model.addAttribute("allNews", news);
        model.addAttribute("totalCount", newsService.totalCount(theme));
        model.addAttribute("itemsOnPage", ITEMS_ON_PAGE);
        model.addAttribute("allThemes", newsService.viewAllThemes());
        model.addAttribute("allAuthors", authorService.read());

        session.setAttribute("theme", theme);
        session.setAttribute("author", author);

        logger.info("AUTHOR : " + author);

        return "filterHome";
    }

    @RequestMapping("/filterNews/page-{pageNumber}")
    public String filterNewsPagination(Model model,
                                       HttpSession session,
                                       @PathVariable int pageNumber) {
        logger.info("NewsmanagementNewsController.filterNewsPagination(" + pageNumber-- + ")");

        pageNumber--;

        Theme theme = (Theme) session.getAttribute("theme");
        List<News> news = newsService.viewAllNews(theme, ITEMS_ON_PAGE * pageNumber + 1, ITEMS_ON_PAGE * pageNumber + ITEMS_ON_PAGE);

        if (news.isEmpty()) {
            throw new NewsNotFoundException(news.toString());
        }

        model.addAttribute("allNews", news);
        model.addAttribute("totalCount", newsService.totalCount(theme));
        model.addAttribute("itemsOnPage", ITEMS_ON_PAGE);
        model.addAttribute("allThemes", newsService.viewAllThemes());
        model.addAttribute("allAuthors", authorService.read());

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

        model.addAttribute("concreteNews", news);

        return "concreteNews";
    }

    @RequestMapping("/addNews")
    public String addNewsPage(Model model) {
        logger.info("NewsmanagementNewsController.addNewsPage()");

        model.addAttribute("allThemes", newsService.viewAllThemes());
        model.addAttribute("allAuthors", authorService.read());

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

        return "redirect:/";
    }

}

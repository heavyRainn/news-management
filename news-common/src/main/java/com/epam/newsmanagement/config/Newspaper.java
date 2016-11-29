package com.epam.newsmanagement.config;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.entity.Theme;
import com.epam.newsmanagement.service.CrudService;
import com.epam.newsmanagement.service.NewsService;
import com.epam.newsmanagement.service.UserService;
import com.epam.newsmanagement.service.impl.AuthorServiceImpl;
import com.epam.newsmanagement.service.impl.CommentServiceImpl;
import com.epam.newsmanagement.service.impl.TagServiceImpl;
import com.epam.newsmanagement.util.Coder;
import com.epam.newsmanagement.util.search.NewsSearchCriteria;
import com.epam.newsmanagement.util.search.NewsSearchType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Newspaper {

    private static final Logger logger = Logger.getLogger(Newspaper.class);

    private static final String TAG_MESSAGE1 = "#HOME";
    private static final String TAG_MESSAGE2 = "#SELFIE";
    private static final String AUTHOR_NAME = "Zigmund";
    private static final String AUTHOR_SURNAME = "Freid";
    private static final String LOGIN = "Grek221";
    private static final String PASSWORD = "1222";
    private static final int NEWS_ID = 501;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(NewspaperRootConfig.class);

        CrudService<Author> authorService = ctx.getBean(AuthorServiceImpl.class);
        UserService userService = ctx.getBean(UserService.class);
        CrudService<Tag> tagService = ctx.getBean(TagServiceImpl.class);
        NewsService newsService = ctx.getBean(NewsService.class);
        CrudService<Comment> commentService = ctx.getBean(CommentServiceImpl.class);

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(TAG_MESSAGE1));
        tags.add(new Tag(TAG_MESSAGE2));
        NewsSearchCriteria nsc = new NewsSearchCriteria(NewsSearchType.BY_TAGS);
        nsc.setTags(tags);

        List<Author> authors = new ArrayList<>();
        authors.add(new Author(AUTHOR_NAME, AUTHOR_SURNAME));

        logger.info("UserService : " + userService.findByLogin(LOGIN));
        logger.info("AuthorService : " + authorService.read());
        logger.info("AuthorService : " + authorService.read(501));
        logger.info("TagService : " + tagService.read());
        logger.info("TagService : " + tagService.read(501));
        logger.info("NewsService : " + newsService.viewAllNews());
        logger.info("NewsService : " + newsService.viewAllNews(Theme.FASHION));
        logger.info("NewsService : " + newsService.totalCount());
        logger.info("NewsService : " + newsService.totalCount(Theme.FASHION));
        logger.info("NewsService : " + newsService.viewAllPopularNews());
        logger.info("NewsService BY TAGS: " + newsService.viewASingleNews(nsc));

        NewsSearchCriteria nsc1 = new NewsSearchCriteria(NewsSearchType.BY_AUTHOR);
        nsc1.setAuthors(authors);

        logger.info("NewsService BY AUTHORS : " + newsService.viewASingleNews(nsc1));
        logger.info("CommentService OF ONE NEWS :" + commentService.read(NEWS_ID));

        logger.info("Hashed Password : " + Coder.getHashedPassword(PASSWORD));

        logger.info("News query : " + newsService.viewAllNews(6, 9));
    }
}

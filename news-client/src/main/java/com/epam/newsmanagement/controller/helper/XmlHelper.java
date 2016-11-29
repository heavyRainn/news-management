package com.epam.newsmanagement.controller.helper;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.exception.DomParserException;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Helps to initialize map with commands
 */

public class XmlHelper {

    private static final Logger logger = Logger.getLogger(XmlHelper.class);
    private static final String xmlPath = "C:\\Users\\Andrei_Fiodarau\\IdeaProjects\\news-management\\news-client\\src\\main\\resources\\cmd.xml";

    private volatile static XmlHelper instance = null;

    public static XmlHelper getInstance() {
        if (instance == null) {
            synchronized (XmlHelper.class) {
                if (instance == null) {
                    instance = new XmlHelper();
                }
            }
        }
        return instance;
    }

    /**
     * Invokes method parse form DomParser class
     *
     * @return map of commands
     */

    public Map<String, Command> parse() {
        Map<String, Command> map = null;
        try {
            map = DomParser.getInstance().parse(xmlPath);
        } catch (DomParserException e) {
            logger.error(e.getMessage());
        }
        return map;
    }

}
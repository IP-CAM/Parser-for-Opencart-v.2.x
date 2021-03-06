package com.guhar4k.parser;

import com.guhar4k.product.Category;
import com.guhar4k.product.Product;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParserTest {
    private String SAMPLE_URL = "https://ilfumoshop.ru/";
    private static String CATEGORIES_URL = "https://ilfumoshop.ru/zhidkost-dlya-zapravki-vejporov";
    private int CATEGORIES_COUNT = 9;
    private static Parser parser;
    static List<Category> categoriesList;

    @BeforeAll
    static void initAll() throws IOException {
        IParser listener = mock(IParser.class);
        parser = new Parser(listener);
        Document doc = parser.downloadPage(CATEGORIES_URL);
        categoriesList = parser.getCategories(doc);
    }

    @Test
    void downloadPage() throws IOException {
        Document resultDoc = parser.downloadPage(SAMPLE_URL);
        assertNotNull(resultDoc);
    }

    @Test
    void parseCategoriesFromDocument() {
        assertNotNull(categoriesList);
    }

    @Test
    void checkForValidCategoriesCount() {
        int actualCount = categoriesList.size();
        assertEquals(CATEGORIES_COUNT, actualCount);
    }

    @Test
    void getGroupsFromCategory() {
        Elements groups = parser.getInnerGroups(categoriesList.get(0).getUrl());
        assertEquals(false, groups.isEmpty());
    }

    @Test
    void getInnerLiquids() throws IOException {
        Elements groups = parser.getInnerGroups(categoriesList.get(0).getUrl());
        Element group = groups.get(0).child(1).select("a").get(0);
        Elements products = parser.getInnerLiquids(group.attr("href"));
        assertEquals(false, products.isEmpty());
    }

    @Test
    void parseProduct() throws IOException {
        Elements groups = parser.getInnerGroups(categoriesList.get(0).getUrl());
        Element group = groups.get(0).child(1).select("a").get(0);
        Elements products = parser.getInnerLiquids(group.attr("href"));
        Product product = parser.parseProduct(products.get(0).attr("href"));
        assertNotNull(product.getURL());
        assertNotNull(product.getName());
    }

    @Test
    void getCategoryElements() throws IOException {
        List<Element> categoryElements = parser.getCategoryElements(categoriesList.get(0));
        assertNotNull(categoryElements);
    }
}
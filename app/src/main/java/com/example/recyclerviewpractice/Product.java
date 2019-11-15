package com.example.recyclerviewpractice;

import android.widget.ImageView;

import java.util.Comparator;

public class Product {

    private String title,subtitle,link;
    private int importance;

    public Product(String title, String subtitle, String link, int importance) {
        this.title = title;
        this.subtitle = subtitle;
        this.link = link;
        this.importance = importance;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getLink() {
        return link;
    }

    public int getImportance() {
        return importance;
    }

    public static Comparator<Product> StuRollno = new Comparator<Product>() {

        public int compare(Product s1, Product s2) {

            int rollno1 = s1.getImportance();
            int rollno2 = s2.getImportance();
            return rollno1-rollno2;
        }
    };


}

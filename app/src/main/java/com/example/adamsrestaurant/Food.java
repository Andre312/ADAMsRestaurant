package com.example.adamsrestaurant;

public class Food {
    private int id;
    private String category;
    private String itemName;
    private String ingredients;
    private char spicy;
    private char lactose;
    private char nutty;
    private char gluten;
    private char vegetarian;
    private String price;
    private byte[] link;

    public Food(int id, String category, String itemName, String ingredients, char spicy, char lactose, char nutty, char gluten, char vegetarian, String price, byte[] link) {
        this.id = id;
        this.category = category;
        this.itemName = itemName;
        this.ingredients = ingredients;
        this.spicy = spicy;
        this.lactose = lactose;
        this.nutty = nutty;
        this.gluten = gluten;
        this.vegetarian = vegetarian;
        this.price = price;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public char getSpicy() {
        return spicy;
    }

    public void setSpicy(char spicy) {
        this.spicy = spicy;
    }

    public char getLactose() {
        return lactose;
    }

    public void setLactose(char lactose) {
        this.lactose = lactose;
    }

    public char getNutty() {
        return nutty;
    }

    public void setNutty(char nutty) {
        this.nutty = nutty;
    }

    public char getGluten() {
        return gluten;
    }

    public void setGluten(char gluten) {
        this.gluten = gluten;
    }

    public char getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(char vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getLink() {
        return link;
    }

    public void setLink(byte[] link) {
        this.link = link;
    }
}

package com.mazter707.trends;



public class Album {
    private int product;
    private String money;

    public Album(){
    }

    public Album(int product, String money) {
        this.product = product;
        this.money = money;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}

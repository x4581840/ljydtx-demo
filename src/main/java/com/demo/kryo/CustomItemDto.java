package com.demo.kryo;

import java.io.Serializable;

public class CustomItemDto implements Serializable {

    private Long id;
    private String itemCode;
    private double itemDespositPrice;
    private String itemMemo;
    private String itemName;
    private double itemPrice;
    private int sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getItemDespositPrice() {
        return itemDespositPrice;
    }

    public void setItemDespositPrice(double d) {
        this.itemDespositPrice = d;
    }

    public String getItemMemo() {
        return itemMemo;
    }

    public void setItemMemo(String itemMemo) {
        this.itemMemo = itemMemo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }


}

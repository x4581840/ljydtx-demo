package com.demo.kryo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomCategoryDto implements Serializable {
    private String categoryCode;
    private String categoryName;
    private Long id;
    private String itemCode;
    private double itemDespositPrice;
    private String itemMemo;
    private String itemName;
    private double itemPrice;
    private int sort;
    private List<CustomItemDto> customItemList;
    private Set<CustomItemDto> customItemSet;
    private Map<String, CustomItemDto> customItemMap;


    public Set<CustomItemDto> getCustomItemSet() {
        return customItemSet;
    }

    public void setCustomItemSet(Set<CustomItemDto> customItemSet) {
        this.customItemSet = customItemSet;
    }

    public Map<String, CustomItemDto> getCustomItemMap() {
        return customItemMap;
    }

    public void setCustomItemMap(Map<String, CustomItemDto> customItemMap) {
        this.customItemMap = customItemMap;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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

    public void setItemDespositPrice(double itemDespositPrice) {
        this.itemDespositPrice = itemDespositPrice;
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

    public List<CustomItemDto> getCustomItemList() {
        return customItemList;
    }

    public void setCustomItemList(List<CustomItemDto> customItemList) {
        this.customItemList = customItemList;
    }


}

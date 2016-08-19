package com.ylife.inventory.model;

public class Inventory extends InventoryKey {
    //库存
    private Integer inventory;
    //可用库存
    private Integer available;

    public Inventory() {
    }

    public Inventory(InventoryKey key, Integer inventory, Integer available) {
        super(key.getEnterpriseId(), key.getGoodsId());
        this.inventory = inventory;
        this.available = available;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
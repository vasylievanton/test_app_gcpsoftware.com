
package com.test.test.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuResponse {

    @SerializedName("menu")
    @Expose
    private List<Menu> menu = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MenuResponse() {
    }

    /**
     * 
     * @param menu
     */
    public MenuResponse(List<Menu> menu) {
        super();
        this.menu = menu;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

}

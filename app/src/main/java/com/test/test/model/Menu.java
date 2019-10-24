
package com.test.test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("function")
    @Expose
    private String function;
    @SerializedName("param")
    @Expose
    private String param;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Menu() {
    }

    /**
     * 
     * @param param
     * @param function
     * @param name
     */
    public Menu(String name, String function, String param) {
        super();
        this.name = name;
        this.function = function;
        this.param = param;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

}

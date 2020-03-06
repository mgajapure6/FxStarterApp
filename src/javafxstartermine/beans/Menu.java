/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxstartermine.beans;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author EPS01
 */
public class Menu {

    String menu_name;
    Boolean isLink;
    String url;

    public Menu(String menu_name, Boolean isLink, String url) {
        this.menu_name = menu_name;
        this.isLink = isLink;
        this.url = url;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public Boolean getIsLink() {
        return isLink;
    }

    public void setIsLink(Boolean isLink) {
        this.isLink = isLink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Menu{" + "menu_name=" + menu_name + ", isLink=" + isLink + ", url=" + url + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.menu_name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        if (!Objects.equals(this.menu_name, other.menu_name)) {
            return false;
        }
        return true;
    }

}

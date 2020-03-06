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
public class Module {

    String module_name;
    Boolean isLink;
    String url;
    List<Menu> menu;

    public Module(String module_name, Boolean isLink, String url, List<Menu> menu) {
        this.module_name = module_name;
        this.isLink = isLink;
        this.url = url;
        this.menu = menu;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
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

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }
    
    

    @Override
    public String toString() {
        return "Module{" + "module_name=" + module_name + ", isLink=" + isLink + ", url=" + url + ", menu=" + menu + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.module_name);
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
        final Module other = (Module) obj;
        if (!Objects.equals(this.module_name, other.module_name)) {
            return false;
        }
        return true;
    }

}

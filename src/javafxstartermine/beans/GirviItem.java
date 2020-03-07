/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxstartermine.beans;

import java.util.Objects;

/**
 *
 * @author EPS01
 */
public class GirviItem {
    Integer id;
    String metal;
    String itemName;
    String qty;
    String gWeight;
    String gWeightUoM;
    String nWeight;
    String nWeightUoM;
    String valuation;

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getgWeight() {
        return gWeight;
    }

    public void setgWeight(String gWeight) {
        this.gWeight = gWeight;
    }

    public String getgWeightUoM() {
        return gWeightUoM;
    }

    public void setgWeightUoM(String gWeightUoM) {
        this.gWeightUoM = gWeightUoM;
    }

    public String getnWeight() {
        return nWeight;
    }

    public void setnWeight(String nWeight) {
        this.nWeight = nWeight;
    }

    public String getnWeightUoM() {
        return nWeightUoM;
    }

    public void setnWeightUoM(String nWeightUoM) {
        this.nWeightUoM = nWeightUoM;
    }

    public String getValuation() {
        return valuation;
    }

    public void setValuation(String valuation) {
        this.valuation = valuation;
    }

    @Override
    public String toString() {
        return "GirviItem{" + "metal=" + metal + ", itemName=" + itemName + ", qty=" + qty + ", gWeight=" + gWeight + ", gWeightUoM=" + gWeightUoM + ", nWeight=" + nWeight + ", nWeightUoM=" + nWeightUoM + ", valuation=" + valuation + '}';
    }

    public GirviItem() {
    }

    public GirviItem(Integer id, String metal, String itemName, String qty, String gWeight, String gWeightUoM, String nWeight, String nWeightUoM, String valuation) {
        this.id = id;
        this.metal = metal;
        this.itemName = itemName;
        this.qty = qty;
        this.gWeight = gWeight;
        this.gWeightUoM = gWeightUoM;
        this.nWeight = nWeight;
        this.nWeightUoM = nWeightUoM;
        this.valuation = valuation;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final GirviItem other = (GirviItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}

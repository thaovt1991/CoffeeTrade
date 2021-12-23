//package com.codegym.model.dto;
//
//import com.codegym.model.Drinks;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class CartDrinks {
//
//    private Map<Drinks, Integer> listDrinks = new HashMap<>() ;
//
//    public CartDrinks(){
//    }
//
//    public CartDrinks(Map<Drinks, Integer> listDrinks) {
//        this.listDrinks = listDrinks;
//    }
//
//    public Map<Drinks, Integer> getListDrinks() {
//        return listDrinks;
//    }
//
//    private boolean checkItemInCart(Drinks drinks) {
//        for (Map.Entry<Drinks, Integer> entry : listDrinks.entrySet()) {
//            if (entry.getKey().getId().equals(drinks.getId())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private Map.Entry<Drinks, Integer> selectItemInCart(Drinks drinks) {
//        for (Map.Entry<Drinks, Integer> entry : listDrinks.entrySet()) {
//            if (entry.getKey().getId().equals(drinks.getId())) {
//                return entry;
//            }
//        }
//        return null;
//    }
//
//    public void addDrinks(Drinks drinks) {
//        if (!checkItemInCart(drinks)) {
//            listDrinks.put(drinks, 1);
//        } else {
//            Map.Entry<Drinks, Integer> itemEntry = selectItemInCart(drinks);
//            Integer newQuantity = itemEntry.getValue() + 1;
//            listDrinks.replace(itemEntry.getKey(), newQuantity);
//        }
//    }
//
//    public void subDrinks(Drinks drinks) {
//        for (Map.Entry<Drinks, Integer> entry : listDrinks.entrySet()) {
//            if (entry.getKey().getId().equals(drinks.getId())) {
//                if( entry.getValue() > 1){
//                    Map.Entry<Drinks, Integer> itemEntry = selectItemInCart(drinks);
//                    Integer newQuantity = itemEntry.getValue() - 1;
//                    listDrinks.replace(itemEntry.getKey(), newQuantity);break;
//                }else if(entry.getValue()==1) {
//                    listDrinks.remove(entry.getKey()) ;
//                    break;
//                }
//            }
//        }
//    }
//
//    public Integer countDrinksQuantity() {
//        Integer drinksQuantity = 0;
//        return drinksQuantity;
//    }
//
//    public Integer countItemQuantity() {
//        return listDrinks.size();
//    }
//
//    public Long countTotalPayment() {
//        long payment = 0;
//        for (Map.Entry<Drinks, Integer> entry : listDrinks.entrySet()) {
//            payment += Long.valueOf(entry.getKey().getPrice().toString())* (long) entry.getValue();
//        }
//        return payment;
//    }
//}

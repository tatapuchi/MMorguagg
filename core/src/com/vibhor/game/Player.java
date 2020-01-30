package com.vibhor.game;

public class Player {

    //Adds the item to your inventory.

    public void addItemToInv(String name){

        for(int i = 0; i < 5000; i++){
            if(inv[i] == ""){
                inv[i] = name;

                System.out.println("Player has Obtained " + name + ".");
                return;
            }
        }
    }

    //Prints the inventory.

    public void printInv(){
        for(String x: inv){
            System.out.println(x);
        }
    }

    //Removes a item from your inventory.

    public void removeItemFromInv(String name){
        for(int i = 0; i < 5000; i++){
            if(inv[i].equals(name)){
                inv[i] = "";
            }
        }
    }

    private static String inv[] = {"","","","",""};

}

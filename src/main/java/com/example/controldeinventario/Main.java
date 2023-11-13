package com.example.controldeinventario;

public class Main {
    public static void main(String[] args) {
        try {
            HelloApplication.main(args);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }

    }
}

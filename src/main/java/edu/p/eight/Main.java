package edu.p.eight;

class Main {
    public static void main(String[]args){
        try {
            System.out.println(StringUtils.add("Hello, ", null));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
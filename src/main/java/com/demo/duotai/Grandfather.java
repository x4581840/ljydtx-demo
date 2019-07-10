package com.demo.duotai;

//多态
public class Grandfather {

    public String show(Grandfather obj) {
        return ("Grandfather and Grandfather");
    }

    public String show(Son2 obj) {
        return ("Grandfather and Son2");
    }

    public static void main(String[] args) {
        Grandfather gf = new Grandfather();
        Grandfather gf2 = new Father();
        Father f = new Father();
        Son1 son1 = new Son1();
        Son2 son2 = new Son2();

        System.out.println(gf.show(f));     //Grandfather and Grandfather
        System.out.println(gf.show(son1));  //Grandfather and Grandfather
        System.out.println(gf.show(son2));  //Grandfather and Son2

        System.out.println(gf2.show(f));    //Father and Grandfather
        System.out.println(gf2.show(son1)); //Father and Grandfather
        System.out.println(gf2.show(son2)); //Grandfather and Son2

        System.out.println(f.show(f));      //Father and Father
        System.out.println(f.show(son1));   //Father and Father
        System.out.println(f.show(son2));   //Grandfather and Son2
    }
}

class Father extends Grandfather {
    public String show(Father obj) {
        return ("Father and Father");
    }

    public String show(Grandfather obj) {
        return ("Father and Grandfather");
    }
}

class Son1 extends Father {

}

class Son2 extends Father {

}

import java.util.*;
public class Item {
    private String name;  //물품 이름
    private double price;  //물품 가격
    private int num;  //재고 확인
    private String site;  //물품 사이트
    private String company;  //물품 제작회사

    public Item(){}  //기본 생성자

    //사용자가 제품 이름이랑 가격까지는 입력하게 방지!
    public Item(String company, String name, int num, double price, String site) {
        this.company = company;
        this.name = name;
        this.num = num;
        this.price = price;
        this.site = site;
    }

    public Item(String name, int num, double price, String site) {
        this.company = "정보 없음";
        this.name = name;
        this.num = num;
        this.price = price;
        this.site = site;
    }

    public Item(String name, int num, double price) {
        this.company = "정보 없음";
        this.name = name;
        this.num = num;
        this.price = price;
        this.site = "정보 없음";
    }

    public Item(String company, String name, int num, double price){
        this.company = company;
        this.name = name;
        this.num = num;
        this.price = price;
        this.site = "정보 없음";
    }

    public Item(String company, String name, int num){
        this.company = company;
        this.name = name;
        this.num = num;
        this.price = 0.0;
        this.site = "정보 없음";
    }

    public Item(String name, int num){
        this.company = "정보 없음";
        this.name = name;
        this.num = num;
        this.price = 0.0;
        this.site = "정보 없음";
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}

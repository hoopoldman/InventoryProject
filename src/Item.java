import java.util.*;
public class Item {
    private String name;  //물품 이름
    private double price;  //물품 가격
    private int num;  //재고 확인
    private String site;  //물품 사이트
    private String company;  //물품 제작회사
    private static int itemCount = 0;       // 아이템 객체 전체에서 공유되는 변수
    private String itemCode;                // 개별 아이템 코드
    private String store;
    public Item(){}  //기본 생성자

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Item item = (Item) object;
        return Objects.equals(itemCode, item.itemCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(itemCode);
    }

    //사용자가 제품 이름이랑 가격까지는 입력하게 방지!
    public Item(String company, String name, int num, double price, String site, String store) {
        this.company = company;
        this.name = name;
        this.num = num;
        this.price = price;
        this.site = site;
        this.store = store;
        itemCount++;
        this.itemCode = Integer.toString(itemCount);  //랩퍼 클래스로 변환뒤 string으로 전환
    }

    public Item(String name, int num, double price, String site, String store) {
        this.company = "정보 없음";
        this.name = name;
        this.num = num;
        this.price = price;
        this.site = site;
        this.store = store;
        itemCount++;
        this.itemCode = Integer.toString(itemCount);  //랩퍼 클래스로 변환뒤 string으로 전환
    }

    public Item(String name, int num, double price, String store) {
        this.company = "정보 없음";
        this.name = name;
        this.num = num;
        this.price = price;
        this.site = "정보 없음";
        this.store = store;
        itemCount++;
        this.itemCode = Integer.toString(itemCount);  //랩퍼 클래스로 변환뒤 string으로 전환
    }

    public Item(String company, String name, int num, double price, String store){
        this.company = company;
        this.name = name;
        this.num = num;
        this.price = price;
        this.site = "정보 없음";
        this.store = store;
        itemCount++;
        this.itemCode = Integer.toString(itemCount);  //랩퍼 클래스로 변환뒤 string으로 전환
    }

    public Item(String company, String name, int num, String store){
        this.company = company;
        this.name = name;
        this.num = num;
        this.price = 0.0;
        this.site = "정보 없음";
        this.store = store;
        itemCount++;
        this.itemCode = Integer.toString(itemCount);  //랩퍼 클래스로 변환뒤 string으로 전환
    }

    public Item(String name, int num, String store){
        this.company = "정보 없음";
        this.name = name;
        this.num = num;
        this.price = 0.0;
        this.site = "정보 없음";
        this.store = store;
        itemCount++;
        this.itemCode = Integer.toString(itemCount);  //랩퍼 클래스로 변환뒤 string으로 전환
    }

    @Override
    public String toString() {
        return "Item{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", site='" + site + '\'' +
                ", itemCode='" + itemCode + '\'' +
                '}';
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public static int getItemCount() {
        return itemCount;
    }

    public static void setItemCount(int itemCount) {
        Item.itemCount = itemCount;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
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

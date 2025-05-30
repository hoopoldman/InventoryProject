import java.util.*;

public interface Store {
    public void store_item();   //재고를 저장해주는 함수
    public Item delete_Item();   //재고를 빼주는 함수
    public void show_Store_Item();   //가게 안의 재고를 보여주는 함수
    public void show_Item(String name);  //해당 아이템의 재고를 보여주는 함수(모든 가게 포함)
    public int filter_Item(Item temp);  // 사용자가 검색한 조건에 따라 상품 필터링
    public void saveInventoryToCSV();  //전체 재고를 csv 형식 파일에 저장해주는 함수
    public void printInventoryCsv();  //csv 파일에 저장된 전체 재고를 출력해주는 함수
    public void saveStoreToCSV(StoreInfo registeredStore);  //전체 재고를 csv 형식 파일에 저장해주는 함수
    public void printStoreCSV(String store);  //가게당 재고를 출력해주는 함수
    public void overwriteInventoryCSV(); //삭제하면 다시 재고저장
    public void overwriteStoreCSV(StoreInfo temp);
}

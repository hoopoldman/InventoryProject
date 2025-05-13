import java.util.*;

public interface Store {
    public void store_item();   //재고를 저장해주는 함수
    //public Item delete_Item();   //재고를 빼주는 함수
    //public void show_Store_Item();   //가게 안의 재고를 보여주는 함수
    //public void show_Item();  //해당 아이템의 재고를 보여주는 함수(모든 가게 포함)
    public int filter_Item(Item temp);  // 사용자가 검색한 조건에 따라 상품 필터링
}

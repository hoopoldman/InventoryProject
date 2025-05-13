import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mystore store = new Mystore();

        while(true){
            int choice;
            System.out.print("메뉴를 선택해주세요: ");
            System.out.println("1) 재고 저장 2) 재고 삭제 3) 재고 검색 ver 1 4) 재고 검색 ver 2");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    store.store_item();
                    break;
            }
        }
    }
}

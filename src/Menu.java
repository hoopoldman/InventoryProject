import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public Menu() {
        Scanner scanner = new Scanner(System.in);
        Mystore store = new Mystore();

        System.out.println("😁 재고 확인 프로그램 실행!");
        System.out.println();  //단순하게 이뻐보이게 하려고..

        while (true) {
            int choice;
            try{
                System.out.println("메뉴 선택창으로 이동합니다...");
                System.out.println("========================================================");
                System.out.println("1) 재고 저장 2) 재고 삭제 3) 검색 ver1 4) 검색 ver2 0) 종료");
                System.out.println("3번은 가게 별 재고 확인입니다.");
                System.out.println("4번은 모든 재고 확인입니다.");
                System.out.println("========================================================");
                System.out.println();
                System.out.print("메뉴를 선택해주세요 : ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        store.store_item();
                        break;
                    case 2:
                        store.delete_Item();
                        break;
                    case 3:
                        store.show_Store_Item();
                        break;
                    case 4:
                        store.printInventoryCsv();
                        break;
                    case 0:
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    default:
                        System.out.println("올바른 번호를 입력해주세요.");
                }
            }catch (InputMismatchException e){
                System.out.println("예외 처리: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }
}

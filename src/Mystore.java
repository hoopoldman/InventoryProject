import java.util.ArrayList;
import java.util.Scanner;

public class Mystore implements Store{
    public Scanner scanner;
    private ArrayList<Item> inventory = new ArrayList<>();  //재고 배열

    public void store_item(Item item){
        String name;
        String company;
        int num;
        int price;
        String site;

        System.out.println("등록할 아이템을 입력해주세요 : ");
        name = scanner.next();  //이름 입력받기
        System.out.println("자세히 모르시다면 0으로 입력해주세요! ");
        System.out.println("등록할 아이템의 가격 및 재고를 입력해주세요 : ");
        price = scanner.nextInt();  //가격 입력받기
        num = scanner.nextInt();  //재고 입력받기
        scanner.nextLine(); // 개행 제거!! .. nextInt는 숫자만 읽고 개행문자는 남겨둠
        System.out.println("자세히 모르시다면 빈칸으로 입력해주세요!");
        System.out.println("등록할 아이템의 제조회사 및 사이트(예: 쿠팡)를 입력해주세요 : ");
        company = scanner.next();
        site = scanner.next();
        Item temp = new Item(company, name, num, price, site);  //아이템 임시객체 만들기
        int condition = filter_Item(temp);  //필터 함수 만들기
        switch(condition){
            case 0:
                inventory.add(temp); //temp 를 리스트에 넣어준다.
                break;
                case 1:

        }
    }   //재고를 저장해주는 함수
    public Item delete_Item(){

    }   //재고를 빼주는 함수
    public void show_Store_Item(){

    }   //가게 안의 재고를 보여주는 함수
    public void show_Item(){

    } //해당 아이템의 재고를 보여주는 함수(모든 가게 포함)
    public int filter_Item(Item temp){
        int count = 0;  //count 변수가 ++할때마다 누락되어있는 정보수 증가!
        if(temp.getCompany().equals("")){
            count++;
        }
        if(temp.getSite().equals("")){
            count++;
        }
        if(temp.getPrice() == 0.0){
            count++;
        }
        return count;
    }  // 사용자가 검색한 조건에 따라 상품 필터링


}

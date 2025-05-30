import java.io.*;
import java.util.*;

public class Mystore implements Store{
    public Scanner scanner = new Scanner(System.in);  //Scanner 객체
    private ArrayList<Item> inventory = new ArrayList<>();  //각 가게의 모든 재고 배열
    private Map<String, ArrayList<Item>> sameItemList = new HashMap<>();  //각 가게의 아이템의 이름이 동일한경우
    StoreManager storeManager = new StoreManager();
    
    Mystore(){}

    //재고를 저장해주는 함수
    public void store_item(){
        try{
            ArrayList<Item> store_inventory;  //가게용 재고 배열 -> 만약에 멤버 변수마냥 선언되어있으면 모든 가게가 같은 배열을 공유하게 됨
            String name;
            String company;
            int num;
            int price;
            String site;
            String store;

            //가게 등록
            System.out.print("가게이름을 입력해주세요 : ");
            String storeName = scanner.nextLine().toLowerCase();  // 이름만 소문자로
            StoreInfo storeInfoTemp = new StoreInfo(storeName);
            StoreInfo registeredStore = storeManager.registerStore(storeInfoTemp);

            System.out.print("등록할 아이템을 입력해주세요 : ");
            name = scanner.next();  //이름 입력받기

            System.out.println("등록할 아이템의 가격을 입력해주세요");
            System.out.print("자세히 모르시다면 0으로 입력해주세요! :  ");
            price = scanner.nextInt();  //가격 입력받기

            Item temp = new Item(name, price, storeName);
            checkSameItem(temp);  //동일한 아이템 체크(가격, 이름)

            System.out.println("등록할 아이템의 재고를 입력해주세요");
            System.out.print("자세히 모르시다면 0으로 입력해주세요! :  ");
            num = scanner.nextInt();  //재고 입력받기

            System.out.println("등록할 아이템의 제조회사 및 사이트(예: 쿠팡)를 입력해주세요");
            System.out.print("자세히 모르시다면 빈칸으로 입력해주세요! : ");
            company = scanner.next();
            site = scanner.next();

            Item check_temp = new Item(company, name, num, price, site, storeName);  //아이템 임시객체 만들기
            checkCondition(check_temp, registeredStore);  //아이템 임시객체와 storeInfoTemp를 받음

        }catch (InputMismatchException e){
            System.out.println("올바른 입력을 해주세요");
            scanner.nextLine();
        }catch(InvalidValue e) {
            System.out.println("예외 처리 : " + e.getMessage());
            scanner.nextLine();
        } finally {
            System.out.println("재고 저장을 종료하겠습니다.");
            scanner.nextLine();
        }
    }   //재고를 저장해주는 함수

    public void checkSameItem(Item temp){
        for(Item item: inventory){
            if(item.getName().equals(temp.getName())){
                System.out.println("동일한 이름과 가격이 등록된 제품이 존재합니다.");
                System.out.print("이름과 가격이 동일한 재고를 저장하시겠습니까? Y를 누르면 수량이 증가함 (Y/N) : ");
                String ans = scanner.next();
                switch (ans.toUpperCase()){
                    case "Y":
                        System.out.println("다시 재고를 저장하겠습니다...");
                        store_item();
                        break;
                    case "N":
                        System.out.println("재고 저장을 종료하겠습니다...");
                        break;
                    default:
                        System.out.println("올바르게 입력해주세요!");
                        break;
                }
                return;  //무한 루프 문제 해결
            }
        }  //이름이 동일한 아이템
    }

    public void show_Item(String name){
        for(Item item:inventory){
            if(name.equals(item.getName())){
                System.out.println(item);
            }
        }
    } //해당 아이템의 재고를 보여주는 함수(모든 가게 포함)

   public Item delete_Item(){
        try{
            boolean found = false; //찾았는지 확인하는 변수
            String name, choice;
            int index = 0;
            System.out.println("삭제할 아이템의 이름을 입력해주세요!");
            name =scanner.nextLine();
            for(Item item : inventory){
                if(name.equals(item.getName())){
                    System.out.println("삭제하려는 아이템 " + name +"을 찾았습니다!");
                    found  = true;
                    break;
                }
                index++;
            }
            if(!found){
                System.out.println("삭제하려는 아이템 "+ name+"을 찾지 못했습니다!");
                return null;
            }

            String code;
            System.out.println("삭제할 아이템의 제품 코드를 입력해주세요 : ");
            code = scanner.next();
            System.out.print("진짜 삭제하시겠습니까?(Y/N) : ");
            choice = scanner.next();
            choice = choice.toUpperCase();

            switch (choice){
                case "Y":
                    System.out.println("삭제하겠습니다.");
                    Iterator<Item> it = inventory.iterator();
                    while (it.hasNext()) {
                        Item item = it.next();
                        if (code.equals(item.getItemCode())) {
                            it.remove();  // 안전한 삭제
                            System.out.println("전체 재고 리스트에서 삭제하겠습니다!");
                            String storeName = item.getStore();
                            StoreInfo storeRemove = new StoreInfo(storeName);
                            if (storeManager.getStoreList().containsKey(storeRemove)) {
                                storeManager.getStoreList().get(storeRemove).remove(item);
                                System.out.println(storeRemove.getName() + "의 아이템을 삭제하겠습니다.");
                                overwriteStoreCSV(storeRemove);
                            }
                            break;
                        }
                    }
                    overwriteInventoryCSV();
                    break;
                case "N":
                    System.out.println("삭제하지 않겠습니다.");
                    return null;
                default:
                    System.out.println("올바르게 입력하세요");
                    break;
            }
        } catch (InputMismatchException e){
            System.out.println("올바른 형식으로 입력해주세요!");
        }
        return null;
    }

    //재고에 저장된 정보에 따라 저장해주는 함수 (파일과 배열 둘다) , 그리고 가게도
    public void checkCondition(Item check_temp, StoreInfo registeredStore){
        int condition = filter_Item(check_temp);  //아이템별로 필터링하여 객체 생성

        // 가게별 재고 배열 꺼내기 또는 새로 생성
        ArrayList<Item> store_inventory;
        if (storeManager.getStoreList().containsKey(registeredStore)) {
            store_inventory = storeManager.getStoreList().get(registeredStore);   //가게별 아이템 재고 가져오기
        } else {
            store_inventory = new ArrayList<>();
        }

        switch(condition){
            case 0:
                inventory.add(check_temp); //check_temp 를 리스트에 넣어준다.-
                storeManager.getStoreList().get(registeredStore).add(check_temp);
                break;
            case 1:
                Item item1 = new Item(check_temp.getName(), check_temp.getNum(), check_temp.getPrice(), check_temp.getSite(), check_temp.getStore());
                inventory.add(item1); // 필터링한 item1 객체를 넣어준다.
                storeManager.getStoreList().get(registeredStore).add(item1);
                break;
            case 2:
                Item item2 = new Item(check_temp.getName(), check_temp.getNum(), check_temp.getPrice(), check_temp.getStore());
                inventory.add(item2); // 필터링한 item2 객체를 넣어준다.
                storeManager.getStoreList().get(registeredStore).add(item2);
                break;
            case 3:
                Item item3 = new Item(check_temp.getName(), check_temp.getNum(), check_temp.getStore());
                inventory.add(item3); // 필터링한 item3 객체를 넣어준다.
                storeManager.getStoreList().get(registeredStore).add(item3);
                break;
            default:
                String ch;  //y,n 입력용 키
                System.out.println("제품 이름과 재고는 저장해주세요!");
                System.out.print("다시 재고를 저장하시겠습니까? (y/n): ");
                ch = scanner.next();
                if(ch.equals("y") || ch.equals("Y")){
                    store_item();
                }else if(ch.equals("n") || ch.equals("N")) {
                    System.out.println("재고를 저장하지 않겠습니다!");
                    break;
                }
                break;
        }
        saveInventoryToCSV();
        saveStoreToCSV(registeredStore);
    }

    public void overwriteStoreCSV(StoreInfo temp) {
        String fileName = temp.getName() + ".csv";

        if (!storeManager.getStoreList().containsKey(temp)) return;  //가게가 없는경우

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, false))) {
            pw.println("이름 , 수량 , 가격 , 제조 회사 , 사이트 , 가게 , 제품코드");  // 헤더 (필드에 맞게 조정)
            for (Item item : storeManager.getStoreList().get(temp)) {
                pw.printf("%s , %d , %.2f , %s , %s , %s , %s%n",
                        item.getName(),
                        item.getNum(),
                        item.getPrice(),
                        item.getCompany(),
                        item.getSite(),
                        item.getStore(),
                        item.getItemCode()
                );
            }
        } catch (IOException e) {
            System.out.println(temp.getName() + ".csv 파일 저장 중 오류: " + e.getMessage());
        }
    }

    public void overwriteInventoryCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("inventory.csv", false))) {
            pw.println("이름 , 수량 , 가격 , 제조 회사 , 사이트 , 가게 , 제품코드");  // 헤더 (필드에 맞게 조정)
            for (Item item : inventory) {
                pw.printf("%s , %d , %.2f , %s , %s , %s , %s%n",
                        item.getName(),
                        item.getNum(),
                        item.getPrice(),
                        item.getCompany(),
                        item.getSite(),
                        item.getStore(),
                        item.getItemCode()
                );
            }
        } catch (IOException e) {
            System.out.println("inventory.csv 파일 저장 중 오류: " + e.getMessage());
        }
    }

    public void printInventoryCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))) {
            while(true){
                String line = br.readLine();
                if(line == null) break; //더이상 읽을 내용이 없을때
                System.out.println(line);
            }
        } catch(FileNotFoundException e){
            System.out.println("가게 재고 파일을 찾는데 실패하였습니다.");
        } catch (IOException e) {
            System.out.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 전체 재고를 파일로 저장해줌 (CSV 파일로)
    public void saveInventoryToCSV() {
        File file = new File("inventory.csv");
        boolean fileExists = file.exists(); // 파일 존재 여부

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            if (!fileExists) {
                pw.println("이름 , 수량 , 가격 , 제조 회사 , 사이트 , 가게 , 제품코드");  // 파일이 없었으면 헤더 작성
            }

            for (Item item : inventory) {
                pw.printf("%s , %d , %.2f , %s , %s , %s , %s %n",
                        item.getName(),
                        item.getNum(),
                        item.getPrice(),
                        item.getCompany(),
                        item.getSite(),
                        item.getStore(),
                        item.getItemCode()
                );  //csv 파일: , 형식으로 데이터를 구분한다.
            }

            System.out.println("전체 재고(inventory)를 CSV 파일에 저장했습니다.");

        } catch (FileNotFoundException e){
            System.out.println("전체 재고 파일을 찾는 데 실패하였습니다.");
        } catch (IOException e) {
            System.out.println("파일 오류!");
        }
    }

    // 전체 재고를 파일로 저장한 것을 출력해줌
    public void printStoreCSV(String store){
        String fileName = store + ".csv";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while(true){
                String line = br.readLine();
                if(line == null) break; //더이상 읽을 내용이 없을때
                System.out.println(line);
            }
        } catch(FileNotFoundException e){
            System.out.println("가게 재고 파일을 찾는데 실패하였습니다.");
        } catch (IOException e) {
            System.out.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
        }
    }


    public void saveStoreToCSV(StoreInfo registeredStore){

        if(!storeManager.getStoreList().containsKey(registeredStore)){
            System.out.println("해당 가게 "+ registeredStore.getName() + "의 재고가 없습니다.");
            System.out.println("등록하겠습니다! ");
            return;
        } //가게 이름이 없는 경우!

        String fileName = registeredStore.getName() + ".csv"; //가게이름으로 파일이름 설정
        File file = new File(fileName);
        boolean fileExists = file.exists();  // 파일이 이미 존재하는지 확인

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            if (!fileExists) {
                pw.println("이름 , 수량 , 가격 , 제조 회사 , 사이트 , 가게 , 제품코드");  // 새 파일일 때만 헤더 작성
            }

            for (Item item : storeManager.getStoreList().get(registeredStore)) {
                pw.printf(" %s , %d , %.2f , %s , %s , %s , %s %n",
                        item.getName(),
                        item.getNum(),
                        item.getPrice(),
                        item.getCompany(),
                        item.getSite(),
                        item.getStore(),
                        item.getItemCode()
                );
            }

            System.out.println("가게 '" + registeredStore.getName() + "'의 재고를 " + fileName + "에 저장했습니다.");
        } catch (IOException e) {
            System.out.println("CSV 저장 중 오류: " + e.getMessage());
        }
    }

    public void show_Store_Item(){
        try{
            String search_store;  //재고를 찾으려는 가게의 이름
            System.out.print("가게 이름을 입력해주세요 : ");
            search_store = scanner.nextLine();
            StoreInfo temp= new StoreInfo(search_store);
            ArrayList<Item> search_item = storeManager.getStoreList().get(temp);

            if(search_item==null||search_item.isEmpty()){
                System.out.println("가게에 재고가 없습니다.");
                return;
            }//가게에 재고가 없는 경우

            System.out.println("===========================");
            System.out.println(search_store+"의 재고 배열");
            printStoreCSV(search_store);
            System.out.println("===========================");
        }catch (InputMismatchException e){
            System.out.println("올바르게 입력해주세요");
        }catch (InvalidValue e){
            System.out.println("예외처리 : "+e.getMessage());
        }finally{
            System.out.println("종료하겠습니다.");
        }
    }   //가게 안의 재고를 보여주는 함수

    public int filter_Item(Item check_temp){
        int count = 0;  //count 변수가 ++할때마다 누락되어있는 정보수 증가!
        if(check_temp.getCompany().equals("")){
            count++;
        }  //제조 회사 정보 누락
        if(check_temp.getSite().equals("")){
            count++;
        }  //사이트 정보 누락
        if(check_temp.getPrice() == 0.0){
            count++;
        }  //가격 정보 누락
        return count;
    }  // 사용자가 검색한 조건에 따라 상품 필터링

}


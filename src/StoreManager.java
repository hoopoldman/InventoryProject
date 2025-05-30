import java.util.*;

//모든 가게 관리 클래스
public class StoreManager {
    public Scanner scanner = new Scanner(System.in);  //Scanner 객체
    private Map<StoreInfo, ArrayList<Item>> storeList = new HashMap<>();

    public StoreManager() {}

    public StoreInfo findStore(String storeName){
        Iterator<StoreInfo> iterator = storeList.keySet().iterator();
        while (iterator.hasNext()) {
            StoreInfo info = iterator.next();
            if (info.getName().equalsIgnoreCase(storeName)) {
                return info;
            }
        }
        return null;
    }

    public StoreInfo registerStore(StoreInfo store) {
        StoreInfo exist = findStore(store.getName()); //등록된 가게인지 검사해주는 함수 호출
        if (exist != null) {
            System.out.println("이미 등록된 가게입니다.");
            return store;
        } //기존 가게인경우

        //등록되지 않은 가게인경우
        System.out.print("가게 주소를 입력해주세요 : ");
        String address = scanner.nextLine();
        store.setAddress(address);

        System.out.print("사장님 이름을 입력해주세요 : ");
        String owner = scanner.nextLine();
        store.setOwner(owner);

        System.out.print("연락처를 입력해주세요 : ");
        String contact = scanner.nextLine();
        store.setContact(contact);

        ArrayList<Item> list = new ArrayList<>();
        storeList.put(store, list);  //가게의 재고에는 아무것도 없는 상태
        return store;
    }

    public Map<StoreInfo, ArrayList<Item>> getStoreList() {
        return storeList;
    }

    public void setStoreList(Map<StoreInfo, ArrayList<Item>> storeInventoryMap) {
        this.storeList = storeInventoryMap;
    }

    //갖고있는 가게 리스트가 같으면 같은 객체
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StoreManager that = (StoreManager) object;
        return Objects.equals(storeList, that.storeList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(storeList);
    }
}

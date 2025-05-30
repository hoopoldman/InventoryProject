public class StoreInfo {
    private String name;   //가게 이름
    private String address;   //가게 주소
    private String owner;   //가게 사장님
    private String contact;  //연락처

    public StoreInfo(String name){
        this.name = name;
    }

    public StoreInfo(String name, String address, String owner, String contact) {
        this.name = name;
        this.address = address;
        this.owner = owner;
        this.contact = contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getOwner() { return owner; }
    public String getContact() { return contact; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoreInfo)) return false;
        StoreInfo info = (StoreInfo) o;
        return name.equals(info.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "StoreInfo{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}

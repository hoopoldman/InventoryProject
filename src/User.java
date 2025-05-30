import java.io.*;
import java.util.*;

//아이디와 비번이 동일하지 않으면 로그인 불가
//user 객체에 5개의 정보저장
public class User {
    private String name; //사용자 이름
    private String gender;  //사용자 성별
    private String phoneNumber;  //사용자 전번
    private String id;  //아이디
    private String passWord; //비번
    public Scanner scanner = new Scanner(System.in);

    User(){
        System.out.println("아이디를 입력해주세요! ");
        String id = scanner.nextLine();
        System.out.println("비번을 입력해주세요! ");
        String passWord = scanner.nextLine();
    }

    User(String id, String passWord){
        this.id = id;
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(id, user.id) && Objects.equals(passWord, user.passWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passWord);
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }  //나중에 사용자가 이름을 바꿀수도 있으므로

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

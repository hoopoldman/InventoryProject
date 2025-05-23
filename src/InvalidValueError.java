public class InvalidValueError  {
    public void checkNum(int num) throws InvalidValue{
        if(num<0){
            throw new InvalidValue("재고가 0 이하가 될 수 없습니다!\n");
        }
        System.out.println("제대로 입력하셨어요!");
    }

    public void checkPrice(double price) throws InvalidValue{
        if(price<=0.0){
            throw new InvalidValue("가격이 0 미만이 될 수 없습니다.\n");
        }
        System.out.println("제대로 입력하셨어요!");
    }

    /*public void enterCondition(String enter) throws InvalidValue{
        if(enter)
    }*/
}

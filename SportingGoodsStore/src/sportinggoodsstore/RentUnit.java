package sportinggoodsstore;

public class RentUnit {
    private int numberOfBaskets = 3;
    private SportEquipment[] units = new SportEquipment[numberOfBaskets];
    
    public void addUnit(Shop shop, SportEquipment requiredEquipment){
        
        Integer num = shop.searchEquipment(requiredEquipment);
        
        if (num != null){
            int basketNumber = canAddUnit();
            
            if (basketNumber < numberOfBaskets){
                units[basketNumber] = requiredEquipment;
                num -= 1;
                shop.updateAvailableGoods(requiredEquipment, num);
                shop.addUnAvailableGood(requiredEquipment);
            } else {
                System.out.println("Лимит выдачи превышен. Вы можете взять только " + numberOfBaskets + " товаров.");
            }  
        } 
    }
    
    public int canAddUnit(){
        int basketNumber = 0;
        for (Object o : units){
            if (o == null)
                return basketNumber;
            basketNumber++;
        }
        return basketNumber;
    }
}

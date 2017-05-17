package sportinggoodsstore;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Map;


public class Shop {
    private HashMap<SportEquipment, Integer> availableGoods = new HashMap<>();
    private HashMap<SportEquipment, Integer> unAvailableGoods = new HashMap<>();
    
    public void showAvailableGoods(){
        System.out.println("Снаряжение в наличии");
        
        for (Map.Entry<SportEquipment, Integer> entry : availableGoods.entrySet()){
            System.out.println(entry.getKey().toString() + " : available - " + entry.getValue().toString() + " units");
        }
    }
    
    public void showUnAvailableGoods(){
        System.out.println("Снаряжение отданное в прокат");
        
        for (Map.Entry<SportEquipment, Integer> entry : unAvailableGoods.entrySet()){
            System.out.println(entry.getKey().toString() + " : отдано в прокат - " + entry.getValue().toString() + " units");
        }
    }
    
    public Integer searchEquipment(SportEquipment equipment){
        Integer num = availableGoods.get(equipment);
        
        if (num != null && num > 0)
            System.out.println(equipment.toString() + " в наличии - " + num + " едениц");
        else{
            System.out.println("Нет в наличии такого сняржения  " + equipment);
            return null;
        }
        return num;
    }
    
    public void addUnAvailableGood(SportEquipment equipment){
        Integer num = unAvailableGoods.get(equipment);
        
        if (num == null){
            this.unAvailableGoods.put(equipment, 1);
        } else {
            num = new Integer(num.intValue() + 1);
            this.unAvailableGoods.put(equipment, num);
        }
    }
    
    public void updateAvailableGoods(SportEquipment equipment, Integer num){
        this.availableGoods.put(equipment, num);
    }
    
    public void shopInit(String file) throws IOException{
        BufferedReader reader = null;
        String s;
        
        try {
            reader = new BufferedReader(new FileReader(file));

            while((s = reader.readLine()) != null){
                
                String[] spl = s.split(":");
                Category category = Category.valueOf(spl[0]);
                String title = spl[1];
                int price = Integer.parseInt(spl[2]);
                Integer num = Integer.valueOf(spl[3]);
                
                SportEquipment equip = new SportEquipment(category, title, price);    
                availableGoods.put(equip, num);
            }
        } catch(FileNotFoundException e){
            System.out.println(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
	}
    }
    
    public static void main(String[] args) {
        Shop shop = new Shop();
        try{
            shop.shopInit("file.txt");
        } catch(IOException e){
            System.out.println(e);
        }
        SportEquipment requiredEquipment1 = new SportEquipment(Category.SUMMER, "Basket Ball", 10);
        shop.showAvailableGoods();
        System.out.println("");
        RentUnit rU = new RentUnit();
        rU.addUnit(shop, null);
        rU.addUnit(shop, requiredEquipment1);
        rU.addUnit(shop, requiredEquipment1);
        rU.addUnit(shop, requiredEquipment1);
        System.out.println("");
        shop.showAvailableGoods();
        System.out.println("");
        shop.showUnAvailableGoods();
    }
}

package sportinggoodsstore;

public class SportEquipment {
    private Category category;
    private String title;
    private int price;
    
    public SportEquipment(Category category, String title, int price){
        this.category = category;
        this.title = title;
        this.price = price;
    }
    
    public Category getCategory(){
        return this.category;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public int getPrice(){
        return this.price;
    }
    
    @Override
    public String toString(){
        return category + " : " + title + " : price - " + price;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if (o == this)
           return true;
        if (getClass() != o.getClass())
            return false;
        
        SportEquipment e = (SportEquipment) o;
        return (this.price == e.getPrice() && this.category.equals(e.getCategory()) && this.title.equals(e.getTitle()));
    }
    
    @Override
    public int hashCode(){
        int result = category != null ? category.hashCode() : 0;
        result = 43 * result + (title != null ? title.hashCode() : 0);
        result = 43 * result + price;
        return result;
    }
}

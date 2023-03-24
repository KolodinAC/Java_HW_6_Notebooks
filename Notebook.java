import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Notebook {
    static class Stat {
        public static int counter = 0;
        public static int id = 0;
    }

    {
        Stat.counter++;
        id = ++Stat.id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void showId(){
        System.out.println("ID: " + this.id);
    }

    private static final String[] oSArr = new String[]{"Windows11", "Windows10", "MacOS", "DOS"};
    private int ram;
    private int storageCapacity;
    private String oS;
    private String color;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public void setoS(String oS) {
        this.oS = oS;
    }

    public void setColor(String color) {
        this.color = color;
    }



    public int getRam() {
        return ram;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public String getoS() {
        return oS;
    }

    public String getColor() {
        return color;
    }


    Notebook(int ram, int storageCapacity, String oS, String color, double price) {
        this.ram = ram;
        if(price>0){
            this.price = price;
        }
        this.storageCapacity = storageCapacity;
        if (isCorrectOs(oS)) {
            this.oS = oS;
        } else {
            this.oS = "incorrect info";
        }
        this.color = color;
    }

    private boolean isCorrectOs(String oSname) {
        for (String name :
                oSArr) {
            if (name.equals(oSname)) {
                return true;
            }
        }
        return false;
    }

    public void showNote(){
        System.out.printf("Ноутбук №: %d, Цена со скидкой: %.2f руб., ОЗУ: %d Гб, Объем HDD/SSD: %d Гб, ОС: %s, цвет: %s\n", id, price, ram, storageCapacity, oS, color);
    }

}
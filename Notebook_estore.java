// Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы.
// Реализовать в java.

import java.util.*;

public class Notebook_estore {
    public static void main(String[] args) {
        Notebook nb1 = new Notebook(16, 1024, "Windows10", "черный", 56500.0);
        Notebook nb2 = new Notebook(32, 1024, "Windows10", "белый", 65000.0);
        Notebook nb3 = new Notebook(8, 512, "DOS", "металлик", 26000.0);
        Notebook nb4 = new Notebook(12, 1024, "MacOS", "металлик", 133000.0);
        Notebook nb5 = new Notebook(32, 1024, "Windows10", "белый", 82000.0);
        Notebook nb6 = new Notebook(16, 500, "Windows11", "серый", 64000.0);
        Notebook nb7 = new Notebook(64, 1024, "Windows11", "синий", 167000.0);
        Notebook nb8 = new Notebook(32, 256, "DOS", "черный", 29000.0);
        Notebook nb9 = new Notebook(16, 2048, "Windows10", "белый", 59000.0);
        Notebook nb10 = new Notebook(32, 2048, "Windows10", "белый", 67000.0);
        Notebook nb11 = new Notebook(64, 2048, "Windows11", "черный", 85000.0);
        Map<Integer, Notebook> db = new LinkedHashMap<>();
        db.put(nb1.getId(), nb1);
        db.put(nb2.getId(), nb2);
        db.put(nb3.getId(), nb3);
        db.put(nb4.getId(), nb4);
        db.put(nb5.getId(), nb5);
        db.put(nb6.getId(), nb6);
        db.put(nb7.getId(), nb7);
        db.put(nb8.getId(), nb8);
        db.put(nb9.getId(), nb9);
        db.put(nb10.getId(), nb10);
        db.put(nb11.getId(), nb11);
        System.out.println("\nДобро пожаловать в наш интернет-магазин ноутбуков!");
        System.out.printf("На данный момент в наличии %d ноутбуков\n", Notebook.Stat.counter);
        switcher(db);
    }

    public static Object getData(Map<Integer, Notebook> db, String param) {
        Map<Integer, Integer> ram = new LinkedHashMap<>();
        Map<Integer, Integer> hdd = new LinkedHashMap<>();
        Map<Integer, String> os = new LinkedHashMap<>();
        Map<Integer, String> color = new LinkedHashMap<>();
        for (Notebook n : db.values()) {
            ram.put(n.getId(), n.getRam());
            hdd.put(n.getId(), n.getStorageCapacity());
            os.put(n.getId(), n.getoS());
            color.put(n.getId(), n.getColor());
        }

        Set<String> colorU = new LinkedHashSet<>(color.values());
        Set<String> osU = new LinkedHashSet<>(os.values());

        if (Objects.equals(param, "ram")) return ram;
        if (Objects.equals(param, "hdd")) return hdd;
        if (Objects.equals(param, "color")) return color;
        if (Objects.equals(param, "os")) return os;
        if (Objects.equals(param, "colorUnique")) return colorU;
        if (Objects.equals(param, "osUnique")) return osU;
        else {
            return "";
        }
    }
    public static List<Integer> checkID(Map<Integer, Notebook> db, String hddMin, String ramMin, String setColor, String setOs){
        List<Integer> result = new ArrayList<>();
        LinkedHashMap<Integer, Integer> ram = (LinkedHashMap<Integer, Integer>) getData(db, "ram");
        LinkedHashMap<Integer, Integer> hdd = (LinkedHashMap<Integer, Integer>) getData(db, "hdd");
        LinkedHashMap<Integer, String> color = (LinkedHashMap<Integer, String>) getData(db, "color");
        LinkedHashMap<Integer, String> os = (LinkedHashMap<Integer, String>) getData(db, "os");
        int minHdd = Integer.parseInt(hddMin);
        int minRam = Integer.parseInt(ramMin);
        List<Integer> colorId = new ArrayList<>();
        List<Integer> osId = new ArrayList<>();
        List<Integer> ramId = new ArrayList<>();
        List<Integer> hddId = new ArrayList<>();

        //color
        for (Map.Entry<Integer, String> entry : color.entrySet()){
            if(setColor==null) {
                colorId.add(entry.getKey());
            } else {
                if(Objects.equals(entry.getValue(), setColor)){
                    colorId.add(entry.getKey());
                }
            }
        }
        //oS
        for (Map.Entry<Integer, String> entry : os.entrySet()){
            if(setOs==null) {
                osId.add(entry.getKey());
            } else {
                if(Objects.equals(entry.getValue(), setOs)){
                    osId.add(entry.getKey());
                }
            }
        }
        //RAM
        for (Map.Entry<Integer, Integer> entry : ram.entrySet()){
            if(entry.getValue() >= minRam){
                ramId.add(entry.getKey());
            }
        }
        //HDD
        for (Map.Entry<Integer, Integer> entry : hdd.entrySet()){
            if(entry.getValue() >= minHdd){
                hddId.add(entry.getKey());
            }
        }

        if (colorId.isEmpty() || osId.isEmpty() || hddId.isEmpty() || ramId.isEmpty()){
            return result;
        } else {

            for (int i : colorId) {
                for (int j : osId) {
                    for (int k : ramId) {
                        for (int m : hddId) {
                            if (i == j && j == k && k == m) {
                                result.add(i);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void switcher(Map<Integer, Notebook> db) {
        HashMap<String, String> fil = new HashMap<>();
        fil.put("RAM", "0");
        fil.put("HDD", "0");
        fil.put("COLOR", null);
        fil.put("OS", null);
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            boolean f1;
            System.out.println("\nВыберите интересующий Вас параметр ноутбука: \n");
            String[] menu = new String[]{"Оперативная память (ОЗУ)", "Объем накопителей (SSD/HDD)", "Операционная система", "Цвет корпуса", "Проверить выбранные фильтры", "Отобразить результат поиска по заданным параметрам"};
            for (int i = 1; i <= menu.length; i++) {
                System.out.printf("%d - %s\n", i, menu[i - 1]);
            }
            System.out.print("\nВведите номер интересующего параметра из списка выше: ");
            int sw = sc.nextInt();
            switch (sw) {
                case (1) -> {
                    System.out.print("Укажите минимальное значени оперативной памяти в Гб: ");
                    if (sc.hasNextInt()) {
                        String swRam = sc.next();
                        fil.put("RAM", swRam);
                    } else {
                        String temp = sc.next();
                        System.out.printf("\nЗначение %s указано некорректно!\n", temp);
                    }
                }
                case (2) -> {
                    System.out.print("Укажите минимально необходимое значени объема накопителей HDD/SSD в Гб: ");
                    if (sc.hasNextInt()) {
                        String swHdd = sc.next();
                        fil.put("HDD", swHdd);
                    } else {
                        String temp = sc.next();
                        System.out.printf("\nЗначение %s указано некорректно!\n", temp);
                    }
                }
                case (3) -> {
                    LinkedHashSet<String> os = (LinkedHashSet<String>) getData(db, "osUnique");
                    System.out.println("Выберите операционную систему из доступных в наличии вариантов: ");
                    System.out.println(os);
                    System.out.print("Введите наименование операционной системы: ");
                    String ossw = sc.next();
                    f1 = false;
                    for (String n : os) {
                        if (Objects.equals(ossw, n)) {
                            f1 = true;
                            fil.replace("OS", n);
                            break;
                        }
                    }
                    if (!f1) {
                        System.out.println("\nЗначение параметра указано некорректно!");
                    }
                }
                case (4) -> {
                    System.out.print("Выберите цвет корпуса: ");
                    LinkedHashSet<String> cl = (LinkedHashSet<String>) getData(db, "colorUnique");
                    System.out.println("Выберите операционную систему из доступных в наличии вариантов: ");
                    System.out.println(cl);
//                    System.out.println(os.getClass().getName());
                    System.out.print("Введите наименование цвета корпуса: ");
                    String clsw = sc.next();
                    f1 = false;
                    for (String n : cl) {
                        if (Objects.equals(clsw, n)) {
                            f1 = true;
                            fil.replace("COLOR", n);
                            break;
                        }
                    }
                    if (!f1) {
                        System.out.println("\nЗначение фильтра указано некорректно!");
                    }
                }
                case (5) -> {
                    for (Map.Entry<String, String> entry : fil.entrySet()) {
                        System.out.print("\n" + entry.getKey() + " - " + entry.getValue());
                        if (Objects.equals(entry.getKey(), "RAM") || Objects.equals(entry.getKey(), "HDD")) {
                            System.out.print(" гб. минимум");
                        }
                    }
                    System.out.println("\n");
                }
                case (6) -> flag = false;
            }
        }

        List<Integer> intersection = checkID(db, fil.get("HDD"), fil.get("RAM"), fil.get("COLOR"), fil.get("OS"));

        if(intersection.isEmpty()) {
            System.out.println("К сожалению, ноутбуков с интересующими Вас параметрами нет в наличии! :( ");
        } else {
            System.out.println("\nНайдены следующие ноутбуки в наличии: \n");
            for (Integer nId : intersection) {
                db.get(nId).showNote();
            }
            System.out.println("\nСпасибо, что воспользовались услугами нашего интернет магазина! :) \n");
        }
    }

}

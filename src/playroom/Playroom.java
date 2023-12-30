package playroom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Playroom {
    public List<Toy> toyList;
    private final int toysCount;
    private final double totalAmount;
    private double currentAmount;
    private final List<String> categories = new ArrayList<>(Arrays.asList("dolls","balls","cars"));
    Scanner scanner=new Scanner(System.in);

    /**
     * Конструктор
     * @param toyList
     * @param toysCount
     * @param totalAmount
     */
    public Playroom(List<Toy> toyList, int toysCount,double totalAmount) {
        this.toyList = toyList;
        this.toysCount = toysCount;
        this.totalAmount=totalAmount;
        this.currentAmount=0;
    }

    /**
     * Метод вибору параметру сортування
     */
    public int chooseSortParameter(){
        System.out.println("Виберіть параметр: ");
        System.out.println("1 - За іменем");
        System.out.println("2 - За розміром");
        System.out.println("3 - За вартістю");
        System.out.println("4 - За категорією");
        System.out.println("Виберіть опцію: ");
        return scanner.nextInt();
    }

    /**
     * Метод завантаження іграшок з файлу
     */
    public void loadToysFromFile(List<Toy> toys) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву файлу(шлях): ");
        String filename= scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Розділити рядок на компоненти (наприклад, за допомогою методу split)
                String[] parts = line.split(","); // Припустимо, що дані розділені комами

                // Отримати значення для створення нового об'єкта Toy
                String name = parts[0];
                int size = Integer.parseInt(parts[1]);
                double price = Double.parseDouble(parts[2]);
                if(currentAmount+price > totalAmount) {
                    System.out.println("Перевищено ліміт виділеної суми грошей!");
                    return;
                }
                currentAmount += price;

                String category = parts[3];
                if(!categories.contains(category))
                {
                    categories.add(category.toLowerCase());
                }

                // Створити новий об'єкт Toy і додати його до списку
                Toy toy = new Toy(name, size, price, category);
                toys.add(toy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод сортування іграшок у кімнаті по одному з
     * параметрів
     */
    public void sortToysBy() {
        System.out.println("Сортування іграшок");
        int choice = chooseSortParameter();
        String param = null;

        switch (choice) {
            case 1 -> {
                toyList.sort(Comparator.comparing(Toy::getName));
                param = "ім'я";
            }
            case 2 -> {
                toyList.sort(Comparator.comparingInt(Toy::getSize));
                param = "розмір";
            }
            case 3 -> {
                toyList.sort(Comparator.comparingDouble(Toy::getPrice));
                param = "ціна";
            }
            case 4 -> {
                //Collections.sort(toyList, (t1, t2) -> t1.getCategory().compareTo(t2.getCategory()));
                toyList.sort(Comparator.comparing(Toy::getCategory));
                param = "категорія";
            }
            default -> System.out.println("Некоректний вибір параметра");
        }
        System.out.println("Відсортовані іграшки за таким параметром - "+param+":");
        displayToys(toyList);
    }

    /**
     * Метод задання параметрів пошуку іграшки
     */
    public SearchParameters setParametersRange() {
        System.out.println("Задайте діапазон параметрів:");

        System.out.print("Введіть мінімальний розмір іграшок: ");
        int minSize = scanner.nextInt();

        System.out.print("Введіть максимальний розмір іграшок: ");
        int maxSize = scanner.nextInt();

        System.out.print("Введіть мінімальну ціну іграшок: ");
        double minPrice = scanner.nextDouble();

        System.out.print("Введіть максимальну ціну іграшок: ");
        double maxPrice = scanner.nextDouble();

        System.out.print("Введіть категорію іграшок: ");
        String category = getCategoryFromUser();
        return new SearchParameters(minSize,maxSize,minPrice,maxPrice,category);
    }

    /**
     * Метод знаходження іграшок у кімнаті, що відповідають заданому діапазону
     * параметрів
     */
    public void searchToys() {
        // Отримуємо параметри від користувача
        SearchParameters parameters = setParametersRange();

        // Викликаємо метод пошуку іграшок, передаючи параметри
        List<Toy> matchingToys = searchToysByParameters(parameters);

        if (matchingToys.isEmpty())
        {
            System.out.println("В заданому діапазоні не було знайдено жодної іграшки");
        }
        displayToys(matchingToys);
    }

    /**
     * Метод пошуку іграшок за параметрами
     */
    public List<Toy> searchToysByParameters(SearchParameters parameters) {
        List<Toy> matchingToys = new ArrayList<>();

        for (Toy toy : toyList) {
            if (toy.getSize() >= parameters.minSize() && toy.getSize() <= parameters.maxSize() &&
                    toy.getPrice() >= parameters.minPrice() && toy.getPrice() <= parameters.maxPrice() &&
                    toy.getCategory().equals(parameters.category())) {
                matchingToys.add(toy);
            }
        }

        return matchingToys;
    }

    /**
     * Метод додати іграшку
     */
    public void addToy(){
        if(toyList.size()==toysCount) {
            System.out.println("Неможливо додати іграшку, оскільки їхня кількість досягла максимально дозволеної!");
            return;
        }
        // Логіка додавання іграшки до кімнати
        System.out.println("Введіть назву іграшки:");
        String name=scanner.next();

        System.out.println("Введіть розмір іграшки:");
        int size=scanner.nextInt();

        System.out.println("Введіть ціну іграшки:");
        double price=scanner.nextDouble();
        if(currentAmount+price > totalAmount)
        {
            System.out.println("Перевищено ліміт виділеної суми грошей!");
            return;
        }
        currentAmount += price;

        System.out.println("Введіть категорію,до якої належить іграшка:");
        String category=getCategoryFromUser();

        Toy toyToAdd=new Toy(name,size,price,category);
        toyList.add(toyToAdd);
        System.out.println("Іграшка " + toyToAdd.getName() + " успішно додана до кімнати.");
    }

    /**
     * Метод вибору категорії користувачем
     */
    private String getCategoryFromUser() {
        System.out.println("Доступні категорії: ");
        getCategoryList();
        System.out.println("1 - Вибрати з доступних категорій");
        System.out.println("2 - Створити свою категорію");
        System.out.println("Оберіть опцію: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            getCategoryList();
            System.out.println("Виберіть існуючу категорію:");
            int i = scanner.nextInt();
            return categories.get(i-1);

        } else if (choice == 2) {
            System.out.println("Введіть нову категорію:");
            return scanner.next().toLowerCase();
        } else {
            System.out.println("Ви обрали хибну опцію.Спробуйте ще раз");
            return getCategoryFromUser();
        }
    }

    /**
     * Метод відображення списку категорій
     */
    private void getCategoryList() {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
    }
    /**
     * Метод видалити іграшку
     */
    public void removeToy(){
        // Логіка видалення іграшки з кімнати

        System.out.println("Оберіть зі списку іграшку ,яку ви хочете забрати");
        displayToys(toyList);
        System.out.println("Введіть номер іграшки");

        int toyIndex = scanner.nextInt() - 1;
        if (toyIndex >= 0 && toyIndex < toyList.size()) {
            Toy toyToRemove = toyList.get(toyIndex);
            currentAmount-=toyToRemove.getPrice();
            toyList.remove(toyToRemove);
            System.out.println("Іграшка " + toyToRemove.getName() + " успішно забрана з кімнати.");
        } else {
            System.out.println("Невірний номер іграшки.");
        }
    }

    /**
     * Метод для виведення списку іграшок у кімнаті
     */
    public void displayToys(List<Toy> toyList){
        System.out.println("Список іграшок: ");
        for (int i=0;i<toyList.size();i++) {
            System.out.println((i+1)+" "+toyList.get(i).toString());
        }
    }
}
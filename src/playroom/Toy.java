package playroom;

/**
 * Клас Іграшка
 */
public class Toy {
    private final String name;
    private final int size;
    private final double price;
    private final String category;

    /**
     * Конструктор
     * @param name
     * @param size
     * @param price
     * @param category
     */
    public Toy(String name,int size, double price, String category) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.category = category;
    }

    /**
     * Геттери класу Toy
     */
    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}

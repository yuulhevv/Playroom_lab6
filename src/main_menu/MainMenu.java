package main_menu;

import commands.*;
import playroom.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        int totalCount=getTotalCount();
        double totalAmount=getTotalAmount();

        List <Toy> toys=new ArrayList<>(totalCount); //оголошення масиву іграшок
        Playroom room = new Playroom(toys,totalCount,totalAmount); //створення ігрової кімнати
        Executor executor = new Executor(); //створення виконавця команд

        menu(executor,room); //виклик меню
    }

    /**
     * Метод встановлення ліміту іграшок у кімнаті
     */
    public static int getTotalCount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Задати фіксовану кількість іграшок:");
        return scanner.nextInt();
    }
    /**
     * Метод виділення фіксованої суми
     */
    public static double getTotalAmount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Виділена сума грошей:");
        return scanner.nextDouble();
    }

    /**
     * Метод відображення меню
     */
    private static void displayMenu() {
        System.out.println("Меню:");
        System.out.println("1. Додати іграшку");
        System.out.println("2. Видалити іграшку");
        System.out.println("3. Завантажити з файлу");
        System.out.println("4. Список іграшок");
        System.out.println("5. Пошук іграшок за діапазоном параметрів");
        System.out.println("6. Сортувати іграшки");
        System.out.println("7. Вийти");
    }

    /**
     * Меню
     */
    private static void menu(Executor executor,Playroom room){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            System.out.println("\nОберіть опцію: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера введення

            switch (choice) {
                case 1 -> {
                    Command addToyCommand = new AddToyCommand(room);
                    executor.executeCommand(addToyCommand);
                }
                case 2 -> {
                    Command removeToyCommand = new RemoveToyCommand(room);
                    executor.executeCommand(removeToyCommand);
                }
                case 3 -> {
                    Command loadToysFromFileCommand = new LoadToysFromFileCommand(room);
                    executor.executeCommand(loadToysFromFileCommand);
                }
                case 4 -> {
                    Command displayToyCommand = new DisplayToyCommand(room);
                    executor.executeCommand(displayToyCommand);
                }
                case 5 -> {
                    Command searchToysCommand = new SearchToysCommand(room);
                    executor.executeCommand(searchToysCommand);
                }
                case 6 -> {
                    Command sortToysCommand = new SortToysCommand(room);
                    executor.executeCommand(sortToysCommand);
                }
                case 7 -> {
                    System.out.println("Завершення роботи програми.");
                    System.exit(0);
                }
                default -> System.out.println("Невідома команда. Спробуйте ще раз.");
            }
        }
    }
}

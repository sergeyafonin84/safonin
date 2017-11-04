package ru.job4j.start;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Sergey Afonin (mailto: afonin1c@mail.ru)
 * @since 19.10.2017
 */
public class Tracker {

    /**
     * items is an array for items.
     */
//    private Item[] items = new Item[100];
    private ArrayList<Item> items = new ArrayList<Item>();


    /**
     * position is the counter that counts number of elements that had been added.
     */
    private int position = 0;

    /**
     * RN is a constant which contains current random value.
     */
    private static final Random RN = new Random();

    /**
     * void add(Item item) {}; добавление заявок - public Item add(Item); 1. Метод public Item add(Item) добавляет заявку, переданную в аргументах в массив заявок this.items.
     * @param item is item for add to array items.
     * @return item.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
//        this.items[position++] = item;
        this.items.add(position++, item);
        return item;
    }

    /**
     * method which generates id for item.
     * @return generated id for item.
     */
    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }

    /**
     * редактирование заявок - public void update(Item); 2. Метод public void update(Item) должен заменить ячейку в массиве this.items.
     * Для этого необходимо найти ячейку в массиве по id (id у Item можно получить с помощью метода getId). Обязательно принимать один параметр Item, а не список полей.
     * @param item is item for update.
     */
    public void update(Item item) {
        String id = item.getId();
        for (Item itemForUpdate : items) {
            if (item != null && item.getId().equals(id)) {
                itemForUpdate = item;
                break;
            }
        }
    }

    /**
     * удаление заявок - public void delete(Item); Метод public void delete(Item) должен удалить ячейку в массиве this.items. Для этого необходимо найти ячейку в массиве по id.
     * После этого присвоить ей null, либо сместить все значения справа от удаляемого элемента - на одну ячейку влево с помощью System.arrayCopy().
     * @param item is item for delete.
     */
    public void delete(Item item) {
        String id = item.getId();
//        for (int index = 0; index < items.length; index++) {
//            if (items[index] != null && items[index].getId().equals(id)) {
//                items[index] = null;
//                break;
//            }
//        }
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index) != null && items.get(index).getId().equals(id)) {
                items.set(index, null);
                break;
            }
        }
    }

    /**
     * получение списка всех заявок - public Item[] findAll(); 4. Метод public Item[] findAll() возвращает копию массива this.items без null элементов;
     * @return all items with not null value
     */
    public Item[] findAll() {
        int quontityOfElements = 0;
//        for (int index = 0; index < items.length; index++) {
//            if (items[index] != null) {
//                quontityOfElements = quontityOfElements + 1;
//            }
//        }
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index) != null) {
                quontityOfElements = quontityOfElements + 1;
            }
        }

        Item[] returnItems = new Item[quontityOfElements];
        int indexReturnItems = 0;

//        for (int index = 0; index < items.length; index++) {
//            if (items[index] != null) {
//                returnItems[indexReturnItems] = items[index];
//                indexReturnItems = indexReturnItems + 1;
//            }
//        }
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index) != null) {
                returnItems[indexReturnItems] = items.get(index);
                indexReturnItems = indexReturnItems + 1;
            }
        }

        return returnItems;
    }

    /**
     * получение списка по имени - public Item[] findByName(String key); 5. Метод public Item[] findByName(String key) проверяет в цикле все элементы массива this.items,.
     * сравнивая name (используя метод getName класса Item) с аргументом метода String key. Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его;.
     * @param key is a name by which function finds elements from array.
     * @return all items with the same name
     */
//    public Item[] findByName(String key) {
    public ArrayList<Item> findByName(String key) {
        int quontityOfElements = 0;
//        for (int index = 0; index < items.length; index++) {
//            if (items[index] != null && items[index].getName().equals(key)) {
//                quontityOfElements = quontityOfElements + 1;
//            }
//        }
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index) != null && items.get(index).getName().equals(key)) {
                quontityOfElements = quontityOfElements + 1;
            }
        }

//        Item[] returnItems = new Item[quontityOfElements];
        ArrayList<Item> returnItems = new ArrayList<Item>();
        int indexReturnItems = 0;

//        for (int index = 0; index < items.length; index++) {
//            if (items[index] != null && items[index].getName().equals(key)) {
//                returnItems[indexReturnItems] = items[index];
//                indexReturnItems = indexReturnItems + 1;
//            }
//        }

        for (int index = 0; index < items.size(); index++) {
            if (items.get(index) != null && items.get(index).getName().equals(key)) {
//                returnItems[indexReturnItems] = items.get(index);
                returnItems.add(indexReturnItems, items.get(index));
                indexReturnItems = indexReturnItems + 1;
            }
        }
        return returnItems;
    }

    /**
     * получение заявки по id - public Item findById(String id); Метод public Item findById(String id) проверяет в цикле все элементы массива this.items, сравнивая id с
     * аргументом String id и возвращает найденный Item. Если Item не найден - возвращает null.
     * @param id is id by which function finds elements from array.
     * @return item with the same id.
     */
    protected Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * edit item.
     * @param item item for edit.
     */
    void edit(Item item) {

        Item itemForEdit = findById(item.getId());
        itemForEdit.setName(item.getName());
        itemForEdit.setDescription(item.getDescription());

    }

    /**
     * get all items.
     * @return all items
     */
    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
//            result[index] = this.items[index];
            result[index] = this.items.get(index);
        }
        return result;
    }

    /**
     *
     * @param item item.
     * @return item.
     */
    public String showItem(Item item) {
        return "Item id: " + item.getId() + " name: " + item.getName() + " descreption: " + item.getDescription() + " create: " + item.getCreate() + "\n";
    }

    /**
     *
     * @return resultstring.
     */
    public String showAll() {
        String resultString = "";
        //Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
//            if (this.items[index] != null) {
//                resultString =  resultString + showItem(this.items[index]);
//            }
            if (this.items.get(index) != null) {
                resultString =  resultString + showItem(this.items.get(index));
            }

        }
        return resultString;
    }
}
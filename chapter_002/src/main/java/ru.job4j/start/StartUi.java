package ru.job4j.start;

/**
 * 5. Переписать в классе StartUI метод void init() с учетом доработки меню.
 * Пример:

 При запуске класса StartUI пользователю отображается следующее меню:

 Add new Item
 Show all items
 Edit item
 Delete item
 Find item by Id
 Find items by name
 Exit Program
 Select:

 При выборе пункта меню 0, программа должна запросить: имя пользователя и описание заявки, после этого добавить эту заявку в трекер.

 При выборе пункта меню 4, программа должна запросить у пользователя id заявки, после этого отобразить найденную заявку на экране.

 */
public class StartUi {
    /**
     *
     */
    private Input input;

    /**
     *
     */
    private Tracker tracker;
    /**
     *
     */
    private static final String ADDNEWITEM = "0";
    /**
     *
     */
    private static final String SHOWALLITEMS = "1";
    /**
     *
     */
    private static final String EDITITEM = "2";
    /**
     *
     */
    private static final String DELETEITEM = "3";
    /**
     *
     */
    private static final String FINDITEMBYID = "4";
    /**
     *
     */
    private static final String FINDITEMSBYNAME = "5";


    /**
     *
     */
    private static final String EXIT = "6";

    /**
     *
     * @param input input.
     */
    public StartUi(Input input) {
        this.input = input;
    }

    /**
     * @param tracker  tracker.
     * @param input input.
     */
    public StartUi(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }


    /**
     * init.
     */

    public void init(String newInit) {
        // tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            int key = Integer.valueOf(input.ask("Select: "));
            menu.select(key);
        } while (!"y".equals(this.input.ask("Exit?(y):")));
    }
    /**
     * init.
     */
    public void init() {

        String theMainQuestion = "0. Add new Item\n"
                +
                "1. Show all items\n"
                +
                "2. Edit item\n"
                +
                "3. Delete item\n"
                +
                "4. Find item by Id\n"
                +
                "5. Find items by name\n"
                +
                "6. Exit Program\n"
                +
                "Select:";

        String userAnswer = "";

        if (tracker == null) {
            Tracker tracker = new Tracker();
        }


        while (!userAnswer.equals(EXIT)) {

            userAnswer = input.ask(theMainQuestion);

            if (userAnswer.equals(ADDNEWITEM)) {

                this.addNewItem(tracker);

            } else if (userAnswer.equals(SHOWALLITEMS)) {

                this.showAllItems(tracker);

            } else if (userAnswer.equals(EDITITEM)) {

                this.editItem(tracker);

            } else if (userAnswer.equals(DELETEITEM)) {

                this.deleteItem(tracker);

            } else if (userAnswer.equals(FINDITEMBYID)) {

                this.findItemById(tracker);

            } else if (userAnswer.equals(FINDITEMSBYNAME)) {

                this.findItemsByName(tracker);

            }
        }
    }

    /**
     *
     * @param tracker tracker.
     */
    public void addNewItem(Tracker tracker) {

        String userAnswerUserName = input.ask("Enter your name!");
        String userAnswerDescriptionOfTheItem = input.ask("Enter the description of the Item!");
        if (!userAnswerUserName.equals("") && !userAnswerDescriptionOfTheItem.equals("")) {
            Item newItem = new Item(" ", " ", 123L);
            newItem.setName(userAnswerUserName);
            newItem.setDescription(userAnswerDescriptionOfTheItem);
            tracker.add(newItem);
        } else {
            System.out.println("Wrong input!");
        }
    }

    /**
     *
     * @param tracker tracker.
     */
    public void showAllItems(Tracker tracker) {

        String allItems = tracker.showAll();
        System.out.println(allItems);
    }

    /**
     *
     * @param tracker tracker.
     */
    public void editItem(Tracker tracker) {

        String userUnswerIdOfTheItem = input.ask("Enter the id of the Item");
        Item foudedItemById = tracker.findById(userUnswerIdOfTheItem);
        if (foudedItemById.getId() != null) {
            String userUnswerNewNameOfTheItem = input.ask("Enter new name of the Item");
            String userUnswerNewDescreptionOfTheItem = input.ask("Enter new description of the Item");
            if (!userUnswerNewDescreptionOfTheItem.equals("") && !userUnswerNewNameOfTheItem.equals("")) {
                foudedItemById.setName(userUnswerNewNameOfTheItem);
                foudedItemById.setDescription(userUnswerNewDescreptionOfTheItem);
                tracker.edit(foudedItemById);
            } else {
                System.out.println("Wrong input!");
            }
        } else {
            System.out.println("Wrong input!");
        }
    }

    /**
     *
     * @param tracker tracker.
     */
    public void deleteItem(Tracker tracker) {

        String userUnswerIdOfTheItem = input.ask("Enter the id of the Item");
        Item foudedItemById = tracker.findById(userUnswerIdOfTheItem);
        if (foudedItemById.getId() != null) {
            tracker.delete(foudedItemById);
        } else {
            System.out.println("Wrong input!");
        }
    }

    /**
     *
     * @param tracker tracker.
     */
    public void findItemById(Tracker tracker) {

        String userUnswerIdOfTheItem = input.ask("Enter the id of the Item");
        Item foudedItemById = tracker.findById(userUnswerIdOfTheItem);
        if (foudedItemById.getId() != null) {
            System.out.println(tracker.showItem(foudedItemById));
        } else {
            System.out.println("Wrong input!");
        }
    }

    /**
     *
     * @param tracker tracker.
     */
    public void findItemsByName(Tracker tracker) {

        String userAnswerOfTheItemsName = input.ask("Enter the Name of the Item");
        Item[] foudedItemsByName = tracker.findByName(userAnswerOfTheItemsName);
        if (foudedItemsByName.length != 0) {
            for (Item item:foudedItemsByName) {
                System.out.println(tracker.showItem(item));
            }
        } else {
            System.out.println("Wrong input or no items with this name!");
        }
    }

    /**
     *
     * @param args args.
     */
    public static void main(String[] args) {

        Input input = new ConsoleInput(); //StubInput(new String[] {"create stub task"});
//        new StartUi(input).init();
        Tracker tracker = new Tracker();
        new StartUi(input, tracker).init("kjkhjjk");
    }
}
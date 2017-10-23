package ru.job4j.start;

/**
 *
 */
public class StartUi {
    /**
     *
     */
    private Input input;

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

        Tracker tracker = new Tracker();

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
            String userUnswerNewDescreptionOfTheItem = input.ask("Enter new description of the Item");
            if (!userUnswerNewDescreptionOfTheItem.equals("")) {
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
        new StartUi(input).init();
    }
}
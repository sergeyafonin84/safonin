package ru.job4j.start;

/**
 * @author Sergey Afonin (mailto afonin1c@mail.ru)
 */
public class Item {

    /**
     * Item id.
     */
    private String id;

    /**
     * name.
     */
    private String name;

    /**
     * Item description.
     */
    private String description;

    /**
     * Item create.
     */
    private long create;

    /**
     *
     * @param create set create.
     */
    public void setCreate(long create) {
        this.create = create;
    }
    /**
     * constructor.
     */
    public Item() {

    }

    /**
     * constructor.
     * @param name item name.
     * @param description item description.
     * @param create item create.
     */
    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * get name of item.
     * @return name of item.
     */
    String getName() {
        return this.name;
    }

    /**
     * get item description.
     * @return item description.
     */
    public  String getDescription() {
        return this.description;
    }

    /**
     * get item create.
     * @return item create.
     */
    public long getCreate() {
        return this.create;
    }

    /**
     * get item id.
     * @return item id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * set item id.
     * @param id new item id.
     */
    public  void setId(String id) {
        this.id = id;
    }

    /**
     * set item name.
     * @param name new item name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * new item description.
     * @param description new item description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
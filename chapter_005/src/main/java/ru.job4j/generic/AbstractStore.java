package ru.job4j.generic;

//4. После реализации проверьте можно ли избавиться от дублирования кода в вашем проекте.
// UserStore и RoleStore будут иметь один и тот же функционал.
// Общий для них функционал необходимо вынести в абстрактный класс AbstractStore.
public abstract class AbstractStore implements Store {

    private SimpleArray<Base> elements;

    public AbstractStore(int numberOfElements) {
        this.elements = new SimpleArray<>(numberOfElements);
    }

    @Override
    public Base add(Base model) {

        elements.add(model);

        return model;

    }

    //    3. Помните. про инкапсуляцию. В методах store нельзя использовать методы c index,
// т.е. при реализации метода update вам необходимо найти в коллекции элемент с таким же id,
// как и переданный параметр, и если найден, то заменить его
    @Override
    public Base update(Base model) {

        Base baseForUpdate =  findByBaseObjectsInternalId(model.getId());

        elements.update(elements.indexOf(baseForUpdate), model);

        return model;
    }

    @Override
    public boolean delete(String id) {

        boolean returnValue = false;

        Base baseForDelete = this.findByBaseObjectsInternalId(id);

        if (baseForDelete != null) {
            Base oldValue = elements.delete(elements.indexOf(baseForDelete));
            returnValue = true;
        }

        return returnValue;
    }

    public Base findByBaseObjectsInternalId(String id) {

        Base returnValue = null;

        for (int ind = 0; ind < elements.size(); ind++) {
            Base currElement = (Base) elements.get(ind);
            if (currElement.getId() == id) {
                returnValue = currElement;
            }
        }
        return returnValue;
    }

}
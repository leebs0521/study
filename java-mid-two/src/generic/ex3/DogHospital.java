package generic.ex3;

import generic.animal.Dog;

public class DogHospital {

    private Dog animal;

    public void setAnimal(Dog animal) {
        this.animal = animal;
    }

    public void checkup() {
        System.out.println("animal.getName() = " + animal.getName());
        System.out.println("animal.getSize() = " + animal.getSize());
        animal.sound();
    }

    public Dog bigger(Dog target) {
        return animal.getSize() > target.getSize() ? animal : target;
    }
}

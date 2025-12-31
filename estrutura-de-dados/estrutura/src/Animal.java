public abstract class Animal {

    String name;
    int legs;

    public Animal(String nomeAnimal, int legs) {
        this.name = nomeAnimal;
        this.legs = legs;
    };

    public String getName() {
        return name;
    };

    public int getLegs() {
        return legs;
    };

    public void walkDistance() {
        System.out.println("Walking");
    }
}

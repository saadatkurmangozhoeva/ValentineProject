package kg.megacom.models;

import kg.megacom.exceptions.MaxCountSubsWishes;

public class Subscriber{

    private double id;
    private String phone;
    private boolean isActive;
    private int countWishes;

    public Subscriber() {
    }

    public Subscriber(String phone) {
        this.phone = phone;
        this.id = Math.random();
        this.isActive = false;
        countWishes = 0;
    }

    public void setId(double id) {
        this.id = id;
    }

    public int getCountWishes() {
        return countWishes;
    }

    public void incrementSubsWish() {
        if (countWishes >= 2){
            throw new MaxCountSubsWishes("Вы достигли максимального кол-во отправок");
        }
        this.countWishes++;
    }

    public double getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                ", countWishes=" + countWishes +
                '}';
    }
}

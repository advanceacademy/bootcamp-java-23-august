package com.advanceacademy.moonlighthotel.entity.hotel;

public enum RoomType {
    STANDART(2), // Standard rooms can accommodate up to 2 people
    STUDIO(3),    // Studio rooms can accommodate up to 3 people
    APARTMENT(4);  // Apartment rooms can accommodate up to 4 people

    private final int maxPeople;

    RoomType(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getMaxPeople() {
        return maxPeople;
    }


}

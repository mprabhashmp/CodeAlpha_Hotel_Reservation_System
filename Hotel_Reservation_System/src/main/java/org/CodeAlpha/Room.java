package org.CodeAlpha;

public class Room {
    private int roomId;
    private RoomType type;
    private boolean available;

    public Room(int roomId, RoomType type) {
        this.roomId = roomId;
        this.type = type;
        this.available = true;
    }

    public int getRoomId() {
        return roomId;
    }

    public RoomType getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

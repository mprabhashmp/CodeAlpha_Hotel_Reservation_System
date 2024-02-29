package org.CodeAlpha;

import java.util.Date;

public class Hotels {
    private Room[] rooms;
    private Booking[] bookings;
    private int nextBookingId;

    public Hotels(int numRooms) {
        rooms = new Room[numRooms];
        for (int i = 0; i < numRooms; i++) {
            rooms[i] = new Room(i + 1, RoomType.SINGLE); // Example: All rooms are single for simplicity
        }
        bookings = new Booking[100]; // Assuming a maximum of 100 bookings for now
        nextBookingId = 1;
    }

    // Method to search for available rooms based on room type and dates
    public Room[] searchRooms(RoomType type, Date startDate, Date endDate) {
        // Ensure rooms array is not null
        if (rooms == null) {
            System.out.println("No rooms available in the hotel.");
            return new Room[0];
        }

        // Implement search logic here
        // Return an array of available rooms matching the criteria
        // Example: For simplicity, return all available rooms of the specified type
        return rooms;
    }

    // Method to make a reservation
    public Booking makeReservation(Room room, Date startDate, Date endDate) {
        // Check if the room is available for the given dates
        if (!isRoomAvailableForDates(room, startDate, endDate)) {
            System.out.println("Room is not available for the selected dates.");
            return null;
        }

        // Create a new Booking object and add it to the bookings array
        Booking newBooking = new Booking(nextBookingId++, room, startDate, endDate, calculateTotalPrice(startDate, endDate));
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] == null) {
                bookings[i] = newBooking;
                break;
            }
        }

        // Update room availability status
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getRoomId() == room.getRoomId()) {
                rooms[i].setAvailable(false);
                break;
            }
        }

        return newBooking;
    }

    // Method to get booking details
    public Booking getBookingDetails(int bookingId) {
        for (Booking booking : bookings) {
            if (booking != null && booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        System.out.println("Booking not found for ID: " + bookingId);
        return null;
    }

    // Helper method to check if a room is available for the given dates
    private boolean isRoomAvailableForDates(Room room, Date startDate, Date endDate) {
        for (Booking booking : bookings) {
            if (booking != null && booking.getRoom().getRoomId() == room.getRoomId()) {
                // Check if the room is already booked for any overlapping dates
                if (startDate.before(booking.getEndDate()) && endDate.after(booking.getStartDate())) {
                    return false; // Room is not available for the given dates
                }
            }
        }
        return true; // Room is available for the given dates
    }

    // Method to calculate total price for a booking (example implementation)
    private double calculateTotalPrice(Date startDate, Date endDate) {
        // Example: Calculate total price based on duration of stay
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
        // Example: Price per day is $100
        return diffInDays * 100;
    }


}

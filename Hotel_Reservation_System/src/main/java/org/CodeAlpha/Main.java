package org.CodeAlpha;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create a hotel with 10 rooms
        Hotels hotel = new Hotels(10);

        // Get reservation details from the user
        System.out.println("Enter reservation details:");
        System.out.print("Room type (SINGLE/DOUBLE): ");
        RoomType roomType = RoomType.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Start date (YYYY-MM-DD): ");
        Date startDate = parseDate(scanner.nextLine());
        System.out.print("End date (YYYY-MM-DD): ");
        Date endDate = parseDate(scanner.nextLine());

        // Search for available rooms
        Room[] availableRooms = hotel.searchRooms(roomType, startDate, endDate);
        if (availableRooms.length == 0) {
            System.out.println("No rooms available for the selected dates.");
            return;
        }

        // Output the available rooms
        System.out.println("Available Rooms:");
        for (Room room : availableRooms) {
            System.out.println("Room ID: " + room.getRoomId());
        }

        // Ask user to select a room
        System.out.print("Enter the room ID to book: ");
        int roomId = scanner.nextInt();

        // Find the selected room
        Room selectedRoom = null;
        for (Room room : availableRooms) {
            if (room.getRoomId() == roomId) {
                selectedRoom = room;
                break;
            }
        }

        // Make reservation if room is valid
        if (selectedRoom != null) {
            Booking booking = hotel.makeReservation(selectedRoom, startDate, endDate);
            if (booking != null) {
                System.out.println("Reservation successful! Booking ID: " + booking.getBookingId());
            } else {
                System.out.println("Failed to make reservation.");
            }
        } else {
            System.out.println("Invalid room ID.");
        }

        // Close scanner
        scanner.close();
    }

    // Method to parse date strings
    private static Date parseDate(String dateStr) {
        // Implement date parsing logic (e.g., using SimpleDateFormat)
        // For simplicity, assuming date format: "YYYY-MM-DD"
        return new Date();
    }
}
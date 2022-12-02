package edu.baylor.cs.beargo.model;

public enum DeliveryStatus {
    SEARCHING_TRAVELER, // by sender when creating product post
    INITIATED,          // by sender when confirming traveler
    PICKED_UP,          // by sender or traveler
    IN_TRANSIT,         // by traveler only
    DELIVERED,          // by sender only
    UNSUCCESSFUL       // only when contract end date passed
}
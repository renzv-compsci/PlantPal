package com.potsko.model;

/**
 * Represents a journal entry written by a user, possibly about a specific plant.
 * 
 * OOP Principles demonstrated:
 * - Encapsulation: All fields are private and accessed via public getters.
 * - Abstraction: The class abstracts the concept of a journal entry.
 * - Immutability: All fields are final and set only via the constructor.
 * - (Optionally) Association: userPlantId links this entry to a plant (if any).
 */
public class JournalEntry {
    private final int entryId;
    private final int userId;
    private final Integer userPlantId; // nullable
    private final String title;
    private final String content;
    private final long dateWritten;

    public JournalEntry(int entryId, int userId, Integer userPlantId, String title, String content, long dateWritten) {
        this.entryId = entryId;
        this.userId = userId;
        this.userPlantId = userPlantId;
        this.title = title;
        this.content = content;
        this.dateWritten = dateWritten;
    }

    public int getEntryId() {
        return entryId;
    }

    public int getUserId() {
        return userId;
    }

    public Integer getUserPlantId() {
        return userPlantId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getDateWritten() {
        return dateWritten;
    }
}

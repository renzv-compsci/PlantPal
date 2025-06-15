package com.potsko.model;

public class JournalEntry {
    private int entryId;
    private int userId;
    private Integer userPlantId; // nullable
    private String title;
    private String content;
    private long dateWritten;

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

    // Setters...
}

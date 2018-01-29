package de.emeraldmc.chatsystem;

public class ChatMessageQueue {
    private static final int size = 10; // Main.getInstance().getConfig().getInt("Config.messageCheckSize");

    private ChatMessageLight[] chatMessages = new ChatMessageLight[size];
    private int head = 0;
    private int tail = 0;

    public ChatMessageQueue() {

    }

    /**
     * Removes the last entry (fifo = first in first out)
     */
    private void removeLast() {
        head = (head + 1) % size;
    }

    /**
     * Puts a new Object at the end of the queue
     * @param message the object to put in the queue
     */
    public void put(ChatMessageLight message) {
        boolean overflow = (tail + 1) % size == head;
        if (overflow) removeLast();
        chatMessages[tail] = message;
        tail = (tail + 1) % size;
    }

    /**
     * Get the first element of the queue
     * @return the first element of the queue
     * @throws IndexOutOfBoundsException
     */
    public ChatMessageLight getFirst() throws IndexOutOfBoundsException {
        if (tail == 0) return chatMessages[size-1];
        return chatMessages[tail-1];
    }
    /**
     * Get the ChatCommands array
     * @return the ChatCommands array
     */
    public ChatMessageLight[] getChatMessages() {
        return chatMessages;
    }

}

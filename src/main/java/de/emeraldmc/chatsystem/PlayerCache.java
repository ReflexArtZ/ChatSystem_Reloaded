package de.emeraldmc.chatsystem;

import de.emeraldmc.chatsystem.utils.Debug;
import org.bukkit.entity.Player;

public class PlayerCache {
    private ChatMessageQueue messageQueue = new ChatMessageQueue();

    /**
     *
     */
    public PlayerCache() {
    }

    /**
     * Check if the message was sent too fast
     * @param interaction
     * @return
     */
    private boolean checkTime(ChatMessageLight interaction) {
        long timeDiff = interaction.getTime() - messageQueue.getFirst().getTime();
        Debug.print("Time difference of "+interaction.getMessage()+" and "+messageQueue.getFirst().getMessage()+" is"+"\t"+timeDiff);
        return timeDiff < Main.configuration.MIN_TIME_DIFF;
    }

    /**
     * Check if the message is spammed
     * @param interaction current interaction
     * @param interactions all interactions
     * @return
     */
    private boolean checkSpam(ChatMessageLight interaction, ChatMessageLight[] interactions) {
        int cnt = 0;
        for (ChatMessageLight c : interactions) {
            if (c == null) continue;
            int distance = c.levenshteinDistance(interaction.getMessage());
            if (distance < Main.configuration.MIN_LEVENSTHEIN_DISTANCE) {
                cnt++;
            }
            Debug.print("LevenstheinDistance of "+interaction.getMessage()+" and "+c.getMessage()+" is"+"\t"+distance);
        }
        return cnt > Main.configuration.FREQ_MAX_MIN_LEVENSTHEIN_DISTANCE;
    }

    /**
     * Check if the message contains an ip
     * @param s
     * @return
     */
    private boolean checkIp(String s) {
        java.util.regex.Matcher m = java.util.regex.Pattern.compile(
                "(?<!\\d|\\d\\.)" +
                        "(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])" +
                        "(?!\\d|\\.\\d)").matcher(s);
        boolean result = m.find();
        Debug.print("Checked if there is and IP in "+s+" result:"+"\t"+result);
        return result;
    }

    /**
     * check if the message contains a domain
     * @param s
     * @return
     */
    private boolean checkDomain(String s) {
        final String[] tlds = {".at", ".de", ".com", ".eu", ".us", ".net", ".ch", ".io", ".org", ".info", ".biz", ".cc", ".uk", ".li", ".tk", ".me", "nl", ".tv"};
        Debug.print("Check if there is an Domain in "+s);
        for (String tld : tlds) {
            if (s.toLowerCase().contains(tld)) {
                Debug.print("Found Domain! "+tld);
                return true;
            }
        }
        return false;
    }

    public ChatIssue checkMessage(ChatMessageLight message) {
        ChatMessageLight[] chatMessages = messageQueue.getChatMessages();

        if (checkTime(message)) return ChatIssue.TIME;
        if (checkSpam(message, chatMessages)) return ChatIssue.SPAM;
        if (checkIp(message.getMessage())) return ChatIssue.AD;
        if (checkDomain(message.getMessage())) return ChatIssue.AD;
        if (message.capsCount() > Main.configuration.MAX_CAPS) return ChatIssue.CAPS;
        return null;
    }

    public ChatMessageQueue getMessageQueue() {
        return messageQueue;
    }
}

package de.emeraldmc.chatsystem;

import de.emeraldmc.chatsystem.utils.Percentage;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class ChatMessageLight {
    private String message;

    private long time;

    public ChatMessageLight(String message) {
        this.message = message;
        time = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }

    /**
     * calculates the levensthein-distance
     * @param t
     * @return levensthein-distance
     */
    public int levenshteinDistance(String t) {
        final int sLen = message.length(), tLen = t.length();

        if (sLen == 0)
            return tLen;
        if (tLen == 0)
            return sLen;

        int[] costsPrev = new int[sLen + 1]; // previous cost array, horiz.
        int[] costs = new int[sLen + 1];     // cost array, horizontally
        int[] tmpArr;                        // helper to swap arrays
        int sIndex, tIndex;                  // current s and t index
        int cost;                            // current cost value
        char tIndexChar;                     // char of t at tIndexth pos.

        for (sIndex = 0; sIndex <= sLen; sIndex++)
            costsPrev[sIndex] = sIndex;

        for (tIndex = 1; tIndex <= tLen; tIndex++) {
            tIndexChar = t.charAt(tIndex - 1);
            costs[0] = tIndex;

            for (sIndex = 1; sIndex <= sLen; sIndex++) {
                cost = (message.charAt(sIndex - 1) == tIndexChar) ? 0 : 1;

                costs[sIndex] = Math.min(Math.min(costs[sIndex - 1] + 1,
                        costsPrev[sIndex] + 1),
                        costsPrev[sIndex - 1] + cost);
            }

            tmpArr = costsPrev;
            costsPrev = costs;
            costs = tmpArr;
        }


        return costsPrev[sLen];
    }
    /**
     * Caps Count
     * @return Number of Capsed Chars
     */
    public int capsCount() {
        int cnt = 0;
        char[] arr = message.trim().toCharArray();
        for (char c : arr) {
            if (Character.isUpperCase(c)) cnt++;
        }
        return cnt;
    }
}

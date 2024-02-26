package com.whiletruebackend.global.utils;

import com.whiletruebackend.domain.Problem.vo.IconType;
import com.whiletruebackend.domain.Problem.vo.SiteType;

public class ProblemDataConverter {

    public static IconType createIconType(SiteType siteType) {
        switch (siteType) {
            case BOJ -> {
                return IconType.EXTERNAL;
            }
            case PROGRAMMERS, SWEA -> {
                return IconType.EMOJI;
            }
        }
        return null;
    }

    public static String createIconSrc(SiteType siteType, String inputLevel) {
        switch (siteType) {
            case BOJ -> {
                return String.format("https://d2gd6pc034wcta.cloudfront.net/tier/%s.svg", inputLevel);
            }
            case PROGRAMMERS -> {
                String[] icons = { "0️⃣", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣" };
                return icons[Integer.parseInt(inputLevel)];
            }
            case SWEA -> {
                String[] icons = { "", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣" };
                return icons[Integer.parseInt(inputLevel)];
            }
        }
        return null;
    }

    public static String createLevelTag(SiteType siteType, String inputLevel) {
        switch (siteType) {
            case BOJ -> {
                String[] levels = { "Unrated", "Bronze 5", "Bronze 4", "Bronze 3", "Bronze 2", "Bronze 1", "Silver 5", "Silver 4",
                        "Silver 3", "Silver 2", "Silver 1", "Gold 5", "Gold 4", "Gold 3", "Gold 2", "Gold 1", "Platinum 5", "Platinum 4",
                        "Platinum 3", "Platinum 2", "Platinum 1", "Diamond 5", "Diamond 4", "Diamond 3", "Diamond 2", "Diamond 1", "Ruby 5",
                        "Ruby 4", "Ruby 3", "Ruby 2", "Ruby 1"
                };
                return levels[Integer.parseInt(inputLevel)];
            }
            case PROGRAMMERS -> {
                String[] levels = { "Lv. 0", "Lv. 1", "Lv. 2", "Lv. 3", "Lv. 4", "Lv. 5" };
                return levels[Integer.parseInt(inputLevel)];
            }
            case SWEA -> {
                String[] levels = { "", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8" };
                return levels[Integer.parseInt(inputLevel)];
            }
        }
        return "Unrated";
    }
}

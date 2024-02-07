package com.whiletruebackend.global.notion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class NotionPropertyType {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Select {

        private SelectItem select;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SelectItem {

        private String name;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Number {

        private String number;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Url {

        private String url;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Title {

        private List<NotionPropertyType.Text> title;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Text {

        @JsonProperty("plain_text")
        private String plainText;

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Icon {

        private String emoji;
    }
}

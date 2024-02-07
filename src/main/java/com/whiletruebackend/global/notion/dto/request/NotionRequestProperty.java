package com.whiletruebackend.global.notion.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class NotionRequestProperty {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Select {

        private SelectItem select;

        public static Select create(String selected) {
            return new Select(new SelectItem(selected));
        }
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class SelectItem {

        private String name;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Title {

        private List<Text> title;

        public static Title create(String title) {
            return new Title(List.of(new Text(new TextContent(title))));
        }
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Text {

        private TextContent text;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class TextContent {

        private String content;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Number {

        private Integer number;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Url {

        private String url;
    }
}

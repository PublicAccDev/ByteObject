package org.pacc.ByteObj.Format.Object.Json;

import lombok.Getter;


public class JsonFormatConfig
{
    @Getter
    private final JsonFormatOption option;
    @Getter
    private final String indent;
    @Getter
    private final boolean spaceAfterColon;
    @Getter
    private final boolean spaceAfterComma;
    @Getter
    private final int maxLineWidth;

    public JsonFormatConfig(Builder builder)
    {
        this.option = builder.option;
        this.indent = builder.indent;
        this.spaceAfterColon = builder.spaceAfterColon;
        this.spaceAfterComma = builder.spaceAfterComma;
        this.maxLineWidth = builder.maxLineWidth;
    }

    public JsonFormatConfig(JsonFormatOption option)
    {
        this.option = option;
        this.maxLineWidth = 80;
        switch (option)
        {
            case COMPACT:
                this.indent = "";
                this.spaceAfterColon = false;
                this.spaceAfterComma = false;
                break;
            case PRETTY:
                this.indent = "  ";
                this.spaceAfterColon = true;
                this.spaceAfterComma = true;
                break;
            case PRETTY_TAB:
                this.indent = "\t";
                this.spaceAfterColon = true;
                this.spaceAfterComma = true;
                break;
            default:
                this.indent = "  ";
                this.spaceAfterColon = true;
                this.spaceAfterComma = true;
                break;
        }
    }

    public JsonFormatConfig(JsonFormatOption option, String indent, boolean spaceAfterColon, boolean spaceAfterComma, int maxLineWidth)
    {
        this.option = option;
        this.indent = indent;
        this.spaceAfterColon = spaceAfterColon;
        this.spaceAfterComma = spaceAfterComma;
        this.maxLineWidth = maxLineWidth;
    }

    public static class Builder
    {
        private JsonFormatOption option = JsonFormatOption.PRETTY;
        private String indent = "  ";
        private boolean spaceAfterColon = true;
        private boolean spaceAfterComma = true;
        private int maxLineWidth = 80;

        public Builder withOption(JsonFormatOption option)
        {
            this.option = option;
            switch (option)
            {
                case COMPACT:
                    this.indent = "";
                    this.spaceAfterColon = false;
                    this.spaceAfterComma = false;
                    break;
                case PRETTY:
                    this.indent = "  ";
                    this.spaceAfterColon = true;
                    this.spaceAfterComma = true;
                    break;
                case PRETTY_TAB:
                    this.indent = "\t";
                    this.spaceAfterColon = true;
                    this.spaceAfterComma = true;
                    break;
                default:
                    break;
            }
            return this;
        }

        public Builder withIndent(String indent)
        {
            this.indent = indent;
            return this;
        }

        public Builder withSpaceAfterColon(boolean spaceAfterColon)
        {
            this.spaceAfterColon = spaceAfterColon;
            return this;
        }

        public Builder withSpaceAfterComma(boolean spaceAfterComma)
        {
            this.spaceAfterComma = spaceAfterComma;
            return this;
        }

        public Builder withMaxLineWidth(int maxLineWidth)
        {
            this.maxLineWidth = maxLineWidth;
            return this;
        }

        public JsonFormatConfig build()
        {
            return new JsonFormatConfig(this);
        }
    }
}

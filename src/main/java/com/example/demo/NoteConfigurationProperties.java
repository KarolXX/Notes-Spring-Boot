package com.example.demo;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("note")
public class NoteConfigurationProperties {
    private Template template;

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public static class Template {
        private boolean allowMultipleNotes;

        public boolean isAllowMultipleNotes() {
            return allowMultipleNotes;
        }

        public void setAllowMultipleNotes(boolean allowMultipleNotes) {
            this.allowMultipleNotes = allowMultipleNotes;
        }
    }
}

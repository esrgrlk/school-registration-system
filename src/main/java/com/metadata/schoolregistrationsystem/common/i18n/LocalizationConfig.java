package com.metadata.schoolregistrationsystem.common.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class LocalizationConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages/course_messages_en",
                                   "classpath:/messages/student_messages_en");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
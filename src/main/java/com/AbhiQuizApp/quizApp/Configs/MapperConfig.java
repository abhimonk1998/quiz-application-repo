package com.AbhiQuizApp.quizApp.Configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.AbhiQuizApp.quizApp.Mapper") // Specify the package containing QuizMapper
public class MapperConfig {
    // You can define additional beans or configurations here
}
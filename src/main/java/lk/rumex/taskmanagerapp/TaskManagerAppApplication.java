package lk.rumex.taskmanagerapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskManagerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerAppApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}

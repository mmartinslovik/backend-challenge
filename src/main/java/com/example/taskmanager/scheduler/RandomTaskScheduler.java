package com.example.taskmanager.scheduler;

import com.example.taskmanager.api.CreateTaskDto;
import com.example.taskmanager.api.TaskPriority;
import com.example.taskmanager.api.TaskStatus;
import com.example.taskmanager.facade.TaskFacade;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;


@Service
public class RandomTaskScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RandomTaskScheduler.class);

    private final TaskFacade<Long> taskFacade;

    private final Random random = new Random();

    @Autowired
    public RandomTaskScheduler(TaskFacade<Long> taskFacade) {
        this.taskFacade = taskFacade;
    }

    @Scheduled(fixedRate = 15000)
    public void createTask() {
        CreateTaskDto taskDto = new CreateTaskDto();
        taskDto.setDueDate(generateRandomLocalDateTime());
        taskDto.setResolvedAt(generateRandomLocalDateTime());
        taskDto.setTitle(generateRandomString(10));
        taskDto.setDescription(generateRandomString(20));
        taskDto.setPriority(generateRandomPriority());
        taskDto.setStatus(generateRandomStatus());

        try {
            logger.info("Performing scheduled task.");
            taskFacade.save(taskDto);
            logger.info("Scheduled task saved successfully.");
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    private LocalDateTime generateRandomLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        long daysToAdd = random.nextInt(30);
        return now.plusDays(daysToAdd);
    }

    private String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    private TaskPriority generateRandomPriority() {
        TaskPriority[] priorities = TaskPriority.values();
        return priorities[random.nextInt(priorities.length)];
    }

    private TaskStatus generateRandomStatus() {
        TaskStatus[] statuses = TaskStatus.values();
        return statuses[random.nextInt(statuses.length)];
    }
}

package com.LinkUp.repository;

import com.LinkUp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findByChatId(Integer chatId);
}

package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Model.entity.Message;
import com.uca.m2.pdd.Model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findBySenderOrReceiver(Users sender, Users receiver);
}

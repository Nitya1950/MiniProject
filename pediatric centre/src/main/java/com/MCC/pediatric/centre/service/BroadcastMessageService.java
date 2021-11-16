package com.MCC.pediatric.centre.service;

import com.MCC.pediatric.centre.repository.BroadcastMessageEntity;
import com.MCC.pediatric.centre.repository.BroadcastMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class BroadcastMessageService {
    private static final String BROADCAST_MESSAGE_ID = "BROADCAST_MESSAGE_ID";
    @Autowired
    private BroadcastMessageRepository bmr;

    @Transactional
    public String setBroadcastMessage(String message) {
        Optional<BroadcastMessageEntity> optional = bmr.findById(BROADCAST_MESSAGE_ID);
        BroadcastMessageEntity entity = optional.orElseGet(BroadcastMessageEntity::new);
        entity.setId(BROADCAST_MESSAGE_ID);
        entity.setMessage(message);
        bmr.save(entity);
        return message;
    }

    @Transactional
    public Optional<String> getBroadcastMessage() {
        return bmr.findById(BROADCAST_MESSAGE_ID).map(BroadcastMessageEntity::getMessage);
    }
}

package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Story;
import com.somelight.project.db.repository.CommunityRepository;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommunityRepository communityRepository;

    public List<Story> getUserStories(int user_id){
        List<Story> userStories = communityRepository.getStoriesByUserId(user_id).orElse(null);
        return userStories;
    }

    public Story registerStory(String email, String content, int result){
        int user_id = userRepository.findByEmail(email).get().getUser_id();
        LocalDateTime created_date = LocalDateTime.now();
        Story story = Story.builder()
                .user_id(user_id)
                .content(content)
                .result(result)
                .created_date(created_date)
                .build();
        communityRepository.save(story);
        return story;
    }
}

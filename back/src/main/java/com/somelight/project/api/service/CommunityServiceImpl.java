package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Story;
import com.somelight.project.db.repository.CommunityRepository;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService{
    private UserRepository userRepository;
    private CommunityRepository communityRepository;

    @Autowired
    public CommunityServiceImpl(UserRepository userRepository, CommunityRepository communityRepository) {
        this.userRepository = userRepository;
        this.communityRepository = communityRepository;
    }

    public Page<Story> findStories(Pageable pageable) {
        Page<Story> articles = null;
        articles = communityRepository.findAll(pageable);
        return articles;
    }

    public List<Story> getUserStories(int user_id){
        List<Story> userStories = communityRepository.getStoriesByUserId(user_id).orElse(null);
        return userStories;
    }

    public Story registerStory(String email, String content, int result){
        int userId = userRepository.findByEmail(email).get().getUserId();
        LocalDateTime created_date = LocalDateTime.now();
        Story story = Story.builder()
                .userId(userId)
                .content(content)
                .result(result)
                .createdDate(created_date)
                .build();
        communityRepository.save(story);
        return story;
    }
}

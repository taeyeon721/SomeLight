package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Vote;
import com.somelight.project.db.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService{
    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote getVoteByArticleIdAndUserId(int articleId, int userId) {
        Vote vote = voteRepository.findByArticleIdAndUserId(articleId, userId).orElse(null);
        return vote;
    }

    @Override
    public void updateVote(int userId, int articleId, int voteResult) {
        Vote vote = voteRepository.findByArticleIdAndUserId(articleId, userId).orElse(null);
        if (vote == null) {
            voteRepository.save(Vote.builder()
                    .articleId(articleId)
                    .userId(userId)
                    .voteResult(voteResult)
                    .build());
        } else {
            vote.setVoteResult(voteResult);
            voteRepository.save(vote);
        }
    }

    @Override
    @Transactional
    public void deleteVoteList(int articleId) {
        voteRepository.deleteAllByArticleId(articleId);
    }
}

package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.ArticleMapper;
import com.savvas.jobapp.model.Ad;
import com.savvas.jobapp.model.Article;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.model.UserLikesArticle;
import com.savvas.jobapp.repository.ArticleRepository;
import com.savvas.jobapp.repository.UserLikesArticleRepository;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.ArticleCreateRequest;
import com.savvas.jobapp.requests.ArticleLikeRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.AdResponse;
import com.savvas.jobapp.responses.ArticleResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserLikesArticleRepository userLikesArticleRepository;

    @GetMapping("/all")
    public List<ArticleResponse> all() {
        List<Article> all = articleRepository.findAll();
        List<ArticleResponse> result = articleMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public ArticleResponse create(@RequestBody ArticleCreateRequest request) {
        Article entity = articleMapper.toEntity(request);

        Article article = articleRepository.saveAndFlush(entity);

        return articleMapper.fromEntity(article);
    }

    @PostMapping("/like")
    public ArticleResponse like(@RequestBody ArticleLikeRequest request) {
        UserLikesArticle ula = new UserLikesArticle();

        ula.setArticle_id(request.getArticle_id());
        ula.setUser_id(request.getUser_id());

        DateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date now = new Date();
        ula.setDate(formatter.format(now));

        userLikesArticleRepository.save(ula);

        Optional<Article> article = articleRepository.findById(ula.getArticle_id());
        return articleMapper.fromEntity(article.get());
    }


    @GetMapping("/id/{value}")
    public ArticleResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<Article> article = articleRepository.findById(value);

        if (article.isPresent()) {
            return articleMapper.fromEntity(article.get());
        } else {
            throw new ResourceNotFoundException("Article", "id", value);
        }
    }

    @GetMapping("/user/{value}")
    public List<ArticleResponse> findByUserID(@PathVariable(value = "value") Integer value) {
        Optional<User> user = userRepository.findById(value);

        List<ArticleResponse> result = new ArrayList<>();

        if (user.isPresent()) {
            List<Article> likedArticles = user.get().getLikedArticles();
            for (Article article : likedArticles) {
                ArticleResponse articleResponse = articleMapper.fromEntity(article);
                result.add(articleResponse);
            }
        }

        return result;
    }

    // ----------------------------------------------------------------------

    @GetMapping("/author/{value}")
    public List<ArticleResponse> author(@PathVariable(value = "value") Integer value) {
        Optional<User> opt = userRepository.findById(value);

        List<ArticleResponse> result = new ArrayList<>();

        if (opt.isPresent()) {
            User user = opt.get();

            Set<Article> myarticles = user.getArticles();
            for (Article article : myarticles) {
                ArticleResponse articleResponse = articleMapper.fromEntity(article);
                result.add(articleResponse);
            }
        }

        return result;
    }

    @GetMapping("/friends/{value}")
    public List<ArticleResponse> friendsOf(@PathVariable(value = "value") Integer value) {
        Optional<User> user = userRepository.findById(value);

        List<ArticleResponse> result = new ArrayList<>();

        if (user.isPresent()) {
            for (User friend : user.get().getConnectedUsers()) {
                for (Article a : friend.getArticles()) {
                    ArticleResponse articleResponse = articleMapper.fromEntity(a);
                    result.add(articleResponse);
                }
            }
        }

        return result;
    }

    @GetMapping("/liked/{value}")
    public List<ArticleResponse> liked(@PathVariable(value = "value") Integer value) {
        Optional<User> user = userRepository.findById(value);

        List<ArticleResponse> result = new ArrayList<>();

        if (user.isPresent()) {
            for (User friend : user.get().getConnectedUsers()) {
                for (Article a : friend.getLikedArticles()) {
                    ArticleResponse articleResponse = articleMapper.fromEntity(a);
                    result.add(articleResponse);
                }
            }
        }

        return result;
    }

    @GetMapping("/recommended/{value}")
    public List<ArticleResponse> recommended(@PathVariable(value = "value") Integer value) {
        Optional<User> user = userRepository.findById(value);

        List<ArticleResponse> result = new ArrayList<>();

        if (user.isPresent()) {
            for (Article a : matrixFactorization(user.get())) {
                ArticleResponse articleResponse = articleMapper.fromEntity(a);
                result.add(articleResponse);
            }
        }

        return result;
    }

    private float [][] transpose(float [][] Q) {
        int N = Q.length;
        int M = Q[0].length;
        float [][] QT = new float [M][N];

        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                QT[j][i] = Q[i][j];
            }
        }
        return QT;
    }

    private float dot(float [][] P, float [][] Q, int i,int j) {
        int k = P[i].length;
        float dot = 0.0f;
        for (int x=0;x<k;x++) {
            dot += P[i][x]*Q[x][j];
        }
        return dot;
    }

    private float [][] dot(float [][] P, float [][] Q) {
        int N = P.length;
        int M = P[0].length;

        float [][] PQ = new float [N][M];

        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                PQ[i][j] = dot(P, Q, i ,j );
            }
        }

        return PQ;
    }

    private float [][] matrixFactorization(float [][] R, float [][] P, float [][] Q, int K) {
        int steps = 10;
        float alpha = 0.0002f;
        float beta = 0.0f;
        Q = transpose(Q);
        int N = R.length;
        int M = R[0].length;

        for (int step = 0;step< steps;step++) {
            for (int i =0;i<N;i++) {
                for (int j=0;j<M;j++) {
                    if (R[i][j] > 0) {
                        float eij = R[i][j] - dot(P, Q, i , j);

                        for (int k=0;k<K;k++) {
                            P[i][k] += alpha * (2*eij*Q[k][j] - beta*P[i][k]);
                            Q[k][j] += alpha * (2*eij*P[i][k] - beta*Q[k][j]);
                        }
                    }
                }
            }
            float e = 0;

            for (int i =0;i<N;i++) {
                for (int j=0;j<M;j++) {
                    if (R[i][j] > 0) {
                        e += Math.pow(R[i][j] - dot(P, Q, i , j),2);

                        for (int k=0;k<K;k++) {
                            e += (beta/2)*(Math.pow(P[i][k], 2) + Math.pow(Q[k][j],2));
                        }
                    }
                }
            }

            if (e < 0.001f) {
                break;
            }

        }

        float [][] PQ = dot(P,Q);

        return PQ;
    }
    private List<Article> matrixFactorization(User target) {
        List<User> users = userRepository.findAll();
        List<Article> articles = articleRepository.findAll();

        if (articles.size() > 1000) {
            Collections.shuffle(articles);
            articles = articles.stream().limit(1000).collect(Collectors.toList());
        }

        int N = users.size();
        int M = articles.size();
        int K = M/2 + 1;
        int top = 3;

        HashMap<Integer, Integer> user_id_to_offset = new HashMap<>();
        HashMap<Integer, Integer> article_id_to_offset = new HashMap<>();


        for (int i=0;i<N;i++) {
            user_id_to_offset.put(users.get(i).getId(), i);
        }

        for (int i=0;i<M;i++) {
            article_id_to_offset.put(articles.get(i).getId(), i);
        }

        float [][] R = new float[N][M];

        for (User u : users) {
            for (Article a : u.getLikedArticles()) {
                int ui = user_id_to_offset.get(u.getId());
                int ai = article_id_to_offset.get(a.getId());
                R[ui][ai]++;
            }
        }

        List<Article> candidates = new ArrayList<>();
        for (Article a : articles) {
            int ui = user_id_to_offset.get(target.getId());
            int ai = article_id_to_offset.get(a.getId());
            if (R[ui][ai] == 0) {
                candidates.add(a);
            }
        }

        if (candidates.size() <= top) {
            return candidates;
        }

        float [][] P = new float[N][K];
        float [][] Q = new float[N][K];

        float [][] PQ = matrixFactorization(R, P, Q, K);

        int ui = user_id_to_offset.get(target.getId());

        Collections.sort(candidates, (a1, a2) ->  Float.compare(R[ui][article_id_to_offset.get(a1.getId())] , R[ui][article_id_to_offset.get(a2.getId())]));

        List<Article> recommendation = candidates.stream().limit(top).collect(Collectors.toList());

        return recommendation;
    }
}

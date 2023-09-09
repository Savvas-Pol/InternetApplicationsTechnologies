package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.AdMapper;
import com.savvas.jobapp.model.*;
import com.savvas.jobapp.repository.AdRepository;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.repository.UserViewsAdRepository;
import com.savvas.jobapp.requests.AdCreateRequest;
import com.savvas.jobapp.requests.AdViewRequest;
import com.savvas.jobapp.requests.ArticleLikeRequest;
import com.savvas.jobapp.responses.AdResponse;
import com.savvas.jobapp.responses.ArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ad")
public class AdController {
    @Autowired
    AdRepository adRepository;

    @Autowired
    AdMapper adMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserViewsAdRepository userViewsAdRepository;

    @GetMapping("/all")
    public List<AdResponse> all() {
        List<Ad> all = adRepository.findAll();
        List<AdResponse> result = adMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public AdResponse create(@RequestBody AdCreateRequest request) {
        Ad entity = adMapper.toEntity(request);

        Ad ad = adRepository.saveAndFlush(entity);

        return adMapper.fromEntity(ad);
    }

    @PostMapping("/view")
    public AdResponse view(@RequestBody AdViewRequest request) {
        UserViewsAd uva = new UserViewsAd();

        uva.setAd_id(request.getAd_id());
        uva.setUser_id(request.getUser_id());

        userViewsAdRepository.save(uva);

        Optional<Ad> ad = adRepository.findById(uva.getAd_id());
        return adMapper.fromEntity(ad.get());
    }

    @GetMapping("/id/{value}")
    public AdResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<Ad> ad = adRepository.findById(value);

        if (ad.isPresent()) {
            return adMapper.fromEntity(ad.get());
        } else {
            throw new ResourceNotFoundException("Ad", "id", value);
        }
    }

    @GetMapping("/user/{value}")
    public List<AdResponse> findByUserID(@PathVariable(value = "value") Integer value) {
        Optional<User> user = userRepository.findById(value);

        List<AdResponse> result = new ArrayList<>();

        if (user.isPresent()) {
            List<Ad> viewedAds = user.get().getViewedAds();
            for (Ad ad : viewedAds) {
                AdResponse adResponse = adMapper.fromEntity(ad);
                result.add(adResponse);
            }
        }

        return result;
    }

    @GetMapping("/author/{value}")
    public List<AdResponse> author(@PathVariable(value = "value") Integer value) {
        Optional<User> opt = userRepository.findById(value);

        List<AdResponse> result = new ArrayList<>();

        if (opt.isPresent()) {
            User user = opt.get();

            Set<Ad> myads = user.getAds();
            for (Ad ad : myads) {
                AdResponse adResponse = adMapper.fromEntity(ad);
                result.add(adResponse);
            }
        }

        return result;
    }

    @GetMapping("/friends/{value}")
    public List<AdResponse> friendsOf(@PathVariable(value = "value") Integer value) {
        Optional<User> user = userRepository.findById(value);

        List<AdResponse> result = new ArrayList<>();

        if (user.isPresent()) {
            for (User friend : user.get().getConnectedUsers()) {
                for (Ad a : friend.getAds()) {
                    AdResponse adResponse = adMapper.fromEntity(a);
                    result.add(adResponse);
                }
            }
        }

        return result;
    }

    @GetMapping("/applied/{value}")
    public List<AdResponse> liked(@PathVariable(value = "value") Integer value) {
        Optional<User> user = userRepository.findById(value);

        List<AdResponse> result = new ArrayList<>();

        if (user.isPresent()) {
            for (User friend : user.get().getConnectedUsers()) {
                for (Ad a : friend.getViewedAds()) {
                    AdResponse adResponse = adMapper.fromEntity(a);
                    result.add(adResponse);
                }
            }
        }

        return result;
    }

    @GetMapping("/recommended/{value}")
    public List<AdResponse> recommended(@PathVariable(value = "value") Integer value) {
        Optional<User> user = userRepository.findById(value);

        List<AdResponse> result = new ArrayList<>();

        if (user.isPresent()) {
            for (Ad a : matrixFactorization(user.get())) {
                AdResponse adResponse = adMapper.fromEntity(a);
                result.add(adResponse);
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
    private List<Ad> matrixFactorization(User target) {
        List<User> users = userRepository.findAll();
        List<Ad> ads = adRepository.findAll();

        if (ads.size() > 1000) {
            Collections.shuffle(ads);
            ads = ads.stream().limit(1000).collect(Collectors.toList());
        }

        int N = users.size();
        int M = ads.size();
        int K = M/2 + 1;
        int top = 3;

        HashMap<Integer, Integer> user_id_to_offset = new HashMap<>();
        HashMap<Integer, Integer> ad_id_to_offset = new HashMap<>();


        for (int i=0;i<N;i++) {
            user_id_to_offset.put(users.get(i).getId(), i);
        }

        for (int i=0;i<M;i++) {
            ad_id_to_offset.put(ads.get(i).getId(), i);
        }

        float [][] R = new float[N][M];

        for (User u : users) {
            for (Article a : u.getLikedArticles()) {
                int ui = user_id_to_offset.get(u.getId());
                int ai = ad_id_to_offset.get(a.getId());
                R[ui][ai]++;
            }
        }

        List<Ad> candidates = new ArrayList<>();
        for (Ad a : ads) {
            int ui = user_id_to_offset.get(target.getId());
            int ai = ad_id_to_offset.get(a.getId());
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

        Collections.sort(candidates, (a1, a2) ->  Float.compare(R[ui][ad_id_to_offset.get(a1.getId())] , R[ui][ad_id_to_offset.get(a2.getId())]));

        List<Ad> recommendation = candidates.stream().limit(top).collect(Collectors.toList());

        return recommendation;
    }
}

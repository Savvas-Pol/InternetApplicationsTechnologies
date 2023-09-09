import { Vue } from "vue-property-decorator";
import {
  FIND_ALL_ARTICLES,
  FIND_ARTICLE_BY_ID,
  INSERT_COMMENT,
  FIND_MY_ARTICLES,
  FIND_FRIENDS_ARTICLES,
  FIND_FRIENDS_LIKED_ARTICLES,
  FIND_RECOMMENDED_ARTICLES,
  INSERT_ARTICLE,
  LIKE_ARTICLE,
} from "@/axios/api";

class ArticleService {
  public list() {
    return Vue.axios.get(FIND_ALL_ARTICLES);
  }

  public getArticleDetails(id: number) {
    return Vue.axios.get(FIND_ARTICLE_BY_ID + id);
  }

  public submitComment(data: Record<string, unknown>) {
    return Vue.axios.post(INSERT_COMMENT, data);
  }

  public getMyArticles(id: number) {
    return Vue.axios.get(FIND_MY_ARTICLES + id);
  }

  public getFriendsArticles(id: number) {
    return Vue.axios.get(FIND_FRIENDS_ARTICLES + id);
  }

  public getFriendsLikedArticles(id: number) {
    return Vue.axios.get(FIND_FRIENDS_LIKED_ARTICLES + id);
  }

  public getRecommendedArticles(id: number) {
    return Vue.axios.get(FIND_RECOMMENDED_ARTICLES + id);
  }

  public publish(data: Record<string, unknown>) {
    return Vue.axios.post(INSERT_ARTICLE, data);
  }

  public like(data: Record<string, unknown>) {
    return Vue.axios.post(LIKE_ARTICLE, data);
  }
}

export default new ArticleService();

import { Vue } from "vue-property-decorator";
import {
  FIND_ALL_ADS,
  FIND_AD_BY_ID,
  FIND_MY_ADS,
  FIND_FRIENDS_ADS,
  FIND_APPLIED_ADS,
  FIND_RECOMMENDED_ADS,
  INSERT_APPLICATION,
} from "@/axios/api";

class AdService {
  public list() {
    return Vue.axios.get(FIND_ALL_ADS);
  }

  public getAdDetails(id: number) {
    return Vue.axios.get(FIND_AD_BY_ID + id);
  }

  public getMyAds(id: number) {
    return Vue.axios.get(FIND_MY_ADS + id);
  }

  public getFriendsAds(id: number) {
    return Vue.axios.get(FIND_FRIENDS_ADS + id);
  }

  public getAppliedAds(id: number) {
    return Vue.axios.get(FIND_APPLIED_ADS + id);
  }

  public getRecommendedAds(id: number) {
    return Vue.axios.get(FIND_RECOMMENDED_ADS + id);
  }

  public applyToAd(data: Record<string, unknown>) {
    return Vue.axios.post(INSERT_APPLICATION, data);
  }
}

export default new AdService();

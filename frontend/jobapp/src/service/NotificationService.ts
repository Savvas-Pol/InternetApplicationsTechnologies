import { Vue } from "vue-property-decorator";
import { FIND_ALL_NOTIFICATIONS } from "@/axios/api";

class NotificationService {
  public list() {
    return Vue.axios.get(FIND_ALL_NOTIFICATIONS);
  }
}

export default new NotificationService();

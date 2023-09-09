import { Vue } from "vue-property-decorator";
import { FIND_ALL_CONVERSATIONS } from "@/axios/api";

class ConversationService {
  public list() {
    return Vue.axios.get(FIND_ALL_CONVERSATIONS);
  }
}

export default new ConversationService();

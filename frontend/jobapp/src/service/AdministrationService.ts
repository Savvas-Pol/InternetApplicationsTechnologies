import { Vue } from "vue-property-decorator";
import { DOWNLOAD_XML, DOWNLOAD_JSON } from "@/axios/api";

class AuthenticationService {
  public downloadXML() {
    return Vue.axios.get(DOWNLOAD_XML, { responseType: "blob" });
  }

  public downloadJSON() {
    return Vue.axios.get(DOWNLOAD_JSON, { responseType: "blob" });
  }
}

export default new AuthenticationService();

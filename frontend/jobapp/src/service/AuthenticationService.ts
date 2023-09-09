import store from "../store/index";
import { Vue } from "vue-property-decorator";
import { LOGIN_URL, REGISTRATION_URL, WHO_AM_I, LOGOUT_URL } from "@/axios/api";

class AuthenticationService {
  public register(registerData: Record<string, unknown>) {
    return Vue.axios.post(REGISTRATION_URL, registerData);
  }

  public getUserID(): number {
    console.log(store.getters.getCurrentUser);
    return store.getters.getCurrentUser.id;
  }

  public performLogout(): void {
    Vue.axios.post(LOGOUT_URL);
    store.commit("setIsAuthenticated", false);
    store.commit("clearCurrentUser");
  }

  public async getAuthenticatedUser() {
    try {
      const response = await Vue.axios.get(WHO_AM_I);

      if (response.status === 200) {
        const currentUser = response.data;
        store.commit("setCurrentUser", currentUser);
        store.commit("setIsAuthenticated", true);
      } else {
        this.performLogout();
      }
    } catch (error) {
      this.performLogout();
    }
  }

  public async login(email: string, password: string) {
    const logindata = {
      email: email,
      password: password,
    };
    return Vue.axios.post(LOGIN_URL, logindata);
  }
}

export default new AuthenticationService();

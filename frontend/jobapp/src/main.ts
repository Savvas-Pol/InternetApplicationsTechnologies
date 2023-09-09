import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueAxios from "vue-axios";

import "@/assets/css/custom-bs.css";
import "@/assets/css/jquery.fancybox.min.css";
import "@/assets/css/bootstrap-select.min.css";
import "@/assets/fonts/icomoon/style.css";
import "@/assets/fonts/line-icons/style.css";
// import "@/assets/css/owl.carousel.min.css";
import "@/assets/css/animate.min.css";
import "@/assets/css/style.css";
import httpResource from "@/http/httpResource";
import authenticationService from "@/service/AuthenticationService";

Vue.config.productionTip = false;

Vue.use(VueAxios, httpResource);

async function init() {
  authenticationService.getAuthenticatedUser().catch(() => {
    console.log("redirecting to login screen - user is not authenticated");
    router.push("/login");
  });

  router.beforeEach(async (to, from, next) => {
    if (to.path !== "/login" && !store.getters.getIsAuthenticated) {
      console.log("redirecting to login screen - session expired");
      next("/login");
    } else {
      next();
    }
  });

  new Vue({
    router,
    store,
    render: (h) => h(App),
  }).$mount("#site-wrap");
}

init();

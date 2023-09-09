import Vue from "vue";
import Vuex from "vuex";
// import createPersistedState from "vuex-persistedstate";

import authStore from "./modules/authStore";

Vue.use(Vuex);

// const dataState = createPersistedState({
//   paths: ["isAuthenticated"],
// });

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    authStore,
  },
});

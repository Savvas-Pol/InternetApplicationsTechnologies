const state = {
  isAuthenticated: localStorage.getItem("isAuthenticated") === "true",
  currentUser: {
    id: "",
    email: "",
    role: 0,
  },
  intervalName: "",
};

const getters = {
  getIsAuthenticated(state) {
    return state.isAuthenticated;
  },
  getCurrentUser(state) {
    return state.currentUser;
  },
  getIntervalName(state) {
    return state.intervalName;
  },
};

const mutations = {
  setIsAuthenticated(state, isAuthenticated) {
    state.isAuthenticated = isAuthenticated;
    localStorage.setItem("isAuthenticated", "true");
  },
  setCurrentUser(state, currentUser) {
    state.currentUser.id = currentUser.id;
    state.currentUser.email = currentUser.email;
    state.currentUser.role = currentUser.role.id;
  },
  setIntervalName(state, intervalName) {
    state.intervalName = intervalName;
  },
  clearCurrentUser(state) {
    state.currentUser.id = "";
    state.currentUser.email = "";
    localStorage.removeItem("isAuthenticated");
  },
};

const actions = {
  async login({ commit, dispatch }, loginData) {
    return true;
  },
  async getCurrentUser({ commit, dispatch }) {
    return true;
  },
  async refresh({ commit, dispatch, getters }) {
    return true;
  },
  async logout({ commit, dispatch, getters }) {
    return true;
  },
};

export default {
  namespaced: false,
  state,
  getters,
  mutations,
  actions,
};

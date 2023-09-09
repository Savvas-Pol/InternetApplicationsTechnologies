<template>
  <div class="col-lg-6">
    <h2 class="mb-4">Log In To JobApp</h2>
    <form @submit.prevent="login" action="#" class="p-4 border rounded">
      <div class="row form-group">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="email">Email</label>
          <input
            type="text"
            id="email"
            class="form-control"
            placeholder="Email address"
            v-model="loginData.email"
          />
        </div>
      </div>
      <div class="row form-group mb-4">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="pw">Password</label>
          <input
            type="password"
            id="pw"
            class="form-control"
            placeholder="Password"
            v-model="loginData.password"
          />
        </div>
      </div>

      <div class="row form-group">
        <div class="col-md-12">
          <input
            type="submit"
            value="Log In"
            class="btn px-4 btn-primary text-white"
          />
        </div>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import authenticationService from "@/service/AuthenticationService";

@Component
export default class Signin extends Vue {
  private loginData = {
    email: "",
    password: "",
  };

  private showModal = false;

  private async login() {
    authenticationService
      .login(this.loginData.email, this.loginData.password)
      .then(() => {
        authenticationService.getAuthenticatedUser().then(() => {
          this.$router.replace("/");
        });
      })
      .catch((response) => {
        alert(response.message);
      });
  }
}
</script>

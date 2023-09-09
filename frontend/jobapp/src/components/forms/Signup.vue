<template>
  <div class="col-lg-6 mb-5">
    <h2 class="mb-4">Sign Up To JobApp</h2>
    <form @submit.prevent="register" action="#" class="p-4 border rounded">
      <div class="row form-group">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="fname">First Name</label>
          <input
            type="text"
            v-model="registerData.firstname"
            id="fname"
            class="form-control"
            placeholder="First Name"
          />
        </div>
      </div>
      <div class="row form-group">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="fname">Last Name</label>
          <input
            type="text"
            v-model="registerData.lastname"
            id="fname"
            class="form-control"
            placeholder="Last Name"
          />
        </div>
      </div>
      <div class="row form-group">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="fname">Email</label>
          <input
            type="text"
            v-model="registerData.email"
            id="fname"
            class="form-control"
            placeholder="Email address"
          />
        </div>
      </div>
      <div class="row form-group">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="fname">Password</label>
          <input
            type="password"
            v-model="registerData.password"
            id="fname"
            class="form-control"
            placeholder="Password"
          />
        </div>
      </div>
      <div class="row form-group mb-4">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="fname">Re-Type Password</label>
          <input
            type="password"
            v-model="registerData.password"
            id="fname"
            class="form-control"
            placeholder="Re-type Password"
          />
        </div>
      </div>
      <div class="row form-group mb-4">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="fname">Phone</label>
          <input
            type="password"
            v-model="registerData.phone"
            id="fname"
            class="form-control"
            placeholder="Phone"
          />
        </div>
      </div>
      <div class="row form-group mb-4">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="fname">Photo</label>
          <input
            type="password"
            v-model="registerData.photo"
            id="fname"
            class="form-control"
            placeholder="Photo"
          />
        </div>
      </div>

      <div class="row form-group">
        <div class="col-md-12 mb-3 mb-md-0">
          <input
            v-model="registerData.scope_id_email"
            type="checkbox"
            id="scope_id_email"
          />
          <label class="text-black" for="scope_id_email"
            >&nbsp;&nbsp;&nbsp;Allow others to see your email</label
          >
        </div>
      </div>
      <div class="row form-group">
        <div class="col-md-12 mb-3 mb-md-0">
          <input
            v-model="registerData.scope_id_phone"
            type="checkbox"
            id="scope_id_email"
          />
          <label class="text-black" for="scope_id_email"
            >&nbsp;&nbsp;&nbsp;Allow others to see your phone</label
          >
        </div>
      </div>
      <div class="row form-group">
        <div class="col-md-12 mb-3 mb-md-0">
          <input
            v-model="registerData.scope_id_photo"
            type="checkbox"
            id="scope_id_email"
          />
          <label class="text-black" for="scope_id_email"
            >&nbsp;&nbsp;&nbsp;Allow others to see your photo</label
          >
        </div>
      </div>

      <div class="row form-group">
        <div class="col-md-12">
          <input
            type="submit"
            value="Sign Up"
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
export default class Signup extends Vue {
  private registerData = {
    firstname: "",
    lastname: "",
    email: "",
    password: "",
    phone: "",
    photo: "",
    role_id: 2,
    scope_id_email: 1,
    scope_id_phone: 1,
    scope_id_photo: 1,
  };

  private showModal = false;

  private register() {
    authenticationService
      .register(this.registerData)
      .then(() => {
        authenticationService
          .login(this.registerData.email, this.registerData.password)
          .then(() => {
            authenticationService.getAuthenticatedUser().then(() => {
              this.$router.replace("/");
            });
          })
          .catch((response) => {
            alert(response.message);
          });
      })
      .catch((response) => {
        alert("Invalid credentials. Please change them and try again");
      });
  }
}
</script>

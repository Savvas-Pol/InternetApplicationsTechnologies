<template>
  <div class="row">
    <div class="col-lg-6">
      <h2 class="mb-4">Change your email</h2>
      <form action="#" class="p-4 border rounded">
        <div class="row form-group">
          <div class="col-md-12 mb-3 mb-md-0">
            <label class="text-black" for="fname">Enter old email</label>
            <input
              type="text"
              id="fname"
              class="form-control"
              placeholder="Email"
              v-model="email_old"
            />
          </div>
        </div>
        <div class="row form-group">
          <div class="col-md-12 mb-3 mb-md-0">
            <label class="text-black" for="fname">Enter new email</label>
            <input
              type="text"
              id="fname"
              class="form-control"
              placeholder="Password"
              v-model="email_new"
            />
          </div>
        </div>
        <div class="row form-group">
          <div class="col-md-12 mb-3 mb-md-0">
            <label class="text-black" for="fname">Confirm new email</label>
            <input
              type="text"
              id="fname"
              class="form-control"
              placeholder="Confirm Password"
              v-model="email_confirm"
            />
          </div>
        </div>
      </form>

      <div class="row form-group">
        <div class="col-md-12">
          <input
            type="submit"
            value="Submit"
            class="btn px-4 btn-primary text-white"
            @click.prevent="changeEmail"
            :disabled="!enableChangeEmail"
          />
        </div>
      </div>
    </div>

    <div class="col-lg-6">
      <h2 class="mb-4">Change your password</h2>
      <form action="#" class="p-4 border rounded">
        <div class="row form-group">
          <div class="col-md-12 mb-3 mb-md-0">
            <label class="text-black" for="fname">Enter email</label>
            <input
              type="text"
              id="fname"
              class="form-control"
              placeholder="Email"
              v-model="email"
            />
          </div>
        </div>
        <div class="row form-group">
          <div class="col-md-12 mb-3 mb-md-0">
            <label class="text-black" for="fname">Enter old password</label>
            <input
              type="password"
              id="fname"
              class="form-control"
              placeholder="Email"
              v-model="password_old"
            />
          </div>
        </div>
        <div class="row form-group">
          <div class="col-md-12 mb-3 mb-md-0">
            <label class="text-black" for="fname">Enter new Password</label>
            <input
              type="password"
              id="fname"
              class="form-control"
              placeholder="Password"
              v-model="password_new"
            />
          </div>
        </div>
        <div class="row form-group">
          <div class="col-md-12 mb-3 mb-md-0">
            <label class="text-black" for="fname">Confirm new Password</label>
            <input
              type="password"
              id="fname"
              class="form-control"
              placeholder="Confirm Password"
              v-model="password_confirm"
            />
          </div>
        </div>
      </form>

      <div class="row form-group">
        <div class="col-md-12">
          <input
            type="submit"
            value="Submit"
            class="btn px-4 btn-primary text-white"
            @click.prevent="changePassword"
            :disabled="!enableChangePassword"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import userService from "@/service/UserService";

@Component
export default class SettingsForm extends Vue {
  private email_old = "";
  private email_new = "";
  private email_confirm = "";

  private email = "";
  private password_old = "";
  private password_new = "";
  private password_confirm = "";

  private get enableChangeEmail() {
    return (
      this.email_new == this.email_confirm &&
      this.email_new !== "" &&
      this.email_old !== ""
    );
  }

  private get enableChangePassword() {
    return (
      this.password_new == this.password_confirm &&
      this.password_new !== "" &&
      this.password_old !== "" &&
      this.email != ""
    );
  }

  private changePassword() {
    userService
      .changePassword(this.email, this.password_old, this.password_new)
      .then(() => {
        alert("Update successful");
      })
      .catch(() => {
        alert("Invalid credentials. Please change them and try again");
      });

    console.log("Change password");
  }

  private changeEmail() {
    userService
      .changeEmail(this.email_old, this.email_new)
      .then(() => {
        alert("Update successful");
      })
      .catch(() => {
        alert("Invalid credentials. Please change them and try again");
      });
    console.log("Change email");
  }
}
</script>

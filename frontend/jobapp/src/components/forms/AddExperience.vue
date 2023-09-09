<template>
  <div class="col-lg-6">
    <h2 class="mb-4">Add Experience</h2>
    <form action="#" class="p-4 border rounded">
      <div class="row form-group">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="title">Title</label>
          <input
            type="text"
            id="title"
            class="form-control"
            placeholder="Title"
            v-model="formData.title"
          />
        </div>
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="title">Date From</label>
          <input
            type="text"
            id="date_from"
            class="form-control"
            placeholder="Date From"
            v-model="formData.date_from"
          />
        </div>
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="title">Date To</label>
          <input
            type="text"
            id="to"
            class="form-control"
            placeholder="Date To"
            v-model="formData.date_to"
          />
        </div>
      </div>
      <div class="row form-group mb-4">
        <div class="col-md-12 mb-3 mb-md-0">
          <label class="text-black" for="scope">Scope</label>
          <br />
          <select id="scope" v-model="formData.scope_id">
            <option value="1">Public</option>
            <option value="2">Private</option>
          </select>
        </div>
      </div>

      <div class="row form-group">
        <div class="col-md-12">
          <input
            type="submit"
            value="Add"
            class="btn px-4 btn-primary text-white"
            @click.prevent="addExperience"
          />
        </div>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import userService from "@/service/UserService";
import authenticationService from "@/service/AuthenticationService";

@Component
export default class AddEducation extends Vue {
  private formData = {
    title: "",
    date_from: "",
    date_to: "",
    scope_id: 1,
    user_id: authenticationService.getUserID()
  };

  private showModal = false;

  private addExperience() {
    userService
      .addExperience(this.formData)
      .then((response) => {
        this.showModal = true;
      })
      .catch((response) => {
        alert("Experience creation failed");
      });
  }
}
</script>

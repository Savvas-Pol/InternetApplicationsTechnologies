<template>
  <div>
    <!-- ADMIN -->
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />
    <section class="site-section">
      <div class="container">
        <div class="row">
          <user-info
            :user="user"
            alignment="left"
            :details="true"
            :showHiddenFields="true"
          ></user-info>
        </div>
        <div class="row">
          <add-experience />
          <add-education />
          <add-skill />
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import UserInfo from "@/components/areas/UserInfo.vue";
import authenticationService from "@/service/AuthenticationService";
import userService from "@/service/UserService";
import AddEducation from "@/components/forms/AddEducation.vue";
import addExperience from "@/components/forms/AddExperience.vue";
import addSkill from "@/components/forms/AddSkill.vue";

@Component({
  components: {
    TitleSection,
    UserInfo,
    AddEducation,
    addExperience,
    addSkill,
  },
})
export default class Profile extends Vue {
  private breadcrumbList = ["Personal Info"];
  private componentCaption = "My profile";
  private userName = "Chintan Patel";
  private userJob = "Creative Director";
  public user = {};

  private myID = authenticationService.getUserID();

  mounted(): void {
    userService
      .getUserDetails(this.myID)
      .then((response) => {
        this.user = response.data;
        console.log(this.user);
      })
      .catch(() => {
        alert("Network error");
      });
  }
}
</script>

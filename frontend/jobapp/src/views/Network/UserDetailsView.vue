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
            :showHiddenFields="false"
          ></user-info>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import UserInfo from "@/components/areas/UserInfo.vue";
import userService from "@/service/UserService";

@Component({
  components: {
    TitleSection,
    UserInfo,
  },
})
export default class UserDetailsView extends Vue {
  private breadcrumbList = ["Personal Info"];
  private componentCaption = "User Profile";
  private userName = "Chintan Patel";
  private userJob = "Creative Director";
  public user = {};

  private id = parseInt(this.$route.params.id);

  mounted(): void {
    userService
      .getUserDetails(this.id)
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

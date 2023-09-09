<template>
  <div>
    <!-- ADMIN -->
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />
    <section class="site-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-12 text-center" data-aos="fade">
            <span v-for="i in pages" :key="i">
              <a href="#" @click.prevent="changePage(i)">{{ i }} </a>
            </span>
          </div>
        </div>

        <div
          class="row"
          v-for="(object, idx) in getCurrentPage"
          :key="object.id"
        >
          <user-info
            :user="object"
            :alignment="idx % 2 == 0 ? 'left' : 'right'"
            :details="false"
            :showHiddenFields="true"
          ></user-info>
        </div>
        <div class="col-12 text-center" data-aos="fade">
          <span v-for="i in pages" :key="i">
            <a href="#" @click.prevent="changePage(i)">{{ i }} </a>
          </span>
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
import authenticationService from "@/service/AuthenticationService";

@Component({
  components: {
    TitleSection,
    UserInfo,
  },
})
export default class NetworkView extends Vue {
  private breadcrumbList = ["List all Friends"];
  private componentCaption = "Friends";
  private userName = "Chintan Patel";
  private userJob = "Creative Director";
  private currentPage = 1;
  private pageSize = 2;
  private pages = 1;
  public users = [];

  private id = authenticationService.getUserID();

  private get getCurrentPage() {
    if (this.users.length === 0) {
      return this.users;
    }
    return this.users.slice(
      (this.currentPage - 1) * this.pageSize,
      (this.currentPage - 1) * this.pageSize + this.pageSize
    );
  }

  private changePage(i: number) {
    this.currentPage = i;
    console.log("page changed to " + i);
  }

  mounted(): void {
    console.log("ListFriends mounted");
    userService
      .listFriends(this.id)
      .then((response) => {
        this.users = response.data;
        this.currentPage = 1;

        this.pages = Math.ceil(Math.max(this.users.length / this.pageSize, 1));

        console.log(this.users);
      })
      .catch((ex) => {
        console.log(ex);
        alert("Network error");
      });
  }
}
</script>

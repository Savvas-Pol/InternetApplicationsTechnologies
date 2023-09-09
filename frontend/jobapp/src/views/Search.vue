<template>
  <div>
    <!-- ADMIN -->
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />
    <section class="site-section">
      <div class="container">
        <form @submit.prevent="refresh" action="#" class="p-4 border rounded">
          <div class="row form-group">
            <div class="col-md-12 mb-3 mb-md-0">
              <label class="text-black" for="email">Search for:</label>
              <input
                type="text"
                id="email"
                class="form-control"
                placeholder="Firstname or lastname"
                v-model="key"
              />
            </div>
          </div>
          <div class="row form-group">
            <div class="col-md-12">
              <input
                type="submit"
                value="Search"
                class="btn px-4 btn-primary text-white"
              />
            </div>
          </div>
        </form>

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
export default class SearchView extends Vue {
  private breadcrumbList = ["Search"];
  private componentCaption = "Meet new friends";
  private userName = "Chintan Patel";
  private userJob = "Creative Director";
  private currentPage = 1;
  private pageSize = 2;
  private pages = 1;
  public users = [];
  private key = "";

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

  private refresh(): void {
    /* eslint-disable @typescript-eslint/no-explicit-any*/
    userService
      .list()
      .then((response) => {
        this.users = response.data.filter(
          (u: Record<string, any>) =>
            u.firstname.toLowerCase().includes(this.key.toLowerCase()) ||
            u.lastname.toLowerCase().includes(this.key.toLowerCase())
        );
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

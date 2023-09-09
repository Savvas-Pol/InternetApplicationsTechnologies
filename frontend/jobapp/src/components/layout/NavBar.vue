<template>
  <!-- NAVBAR -->
  <header class="site-navbar mt-3">
    <div class="container-fluid">
      <div class="row align-items-center">
        <div class="site-logo col-6">
          <router-link class="nav-link active" to="/">JobApp</router-link>
        </div>

        <nav class="mx-auto site-navigation">
          <ul class="site-menu js-clone-nav d-none d-xl-block ml-0 pl-0">
            <li v-if="isLoggedIn && isAdmin" class="has-children">
              <a href="#">Admin</a>
              <ul class="dropdown">
                <li>
                  <router-link class="nav-link" to="/admin/users"
                    >Manage users</router-link
                  >
                </li>
                <li>
                  <router-link class="nav-link" to="/admin/data"
                    >Export data</router-link
                  >
                </li>
              </ul>
            </li>
            <li>
              <router-link class="nav-link active" to="/">Home</router-link>
            </li>
            <li v-if="isLoggedIn" class="has-children">
              <a href="#">Network</a>
              <ul class="dropdown">
                <li>
                  <router-link class="nav-link" to="/network"
                    >My friends</router-link
                  >
                </li>
                <li>
                  <router-link class="nav-link" to="/search"
                    >Search friends</router-link
                  >
                </li>
              </ul>
            </li>

            <li v-if="isLoggedIn">
              <router-link class="nav-link active" to="/ads">Ads</router-link>
            </li>
            <li v-if="isLoggedIn">
              <router-link class="nav-link active" to="/timeline"
                >Timeline</router-link
              >
            </li>
            <li v-if="isLoggedIn">
              <router-link class="nav-link active" to="/profile"
                >Profile</router-link
              >
            </li>
            <li v-if="isLoggedIn">
              <router-link class="nav-link active" to="/conversations"
                >Conversations</router-link
              >
            </li>
            <li v-if="isLoggedIn">
              <router-link class="nav-link active" to="/notifications"
                >Notifications</router-link
              >
            </li>
            <li v-if="isLoggedIn">
              <router-link class="nav-link active" to="/settings"
                >Settings</router-link
              >
            </li>
            <!-- <li class="has-children">
              <a href="services.html">Pages</a>
              <ul class="dropdown">
                <li><a href="services.html">Services</a></li>
                <li><a href="service-single.html">Service Single</a></li>
                <li><a href="blog-single.html">Blog Single</a></li>
                <li><a href="portfolio.html">Portfolio</a></li>
                <li>
                  <a href="portfolio-single.html">Portfolio Single</a>
                </li>
                <li><a href="testimonials.html">Testimonials</a></li>
                <li><a href="faq.html">Frequently Ask Questions</a></li>
                <li><a href="gallery.html">Gallery</a></li>
              </ul>
            </li> -->
            <li class="d-lg-none">
              <router-link class="nav-link" to="/compose/article"
                ><span class="mr-2 icon-add"></span>Create Article</router-link
              >
              <!-- <a href="post-job.html"
                ><span class="mr-2">+</span>Create Article</a
              > -->
            </li>
            <li class="d-lg-none"><a href="login.html">Log In</a></li>
          </ul>
        </nav>

        <div class="right-cta-menu text-right d-flex aligin-items-center col-6">
          <div class="ml-auto">
            <router-link
              class="
                btn btn-outline-white
                border-width-2
                d-none d-lg-inline-block
              "
              to="/compose/article"
              ><span class="mr-2 icon-add"></span>Create Article</router-link
            >
            <div
              class="btn btn-primary border-width-2 d-none d-lg-inline-block"
              @click="buttonAction"
            >
              <span class="mr-2 icon-lock_outline"></span>{{ sessionButton }}
            </div>
          </div>
          <a
            href="#"
            class="
              site-menu-toggle
              js-menu-toggle
              d-inline-block d-xl-none
              mt-lg-2
              ml-3
            "
            ><span class="icon-menu h3 m-0 p-0 mt-2"></span
          ></a>
        </div>
      </div>
    </div>
  </header>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import authenticationService from "@/service/AuthenticationService";

@Component
export default class NavBar extends Vue {
  private get sessionButton() {
    if (this.$store.getters.getIsAuthenticated) {
      return "Logout";
    } else {
      return "Login/Signup";
    }
  }
  private get getCurrentUser(): Record<string, unknown> {
    return this.$store.getters.getCurrentUser;
  }

  private get isLoggedIn(): boolean {
    console.log("return: " + this.$store.getters.getIsAuthenticated);
    console.log(localStorage.getItem("isAuthenticated") === "true");
    console.log(localStorage.getItem("isAuthenticated"));
    return this.$store.getters.getIsAuthenticated;
  }

  private get isAdmin(): boolean {
    return this.$store.getters.getCurrentUser.role === 1;
  }

  private buttonAction() {
    if (this.$store.getters.getIsAuthenticated) {
      this.logout();
    } else {
      this.$router.push("/login");
    }
  }

  private logout() {
    authenticationService.performLogout();
    this.$router.push("/login");
  }
}
</script>

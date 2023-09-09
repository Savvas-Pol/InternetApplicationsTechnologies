<template>
  <section class="site-section">
    <div class="container">
      <div class="row align-items-center block__69944">
        <div class="col-md-6">
          <img :src="user.photo" alt="Image" class="img-fluid mb-4 rounded" />
        </div>
        <div class="col-md-6">
          <h3>{{ user.firstname }} {{ user.lastname }}</h3>
          <p v-if="user.role" class="text-emphasis">
            {{ user.role.description }}
          </p>

          <p>Email: {{ user.email }}</p>
          <p>Phone: {{ user.phone }}</p>

          <p>details of user</p>
          <div class="social mt-4">
            <a href="#"><span class="icon-facebook"></span></a>
            <a href="#"><span class="icon-twitter"></span></a>
            <a href="#"><span class="icon-instagram"></span></a>
            <a href="#"><span class="icon-linkedin"></span></a>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
@Component
export default class UserDetails extends Vue {
  @Prop() private user!: Record<string, unknown>;
}
</script>

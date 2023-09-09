<template>
  <section
    class="section-hero overlay inner-page bg-image"
    style="background-image: url('images/hero_1.jpg')"
    id="home-section"
  >
    <div class="container">
      <div class="row">
        <div class="col-md-7">
          <h1 class="text-white font-weight-bold">{{ caption }}</h1>
          <div class="custom-breadcrumbs">
            <router-link to="/">Home</router-link>
            <span class="mx-2 slash">/</span>
            <span
              v-for="(value, i) in breadcrumbList"
              v-bind:key="i"
              class="text-white"
            >
              <strong>{{ value }}</strong>
              <span class="mx-2 slash">/</span>
            </span>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
@Component
export default class TitleSection extends Vue {
  @Prop() readonly caption!: string;
  @Prop() readonly breadcrumbList!: [];
}
</script>

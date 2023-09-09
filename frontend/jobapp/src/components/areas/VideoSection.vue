<template>
  <section class="site-section pb-0">
    <div class="container">
      <div class="row align-items-center">
        <div
          class="col-lg-6 mb-5 mb-lg-0"
          v-bind:class="{ 'order-md-2': displayRight }"
        >
          <a
            data-fancybox
            data-ratio="2"
            href="https://vimeo.com/317571768"
            class="block__96788"
          >
            <span class="play-icon"><span class="icon-play"></span></span>
            <img
              src="images/sq_img_6.jpg"
              alt="Image"
              class="img-fluid img-shadow"
            />
          </a>
        </div>
        <div
          class="col-lg-5 ml-auto"
          v-bind:class="{
            'order-md-1': displayRight,
            'mb-5': displayRight,
            'mb-lg-0': displayRight,
          }"
        >
          <h2 class="section-title mb-3">
            {{ title }}
          </h2>
          <p class="lead">
            <slot name="lead"></slot>
          </p>
          <p>
            <slot name="description"></slot>
          </p>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
@Component
export default class VideoSection extends Vue {
  @Prop() private title!: string;
  @Prop() private alignment!: string;

  private get displayRight(): boolean {
    return this.alignment === "right";
  }
}
</script>

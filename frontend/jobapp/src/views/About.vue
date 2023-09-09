<template>
  <div>
    <!-- HOME -->

    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />

    <stats-section a="100" b="200" c="300" d="400" />

    <video-section :title="componentCaption1" alignment="left">
      <template v-slot:lead>
        <p>
          Eveniet voluptatibus voluptates suscipit minima, cum voluptatum ut
          dolor, sed facere corporis qui, ea quisquam quis odit minus nulla
          vitae. Sit, voluptatem.
        </p>
      </template>

      <template v-slot:description>
        <p>
          Ipsum harum assumenda in eum vel eveniet numquam, cumque vero vitae
          enim cupiditate deserunt eligendi officia modi consectetur. Expedita
          tempora quos nobis earum hic ex asperiores quisquam optio nostrum sit!
        </p>
      </template>
    </video-section>

    <video-section :title="componentCaption2" alignment="right">
      <template v-slot:lead>
        <p>
          Eveniet voluptatibus voluptates suscipit minima, cum voluptatum ut
          dolor, sed facere corporis qui, ea quisquam quis odit minus nulla
          vitae. Sit, voluptatem.
        </p>
      </template>

      <template v-slot:description>
        <p>
          Ipsum harum assumenda in eum vel eveniet numquam, cumque vero vitae
          enim cupiditate deserunt eligendi officia modi consectetur. Expedita
          tempora quos nobis earum hic ex asperiores quisquam optio nostrum sit!
        </p>
      </template>
    </video-section>

    <section class="site-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-12 text-center" data-aos="fade">
            <h2 class="section-title mb-3">Our Team</h2>
          </div>
        </div>
      </div>
    </section>

    <user-info :title="userName2" :job="userJob" alignment="left">
      <template v-slot:description>
        <p>
          Ipsum harum assumenda in eum vel eveniet numquam, cumque vero vitae
          enim cupiditate deserunt eligendi officia modi consectetur. Expedita
          tempora quos nobis earum hic ex asperiores quisquam optio nostrum sit!
        </p>
      </template>
    </user-info>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import StatsSection from "@/components/areas/StatsSection.vue";
import VideoSection from "@/components/areas/VideoSection.vue";
import UserInfo from "@/components/areas/UserInfo.vue";

@Component({
  components: {
    TitleSection,
    StatsSection,
    VideoSection,
    UserInfo,
  },
})
export default class AboutComponent extends Vue {
  private breadcrumbList = ["About"];
  private componentCaption = "About";
  private componentCaption1 = "About us";
  private componentCaption2 = "The Company";
  private userName1 = "Elisabeth Smith";
  private userName2 = "Chintan Patel";
  private userJob = "Creative Director";
}
</script>

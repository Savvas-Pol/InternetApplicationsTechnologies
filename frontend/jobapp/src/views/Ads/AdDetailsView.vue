<template>
  <div>
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />
    <ad-details :ad="ad" />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import AdDetails from "@/components/areas/AdDetails.vue";
import adService from "@/service/AdService";

@Component({
  components: {
    TitleSection,
    AdDetails,
  },
})
export default class AdDetailsComponent extends Vue {
  private breadcrumbList = ["Ads"];
  private componentCaption = "Ads";

  public ad = {};
  private id = parseInt(this.$route.params.id);

  mounted(): void {
    adService
      .getAdDetails(this.id)
      .then((response) => {
        this.ad = response.data;
        console.log(this.ad);
      })
      .catch(() => {
        alert("Network error");
      });
  }
}
</script>

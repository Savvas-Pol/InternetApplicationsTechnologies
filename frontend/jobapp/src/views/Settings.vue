<template>
  <div>
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />

    <section class="site-section">
      <div class="container">
        <settings-form />
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import SettingsForm from "@/components/forms/SettingsForm.vue";

@Component({
  components: {
    TitleSection,
    SettingsForm,
  },
})
export default class Signup extends Vue {
  private breadcrumbList = ["Settings"];
  private componentCaption = "Settings";
}
</script>

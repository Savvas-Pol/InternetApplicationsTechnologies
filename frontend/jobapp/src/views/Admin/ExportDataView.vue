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
          <export-section />
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import ExportSection from "@/components/forms/ExportData.vue";

@Component({
  components: {
    TitleSection,
    ExportSection,
  },
})
export default class AdminComponent extends Vue {
  private breadcrumbList = ["Export data"];
  private componentCaption = "Admin";
}
// private listUsers() {
//   ListUsersService.listUsers();
// }
</script>

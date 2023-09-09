<template>
  <div>
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />

    <section class="site-section">
      <div class="container">
        <article-create />
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import ArticleCreate from "@/components/forms/ArticleCreate.vue";
@Component({
  components: {
    TitleSection,
    ArticleCreate,
  },
})
export default class CreateArticleView extends Vue {
  private breadcrumbList = ["Articles", "Compose"];
  private componentCaption = "Create Article";
}
</script>

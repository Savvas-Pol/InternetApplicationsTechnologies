<template>
  <div>
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />
    <article-details :article="article" />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import ArticleDetails from "@/components/areas/ArticleDetails.vue";
import articleService from "@/service/ArticleService";

@Component({
  components: {
    TitleSection,
    ArticleDetails,
  },
})
export default class ArticleDetailsComponent extends Vue {
  private breadcrumbList = ["Article"];
  private componentCaption = "Articles";

  public article = {};
  private id = parseInt(this.$route.params.id);

  mounted(): void {
    articleService
      .getArticleDetails(this.id)
      .then((response) => {
        this.article = response.data;
        console.log(this.article);
      })
      .catch(() => {
        alert("Network error");
      });
  }
}
</script>

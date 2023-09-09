<template>
  <div class="col-md-6 col-lg-4 mb-5">
    <router-link
      class="nav-link active"
      :to="{ name: 'ArticleDetailsView', params: { id: article.id } }"
      ><img
        src="images/sq_img_1.jpg"
        alt="Image"
        class="img-fluid rounded mb-4"
      />

      <h3>
        <a class="text-black">{{ article.title }}</a>
      </h3>
      <div>
        {{ article.authored_at }} <span class="mx-2">|</span>
        <a>{{ article.comment.length }} Comments</a>
        <span class="mx-2">|</span>
        <a>{{ article.users_liked.length }} Likes</a>
      </div>
      <div class="text-black">
        By {{ article.user_id.firstname }} {{ article.user_id.lastname }}
      </div>
    </router-link>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
@Component
export default class ArticlesSection extends Vue {
  @Prop() private article!: Record<string, unknown>;
}
</script>

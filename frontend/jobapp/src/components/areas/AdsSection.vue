<template>
  <div class="col-md-6 col-lg-4 mb-5">
    <router-link
      class="nav-link active"
      :to="{ name: 'AdDetailsView', params: { id: ad.id } }"
      ><img
        src="images/sq_img_1.jpg"
        alt="Image"
        class="img-fluid rounded mb-4"
      />

      <h3>
        <a class="text-black">{{ ad.title }}</a>
      </h3>
      <div>{{ ad.authored_at }} <span class="mx-2">|</span></div>
      <div class="text-black">
        By {{ ad.user_id.firstname }} {{ ad.user_id.lastname }}
      </div>
    </router-link>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
@Component
export default class AdsSection extends Vue {
  @Prop() private ad!: Record<string, unknown>;
}
</script>

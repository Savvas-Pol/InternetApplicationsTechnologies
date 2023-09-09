<template>
  <section class="site-section" id="next-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 blog-content">
          <h3 class="mb-4">
            <h1>{{ ad.title }}</h1>
          </h3>
          <p class="lead">
            {{ ad.details }}
          </p>
          <div>Authored at : {{ ad.authored_at }}</div>

          <div class="pt-5">
            <h5>Applications</h5>
            <ul class="comment-list">
              <li class="comment" v-for="c in ad.application" v-bind:key="c.id">
                <div class="vcard bio">
                  <img :src="c.user_id.photo" alt="Image placeholder" />
                </div>
                <div class="comment-body">
                  <h3>
                    {{ c.user_id.firstname }}
                    {{ c.user_id.lastname }}
                  </h3>
                  <div class="meta">{{ c.application_date }}</div>
                </div>
              </li>
            </ul>

            <div
              v-if="ad.user_id !== createApplication.user_id"
              class="comment-form-wrap pt-5"
            >
              <h3 class="mb-5">Apply</h3>
              <form action="#" class="">
                <div class="form-group">
                  <input
                    @click.prevent="applyToAd"
                    type="submit"
                    value="Apply"
                    class="btn btn-primary btn-md"
                  />
                </div>
              </form>
            </div>
          </div>
        </div>
        <div class="col-lg-4 sidebar pl-lg-5">
          <div class="sidebar-box" v-if="ad.user_id">
            <img
              :src="ad.user_id.photo"
              alt="Image placeholder"
              class="img-fluid mb-4 w-50 rounded-circle"
            />
            <h3 v-if="ad.user_id">
              About {{ ad.user_id.firstname }}
              {{ ad.user_id.lastname }}
            </h3>
            <p v-if="ad.user_id">
              {{ ad.user_id.description }}
            </p>
            <p><a href="#" class="btn btn-primary btn-sm">Read More</a></p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import adService from "@/service/AdService";
import authenticationService from "@/service/AuthenticationService";

@Component
export default class AdDetails extends Vue {
  @Prop() private ad!: Record<string, unknown>;

  private createApplication = {
    ad_id: this.ad.id,
    user_id: authenticationService.getUserID(),
  };

  private applyToAd() {
    this.createApplication.user_id = authenticationService.getUserID();
    this.createApplication.ad_id = this.ad.id;

    adService
      .applyToAd(this.createApplication)
      .then((res) => {
        let data = res.data;
        console.log("insert done");

        (this.ad.application as Array<unknown>).push(data);
      })
      .catch(() => {
        alert("Submit failed. Please try again later");
      });
  }
}
</script>

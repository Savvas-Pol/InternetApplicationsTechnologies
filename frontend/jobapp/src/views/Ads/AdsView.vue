<template>
  <div>
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />
    <section class="site-section">
      <div class="row mb-5">
        <div class="col-12 text-center" data-aos="fade">
          <h2 class="section-title mb-3">
            My Ads - {{ currentPage1 }} of {{ pages1 }}
          </h2>
          <span v-for="i in pages1" :key="i">
            <a href="#" @click.prevent="changePage1(i)">{{ i }} </a>
          </span>
        </div>
      </div>

      <div class="container">
        <div class="row mb-5">
          <ads-section
            v-for="object in getCurrentPage1"
            :key="object.id"
            :ad="object"
          ></ads-section>
        </div>
        <!-- <div class="col-12 text-center" data-aos="fade">
          <span v-for="i in pages" :key="i">
            <a href="#" @click.prevent="changePage(i)">{{ i }} </a>
          </span>
        </div> -->
      </div>
    </section>
    <section class="site-section">
      <div class="row mb-5">
        <div class="col-12 text-center" data-aos="fade">
          <h2 class="section-title mb-3">
            Friends' Ads - {{ currentPage2 }} of {{ pages2 }}
          </h2>
          <span v-for="i in pages2" :key="i">
            <a href="#" @click.prevent="changePage2(i)">{{ i }} </a>
          </span>
        </div>
      </div>

      <div class="container">
        <div class="row mb-5">
          <ads-section
            v-for="object in getCurrentPage2"
            :key="object.id"
            :ad="object"
          ></ads-section>
        </div>
        <!-- <div class="col-12 text-center" data-aos="fade">
          <span v-for="i in pages" :key="i">
            <a href="#" @click.prevent="changePage(i)">{{ i }} </a>
          </span>
        </div> -->
      </div>
    </section>
    <section class="site-section">
      <div class="row mb-5">
        <div class="col-12 text-center" data-aos="fade">
          <h2 class="section-title mb-3">
            Applied Ads - {{ currentPage3 }} of {{ pages3 }}
          </h2>
          <span v-for="i in pages3" :key="i">
            <a href="#" @click.prevent="changePage3(i)">{{ i }} </a>
          </span>
        </div>
      </div>

      <div class="container">
        <div class="row mb-5">
          <ads-section
            v-for="object in getCurrentPage3"
            :key="object.id"
            :ad="object"
          ></ads-section>
        </div>
        <!-- <div class="col-12 text-center" data-aos="fade">
          <span v-for="i in pages" :key="i">
            <a href="#" @click.prevent="changePage(i)">{{ i }} </a>
          </span>
        </div> -->
      </div>
    </section>
    <section class="site-section">
      <div class="row mb-5">
        <div class="col-12 text-center" data-aos="fade">
          <h2 class="section-title mb-3">
            Recommended Ads - {{ currentPage4 }} of {{ pages4 }}
          </h2>
          <span v-for="i in pages4" :key="i">
            <a href="#" @click.prevent="changePage4(i)">{{ i }} </a>
          </span>
        </div>
      </div>

      <div class="container">
        <div class="row mb-5">
          <ads-section
            v-for="object in getCurrentPage4"
            :key="object.id"
            :ad="object"
          ></ads-section>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import AdsSection from "@/components/areas/AdsSection.vue";
import adService from "@/service/AdService";
import authenticationService from "@/service/AuthenticationService";

@Component({
  components: {
    TitleSection,
    AdsSection,
  },
})
export default class AdsView extends Vue {
  private breadcrumbList = ["Ads"];
  private componentCaption = "Ads";
  private pageSize = 3;

  private currentPage1 = 1;
  private pages1 = 1;
  public ads1 = [];

  private currentPage2 = 1;
  private pages2 = 1;
  public ads2 = [];

  private currentPage3 = 1;
  private pages3 = 1;
  public ads3 = [];

  private currentPage4 = 1;
  private pages4 = 1;
  public ads4 = [];

  private get getCurrentPage1() {
    if (this.ads1.length === 0) {
      return this.ads1;
    }
    return this.ads1.slice(
      (this.currentPage1 - 1) * this.pageSize,
      (this.currentPage1 - 1) * this.pageSize + this.pageSize
    );
  }

  private get getCurrentPage2() {
    if (this.ads2.length === 0) {
      return this.ads2;
    }
    return this.ads2.slice(
      (this.currentPage2 - 1) * this.pageSize,
      (this.currentPage2 - 1) * this.pageSize + this.pageSize
    );
  }

  private get getCurrentPage3() {
    if (this.ads3.length === 0) {
      return this.ads3;
    }
    return this.ads3.slice(
      (this.currentPage3 - 1) * this.pageSize,
      (this.currentPage3 - 1) * this.pageSize + this.pageSize
    );
  }

  private get getCurrentPage4() {
    if (this.ads4.length === 0) {
      return this.ads4;
    }
    return this.ads4.slice(
      (this.currentPage4 - 1) * this.pageSize,
      (this.currentPage4 - 1) * this.pageSize + this.pageSize
    );
  }

  private changePage1(i: number) {
    this.currentPage1 = i;
    console.log("page#1 changed to " + i);
  }

  private changePage2(i: number) {
    this.currentPage2 = i;
    console.log("page#2 changed to " + i);
  }

  private changePage3(i: number) {
    this.currentPage3 = i;
    console.log("page#3 changed to " + i);
  }

  private changePage4(i: number) {
    this.currentPage4 = i;
    console.log("page#4 changed to " + i);
  }

  mounted(): void {
    console.log("AdsView mounted");

    let myID = authenticationService.getUserID();

    console.log("id: " + myID);

    adService
      .getMyAds(myID)
      .then((response) => {
        this.ads1 = response.data;
        this.currentPage1 = 1;

        this.pages1 = Math.ceil(Math.max(this.ads1.length / this.pageSize, 1));

        console.log(this.ads1);

        adService
          .getFriendsAds(myID)
          .then((response) => {
            this.ads2 = response.data;
            this.currentPage2 = 1;

            this.pages2 = Math.ceil(
              Math.max(this.ads2.length / this.pageSize, 1)
            );

            console.log(this.ads2);

            adService
              .getAppliedAds(myID)
              .then((response) => {
                this.ads3 = response.data;
                this.currentPage3 = 1;

                this.pages3 = Math.ceil(
                  Math.max(this.ads3.length / this.pageSize, 1)
                );

                console.log(this.ads3);

                adService
                  .getRecommendedAds(myID)
                  .then((response) => {
                    this.ads4 = response.data;
                    this.currentPage4 = 1;

                    this.pages4 = Math.ceil(
                      Math.max(this.ads4.length / this.pageSize, 1)
                    );

                    console.log(this.ads4);
                  })
                  .catch((ex) => {
                    console.log(ex);
                    alert("Network error");
                  });
              })
              .catch((ex) => {
                console.log(ex);
                alert("Network error");
              });
          })
          .catch((ex) => {
            console.log(ex);
            alert("Network error");
          });
      })
      .catch((ex) => {
        console.log(ex);
        alert("Network error");
      });
  }
}
</script>

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
            My Articles - {{ currentPage1 }} of {{ pages1 }}
          </h2>
          <span v-for="i in pages1" :key="i">
            <a href="#" @click.prevent="changePage1(i)">{{ i }} </a>
          </span>
        </div>
      </div>

      <div class="container">
        <div class="row mb-5">
          <articles-section
            v-for="object in getCurrentPage1"
            :key="object.id"
            :article="object"
          ></articles-section>
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
            Friends' Articles - {{ currentPage2 }} of {{ pages2 }}
          </h2>
          <span v-for="i in pages2" :key="i">
            <a href="#" @click.prevent="changePage2(i)">{{ i }} </a>
          </span>
        </div>
      </div>

      <div class="container">
        <div class="row mb-5">
          <articles-section
            v-for="object in getCurrentPage2"
            :key="object.id"
            :article="object"
          ></articles-section>
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
            Friend's liked Articles - {{ currentPage3 }} of {{ pages3 }}
          </h2>
          <span v-for="i in pages3" :key="i">
            <a href="#" @click.prevent="changePage3(i)">{{ i }} </a>
          </span>
        </div>
      </div>

      <div class="container">
        <div class="row mb-5">
          <articles-section
            v-for="object in getCurrentPage3"
            :key="object.id"
            :article="object"
          ></articles-section>
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
            Recommended Articles - {{ currentPage4 }} of {{ pages4 }}
          </h2>
          <span v-for="i in pages4" :key="i">
            <a href="#" @click.prevent="changePage4(i)">{{ i }} </a>
          </span>
        </div>
      </div>

      <div class="container">
        <div class="row mb-5">
          <articles-section
            v-for="object in getCurrentPage4"
            :key="object.id"
            :article="object"
          ></articles-section>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import ArticlesSection from "@/components/areas/ArticlesSection.vue";
import articleService from "@/service/ArticleService";
import authenticationService from "@/service/AuthenticationService";

@Component({
  components: {
    TitleSection,
    ArticlesSection,
  },
})
export default class ArticlesView extends Vue {
  private breadcrumbList = ["Articles"];
  private componentCaption = "Articles";
  private pageSize = 3;

  private currentPage1 = 1;
  private pages1 = 1;
  public articles1 = [];

  private currentPage2 = 1;
  private pages2 = 1;
  public articles2 = [];

  private currentPage3 = 1;
  private pages3 = 1;
  public articles3 = [];

  private currentPage4 = 1;
  private pages4 = 1;
  public articles4 = [];

  private get getCurrentPage1() {
    if (this.articles1.length === 0) {
      return this.articles1;
    }
    return this.articles1.slice(
      (this.currentPage1 - 1) * this.pageSize,
      (this.currentPage1 - 1) * this.pageSize + this.pageSize
    );
  }

  private get getCurrentPage2() {
    if (this.articles2.length === 0) {
      return this.articles2;
    }
    return this.articles2.slice(
      (this.currentPage2 - 1) * this.pageSize,
      (this.currentPage2 - 1) * this.pageSize + this.pageSize
    );
  }

  private get getCurrentPage3() {
    if (this.articles3.length === 0) {
      return this.articles3;
    }
    return this.articles3.slice(
      (this.currentPage3 - 1) * this.pageSize,
      (this.currentPage3 - 1) * this.pageSize + this.pageSize
    );
  }

  private get getCurrentPage4() {
    if (this.articles4.length === 0) {
      return this.articles4;
    }
    return this.articles4.slice(
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
    console.log("ArticlesView mounted");

    let myID = authenticationService.getUserID();

    articleService
      .getMyArticles(myID)
      .then((response) => {
        this.articles1 = response.data;
        this.currentPage1 = 1;

        this.pages1 = Math.ceil(
          Math.max(this.articles1.length / this.pageSize, 1)
        );

        console.log(this.articles1);

        articleService
          .getFriendsArticles(myID)
          .then((response) => {
            this.articles2 = response.data;
            this.currentPage2 = 1;

            this.pages2 = Math.ceil(
              Math.max(this.articles2.length / this.pageSize, 1)
            );

            console.log(this.articles2);

            articleService
              .getFriendsLikedArticles(myID)
              .then((response) => {
                this.articles3 = response.data;
                this.currentPage3 = 1;

                this.pages3 = Math.ceil(
                  Math.max(this.articles3.length / this.pageSize, 1)
                );

                console.log(this.articles3);

                articleService
                  .getRecommendedArticles(myID)
                  .then((response) => {
                    this.articles4 = response.data;
                    this.currentPage4 = 1;

                    this.pages4 = Math.ceil(
                      Math.max(this.articles4.length / this.pageSize, 1)
                    );

                    console.log(this.articles4);
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

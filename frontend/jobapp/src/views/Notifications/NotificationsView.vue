<template>
  <div>
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />
    <section class="site-section" id="next-section">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 mb-4">
            <div class="block__87154">
              <blockquote>
                <p>
                  &ldquo;Ipsum harum assumenda in eum vel eveniet numquam cumque
                  vero vitae enim cupiditate deserunt eligendi officia modi
                  consectetur. Expedita tempora quos nobis earum hic ex
                  asperiores quisquam optio nostrum sit&rdquo;
                </p>
              </blockquote>
              <div class="block__91147 d-flex align-items-center">
                <figure class="mr-4">
                  <img
                    src="images/person_1.jpg"
                    alt="Image"
                    class="img-fluid"
                  />
                </figure>
                <div>
                  <h3>Elisabeth Smith</h3>
                  <span class="position">Creative Director</span><br>
                  <span class="mr-2">Accept</span>
                  <span class="mr-2">Reject</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import ArticlesSection from "@/components/areas/ArticlesSection.vue";
// import articleService from "@/service/ArticleService";
// import authenticationService from "@/service/AuthenticationService";

@Component({
  components: {
    TitleSection,
    ArticlesSection,
  },
})
export default class NotificationsView extends Vue {
  private breadcrumbList = ["Notifications"];
  private componentCaption = "Notifications";
  private pageSize = 3;

  private currentPage = 1;
  private pages = 1;
  public articles = [];

  private get getCurrentPage1() {
    if (this.articles.length === 0) {
      return this.articles;
    }
    return this.articles.slice(
      (this.currentPage - 1) * this.pageSize,
      (this.currentPage - 1) * this.pageSize + this.pageSize
    );
  }

  private changePage(i: number) {
    this.currentPage = i;
    console.log("page#1 changed to " + i);
  }

  mounted(): void {
    // console.log("ArticlesView mounted");
    // let myID = authenticationService.getUserID();
    // articleService
    //   .getMyArticles(myID)
    //   .then((response) => {
    //     this.articles1 = response.data;
    //     this.currentPage1 = 1;
    //     this.pages1 = Math.ceil(
    //       Math.max(this.articles1.length / this.pageSize, 1)
    //     );
    //     console.log(this.articles1);
    //     articleService
    //       .getFriendsArticles(myID)
    //       .then((response) => {
    //         this.articles2 = response.data;
    //         this.currentPage2 = 1;
    //         this.pages2 = Math.ceil(
    //           Math.max(this.articles2.length / this.pageSize, 1)
    //         );
    //         console.log(this.articles2);
    //         articleService
    //           .getFriendsLikedArticles(myID)
    //           .then((response) => {
    //             this.articles3 = response.data;
    //             this.currentPage3 = 1;
    //             this.pages3 = Math.ceil(
    //               Math.max(this.articles3.length / this.pageSize, 1)
    //             );
    //             console.log(this.articles3);
    //           })
    //           .catch((ex) => {
    //             console.log(ex);
    //             alert("Network error");
    //           });
    //       })
    //       .catch((ex) => {
    //         console.log(ex);
    //         alert("Network error");
    //       });
    //   })
    //   .catch((ex) => {
    //     console.log(ex);
    //     alert("Network error");
    //   });
  }
}
</script>
